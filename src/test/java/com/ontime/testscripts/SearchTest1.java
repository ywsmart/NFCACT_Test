package com.ontime.testscripts;

import com.ontime.pageobjects.LoginPage;
import com.ontime.pageobjects.SearchPage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Function：商品检索测试类，测试数据库数据驱动，太繁琐，测试数据不好预处理
 * Created by yawa1hz1 on 2017/3/15 15:23.
 */
public class SearchTest1 {
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
        Thread.sleep(1000);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        this.driver.quit();
    }

    // 使用注解DataProvider，将数据集合命名为“product”
    @DataProvider(name = "product")
    public static Object[][] words() throws IOException {
        // 调用类中的静态方法getTestData，获取MySQL数据库中的测试数据
        return getTestData("product");
    }

    @Test(dataProvider = "product")
    // 检索商品名和品牌名
    public void testCase1(String ID, String nameSearch, String brandSearch, String expectText) throws Exception {
        System.out.println("testCase2." + ID + "开始......");
        this.searchpage = new SearchPage(this.driver, url);
        // 调用搜索方法
        this.searchpage.search(nameSearch, brandSearch);
        // 等待2秒显示结果
        Thread.sleep(1000);
        // 判断搜索结果页面是否包含关键字
        Assert.assertTrue(this.searchpage.searchResult(expectText));
    }

    public static Object[][] getTestData(String tablename) throws IOException {
        // 声明MySQL数据库的驱动
        String driver = "com.mysql.cj.jdbc.Driver";
        // 声明数据库的IP地址和数据库名称
        // 测试服务器数据库
        String url = "jdbc:mysql://PC-20160616LXPL:3306/dqa_nfcact?serverTimezone=UTC&useUnicode=true" +
                "&characterEncoding=UTF-8&allowMultiQueries=true&autoReconnect=true&useSSL=false";
        // 本地测试数据库
//         String url = "jdbc:mysql://127.0.0.1:3306/testdate?serverTimezone=UTC&characterEncoding=UTF-8&useSSL=true";
        // 声明数据库的用户名
        String user = "dqa_nfcact";
        // 声明数据库的密码
        String password = "nfcact";
        // 声明储存测试数据的list对象
        List<Object[]> records = new ArrayList<Object[]>();
        try {
            // 设定驱动，指定连接类型
            Class.forName(driver);
            // 声明连接数据库的链接对象，使用数据库服务器地址、用户名和密码作为参数
            Connection conn = DriverManager.getConnection(url, user, password);
            // 如果数据库连接可以用，打印数据库连接成功的信息
            if (!conn.isClosed())
                System.out.println("连接数据库成功！");
            // 创建 statement 对象
            Statement statement = conn.createStatement();
            // 使用函数参数拼接要执行的SQL语句，此语句用来获取数据表的所有数据行
            String sql = "select * from " + tablename;
            // 声明 ResultSet 对象，存取执行SQL语句后返回的数据结果集
            ResultSet rs = statement.executeQuery(sql);
            // 声明一个 ResultSetMetaData 对象
            ResultSetMetaData rsMetaData = rs.getMetaData();
            // 调用 ResultSetMetaData 对象的getColumnCount方法获取数据行的列数
            int cols = rsMetaData.getColumnCount();
            // 使用 next 方法遍历数据结果集中的所有数据行
            while (rs.next()) {
                // 选取所有行的1ID、3名称、6品牌列数据
                // 声明一个字符型数据，数组大小5进行声明
                String fileds[] = new String[5];
                //遍历所有数据行的ID、名称、品牌列数据，并存储在字符数组中
                fileds[0] = rs.getString("id");
                fileds[1] = rs.getString("name");
                fileds[2] = rs.getString("brand");
                fileds[3] = rs.getString("url");
                fileds[4] = rs.getString("status");

                // 将每一行的数据存储到records中
                records.add(fileds);
                //输出数据行中的前四列内容，用于验证数据库内容是否正确取出
                System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  "
                        +rs.getString(3) + "  " +rs.getString(4));
            }
            // 关闭数据结果集对象
            rs.close();
            // 关闭声明
            statement.close();
            // 关闭数据库链接
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("未能找到MySQL的驱动类！");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 将存储测试数据的list转换为一个Object的二维数组
        Object[][] results = new Object[records.size()][];
        // 设置二维数组每行的值，每行是一个Object对象
        for (int i = 0; i < records.size(); i++) {
            results[i] = records.get(i);
        }
        // 定义函数返回值，即Object[][] resultsData
        Object[][] resultsData = new Object[1][4];
        // 每行加上编号
        for(int i = 0; i < resultsData.length ; i++)
        {
            int j = 0;
            j++;
            resultsData[i][0] = String.valueOf(j);
        }
        for (Object[] result : results) {
            if (result[4].equals("1")) {
                resultsData[0][1] = result[1];// 存在的商品名全称
                resultsData[0][2] = "";// 不输入的商品品牌
                //resultsData[1][2] = results[i][2];// 存在的商品品牌全称
                resultsData[0][3] = result[3];// 存在商品的URL，用于页面验证
                resultsData[0][3] = result[3];// 存在商品的URL，用于页面验证
                break;
            }
        }
        return resultsData;
    }

    @Test
    public void testCase2() throws Exception {

    }
}
