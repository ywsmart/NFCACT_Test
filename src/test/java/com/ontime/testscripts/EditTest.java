package com.ontime.testscripts;

import com.ontime.pageobjects.EditPage;
import com.ontime.pageobjects.LoginPage;
import com.ontime.pageobjects.ProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Function：编辑商品测试类
 * Created by yawa1hz1 on 2017/3/28 10:48.
 */
public class EditTest {
    private LoginPage loginpage;
    private EditPage editpage;
    private WebDriver driver;
    private String url = "pc-20160616lxpl:8092/nfcact70/";

    @BeforeMethod
    public void setUp() throws Exception {
        this.driver = new ChromeDriver();
        this.loginpage = new LoginPage(this.driver, url);// 声明对象时，自动加载页面
        this.loginpage.isLoaded();// 检测页面是否加载
        this.driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();// 最大化浏览器窗口
        Thread.sleep(1000);
    }

    // 使用注解DataProvider,将数据集合命名为editText
    @DataProvider(name = "editText")
    public static Object[][] words() throws IOException {
//        // 调用GetExcelTestData类中的静态方法getTestData,获取Excel文件的测试数据
//        return ProductPage.getTestData("C:\\Users\\yawa1hz1\\IdeaProjects\\NFCACT_Test\\info"
//                , "editProductText.xlsx", "Sheet1");
            return ProductPage.getTestData("C:\\Users\\yawa1hz1\\IdeaProjects\\NFCACT_Test\\info"
                , "test.xlsx", "Sheet1");
    }

    @Test(dataProvider = "editText")
    // 新建商品，输入文本和图片
    public void testCase5_1(String caseNotes, String no, String name, String spec, String unit
            , String brand, String longName, String origin, String manufacturer, String price
            , String productDate, String expiryData, String proUrl, String remark, String imgUrl1
            , String imgUrl2, String messageText, String expectTestID, String expectText) throws Exception {
        System.out.println(caseNotes);
        this.editpage = new EditPage(this.driver, url);
        switch (expectTestID) {
            // 测试编辑保存和文本临界值
            case "1":
                // 新建商品
                this.editpage.edit1(no, name, spec, unit, brand, longName, origin, manufacturer
                        , price, productDate, expiryData, proUrl, remark, imgUrl1, imgUrl2);
                Assert.assertTrue(this.editpage.MessageResult(messageText));
                // 等待2秒显示结果，避免获取页面源码错误
                Thread.sleep(2000);
                // 判断结果
                Assert.assertTrue(ProductPage.addEditResult(this.editpage, expectText));
                System.out.println("==>Pass");
                break;
            // 测试必填项
            case "2":
                // 新建商品
                this.editpage.edit1(no, name, spec, unit, brand, longName, origin, manufacturer
                        , price, productDate, expiryData, proUrl, remark, imgUrl1, imgUrl2);
                // 为了肉眼真实看到提示语而添加延时
                Thread.sleep(1000);
                Assert.assertTrue(this.editpage.MessageResult(messageText));
                System.out.println("==>Pass，skip expectText");
                break;
            // 测试主图异常提示
            case "3":
                // 新建商品
                this.editpage.edit2(no, name, spec, unit, brand, longName, origin, manufacturer
                        , price, productDate, expiryData, proUrl, remark, imgUrl1);
                // 判断提示语
                Assert.assertTrue(this.editpage.MessageResult(messageText));
                // 等待2秒显示结果，避免获取页面源码错误
                Thread.sleep(1000);
                System.out.println("==>Pass");
                break;
        }
    }

    @Test
    public void testCase5_2() throws Exception {
        System.out.println("testCase：输入框缺省文本验证");
        this.editpage = new EditPage(this.driver, url);
        this.editpage.openEdit();
        Assert.assertTrue(this.editpage.noDefaultText());
        Assert.assertTrue(this.editpage.nameDefaultText());
        Assert.assertTrue(this.editpage.specDefaultText());
        Assert.assertTrue(this.editpage.unitDefaultText());
        Assert.assertTrue(this.editpage.brandDefaultText());
        Assert.assertTrue(this.editpage.longNameDefaultText());
        Assert.assertTrue(this.editpage.originDefaultText());
        Assert.assertTrue(this.editpage.manufacturerDefaultText());
        Assert.assertTrue(this.editpage.priceDefaultText());
        Assert.assertTrue(this.editpage.productDateDefaultText());
        Assert.assertTrue(this.editpage.expiryDateDefaultText());
        Assert.assertTrue(this.editpage.proUrlDefaultText());
        Assert.assertTrue(this.editpage.remarkDefaultText());
        System.out.println("==>Pass");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        this.driver.quit();
    }
}
