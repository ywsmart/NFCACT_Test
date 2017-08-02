package com.ontime.pageobjects;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Function：商品编辑页面类
 * Created by yawa1hz1 on 2017/3/28 11:09.
 */
public class ProductPage {
    WebDriver driver;
    String url;

    public ProductPage(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;
        // 加载页面
        this.driver.get(this.url);
    }

    public static class getElement {

        // 通过XPATH 获取页面元素
        public static final String NO_INPUT_ID = "no";// 商品编号输入框
        public static final String NAME_INPUT_ID = "name";// 商品名称输入框
        public static final String SPEC_INPUT_ID = "spec";// 商品规格输入框
        public static final String UNIT_INPUT_ID = "unit";// 商品单位输入框
        public static final String BRAND_INPUT_ID = "brand";// 商品品牌输入框
        public static final String LONGNAME_INPUT_ID = "longName";// 商品长名称输入框
        public static final String ORIGIN_INPUT_ID = "origin";// 商品产地输入框
        public static final String MANUFACTURER_INPUT_ID = "manufacturer";// 商品厂家输入框
        public static final String PRICE_INPUT_ID = "price";// 商品价格输入框
        public static final String PRODUCTDATA_INPUT_ID = "productDate";// 商品生产日期输入框
        public static final String TODAY_NUTTON_ID = "dpTodayInput";// 商品生产日期今日按钮
        public static final String DATEOK_NUTTON_ID = "dpOkInput";// 商品生产日期确定按钮
        public static final String EXPIRYDATA_INPUT_ID = "expiryDate";// 商品保质期输入框
        public static final String PROURL_INPUT_ID = "url";// 商品链接输入框
        public static final String REMARK_INPUT_ID = "remark";// 商品备注输入框
        public static final String ADDFILE_BUTTON_ID = "file";// 商品主图添加按钮
        public static final String ADDIMG_BUTTON_CLASSNAME = "addImgBtn";// 商品附件图片“+图片”按钮fileImage
        public static final String ADDIMG_INPUT_BUTTON_ID = "fileImage";
        public static final String ADDIMG_FILE_PICKER_BUTTON_CLASSNAME = "filePicker";// 商品附件图片“点击上传”按钮
        public static final String ADDIMG_UPLOAD_BUTTON_CLASSNAME = "upload_btn";// 商品附件图片“开始上传”按钮
        public static final String ADDIMG_OK_BUTTON_XPATH = "//*[@id='addPosProductFrom']/div/button[1]";// 商品附件图片“确定”按钮
        public static final String SAVEDATA_BUTTON_ID = "saveData";// 保存按钮
        public static final String IFRAME_ID = "iframe";// 表单
        public static final String OPERATE_BUTTON_XPATH1 = "//button[@type='button']";// 第一个操作按钮
        public static final String EDIT_BUTTON_LINKTEXT = "编辑";// 操作后的编辑按钮
    }

    // 判断编辑结果
    public static boolean addEditResult(ProductPage productPage, String expectText) throws InterruptedException {
        if (Objects.equals("skip", expectText)) {
            System.out.println("==>skip expectText");
            return true;
        } else if (Objects.equals("nomaxlength16", expectText)) {
            return productPage.noResultTextLength() == 16;
        } else if (Objects.equals("namemaxlength64", expectText)) {
            return productPage.nameResultTextLength() == 64;
        } else if (Objects.equals("specmaxlength16", expectText)) {
            return productPage.specResultTextLength() == 16;
        } else if (Objects.equals("unitmaxlength16", expectText)) {
            return productPage.unitResultTextLength() == 16;
        } else if (Objects.equals("brandmaxlength32", expectText)) {
            return productPage.brandResultTextLength() == 32;
        } else if (Objects.equals("longnamemaxlength128", expectText)) {
            return productPage.longNameResultTextLength() ==128;
        } else if (Objects.equals("originmaxlength64", expectText)) {
            return productPage.originResultTextLength() == 64;
        } else if (Objects.equals("manufacturermaxlength32", expectText)) {
            return productPage.manufacturerResultTextLength() == 32;
        } else if (Objects.equals("pricemaxlength17", expectText)) {// 包含小数点
            return productPage.priceResultTextLength() == 17;
        } else if (Objects.equals("expirydatamaxlength16", expectText)) {
            return productPage.expiryDateResultTextLength() == 16;
        } else if (Objects.equals("prourlmaxlength128", expectText)) {
            return productPage.proUrlResultTextLength() == 128;
        } else if (Objects.equals("remarkmaxlength256", expectText)) {
            return productPage.remarkResultTextLength() == 256;
        } else {
            productPage.productPageIframeIn();
            boolean bl = productPage.driver.getPageSource().contains(expectText);
            productPage.productPageIframeOut();
            return bl;
        }
    }

