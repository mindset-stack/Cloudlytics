/**
 * @author Manjunath Kodikoppamath
 * @role QA Engineer
  */

package pages;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Locator.ScreenshotOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.WaitForSelectorOptions;
import com.microsoft.playwright.options.WaitForSelectorState;

import Utility.PlaywrightFactory;
import Utility.Utility;

public class CommonPage extends Utility {

	Page page;
	Browser browser;
	BrowserContext context;
	Utility util; // Utility Page

	PlaywrightFactory pf = new PlaywrightFactory();

	public CommonPage(Page page) {
		this.page = page;
		// TODO Auto-generated constructor stub
	}

//	public void invokeApplication(String browserName,String url) {
//		page=pf.initializeBrowser(browserName);
//		page.navigate(prop.getProperty(url));
//	}

	public void refreshPage() {
		page.reload();
	}

	public boolean checkVisibility(String locatorName) throws InterruptedException {
		for (int i = 0; i < 3; i++) {
			Thread.sleep(1000);
			if (page.locator(locatorName).isVisible() == true)
				return true;
		}
		return false;
	}

	/*
	 * MethodName : enterText Description : it's enter the text in text box
	 **/
	public void enterText(String locator, String text) {
		page.locator(locator).fill(text);
	}

//Drag and Drop	
	public void dragandDrop(String srclocator ,String destlocator) throws Exception {
		page.locator(srclocator).dragTo(page.locator(destlocator));
	}

	/* upload Attachment Method */
	public void uploadAttachment(String filename, String locator) throws InterruptedException {
		FileChooser fileChooser1 = page.waitForFileChooser(() -> page.click(locator));
		fileChooser1.setFiles(Paths.get("attachments\\" + filename));
	}

	/*
	 * Get  Notification Message  (pass only notification locator to this method)
	 */
	public String getNotificationMessage(String locator) {
		page.waitForSelector(locator);
		String msg = page.textContent(locator);
		System.out.println(msg);
		page.waitForSelector(locator, new WaitForSelectorOptions().setState(WaitForSelectorState.DETACHED));
		return msg;
	}

	/* GetText Method of any locator */
	public String getText(String locator) {
		return page.locator(locator).textContent();
	}

	/* Click Locator */
	public void clickElement(String locator) {
		page.click(locator);
	}

	/* Double Click Locator */
	public void doubleClickElement(String locator) {
		page.dblclick(locator);
	}

	/* Take Full page Screenshot and store screenshot png file under project */
	public void takeFullPageScreenshot() {
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("FullPage.png")).setFullPage(true));
	}

	/*
	 * Take current view of the page Screenshot and store screenshot current view png
	 * file under project
	 */
	public void takeCurrentViewScreenshot() {
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("CurrentView.png")).setFullPage(false));
	}

	/* Take screenshot of particular locator */
	public void takeLocatorScreenshot(String locator) {
		page.locator(locator).screenshot(new ScreenshotOptions().setPath(Paths.get("LocatorView.png")));
	}

	/* Click a locator inside a Frame */
	public void frameHandling(String locator, String framelocator) {
		page.frame(locator).click(framelocator);
	}

	/* pop up handling with printing text and accepting the alert */
	public void popUpHandlingWithAccepting() {
		page.onDialog(dailog -> {
			String text = dailog.message();
			System.out.println(text);
			dailog.accept();
		});
	}

	/* pop up handling with printing text and rejecting the alert */
	public void popUpHandlingWithDismiss() {
		page.onDialog(dailog -> {
			String text = dailog.message();
			System.out.println(text);
			dailog.dismiss();
		});
	}

	/* pop up handling with printing text  prompt and accepting the alert */
	public void popUpHandlingWithAcceptAndEnterText(String enterText) {
		page.onDialog(dailog -> {
			String text = dailog.message();
			System.out.println(text);
			dailog.accept(enterText);
		});
	}

	/* Open new tab with specific url */
	public void openNewWindowWithSpecificUrl(String url) {
		Page popup = page.waitForPopup(() -> {
			page.click("a[target='_blank']");
		});
		popup.waitForLoadState();
		popup.navigate(url);
	}

	/* Open new tab with specific url and close */
	public void openNewWindowWithSpecificUrlAndClose(String url) {
		Page popup = page.waitForPopup(() -> {
			page.click("a[target='_blank']");
		});
		popup.waitForLoadState();
		popup.navigate(url);
		popup.close();
	}

	/* wait for locator to attach */
	public void waitForLocatorToAttach(String locator) {
		page.waitForSelector(locator, new WaitForSelectorOptions().setState(WaitForSelectorState.ATTACHED));
	}

	/* wait for locator to hide */
	public void waitForLocatorToHide(String locator) {
		page.waitForSelector(locator, new WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN));
	}

	/* wait for locator to visible */
	public void waitForLocatorToVisible(String locator) {
		page.waitForSelector(locator, new WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
	}

	/* wait for locator to detach */
	public void waitForLocatorToDetach(String locator) {
		page.waitForSelector(locator, new WaitForSelectorOptions().setState(WaitForSelectorState.DETACHED));
	}

	/*After doing particular action wait for the page to load*/
	public void waitForLoadState(String locator)
	{
		page.waitForLoadState();
	}
	
	/* Tear Down Method helps to close all the browser */
	public void tearDown() {
		page.context().browser().close();
	}

}