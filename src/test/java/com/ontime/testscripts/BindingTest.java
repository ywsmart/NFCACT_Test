package com.ontime.testscripts;

import com.ontime.pageobjects.BindingPage;
import com.ontime.pageobjects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Function：绑定与解绑测试类
 * Created by yawa1hz1 on 2017/3/29 22:13.
 */
public class BindingTest {
    private LoginPage loginpage;
    private BindingPage bindingpage;
    private WebDriver driver;
    private String url = "pc-20160616lxpl:8092/nfcact70/";

    @BeforeMethod
    public void setUp() throws Exception {
        this.driver = new ChromeDriver();
        this.loginpage = new LoginPage(this.driver, url);// 声明对象时，自动加载页面
        this.loginpage.isLoaded();// 检测页面是否加载
        this.driver.manage().timeouts().implicitlyWait(11, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();// 最大化浏览器窗口
        Thread.sleep(1000);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        this.driver.quit();
    }

    @Test
    // 成功绑定商品
    public void testCase7_1_1() throws Exception {
        System.out.println("7.1.1绑定商品");
        this.bindingpage = new BindingPage(this.driver, url);
        // 绑定标签
        this.bindingpage.bindingID();
        // 断言提示语
        Assert.assertTrue(this.bindingpage.bindingSuccessMessageResult());
        // 断言绑定状态
        Assert.assertTrue(this.bindingpage.bindingSuccessResult());
        System.out.println("==>Pass");
    }
    // 解绑商品
    public void testCase7_2_1() throws Exception {
        System.out.println("7.2.1解绑商品");
        this.bindingpage = new BindingPage(this.driver, url);
        // 解绑标签
        this.bindingpage.unbindID();
        // 断言提示语
        Assert.assertTrue(this.bindingpage.unbindSuccessMessageResult());
        System.out.println("==>Pass");
    }
}
