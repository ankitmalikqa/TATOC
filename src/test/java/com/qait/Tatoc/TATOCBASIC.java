package com.qait.Tatoc;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
public class TATOCBASIC {
	static WebDriver driver;  
	/**
	 * this function would click on the link named Basic Course
	 * @throws InterruptedException 
	 */
	public static void click_on_basic_course() throws InterruptedException
	{
		driver.get("http://10.0.1.86/tatoc/");
		 (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Basic Course")));
		driver.findElement(By.linkText("Basic Course")).click();
		 (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".redbox")));
	}
	/**
	 * if the redbox is clicked then it would give error
	 * @throws InterruptedException
	 */
	public static void click_on_redbox_would_give_an_error() throws InterruptedException
	{
		driver.findElement(By.cssSelector(".redbox")).click();
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Basic Course")));
	}
	/**
	 * if it click on greenbox then it would proceed further
	 * @throws InterruptedException
	 */
	public static void click_on_greenbox_and_proceed() throws InterruptedException
	{
		driver.findElement(By.linkText("Basic Course")).click();
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".greenbox")));
		driver.findElement(By.cssSelector(".greenbox")).click();
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("main")));
	}
	/**
	 * it would proceed further if the colors of box1 and box2 matches
	 * @throws InterruptedException
	 */
	public static void repaint_box2_and_click_on_proceed_if_both_box_colors_matches() throws InterruptedException
	{
		driver.switchTo().frame("main");
		String elementval = driver.findElement(By.id("answer")).getAttribute("class");
		driver.switchTo().frame("child");
		String elementval1 = driver.findElement(By.id("answer")).getAttribute("class");
		while(!elementval.equals(elementval1))
		{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("main");
			driver.findElement(By.cssSelector("a[onclick='reloadChildFrame();']")).click();
			driver.switchTo().frame("child");
			elementval1 = driver.findElement(By.id("answer")).getAttribute("class");
		}
		driver.switchTo().defaultContent();
		driver.switchTo().frame("main");
		driver.findElement(By.cssSelector("a[onclick='gonext();']")).click();
		driver.switchTo().defaultContent();	
	}
	/**
	 * it would drag the dragbox and put that into the dropbox 
	 * @throws InterruptedException
	 */
	public static void drag_box_and_click_proceed_link() throws InterruptedException
	{
		WebElement element = driver.findElement(By.id("dragbox"));
		WebElement target = driver.findElement(By.id("dropbox"));
		(new Actions(driver)).dragAndDrop(element, target).perform();
		driver.findElement(By.linkText("Proceed")).click();
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Launch Popup Window")));
	}
	/**
	 * it would launch pop up window write name and click proceed
	 * @throws InterruptedException
	 */
	public static void launch_popup_window_write_name_and_click_proceed() throws InterruptedException
	{
		 driver.findElement(By.linkText("Launch Popup Window")).click();
		 ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		 driver.switchTo().window(tabs2.get(1));  
		 driver.findElement(By.id("name")).sendKeys("ankitmalik");
		 driver.findElement(By.id("submit")).click();
		 driver.switchTo().window(tabs2.get(0));
		 driver.findElement(By.linkText("Proceed")).click();
		 (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Generate Token")));
	}
	public static void set_cookie_and_click_Proceed_link() throws InterruptedException
	{
		 driver.findElement(By.linkText("Generate Token")).click();
		 (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("token")));
		 String value=driver.findElement(By.id("token")).getText();
		 String value1=value.substring(7);
		 Cookie cookie = new Cookie("Token", value1);
		 driver.manage().addCookie(cookie);
		 (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Proceed")));
		 driver.findElement(By.linkText("Proceed")).click();
	}
	/**	  
	 * @param args
	 * @throws InterruptedException
	 */
	@Test
	public static void xyz() throws InterruptedException  
{
	System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
	driver=new ChromeDriver();
	click_on_basic_course();
	click_on_redbox_would_give_an_error();
	click_on_greenbox_and_proceed();
	repaint_box2_and_click_on_proceed_if_both_box_colors_matches();
	drag_box_and_click_proceed_link();
	launch_popup_window_write_name_and_click_proceed();
	set_cookie_and_click_Proceed_link();
}
}