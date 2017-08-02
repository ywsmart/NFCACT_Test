package com.ontime.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Function：绑定与解绑类
 * Created by yawa1hz1 on 2017/3/29 22:12.
 */
public class BindingPage {
    private WebDriver driver;
    // 登陆页面的url
    private String url;

    // 声明对象时，自动加载页面
    public BindingPage(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;
        // 加载页面
        this.driver.get(this.url);
    }

    public static class getElement {
        // 通过XPATH 获取页面元素
        public static final String IFRAME_ID = "iframe";// iframe表单
        public static final String OPERATE_BUTTON_XPATH1 = "//button[@type='button']";// 第一个操作按钮
        public static final String BINDING_BUTTON_LINKTEXT = "绑定";// 操作后的绑定按钮
        public static final String EDIT_BUTTON_LINKTEXT = "编辑";// 操作后的绑定按钮
        public static final String BINGING_SUCCESS_TEXT_XPATH = "//table[@id='dataGrid']/tbody/tr/td[11]";// 绑定成功，绑定状态为“是”
        public static final String UNBIND_BUTTON_XPATH = "//input[@value='解绑']";// 编辑页面第一个解绑按钮
    }

    // 点击操作→绑定按钮
    public void bindingID() {
        bindingPageIframeIn();
        operateButton();
        bindingButton();
        bindingPageIframeOut();
    }

    // 绑定商品
    public void unbindID() {
        bindingPageIframeIn();
        operateButton();
        editButton();
        unbindButton();
        bindingPageIframeOut();
    }

    // 进入表单的
    public void bindingPageIframeIn() {
        WebElement xf = this.driver.findElement(By
                .id(getElement.IFRAME_ID));
        this.driver.switchTo().frame(xf);
    }

    // 退出表单
    public void bindingPageIframeOut() {
        this.driver.switchTo().defaultContent();
    }

    // 商品第一个操作按钮
    public void operateButton() {
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

    // 商品第一个绑定按钮
    public void bindingButton() {
        this.driver.findElement(By
                .linkText(getElement.BINDING_BUTTON_LINKTEXT))
                .click();
    }

    // 商品第一个解绑按钮
    public void unbindButton() {
        this.driver.findElement(By
                .xpath(getElement.UNBIND_BUTTON_XPATH))
                .click();
    }

    // 判断绑定成功后，商品绑定状态为“是”
    public boolean bindingSuccessResult() {
        bindingPageIframeIn();
        WebElement el = this.driver.findElement(By
                .xpath(getElement.BINGING_SUCCESS_TEXT_XPATH));
        boolean bl = el.getText().equals("是");
        bindingPageIframeOut();
        return bl;
    }

    // 判断绑定成功提示语
    public boolean bindingSuccessMessageResult() {
        try {
            bindingPageIframeIn();
            boolean bl = this.driver.findElement(By
                    .xpath("//div[contains(text(),'绑定成功')]")).isDisplayed();
            bindingPageIframeOut();
            return bl;
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
    }
    
    // 判断解绑成功提示语结果
    public boolean unbindSuccessMessageResult() {
        try {
            bindingPageIframeIn();
            boolean bl = this.driver.findElement(By
                    .xpath("//div[contains(text(),'解绑成功')]")).isDisplayed();
            bindingPageIframeOut();
            return bl;
        } catch (Exception e) {
            return false;
        }
    }

}
