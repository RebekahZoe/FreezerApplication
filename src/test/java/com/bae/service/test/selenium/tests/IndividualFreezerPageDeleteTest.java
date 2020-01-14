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
import com.bae.service.test.selenium.pages.IndividualFreezerPage;

public class IndividualFreezerPageDeleteTest {

	private WebDriver driver;

	private String freezerName = "Garage Freezer";
	private String validInput = "curry";
	private String numberInput = "34";
	private String specialCharacterInput = "£";
	private String shortCharacterCount = "he";

	

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
		IndividualFreezerPage iFPage = PageFactory.initElements(driver, IndividualFreezerPage.class);

		freezerPage.createFreezer(freezerName);
		Thread.sleep(1000);
		freezerPage.individualFreezer();

		assertEquals(iFPage.getTitle(), freezerName);

		iFPage.createItem(validInput, numberInput);
		Thread.sleep(500);
		assertTrue(iFPage.hasItemBeenAdded());
		assertEquals(iFPage.hasItemBeenAddedCorrectly(), (validInput + numberInput));
		iFPage.clearAddInput();
		System.out.println("1");

		iFPage.deleteItem(validInput);
		this.driver.switchTo().alert().dismiss();
		iFPage.hasItemBeenAdded();
		assertEquals(iFPage.hasItemNotBeenDeleted(), validInput);
		iFPage.clearDeleteInput();
		System.out.println("1");

		iFPage.deleteItem(validInput);
		this.driver.switchTo().alert().accept();
		assertFalse(iFPage.hasBeenDeletedCorrectly());
		System.out.println("2");

		iFPage.deleteItem(numberInput);
		String alert5 = this.driver.switchTo().alert().getText();
		assertEquals("Please enter a valid item name (No numbers)", alert5);
		this.driver.switchTo().alert().accept();
		iFPage.clearDeleteInput();
		System.out.println("3");

		iFPage.deleteItem(specialCharacterInput);
		String alert6 = this.driver.switchTo().alert().getText();
		assertEquals("Please enter a valid item name (No special characters)", alert6);
		this.driver.switchTo().alert().accept();
		iFPage.clearDeleteInput();
		System.out.println("4");

		iFPage.deleteItem(shortCharacterCount);
		String alert7 = this.driver.switchTo().alert().getText();
		assertEquals("Please enter a valid item name (must be at least 3 characters)", alert7);
		this.driver.switchTo().alert().accept();
		iFPage.clearDeleteInput();
		System.out.println("5");

		iFPage.deleteItem("");
		String alert8 = this.driver.switchTo().alert().getText();
		assertEquals("Please enter a valid item name", alert8);
		this.driver.switchTo().alert().accept();
		iFPage.clearDeleteInput();
		System.out.println("6");

		iFPage.deleteItem(validInput);
		this.driver.switchTo().alert().accept();
		Thread.sleep(1000);
		String alert9 = this.driver.switchTo().alert().getText();
		assertEquals("Item is not in this freezer", alert9);
		this.driver.switchTo().alert().accept();
		iFPage.clearDeleteInput();
		System.out.println("7");
	}

	@After
	public void tearDown() {
		FreezerPage freezerPage = PageFactory.initElements(driver, FreezerPage.class);
		IndividualFreezerPage iFPage = PageFactory.initElements(driver, IndividualFreezerPage.class);
		
		iFPage.deleteItem(validInput);
		this.driver.switchTo().alert().accept();
		this.driver.get("http://35.176.212.133:8181/FreezerApplication/Freezer.html");
		freezerPage.deleteFreezer(freezerName);
		this.driver.switchTo().alert().accept();
		this.driver.close();
	}

}
