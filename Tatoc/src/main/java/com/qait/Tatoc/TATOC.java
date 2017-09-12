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
public class TATOC {
	public static void main(String[] args) throws InterruptedException  
{
	System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
	WebDriver driver = new ChromeDriver(); 
	driver.get("http://10.0.1.86/tatoc/");
	Thread.sleep(2000);
	driver.findElement(By.linkText("Basic Course")).click();
	Thread.sleep(2000);
	driver.findElement(By.cssSelector(".redbox")).click();
	Thread.sleep(2000);
	driver.findElement(By.linkText("Basic Course")).click();
	Thread.sleep(2000);
	driver.findElement(By.cssSelector(".greenbox")).click();
	Thread.sleep(2000);
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
	WebElement element = driver.findElement(By.id("dragbox"));
	WebElement target = driver.findElement(By.id("dropbox"));
	(new Actions(driver)).dragAndDrop(element, target).perform();
	driver.findElement(By.linkText("Proceed")).click();
	Thread.sleep(1000);
	driver.findElement(By.linkText("Launch Popup Window")).click();
	Thread.sleep(1000);
	  ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));  
	driver.findElement(By.id("name")).sendKeys("ankitmalik");
	driver.findElement(By.id("submit")).click();
	 driver.switchTo().window(tabs2.get(0));
	 driver.findElement(By.linkText("Proceed")).click();
	 Thread.sleep(1000);
	 driver.findElement(By.linkText("Generate Token")).click();
	 Thread.sleep(1000);
	 String value=driver.findElement(By.id("token")).getText();
	 String value1=value.substring(7);
	 Cookie cookie = new Cookie("Token", value1);
	 driver.manage().addCookie(cookie);
	 (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Proceed")));
	 driver.findElement(By.linkText("Proceed")).click();
}
}