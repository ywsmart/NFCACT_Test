package com.ontime.testscripts;

import com.ontime.pageobjects.LoginPage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

/**
 * Function：登入模块测试类
 * Created by yawa1hz1 on 2017/3/14 18:00.
 */
public class LoginTest {
    private LoginPage loginpage;
    private WebDriver driver;
    private String url;

    @BeforeMethod
    public void setUp() throws Exception {
        this.driver = new ChromeDriver();
        this.url = "pc-20160616lxpl:8092/nfcact70/";
        this.loginpage = new LoginPage(this.driver, url);
        this.driver.manage().window().maximize();
//        this.driver.manage().window().setSize(new Dimension(1600, 800));// 设置浏览器窗口长1600宽800
        this.loginpage.isLoaded();// 检测页面是否加载
        this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown() {this.driver.quit();}

    @Test()
    public void loginTestCase() throws InterruptedException {
        this.loginpage.login();
        Thread.sleep(1000);
        String username = this.loginpage.user();
        assertEquals(username, "admin");// 断言用户名
        System.out.println("==>Pass");
    }
}
