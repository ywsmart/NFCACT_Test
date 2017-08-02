package com.ontime.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Objects;

/**
 * Function：新建商品类
 * Created by yawa1hz1 on 2017/3/24 13:58.
 */
public class AddPage extends ProductPage {
    public static class getElement {

        // 通过XPATH 获取页面元素
        public static final String ADD_BUTTON_ID = "addData";// 新建按钮
        public static final String PRICE_TEXT_XPATH = "//td[7]";// 商品列表的第一个商品价格
    }
    private WebDriver driver;

    private String url;
    public AddPage(WebDriver driver, String url) {
        super(driver, url);
        this.driver = driver;
        this.url = url;
        // 加载页面
        this.driver.get(this.url);
    }


    // 新建保存商品
    public void add1(String no, String name, String spec, String unit
            , String brand, String longName, String origin, String manufacturer
            , String price, String productDate, String expiryData, String proUrl
            , String remark, String imgUrl1, String imgUrl2) throws Exception {
        productPageIframeIn();
        addButton();
        addProText(no, name, spec, unit, brand, longName, origin, manufacturer
                , price, productDate, expiryData, proUrl, remark);
        addProductImage(imgUrl1, imgUrl2);
        saveButton();
        productPageIframeOut();
    }

    // 新建商品用于测试主图异常
    public void add3(String no, String name, String spec, String unit
            , String brand, String longName, String origin, String manufacturer
            , String price, String productDate, String expiryData, String proUrl
            , String remark, String imgUrl1) throws Exception {
        productPageIframeIn();
        addButton();
        addProText(no, name, spec, unit, brand, longName, origin, manufacturer
                , price, productDate, expiryData, proUrl, remark);
        addFileButton(imgUrl1);
        productPageIframeOut();
    }

    // 新建商品测试附图1
    public void add4(String no, String name, String spec, String unit
            , String brand, String longName, String origin, String manufacturer
            , String price, String productDate, String expiryData, String proUrl
            , String remark, String imgUrl1, String imgUrl2) throws Exception {
        productPageIframeIn();
        addButton();
        addProText(no, name, spec, unit, brand, longName, origin, manufacturer
                , price, productDate, expiryData, proUrl, remark);
        addProductImage1(imgUrl1,imgUrl2);
        productPageIframeOut();
    }

    // 新建商品测试附图2
    public void add2(String no, String name, String spec, String unit
            , String brand, String longName, String origin, String manufacturer
            , String price, String productDate, String expiryData, String proUrl
            , String remark, String imgUrl1, String imgUrl2) throws Exception {
        productPageIframeIn();
        addButton();
        addProText(no, name, spec, unit, brand, longName, origin, manufacturer
                , price, productDate, expiryData, proUrl, remark);
        addProductImage1(imgUrl1,imgUrl2);
        saveButton();
        productPageIframeOut();
    }

    // 添加商品文本内容
    public void addProText(String no, String name, String spec, String unit
            , String brand, String longName, String origin, String manufacturer
            , String price, String productDate, String expiryData, String proUrl
            , String remark) throws Exception {
        noInput(no);
        nameInput(name);
        specInput(spec);
        unitInput(unit);
        brandInput(brand);
        longNameInput(longName);
        originInput(origin);
        manufacturerInput(manufacturer);
        priceInput(price);
        productDataInput(productDate);
        expiryDataInput(expiryData);
        proUrlInput(proUrl);
        remarkInput(remark);
    }

    // 添加商品图片
    public void addProductImage(String imgUrl1, String imgUrl2) throws Exception {
        addFileButton(imgUrl1);
        addImgButton(imgUrl2);
    }

    // 添加商品图片测试附图
    public void addProductImage1(String imgUrl1, String imgUrl2) throws Exception {
        addFileButton(imgUrl1);
        addImgButton1(imgUrl2);
    }

    // 新建按钮
    public void addButton() {
        driver.findElement(By
                .id(getElement.ADD_BUTTON_ID))
                .click();
    }

