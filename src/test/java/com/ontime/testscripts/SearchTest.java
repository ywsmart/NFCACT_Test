package com.ontime.testscripts;

import com.ontime.pageobjects.LoginPage;
import com.ontime.pageobjects.ProductPage;
import com.ontime.pageobjects.SearchPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Function：检索测试类，Excel数据驱动
 * Created by yawa1hz1 on 2017/3/31 10:35.
 */
public class SearchTest {
    private LoginPage loginpage;
    private SearchPage searchpage;
    private WebDriver driver;
    private String url = "pc-20160616lxpl:8092/nfcact70/";

    @BeforeMethod
    public void setUp() throws Exception {
        this.driver = new ChromeDriver();
        this.loginpage = new LoginPage(this.driver, url);// 声明对象时，自动加载页面
        this.loginpage.isLoaded();// 检测页面是否加载
        this.driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();// 最大化浏览器窗口
        Thread.sleep(1000);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        this.driver.quit();
    }

    // 使用注解DataProvider,将数据集合命名为searchText
    @DataProvider(name = "searchText")
    public static Object[][] words() throws IOException {
        // 调用类中的静态方法getTestData,获取Excel文件的测试数据
        return ProductPage.getTestData("C:\\Users\\yawa1hz1\\IdeaProjects\\NFCACT_Test\\info"
                ,"searchProductText.xlsx","Sheet1");
    }

    @Test
    // 输入框缺省文本验证
    public void testCase1() throws Exception {
        System.out.println("testCase：输入框缺省文本验证");
        this.searchpage = new SearchPage(this.driver, url);
        Assert.assertTrue(this.searchpage.nameSearchDefaultText());
        Assert.assertTrue(this.searchpage.brandSearchDefaultText());
        System.out.println("==>Pass");
    }

    @Test(dataProvider = "searchText")
    // 检索商品名和品牌名
    public void testCase2(String caseNotes, String nameSearch, String brandSearch, String expectText) throws Exception {
        System.out.println("TestCase：" + caseNotes);
        this.searchpage = new SearchPage(this.driver, url);
        // 搜索
        this.searchpage.search(nameSearch, brandSearch);
        // 等待2秒显示结果
        Thread.sleep(1000);
        // 判断搜索结果页面是否包含关键字
        Assert.assertTrue(this.searchpage.searchResult(expectText));
        System.out.println("==>Pass");
    }

}
