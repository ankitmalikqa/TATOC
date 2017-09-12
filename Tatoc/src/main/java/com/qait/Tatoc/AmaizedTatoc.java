package com.qait.Tatoc;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmaizedTatoc {

	public static void main(String args[]) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(); 
		driver.get("http://10.0.1.86/tatoc/");
		 (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Basic Course")));
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("document.getElementsByTagName('a')[0].click()");
		 (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".redbox")));
		 js.executeScript("document.getElementsByClassName('redbox')[0].click()");
		 (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Basic Course")));
		 js.executeScript("document.getElementsByTagName('a')[0].click()");
		 (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".greenbox")));
		 js.executeScript("document.getElementsByClassName('greenbox')[0].click()");
		 (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("main")));
		 driver.switchTo().frame("main");
		 JavascriptExecutor js1 = (JavascriptExecutor) driver;
         //js1.executeScript("document.getElementById('answer').setAttribute('class', 'red')");
         String mainColor=(String)js1.executeScript("return document.getElementById('answer').getAttribute('class')");
		 driver.switchTo().frame("child");
		 JavascriptExecutor js2 = (JavascriptExecutor) driver;
		 String childColor=(String)js2.executeScript("return document.getElementById('answer').getAttribute('class')");
		 while(!mainColor.equals(childColor))
			{
				driver.switchTo().defaultContent();
				driver.switchTo().frame("main");
				js2.executeScript("document.getElementsByTagName('a')[0].click()");
				driver.switchTo().frame("child");
				childColor = driver.findElement(By.id("answer")).getAttribute("class");
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("main");
			js2.executeScript("document.getElementsByTagName('a')[1].click()");
			driver.switchTo().defaultContent();
			JavascriptExecutor js3 = (JavascriptExecutor) driver;
			js3.executeScript("document.getElementById('dragbox').setAttribute('style', 'position: relative; left: 31px; top: -67px;')");
			js3.executeScript("document.getElementsByTagName('a')[0].click()");
	        js3.executeScript("document.getElementsByTagName('a')[0].click()");
			(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Launch Popup Window")));
			  ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
			    driver.switchTo().window(tabs2.get(1));  
			    js3.executeScript("document.getElementById('name').value='malik'");
			    js3.executeScript("document.getElementById('submit').click()");
			 driver.switchTo().window(tabs2.get(0));
			 js3.executeScript("document.getElementsByTagName('a')[1].click()");
			 (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Generate Token")));
			 js3.executeScript("document.getElementsByTagName('a')[0].click()");
			 (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("token")));
			 String value=(String)js2.executeScript("return document.getElementById('token').innerHTML");
			 System.out.println(value);
			 String value1=value.substring(7);
			 Cookie cookie = new Cookie("Token", value1);
			 driver.manage().addCookie(cookie);
			 Thread.sleep(2000);
			 js3.executeScript("document.getElementsByTagName('a')[1].click()");
	}
}
