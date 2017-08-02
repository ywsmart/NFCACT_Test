package com.ontime.pageobjects;

import org.openqa.selenium.*;

import java.util.Objects;

/**
 * Function：商品检索类
 * Created by yawa1hz1 on 2017/3/15 14:17.
 */
public class SearchPage {
    public static class getElement {
        // 通过XPATH 获取页面元素
        public static final String NAME_SEARCH_INPUT_ID = "nameS";// 商品名称搜索输入框
        public static final String BRAND_SEARCH_INPUT_ID = "brandS";// 商品品牌搜索输入框
        public static final String SEARCH_BUTTON_ID = "searchData";// 搜索按钮
        public static final String IFRAME_ID = "iframe";// 表单
    }
    private WebDriver driver;
    private String url;

    public SearchPage(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;
        // 加载页面
        this.driver.get(this.url);
    }
    public void search(String nameSearch , String brandSearch) {
//        System.out.println("进入search方法");
        searchIframeIn();
        search_nameSearch_input(nameSearch);
        search_brandSearch_input(brandSearch);
        search_button();
        searchIframeOut();
    }
    // 获取页面标题
    public String getTitle() {
        return this.driver.getTitle();
    }
    // 搜索按钮
    public void search_button() {
//        System.out.println("进入search_button方法");
        this.driver
                .findElement(By.id(getElement.SEARCH_BUTTON_ID))
                .click();
    }
    // 商品名称输入框
    public void search_nameSearch_input(String nameSearch) {
//        System.out.println("进入search_nameSearch_input方法");
        WebElement el = this.driver.findElement(By
                .id(SearchPage.getElement.NAME_SEARCH_INPUT_ID));
        el.clear();
        el.sendKeys(nameSearch);
    }
    // 商品品牌输入框
    public void search_brandSearch_input(String brandSearch) {
//        System.out.println("进入search_brandSearch_input方法");
        WebElement el = this.driver.findElement(By
                .id(SearchPage.getElement.BRAND_SEARCH_INPUT_ID));
        el.clear();
        el.sendKeys(brandSearch);
    }
    // 表单的进入
    public void searchIframeIn() {
//        System.out.println("进入search_iframe_in方法");
        WebElement xf = this.driver.findElement(By
                .id(SearchPage.getElement.IFRAME_ID));
        this.driver.switchTo().frame(xf);
    }
    // 表单的退出
    public void searchIframeOut() {
//        System.out.println("进入search_iframe_out方法");
        this.driver.switchTo().defaultContent();
    }
    // 判断检索结果
    public boolean searchResult(String expectText) {
        if (Objects.equals("Skip", expectText)) {
            System.out.println(" ==>Skip");
            return true;
        } else {
            searchIframeIn();
            boolean bl = this.driver.getPageSource().contains(expectText);
            searchIframeOut();
            return bl;
        }
    }

    // 检索商品名称缺省文本
    public boolean nameSearchDefaultText() {
        searchIframeIn();
        WebElement el = this.driver.findElement(By
                .id(getElement.NAME_SEARCH_INPUT_ID));
        String text = el.getAttribute("placeholder");
        searchIframeOut();
        return text.equals("商品名称");
    }

    // 检索商品品牌缺省文本
    public boolean brandSearchDefaultText() {
        searchIframeIn();
        WebElement el = this.driver.findElement(By
                .id(getElement.BRAND_SEARCH_INPUT_ID));
        String text = el.getAttribute("placeholder");
        searchIframeOut();
        return text.equals("品牌");
    }

}