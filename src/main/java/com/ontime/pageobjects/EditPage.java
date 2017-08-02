package com.ontime.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Function：编辑商品类
 * Created by yawa1hz1 on 2017/3/28 10:48.
 */
public class EditPage extends ProductPage {
    public static class getElement {
        // 通过XPATH 获取页面元素
        public static final String OPERATE_BUTTON_XPATH1 = "//button[@type='button']";// 第一个操作按钮
        public static final String OPERATE_BUTTON_XPATH2 = "(//button[@type='button'])[2]";// 第二个操作按钮
        public static final String EDIT_BUTTON_LINKTEXT = "编辑";// 操作后的编辑按钮
        public static final String GOBACK_BUTTON_ID = "goBack";// 编辑商品返回按钮
    }

    public EditPage(WebDriver driver, String url) {
        super(driver, url);
        this.driver = driver;
        this.url = url;
        // 加载页面
        this.driver.get(this.url);
    }

    // 编辑保存商品
    public void edit1(String no, String name, String spec, String unit
            , String brand, String longName, String origin, String manufacturer
            , String price, String productDate, String expiryData, String proUrl
            , String remark, String imgUrl1, String imgUrl2) throws Exception {
        productPageIframeIn();
        addProText(no, name, spec, unit, brand, longName, origin, manufacturer
                , price, productDate, expiryData, proUrl, remark);
        addProductImage(imgUrl1, imgUrl2);
        saveButton();
        productPageIframeOut();
    }

    // 编辑商品用于测试主图异常
    public void edit2(String no, String name, String spec, String unit
            , String brand, String longName, String origin, String manufacturer
            , String price, String productDate, String expiryData, String proUrl
            , String remark, String imgUrl1) throws Exception {
        productPageIframeIn();
        addProText(no, name, spec, unit, brand, longName, origin, manufacturer
                , price, productDate, expiryData, proUrl, remark);
        addFileButton(imgUrl1);
        productPageIframeOut();
    }

    // 添加商品文本内容
    public void addProText(String no, String name, String spec, String unit
            , String brand, String longName, String origin, String manufacturer
            , String price, String productDate, String expiryData, String proUrl
            , String remark) throws Exception {
        operateButton1();
        Thread.sleep(1000);
        editButton();
        Thread.sleep(1000);
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

    // 商品第一个操作按钮
    public void operateButton1() {
        this.driver.findElement(By
                .xpath(getElement.OPERATE_BUTTON_XPATH1))
                .click();
    }

    // 商品编辑按钮
    public void editButton() {
        this.driver.findElement(By
                .linkText(getElement.EDIT_BUTTON_LINKTEXT))
                .click();
    }

    // 编辑商品返回按钮
    public void goBackButton() {
        this.driver.findElement(By
                .id(getElement.GOBACK_BUTTON_ID))
                .click();
    }

    // 打开编辑按钮
    public void openEdit() throws InterruptedException {
        productPageIframeIn();
        operateButton1();
        Thread.sleep(1000);
        editButton();
        productPageIframeOut();
    }
}
