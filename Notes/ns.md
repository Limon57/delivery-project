public class VerdictCreator {
    OpenObserveQueryHandler bigQueryJson = new OpenObserveQueryHandler();
    JsonSessionParser jsonParser = new JsonSessionParser();
    JsonResultHelper jsonResults = new JsonResultHelper();

    public String getVerdict(String locationId, String alertTime) throws IOException {
        String verdict;

        // 1. Run timed query using locationId and alertTime
        bigQueryJson.runTimedNormalQuery(locationId, alertTime);

        if (bigQueryJson.getNoResult()) {
            // 2. No sessions completed, check actively down stations
            bigQueryJson.runActivelyDownStationQuery();

            if (bigQueryJson.getActiveDownStationsQueryResults() == null) {
                verdict = "There have been no completed sessions from the time of the alert, however there are no actively down stations. Check the website manually to confirm that the station is up and running.";
            } else {
                // Parse actively down station result
                jsonParser.splitJson(bigQueryJson.getActiveDownStationsQueryResults());
                String locationName = jsonParser.getValue("facility_station_evse", jsonParser.peekCustomerSession());

                boolean isMatch = false;
                while (!jsonParser.customerSessionIsEmpty()) {
                    String activeName = jsonParser.getValue("facility_station_evse", jsonParser.peekCustomerSession());
                    if (activeName != null && activeName.contains(locationName)) {
                        isMatch = true;
                        break;
                    }
                    jsonParser.getNextCustomerSession();
                }

                if (isMatch) {
                    verdict = "There have been no completed sessions from the time of the alert. The location " + locationName + " is currently down since it appears in the Actively down results. Check the Website to confirm this.";
                } else {
                    verdict = "There have been no completed sessions from the time of the alert. However the location " + locationName + " is not in the actively down station results. Check the website to confirm that the station is running.";
                }
            }
        } else {
            // 3. There was a completed session
            String stationName = jsonResults.getFirstResultVariable(bigQueryJson.getTimedNormalResult(), "facility_station_evse");

            // 4. Check if station appears in outage history
            bigQueryJson.runOutageQuery(locationId);
            jsonParser.splitJson(bigQueryJson.getOutageQueryResults());

            boolean stationIsOnOutageResults = false;
            while (!jsonParser.customerSessionIsEmpty()) {
                if (Objects.equals(stationName, jsonParser.getValue("facility_station_evse", jsonParser.peekCustomerSession()))) {
                    stationIsOnOutageResults = true;
                    break;
                }
                jsonParser.getNextCustomerSession();
            }

            if (stationIsOnOutageResults) {
                verdict = "The location " + stationName + " was out for some time. However from the time of the alert, this location did have a completed session. Check on the website to confirm that it is up and running.";
            } else {
                verdict = "The location " + stationName + " has had a completed session from the time of the alert. This location does not appear to be down or to have a recent outage.";
            }
        }

        return verdict;
    }
}
