package factoryBrowser;

import java.util.Collections;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import liveguru.user.GlobalConstants;

public class ChromeDriverManager implements BrowserFactory {

	@Override
	public WebDriver getBrowserDriver() {
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-geolocation");
		// options.addArguments("--incognito");

		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_directory", GlobalConstants.getGlobalConstants().getDownloadFolderPath());
		chromePrefs.put("profile.default_content_setting_values.notifications", 2);
		chromePrefs.put("credentials_enable_service", false);
		chromePrefs.put("profile.password_manager_enabled", false);
		chromePrefs.put("autofill.profile_enabled", false);
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--disable-notifications");

		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");

		return new ChromeDriver(options);
	}

}
