package com.ontime.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Function：删除商品类
 * Created by yawa1hz1 on 2017/3/29 14:16.
 */
public class DeletePage {
    public static class getElement {
        // 通过XPATH 获取页面元素
        public static final String IFRAME_ID = "iframe";// 表单
        public static final String DELETE_BUTTON_ID = "deleteData";// 删除按钮
        public static final String DELETE_OK_BUTTON_LINKTEXT = "确认";// 删除确认按钮
        public static final String DELETE_OK_BUTTON_XPATH = "//a[contains(text(),'确定')]";// 删除确认按钮
        public static final String DELETE_CANCEL_BUTTON_LINKTEXT = "取消";// 删除取消按钮
        public static final String SELECTALL_BUTTON_NAME = "btSelectAll";// 全选复选框
        public static final String SELECTITEM_BUTTON1_XPATH = "//input[@name='btSelectItem']";// 第一个商品复选框
        public static final String SELECTITEM_BUTTON2_XPATH = "(//input[@name='btSelectItem'])[2]";// 第二个商品复选框
        public static final String OPERATE_BUTTON_XPATH1 = "//button[@type='button']";// 第一个操作按钮
        public static final String OPERATE_BUTTON5_XPATH = "(//button[@type='button'])[5]";// 第五个操作按钮
        public static final String OPERATE_DELETE_BUTTON_LINKTEXT = "删除";// 第一个操作的删除按钮
    }

    private WebDriver driver;
    private String url;

    public DeletePage(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;
        // 加载页面
        this.driver.get(this.url);
    }

    // 删除当页所有商品
    public void deletePageAll() {
        deletePageIframeIn();
        selectAllButton();
        deleteButton();
        deleteOKButton();
        deletePageIframeOut();
    }

    // 取消删除当页所有商品
    public void cancelDeletePageAll() {
        deletePageIframeIn();
        selectAllButton();
        deleteButton();
        deleteCancelButton();
        deletePageIframeOut();
    }

    // 复选框删除一个商品
    public void deleteOne1() {
        deletePageIframeIn();
        selectItemButton1();
        deleteButton();
        deleteOKButton();
        deletePageIframeOut();
    }

    // 操作删除一个未绑定商品
    public void operateDeleteOne() {
        deletePageIframeIn();
        operateButton5();
        operateDeleteButton();
        deleteOKButton();
        deletePageIframeOut();
    }

    // 不勾选商品复选框点击删除
    public void deleteNoSelect() {
        deletePageIframeIn();
        deleteButton();
        deletePageIframeOut();
    }

    // 进入表单
    public void deletePageIframeIn() {
        WebElement xf = this.driver.findElement(By
                .id(getElement.IFRAME_ID));
        this.driver.switchTo().frame(xf);
    }

    // 退出表单
    public void deletePageIframeOut() {
        this.driver.switchTo().defaultContent();
    }

    // 删除按钮
    public void deleteButton() {
        driver.findElement(By
                .id(getElement.DELETE_BUTTON_ID))
                .click();
    }

    // 删除确认按钮
    public void deleteOKButton() {
        driver.findElement(By
                .xpath(getElement.DELETE_OK_BUTTON_XPATH))
                .click();

    }

    // 删除取消按钮
    public void deleteCancelButton() {
        driver.findElement(By
                .linkText(getElement.DELETE_CANCEL_BUTTON_LINKTEXT))
                .click();
    }

    // 全选复选框
    public void selectAllButton() {
        WebElement el = driver.findElement(By.name(getElement.SELECTALL_BUTTON_NAME));
        if (!el.isSelected())
            el.click();
    }

    // 第一个商品复选框
    public void selectItemButton1() {
        driver.findElement(By
                .name(getElement.SELECTALL_BUTTON_NAME))
                .click();
    }

    // 判断提示语结果
    public boolean messageResult(String messageText) {
        try {
            deletePageIframeIn();
            boolean bl = this.driver.findElement(By
                    .xpath("//div[contains(text(),'" + messageText + "')]")).isDisplayed();
            deletePageIframeOut();
            return bl;
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
    }

    // 判断结果
    public boolean saveResult(String expectText) {
        deletePageIframeIn();
        boolean bl = this.driver.getPageSource().contains(expectText);
        deletePageIframeOut();
        return bl;
    }

    // 操作按钮
    public void operateButton() {
        this.driver.findElement(By
                .xpath(getElement.OPERATE_BUTTON_XPATH1))
                .click();
    }

    // 第五个操作按钮
    public void operateButton5() {
        this.driver.findElement(By
                .xpath(getElement.OPERATE_BUTTON5_XPATH))
                .click();
    }

    // 操作删除按钮
    public void operateDeleteButton() {
        this.driver.findElement(By
                .linkText(getElement.OPERATE_DELETE_BUTTON_LINKTEXT))
                .click();
    }
}
