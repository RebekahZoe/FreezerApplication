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

public class IndividualFreezerPageEditTest {

private WebDriver driver;
	
	private String freezerName = "Garage Freezer";
	private String validNameInput = "curry";
	private String letters = "abc";
	private String numberInput = "3";
	private String numberInput2 = "2";
	private String numberInput3 = "0";
	private String decimal = "0.5";
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
        
        iFPage.editItem(validNameInput, numberInput);
        Thread.sleep(500);
        String notInList = this.driver.switchTo().alert().getText();
        assertEquals("Item is not in this freezer",notInList);
        this.driver.switchTo().alert().dismiss();
        iFPage.clearEditInput();
        System.out.println("1");
        
        iFPage.createItem(validNameInput, numberInput);
        Thread.sleep(500);
        assertTrue(iFPage.hasItemBeenAdded());
        assertEquals(iFPage.hasItemBeenAddedCorrectly(),(validNameInput + numberInput));
        iFPage.clearEditInput();
        System.out.println("1");
        
        iFPage.editItem(validNameInput, numberInput2);
        Thread.sleep(500);
        assertEquals(iFPage.hasItemBeenAddedCorrectly(),(validNameInput + numberInput2));
        iFPage.clearEditInput();
        System.out.println("1");
        
        
        iFPage.editItem(validNameInput,decimal);
        String alert = this.driver.switchTo().alert().getText();
        Thread.sleep(500);
        assertEquals("Please enter a valid quantity (must be a whole number)",alert);
        this.driver.switchTo().alert().dismiss();
        iFPage.clearEditInput();
        System.out.println("2");
        
        iFPage.editItem(validNameInput, specialCharacterInput);
        String alert2 = this.driver.switchTo().alert().getText();
        assertEquals("Please enter a valid quantity (no special characters)",alert2);
        this.driver.switchTo().alert().dismiss();
        iFPage.clearEditInput();
        System.out.println("3");
        
        iFPage.editItem(validNameInput, letters);
        String alert3 = this.driver.switchTo().alert().getText();
        assertEquals("Please enter a valid quantity (no letters)",alert3);
        this.driver.switchTo().alert().dismiss();
        iFPage.clearEditInput();
        System.out.println("5");
        
        iFPage.editItem(validNameInput, numberInput3);
        String alert4 = this.driver.switchTo().alert().getText();
        assertEquals("Please enter a valid quantity (must be greater than 0)",alert4);
        this.driver.switchTo().alert().dismiss();
        iFPage.clearEditInput();
        System.out.println("6");
        
        iFPage.editItem(numberInput,decimal);
        String alert5 = this.driver.switchTo().alert().getText();
        assertEquals("Please enter a valid item name (No numbers) and a valid quantity (must be a whole number)",alert5);
        this.driver.switchTo().alert().dismiss();
        iFPage.clearEditInput();
        System.out.println("7");
        
        iFPage.editItem(numberInput, specialCharacterInput);
        String alert6 = this.driver.switchTo().alert().getText();
        assertEquals("Please enter a valid item name (No numbers) and a valid quantity (no special characters)",alert6);
        this.driver.switchTo().alert().dismiss();
        iFPage.clearEditInput();
        System.out.println("8");
        
        iFPage.editItem(numberInput, letters);
        String alert7 = this.driver.switchTo().alert().getText();
        assertEquals("Please enter a valid item name (No numbers) and a valid quantity (no letters)",alert7);
        this.driver.switchTo().alert().dismiss();
        iFPage.clearEditInput();
        System.out.println("9");
        
        iFPage.editItem(numberInput, numberInput3);
        String alert8 = this.driver.switchTo().alert().getText();
        assertEquals("Please enter a valid item name (No numbers) and a valid quantity (must be greater than 0)",alert8);
        this.driver.switchTo().alert().dismiss();
        iFPage.clearEditInput();
        System.out.println("10");
		
        iFPage.editItem(specialCharacterInput,decimal);
        String alert9 = this.driver.switchTo().alert().getText();
        assertEquals("Please enter a valid item name (No special characters) and a valid quantity (must be a whole number)",alert9);
        this.driver.switchTo().alert().dismiss();
        iFPage.clearEditInput();
        System.out.println("11");
        
