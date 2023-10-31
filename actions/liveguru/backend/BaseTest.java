package liveguru.backend;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import factoryBrowser.BrowserList;
import factoryEnvironment.BrowserStackFactory;
import factoryEnvironment.CrossbrowserFactory;
import factoryEnvironment.EnvironmentList;
import factoryEnvironment.GridFactory;
import factoryEnvironment.LambdaFactory;
import factoryEnvironment.LocalFactory;
import factoryEnvironment.SaucelabFactory;

public class BaseTest {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	protected final Log log;

	@BeforeSuite
	public void initBeforeSuit() {
		deleteAllureRport();
	}

	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}

	protected WebDriver getBrowserDriver(String envName, String serverName, String browserName, String ipAddress, String portNumber, String osName, String osVersion) {
		switch (envName) {
		case "local":
			driver.set(new LocalFactory(browserName).createDriver());
			break;
		case "grid":
			driver.set(new GridFactory(browserName, ipAddress, portNumber).createDriver());
			break;

		case "browserStack":
			driver.set(new BrowserStackFactory(browserName, osName, osVersion).createDriver());
			break;

		case "saucelab":
			driver.set(new SaucelabFactory(browserName, osName).createDriver());
			break;

		case "crossBrowser":
			driver.set(new CrossbrowserFactory(browserName, osName).createDriver());
			break;

		case "lambda":
			driver.set(new LambdaFactory(browserName, osName).createDriver());
			break;

		default:
			driver.set(new LocalFactory(browserName).createDriver());
			break;
		}

		driver.get().manage().window().maximize();
		driver.get().get(getEnvironmentValue(serverName));

		return driver.get();
	}

	String projectPath = System.getProperty("user.dir");

	protected WebDriver getBrowserDriver(String browserName) {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
		if (browser == BrowserList.FIREFOX) {
			driver.set(new FirefoxDriver());
		} else if (browser == BrowserList.CHROME) {
			ChromeOptions options = new ChromeOptions();

			Map<String, Object> prefs = new HashMap<String, Object>();

			prefs.put("profile.default_content_settings.popups", 0);
			prefs.put("download.default_directory", GlobalConstants.getGlobalConstants().getProjectPath() + "\\downloadFiles");
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);

			options.setExperimentalOption("prefs", prefs);
			options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

			driver.set(new ChromeDriver(options));
		} else if (browser == BrowserList.EDGE) {
			driver.set(new EdgeDriver());
		} else if (browser == BrowserList.IE) {
			driver.set(new InternetExplorerDriver());
		} else if (browser == BrowserList.COCCOC) {
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
			driver.set(new ChromeDriver(options));
		} else if (browser == BrowserList.SAFARI) {
			driver.set(new SafariDriver());
		} else {
			throw new RuntimeException("Please enter correct browser name!");
		}

		driver.get().manage().window().maximize();
		driver.get().get(GlobalConstants.getGlobalConstants().getDevAppUrl());

		return driver.get();
	}

	public WebDriver getDriverInstance() {
		return driver.get();
	}

	protected String getEnvironmentValue(String serverName) {
		String envUrl = null;
		EnvironmentList environment = EnvironmentList.valueOf(serverName.toUpperCase());

		switch (environment) {
		case DEV:
			envUrl = "https://account.collegeboard.org/login/signUp";
			break;
		case TESTING:
			envUrl = "http://live.techpanda.org/";
			break;
		case STAGING:
			envUrl = "https://account.collegeboard.org/login/signUp";
			break;
		case PRE_PROD:
			envUrl = "https://account.collegeboard.org/login/signUp";
			break;
		case PROD:
			envUrl = "https://account.collegeboard.org/login/signUp";
			break;

		default:
			envUrl = null;
			break;
		}
		return envUrl;
	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);

	}

	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	public void deleteAllureRport() {
		try {
			String pathFolderDownload = GlobalConstants.getGlobalConstants().getProjectPath() + "/allure-results";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					System.out.println(listOfFiles[i].getName());
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	protected void closeBrowserAndDriver() {
		String cmd = "";
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driver.get().toString().toLowerCase();
			log.info("Driver instance name = " + driverInstanceName);

			if (driverInstanceName.contains("chrome")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				} else {
					cmd = "pkill chromedriver";
				}
			} else if (driverInstanceName.contains("internetexplorer")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driverInstanceName.contains("firefox")) {
				if (osName.contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				} else {
					cmd = "pkill geckodriver";
				}
			} else if (driverInstanceName.contains("edge")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				} else {
					cmd = "pkill msedgedriver";
				}
			} else if (driverInstanceName.contains("opera")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
				} else {
					cmd = "pkill operadriver";
				}
			} else if (driverInstanceName.contains("safari")) {
				if (osName.contains("mac")) {
					cmd = "pkill safaridriver";
				}
			}

			if (driver != null) {
				driver.get().manage().deleteAllCookies();
				driver.get().quit();

				driver.remove();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	protected String getCurrentDate() {
		DateTime nowUTC = new DateTime(DateTimeZone.UTC);
		int day = nowUTC.getDayOfMonth();
		if (day < 10) {
			String dayValue = "0" + day;
			return dayValue;
		}
		return day + "";
	}

	protected String getCurrentMonth() {
		DateTime now = new DateTime(DateTimeZone.UTC);
		int month = now.getMonthOfYear();
		if (month < 10) {
			String monthValue = "0" + month;
			return monthValue;
		}
		return month + "";
	}

	protected String getCurrentYear() {
		DateTime now = new DateTime(DateTimeZone.UTC);
		return now.getYear() + "";
	}

	protected String getCurrentDay() {
		return getCurrentDate() + "/" + getCurrentMonth() + "/" + getCurrentYear();
	}

}