    // 从Excel文件获取测试数据的静态方法
    public static Object[][] getTestData(String filePath, String fileName
            , String sheetName) throws IOException {
        // 根据参数传入的数据文件路径和文件名称，组合出Excel数据文件的绝对路径
        // 声明一个File文件对象
        File file = new File(filePath + "\\" + fileName);
        // 创建FileInputSteam对象用于读取Excel文件
        FileInputStream inputStream = new FileInputStream(file);
        // 声明Wordbook对象
        Workbook workbook = null;
        // 获取文件名参数的扩展名，判断是.xlsx文件还是.xls文件
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        // 判断文件类型如果是.xlsx，则使用XSSFWorkbook对象进行实例化
        // 判断文件类型如果是.xls，则使用SSFWorkbook对象进行实例化
        if (fileExtensionName.equals(".xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (fileExtensionName.equals(".xls")) {
            workbook = new HSSFWorkbook(inputStream);
        }
        // 通过sheetName参数，生成Sheet对象
        assert workbook != null;
        Sheet sheet = workbook.getSheet(sheetName);
        // 获取Excel数据文件Sheet1中数据的行数，getLastRowNum方法获取数据的最后一行行号
        // getFirstRowNum方法获取数据的第一行行号，相减之后算出数据的行数
        // 注意：Excel文件的行号和列号都是从0开始的
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        // 创建名为records的list对象来存储从Excel数据文件读取的数据
        List<Object[]> records = new ArrayList<Object[]>();
        // 使用两个for循环遍历Excel数据文件的所有数据（除了第一行，第一行是数据列名称）
        // 所有i从1开始，而不是0
        for (int i = 1; i < rowCount + 1; i++) {
            // 使用getRow方法获取行对象
            Row row = sheet.getRow(i);
            // 声明一个数组，用来存储Excel数据文件每行中的3个数据，数组的大小用getLastCellNum办法来进行动态声明
            // 实现测试数据个数和数组大小相一致
            String fields[] = new String[sheet.getRow(0).getLastCellNum()];
            for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                // 调用getCell和getStringCellValue方法获取Excel文件中的单元格数据
                if (row.getCell(j) == null) {
                    fields[j] = "";
                } else {
//                    fields[j] = row.getCell(j).getStringCellValue();
                    fields[j] = row.getCell(j).toString();
                }
            }
            // 将fields的数据对象存储到records的list中
            records.add(fields);
        }
        // 定义函数返回值，即Object[][]
        // 将存储测试数据的list转换为一个Object的二维数组
        Object[][] results = new Object[records.size()][];
        // 设置二维数组每行的值，每行是一个Object对象
        for (int i = 0; i < records.size(); i++) {
            results[i] = records.get(i);
        }
        return results;
    }

    // 获取页面标题
    public String getTitle() {
        return this.driver.getTitle();
    }

    // 商品编号输入框
    public void noInput(String no) {
        WebElement el = this.driver.findElement(By
                .id(getElement.NO_INPUT_ID));
        el.clear();
        el.sendKeys(no);
    }

    // 商品名称输入框
    public void nameInput(String name) {
        WebElement el = this.driver.findElement(By
                .id(getElement.NAME_INPUT_ID));
        el.clear();
        el.sendKeys(name);
    }

    // 商品规格输入框
    public void specInput(String spec) {
        WebElement el = this.driver.findElement(By
                .id(getElement.SPEC_INPUT_ID));
        el.clear();
        el.sendKeys(spec);
    }

