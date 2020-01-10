package com.bae.service.test.selenium.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.bae.service.test.selenium.constants.Constants;
import com.bae.service.test.selenium.pages.FreezerPage;
import com.bae.service.test.selenium.pages.IndexPage;
import com.bae.service.test.selenium.pages.IndividualFreezerPage;

public class FreezerPageFunctionalityTest {

	private WebDriver driver;
	
	private String validInput = "garage freezer";
	private String numberInput = "34";
	private String specialCharacterInput = "£";
	private String shortCharacterCount = "hello";
	private String alert;

	@Before
	public void setup() {
		System.setProperty(Constants.PROPERTY, Constants.PATH);
		driver = new ChromeDriver();
	}
	
	@Test
	public void test() throws InterruptedException {
		this.driver.manage().window().fullscreen();
		this.driver.get("http://35.176.212.133:8181/FreezerApplication/Freezer.html");
		
		FreezerPage freezerPage = PageFactory.initElements(driver, FreezerPage.class);
		freezerPage.emptyFreezer();
		freezerPage.createFreezer(validInput);
		Thread.sleep(2000);
		freezerPage.hasFreezerBeenAdded();
		assertEquals(freezerPage.hasFreezerBeenAddedCorrectly(),validInput);
		
		freezerPage.createFreezer(numberInput);
		alert = this.driver.switchTo().alert().getText();
		assertEquals("Please enter a valid freezer name (No numbers)",alert);
		this.driver.switchTo().alert().accept();
		freezerPage.clearAddInput();
		Thread.sleep(2000);
		
		freezerPage.createFreezer(specialCharacterInput);
		Thread.sleep(1000);
		alert = this.driver.switchTo().alert().getText();
		assertEquals("Please enter a valid freezer name (no special characters)",alert);
		this.driver.switchTo().alert().accept();
		freezerPage.clearAddInput();
		Thread.sleep(2000);
		
		freezerPage.createFreezer(shortCharacterCount);
		alert = this.driver.switchTo().alert().getText();
		assertEquals("Please enter a valid freezer name of 6 characters or more",alert);
		this.driver.switchTo().alert().accept();
		freezerPage.clearAddInput();
		Thread.sleep(2000);
		
		freezerPage.createFreezer("");
		alert = this.driver.switchTo().alert().getText();
		assertEquals("Please enter a valid freezer name",alert);
		this.driver.switchTo().alert().accept();
		freezerPage.clearAddInput();
		Thread.sleep(2000);
		
		
		
	}
	
	@After
	public void tearDown() {
		this.driver.close();
	}

}
