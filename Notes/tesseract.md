# ✅ Tesseract Java OCR Cheat Sheet

## 1. Optical Character Recognition (OCR)
```java
Tesseract tesseract = new Tesseract();
tesseract.setDatapath("tessdata");
String result = tesseract.doOCR(new File("image.png"));
System.out.println(result);
```
**Explanation:** Basic OCR to extract text from a standard image using the default language.

---

## 2. Recognize Text from Scanned Documents
```java
String result = tesseract.doOCR(new File("scanned_document.jpg"));
System.out.println(result);
```
**Explanation:** Same as OCR, especially useful for high-resolution scans of paper documents.

---

## 3. Extract Text from Images (JPEG, PNG, TIFF, etc.)
```java
BufferedImage img = ImageIO.read(new File("sample.jpg"));
String text = tesseract.doOCR(img);
```
**Explanation:** Accepts common image formats such as PNG, JPG, BMP, and TIFF.

---

## 4. Support for Over 100 Languages
```java
tesseract.setLanguage("eng+spa+deu");
```
**Explanation:** Set multiple languages. Ensure `.traineddata` files are present in `tessdata`.

---

## 5. Recognize Multiple Languages in the Same Image
```java
tesseract.setLanguage("eng+chi_sim");
```
**Explanation:** OCR can detect and extract multilingual content from the same image.

---

## 6. Detect and Extract Text from Rotated or Skewed Images
```java
// Tesseract doesn't deskew images itself — use preprocessing tools like ImageMagick.
```
**Explanation:** Images must be deskewed before processing. Use tools like Leptonica or ImageMagick.

---

## 7. Layout Analysis (Detect Blocks, Paragraphs, Lines, Words)
```java
tesseract.setOcrEngineMode(ITessAPI.TessOcrEngineMode.OEM_LSTM_ONLY);
tesseract.setPageSegMode(ITessAPI.TessPageSegMode.PSM_AUTO);
```
**Explanation:** Uses intelligent page layout analysis to separate paragraphs, lines, and words.

---

## 8. Support for Training Custom OCR Models
```bash
# Command-line only:
tesseract myimage.png output --psm 6 lstm.train
```
**Explanation:** Model training is not done in Java. Generate `.traineddata` using CLI and place in `tessdata`.

---

## 9. Recognize Handwritten Text (Limited Support)
```java
tesseract.setLanguage("eng");
String result = tesseract.doOCR(new File("handwritten_note.jpg"));
System.out.println(result);
```
**Explanation:** Tesseract can handle printed handwriting to some extent, but cursive is poorly supported.

---

## 10. Recognize and Extract Digits and Numbers
```java
tesseract.setLanguage("eng");
tesseract.setTessVariable("tessedit_char_whitelist", "0123456789");
```
**Explanation:** Use a whitelist to restrict OCR to digits only.

---

## 11. Detect Orientation and Script of Text
```bash
# Command-line only:
tesseract image.png stdout --psm 0
```
**Explanation:** Use `--psm 0` to let Tesseract detect the image's orientation and script automatically.

---

## 12. PDF Output with Embedded OCR Text
```bash
# Command-line only:
tesseract input.png output pdf
```
**Explanation:** Generates a searchable PDF with text layer embedded under the image.

---

## 13. HOCR Output (HTML with OCR Info)
```java
tesseract.setHocr(true);
String hocr = tesseract.doOCR(new File("page.png"));
System.out.println(hocr);
```
**Explanation:** Outputs OCR results as HTML with word-level bounding boxes and layout.

---

## 14. ALTO XML Output
```bash
# Command-line only:
tesseract image.png output -c tessedit_create_alto=1
```
**Explanation:** Outputs results in ALTO XML format, useful for archival metadata systems.

---

## 📌 Print a Specific Line from OCR Result
```java
String result = tesseract.doOCR(new File("image.png"));
String[] lines = result.split("\n");
System.out.println("Line 2: " + lines[1]); // change index as needed
```
**Explanation:** Split the full OCR text by line breaks and access specific lines by array index.