    // 商品单位输入框
    public void unitInput(String unit) {
        WebElement el = this.driver.findElement(By
                .id(getElement.UNIT_INPUT_ID));
        el.clear();
        el.sendKeys(unit);
    }

    // 商品品牌输入框
    public void brandInput(String brand) {
        WebElement el = this.driver.findElement(By
                .id(getElement.BRAND_INPUT_ID));
        el.clear();
        el.sendKeys(brand);
    }

    // 商品长名称输入框
    public void longNameInput(String longName) {
        WebElement el = this.driver.findElement(By
                .id(getElement.LONGNAME_INPUT_ID));
        el.clear();
        el.sendKeys(longName);
    }

    // 商品产地输入框
    public void originInput(String origin) {
        WebElement el = this.driver.findElement(By
                .id(getElement.ORIGIN_INPUT_ID));
        el.clear();
        el.sendKeys(origin);
    }

    // 商品厂家输入框
    public void manufacturerInput(String manufacturer) {
        WebElement el = this.driver.findElement(By
                .id(getElement.MANUFACTURER_INPUT_ID));
        el.clear();
        el.sendKeys(manufacturer);
    }

    // 商品价格输入框
    public void priceInput(String price) {
        WebElement el = this.driver.findElement(By
                .id(getElement.PRICE_INPUT_ID));
        el.clear();
        el.sendKeys(price);
    }

    // 商品生产日期输入框
    public void productDataInput(String productDate) throws Exception {
        // 调用removeAttribute方法删除文本框的只读限制
        WebElement dateInput = driver.findElement(By.id(getElement.PRODUCTDATA_INPUT_ID));
        removeAttribute(driver, dateInput, "readonly");
        // 输入日期20170327格式
        WebElement el = this.driver.findElement(By
                .id(ProductPage.getElement.PRODUCTDATA_INPUT_ID));
        el.clear();
        el.sendKeys(productDate);
    }