        iFPage.editItem(specialCharacterInput, specialCharacterInput);
        String alert10 = this.driver.switchTo().alert().getText();
        assertEquals("Please enter a valid item name (No special characters) and a valid quantity (no special characters)",alert10);
        this.driver.switchTo().alert().dismiss();
        iFPage.clearEditInput();
        System.out.println("12");
        
        iFPage.editItem(specialCharacterInput, letters);
        String alert11 = this.driver.switchTo().alert().getText();
        assertEquals("Please enter a valid item name (No special characters) and a valid quantity (no letters)",alert11);
        this.driver.switchTo().alert().dismiss();
        iFPage.clearEditInput();
        System.out.println("13");
        
        iFPage.editItem(specialCharacterInput, numberInput3);
        String alert12 = this.driver.switchTo().alert().getText();
        assertEquals("Please enter a valid item name (No special characters) and a valid quantity (must be greater than 0)",alert12);
        this.driver.switchTo().alert().dismiss();
        iFPage.clearEditInput();
        System.out.println("14");
		
        iFPage.editItem(shortCharacterCount,decimal);
        String alert13 = this.driver.switchTo().alert().getText();
        assertEquals("Please enter a valid item name (must be at least 3 characters) and a valid quantity (must be a whole number)",alert13);
        this.driver.switchTo().alert().dismiss();
        iFPage.clearEditInput();
        System.out.println("15");
        
        iFPage.editItem(shortCharacterCount, specialCharacterInput);
        String alert14 = this.driver.switchTo().alert().getText();
        assertEquals("Please enter a valid item name (must be at least 3 characters) and a valid quantity (no special characters)",alert14);
        this.driver.switchTo().alert().dismiss();
        iFPage.clearEditInput();
        System.out.println("16");
        
        iFPage.editItem(shortCharacterCount, letters);
        String alert15 = this.driver.switchTo().alert().getText();
        assertEquals("Please enter a valid item name (must be at least 3 characters) and a valid quantity (no letters)",alert15);
        this.driver.switchTo().alert().dismiss();
        iFPage.clearEditInput();
        System.out.println("17");
        
        iFPage.editItem(shortCharacterCount, numberInput3);
        String alert16 = this.driver.switchTo().alert().getText();
        assertEquals("Please enter a valid item name (must be at least 3 characters) and a valid quantity (must be greater than 0)",alert16);
        this.driver.switchTo().alert().dismiss();
        iFPage.clearEditInput();
        System.out.println("18");
		
        iFPage.editItem(shortCharacterCount, numberInput);
        String alert17 = this.driver.switchTo().alert().getText();
        assertEquals("Please enter a valid item name (must be at least 3 characters)",alert17);
        this.driver.switchTo().alert().dismiss();
        iFPage.clearEditInput();
        System.out.println("19");
        
        iFPage.editItem(specialCharacterInput, numberInput);
        String alert18 = this.driver.switchTo().alert().getText();
        assertEquals("Please enter a valid item name (No special characters)",alert18);
        this.driver.switchTo().alert().dismiss();
        iFPage.clearEditInput();
        System.out.println("20");
        
        iFPage.editItem(numberInput, numberInput);
        String alert19 = this.driver.switchTo().alert().getText();
        assertEquals("Please enter a valid item name (No numbers)",alert19);
        this.driver.switchTo().alert().dismiss();
        iFPage.clearEditInput();

        System.out.println("21");
     
	}
	
	@After
	public void tearDown() {
		
		FreezerPage freezerPage = PageFactory.initElements(driver, FreezerPage.class);
		IndividualFreezerPage iFPage = PageFactory.initElements(driver, IndividualFreezerPage.class);
		
		iFPage.deleteItem(validNameInput);
		this.driver.switchTo().alert().accept();
		this.driver.get("http://35.176.212.133:8181/FreezerApplication/Freezer.html");
		freezerPage.deleteFreezer(freezerName);
		this.driver.switchTo().alert().accept();
		this.driver.close();
	}

}
