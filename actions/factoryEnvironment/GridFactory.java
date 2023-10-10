package factoryEnvironment;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import factoryBrowser.BrowserList;
import liveguru.user.GlobalConstants;

public class GridFactory {
	private WebDriver driver;
	private String browserName;
	private String ipAddress;
	private String portNumber;

	public GridFactory(String browserName, String ipAddress, String portNumber) {
		this.browserName = browserName;
		this.ipAddress = ipAddress;
		this.portNumber = portNumber;
	}

	public WebDriver createDriver() {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
		DesiredCapabilities capability = null;

		if (browser == BrowserList.FIREFOX) {
			FirefoxOptions options = new FirefoxOptions();
			options.merge(capability);
		} else if (browser == BrowserList.CHROME) {
			ChromeOptions options = new ChromeOptions();
			options.merge(capability);
		} else if (browser == BrowserList.EDGE) {
			driver = new EdgeDriver();
		} else if (browser == BrowserList.IE) {
			driver = new InternetExplorerDriver();
		} else if (browser == BrowserList.COCCOC) {
			ChromeOptions options = new ChromeOptions();

			if (GlobalConstants.getGlobalConstants().getOsName().startsWith("Windows")) {
				options.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
			} else {
				options.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
			}

			driver = new ChromeDriver(options);
		} else if (browser == BrowserList.SAFARI) {
			driver = new SafariDriver();
		} else {
			throw new RuntimeException("Please enter correct browser name!");
		}

		try {
			driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/wd/hub", ipAddress, portNumber)), capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}
}
