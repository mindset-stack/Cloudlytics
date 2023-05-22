package Utility;

import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory extends Utility {
	Browser browser;
	BrowserContext context;
	public Page page;	

	public Page  initializeBrowser(String browserName) {

		System.out.println("\n Testing is going to carried on the " + browserName + " browser\n");
		prop = readPropertyFile();
		Playwright playwright = Playwright.create();
		switch (browserName.toLowerCase()) {
		case "chromium":
			browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
		case "firefox":
			browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
		case "safari":
			browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
		case "chrome":
			browser = playwright.webkit()
					.launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
			break;
		default:
			System.out.println("Please enter valid browser name.....................");
		}
//		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		context = browser
				.newContext(new Browser.NewContextOptions().setViewportSize(getScreenWidth(), getScreenHieght()));
		page = context.newPage();
		page.navigate(prop.getProperty("URL1"));
		return page;
	}

}
