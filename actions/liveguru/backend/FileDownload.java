package liveguru.backend;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FileDownload {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	public void test01_DownloadAndDeleteFileFullName() throws Exception {
		String file = "smilechart.xls";
		driver.get("http://spreadsheetpage.com/index.php/file/C35/P10/");
		driver.manage().window().maximize();
		Duration.ofSeconds(15);
		// Xóa toàn bộ file trong thư mục
		deleteAllFileInFolder();
		// Click vào title chứa file tải về
		driver.findElement(By.xpath("//a[contains(text(),'smilechart.xls')]")).click();
		Duration.ofSeconds(15);
		// Verify có 1 file được tải về
		waitForDownloadFileFullnameCompleted(file);
		// Đếm số lượng file trong thư mục sau khi tải về
		int countFileBeforeDelete = countFilesInDirectory();
		System.out.println("SAU KHI TAI VE: " + countFileBeforeDelete);
		// Verify số lượng file tải về bằng 1
		Assert.assertEquals(countFileBeforeDelete, 1);
		// Xóa file đã tải về
		deleteFileFullName(file);
		// Đếm số lượng file trong thư mục sau khi xóa
		int countFileAfterDelete = countFilesInDirectory();
		System.out.println("SAU KHI XOA: " + countFileAfterDelete);
		// Verify số lượng file tải về bằng 0
		Assert.assertEquals(countFileAfterDelete, 0);
	}

	@Test
	public void test02_DownloadAndDeleteFileContainName() throws Exception {
		String file = ".xls";
		driver.get("http://spreadsheetpage.com/index.php/file/C35/P10/");
		Duration.ofSeconds(15);
		driver.manage().window().maximize();
		// Xóa toàn bộ file trong thư mục
		deleteAllFileInFolder();
		// Click vào title chứa file tải về
		driver.findElement(By.xpath("//a[contains(.,'lister.xls')]")).click();
		Duration.ofSeconds(15);
		// Verify có 1 file được tải về chứa đuôi file mở rộng là .xls
		waitForDownloadFileContainsNameCompleted(file);
		// Đếm số lượng file trong thư mục sau khi tải về
		int countFileBeforeDelete = countFilesInDirectory();
		System.out.println("SAU KHI TAI VE: " + countFileBeforeDelete);
		// Verify số lượng file tải về bằng 1
		Assert.assertEquals(countFileBeforeDelete, 1);
		deleteFileContainName(file);
		// Đếm số lượng file trong thư mục sau khi xóa
		int countFileAfterDelete = countFilesInDirectory();
		System.out.println("SAU KHI XOA: " + countFileAfterDelete);
		// Verify số lượng file tải về bằng 0
		Assert.assertEquals(countFileAfterDelete, 0);
	}

	public void deleteAllFileInFolder() {
		try {
			String pathFolderDownload = getPathContainDownload();
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	public String getPathContainDownload() {
		String machine_name = System.getProperty("user.home");
		return String.format("%s\\Downloads\\", machine_name);
	}

	public void waitForDownloadFileFullnameCompleted(String fileName) throws Exception {
		int i = 0;
		while (i < 30) {
			boolean exist = isFileExists(fileName);
			if (exist) {
				i = 30;
			}
			Thread.sleep(500);
			i++;
		}
	}

	public boolean isFileExists(String file) {
		try {
			String pathFolderDownload = getPathContainDownload();
			File files = new File(pathFolderDownload + file);
			return files.exists();
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
	}

	public int countFilesInDirectory() {
		String pathFolderDownload = getPathContainDownload();
		File file = new File(pathFolderDownload);
		int i = 0;
		for (File listOfFiles : file.listFiles()) {
			if (listOfFiles.isFile()) {
				i++;
			}
		}
		return i;
	}

	public void deleteFileFullName(String fileName) {
		if (isFileExists(fileName)) {
			deleteFullName(fileName);
		}
	}

	public void deleteFullName(String fileName) {
		try {
			if (isFileExists(fileName)) {
				String pathFolderDownload = getPathContainDownload();
				File files = new File(pathFolderDownload + fileName);
				files.delete();
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	public void waitForDownloadFileContainsNameCompleted(String fileName) throws Exception {
		int i = 0;
		while (i < 30) {
			boolean exist = isFileContain(fileName);
			if (exist == true) {
				i = 30;
			}
			Thread.sleep(500);
			i = i + 1;
		}
	}

	public boolean isFileContain(String fileName) {
		try {
			boolean flag = false;
			String pathFolderDownload = getPathContainDownload();
			File dir = new File(pathFolderDownload);
			File[] files = dir.listFiles();
			if (files == null || files.length == 0) {
				flag = false;
			}
			for (int i = 1; i < files.length; i++) {
				if (files[i].getName().contains(fileName)) {
					flag = true;
				}
			}
			return flag;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
	}

	public void deleteFileContainName(String fileName) {
		deleteContainName(fileName);
	}

	public void deleteContainName(String fileName) {
		try {
			String files;
			String pathFolderDownload = getPathContainDownload();
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					files = listOfFiles[i].getName();
					if (files.contains(fileName)) {
						new File(listOfFiles[i].toString()).delete();
					}
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
