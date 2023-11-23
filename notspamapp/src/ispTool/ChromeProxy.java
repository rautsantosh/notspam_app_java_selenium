/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispTool;

import isp.ispBean;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import utility.CustomException;

/**
 *
 * @author rakeshv
 */
public class ChromeProxy {

    private WebDriver webDriver;

    public WebDriver setupProxy(ispBean bean, String webUrl) throws CustomException {
        try {
            Socket pingSocket = new Socket();
            pingSocket.connect(new InetSocketAddress(bean.getProxyIP(), Integer.parseInt(bean.getProxyPort())), 3000);
            boolean flag = pingSocket.isConnected();

        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("Unable to connect proxy at this moment.", "5003");
        }
        try {
            this.webDriver = null;
            String profileDir = null;
            if (bean.getEmail().toLowerCase().contains("@yahoo")) {
                profileDir = "D:\\Temp\\Yahoo\\";
            }
            if (bean.getEmail().toLowerCase().contains("@aol")) {
                profileDir = "D:\\Temp\\Aol\\";
            }
            String email = bean.getEmail().toString().trim();
            email = email.replace("@", "").replace(".", "");
            profileDir = profileDir + email;
            String proxyUrl = bean.getProxyIP() + ":" + bean.getProxyPort();

            Proxy proxy = new Proxy();
            proxy.setHttpProxy(proxyUrl);
            proxy.setSslProxy(proxyUrl);
            System.setProperty("webdriver.chrome.driver", bean.getChromeDriverPath());

            ChromeOptions options = new ChromeOptions();
            options.setCapability("proxy", proxy);

            if (bean.getProxyUsername().toLowerCase().trim().equals("indiausr")) {
                options.addExtensions(new File("C:\\notspam\\F4F35C1771995DA6F9E3FBD77030EF72.zip"));

            }
            options.addArguments("disable-infobars");
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");

            String windowsUser = System.getProperty("user.name").toLowerCase();
//            if (bean.getEmail().toLowerCase().contains("@aol") || (bean.getEmail().toLowerCase().contains("@yahoo"))) {
//
//                System.out.println("Profile Directory : " + profileDir);
//                options.addArguments("user-data-dir=" + profileDir);
//                options.addArguments("--restore-last-session");
//
//            }

            this.webDriver = new ChromeDriver(options);

            this.webDriver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

//                if (bean.getEmailUsername().trim().endsWith("yahoo.com") && (bean.getSendKeyword().trim().toUpperCase().equals("__AUTORESPONSE") || bean.getSendKeyword().trim().toUpperCase().equals("__RESPONSECONTACT"))) {
//                    this.webDriver.get("http://94.249.168.14/testMsg.html");
//                    Thread.sleep(2000);
//                    this.webDriver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "a");
//                    this.webDriver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "c");
//                    Thread.sleep(1000);
//                }
            if (bean.getEmail().contains("@yahoo")) {

                if (!this.webDriver.getCurrentUrl().contains("mail.yahoo.com/neo/launch")) {
                    this.webDriver.navigate().to(webUrl);
                } else {
                    this.webDriver.navigate().to("https://mail.yahoo.com/neo/launch?.src=ym&reason=myc");
                }
                //this.webDriver.navigate().to(webUrl);
            }

            this.webDriver.navigate().to(webUrl);
        } catch (Exception e) {
            e.printStackTrace();
            this.webDriver.close();
            this.webDriver.quit();
            throw new CustomException("Unknown Error", "5010");
        }
        return this.webDriver;
    }

}
