package com.ontime.testscripts;

import com.ontime.pageobjects.DeletePage;
import com.ontime.pageobjects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Function：删除商品测试类
 * Created by yawa1hz1 on 2017/3/29 14:16.
 */
public class DeleteTest {
    private LoginPage loginpage;
    private DeletePage deletepage;
    private WebDriver driver;
    private String url = "pc-20160616lxpl:8092/nfcact70/";

    @BeforeMethod
    public void setUp() throws Exception {
        this.driver = new ChromeDriver();
        this.loginpage = new LoginPage(this.driver, url);// 声明对象时，自动加载页面
        this.loginpage.isLoaded();// 检测页面是否加载
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();// 最大化浏览器窗口
//        this.driver.manage().window().setSize(new Dimension(1600, 1400));
        Thread.sleep(1000);
    }

    @Test
    // 不勾选商品复选框点击删除
    public void testCase6_1_1() throws Exception {
        System.out.println("6.1.1不勾选商品复选框点击删除");
        this.deletepage = new DeletePage(this.driver, url);
        // 不勾选删除
        this.deletepage.deleteNoSelect();
        // 断言提示语
        Assert.assertTrue(this.deletepage.messageResult("请选择数据"));
        System.out.println("==> Pass");
    }

    @Test
    // 操作-删除单个未绑定商品
    public void testCase6_1_2() throws Exception {
        System.out.println("6.1.2操作-删除单个未绑定商品");
        this.deletepage = new DeletePage(this.driver, url);
        // 操作→删除
        this.deletepage.operateDeleteOne();
        // 断言提示语
        Assert.assertTrue(this.deletepage.messageResult("删除成功！"));
        System.out.println("==>Pass");
    }

    @Test
    // 复选框删除一个未绑定的商品
    public void testCase6_1_4() throws Exception {
        System.out.println("6.1.4复选框删除一个商品");
        this.deletepage = new DeletePage(this.driver, url);
        // 复选框删除第一个商品
        this.deletepage.deleteOne1();
        // 判断成功提示语
        Assert.assertTrue(this.deletepage.messageResult("删除成功！"));
        // 等待2秒显示结果，避免获取页面源码错误
        Thread.sleep(2000);
        // 判断新建商品结果页面是否包含关键字
        Assert.assertTrue(this.deletepage.saveResult("1"));
        System.out.println("==> Pass");
    }

    @Test
    // 取消全选批量删除商品
    public void testCase6_1_8() throws Exception {
        System.out.println("6.1.8取消全选批量删除商品");
        this.deletepage = new DeletePage(this.driver, url);
        // 取消删除当页全部商品
        this.deletepage.cancelDeletePageAll();
//        // 判断成功提示语
//        Assert.assertTrue(this.deletepage.messageResult("删除成功！"));
//        // 等待2秒显示结果，避免获取页面源码错误
//        Thread.sleep(2000);
//        // 判断新建商品结果页面是否包含关键字
//        Assert.assertTrue(this.deletepage.addResult("1"));
        System.out.println("==>Pass");
    }

    @Test
    // 删除当页全部商品
    public void testCase6_1_9() throws Exception {
        System.out.println("6.1.9全选批量删除商品");
        this.deletepage = new DeletePage(this.driver, url);
        // 删除当页全部商品
        this.deletepage.deletePageAll();
        // 判断成功提示语
        Assert.assertTrue(this.deletepage.messageResult("删除成功！"));
        // 等待2秒显示结果，避免获取页面源码错误
        Thread.sleep(2000);
        // 判断新建商品结果页面是否包含关键字
        Assert.assertTrue(this.deletepage.saveResult("1"));
        System.out.println("==>Pass");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        this.driver.quit();
    }
}
