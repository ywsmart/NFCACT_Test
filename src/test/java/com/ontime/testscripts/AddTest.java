package com.ontime.testscripts;

import com.ontime.pageobjects.AddPage;
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
 * Function：新建商品测试类
 * Created by yawa1hz1 on 2017/3/24 16:53.
 */
public class AddTest {
    private LoginPage loginpage;
    private AddPage addpage;
    private EditPage editpage;
    private WebDriver driver;
    private String url = "pc-20160616lxpl:8092/nfcact70/";

    @BeforeMethod
    public void setUp() throws Exception {
        this.driver = new ChromeDriver();
        this.loginpage = new LoginPage(this.driver, url);// 声明对象时，自动加载页面
        this.loginpage.isLoaded();// 检测页面是否加载
        this.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();// 最大化浏览器窗口
//        this.driver.manage().window().setSize(new Dimension(1600, 1400));
        Thread.sleep(1000);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        this.driver.quit();
    }

    // 使用注解DataProvider,将数据集合命名为addText
    @DataProvider(name = "addText")
    public static Object[][] words() throws IOException {
        // 调用类中的静态方法getTestData,获取Excel文件的测试数据
//        return ProductPage.getTestData("C:\\Users\\yawa1hz1\\IdeaProjects\\NFCACT_Test\\info"
//                , "addProductText.xlsx", "Sheet1");
        return ProductPage.getTestData("C:\\Users\\yawa1hz1\\IdeaProjects\\NFCACT_Test\\info"
                , "addProductText可展示的11个商品.xlsx", "Sheet1");
//        return ProductPage.getTestData("C:\\Users\\yawa1hz1\\IdeaProjects\\NFCACT_Test\\info"
//                , "test.xlsx", "Sheet1");
    }

    @Test(dataProvider = "addText")
    // 新建商品，输入文本和图片
    public void testCase4_1(String caseNotes, String no, String name, String spec, String unit
            , String brand, String longName, String origin, String manufacturer, String price
            , String productDate, String expiryData, String proUrl, String remark, String imgUrl1
            , String imgUrl2, String messageText, String expectTestID, String expectText) throws Exception {
        System.out.println("TestCase：" + caseNotes);
        this.addpage = new AddPage(this.driver, url);
        switch (expectTestID) {
            // 测试必填项
            case "1":
                // 新建商品
                this.addpage.add1(no, name, spec, unit, brand, longName, origin, manufacturer
                        , price, productDate, expiryData, proUrl, remark, imgUrl1, imgUrl2);
                // 判断无法保存提示语
                Assert.assertTrue(this.addpage.MessageResult(messageText));
                // 为了肉眼真实看到提示语而添加延时
                Thread.sleep(1000);
                System.out.println("==>Pass，skip expectText");
                break;
            // 测试主图异常提示
            case "4":
                // 新建商品
                this.addpage.add3(no, name, spec, unit, brand, longName, origin, manufacturer
                        , price, productDate, expiryData, proUrl, remark, imgUrl1);
                // 判断提示语
                Assert.assertTrue(this.addpage.MessageResult(messageText));
                // 为了肉眼真实看到提示语而添加延时
                Thread.sleep(1000);
                System.out.println("==>Pass");
                break;
            // 测试新建保存和文本临界值
            case "2":
                // 新建商品
                this.addpage.add1(no, name, spec, unit, brand, longName, origin, manufacturer
                        , price, productDate, expiryData, proUrl, remark, imgUrl1, imgUrl2);
                // 判断保存成功提示语
                Assert.assertTrue(this.addpage.MessageResult(messageText));
                // 等待2秒显示结果，避免获取页面源码错误
                Thread.sleep(2000);
                // 判断新建商品结果页面是否包含关键字
                Assert.assertTrue(ProductPage.addEditResult(this.addpage, expectText));
                System.out.println("==>Pass");
                break;
            // 测试价格异常
            case "5":
                // 新建商品
                this.addpage.add1(no, name, spec, unit, brand, longName, origin, manufacturer
                        , price, productDate, expiryData, proUrl, remark, imgUrl1, imgUrl2);
                // 判断保存成功提示语
                Assert.assertTrue(this.addpage.MessageResult(messageText));
                // 等待2秒显示结果，避免获取页面源码错误
                Thread.sleep(2000);
                // 判断新建商品结果页面的价格结果
                Assert.assertTrue(this.addpage.addPriceResult(expectText));
                System.out.println("==>Pass");
                break;
            // 测试附图临界值1,图片地址在添加方法里
            case "6":
                // 新建商品
                this.addpage.add4(no, name, spec, unit, brand, longName, origin, manufacturer
                        , price, productDate, expiryData, proUrl, remark, imgUrl1, imgUrl2);
                // 判断提示语
                Assert.assertTrue(this.addpage.MessageResult(messageText));
                System.out.println("==>Pass");
                break;
            // 测试附图临界值2,图片地址在添加方法里
            case "3":
                // 新建商品
                this.addpage.add2(no, name, spec, unit, brand, longName, origin, manufacturer
                        , price, productDate, expiryData, proUrl, remark, imgUrl1, imgUrl2);
                // 判断保存成功提示语
                Assert.assertTrue(this.addpage.MessageResult(messageText));
                // 等待2秒显示结果，避免获取页面源码错误
                Thread.sleep(2000);
                // 判断新建商品结果页面是否包含关键字
                Assert.assertTrue(ProductPage.addEditResult(this.addpage, expectText));
                System.out.println("==>Pass");
                break;
            default:
                System.out.println(imgUrl2 + "此文本不符合预先约定，约定文本：1、2、3、4、5、6、7");
        }
    }

    @Test
    // 输入框缺省文本验证
    public void testCase4_2() throws Exception {
        System.out.println("testCase：输入框缺省文本验证");
        this.addpage = new AddPage(this.driver, url);
        this.addpage.openAdd();
        Assert.assertTrue(this.addpage.noDefaultText());
        Assert.assertTrue(this.addpage.nameDefaultText());
        Assert.assertTrue(this.addpage.specDefaultText());
        Assert.assertTrue(this.addpage.unitDefaultText());
        Assert.assertTrue(this.addpage.brandDefaultText());
        Assert.assertTrue(this.addpage.longNameDefaultText());
        Assert.assertTrue(this.addpage.originDefaultText());
        Assert.assertTrue(this.addpage.manufacturerDefaultText());
        Assert.assertTrue(this.addpage.priceDefaultText());
        Assert.assertTrue(this.addpage.productDateDefaultText());
        Assert.assertTrue(this.addpage.expiryDateDefaultText());
        Assert.assertTrue(this.addpage.proUrlDefaultText());
        Assert.assertTrue(this.addpage.remarkDefaultText());
        System.out.println("==>Pass");
    }
}
