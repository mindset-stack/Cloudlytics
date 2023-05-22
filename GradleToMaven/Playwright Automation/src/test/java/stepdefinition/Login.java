package stepdefinition;

import java.util.Properties;

import com.microsoft.playwright.Page;

import Utility.PlaywrightFactory;
import Utility.Utility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.LoginPage;


public class Login  {

	PlaywrightFactory pf = new PlaywrightFactory();
	LoginPage lp;
	Page page;
	Properties prop;
	Utility util = new Utility();
	
/*Compulsory steps for each page*/
	@Given("^Initialize \"([^\"]*)\" browser for login$")
	public void Initialize_Browser(String browserName) {
		page = pf.initializeBrowser(browserName);
		lp = new LoginPage(page);
		prop = util.readPropertyFile(); //Optional step
	}

//		@When("^Drag and Drop$")
//		public void DragandDrop() throws Exception {
//			lp.dragandDrop();
//		
//	}

}
