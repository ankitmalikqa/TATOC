package com.qait.Tatoc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ADVANCEDTATOC {
	static WebDriver driver;
	/**
	 * it would hover on "menu2" and click "go Next"
	 * @throws InterruptedException
	 */
public static void hover_on_menu2_and_click_goNext() throws InterruptedException
{
	driver.get("http://10.0.1.86/tatoc/advanced/hover/menu");
	Actions action = new Actions(driver);
	WebElement we = driver.findElement(By.cssSelector(".menutitle"));
	action.moveToElement(we).build().perform();
	//Thread.sleep(2000);
	WebElement myDynamicElement = (new WebDriverWait(driver, 10))
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
	public static void use_mysql_and_proceed() throws InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		String url1 ="jdbc:mysql://10.0.1.86/tatoc"; 
		Connection con = DriverManager.getConnection(url1, "tatocuser", "tatoc01");
		WebElement myDynamicElement = (new WebDriverWait(driver, 10))
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
		WebElement myDynamicElement1 = (new WebDriverWait(driver, 10))
				  .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("object[type='application/x-shockwave-flash']")));
	}
	public static void main(String args[]) throws InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		driver = new ChromeDriver(); 
		hover_on_menu2_and_click_goNext();
		use_mysql_and_proceed();
		driver.findElement(By.cssSelector("object[type='application/x-shockwave-flash']")).click();
	}
}