    // 增加和修改页面元素属性的封装方法
    public void setAttribute(WebDriver driver, WebElement element, String attributeName, String value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // 调用JavaScript代码修改页面属性
        // arguments[0],arguments[1],arguments[2]分别被element,attributeName,value参数替换并执行
        js.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element, attributeName, value);
    }

    // 删除页面元素属性的封装方法
    public void removeAttribute(WebDriver driver, WebElement element, String attributeName) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // 调用JavaScript代码删除页面元素的属性
        // arguments[0],arguments[1]分别被element,attributeName参数替换并执行
        js.executeScript("arguments[0].removeAttribute(arguments[1],arguments[2])", element, attributeName);
    }

    // 商品保质期输入框
    public void expiryDataInput(String expiryData) {
        WebElement el = this.driver.findElement(By
                .id(ProductPage.getElement.EXPIRYDATA_INPUT_ID));
        el.clear();
        el.sendKeys(expiryData);
    }

    // 商品链接输入框
    public void proUrlInput(String proUrl) {
        WebElement el = this.driver.findElement(By
                .id(ProductPage.getElement.PROURL_INPUT_ID));
        el.clear();
        el.sendKeys(proUrl);
    }

    // 商品备注输入框
    public void remarkInput(String remark) {
        WebElement el = this.driver.findElement(By
                .id(ProductPage.getElement.REMARK_INPUT_ID));
        el.clear();
        el.sendKeys(remark);
    }

    // 商品主图上传按钮
    public void addFileButton(String imgUrl1) throws Exception {
        if (imgUrl1.equals("")) {
            return;
        } else {
            this.driver.findElement(By
                    .id(ProductPage.getElement.ADDFILE_BUTTON_ID))
                    .sendKeys(imgUrl1);
            Thread.sleep(1000);// 避免错误，需等待
        }
    }

    // 商品附图上传按钮，以后有机会再优化此过程，先用固定等待时间过度一下
    public void addImgButton(String imgUrl2) throws Exception {
        if (imgUrl2.equals("")) {
            return;
        } else {
            this.driver.findElement(By
                    .className(ProductPage.getElement.ADDIMG_BUTTON_CLASSNAME))
                    .click();
            // 更改input的属性为显示
            JavascriptExecutor j = (JavascriptExecutor) driver;
            j.executeScript("document.getElementById('" + getElement.ADDIMG_INPUT_BUTTON_ID
                    + "').style.display='block'; ");
            this.driver.findElement(By
                    .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                    .sendKeys(imgUrl2);
            Thread.sleep(1000);// 避免错误，需等待
            this.driver.findElement(By
                    .className(getElement.ADDIMG_UPLOAD_BUTTON_CLASSNAME))
                    .click();
            Thread.sleep(1000);// 避免错误，需等待
            this.driver.findElement(By
                    .xpath(getElement.ADDIMG_OK_BUTTON_XPATH))
                    .click();
            Thread.sleep(1000);// 避免错误，需等待
        }
    }

    // 商品保存按钮
    public void saveButton() {
        this.driver.findElement(By
                .id(getElement.SAVEDATA_BUTTON_ID))
                .click();
    }

    // 表单的进入
    public void productPageIframeIn() {
        WebElement xf = this.driver.findElement(By
                .id(getElement.IFRAME_ID));
        this.driver.switchTo().frame(xf);
    }

    // 表单的退出
    public void productPageIframeOut() {
        this.driver.switchTo().defaultContent();
    }

    // 商品编号缺省文本
    public boolean noDefaultText() {
        productPageIframeIn();
        WebElement el = this.driver.findElement(By
                .id(getElement.NO_INPUT_ID));
        String text = el.getAttribute("placeholder");
        productPageIframeOut();
        return text.equals("商品编号");
    }

    // 商品名称缺省文本
    public boolean nameDefaultText() {
        productPageIframeIn();
        WebElement el = this.driver.findElement(By
                .id(getElement.NAME_INPUT_ID));
        String text = el.getAttribute("placeholder");
        productPageIframeOut();
        return text.equals("商品名称");
    }

    // 商品规格缺省文本
    public boolean specDefaultText() {
        productPageIframeIn();
        WebElement el = this.driver.findElement(By
                .id(getElement.SPEC_INPUT_ID));
        String text = el.getAttribute("placeholder");
        productPageIframeOut();
        return text.equals("规格");
    }

    // 商品单位缺省文本
    public boolean unitDefaultText() {
        productPageIframeIn();
        WebElement el = this.driver.findElement(By
                .id(getElement.UNIT_INPUT_ID));
        String text = el.getAttribute("placeholder");
        productPageIframeOut();
        return text.equals("单位");
    }

    // 商品品牌缺省文本
    public boolean brandDefaultText() {
        productPageIframeIn();
        WebElement el = this.driver.findElement(By
                .id(getElement.BRAND_INPUT_ID));
        String text = el.getAttribute("placeholder");
        productPageIframeOut();
        return text.equals("品牌");
    }

    // 商品长名称缺省文本
    public boolean longNameDefaultText() {
        productPageIframeIn();
        WebElement el = this.driver.findElement(By
                .id(getElement.LONGNAME_INPUT_ID));
        String text = el.getAttribute("placeholder");
        productPageIframeOut();
        return text.equals("长名称");
    }

    // 商品产地缺省文本
    public boolean originDefaultText() {
        productPageIframeIn();
        WebElement el = this.driver.findElement(By
                .id(getElement.ORIGIN_INPUT_ID));
        String text = el.getAttribute("placeholder");
        productPageIframeOut();
        return text.equals("产地");
    }

    // 商品厂家缺省文本
    public boolean manufacturerDefaultText() {
        productPageIframeIn();
        WebElement el = this.driver.findElement(By
                .id(getElement.MANUFACTURER_INPUT_ID));
        String text = el.getAttribute("placeholder");
        productPageIframeOut();
        return text.equals("厂家");
    }

    // 商品价格缺省文本
    public boolean priceDefaultText() {
        productPageIframeIn();
        WebElement el = this.driver.findElement(By
                .id(getElement.PRICE_INPUT_ID));
        String text = el.getAttribute("placeholder");
        productPageIframeOut();
        return text.equals("价格");
    }

    // 生产日期缺省文本
    public boolean productDateDefaultText() {
        productPageIframeIn();
        WebElement el = this.driver.findElement(By
                .id(getElement.PRODUCTDATA_INPUT_ID));
        String text = el.getAttribute("placeholder");
        productPageIframeOut();
        return text.equals("生产日期");
    }

    // 保质期缺省文本
    public boolean expiryDateDefaultText() {
        productPageIframeIn();
        WebElement el = this.driver.findElement(By
                .id(getElement.EXPIRYDATA_INPUT_ID));
        String text = el.getAttribute("placeholder");
        productPageIframeOut();
        return text.equals("保质期");
    }

    // 商品链接缺省文本
    public boolean proUrlDefaultText() {
        productPageIframeIn();
        WebElement el = this.driver.findElement(By
                .id(getElement.PROURL_INPUT_ID));
        String text = el.getAttribute("placeholder");
        productPageIframeOut();
        return text.equals("商品链接");
    }

    // 商品备注缺省文本
    public boolean remarkDefaultText() {
        productPageIframeIn();
        WebElement el = this.driver.findElement(By
                .id(getElement.REMARK_INPUT_ID));
        String text = el.getAttribute("placeholder");
        productPageIframeOut();
        return text.equals("备注");
    }

    // 保存后商品编号文本长度
    public int noResultTextLength() throws InterruptedException {
        productPageIframeIn();
        this.driver.findElement(By
                .xpath(getElement.OPERATE_BUTTON_XPATH1))
                .click();
        Thread.sleep(1000);
        this.driver.findElement(By
                .linkText(getElement.EDIT_BUTTON_LINKTEXT))
                .click();
        Thread.sleep(1000);
        WebElement el = this.driver.findElement(By
                .id(getElement.NO_INPUT_ID));
        String text = el.getAttribute("value");
        productPageIframeOut();
        return text.length();
    }

    // 保存后商品名称文本长度
    public int nameResultTextLength() throws InterruptedException {
        productPageIframeIn();
        this.driver.findElement(By
                .xpath(getElement.OPERATE_BUTTON_XPATH1))
                .click();
        Thread.sleep(1000);
        this.driver.findElement(By
                .linkText(getElement.EDIT_BUTTON_LINKTEXT))
                .click();
        WebElement el = this.driver.findElement(By
                .id(getElement.NAME_INPUT_ID));
        String text = el.getAttribute("value");
        productPageIframeOut();
        return text.length();
    }

    // 保存后商品规格文本长度
    public int specResultTextLength() throws InterruptedException {
        productPageIframeIn();
        this.driver.findElement(By
                .xpath(getElement.OPERATE_BUTTON_XPATH1))
                .click();
        Thread.sleep(1000);
        this.driver.findElement(By
                .linkText(getElement.EDIT_BUTTON_LINKTEXT))
                .click();
        WebElement el = this.driver.findElement(By
                .id(getElement.SPEC_INPUT_ID));
        String text = el.getAttribute("value");
        productPageIframeOut();
        return text.length();
    }

    // 保存后商品单位文本长度
    public int unitResultTextLength() throws InterruptedException {
        productPageIframeIn();
        this.driver.findElement(By
                .xpath(getElement.OPERATE_BUTTON_XPATH1))
                .click();
        Thread.sleep(1000);
        this.driver.findElement(By
                .linkText(getElement.EDIT_BUTTON_LINKTEXT))
                .click();
        WebElement el = this.driver.findElement(By
                .id(getElement.UNIT_INPUT_ID));
        String text = el.getAttribute("value");
        productPageIframeOut();
        return text.length();
    }

    // 保存后商品品牌文本长度
    public int brandResultTextLength() throws InterruptedException {
        productPageIframeIn();
        this.driver.findElement(By
                .xpath(getElement.OPERATE_BUTTON_XPATH1))
                .click();
        Thread.sleep(1000);
        this.driver.findElement(By
                .linkText(getElement.EDIT_BUTTON_LINKTEXT))
                .click();
        WebElement el = this.driver.findElement(By
                .id(getElement.BRAND_INPUT_ID));
        String text = el.getAttribute("value");
        productPageIframeOut();
        return text.length();
    }

    // 保存后商品长名称文本长度
    public int longNameResultTextLength() throws InterruptedException {
        productPageIframeIn();
        this.driver.findElement(By
                .xpath(getElement.OPERATE_BUTTON_XPATH1))
                .click();
        Thread.sleep(1000);
        this.driver.findElement(By
                .linkText(getElement.EDIT_BUTTON_LINKTEXT))
                .click();
        WebElement el = this.driver.findElement(By
                .id(getElement.LONGNAME_INPUT_ID));
        String text = el.getAttribute("value");
        productPageIframeOut();
        return text.length();
    }

    // 保存后商品产地文本长度
    public int originResultTextLength() throws InterruptedException {
        productPageIframeIn();
        this.driver.findElement(By
                .xpath(getElement.OPERATE_BUTTON_XPATH1))
                .click();
        Thread.sleep(1000);
        this.driver.findElement(By
                .linkText(getElement.EDIT_BUTTON_LINKTEXT))
                .click();
        WebElement el = this.driver.findElement(By
                .id(getElement.ORIGIN_INPUT_ID));
        String text = el.getAttribute("value");
        productPageIframeOut();
        return text.length();
    }

    // 保存后商品厂家文本长度
    public int manufacturerResultTextLength() throws InterruptedException {
        productPageIframeIn();
        this.driver.findElement(By
                .xpath(getElement.OPERATE_BUTTON_XPATH1))
                .click();
        Thread.sleep(1000);
        this.driver.findElement(By
                .linkText(getElement.EDIT_BUTTON_LINKTEXT))
                .click();
        WebElement el = this.driver.findElement(By
                .id(getElement.MANUFACTURER_INPUT_ID));
        String text = el.getAttribute("value");
        productPageIframeOut();
        return text.length();
    }

    // 保存后商品价格文本长度
    public int priceResultTextLength() throws InterruptedException {
        productPageIframeIn();
        this.driver.findElement(By
                .xpath(getElement.OPERATE_BUTTON_XPATH1))
                .click();
        Thread.sleep(1000);
        this.driver.findElement(By
                .linkText(getElement.EDIT_BUTTON_LINKTEXT))
                .click();
        WebElement el = this.driver.findElement(By
                .id(getElement.PRICE_INPUT_ID));
        String text = el.getAttribute("value");
        productPageIframeOut();
        return text.length();
    }

    // 保存后生产日期文本长度
    public int productDateResultTextLength() throws InterruptedException {
        productPageIframeIn();
        this.driver.findElement(By
                .xpath(getElement.OPERATE_BUTTON_XPATH1))
                .click();
        Thread.sleep(1000);
        this.driver.findElement(By
                .linkText(getElement.EDIT_BUTTON_LINKTEXT))
                .click();
        WebElement el = this.driver.findElement(By
                .id(getElement.PRODUCTDATA_INPUT_ID));
        String text = el.getAttribute("value");
        productPageIframeOut();
        return text.length();
    }

    // 保存后保质期文本长度
    public int expiryDateResultTextLength() throws InterruptedException {
        productPageIframeIn();
        this.driver.findElement(By
                .xpath(getElement.OPERATE_BUTTON_XPATH1))
                .click();
        Thread.sleep(1000);
        this.driver.findElement(By
                .linkText(getElement.EDIT_BUTTON_LINKTEXT))
                .click();
        WebElement el = this.driver.findElement(By
                .id(getElement.EXPIRYDATA_INPUT_ID));
        String text = el.getAttribute("value");
        productPageIframeOut();
        return text.length();
    }

    // 保存后商品链接文本长度
    public int proUrlResultTextLength() throws InterruptedException {
        productPageIframeIn();
        this.driver.findElement(By
                .xpath(getElement.OPERATE_BUTTON_XPATH1))
                .click();
        Thread.sleep(1000);
        this.driver.findElement(By
                .linkText(getElement.EDIT_BUTTON_LINKTEXT))
                .click();
        WebElement el = this.driver.findElement(By
                .id(getElement.PROURL_INPUT_ID));
        String text = el.getAttribute("value");
        productPageIframeOut();
        return text.length();
    }

    // 保存后商品备注文本长度
    public int remarkResultTextLength() throws InterruptedException {
        productPageIframeIn();
        this.driver.findElement(By
                .xpath(getElement.OPERATE_BUTTON_XPATH1))
                .click();
        Thread.sleep(1000);
        this.driver.findElement(By
                .linkText(getElement.EDIT_BUTTON_LINKTEXT))
                .click();
        WebElement el = this.driver.findElement(By
                .id(getElement.REMARK_INPUT_ID));
        String text = el.getAttribute("value");
        productPageIframeOut();
        return text.length();
    }

    // 判断提示语结果
    public boolean MessageResult(String messageText) {
        try {
            productPageIframeIn();
            boolean bl = this.driver.findElement(By
                    .xpath("//div[contains(text(),'" + messageText + "')]")).isDisplayed();
            productPageIframeOut();
            return bl;
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }

    }

    // 获取商品编号输入框文本最大长度
    public String noInputTextMaxLength() {
        WebElement el = this.driver.findElement(By
                .id(getElement.NO_INPUT_ID));
        return el.getAttribute("maxlength");
    }

    // 获取商品名称输入框文本最大长度
    public String nameInputTextMaxLength() {
        WebElement el = this.driver.findElement(By
                .id(getElement.NAME_INPUT_ID));
        return el.getAttribute("maxlength");
    }

    // 获取商品规格输入框文本最大长度
    public String specInputTextMaxLength() {
        WebElement el = this.driver.findElement(By
                .id(getElement.SPEC_INPUT_ID));
        return el.getAttribute("maxlength");
    }

    // 获取商品单位输入框文本最大长度
    public String unitInputTextMaxLength() {
        WebElement el = this.driver.findElement(By
                .id(getElement.UNIT_INPUT_ID));
        return el.getAttribute("maxlength");
    }

    // 获取商品品牌输入框文本最大长度
    public String brandInputTextMaxLength() {
        WebElement el = this.driver.findElement(By
                .id(getElement.BRAND_INPUT_ID));
        return el.getAttribute("maxlength");
    }

    // 获取商品长名称输入框文本最大长度
    public String longNameInputTextMaxLength() {
        WebElement el = this.driver.findElement(By
                .id(getElement.LONGNAME_INPUT_ID));
        return el.getAttribute("maxlength");
    }

    // 获取商品产地输入框文本最大长度
    public String originInputTextMaxLength() {
        WebElement el = this.driver.findElement(By
                .id(getElement.ORIGIN_INPUT_ID));
        return el.getAttribute("maxlength");
    }

    // 获取商品厂家输入框文本最大长度
    public String manufacturerInputTextMaxLength() {
        WebElement el = this.driver.findElement(By
                .id(getElement.MANUFACTURER_INPUT_ID));
        return el.getAttribute("maxlength");
    }

    // 获取商品价格输入框文本最大长度
    public String priceInputTextMaxLength() {
        WebElement el = this.driver.findElement(By
                .id(getElement.PRICE_INPUT_ID));
        return el.getAttribute("maxlength");
    }

    // 获取商品保质期输入框文本最大长度
    public String expiryDataInputTextMaxLength() {
        WebElement el = this.driver.findElement(By
                .id(ProductPage.getElement.EXPIRYDATA_INPUT_ID));
        return el.getAttribute("maxlength");
    }

    // 获取商品链接输入框文本最大长度
    public String proUrlInputTextMaxLength() {
        WebElement el = this.driver.findElement(By
                .id(ProductPage.getElement.PROURL_INPUT_ID));
        return el.getAttribute("maxlength");
    }

    // 获取商品备注输入框文本最大长度
    public String remarkInputTextMaxLength() {
        WebElement el = this.driver.findElement(By
                .id(ProductPage.getElement.REMARK_INPUT_ID));
        return el.getAttribute("maxlength");
    }

}

