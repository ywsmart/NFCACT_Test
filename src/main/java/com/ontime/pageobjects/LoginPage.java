package com.ontime.pageobjects;

import org.openqa.selenium.*;

/**
 * Function：登录类（暂无用户登录功能），加载网页即登录
 * Created by yawa1hz1 on 2017/3/14.
 */
public class LoginPage {
    public static class getElement {
        public static final String TITLE = "NFCACT";// 标题
        // 通过XPATH 获取页面元素
        public static final String IFRAME_ID = "iframe";// iframe表单

        // 登陆成功的用户名
        public static final String LOGIN_SUCCESS_TEXT_XPATH =
                "//*[@id='container']/header/ul/li[2]/a/span[1]";// 用户名
    }
    private WebDriver driver;
    // 登陆页面的url
    private String url;
    // 声明对象时，自动加载页面
    public LoginPage(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;
        // 加载页面
        this.driver.get(this.url);
    }
    // 获取页面标题
    public String getTitle() {
        return this.driver.getTitle();
    }
    // 检测页面是否加载，判断title 是否相等，返回T/F
    public boolean isLoaded() {
        System.out.println(this.getTitle());
        return LoginPage.getElement.TITLE.equals(this.getTitle());
    }
    // 表单的进入
    public void loginIframeIn() {
        WebElement xf = this.driver.findElement(By
                .id(LoginPage.getElement.IFRAME_ID));
        this.driver.switchTo().frame(xf);
    }
    // 表单的退出
    public void loginIframeOut() {
        this.driver.switchTo().defaultContent();
    }
    // 登陆方法，加载网页即登入，然后根据title 判断跳转是否成功
    public void login() {
        // 暂无账户登入功能
    }
    // 返回登录成功之后的用户名
    public String user() {
        String text = this.driver.findElement(By
                .xpath(LoginPage.getElement.LOGIN_SUCCESS_TEXT_XPATH))
                .getText();
        return text;
    }
}
