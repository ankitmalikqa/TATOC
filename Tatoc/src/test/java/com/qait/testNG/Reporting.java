package com.qait.testNG;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;



public class Reporting implements ITestListener {
	WebDriver driver = null;
//	String filePath = "Screenshots/";
static String success="";
static String failure="";
static String skipped="";
	public void onTestFailure(ITestResult result) {
		System.out.println("***** Error " + result.getName() + " test has failed *****");
		String methodName = result.getName().toString().trim();
		
		failure=failure+result.getName()+"-";
		double timetaken = (result.getEndMillis() - result.getStartMillis()) / 1000;

	}

	public void takeScreenShot(String methodName) {
		// get the driver
		
	
	}

	public void onTestFinish(ITestContext context) {

	

	}

	public void onTestStart(ITestResult result) {

		
	}

	public void onTestSuccess(ITestResult result) {

		System.out.println(result.getName()+"tEST HAS PASSED");
		System.err.println(result.getAttributeNames());
		success=success+result.getName()+"-";
		double timetaken = (result.getEndMillis() - result.getStartMillis()) / 1000;

		

	}

	public void onTestSkipped(ITestResult result) {
		double timetaken = (result.getEndMillis() - result.getStartMillis()) / 1000;
		skipped=skipped+result.getName()+"-";
	
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext result) {
	

	}

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

	}
}