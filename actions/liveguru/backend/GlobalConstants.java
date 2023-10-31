package liveguru.backend;

import java.io.File;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GlobalConstants {
	private static GlobalConstants globalInstance;

	private GlobalConstants() {

	}

	public static synchronized GlobalConstants getGlobalConstants() {
		if (globalInstance == null) {
			globalInstance = new GlobalConstants();
		}
		return globalInstance;
	}

	private final String devAppUrl = "https://demo.nopcommerce.com/";
	private final String adminDevAppUrl = "https://admin-demo.nopcommerce.com/";
	private final String testingAppUrl = "https://demo.nopcommerce.com/";
	private final String adminTestingAppUrl = "https://admin-demo.nopcommerce.com/";

	private final String projectPath = System.getProperty("user.dir");
	private final String osName = System.getProperty("os.name");
	private final String javaVersion = System.getProperty("java.version");

	private final String uploadFolderPath = projectPath + File.separator + "uploadFiles" + File.separator;
	private final String downloadFolderPath = projectPath + File.separator + "downloadFiles";
	private final String browserLogPath = projectPath + File.separator + "browserLogs" + File.separator;
	private final String dragDropHTML5 = projectPath + File.separator + "dragDropHTML5";
	private final String autoITScript = projectPath + File.separator + "autoIT";
	private final String reportNGScreenshot = projectPath + File.separator + "reportNGImages" + File.separator;

	private final long shortTimeout = 5;
	private final long longTimeout = 30;

	private final String techpandaUrl = "http://live.techpanda.org/";
	private final String adminTechpandaUrl = "http://live.techpanda.org/index.php/backendlogin/customer/";

	private final String collegeUrl = "https://account.collegeboard.org/login/signUp";

	private final String browserStackUsername = "cuongluu_BHC8pL";
	private final String browserStackKey = "z7wxNyWhmUx8dpGm4VKk";
	private final String browserStackUrl = "https://" + browserStackUsername + ":" + browserStackKey + "@hub-cloud.browserstack.com/wd/hub";

	private final String saucelabUsername = "oauth-devil.cuong96-b0404";
	private final String saucelabKey = "a7788e8e-b07c-40d7-a071-57e7eb72897f";
	private final String saucelabUrl = "https://" + saucelabUsername + ":" + saucelabKey + "@ondemand.eu-central-1.saucelabs.com:443/wd/hub";

	private final String crossBrowserUsername = "dam@automationfc.com".replaceAll("@", "%40");
	private final String crossBrowserKey = "u87f334283d50903";
	private final String crossBrowserUrl = "http://" + crossBrowserUsername + ":" + crossBrowserKey + "@hub.crossbrowsertesting.com:80/wd/hub";

	private final String lambdaUsername = "luucuong880";
	private final String lambdaKey = "4ZgaA5UeHoEtkH0coztb4E5uCyf9zpWnaPnAYfFmRn16mWfRey";
	private final String lambdaUrl = "https://" + lambdaUsername + ":" + lambdaKey + "@hub.lambdatest.com/wd/hub";
}