    public void openAdd() {
        productPageIframeIn();
        addButton();
        productPageIframeOut();
    }

    // 商品价格输入框
    @Override
    public void priceInput(String price) {
        if (!Objects.equals(price, "")) {
            WebElement el = this.driver.findElement(By
                    .id(ProductPage.getElement.PRICE_INPUT_ID));
            el.clear();
            el.sendKeys(price);
        }
    }

    // 验证价格结果
    public boolean addPriceResult(String expectText) {
        productPageIframeIn();
        WebElement el = this.driver.findElement(By.xpath(getElement.PRICE_TEXT_XPATH));
        boolean bl = el.getText().equals(expectText);
        productPageIframeOut();
        return bl;
    }

    // 测试附图临界值
    public void addImgButton1(String imgUrl2) throws Exception {
        JavascriptExecutor j = (JavascriptExecutor) driver;
        switch (imgUrl2){
            case "11张图":
                this.driver.findElement(By
                        .className(ProductPage.getElement.ADDIMG_BUTTON_CLASSNAME))
                        .click() ;
                // 更改input的属性为显示
                j.executeScript("document.getElementById('" + ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID
                        + "').style.display='block'; ");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\1.jpg");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\2.jpg");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\3.jpg");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\4.jpg");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\5.jpg");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\6.jpg");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\7.jpg");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\8.jpg");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\9.jpg");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\10.jpg");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\11.jpg");
                Thread.sleep(1000);// 避免错误，需等待
                // 开始上传按钮
                this.driver.findElement(By
                        .className(ProductPage.getElement.ADDIMG_UPLOAD_BUTTON_CLASSNAME))
                        .click();
                Thread.sleep(1000);// 避免错误，需等待
                // 确定按钮
                this.driver.findElement(By
                        .xpath(ProductPage.getElement.ADDIMG_OK_BUTTON_XPATH))
                        .click();
                Thread.sleep(1000);// 避免错误，需等待
                break;
            case "10张图":
                this.driver.findElement(By
                        .className(ProductPage.getElement.ADDIMG_BUTTON_CLASSNAME))
                        .click() ;
                // 更改input的属性为显示
                j.executeScript("document.getElementById('" + ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID
                        + "').style.display='block'; ");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\1.jpg");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\2.jpg");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\3.jpg");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\4.jpg");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\5.jpg");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\6.jpg");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\7.jpg");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\8.jpg");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\9.jpg");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\10.jpg");
                Thread.sleep(1000);// 避免错误，需等待
                // 开始上传按钮
                this.driver.findElement(By
                        .className(ProductPage.getElement.ADDIMG_UPLOAD_BUTTON_CLASSNAME))
                        .click();
                Thread.sleep(1000);// 避免错误，需等待
                // 确定按钮
                this.driver.findElement(By
                        .xpath(ProductPage.getElement.ADDIMG_OK_BUTTON_XPATH))
                        .click();
                Thread.sleep(1000);// 避免错误，需等待
                break;
            case "多图多次":
                // +图片按钮
                this.driver.findElement(By
                        .className(ProductPage.getElement.ADDIMG_BUTTON_CLASSNAME))
                        .click() ;
                // 更改input的属性为显示
                j.executeScript("document.getElementById('" + ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID
                        + "').style.display='block'; ");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\1.jpg");
                Thread.sleep(1000);// 避免错误，需等待
                // 开始上传按钮
                this.driver.findElement(By
                        .className(ProductPage.getElement.ADDIMG_UPLOAD_BUTTON_CLASSNAME))
                        .click();
                Thread.sleep(1000);// 避免错误，需等待
                // 确定按钮
                this.driver.findElement(By
                        .xpath(ProductPage.getElement.ADDIMG_OK_BUTTON_XPATH))
                        .click();
                Thread.sleep(1000);// 避免错误，需等待
                // +图片按钮
                this.driver.findElement(By
                        .className(ProductPage.getElement.ADDIMG_BUTTON_CLASSNAME))
                        .click() ;
                // 更改input的属性为显示
                j.executeScript("document.getElementById('" + ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID
                        + "').style.display='block'; ");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\2.jpg");
                Thread.sleep(1000);// 避免错误，需等待
                // 开始上传按钮
                this.driver.findElement(By
                        .className(ProductPage.getElement.ADDIMG_UPLOAD_BUTTON_CLASSNAME))
                        .click();
                Thread.sleep(1000);// 避免错误，需等待
                // 确定按钮
                this.driver.findElement(By
                        .xpath(ProductPage.getElement.ADDIMG_OK_BUTTON_XPATH))
                        .click();
                Thread.sleep(1000);// 避免错误，需等待
                // +图片按钮
                this.driver.findElement(By
                        .className(ProductPage.getElement.ADDIMG_BUTTON_CLASSNAME))
                        .click() ;
                // 更改input的属性为显示
                j.executeScript("document.getElementById('" + ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID
                        + "').style.display='block'; ");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\3.jpg");
                Thread.sleep(1000);// 避免错误，需等待
                // 开始上传按钮
                this.driver.findElement(By
                        .className(ProductPage.getElement.ADDIMG_UPLOAD_BUTTON_CLASSNAME))
                        .click();
                Thread.sleep(1000);// 避免错误，需等待
                // 确定按钮
                this.driver.findElement(By
                        .xpath(ProductPage.getElement.ADDIMG_OK_BUTTON_XPATH))
                        .click();
                Thread.sleep(1000);// 避免错误，需等待
                // +图片按钮
                this.driver.findElement(By
                        .className(ProductPage.getElement.ADDIMG_BUTTON_CLASSNAME))
                        .click() ;
                // 更改input的属性为显示
                j.executeScript("document.getElementById('" + ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID
                        + "').style.display='block'; ");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\4.jpg");
                Thread.sleep(1000);// 避免错误，需等待
                // 开始上传按钮
                this.driver.findElement(By
                        .className(ProductPage.getElement.ADDIMG_UPLOAD_BUTTON_CLASSNAME))
                        .click();
                Thread.sleep(1000);// 避免错误，需等待
                // 确定按钮
                this.driver.findElement(By
                        .xpath(ProductPage.getElement.ADDIMG_OK_BUTTON_XPATH))
                        .click();
                Thread.sleep(1000);// 避免错误，需等待
                // +图片按钮
                this.driver.findElement(By
                        .className(ProductPage.getElement.ADDIMG_BUTTON_CLASSNAME))
                        .click() ;
                // 更改input的属性为显示
                j.executeScript("document.getElementById('" + ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID
                        + "').style.display='block'; ");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\5.jpg");
                Thread.sleep(1000);// 避免错误，需等待
                // 开始上传按钮
                this.driver.findElement(By
                        .className(ProductPage.getElement.ADDIMG_UPLOAD_BUTTON_CLASSNAME))
                        .click();
                Thread.sleep(1000);// 避免错误，需等待
                // 确定按钮
                this.driver.findElement(By
                        .xpath(ProductPage.getElement.ADDIMG_OK_BUTTON_XPATH))
                        .click();
                Thread.sleep(1000);// 避免错误，需等待
                // +图片按钮
                this.driver.findElement(By
                        .className(ProductPage.getElement.ADDIMG_BUTTON_CLASSNAME))
                        .click() ;
                // 更改input的属性为显示
                j.executeScript("document.getElementById('" + ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID
                        + "').style.display='block'; ");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\6.jpg");
                Thread.sleep(1000);// 避免错误，需等待
                // 开始上传按钮
                this.driver.findElement(By
                        .className(ProductPage.getElement.ADDIMG_UPLOAD_BUTTON_CLASSNAME))
                        .click();
                Thread.sleep(1000);// 避免错误，需等待
                // 确定按钮
                this.driver.findElement(By
                        .xpath(ProductPage.getElement.ADDIMG_OK_BUTTON_XPATH))
                        .click();
                Thread.sleep(1000);// 避免错误，需等待
                // +图片按钮
                this.driver.findElement(By
                        .className(ProductPage.getElement.ADDIMG_BUTTON_CLASSNAME))
                        .click() ;
                // 更改input的属性为显示
                j.executeScript("document.getElementById('" + ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID
                        + "').style.display='block'; ");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\7.jpg");
                Thread.sleep(1000);// 避免错误，需等待
                // 开始上传按钮
                this.driver.findElement(By
                        .className(ProductPage.getElement.ADDIMG_UPLOAD_BUTTON_CLASSNAME))
                        .click();
                Thread.sleep(1000);// 避免错误，需等待
                // 确定按钮
                this.driver.findElement(By
                        .xpath(ProductPage.getElement.ADDIMG_OK_BUTTON_XPATH))
                        .click();
                Thread.sleep(1000);// 避免错误，需等待
                // +图片按钮
                this.driver.findElement(By
                        .className(ProductPage.getElement.ADDIMG_BUTTON_CLASSNAME))
                        .click() ;
                // 更改input的属性为显示
                j.executeScript("document.getElementById('" + ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID
                        + "').style.display='block'; ");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\8.jpg");
                Thread.sleep(1000);// 避免错误，需等待
                // 开始上传按钮
                this.driver.findElement(By
                        .className(ProductPage.getElement.ADDIMG_UPLOAD_BUTTON_CLASSNAME))
                        .click();
                Thread.sleep(1000);// 避免错误，需等待
                // 确定按钮
                this.driver.findElement(By
                        .xpath(ProductPage.getElement.ADDIMG_OK_BUTTON_XPATH))
                        .click();
                Thread.sleep(1000);// 避免错误，需等待
                // +图片按钮
                this.driver.findElement(By
                        .className(ProductPage.getElement.ADDIMG_BUTTON_CLASSNAME))
                        .click() ;
                // 更改input的属性为显示
                j.executeScript("document.getElementById('" + ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID
                        + "').style.display='block'; ");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\9.jpg");
                Thread.sleep(1000);// 避免错误，需等待
                // 开始上传按钮
                this.driver.findElement(By
                        .className(ProductPage.getElement.ADDIMG_UPLOAD_BUTTON_CLASSNAME))
                        .click();
                Thread.sleep(1000);// 避免错误，需等待
                // 确定按钮
                this.driver.findElement(By
                        .xpath(ProductPage.getElement.ADDIMG_OK_BUTTON_XPATH))
                        .click();
                Thread.sleep(1000);// 避免错误，需等待
                // +图片按钮
                this.driver.findElement(By
                        .className(ProductPage.getElement.ADDIMG_BUTTON_CLASSNAME))
                        .click() ;
                // 更改input的属性为显示
                j.executeScript("document.getElementById('" + ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID
                        + "').style.display='block'; ");
                this.driver.findElement(By
                        .id(ProductPage.getElement.ADDIMG_INPUT_BUTTON_ID))
                        .sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\10.jpg");
                Thread.sleep(1000);// 避免错误，需等待
                // 开始上传按钮
                this.driver.findElement(By
                        .className(ProductPage.getElement.ADDIMG_UPLOAD_BUTTON_CLASSNAME))
                        .click();
                Thread.sleep(1000);// 避免错误，需等待
                // 确定按钮
                this.driver.findElement(By
                        .xpath(ProductPage.getElement.ADDIMG_OK_BUTTON_XPATH))
                        .click();
                Thread.sleep(1000);// 避免错误，需等待
                break;
            default:
                System.out.println("“" + imgUrl2 + "”"+ "：此文本不符合预先约定，约定文本：“11张图、10张图、多图多次”");
        }
    }
}
