package factoryEnvironment;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import liveguru.frontend.GlobalConstants;

public class SaucelabFactory {
	private WebDriver driver;
	private String browserName;
	private String osName;

	public SaucelabFactory(String browserName, String osName) {
		this.browserName = browserName;
		this.osName = osName;
	}

	public WebDriver createDriver() {
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("browserName", browserName);
		capability.setCapability("platformName", osName);
		capability.setCapability("browserVersion", "latest");

		Map<String, Object> sauceOptions = new HashMap<>();
		sauceOptions.put("screenResolution", "1920x1080");
		capability.setCapability("sauce:options", sauceOptions);

		try {
			driver = new RemoteWebDriver(new URL(GlobalConstants.getGlobalConstants().getSaucelabUrl()), capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}
}
