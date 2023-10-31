package factoryEnvironment;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import liveguru.frontend.GlobalConstants;

public class BrowserStackFactory {
	private WebDriver driver;
	private String browserName;
	private String osName;
	private String osVersion;

	public BrowserStackFactory(String browserName, String osName, String osVersion) {
		this.browserName = browserName;
		this.osName = osName;
		this.osVersion = osVersion;
	}

	public WebDriver createDriver() {
		DesiredCapabilities capility = new DesiredCapabilities();
		capility.setCapability("os", osName);
		capility.setCapability("os_version", osVersion);
		capility.setCapability("browser", browserName);
		capility.setCapability("browser_version", "latest");
		capility.setCapability("browserstack.debug", "true");
		capility.setCapability("project", "NopCommerce");
		capility.setCapability("resolution", "1920x1080");
		capility.setCapability("name", "Run on " + osName + " | " + osVersion + " | " + browserName);

		try {
			driver = new RemoteWebDriver(new URL(GlobalConstants.getGlobalConstants().getBrowserStackUrl()), capility);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}

}
