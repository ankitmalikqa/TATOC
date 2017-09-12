package com.qait.Tatoc;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qait.testNG.GenerateReportInHTMLFile;

import io.restassured.response.Response;
public class ADVANCEDTATOC {
	static WebDriver driver;
	@BeforeTest
	public void init()
	{

		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		driver = new ChromeDriver(); 	
	}
	
	/**
	 * it would hover on "menu2" and click "go Next"
	 * @throws InterruptedException
	 */
	@Test(priority=1)
    public static void hover_on_menu2_and_click_goNext() throws InterruptedException
   {
	driver.get("http://10.0.1.86/tatoc/advanced/hover/menu");
	Actions action = new Actions(driver);
	WebElement we = driver.findElement(By.cssSelector(".menutitle"));
	action.moveToElement(we).build().perform();
	(new WebDriverWait(driver, 10))
			  .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[onclick='gonext();']")));
	driver.findElement(By.cssSelector("span[onclick='gonext();']")).click();
	}
/**
 * it would use mysql and proceed
 * @throws InterruptedException
 * @throws InstantiationException
 * @throws IllegalAccessException
 * @throws ClassNotFoundException
 * @throws SQLException
 */
    @Test(priority=2)
	public static void use_mysql_and_proceed() throws InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		String url1 ="jdbc:mysql://10.0.1.86/tatoc"; 
		Connection con = DriverManager.getConnection(url1, "tatocuser", "tatoc01");
		(new WebDriverWait(driver, 10))
				  .until(ExpectedConditions.presenceOfElementLocated(By.id("symboldisplay")));
		String value1=driver.findElement(By.id("symboldisplay")).getText();
		PreparedStatement stmt=con.prepareStatement("select id from identity where symbol = ?");  
		stmt.setString(1,value1);
		ResultSet result=stmt.executeQuery();  
	result.next();
		int id=(result.getInt(1));
		PreparedStatement stmt1=con.prepareStatement("select name,passkey from credentials where id = ?"); 
		stmt1.setInt(1,id);
		ResultSet result1=stmt1.executeQuery();
		result1.next();
		driver.findElement(By.id("name")).sendKeys(result1.getString(1));
		driver.findElement(By.id("passkey")).sendKeys(result1.getString(2));
		driver.findElement(By.id("submit")).click();
		(new WebDriverWait(driver, 10))
				  .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("object[type='application/x-shockwave-flash']")));
	}
	@Test(priority=3)
	public static void use_rest_api_to_generate_token() throws JSONException
	{
		driver.get("http://10.0.1.86/tatoc/advanced/rest");
		String value=driver.findElement(By.id("session_id")).getText();
		String value1=value.substring(12);
		int value2=Integer.parseInt(value1);
		System.out.println(value2);
		Response  r1=when().get("http://10.0.1.86/tatoc/advanced/rest/service/token/{value2}",value2);
		String id1=r1.path("token");
		System.out.println(id1);
		String json= "id = "+value2+",signature = "+id1+",allow_access = 1 ";
        System.out.println(json);
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", value1);
        map.put("signature", id1);
        map.put("allow_access", "1");
        
        given().contentType("application/x-www-form-urlencoded").formParams(map).when().post("http://10.0.1.86/tatoc/advanced/rest/service/register").then().statusCode(200);
		driver.findElement(By.linkText("Proceed")).click();
		(new WebDriverWait(driver, 10))
		  .until(ExpectedConditions.presenceOfElementLocated(By.linkText("Download File")));
	}
	@Test(priority=4)
	public static void download_file_and_enter_signature_and_proceed() throws InterruptedException, FileNotFoundException
	{
		driver.findElement(By.cssSelector("a[href='/tatoc/advanced/file/handle/download']")).click();
	    Thread.sleep(2000);
	    File file = new File("C:\\Users\\ankitmalik\\Downloads\\file_handle_test.dat");
	    Scanner scnr = new Scanner(file);
	   String s="";
	    while(scnr.hasNextLine()){
	       String line = scnr.nextLine();
	       s=line;
	    }
	    String s1=s.substring(12);
	    System.out.println(s1);
	    driver.findElement(By.id("signature")).sendKeys(s1);
	    driver.findElement(By.className("submit")).click();
	    scnr.close();
	    
	}
	@Test(priority=5)
	public void go()
	{
		GenerateReportInHTMLFile.report();
	}
}