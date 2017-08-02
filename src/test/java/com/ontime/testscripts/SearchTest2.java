package com.ontime.testscripts;

import com.ontime.pageobjects.LoginPage;
import com.ontime.pageobjects.SearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Function：商品检索测试类，TestNG注释数据驱动
 * Created by yawa1hz1 on 2017/3/21 17:19.
 */
public class SearchTest2 {
    private LoginPage loginpage;
    private SearchPage searchpage;
    private WebDriver driver;
    private String url = "pc-20160616lxpl:8092/nfcact70/";

    @BeforeMethod
    public void setUp() throws Exception {
        this.driver = new ChromeDriver();
        this.loginpage = new LoginPage(this.driver, url);// 声明对象时，自动加载页面
        this.loginpage.isLoaded();// 检测页面是否加载
        this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();// 最大化浏览器窗口
        Thread.sleep(2000);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        this.driver.quit();
    }

    //TestNG注释数据驱动
    // 定义对象数组
    @DataProvider(name = "searchText")
    public Object[][] searchText() {// 待检索的商品名、品牌名，待验证的结果
        return new Object[][]{{"1.只检索商品名关键字", "女式太阳镜", "", "Q02249048"},// 只检索商品名关键字
                {"2.只检索商品名全称", "Chateaud AX THUERRY series Wine red wine", "", "桃瑞酒庄"},// 只检索商品名全称
                {"3.只检索品牌名关键字", "", "DIOR", "http://www.dior.cn"},// 只检索品牌名关键字
                {"4.只检索品牌名全称", "", "LANCOME (兰蔻)", "Advanced Genifique Yeux Youth Activating Eye Conce"},// 只检索品牌名全称
                {"5.同时检索同个商品", "1", "VH", "VANESSA HOGAN"},// 同时检索同个商品
                {"6.只检索不存在商品名无果", "不存在的商品名", "", "No matching records found"},// 只检索不存在商品名无果
                {"7.只检索不存在品牌名无果", "", "不存在的品牌名", "No matching records found"},// 只检索不存在品牌名无果
                {"8.检索商品名和品牌名无果,存在的商品名和另一个存在的品牌名", "女式太阳镜", "DIOR", "No matching records found"},// 检索商品名和品牌名无果,存在的商品名和另一个存在的品牌名
                {"9.检索商品名和品牌名无果,存在的商品名和不存在的品牌名", "女式太阳镜", "不存在的品牌名", "No matching records found"},// 检索商品名和品牌名无果,存在的商品名和不存在的品牌名
                {"10.检索商品名和品牌名无果,不存在的商品名和存在的品牌名", "不存在的商品名", "DIOR", "No matching records found"},// 检索商品名和品牌名无果,不存在的商品名和存在的品牌名
                {"11.检索商品名和品牌名无果,不存在的商品名和不存在的品牌名", "不存在的商品名", "不存在的品牌名", "No matching records found"}// 检索商品名和品牌名无果,不存在的商品名和不存在的品牌名
        };
    }

    @Test(dataProvider = "searchText")
    // 检索商品名和品牌名
    public void testCase1(String nameSearch, String brandSearch, String expectText) throws Exception {
        this.searchpage = new SearchPage(this.driver, url);
        // 搜索
        this.searchpage.search(nameSearch, brandSearch);
        // 等待2秒显示结果
        Thread.sleep(2000);
        // 判断搜索结果页面是否包含关键字
        Assert.assertTrue(this.searchpage.searchResult(expectText));
    }
}