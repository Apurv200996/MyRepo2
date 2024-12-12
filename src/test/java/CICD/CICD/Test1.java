package CICD.CICD;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

import org.testng.annotations.*;

public class Test1 {

	public static String chromePath = "";
	public static WebDriver driver;

	public static String getPropertyValue(String key) throws IOException {
		String userDir = System.getProperty("user.dir");
		String configPath = userDir + "\\config\\appConfig.properties";
		String value = "";
		FileInputStream fis = new FileInputStream(configPath);
		Properties prop = new Properties();
		prop.load(fis);
		value = prop.getProperty(key);
		return value;
	}

	
	@Test
	public void exit()
	{
		driver.quit();
	}
	@Test
	@Parameters("strParams")
	public void launchBrowser(String strParams) {
		try {
			chromePath = getPropertyValue("chromePath");
			String browserName = strParams.split("@#")[0];
			String url = strParams.split("@#")[1];
			if (browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", chromePath);
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized");
			    driver = new ChromeDriver(options);
				driver.get(url);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
