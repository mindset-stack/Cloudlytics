package pages;

import com.microsoft.playwright.Page;

public class LoginPage extends CommonPage {
	Page page;

	public LoginPage(Page page) {
		super(page);
		this.page = page;
	}

}
