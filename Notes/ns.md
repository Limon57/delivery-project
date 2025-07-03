WebElement editor = wait.until(ExpectedConditions.presenceOfElementLocated(
    By.cssSelector("div.monaco-editor")
));

String query = queryBank.getMainQuery("USCPIL14172731");

String script =
    "return (function() {" +
    "  if (!window.monaco || !monaco.editor) return 'monaco not ready';" +
    "  const editors = monaco.editor.getEditors ? monaco.editor.getEditors() : monaco.editor.getModels();" +
    "  if (!editors || editors.length === 0) return 'no editors';" +
    "  const editor = monaco.editor.getEditors ? editors[0] : monaco.editor.getModels()[0];" +
    "  if (editor.setValue) {" +
    "    editor.setValue(arguments[0]);" +
    "    return 'injected with setValue';" +
    "  } else if (monaco.editor.getModels) {" +
    "    const model = monaco.editor.getModels()[0];" +
    "    model.setValue(arguments[0]);" +
    "    return 'injected via model';" +
    "  }" +
    "  return 'injection failed';" +
    "})();";

Object result = ((JavascriptExecutor) driver).executeScript(script, query);
System.out.println("Injection result: " + result);

