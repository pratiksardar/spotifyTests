package org.sel.spotify.factory;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class DriverFactory {
    WebDriver driver;

    OptionsManager optionsManager;

    public static String highlightElement;

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public WebDriver initDriver(Properties prop) {

        String browserName = prop.getProperty("browser").trim();
//        String clientUser = prop.getProperty("clientID").trim();
//        String clientKey = prop.getProperty("clientKey").trim();
        System.out.println("browser name is : " + browserName);
//        System.out.println("clientUser name is : " + clientUser);
//        System.out.println("clientKey  is : " + clientKey);

        highlightElement = prop.getProperty("highlight");

        optionsManager = new OptionsManager(prop);

        tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));

        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().get(prop.getProperty("url"));

        return getDriver();
    }

    public synchronized static WebDriver getDriver() {
        return tlDriver.get();
    }

    public Properties initProp() {
        Properties prop = new Properties();
        FileInputStream ip = null;
        try {
            ip = new FileInputStream("./src/main/resources/config/config.properties");
        } 		catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  prop;
    }

    /**
     * take screenshot
     */
//    public static String getScreenshot() {
//        if(getDriver() != null){
//        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
//        String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
//        File destination = new File(path);
//        try {
//            FileUtil.copyFile(srcFile, destination);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return path;
//    }
//        else {
//            System.out.println("No driver found");
//            return "No driver found";
//        }
//    }

}