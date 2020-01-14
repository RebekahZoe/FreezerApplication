package com.bae.service.test.selenium.tests;



import static org.junit.Assert.assertEquals;
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

public class NavigationTest {
	
	private WebDriver driver;

	@Before
	public void setup() {
		System.setProperty(Constants.PROPERTY, Constants.PATH);
		driver = new ChromeDriver();
	}
	
	@Test
	public void test() throws InterruptedException {
		this.driver.manage().window().fullscreen();
		this.driver.get("http://35.176.212.133:8181/FreezerApplication/");
		
		IndexPage indexPage = PageFactory.initElements(driver, IndexPage.class);
		indexPage.clickFreezerApp();
		assertEquals(driver.getCurrentUrl(),"http://35.176.212.133:8181/FreezerApplication/index.html");
		indexPage.clickHome();
		assertEquals(driver.getCurrentUrl(),"http://35.176.212.133:8181/FreezerApplication/index.html");
		indexPage.clickGitHub();
		assertEquals(driver.getCurrentUrl(),"https://github.com/RebekahZoe/FreezerApplication");
		this.driver.get("http://35.176.212.133:8181/FreezerApplication/");
		indexPage.clickFreezers();
		assertEquals(driver.getCurrentUrl(),"http://35.176.212.133:8181/FreezerApplication/Freezer.html");
		
		FreezerPage freezerPage = PageFactory.initElements(driver, FreezerPage.class);
		freezerPage.clickFreezerApp();
		assertEquals(driver.getCurrentUrl(),"http://35.176.212.133:8181/FreezerApplication/index.html");
		this.driver.get("http://35.176.212.133:8181/FreezerApplication/Freezer.html");
		freezerPage.clickHome();
		assertEquals(driver.getCurrentUrl(),"http://35.176.212.133:8181/FreezerApplication/index.html");
		this.driver.get("http://35.176.212.133:8181/FreezerApplication/Freezer.html");
		freezerPage.clickGitHub();
		assertEquals(driver.getCurrentUrl(),"https://github.com/RebekahZoe/FreezerApplication");
		this.driver.get("http://35.176.212.133:8181/FreezerApplication/Freezer.html");
		freezerPage.clickFreezers();
		assertEquals(driver.getCurrentUrl(),"http://35.176.212.133:8181/FreezerApplication/Freezer.html");
		
		IndividualFreezerPage individualFreezerPage = PageFactory.initElements(driver, IndividualFreezerPage.class);
		individualFreezerPage.clickFreezerApp();
		assertEquals(driver.getCurrentUrl(),"http://35.176.212.133:8181/FreezerApplication/index.html");
		this.driver.get("http://35.176.212.133:8181/FreezerApplication/IndividualFreezer.html");
		individualFreezerPage.clickHome();
		assertEquals(driver.getCurrentUrl(),"http://35.176.212.133:8181/FreezerApplication/index.html");
		this.driver.get("http://35.176.212.133:8181/FreezerApplication/IndividualFreezer.html");
		individualFreezerPage.clickGitHub();
		assertEquals(driver.getCurrentUrl(),"https://github.com/RebekahZoe/FreezerApplication");
		this.driver.get("http://35.176.212.133:8181/FreezerApplication/IndividualFreezer.html");
		individualFreezerPage.clickFreezers();
		assertEquals(driver.getCurrentUrl(),"http://35.176.212.133:8181/FreezerApplication/Freezer.html");
		
	}
	
	@After
	public void tearDown() {
		this.driver.close();
	}
	
}