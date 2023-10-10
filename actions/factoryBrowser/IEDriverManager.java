package factoryBrowser;

import static org.apache.commons.lang3.SystemUtils.IS_OS_WINDOWS;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

public class IEDriverManager implements BrowserFactory {

	@Override
	public WebDriver getBrowserDriver() {
		if (!IS_OS_WINDOWS) {
			throw new BrowserNotSupportedException("IE not supported on " + System.getProperty("os.name"));
		}

		InternetExplorerOptions options = new InternetExplorerOptions();
		options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, "true");
		options.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "true");
		options.setCapability("ignoreProtectedModeSettings", "true");
		options.setCapability("ignoreZoomSettings", "true");
		options.setCapability("requireWindowFocus", "true");
		options.setCapability("enableElementCacheCleanup", "true");
		return new InternetExplorerDriver();
	}

}
