/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isp;

import ispTool.ChromeProxy;
import org.openqa.selenium.*;
import utility.CustomException;
import java.util.*;
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author rakeshv
 */
public class Hotmail {

    private WebDriver webDriver;
    private ispBean bean;
    private static String ispUrl = "https://outlook.live.com/owa/";
    private boolean messageFound;
    private String cookieFile = "C:\\notspam\\app\\cookies\\";
    private int totalMessageCounter = 0;
    private int flagPer;

    /**
     * @return the webDriver
     */
    public WebDriver getWebDriver() {
        return webDriver;
    }

    /**
     * @param webDriver the webDriver to set
     */
    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /**
     * @return the bean
     */
    public ispBean getBean() {
        return bean;
    }

    /**
     * @param bean the bean to set
     */
    public void setBean(ispBean bean) {
        this.bean = bean;
    }

    /**
     * @return the isMessageFound
     */
    public boolean isMessageFound() {
        return messageFound;
    }

    /**
     * @param isMessageFound the isMessageFound to set
     */
    public void setMessageFound(boolean isMessageFound) {
        this.messageFound = isMessageFound;
    }

    private boolean isCookieFileExists() throws CustomException, IOException {
        String fileName = this.cookieFile + this.getBean().getEmail();
        boolean isExists = false;
        int daysBack = 0; // Delete 15 days old cookie file.
        try {
            File cookieFile = new File(fileName);
            isExists = cookieFile.exists();
            if (isExists) {
                File file = new File(fileName);
                long age = new Date().getTime() - file.lastModified();
                long minutes = age / 60000;
                System.out.println(minutes + " Min old.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isExists;

    }

    private void createCookie() {
        try {
            String currentURL = this.getWebDriver().getCurrentUrl();

            this.webDriver.navigate().to("https://login.live.com/favicon.ico");
            Thread.sleep(3000);
            HashSet<Cookie> cookieSet = (HashSet<Cookie>) this.webDriver.manage().getCookies();
            String fileName = this.cookieFile + this.getBean().getEmail();
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            Iterator iterator = cookieSet.iterator();
            while (iterator.hasNext()) {
                Cookie cookie = (Cookie) iterator.next();
                String cookieString = cookie.getName() + "\t" + cookie.getValue();
                writer.println(cookieString);
            }
            writer.close();
            this.webDriver.navigate().to(currentURL);
            System.out.println("Cookie Saved to : " + this.cookieFile + this.getBean().getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    WebElement waitFluent(String find, String dom) {
        Wait wait = new FluentWait(this.getWebDriver())
                .withTimeout(35, TimeUnit.SECONDS)
                .pollingEvery(4, TimeUnit.SECONDS)
                .ignoring(Exception.class);
        return (WebElement) wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement element = null;
                if (find.equals("xpath")) {
                    element = driver.findElement(By.xpath(dom));
                } else if (find.equals("name")) {
                    element = driver.findElement(By.name(dom));
                } else if (find.equals("id")) {
                    element = driver.findElement(By.id(dom));
                }
                return element;
            }
        });
    }

    WebElement waitFluent(WebElement mainElement, String find, String dom) {
        Wait wait = new FluentWait(this.getWebDriver())
                .withTimeout(8, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(Exception.class);
        return (WebElement) wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement element = null;
                if (find.equals("xpath")) {
                    element = mainElement.findElement(By.xpath(dom));
                } else if (find.equals("name")) {
                    element = mainElement.findElement(By.name(dom));
                } else if (find.equals("id")) {
                    element = mainElement.findElement(By.id(dom));
                }
                return element;
            }
        });
    }

    boolean isElementFound(String find, String dom) {
        try {

            Wait wait = new FluentWait(this.getWebDriver())
                    .withTimeout(8, TimeUnit.SECONDS)
                    .pollingEvery(2, TimeUnit.SECONDS)
                    .ignoring(Exception.class);
            WebElement element = (WebElement) wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    WebElement element = null;
                    if (find.equals("xpath")) {
                        element = driver.findElement(By.xpath(dom));
                    } else if (find.equals("name")) {
                        element = driver.findElement(By.name(dom));
                    } else if (find.equals("id")) {
                        element = driver.findElement(By.id(dom));
                    }
                    return element;
                }
            });
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    boolean isElementFound(String find, String dom, int interval) {
        try {

            Wait wait = new FluentWait(this.getWebDriver())
                    .withTimeout(interval, TimeUnit.SECONDS)
                    .pollingEvery(200, TimeUnit.MILLISECONDS)
                    .ignoring(Exception.class);
            WebElement element = (WebElement) wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    WebElement element = null;
                    if (find.equals("xpath")) {
                        element = driver.findElement(By.xpath(dom));
                    } else if (find.equals("name")) {
                        element = driver.findElement(By.name(dom));
                    } else if (find.equals("id")) {
                        element = driver.findElement(By.id(dom));
                    }
                    return element;
                }
            });
            return true;
        } catch (Exception ex) {
            //ex.printStackTrace();
            return false;
        }
    }

    boolean isElementFound(WebElement mainElement, String find, String dom, int interval) {
        try {

            Wait wait = new FluentWait(this.getWebDriver())
                    .withTimeout(interval, TimeUnit.SECONDS)
                    .pollingEvery(1, TimeUnit.SECONDS)
                    .ignoring(Exception.class);
            WebElement element = (WebElement) wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    WebElement element = null;
                    if (find.equals("xpath")) {
                        element = mainElement.findElement(By.xpath(dom));
                    } else if (find.equals("name")) {
                        element = mainElement.findElement(By.name(dom));
                    } else if (find.equals("id")) {
                        element = mainElement.findElement(By.id(dom));
                    }
                    return element;
                }
            });
            return true;
        } catch (Exception ex) {
            //ex.printStackTrace();
            return false;
        }
    }

    private void signIn() throws CustomException {
        String errorTimout = null;
        try {

            this.getWebDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            JavascriptExecutor jse = (JavascriptExecutor) this.getWebDriver();
            if (this.getWebDriver().getCurrentUrl().contains("index_en.html") || this.getWebDriver().getTitle().contains("free personal email and calendar")) {
                jse.executeScript("arguments[0].click();", this.getWebDriver().findElement(By.xpath(".//a[text()='Sign in']")));
            }
            this.getWebDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            if (this.getWebDriver().getCurrentUrl().toString().contains("ERROR: The requested URL could not be")) {
                throw new Exception("P");
            }

            Thread.sleep(1000);

            if (this.isElementFound("xpath", "//*[@id='main-message']//div[@jscontent='errorCode']", 1) || this.isElementFound("xpath", ".//div[contains(text(),'TUNNEL_CONNECTION_FAILED')]", 2)) {
                errorTimout = this.getWebDriver().findElement(By.xpath("//*[@id='main-message']//div[@jscontent='errorCode']")).getText().toString();
                throw new Exception("O");
            }
            this.getWebDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            this.waitFluent("name", "loginfmt").sendKeys(this.getBean().getEmail());
            Thread.sleep(500);
            this.waitFluent("xpath", ".//*[@value='Next']").click();
            Thread.sleep(3000);

//            if (this.isElementFound("xpath", ".//*[@id='usernameError']")) {
//                throw new Exception("C");
//            }
            if (this.isElementFound("xpath", ".//*[@name='passwd']", 20)) {

            }
            if (this.getWebDriver().findElements(By.xpath(".//*[@name='passwd']")).size() > 0) {
                this.waitFluent("name", "passwd").clear();
                Thread.sleep(1000);
                this.waitFluent("name", "passwd").sendKeys(this.getBean().getPassword().trim());
                WebElement signIn = this.getWebDriver().findElement(By.xpath(".//*[@value='Sign in']"));
                Actions action = new Actions(this.getWebDriver());
                action.moveToElement(signIn).perform();
                WebDriverWait wait = new WebDriverWait(this.getWebDriver(), 30);
                wait.until(ExpectedConditions.elementToBeClickable(signIn));
                signIn.click();
            }

            try {
                this.getWebDriver().manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
            } catch (Exception ex) {
                ex.printStackTrace();
                Thread.sleep(2000);
            }
            this.getWebDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            String currentURL = this.getWebDriver().getCurrentUrl();
            currentURL = currentURL.toString().toLowerCase().trim();
            System.out.println(currentURL);

            //if (this.getWebDriver().findElements(By.xpath(".//*[@id=\"passwordError\"]")).size() > 0 && this.getWebDriver().findElement(By.xpath(".//*[@id=\"passwordError\"]")).isEnabled()) {
            //    throw new Exception("F");
            //}
            if (currentURL.contains("tou/accrue")) {
                this.getWebDriver().findElement(By.xpath(".//input[@value='Next']")).click();
            }

            if (currentURL.contains("remind")) {
                this.waitFluent("xpath", ".//input[@id='iLooksGood']").click();

            }

            if (this.getWebDriver().getCurrentUrl().contains("apps/upsell?app=Authenticator")) {
                this.waitFluent("xpath", ".//a[@id=\"iCancel\"]").click();
            }

            try {
                this.getWebDriver().findElement(By.xpath(".//*[@title='Junk Email']")).click();
                System.out.println("Able to click on junk folder");
                return;
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            boolean proofsVerify = false;
            if (this.getWebDriver().getCurrentUrl().toLowerCase().contains("proofs/verify") && this.isElementFound("xpath", ".//div[contains(text(),'Call us overprotective')]", 6)) {
                if (this.isElementFound("xpath", ".//*[@id='iMPBLearnMoreLink']")) {
                    proofsVerify = true;
                    this.getWebDriver().navigate().to("https://account.microsoft.com/?ref=MeControl&lang=en-US&partnerId=smc&partnerDomain=support.microsoft.com&refd=support.microsoft.com");
                    this.getWebDriver().navigate().to("https://outlook.live.com/mail/inbox");
                    ArrayList<String> tabs = new ArrayList<String>(this.getWebDriver().getWindowHandles());
                    for (String handle : this.getWebDriver().getWindowHandles()) {
                        String title = this.getWebDriver().switchTo().window(handle).getTitle();
                        if (title.contains("Outlook")) {
                            break;
                        }
                    }

                } else {
                    throw new Exception("J");
                }
            }

            if (this.getWebDriver().getCurrentUrl().toLowerCase().contains("childConsent/childLanding") || this.getWebDriver().getCurrentUrl().toLowerCase().contains("child-consent/child-landing")) {
                throw new Exception("N");
            }

            if (this.getWebDriver().getCurrentUrl().toLowerCase().contains("proofs")) {
                this.getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                System.out.println("URL==>>" + this.getWebDriver().getCurrentUrl().toLowerCase());
                int tryToSkip = 2;
                while (tryToSkip-- > 0) {
                    try {
                        System.out.println("Size=" + this.getWebDriver().findElements(By.xpath(".//*[@id='iShowSkip']")).size());
                        if (this.isElementFound("xpath", ".//*[@id='iShowSkip']", 5)) {
                            this.waitFluent("xpath", ".//*[@id='iShowSkip']").click();
                            break;
                        }
                    } catch (Exception ex) {
                        //ex.printStackTrace();
                        System.out.println("Trying to skip it again");
                    }
                }

                this.getWebDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            }

            if (this.getWebDriver().getCurrentUrl().toLowerCase().contains("proofs")) {

                if (this.isElementFound("id", "iShowSkip") && this.getWebDriver().findElement(By.xpath(".//*[@id='iShowSkip']")).isDisplayed()) {
                    this.waitFluent("id", "iShowSkip").click();
                }

                if (this.isElementFound("xpath", ".//input[@id='iLooksGood']") && this.getWebDriver().findElement(By.xpath(".//input[@id='iLooksGood']")).isDisplayed()) {
                    //this.getWebDriver().findElement(By.xpath(".//input[@id='iLooksGood']")).click();
                    this.waitFluent("xpath", ".//input[@id='iLooksGood']").click();
                }

                if (this.isElementFound("xpath", ".//select[@id='DisplayPhoneCountryISO']")) {
                    throw new Exception("H");
                }

            }

            System.out.println("3");
            if (this.getWebDriver().getCurrentUrl().toLowerCase().contains("cancel")) {
                if (this.isElementFound("id", "iLandingViewAction", 60)) {
                    this.waitFluent("id", "iLandingViewAction").click();
                }
            }

            if (this.getWebDriver().getCurrentUrl().toLowerCase().contains("abuse")) {
                throw new Exception("G");
            }

            if (this.getWebDriver().getCurrentUrl().toLowerCase().contains("default")) {
                this.waitFluent("xpath", ".//input[@value='Try to return to Outlook.com']").click();

            }

            if (this.getWebDriver().getCurrentUrl().toLowerCase().contains("recover") || this.getWebDriver().getCurrentUrl().toLowerCase().contains("identity")) {
                throw new Exception("H");
            }

            if (this.getWebDriver().getCurrentUrl().contains("tou/accrue")) {
                this.waitFluent("xpath", ".//input[@value='Next']").click();

            }
            this.getWebDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

            if (this.getWebDriver().getCurrentUrl().toLowerCase().contains("accrue")) {
                Random randomGenerator = new Random();
                Thread.sleep(2000);
                int randomMonth = randomGenerator.nextInt(10);
                int randomDay = randomGenerator.nextInt(27);
                int randomYearTemp = (int) (18 + (Math.random() * (70 - 18))); //random number between 18-70
                int randomYear = Calendar.getInstance().get(Calendar.YEAR) - randomYearTemp;

                String tempSubject[] = this.generateRandomWords(new Random().nextInt(6) + 3);

                if (this.isElementFound("xpath", ".//*[@id='idFirstName']") && this.getWebDriver().findElement(By.xpath(".//*[@id='idFirstName']")).isDisplayed()) {
                    this.waitFluent("xpath", ".//*[@id='idFirstName']").sendKeys(tempSubject[0]);
                }

                if (this.isElementFound("xpath", ".//*[@id='idLastName']") && this.getWebDriver().findElement(By.xpath(".//*[@id='idLastName']")).isDisplayed()) {
                    this.waitFluent("xpath", ".//*[@id='idLastName']").sendKeys(tempSubject[1]);
                }

                Select dropdownMonth = new Select(this.waitFluent("xpath", ".//*[@id='BirthMonth']"));
                dropdownMonth.selectByValue(String.valueOf(randomMonth + 1));

                Select dropdownDay = new Select(this.waitFluent("xpath", ".//*[@id='BirthDay']"));
                dropdownDay.selectByValue(String.valueOf(randomDay + 1));

                Select dropdownYear = new Select(this.waitFluent("xpath", ".//*[@id='BirthYear']"));
                dropdownYear.selectByValue(String.valueOf(randomYear));
                this.waitFluent("xpath", ".//*[@id='iNext']").click();

                if (this.getWebDriver().getCurrentUrl().toLowerCase().contains("accrue")) {
                    Thread.sleep(100);
                    this.waitFluent("xpath", ".//input[@id='iSpeedbumpContinue' and @value='Next']").click();
                }
            }

            if (this.getWebDriver().getCurrentUrl().toLowerCase().contains("cancel")) {
                this.waitFluent("id", "iLandingViewAction").click();
            }
            System.out.println("4");

            System.out.println("5");

            try {
                Actions action1 = new Actions(this.getWebDriver());
                action1.sendKeys(Keys.ESCAPE).build().perform();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (this.getWebDriver().getCurrentUrl().toString().toLowerCase().contains("languageselection")) {
                    System.out.println("Language Selection Page");
                    WebElement element = this.waitFluent("name", "tzid");
                    Select select = new Select(element);
                    select.selectByValue("Eastern Standard Time");
                    System.out.println("Time zone selected");
                    this.waitFluent("xpath", ".//*[@class='signinTxt']").click();

                }
            } catch (Exception e) {
                throw new Exception("I");
            }

            System.out.println("6");

            if (this.getWebDriver().getCurrentUrl().toLowerCase().contains("abuse")) {
                throw new Exception("G");
            }

            System.out.println("7");

            if (this.isElementFound("xpath", ".//footer[@class='footer']/div[@class='dotImageDiv']/span", 3)) {
                int counter = this.getWebDriver().findElements(By.xpath(".//footer[@class='footer']/div[@class='dotImageDiv']/span")).size();
                System.out.println("Counter value=" + counter);
                for (int i = 0; i < counter; i++) {
                    //this.getWebDriver().findElement(By.xpath(".//button[contains(@class,'nextButton')]")).click();
                    this.waitFluent("xpath", ".//button[contains(@class,'nextButton')]").click();

                }
                if (this.isElementFound("xpath", ".//h1[text()='Upgrade to Outlook.com Premium']")) {
                    this.waitFluent("xpath", ".//footer[@class='footer']//div[@class='nextButton']").click();
                    System.out.println("Clicked on go premium  next button");
                }

                Thread.sleep(2000);
                if (this.isElementFound("xpath", ".//button[@class='primaryButton']")) {
                    //this.getWebDriver().findElement(By.xpath(".//button[@class='primaryButton']")).click();
                    this.waitFluent("xpath", ".//button[@class='primaryButton']").click();
                }
            }
            System.out.println("8");

            if (this.isElementFound("xpath", ".//i[@data-icon-name='CircleFill']", 3)) {

                int counter = this.getWebDriver().findElements(By.xpath(".//i[@data-icon-name='CircleFill']")).size();
                for (int i = 0; i < counter - 1; i++) {
                    //this.getWebDriver().findElement(By.xpath(".//button[@aria-label='Next slide']")).click();
                    this.waitFluent("xpath", ".//button[@aria-label='Next slide']").click();
                    System.out.println("Clicked on nextSlide button " + i);

                }

                if (this.isElementFound("xpath", ".//button[text()='Got it']")) {
                    this.waitFluent("xpath", ".//button[text()='Got it']").click();
                }

                if (this.isElementFound("xpath", ".//div[text()='Get started']")) {
                    this.waitFluent("xpath", ".//div[text()='Get started']").click();
                }
                if (this.getWebDriver().findElements(By.xpath(".//div[text()='Get started']")).size() > 0) {
                    this.getWebDriver().findElement(By.xpath(".//div[text()='Get started']")).click();
                    System.out.println("Clicked on go premium  next button New interface");
                }
            }

            if (isElementFound("xpath", ".//button[@class=\"refreshPageButton\"]")) {
                this.waitFluent("xpath", ".//button[@class=\"refreshPageButton\"]").click();
            }

            if (proofsVerify && this.getWebDriver().getCurrentUrl().toLowerCase().contains("proofs/verify") && this.isElementFound("xpath", ".//div[contains(text(),'Call us overprotective')]", 3)) {
                if (this.isElementFound("xpath", ".//*[@id='iMPBLearnMoreLink']")) {

                    this.getWebDriver().navigate().to("https://account.microsoft.com/?ref=MeControl&lang=en-US&partnerId=smc&partnerDomain=support.microsoft.com&refd=support.microsoft.com");

                    this.getWebDriver().navigate().to("https://outlook.live.com/mail/inbox");
                    ArrayList<String> tabs = new ArrayList<String>(this.getWebDriver().getWindowHandles());
                    for (String handle : this.getWebDriver().getWindowHandles()) {
                        String title = this.getWebDriver().switchTo().window(handle).getTitle();
                        if (title.contains("Outlook")) {
                            break;
                        }
                    }

                } else {
                    throw new Exception("J");
                }
            }

            try {
                if (this.getWebDriver().getCurrentUrl().toString().toLowerCase().contains("languageselection")) {
                    System.out.println("Language Selection Page");
                    WebElement element = this.waitFluent("name", "tzid");
                    Select select = new Select(element);
                    select.selectByValue("Eastern Standard Time");
                    System.out.println("Time zone selected");
                    this.waitFluent("xpath", ".//*[@class='signinTxt']").click();

                }
            } catch (Exception e) {
                throw new Exception("I");
            }

            //this.checkPopupInInbox();
            System.out.println("9");

            System.out.println("10");

            if (this.isElementFound("xpath", ".//i[@data-icon-name='CircleFill']", 2)) {

                int counter = this.getWebDriver().findElements(By.xpath(".//i[@data-icon-name='CircleFill']")).size();
                for (int i = 0; i < counter - 1; i++) {
                    //this.getWebDriver().findElement(By.xpath(".//button[@aria-label='Next slide']")).click();
                    this.waitFluent("xpath", ".//button[@aria-label='Next slide']").click();
                    System.out.println("Clicked on nextSlide button " + i);
                    Thread.sleep(200);
                }
                //Thread.sleep(2000); //need it 

                if (this.isElementFound("xpath", ".//button[text()='Got it']", 1)) {
                    this.waitFluent("xpath", ".//button[text()='Got it']").click();
                }

                if (this.isElementFound("xpath", ".//div[text()='Got it']", 1)) {
                    this.waitFluent("xpath", ".//div[text()='Got it']").click();
                }

                if (this.isElementFound("xpath", ".//*[text()='Got it']//ancestor::button", 1)) {

                    this.getWebDriver().findElement(By.xpath(".//*[text()='Got it']//ancestor::button")).click();
                }

                if (this.isElementFound("xpath", ".//div[text()='Get started']")) {
                    this.waitFluent("xpath", ".//div[text()='Get started']").click();
                }

            }

            int closePopup = 3;
            while (closePopup > 0) {
                try {

                    int ratting = (int) (7 + (Math.random() * (10 - 7)));
                    String rattingXpath = ".//div[text()='" + ratting + "']";
                    if (this.isElementFound("xpath", rattingXpath, 1)) {
                        System.out.println("Clicked on close button in moveInbxo");
                        this.waitFluent("xpath", rattingXpath).click();
                        this.waitFluent("xpath", ".//div[text()='Very likely']").click();
                        this.waitFluent("xpath", ".//*[text()='Skip']//ancestor::button[1]").click();
                        this.waitFluent("xpath", ".//*[text()='Close']//ancestor::button[1]").click();
                        Thread.sleep(1000);
                        this.pressEscape();

                    }
                    break;
                } catch (Exception e) {
                    closePopup--;
                    e.printStackTrace();
                }
            }

            System.out.println("11");

            try {
                Actions action1 = new Actions(this.getWebDriver());
                action1.sendKeys(Keys.ESCAPE).build().perform();
            } catch (Exception e) {
                e.printStackTrace();
            }
            /*
             commented
            
             if (this.isElementFound("xpath", ".//*[text()='Not now']//ancestor::button[1]", 1)) {

             this.waitFluent("xpath", ".//*[text()='Not now']//ancestor::button[1]").click();
             }*/

            if (this.isElementFound("xpath", ".//div[text()='Continue to Site']//ancestor::button[1]", 3)) {

                this.waitFluent("xpath", ".//div[text()='Continue to Site']//ancestor::button[1]").click();
            }

            if (this.isElementFound("xpath", "//*[@id='main-message']//div[@jscontent='errorCode']", 1) || this.isElementFound("xpath", ".//div[contains(text(),'TUNNEL_CONNECTION_FAILED')]", 2)) {
                errorTimout = this.getWebDriver().findElement(By.xpath("//*[@id='main-message']//div[@jscontent='errorCode']")).getText().toString();
                throw new Exception("O");
            }

            if (!this.isElementFound("xpath", ".//div[@aria-label='Message list']//div[@role='heading']/span[text()='Inbox']") || !this.isElementFound("xpath", ".//*[@aria-label='Content pane']")) {
                if (this.isElementFound("xpath", ".//button[@aria-label='Settings']", 3)) {

                    //this.getWebDriver().findElement(By.xpath(".//div[@aria-label='Settings']")).click();
                    try {

                        this.waitFluent("xpath", ".//button[@aria-label='Settings']").click();

                    } catch (Exception es) {
                        try {
                            WebElement settingBtn = this.getWebDriver().findElement(By.xpath(".//button[@aria-label='Settings']"));
                            Actions action1 = new Actions(this.getWebDriver());
                            action1.moveToElement(settingBtn).perform();
                            WebDriverWait wait2 = new WebDriverWait(this.getWebDriver(), 60);
                            wait2.until(ExpectedConditions.elementToBeClickable(settingBtn));
                            settingBtn.click();
                            es.printStackTrace();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            if (this.isElementFound("xpath", ".//*[@data-icon-name=\"Cancel\"]/ancestor::button[1]") && this.getWebDriver().findElement(By.xpath(".//*[@data-icon-name=\"Cancel\"]/ancestor::button[1]")).isDisplayed()) {
                                this.getWebDriver().findElement(By.xpath(".//*[@data-icon-name=\"Cancel\"]/ancestor::button[1]")).click();
                            }
                        }
                    }
                    try {
                        this.waitFluent("xpath", ".//*[text()='View all Outlook settings']/ancestor::button[1]").click();
                        this.waitFluent("xpath", ".//*[text()=\"Don't sort my messages\"]/ancestor::label[1]").click();
                        Thread.sleep(1000);
                        WebElement readingPanelEle = this.getWebDriver().findElement(By.xpath(".//*[text()='On the right']//parent::div"));
                        Actions action3 = new Actions(this.getWebDriver());
                        action3.moveToElement(readingPanelEle).perform();
                        readingPanelEle.click();

                        if (this.isElementFound("xpath", ".//*[text()='Save']/ancestor::button[1]")) {
                            this.waitFluent("xpath", ".//*[text()='Save']/ancestor::button[1]").click();
                        }
                        this.getWebDriver().findElement(By.xpath(".//*[@data-icon-name=\"Cancel\"]/ancestor::button[1]")).click();
                    } catch (Exception e) {
                        if (this.isElementFound("xpath", ".//*[@data-icon-name=\"Cancel\"]/ancestor::button[1]") && this.getWebDriver().findElement(By.xpath(".//*[@data-icon-name=\"Cancel\"]/ancestor::button[1]")).isDisplayed()) {
                            this.getWebDriver().findElement(By.xpath(".//*[@data-icon-name=\"Cancel\"]/ancestor::button[1]")).click();
                        }
                        e.printStackTrace();
                    }

                    Thread.sleep(1000);
//                    int p = 0;
//                    while (p++ < 3) {
//                        try {
//                            WebElement focusedInbox = this.getWebDriver().findElement(By.xpath(".//*[aria-label=\"Focused Inbox\" and aria-checked=\"true\"]"));
//                            Actions action2 = new Actions(this.getWebDriver());
//                            action2.moveToElement(focusedInbox).perform();
//                            WebDriverWait wait2 = new WebDriverWait(this.getWebDriver(), 30);
//                            wait2.until(ExpectedConditions.elementToBeClickable(focusedInbox));
//                            break;
//                        } catch (Exception e) {
//                            this.waitFluent("xpath", ".//button[@aria-label='Settings']").click();
//                        }
//                    }

                    if (this.isElementFound("xpath", ".//span[text()='Show on the right']/ancestor::div[1]")) {
                        System.out.println("clicked on show on the right");
                        //this.getWebDriver().findElement(By.xpath(".//span[text()='Show on the right']/ancestor::div[1]")).click();
                        this.waitFluent("xpath", ".//span[text()='Show on the right']/ancestor::div[1]").click();
                    }
                    /*
                     if (this.isElementFound("xpath", ".//*[@id='options-quick-undefined']//span[text()='Group messages by date']/ancestor::div[2]//button[@aria-label=\"On\"]")) {
                     this.getWebDriver().findElement(By.xpath(".//*[@id='options-quick-undefined']//span[text()='Group messages by date']/ancestor::div[2]//button[@aria-label=\"On\"]")).click();
                     }
                     System.out.println("Converion View Set");
                     if (this.isElementFound("xpath", ".//*[@id='options-quick-conversations']/ancestor::div[1]//input[@type='radio']/parent::div//span[text()='Off']")) {
                     this.getWebDriver().findElement(By.xpath("//*[@id='options-quick-conversations']/ancestor::div[1]//input[@type='radio']/parent::div//span[text()='Off']")).click();
                     }
                     System.out.println("Focused Inbox");
                     if (this.isElementFound("xpath", ".//button[contains(@class,'ms-Panel-closeButton') and @data-is-visible='true']", 3)) {

                     this.getWebDriver().findElement(By.xpath(".//button[contains(@class,'ms-Panel-closeButton') and @data-is-visible='true']")).click();
                     }*/

                }
            }

            if (!this.isElementFound("xpath", ".//*[text()='New message']//ancestor::button[1]", 15)) {
                throw new Exception("KK");
            }

        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().equals("A")) {
                throw new CustomException("Email input not found", "102");
            } else if (e.getMessage().equals("B")) {
                throw new CustomException("Next button not found.", "103");
            } else if (e.getMessage().equals("C")) {
                throw new CustomException("That Microsoft account doesn't exist.", "104");
            } else if (e.getMessage().equals("D")) {
                throw new CustomException("Password input not found.", "105");
            } else if (e.getMessage().equals("E")) {
                throw new CustomException("Sign In button not found.", "106");
            } else if (e.getMessage().equals("F")) {
                throw new CustomException("Your account or password is incorrect.", "107");
            } else if (e.getMessage().equals("G")) {
                throw new CustomException("Your account has been temporarily suspended.", "108");
            } else if (e.getMessage().equals("H")) {
                throw new CustomException("Help us to protect your account", "109");
            } else if (e.getMessage().equals("I")) {
                throw new CustomException("Unable to set language for your account.", "110");
            } else if (e.getMessage().equals("J")) {
                throw new CustomException("Call us overprotective...", "111");
            } else if (e.getMessage().equals("K")) {
                throw new CustomException("Interface changed for Hotmail", "112");
            } else if (e.getMessage().equals("L")) {
                throw new CustomException("Network connection reset.", "113");
            } else if (e.getMessage().equals("M")) {
                throw new CustomException("We're updating our terms", "114");
            } else if (e.getMessage().equals("N")) {
                throw new CustomException("Parents account permission required", "115");
            } else if (e.getMessage().equals("O")) {
                throw new CustomException("Proxy issue " + errorTimout, "116");
            } else if (e.getMessage().equals("P")) {
                throw new CustomException("ERROR: The requested URL could not be retrieved", "117");
            } else {
                e.printStackTrace();
                throw new CustomException("Unable to sign in.", "118");
            }
        }
    }

    public void signOut() throws CustomException {
        try {
            if (this.isElementFound("xpath", ".//div[contains(text(),'t send the following message')]", 2)) {
                throw new Exception("C");
            }

            Thread.sleep(1000);

            if (this.isElementFound("xpath", ".//*[contains(@aria-label,'Account manage')]")) {
                this.waitFluent("xpath", ".//*[contains(@aria-label,'Account manage')]").click();
                Thread.sleep(500);
                JavascriptExecutor jse = (JavascriptExecutor) this.getWebDriver();
                jse.executeScript("arguments[0].click();", this.waitFluent("xpath", ".//a[@id='meControlSignoutLink']"));

                // this.waitFluent("xpath", ".//a[@id='meControlSignoutLink']").click();
            } else if (this.isElementFound("xpath", ".//button[contains(@aria-label,'sign out')]")) {
                this.waitFluent("xpath", ".//button[contains(@aria-label,'sign out')]").click();
                this.waitFluent("xpath", ".//a[@id='meControlSignoutLink']").click();
            } else {
                this.waitFluent("xpath", ".//button[@aria-label='Account' and @title='Account']").click();
                this.waitFluent("xpath", ".//div[text()='Sign out']/ancestor::button").click();
            }

        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().equals("A")) {
                throw new CustomException("Unable to click on sign out menu.", "119");
            } else if (e.getMessage().equals("B")) {
                throw new CustomException("Unable to click on sign out button.", "120");
            } else if (e.getMessage().equals("C")) {
                throw new CustomException("Asking for captcha verification", "121");
            } else {
                throw new CustomException("Unable to sign out.", "122");
            }
        }
    }

    private void moveSpam() throws CustomException {
        try {
            try {

                int closePopup = 3;
                while (closePopup > 0) {
                    try {

                        int ratting = (int) (7 + (Math.random() * (10 - 7)));
                        String rattingXpath = ".//div[text()='" + ratting + "']";
                        if (this.isElementFound("xpath", rattingXpath, 1)) {
                            System.out.println("Clicked on close button in moveInbxo");
                            this.waitFluent("xpath", rattingXpath).click();
                            this.waitFluent("xpath", ".//div[text()='Very likely']").click();
                            this.waitFluent("xpath", ".//*[text()='Skip']//ancestor::button[1]").click();
                            this.waitFluent("xpath", ".//*[text()='Close']//ancestor::button[1]").click();
                            Thread.sleep(1000);
                            this.pressEscape();

                        }
                        break;
                    } catch (Exception e) {
                        this.pressEscape();
                        closePopup--;
                        e.printStackTrace();
                    }
                }
                Actions action = new Actions(this.getWebDriver());
                action.sendKeys(Keys.ESCAPE).build().perform();

            } catch (Exception e) {
                e.printStackTrace();
            }

            try {

                WebElement junkFolder = this.getWebDriver().findElement(By.xpath(".//*[@title='Junk Email']"));
                Actions action = new Actions(this.getWebDriver());
                action.moveToElement(junkFolder).perform();
                WebDriverWait wait = new WebDriverWait(this.getWebDriver(), 10);
                wait.until(ExpectedConditions.elementToBeClickable(junkFolder));
                junkFolder.click();

            } catch (Exception e) {
                e.printStackTrace();
                this.getWebDriver().navigate().to("https://outlook.live.com/mail/junkemail");

            }

            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().equals("A")) {
                throw new CustomException("Unable click on junk folder", "123");
            } else {
                throw new CustomException("Unable to move spam folder.", "124");
            }
        }
    }

    private void moveInbox() throws CustomException {
        try {
            try {
                //  this.getWebDriver().manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                int closePopup = 3;
                while (closePopup > 0) {
                    try {
                        int ratting = (int) (7 + (Math.random() * (10 - 7)));
                        String rattingXpath = ".//div[text()='" + ratting + "']";
                        if (this.isElementFound("xpath", rattingXpath, 1)) {
                            System.out.println("Clicked on close button in moveInbxo");
                            this.waitFluent("xpath", rattingXpath).click();
                            if (this.isElementFound("xpath", ".//div[text()='Very likely']", 1)) {
                                this.waitFluent("xpath", ".//div[text()='Very likely']").click();
                            }
                            this.waitFluent("xpath", ".//*[text()='Skip']//ancestor::button[1]").click();
                            this.waitFluent("xpath", ".//*[text()='Close']//ancestor::button[1]").click();
                            Thread.sleep(1000);
                            this.pressEscape();

                        }
                        break;
                    } catch (Exception e) {
                        this.pressEscape();
                        closePopup--;
                        e.printStackTrace();
                    }
                }
                this.waitFluent("xpath", ".//div[@title='Inbox' and @role='treeitem']").click();
                Thread.sleep(1);
                //this.waitFluent("xpath", ".//div[@title='Inbox' and @role='treeitem']").click();
            } catch (Exception ex) {
                this.getWebDriver().navigate().to("https://outlook.live.com/mail/inbox");
                ex.printStackTrace();

            }
        } catch (Exception e) {
            throw new CustomException("Unable to move inbox folder.", "125");
        }
    }

    void pressEscape() {
        Actions action = new Actions(this.getWebDriver());
        int popUp = 0;
        while (popUp++ < 2) {
            try {
                action.sendKeys(Keys.ESCAPE).build().perform();
            } catch (Exception e) {
                System.out.println("Error while pressing escape key");
            }
        }
    }

    private void checkPopupInInbox() {
        try {
            if (this.isElementFound("xpath", ".//div[text()='Continue to Site']/ancestor::button[1]", 1)) {

                //this.getWebDriver().findElement(By.xpath(".//*[text()='Not now']//ancestor::button[1]")).click();
                this.waitFluent("xpath", ".//div[text()='Continue to Site']/ancestor::button[1]").click();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String[] generateRandomWords(int numberOfWords) {
        String[] randomStrings = new String[numberOfWords];
        Random random = new Random();
        for (int i = 0; i < numberOfWords; i++) {
            char[] word = new char[random.nextInt(8) + 3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
            for (int j = 0; j < word.length; j++) {
                word[j] = (char) ('a' + random.nextInt(26));
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
    }

    private void bulkNotJunk() throws Exception {
        try {
            if (this.isElementFound("xpath", ".//div[@aria-label='Select all messages']") && this.getWebDriver().findElements(By.xpath(".//div[@aria-label='Message list']//div[text()='This folder is empty']")).size() == 0) {
                this.waitFluent("xpath", ".//div[@aria-label='Select all messages']").click();
                Thread.sleep(2000);
                if (this.getWebDriver().findElements(By.xpath(".//button[@name='Not junk' and contains(@class,'CommandBarItem-link')]")).size() > 0) {

                    this.waitFluent("xpath", ".//button[@name='Not junk' and contains(@class,'CommandBarItem-link')]").click();

                    this.waitFluent("xpath", ".//button[@name='Not junk' and contains(@class,'ContextualMenu-link') and @role='menuitem']").click();
                } else if (this.isElementFound("xpath", ".//button[@name='Move to' and contains(@class,'ms-CommandBarItem-link')]")) {

                    this.waitFluent("xpath", ".//button[@name='Move to' and contains(@class,'ms-CommandBarItem-link')]").click();

                    this.waitFluent("xpath", ".//div[contains(@class,'ms-ContextualMenu-Callout')]//div[@title='Inbox']").click();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("Unable to not junk in bulk", "128");
        }
    }

    private void actionReply(boolean isRandom) {
        try {
            if (isRandom) {
                if (this.isElementFound("xpath", ".//div[@class='wide-content-host']//button[@name='Reply']", 4)) {
                    final String[] proper_noun = {"I will Get Back To You Later", "Hello I am honored that you reply.And yes I am interested in what's happening at Liferay.News & Offerings provides the latest on company news partnerships and products.Thank you Once Again!!!", "Thank you I am pleased honored and humbled to accept this award and tojoin past recipients who I have long admired and respected. A very special thanks to the STPL Board for selecting me", "Hello I’m honored when someone takes the time to email me after a postBy the way this is a fantastic way to start conversations and grow your network! Because it shows that my post impacted them and whoever they are It’s an honor to learn more about their story.", "I’m honored when someone I look up to as a phenomenal journalist agrees to edit my work as a favor to me. That communicates to me that they see value in my work and that they want to help pave the way in my career. Thank you soo much...", "Thank you. I appreciate it!”You work hard so why the great feedback that comes your way? You deserve it.Plus the best thing about learning to accept compliments is that it’ll give you the chance to see yourself as others Ok See You..."};
                    Random random = new Random();
                    int index = random.nextInt(proper_noun.length);
                    System.out.println("Reply content-->" + proper_noun[index]);
                    JavascriptExecutor jse = (JavascriptExecutor) this.getWebDriver();

                    jse.executeScript("arguments[0].click();", this.getWebDriver().findElement(By.xpath(".//div[@class='wide-content-host']//button[@name='Reply']")));

                    Actions actions = new Actions(this.getWebDriver());
                    if (this.isElementFound("xpath", ".//div[@aria-label='Message body']/div[1]", 35)) {
                        actions.moveToElement(this.getWebDriver().findElement(By.xpath(".//div[@aria-label='Message body']/div[1]")));
                        actions.click();
                        actions.sendKeys(proper_noun[index]);
                        actions.build().perform();
                    }
                    this.waitFluent("xpath", ".//button[contains(@title,'Send')]").click();
                    Thread.sleep(1000);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void actionForward(boolean isForward) {
        try {
            if (isForward) {
                if (this.isElementFound("xpath", ".//div[@class='wide-content-host']//button[@name='Forward']", 6)) {
                    this.waitFluent("xpath", ".//div[@class='wide-content-host']//button[@name='Forward']").click();
                    Thread.sleep(1000);
                    this.waitFluent("xpath", ".//div[@aria-label='Content pane']//input[@role='combobox']").sendKeys(this.getBean().getForward_to());
                    Thread.sleep(1000);
                    Actions actions = new Actions(this.getWebDriver());
                    if (this.isElementFound("xpath", ".//div[@aria-label='Message body']/div[1]", 35)) {
                        actions.moveToElement(this.getWebDriver().findElement(By.xpath(".//div[@aria-label='Message body']/div[1]")));
                        actions.click();
                        actions.sendKeys("FYI");
                        actions.build().perform();
                    }
                    Thread.sleep(1000);
                    this.waitFluent("xpath", ".//button[contains(@title,'Send')]").click();
                    Thread.sleep(2000);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                this.pressEscape();
                if (this.isElementFound("xpath", ".//button[contains(@id,'ok-')]")) {
                    this.waitFluent("xpath", ".//button[contains(@id,'ok-')]").click();
                    Thread.sleep(2000);
                }
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
    }

    private void actionForward() {
        try {
            if (this.isElementFound("xpath", ".//div[@class='wide-content-host']//button[@name='Forward']", 6)) {
                this.waitFluent("xpath", ".//div[@class='wide-content-host']//button[@name='Forward']").click();
                Thread.sleep(1000);
                this.waitFluent("xpath", ".//div[@aria-label='Content pane']//input[@role='combobox']").sendKeys(this.getBean().getForward_to());
                Thread.sleep(1000);
                Actions actions = new Actions(this.getWebDriver());
                if (this.isElementFound("xpath", ".//div[@aria-label='Message body']/div[1]", 35)) {
                    actions.moveToElement(this.getWebDriver().findElement(By.xpath(".//div[@aria-label='Message body']/div[1]")));
                    actions.click();
                    actions.sendKeys("FYI");
                    actions.build().perform();
                }
                Thread.sleep(1000);
                this.waitFluent("xpath", ".//button[contains(@title,'Send')]").click();
                Thread.sleep(2000);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                this.pressEscape();
                if (this.isElementFound("xpath", ".//button[contains(@id,'ok-')]")) {
                    this.waitFluent("xpath", ".//button[contains(@id,'ok-')]").click();
                    Thread.sleep(2000);
                }
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
    }

    private void actionReply() {
        try {
            if (this.isElementFound("xpath", ".//div[@class='wide-content-host']//button[@name='Reply']", 4)) {
                final String[] proper_noun = {"I will Get Back To You Later", "Hello I am honored that you reply.And yes I am interested in what's happening at Liferay.News & Offerings provides the latest on company news partnerships and products.Thank you Once Again!!!", "Thank you I am pleased honored and humbled to accept this award and tojoin past recipients who I have long admired and respected. A very special thanks to the STPL Board for selecting me", "Hello I’m honored when someone takes the time to email me after a postBy the way this is a fantastic way to start conversations and grow your network! Because it shows that my post impacted them and whoever they are It’s an honor to learn more about their story.", "I’m honored when someone I look up to as a phenomenal journalist agrees to edit my work as a favor to me. That communicates to me that they see value in my work and that they want to help pave the way in my career. Thank you soo much...", "Thank you. I appreciate it!”You work hard so why the great feedback that comes your way? You deserve it.Plus the best thing about learning to accept compliments is that it’ll give you the chance to see yourself as others Ok See You..."};
                Random random = new Random();
                int index = random.nextInt(proper_noun.length);
                System.out.println("Reply content-->" + proper_noun[index]);
                JavascriptExecutor jse = (JavascriptExecutor) this.getWebDriver();

                jse.executeScript("arguments[0].click();", this.getWebDriver().findElement(By.xpath(".//div[@class='wide-content-host']//button[@name='Reply']")));

                Actions actions = new Actions(this.getWebDriver());
                if (this.isElementFound("xpath", ".//div[@aria-label='Message body']/div[1]", 35)) {
                    actions.moveToElement(this.getWebDriver().findElement(By.xpath(".//div[@aria-label='Message body']/div[1]")));
                    actions.click();
                    actions.sendKeys(proper_noun[index]);
                    actions.build().perform();
                }
                this.waitFluent("xpath", ".//button[contains(@title,'Send')]").click();
                Thread.sleep(1000);
            }
        } catch (Exception ex) {
            System.out.println("Error inside reply");
            try {
                this.pressEscape();
                if (this.isElementFound("xpath", ".//button[contains(@id,'ok')]")) {
                    this.waitFluent("xpath", ".//button[contains(@id,'ok')]").click();
                }
            } catch (Exception es) {
                es.printStackTrace();
            }

            ex.printStackTrace();
        }
    }

    private void actionFlag(WebElement element) {
        try {
            Actions action3 = new Actions(this.getWebDriver());
            action3.moveToElement(element).build().perform();
            if (this.isElementFound(element, "xpath", ".//button[@title='Flag this message']", 1)) {
                System.out.println("Inside actionFlag");
                element.sendKeys(Keys.INSERT);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void actionFlag(WebElement element, boolean isRandom) {
        try {
            if (isRandom) {
                Actions action3 = new Actions(this.getWebDriver());
                action3.moveToElement(element).build().perform();
                if (this.isElementFound(element, "xpath", ".//button[@title='Flag this message']", 1)) {
                    System.out.println("Inside actionFlag");
                    element.sendKeys(Keys.INSERT);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void actionClick(String parentHandle) {
        try {
            List<WebElement> linkArray = null;
            if (this.isElementFound("xpath", ".//div[@class='wide-content-host']//a[not(contains(@href,'mailto') or contains(text(),'s not ') or contains(text(),'blocked content'))]", 2)) {
                linkArray = this.getWebDriver().findElements(By.xpath(".//div[@class='wide-content-host']//a[not(contains(@href,'mailto') or contains(text(),'s not ') or contains(text(),'blocked content'))]"));
            } else {
                linkArray = null;
                System.out.println("linkarray found empty");
            }

            boolean flagClick = false;
            if (linkArray != null) {
                System.out.println("Calling action loop there");

                for (int i = 0; i < linkArray.size() && i < 2; i++) {
                    System.out.println("in side for loop");
                    WebDriverWait wait = new WebDriverWait(this.getWebDriver(), 3);
                    wait.until(ExpectedConditions.elementToBeClickable(linkArray.get(i)));
                    System.out.println("Link text " + linkArray.get(i).getText());

                    try {
                        linkArray.get(i).click();
                    } catch (Exception e) {
                        e.printStackTrace();
                        ArrayList<String> tabs2 = new ArrayList<String>(this.getWebDriver().getWindowHandles());
                        if (tabs2.size() > 1) {
                            this.getWebDriver().switchTo().window(tabs2.get(1));
                            System.out.println("switched to secnod tab");
                            //System.out.println("Title=" + this.getWebDriver().getTitle());

                            //this.getWebDriver().switchTo().window(this.getWebDriver().getWindowHandle());
                            if (!this.isElementFound("xpath", ".//*[text()='New message']//ancestor::button[1]", 1)) {
                                Thread.sleep(1000);
                                this.getWebDriver().close();
                            }
                        }
                    }

                    Thread.sleep(1000);
                    System.out.println("After click");
                    ArrayList<String> tabs2 = new ArrayList<String>(this.getWebDriver().getWindowHandles());
                    if (tabs2.size() > 1) {
                        this.getWebDriver().switchTo().window(tabs2.get(1));
                        System.out.println("switched to secnod tab");
                        //System.out.println("Title=" + this.getWebDriver().getTitle());

                        //this.getWebDriver().switchTo().window(this.getWebDriver().getWindowHandle());
                        if (!this.isElementFound("xpath", ".//*[text()='New message']//ancestor::button[1]", 1)) {
                            Thread.sleep(1000);
                            this.getWebDriver().close();
                        }
                    }
                    tabs2 = null;
                    this.getWebDriver().switchTo().window(parentHandle); // switch back to the original window
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            this.pressEscape();
        } finally {
            this.getWebDriver().switchTo().window(parentHandle);
        }
    }

    private void actionClick(String parentHandle, boolean isRandom) {

        if (isRandom) {
            System.out.println("Calling action loop here");
            try {
                List<WebElement> linkArray = null;
                if (this.isElementFound("xpath", ".//div[@class='wide-content-host']//a[not(contains(@href,'mailto') or contains(text(),'s not ') or contains(text(),'blocked content'))]", 2)) {
                    linkArray = this.getWebDriver().findElements(By.xpath(".//div[@class='wide-content-host']//a[not(contains(@href,'mailto') or contains(text(),'s not ') or contains(text(),'blocked content'))]"));
                } else {
                    linkArray = null;
                    System.out.println("linkarray found empty");
                }

                boolean flagClick = false;
                if (linkArray != null) {
                    System.out.println("Calling action loop there");

                    for (int i = 0; i < linkArray.size() && i < 2; i++) {
                        System.out.println("in side for loop");
                        WebDriverWait wait = new WebDriverWait(this.getWebDriver(), 3);
                        wait.until(ExpectedConditions.elementToBeClickable(linkArray.get(i)));
                        System.out.println("Link text " + linkArray.get(i).getText());

                        try {
                            linkArray.get(i).click();
                        } catch (Exception e) {
                            e.printStackTrace();
                            ArrayList<String> tabs2 = new ArrayList<String>(this.getWebDriver().getWindowHandles());
                            if (tabs2.size() > 1) {
                                this.getWebDriver().switchTo().window(tabs2.get(1));
                                System.out.println("switched to secnod tab");
                            //System.out.println("Title=" + this.getWebDriver().getTitle());

                                //this.getWebDriver().switchTo().window(this.getWebDriver().getWindowHandle());
                                if (!this.isElementFound("xpath", ".//*[text()='New message']//ancestor::button[1]", 1)) {
                                    Thread.sleep(1000);
                                    this.getWebDriver().close();
                                }
                            }
                        }

                        Thread.sleep(1000);
                        System.out.println("After click");
                        ArrayList<String> tabs2 = new ArrayList<String>(this.getWebDriver().getWindowHandles());
                        if (tabs2.size() > 1) {
                            this.getWebDriver().switchTo().window(tabs2.get(1));
                            System.out.println("switched to secnod tab");
                        //System.out.println("Title=" + this.getWebDriver().getTitle());

                            //this.getWebDriver().switchTo().window(this.getWebDriver().getWindowHandle());
                            if (!this.isElementFound("xpath", ".//*[text()='New message']//ancestor::button[1]", 1)) {
                                Thread.sleep(1000);
                                this.getWebDriver().close();
                            }
                        }
                        tabs2 = null;
                        this.getWebDriver().switchTo().window(parentHandle); // switch back to the original window
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                this.pressEscape();
            } finally {
                this.getWebDriver().switchTo().window(parentHandle);
            }
        }
    }

    private void actionSafeSender() {
        try {
            WebDriverWait wait = new WebDriverWait(this.getWebDriver(), 5);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//div[@class='wide-content-host']//button[@aria-label='More mail actions']")));
            this.getWebDriver().findElement(By.xpath(".//div[@class='wide-content-host']//button[@aria-label='More mail actions']")).click();
            JavascriptExecutor jse = (JavascriptExecutor) this.getWebDriver();
            jse.executeScript("arguments[0].click();", this.getWebDriver().findElement(By.xpath(".//button[@name='Add to Safe senders']")));
            Thread.sleep(1500);
            jse.executeScript("arguments[0].click();", this.getWebDriver().findElement(By.xpath(".//button[contains(@id,'ok-')]")));
        } catch (Exception ex) {
            this.pressEscape();
            ex.printStackTrace();
        }
    }

    private void actionSafeSender(boolean isRandom) {
        try {
            if (isRandom) {
                WebDriverWait wait = new WebDriverWait(this.getWebDriver(), 5);
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//div[@class='wide-content-host']//button[@aria-label='More mail actions']")));
                this.getWebDriver().findElement(By.xpath(".//div[@class='wide-content-host']//button[@aria-label='More mail actions']")).click();
                JavascriptExecutor jse = (JavascriptExecutor) this.getWebDriver();
                jse.executeScript("arguments[0].click();", this.getWebDriver().findElement(By.xpath(".//button[@name='Add to Safe senders']")));
                Thread.sleep(1500);
                jse.executeScript("arguments[0].click();", this.getWebDriver().findElement(By.xpath(".//button[contains(@id,'ok-')]")));
            }
        } catch (Exception ex) {
            this.pressEscape();
            ex.printStackTrace();
        }
    }

    private void actionArchive() {
        try {
            this.waitFluent("xpath", ".//button[@name='Archive']").click();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void actionArchive(boolean isRandom) {
        try {
            if (isRandom) {
                this.waitFluent("xpath", ".//button[@name='Archive']").click();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void actionDelete() {
        try {
            if (this.isElementFound("xpath", ".//button[@title='Delete (Del)']")) {
                this.waitFluent("xpath", ".//button[@title='Delete (Del)']").click();
            } else {
                this.waitFluent("xpath", ".//button[@name='Delete']").click();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void actionDelete(boolean isRandom) {
        try {
            if (isRandom) {
                if (this.isElementFound("xpath", ".//button[@title='Delete (Del)']")) {
                    this.waitFluent("xpath", ".//button[@title='Delete (Del)']").click();
                } else {
                    this.waitFluent("xpath", ".//button[@name='Delete']").click();
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void actionColorCategory(boolean isRandom) {
        try {
            if (isRandom) {
                String[] Category = "Purple|Blue|Green|Yellow|Orange|Red".split("\\|");
                JavascriptExecutor jse = (JavascriptExecutor) this.getWebDriver();
                String cName = Category[new Random().nextInt(Category.length)];
                System.out.println("Moving to " + cName);
                try {
                    this.waitFluent("xpath", ".//div[@aria-label=\"Command toolbar\"]//button[@name=\"Categorize\"]").click();
                } catch (org.openqa.selenium.StaleElementReferenceException ex) {
                    this.waitFluent("xpath", ".//div[@aria-label=\"Command toolbar\"]//button[@name=\"Categorize\"]").click();
                }
                jse.executeScript("arguments[0].click();", this.getWebDriver().findElement(By.xpath(".//div[@title='" + cName + " category' and @role=\"menuitemcheckbox\"]")));
                Thread.sleep(1000);
            }
        } catch (Exception ex) {
            this.pressEscape();
            ex.printStackTrace();
        }
    }

    private void actionColorCategory() {
        try {
            String[] Category = "Purple|Blue|Green|Yellow|Orange|Red".split("\\|");
            JavascriptExecutor jse = (JavascriptExecutor) this.getWebDriver();
            String cName = Category[new Random().nextInt(Category.length)];
            System.out.println("Moving to " + cName);
            try {
                this.waitFluent("xpath", ".//div[@aria-label=\"Command toolbar\"]//button[@name=\"Categorize\"]").click();
            } catch (org.openqa.selenium.StaleElementReferenceException ex) {
                this.waitFluent("xpath", ".//div[@aria-label=\"Command toolbar\"]//button[@name=\"Categorize\"]").click();
            }
            jse.executeScript("arguments[0].click();", this.getWebDriver().findElement(By.xpath(".//div[@title='" + cName + " category' and @role=\"menuitemcheckbox\"]")));
            Thread.sleep(1000);

        } catch (Exception ex) {
            this.pressEscape();
            ex.printStackTrace();
        }
    }

    private void composeMail() throws CustomException {
        try {
            Thread.sleep(3);
            String msgBody = "";
            String tempMessage[] = this.generateRandomWords(new Random().nextInt(15) + 3);
            for (String str : tempMessage) {
                msgBody = msgBody + " " + str;
            }
            System.out.println("In sending mail function");

            String subject = "";
            String tempSubject[] = this.generateRandomWords(new Random().nextInt(6) + 2);
            for (String str1 : tempSubject) {
                subject = subject + " " + str1;
            }

            if (this.isElementFound("xpath", ".//div[text()='New message']//ancestor::button[1]", 5)) {

            }

            this.waitFluent("xpath", ".//div[text()='New message']//ancestor::button[1]").click();

            this.getWebDriver().switchTo().window(this.getWebDriver().getWindowHandle());

            if (this.getBean().getEmailTo() == null || this.getBean().getEmailTo().trim().length() == 0) {
                throw new Exception("A");
            }

            //this.getWebDriver().findElement(By.xpath(".//div[@aria-label='Content pane']//input[@role='combobox']")).sendKeys(this.getEmailBean().getSendEmail());
            Thread.sleep(2000);
            if (this.getWebDriver().findElements(By.xpath(".//div[@aria-label='Content pane']//input[@role='combobox']")).size() == 0) {
                Thread.sleep(2000);
            }

            this.waitFluent("xpath", ".//div[@aria-label='Content pane']//input[@role='combobox']").sendKeys(this.getBean().getEmailTo());
            Thread.sleep(1000);
            this.pressEscape();

            this.waitFluent("xpath", ".//input[@id='subjectLine0']").sendKeys(subject);
            this.getWebDriver().findElement(By.xpath(".//input[@id='subjectLine0']")).sendKeys(Keys.TAB + msgBody);

            //this.getWebDriver().findElement(By.xpath(".//div[@class='_3mm2ZCQ8vuTshV2Aax5jnA']")).sendKeys(msgBody);
            //this.getWebDriver().findElement(By.xpath(".//div[@id='uniqName_4_30' and contains(@class,'sendButton')]")).click();
            Thread.sleep(1000);
            this.getWebDriver().findElement(By.xpath(".//button[@name='Send']")).click();
            Thread.sleep(3000);
            if (this.getWebDriver().findElements(By.xpath(".//div[contains(text(),'t send the following message')]")).size() > 0) {
                throw new Exception("B");
            } else if (this.isElementFound("xpath", ".//iframe[contains(@src,\"owa/owahipcontrol.aspx?compose=1\")]", 5)) {
                throw new Exception("B");
            } else if (this.isElementFound("xpath", ".//iframe[@id=\"fc-iframe-wrap\"]", 5)) {
                throw new Exception("B");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().equals("A")) {
                throw new CustomException("Email to field is empty", "145");
            } else if (e.getMessage().equals("B")) {
                throw new CustomException("Asking for verification", "146");
            } else {
                throw new CustomException("Error in compose mail", "147");
            }
        }

    }

    private void addContact() {
        try {
            if (this.isElementFound("xpath", ".//div[contains(@class,'item-header-actions')]//span[@aria-haspopup='dialog']")) {
                this.waitFluent("xpath", ".//div[contains(@class,'item-header-actions')]//span[@aria-haspopup='dialog']").click();
                this.waitFluent("xpath", ".//button[@title='More options']").click();
                if (this.isElementFound("xpath", ".//button[@title='More options']", 3)) {
                    if (this.isElementFound("xpath", ".//button[@name='Edit contact']", 2)) {
                        this.pressEscape();
                        this.pressEscape();
                    } else if (this.isElementFound("xpath", ".//button[@name='Add to contacts']", 2)) {
                        this.waitFluent("xpath", ".//button[@name='Add to contacts']").click();
                        this.waitFluent("xpath", ".//div[text()='Create']/ancestor::button[1]").click();
                    }
                }
            }
        } catch (Exception ex) {
            this.pressEscape();
            ex.printStackTrace();
        }
    }

    private int processMailsRandom(boolean moveInbox, boolean readMessage, String mailboxType) throws CustomException {
        int messageCount = 0;
        int noMailsToProcessInbox = this.getBean().getInboxCount();
        int noMailsToProcessJunk = this.getBean().getNotJunkCount();
        int noMailsToMarkFlag = (int) (3 + (Math.random() * (7 - 3)));
        int noMailsToArchiveOrDelete = (int) (3 + (Math.random() * (5 - 3)));
        //int noMailsToReply = (int) (1 + (Math.random() * (3 - 1)));
        int noMailsToReply = 0;
        int noMailsToColorCategory = (int) (1 + (Math.random() * (3 - 1)));
        //int noMailsToForward = (int) (1 + (Math.random() * (3 - 1)));
        int noMailsToForward = 0;
        boolean clickHyperLink = false;
        boolean isDelete = false;
        boolean isFlag = false;
        boolean isArchive = false;
        boolean isReply = false;
        boolean addSafeSender = false;
        boolean isAddContact = false;
        boolean isColorCategory = false;
        boolean isForwardMail = false;
        Random randomAction = new Random();

        switch (2) {
            case 0:
                clickHyperLink = false;
                break;
            case 1:
                clickHyperLink = true;
                break;
            case 2:
                clickHyperLink = randomAction.nextBoolean();
        }

        switch (Integer.parseInt(this.getBean().getActReplyTo().trim())) {
            case 0:
                isReply = false;
                break;
            case 1:
                isReply = true;
                break;
            case 2:
                isReply = randomAction.nextBoolean();
        }

        switch (Integer.parseInt(this.getBean().getActForwardMail().trim())) {
            case 0:
                isForwardMail = false;
                break;
            case 1:
                isForwardMail = true;
                break;
            case 2:
                isForwardMail = randomAction.nextBoolean();
        }

        switch (2) {
            case 0:
                isColorCategory = false;
                break;
            case 1:
                isColorCategory = true;
                break;
            case 2:
                isColorCategory = randomAction.nextBoolean();
        }

        switch (2) {
            case 0:
                addSafeSender = false;
                break;
            case 1:
                addSafeSender = true;
                break;
            case 2:
                addSafeSender = randomAction.nextBoolean();
        }

        switch (2) {
            case 0:
                isFlag = false;
                break;
            case 1:
                isFlag = true;
                break;
            case 2:
                isFlag = randomAction.nextBoolean();
        }

        switch (Integer.parseInt(this.getBean().getActAddContact().trim())) {
            case 0:
                isAddContact = false;
                break;
            case 1:
                isAddContact = true;
                break;
            case 2:
                isAddContact = randomAction.nextBoolean();
        }

        switch (1) {
            case 0:
                isDelete = false;
                isArchive = false;
                break;
            case 1:
                isDelete = false;
                isArchive = true;
                break;
            case 2:
                isDelete = true;
                isArchive = false;
                break;
            case 3:
                if (randomAction.nextBoolean()) {
                    isDelete = true;
                    isArchive = false;
                } else {
                    isArchive = true;
                    isDelete = false;
                }
                break;
        }

        Random randomActions = new Random();
        boolean res = randomActions.nextBoolean();
        System.out.println(res);
        this.getWebDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        try {
            String messageSubject = this.getBean().getSubject().toLowerCase().trim();
            String messageFrom = this.getBean().getFromName().toLowerCase().trim();
            String parentHandle = this.getWebDriver().getWindowHandle();
            List<WebElement> subjectLines = null;
            boolean isMessageChecked = false;

            if (mailboxType.toLowerCase().equals("spam")) {
                subjectLines = this.getWebDriver().findElements(By.xpath(".//div[@aria-label='Message list']//div[@draggable='true']/div/div/div[2]/div[2]/div/span"));
            }

            if (mailboxType.toLowerCase().equals("inbox")) {
                subjectLines = this.getWebDriver().findElements(By.xpath(".//div[@aria-label='Message list']//div[@draggable='true' and not(@class)]/parent::div[1]"));
            }
            parentHandle = this.getWebDriver().getWindowHandle(); //get the current window handle
            System.out.println("\n Message In  in new" + mailboxType + " : " + subjectLines.size());
            messageCount = subjectLines.size();
            for (int j = 0, k = 0; j < subjectLines.size(); j++) {

                if (mailboxType.toLowerCase().equals("inbox") && noMailsToProcessInbox < 1) {
                    break;
                }

                if (mailboxType.toLowerCase().equals("spam") && noMailsToProcessJunk < 1) {
                    break;
                }
                System.out.println("mailboxType=" + mailboxType + " <--> J=" + j + " --->  totalMessageCounter=" + this.totalMessageCounter);

                String subjectText = "";

                String fromName = "";

                try {
                    if (!(subjectLines.get(j).isEnabled() && subjectLines.get(j).isDisplayed())) {
                    }
                } catch (org.openqa.selenium.StaleElementReferenceException ex) {
                    ex.printStackTrace();
                    Thread.sleep(400);
                }

                if (mailboxType.toLowerCase().equals("inbox")) {
                    try {
                        Actions action3 = new Actions(this.getWebDriver());
                        action3.moveToElement(subjectLines.get(j)).build().perform();
                        subjectText = subjectLines.get(j).findElement(By.xpath("div/div/div/div[2]/div[2]/div/span")).getText().toLowerCase();
                        fromName = subjectLines.get(j).findElement(By.xpath("div/div/div/div[2]/div[1]/div/span")).getText().toLowerCase();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        System.out.println("Error detected");
                        continue;
                    }
                } else {
                    subjectText = subjectLines.get(j).getText().toString().toLowerCase();
                    fromName = subjectLines.get(j).findElement(By.xpath("parent::div/parent::div/parent::div/div[1]//span")).getText();
                }
                if (((subjectText.contains(messageSubject) || fromName.contains(messageSubject) || messageSubject.trim().equalsIgnoreCase("NA")) && mailboxType.toLowerCase().equals("inbox")) || ((subjectText.contains(messageFrom) || fromName.contains(messageFrom) || messageFrom.trim().equalsIgnoreCase("NA")) && mailboxType.toLowerCase().equals("spam"))) {

                    if (subjectText.contains("microsoft") || subjectText.contains("outlook") || subjectText.contains("windows") || fromName.contains("outlook") || fromName.contains("microsoft")) {
                        continue;
                    }
                    if (moveInbox) {
                        if (moveInbox && this.totalMessageCounter == 0) {
                            this.setMessageFound(false);
                        } else {
                            this.setMessageFound(true);
                        }

                        this.totalMessageCounter++;
                        Actions action = new Actions(this.getWebDriver());
                        action.moveToElement(subjectLines.get(j)).click(subjectLines.get(j).findElement(By.xpath("parent::div/parent::div/parent::div/parent::div/div[1]/div"))).build().perform();
                        isMessageChecked = true;
                    }
                    if (readMessage) {
                        System.out.println("Found Subject II: J=" + j + "--->" + this.totalMessageCounter);

                        int tryClickInbox = 3;
                        while (tryClickInbox-- > 0) {
                            try {
                                subjectLines.get(j).click();
                                break;
                            } catch (Exception ex) {
                                this.pressEscape();
                                ex.printStackTrace();
                                JavascriptExecutor js = (JavascriptExecutor) this.getWebDriver();
                                js.executeScript("arguments[0].scrollIntoView();", subjectLines.get(j));
                            }
                        }
                        this.setMessageFound(true);
                        if (isAddContact) {
                            this.addContact();
                        }

                        subjectLines.get(j).sendKeys(Keys.chord(Keys.CONTROL, "q"));
                        System.out.println("Marked as read");

                        if (noMailsToMarkFlag > 0 && randomAction.nextBoolean()) {
                            this.actionFlag(subjectLines.get(j), randomAction.nextBoolean());
                            noMailsToMarkFlag--;
                        }

                        if (randomAction.nextBoolean()) {
                            this.actionSafeSender(randomAction.nextBoolean());
                        }

                        if (randomAction.nextBoolean()) {
                            this.actionClick(parentHandle);
                        }

                        if (randomAction.nextBoolean() && noMailsToColorCategory > 0) {
                            this.actionColorCategory(randomAction.nextBoolean());
                            noMailsToColorCategory--;
                        }

                        if (randomAction.nextBoolean() && noMailsToForward > 0) {
                            this.actionForward(randomAction.nextBoolean());
                            noMailsToForward--;
                        }

                        if (isReply && noMailsToReply > 0 && false) {
                            this.actionReply(randomAction.nextBoolean());
                            noMailsToReply--;
                        } else {
                            isReply = false;
                        }

                        if (isDelete && mailboxType.toLowerCase().equals("inbox") && noMailsToArchiveOrDelete > 0 && !isFlag && !isReply) {
                            this.actionDelete(randomAction.nextBoolean());
                            noMailsToArchiveOrDelete--;
                        } else {
                            isDelete = false;
                        }

                        if (isArchive && mailboxType.toLowerCase().equals("inbox") && noMailsToArchiveOrDelete > 0 && !isReply) {
                            this.actionArchive(randomAction.nextBoolean());
                            noMailsToArchiveOrDelete--;
                        } else {
                            isArchive = false;
                        }
                        System.out.println("noMailsToProcessInbox=" + noMailsToProcessInbox);

                    }
                } //subject

            }

            if (isMessageChecked && moveInbox) {
                System.out.println("Move Inbox Clicked !!!");
                if (this.getWebDriver().findElements(By.xpath(".//button[@name='Not junk' and contains(@class,'CommandBarItem-link')]")).size() > 0) {

                    this.waitFluent("xpath", ".//button[@name='Not junk' and contains(@class,'CommandBarItem-link')]").click();

                    this.waitFluent("xpath", ".//button[@name='Not junk' and contains(@class,'ContextualMenu-link') and @role='menuitem']").click();
                } else {

                    this.waitFluent("xpath", ".//button[@name='Move to' and contains(@class,'ms-CommandBarItem-link')]").click();

                    this.waitFluent("xpath", ".//div[contains(@class,'ms-ContextualMenu-Callout')]//div[@title='Inbox']").click();
                }
            }
            this.getWebDriver().manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("Error while processing in --" + mailboxType, "127");
        }
        return messageCount;
    }

    private int processMailsCustomReportNotSpam(boolean moveInbox, boolean readMessage, String mailboxType) throws CustomException {
        int messageCount = 0;
        int noMailsToProcessInbox = this.getBean().getInboxCount();
        int noMailsToProcessJunk = this.getBean().getNotJunkCount();
        int noMailsToMarkFlag = this.getBean().getFlagCount();
        int noMailsToArchiveOrDelete = this.getBean().getDeleteCount();
        int noMailsToReply = this.getBean().getReplyCount();
        int noMailsToReportNotSpam = (int) (1 + (Math.random() * (5 - 1)));
        int noMailsToColorCategory = this.getBean().getCc_count();
        int noMailsToSafeSender = this.getBean().getSs_count();
        int noMailsToForward = this.getBean().getForwardCount();
        boolean clickHyperLink = false;
        boolean isDelete = false;
        boolean isFlag = false;
        boolean isArchive = false;
        boolean isReply = false;
        boolean addSafeSender = false;
        boolean isAddContact = false;
        boolean isColorCategory = false;
        boolean isForwardMail = false;
        Random randomAction = new Random();

        switch (Integer.parseInt(this.getBean().getActClickLink().trim())) {
            case 0:
                clickHyperLink = false;
                break;
            case 1:
                clickHyperLink = true;
                break;
            case 2:
                clickHyperLink = randomAction.nextBoolean();
        }

        switch (Integer.parseInt(this.getBean().getActColorCategory().trim())) {
            case 0:
                isColorCategory = false;
                break;
            case 1:
                isColorCategory = true;
                break;
            case 2:
                isColorCategory = randomAction.nextBoolean();
        }

        switch (Integer.parseInt(this.getBean().getActReplyTo().trim())) {
            case 0:
                isReply = false;
                break;
            case 1:
                isReply = true;
                break;
            case 2:
                isReply = randomAction.nextBoolean();
        }

        switch (Integer.parseInt(this.getBean().getActForwardMail().trim())) {
            case 0:
                isForwardMail = false;
                break;
            case 1:
                isForwardMail = true;
                break;
            case 2:
                isForwardMail = randomAction.nextBoolean();
        }

        switch (Integer.parseInt(this.getBean().getActColorCategory().trim())) {
            case 0:
                isColorCategory = false;
                break;
            case 1:
                isColorCategory = true;
                break;
            case 2:
                isColorCategory = randomAction.nextBoolean();
        }

        switch (Integer.parseInt(this.getBean().getActSafeSender().trim())) {
            case 0:
                addSafeSender = false;
                break;
            case 1:
                addSafeSender = true;
                break;
            case 2:
                addSafeSender = randomAction.nextBoolean();
        }

        switch (Integer.parseInt(this.getBean().getActMarkFlag().trim())) {
            case 0:
                isFlag = false;
                break;
            case 1:
                isFlag = true;
                break;
            case 2:
                isFlag = randomAction.nextBoolean();
        }

        switch (Integer.parseInt(this.getBean().getActAddContact().trim())) {
            case 0:
                isAddContact = false;
                break;
            case 1:
                isAddContact = true;
                break;
            case 2:
                isAddContact = randomAction.nextBoolean();
        }

        switch (Integer.parseInt(this.getBean().getActArchiveDelete().trim())) {
            case 0:
                isDelete = false;
                isArchive = false;
                break;
            case 1:
                isDelete = false;
                isArchive = true;
                break;
            case 2:
                isDelete = true;
                isArchive = false;
                break;
            case 3:
                if (randomAction.nextBoolean()) {
                    isDelete = true;
                    isArchive = false;
                } else {
                    isArchive = true;
                    isDelete = false;
                }
                break;
        }

        Random randomActions = new Random();
        boolean res = randomActions.nextBoolean();
        System.out.println(res);
        this.getWebDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        try {
            String messageSubject = this.getBean().getSubject().toLowerCase().trim();
            String messageFrom = this.getBean().getFromName().toLowerCase().trim();
            String parentHandle = this.getWebDriver().getWindowHandle();
            List<WebElement> subjectLines = null;
            boolean isMessageChecked = false;

            if (mailboxType.toLowerCase().equals("spam")) {
                //subjectLines = this.getWebDriver().findElements(By.xpath(".//div[@aria-label='Message list']//div[@draggable='true']/div/div/div[2]/div[2]/div/span"));
                subjectLines = this.getWebDriver().findElements(By.xpath(".//div[@aria-label='Message list']//div[@draggable='true' and not(@class)]/parent::div[1]"));
            }

            if (mailboxType.toLowerCase().equals("inbox")) {
                subjectLines = this.getWebDriver().findElements(By.xpath(".//div[@aria-label='Message list']//div[@draggable='true' and not(@class)]/parent::div[1]"));
            }
            parentHandle = this.getWebDriver().getWindowHandle(); //get the current window handle
            System.out.println("\n Message In  in new" + mailboxType + " : " + subjectLines.size());
            messageCount = subjectLines.size();
            for (int j = 0, k = 0; j < subjectLines.size(); j++) {

                if (mailboxType.toLowerCase().equals("inbox") && noMailsToProcessInbox < 1) {
                    break;
                } else {
                    System.out.println("Number of mail to process in inbox : " + noMailsToProcessInbox);
                }

                if (mailboxType.toLowerCase().equals("spam") && noMailsToProcessJunk < 1) {
                    break;
                } else {
                    System.out.println("Number of mail to process in spam : " + noMailsToProcessJunk);
                }

                //System.out.println("Report_not_spam=" + this.getBean().getReport_not_spam());
                if (this.getBean().getReport_not_spam().equals("2") && noMailsToReportNotSpam < 1) {
                    break;
                } else {
                    System.out.println("Number of mail to process in Report Notspam : " + noMailsToReportNotSpam);
                }

                //System.out.println("mailboxType=" + mailboxType + " <--> J=" + j + " --->  totalMessageCounter=" + this.totalMessageCounter);
                String subjectText = "";

                String fromName = "";

                try {
                    if (!(subjectLines.get(j).isEnabled() && subjectLines.get(j).isDisplayed())) {
                    }
                } catch (org.openqa.selenium.StaleElementReferenceException ex) {
                    ex.printStackTrace();
                    Thread.sleep(400);
                }

                if (mailboxType.toLowerCase().equals("inbox") || mailboxType.toLowerCase().equals("spam")) {
                    try {
                        Actions action3 = new Actions(this.getWebDriver());
                        action3.moveToElement(subjectLines.get(j)).build().perform();
                        subjectText = subjectLines.get(j).findElement(By.xpath("div/div/div/div[2]/div[2]/div/span")).getText().toLowerCase();
                        fromName = subjectLines.get(j).findElement(By.xpath("div/div/div/div[2]/div[1]/div/span")).getText().toLowerCase();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        System.out.println("Error detected");
                        continue;
                    }
                }

                if (mailboxType.toLowerCase().equals("inbox")) {
                    noMailsToProcessInbox--;
                }
                if (mailboxType.toLowerCase().equals("spam")) {
                    noMailsToProcessJunk--;
                }
                System.out.println(mailboxType + "  J=  " + j);
                System.out.println(subjectText + " ****** " + messageSubject);

                if (((subjectText.contains(messageSubject) || fromName.contains(messageSubject) || messageSubject.trim().equalsIgnoreCase("NA")) && mailboxType.toLowerCase().equals("inbox")) || ((subjectText.contains(messageFrom) || fromName.contains(messageFrom) || messageFrom.trim().equalsIgnoreCase("NA")) && mailboxType.toLowerCase().equals("spam"))) {
                    //System.out.println("Found Subject I:" + subjectText);
                    //System.out.println("Move Inbox : " + moveInbox);

                    if (subjectText.contains("microsoft account") || subjectText.contains("outlook") || subjectText.contains("windows") || fromName.contains("outlook")) {
                        continue;
                    }
                    if (moveInbox) {
                        if (moveInbox && this.totalMessageCounter == 0) {
                            this.setMessageFound(false);
                        } else {
                            this.setMessageFound(true);
                        }

                        this.totalMessageCounter++;
                        //Actions action = new Actions(this.getWebDriver());
                        //action.moveToElement(subjectLines.get(j)).click(subjectLines.get(j).findElement(By.xpath("parent::div/parent::div/parent::div/parent::div/div[1]/div"))).build().perform();
                        isMessageChecked = true;

                    }
                    if (readMessage) {

                        //System.out.println("Found Subject II: J=" + j + "--->" + this.totalMessageCounter);
                        subjectLines.get(j).click();
                        System.out.println("EE");
                        this.setMessageFound(true);
                        try {
                            subjectLines.get(j).sendKeys(Keys.chord(Keys.CONTROL, "q"));
                            System.out.println("Marked as read");
                        } catch (Exception ex) {
                            System.out.println("Unable to Mark as read");
                            ex.printStackTrace();
                        }
                        if (mailboxType.toLowerCase().equals("spam")) {
                            System.out.println("FF");
                            try {
                                if (this.isElementFound("xpath", ".//a[text()='Show blocked content']", 1)) {
                                    //this.waitFluent("xpath", ".//a[text()='Show blocked content']").click();
                                    this.getWebDriver().findElement(By.xpath(".//a[text()='Show blocked content']")).click();
                                    System.out.println("Show blocked content clicked");
                                }
                            } catch (Exception ex) {
                                System.out.println("Unable to show blocked content");
                                ex.printStackTrace();
                            }
                        }
                        System.out.println("AA");
                        if (mailboxType.toLowerCase().equals("inbox")) {
                            if (isFlag && noMailsToMarkFlag > 0) {
                                if (this.getBean().getActMarkFlag().trim().equals("2")) {
                                    this.actionFlag(subjectLines.get(j), randomAction.nextBoolean());
                                } else {
                                    this.actionFlag(subjectLines.get(j));
                                }
                                noMailsToMarkFlag--;
                            } else {
                                isFlag = false;
                            }

                            if (isColorCategory && noMailsToColorCategory > 0) {
                                if (this.getBean().getActColorCategory().trim().equals("0")) {
                                    isColorCategory = false;
                                } else if (this.getBean().getActColorCategory().trim().equals("2")) {
                                    this.actionColorCategory(randomAction.nextBoolean());
                                    noMailsToColorCategory--;
                                } else {
                                    this.actionColorCategory();
                                    noMailsToColorCategory--;
                                }
                            }

                            if (clickHyperLink) {
                                Thread.sleep(1500);
//                            try {
//                                this.getWebDriver().manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
//                            } catch (Exception ex) {
//                                ex.printStackTrace();
//                            }
                                if (this.getBean().getActMarkFlag().trim().equals("2")) {
                                    this.actionClick(parentHandle, randomAction.nextBoolean());
                                } else {
                                    this.actionClick(parentHandle);
                                }
                            }

                            if (addSafeSender && noMailsToSafeSender > 0) {
                                if (this.getBean().getActSafeSender().trim().equals("2")) {
                                    this.actionSafeSender(randomAction.nextBoolean());
                                    noMailsToSafeSender--;
                                } else {
                                    this.actionSafeSender();
                                    noMailsToSafeSender--;
                                }
                            }

                            if (isForwardMail && noMailsToForward > 0) {
                                if (this.getBean().getActForwardMail().trim().equals("2")) {
                                    this.actionForward(randomAction.nextBoolean());
                                } else {
                                    this.actionForward();
                                }
                                noMailsToForward--;
                            }

                            if (isReply && noMailsToReply > 0 && false) {
                                if (this.getBean().getActReplyTo().trim().equals("2")) {
                                    this.actionReply(randomAction.nextBoolean());
                                } else {
                                    this.actionReply();
                                }
                                noMailsToReply--;
                            } else {
                                isReply = false;
                            }

                            if (isDelete && mailboxType.toLowerCase().equals("inbox") && noMailsToArchiveOrDelete > 0 && !isFlag && !isReply) {
                                this.actionDelete();
                                noMailsToArchiveOrDelete--;
                            } else {
                                isDelete = false;
                            }

                            if (isArchive && mailboxType.toLowerCase().equals("inbox") && noMailsToArchiveOrDelete > 0 && !isReply) {
                                this.actionArchive();
                                noMailsToArchiveOrDelete--;
                            } else {
                                isArchive = false;
                            }
                        }
                        System.out.println("BB");
                    }
                    if (mailboxType.toLowerCase().equals("spam") && (this.getBean().getReport_not_spam().equals("2") || this.getBean().getReport_not_spam().equals("1")) && moveInbox) {
                        if (this.getBean().getReport_not_spam().equals("2")) {
                            noMailsToReportNotSpam--;
                        }
                        System.out.println("CC");

                        if ((this.getBean().getReport_not_spam().equals("1") || this.getBean().getReport_not_spam().equals("2")) && isMessageChecked) {
                            if (this.isElementFound("xpath", ".//a[contains(text(),'s not ')]", 1)) {
                                try {
                                    //this.waitFluent("xpath", ".//a[contains(text(),'s not ')]").click();
                                    this.getWebDriver().findElement(By.xpath(".//a[contains(text(),'s not ')]")).click();
                                } catch (Exception ex) {
                                    ex.printStackTrace();

                                }
                            } else {
                                if (this.getWebDriver().findElements(By.xpath(".//button[@name='Not junk' and contains(@class,'CommandBarItem-link')]")).size() > 0) {
                                    System.out.println("In side If not spam");
                                    if (this.getWebDriver().getWindowHandles().size() > 1) {
                                        System.out.println("Switched to parent window handle");
                                        this.getWebDriver().switchTo().window(parentHandle);
                                    }
                                    this.waitFluent("xpath", ".//button[@name='Not junk' and contains(@class,'CommandBarItem-link')]").click();

                                    this.waitFluent("xpath", ".//button[@name='Not junk' and contains(@class,'ContextualMenu-link') and @role='menuitem']").click();
                                } else {
                                    System.out.println("In side Else not spam");
                                    this.waitFluent("xpath", ".//button[@name='Move to' and contains(@class,'ms-CommandBarItem-link')]").click();

                                    this.waitFluent("xpath", ".//div[contains(@class,'ms-ContextualMenu-Callout')]//div[@title='Inbox']").click();
                                }
                            }

                        }
                    } else if (isMessageChecked && moveInbox) {
                        System.out.println("Move Inbox Clicked !!!");
                        if (this.getWebDriver().findElements(By.xpath(".//button[@name='Not junk' and contains(@class,'CommandBarItem-link')]")).size() > 0) {

                            this.waitFluent("xpath", ".//button[@name='Not junk' and contains(@class,'CommandBarItem-link')]").click();

                            this.waitFluent("xpath", ".//button[@name='Not junk' and contains(@class,'ContextualMenu-link') and @role='menuitem']").click();
                        }
                    }
                } //subject

                /* if(mailboxType.equals("inbox") && this.totalMessageCounter == 0) {
                
                 break;
                 }*/
            }

            //this.getWebDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("Error while processing in ! " + mailboxType, "127");
        }
        return messageCount;
    }

    private int processMailsCustom(boolean moveInbox, boolean readMessage, String mailboxType) throws CustomException {
        int messageCount = 0;
        int noMailsToProcessInbox = this.getBean().getInboxCount();
        int noMailsToProcessJunk = this.getBean().getNotJunkCount();
        int noMailsToMarkFlag = this.getBean().getFlagCount();
        int noMailsToArchiveOrDelete = this.getBean().getDeleteCount();
        int noMailsToReply = this.getBean().getReplyCount();
        int noMailsToColorCategory = this.getBean().getCc_count();
        int noMailsToForward = this.getBean().getForwardCount();
        boolean clickHyperLink = false;
        boolean isDelete = false;
        boolean isFlag = false;
        boolean isArchive = false;
        boolean isReply = false;
        boolean addSafeSender = false;
        boolean isAddContact = false;
        boolean isColorCategory = false;

        boolean isForwardMail = false;

        Random randomAction = new Random();

        switch (Integer.parseInt(this.getBean().getActClickLink().trim())) {
            case 0:
                clickHyperLink = false;
                break;
            case 1:
                clickHyperLink = true;
                break;
            case 2:
                clickHyperLink = randomAction.nextBoolean();
        }

        switch (Integer.parseInt(this.getBean().getActColorCategory().trim())) {
            case 0:
                isColorCategory = false;
                break;
            case 1:
                isColorCategory = true;
                break;
            case 2:
                isColorCategory = randomAction.nextBoolean();
        }

        switch (Integer.parseInt(this.getBean().getActReplyTo().trim())) {
            case 0:
                isReply = false;
                break;
            case 1:
                isReply = true;
                break;
            case 2:
                isReply = randomAction.nextBoolean();
        }

        switch (Integer.parseInt(this.getBean().getActColorCategory().trim())) {
            case 0:
                isColorCategory = false;
                break;
            case 1:
                isColorCategory = true;
                break;
            case 2:
                isColorCategory = randomAction.nextBoolean();
        }

        switch (Integer.parseInt(this.getBean().getActSafeSender().trim())) {
            case 0:
                addSafeSender = false;
                break;
            case 1:
                addSafeSender = true;
                break;
            case 2:
                addSafeSender = randomAction.nextBoolean();
        }

        switch (Integer.parseInt(this.getBean().getActMarkFlag().trim())) {
            case 0:
                isFlag = false;
                break;
            case 1:
                isFlag = true;
                break;
            case 2:
                isFlag = randomAction.nextBoolean();
        }

        switch (Integer.parseInt(this.getBean().getActAddContact().trim())) {
            case 0:
                isAddContact = false;
                break;
            case 1:
                isAddContact = true;
                break;
            case 2:
                isAddContact = randomAction.nextBoolean();
        }

        switch (Integer.parseInt(this.getBean().getActArchiveDelete().trim())) {
            case 0:
                isDelete = false;
                isArchive = false;
                break;
            case 1:
                isDelete = false;
                isArchive = true;
                break;
            case 2:
                isDelete = true;
                isArchive = false;
                break;
            case 3:
                if (randomAction.nextBoolean()) {
                    isDelete = true;
                    isArchive = false;
                } else {
                    isArchive = true;
                    isDelete = false;
                }
                break;
        }

        switch (Integer.parseInt(this.getBean().getActForwardMail().trim())) {
            case 0:
                isForwardMail = false;
                break;
            case 1:
                isForwardMail = true;
                break;
            case 2:
                isForwardMail = randomAction.nextBoolean();
        }

        Random randomActions = new Random();
        boolean res = randomActions.nextBoolean();
        System.out.println(res);
        this.getWebDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        try {
            String messageSubject = this.getBean().getSubject().toLowerCase().trim();
            String messageFrom = this.getBean().getFromName().toLowerCase().trim();
            String parentHandle = this.getWebDriver().getWindowHandle();
            List<WebElement> subjectLines = null;
            boolean isMessageChecked = false;

            if (mailboxType.toLowerCase().equals("spam")) {
                subjectLines = this.getWebDriver().findElements(By.xpath(".//div[@aria-label='Message list']//div[@draggable='true']/div/div/div[2]/div[2]/div/span"));
            }

            if (mailboxType.toLowerCase().equals("inbox")) {
                subjectLines = this.getWebDriver().findElements(By.xpath(".//div[@aria-label='Message list']//div[@draggable='true' and not(@class)]/parent::div[1]"));
            }
            parentHandle = this.getWebDriver().getWindowHandle(); //get the current window handle
            System.out.println("\n Message In  in new" + mailboxType + " : " + subjectLines.size());
            messageCount = subjectLines.size();
            for (int j = 0, k = 0; j < subjectLines.size(); j++) {

                if (mailboxType.toLowerCase().equals("inbox") && noMailsToProcessInbox < 1) {
                    break;
                }

                if (mailboxType.toLowerCase().equals("spam") && noMailsToProcessJunk < 1) {
                    break;
                }

                if (mailboxType.toLowerCase().equals("inbox")) {
                    noMailsToProcessInbox--;
                }
                if (mailboxType.toLowerCase().equals("spam")) {
                    noMailsToProcessJunk--;
                }

                System.out.println("mailboxType=" + mailboxType + " <--> J=" + j + " --->  totalMessageCounter=" + this.totalMessageCounter);

                String subjectText = "";

                String fromName = "";

                try {
                    if (!(subjectLines.get(j).isEnabled() && subjectLines.get(j).isDisplayed())) {
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Thread.sleep(400);
                }

                if (mailboxType.toLowerCase().equals("inbox")) {
                    try {
                        Actions action3 = new Actions(this.getWebDriver());
                        action3.moveToElement(subjectLines.get(j)).build().perform();
                        subjectText = subjectLines.get(j).findElement(By.xpath("div/div/div/div[2]/div[2]/div/span")).getText().toLowerCase();
                        fromName = subjectLines.get(j).findElement(By.xpath("div/div/div/div[2]/div[1]/div/span")).getText().toLowerCase();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        System.out.println("Error detected");
                        continue;
                    }
                } else {
                    subjectText = subjectLines.get(j).getText().toString().toLowerCase();
                    fromName = subjectLines.get(j).findElement(By.xpath("parent::div/parent::div/parent::div/div[1]//span")).getText();
                }
                System.out.println(mailboxType + "  J=  " + j);
                System.out.println(subjectText + " ****** " + messageSubject);
                if (((subjectText.contains(messageSubject) || fromName.contains(messageSubject) || messageSubject.trim().equalsIgnoreCase("NA")) && mailboxType.toLowerCase().equals("inbox")) || ((subjectText.contains(messageFrom) || fromName.contains(messageFrom) || messageFrom.trim().equalsIgnoreCase("NA")) && mailboxType.toLowerCase().equals("spam"))) {

                    if (subjectText.contains("microsoft account") || subjectText.contains("outlook") || subjectText.contains("windows")) {
                        continue;
                    }
                    if (moveInbox) {
                        if (moveInbox && this.totalMessageCounter == 0) {
                            this.setMessageFound(false);
                        } else {
                            this.setMessageFound(true);
                        }

                        this.totalMessageCounter++;
                        Actions action = new Actions(this.getWebDriver());
                        action.moveToElement(subjectLines.get(j)).click(subjectLines.get(j).findElement(By.xpath("parent::div/parent::div/parent::div/parent::div/div[1]/div"))).build().perform();
                        isMessageChecked = true;
                    }
                    if (readMessage) {
                        System.out.println("Found Subject II: J=" + j + "--->" + this.totalMessageCounter);

                        int tryClickInbox = 3;
                        while (tryClickInbox-- > 0) {
                            try {
                                subjectLines.get(j).click();
                                break;
                            } catch (Exception ex) {
                                this.pressEscape();
                                ex.printStackTrace();
                                JavascriptExecutor js = (JavascriptExecutor) this.getWebDriver();
                                js.executeScript("arguments[0].scrollIntoView();", subjectLines.get(j));
                            }
                        }
                        this.setMessageFound(true);

                        subjectLines.get(j).sendKeys(Keys.chord(Keys.CONTROL, "q"));
                        System.out.println("Marked as read");

                        if (isFlag && noMailsToMarkFlag > 0) {
                            if (this.getBean().getActMarkFlag().trim().equals("2")) {
                                this.actionFlag(subjectLines.get(j), randomAction.nextBoolean());
                            } else {
                                this.actionFlag(subjectLines.get(j));
                            }
                            noMailsToMarkFlag--;
                        } else {
                            isFlag = false;
                        }

                        if (isColorCategory && noMailsToColorCategory > 0) {
                            if (this.getBean().getActColorCategory().trim().equals("0")) {
                                isColorCategory = false;
                            } else if (this.getBean().getActColorCategory().trim().equals("2")) {
                                this.actionColorCategory(randomAction.nextBoolean());
                                noMailsToColorCategory--;
                            } else {
                                this.actionColorCategory();
                                noMailsToColorCategory--;
                            }
                        }

                        if (clickHyperLink) {
//                            try {
//                                this.getWebDriver().manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
//                            } catch (Exception ex) {
//                                ex.printStackTrace();
//                            }
                            if (this.getBean().getActMarkFlag().trim().equals("2")) {
                                this.actionClick(parentHandle, randomAction.nextBoolean());
                            } else {
                                this.actionClick(parentHandle);
                            }
                        }

                        if (addSafeSender) {
                            if (this.getBean().getActSafeSender().trim().equals("2")) {
                                this.actionSafeSender(randomAction.nextBoolean());
                            } else {
                                this.actionSafeSender();
                            }
                        }

                        if (isForwardMail && noMailsToForward > 0) {
                            if (this.getBean().getActForwardMail().trim().equals("2")) {
                                this.actionForward(randomAction.nextBoolean());
                            } else {
                                this.actionForward();
                            }
                            noMailsToForward--;
                        }

                        if (isReply && noMailsToReply > 0 && false) {
                            if (this.getBean().getActReplyTo().trim().equals("2")) {
                                this.actionReply(randomAction.nextBoolean());
                            } else {
                                this.actionReply();
                            }
                            noMailsToReply--;
                        } else {
                            isReply = false;
                        }

                        //Forward 
//                        if (forwardCounter < forward && mailboxType.toLowerCase().equals("inbox") && isForwarding && false) {
//                            System.out.println("Inside Forwarding");
//                            try {
//                                if (this.isElementFound("xpath", ".//button[@name='Forward']") || this.getWebDriver().findElement(By.xpath(".//button[@name='Forward']")).isDisplayed()) {
//                                    this.waitFluent("xpath", ".//button[@name='Forward']").click();
//                                    System.out.println("forwardCounter=" + forwardCounter + " forward=" + forward);
//                                    this.waitFluent("xpath", ".//input[@role='combobox']").click();
//                                    this.waitFluent("xpath", ".//input[@role='combobox']").sendKeys(this.getBean().getEmailTo().trim());
//                                    this.waitFluent("xpath", ".//button[@name='Send']").click();
//                                    Thread.sleep(2000);
//                                    forwardCounter++;
//                                }
//                            } catch (Exception ex) {
//                                ex.printStackTrace();
//                            }
//                            if (forwardCounter >= forward) {
//                                isForwarding = false;
//                            }
//                        }
                        if (isDelete && mailboxType.toLowerCase().equals("inbox") && noMailsToArchiveOrDelete > 0 && !isFlag && !isReply) {
                            this.actionDelete();
                            noMailsToArchiveOrDelete--;
                        } else {
                            isDelete = false;
                        }

                        if (isArchive && mailboxType.toLowerCase().equals("inbox") && noMailsToArchiveOrDelete > 0 && !isReply) {
                            this.actionArchive();
                            noMailsToArchiveOrDelete--;
                        } else {
                            isArchive = false;
                        }
                        System.out.println("noMailsToProcessInbox=" + noMailsToProcessInbox);

                    }
                } //subject

                /* if(mailboxType.equals("inbox") && this.totalMessageCounter == 0) {
                
                 break;
                 }*/
            }

            if (isMessageChecked && moveInbox && mailboxType.toLowerCase().equals("spam")) {
                System.out.println("Move Inbox Clicked !!!");
                if (this.getWebDriver().findElements(By.xpath(".//button[@name='Not junk' and contains(@class,'CommandBarItem-link')]")).size() > 0) {

                    this.waitFluent("xpath", ".//button[@name='Not junk' and contains(@class,'CommandBarItem-link')]").click();

                    this.waitFluent("xpath", ".//button[@name='Not junk' and contains(@class,'ContextualMenu-link') and @role='menuitem']").click();
                }
            }
            //this.getWebDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("Error while processing in " + mailboxType, "127");
        }
        return messageCount;
    }

    private int processMailsDefault(boolean moveInbox, boolean readMessage, String mailboxType) throws CustomException {
        int messageCount = 0;
        this.getWebDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        try {
            String messageSubject = this.getBean().getSubject().toLowerCase().trim();
            String messageFrom = this.getBean().getFromName().toLowerCase().trim();
            String parentHandle = this.getWebDriver().getWindowHandle();
            int noMailsToProcessInbox = this.getBean().getInboxCount();
            int noMailsToProcessJunk = this.getBean().getNotJunkCount();
            List<WebElement> subjectLines = null;
            boolean isMessageChecked = false;

            if (mailboxType.toLowerCase().equals("spam")) {
                subjectLines = this.getWebDriver().findElements(By.xpath(".//div[@aria-label='Message list']//div[@draggable='true']/div/div/div[2]/div[2]/div/span"));
            }

            if (mailboxType.toLowerCase().equals("inbox")) {
                subjectLines = this.getWebDriver().findElements(By.xpath(".//div[@aria-label='Message list']//div[@draggable='true' and not(@class)]/parent::div[1]"));
            }
            parentHandle = this.getWebDriver().getWindowHandle(); // get the current window handle
            System.out.println("\n Message In  in new" + mailboxType + " : " + subjectLines.size());
            messageCount = subjectLines.size();
            for (int j = 0, k = 0; j < subjectLines.size(); j++) {

                if (mailboxType.toLowerCase().equals("inbox") && noMailsToProcessInbox < 1) {
                    break;
                }

                if (mailboxType.toLowerCase().equals("spam") && noMailsToProcessJunk < 1) {
                    break;
                }
                System.out.println("mailboxType=" + mailboxType + " <--> J=" + j + " --->  totalMessageCounter=" + this.totalMessageCounter);

                String subjectText = "";

                String fromName = "";

                try {
                    if (!(subjectLines.get(j).isEnabled() && subjectLines.get(j).isDisplayed())) {
                        Thread.sleep(1000);
                    }
                } catch (org.openqa.selenium.StaleElementReferenceException ex) {
                    ex.printStackTrace();
                    Thread.sleep(1200);
                }

                if (mailboxType.toLowerCase().equals("inbox")) {
                    try {
                        Actions action3 = new Actions(this.getWebDriver());
                        action3.moveToElement(subjectLines.get(j)).build().perform();
                        subjectText = subjectLines.get(j).findElement(By.xpath("div/div/div/div[2]/div[2]/div/span")).getText().toLowerCase();
                        fromName = subjectLines.get(j).findElement(By.xpath("div/div/div/div[2]/div[1]/div/span")).getText().toLowerCase();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        System.out.println("Error detected");
                        continue;
                    }
                } else {
                    subjectText = subjectLines.get(j).getText().toString().toLowerCase();
                    fromName = subjectLines.get(j).findElement(By.xpath("parent::div/parent::div/parent::div/div[1]//span")).getText();
                }

                if (mailboxType.toLowerCase().equals("inbox")) {
                    noMailsToProcessInbox--;
                }

                if (mailboxType.toLowerCase().equals("spam")) {
                    noMailsToProcessJunk--;
                }

                System.out.println(mailboxType + "  J=  " + j);
                System.out.println(subjectText + " ****** " + messageSubject);
                System.out.println(fromName + " ******>>> " + messageFrom);
                if (((subjectText.contains(messageSubject) || fromName.contains(messageSubject) || messageSubject.trim().equalsIgnoreCase("NA")) && mailboxType.toLowerCase().equals("inbox")) || ((subjectText.contains(messageFrom) || fromName.contains(messageFrom) || messageFrom.trim().equalsIgnoreCase("NA")) && mailboxType.toLowerCase().equals("spam"))) {
                    System.out.println("Found Subject I:" + subjectText);
                    System.out.println("Move Inbox : " + moveInbox);
                    if (subjectText.contains("microsoft account") || subjectText.contains("outlook") || subjectText.contains("windows")) {
                        continue;
                    }
                    if (moveInbox) {
                        if (moveInbox && this.totalMessageCounter == 0) {
                            this.setMessageFound(false);
                        } else {
                            this.setMessageFound(true);
                        }

                        this.totalMessageCounter++;
                        Actions action = new Actions(this.getWebDriver());
                        action.moveToElement(subjectLines.get(j)).click(subjectLines.get(j).findElement(By.xpath("parent::div/parent::div/parent::div/parent::div/div[1]/div"))).build().perform();
                        isMessageChecked = true;

                    }
                    if (readMessage) {

                        System.out.println("Found Subject II: J=" + j + "--->" + this.totalMessageCounter);

                        int tryClickInbox = 3;
                        while (tryClickInbox-- > 0) {
                            try {
                                subjectLines.get(j).click();
                                break;
                            } catch (Exception ex) {
                                this.pressEscape();
                                ex.printStackTrace();
                                JavascriptExecutor js = (JavascriptExecutor) this.getWebDriver();
                                js.executeScript("arguments[0].scrollIntoView();", subjectLines.get(j));
                            }
                        }
                        if (this.getBean().getSubject().trim().equalsIgnoreCase("NA")) {
                            this.setMessageFound(true);
                        }

                        subjectLines.get(j).sendKeys(Keys.chord(Keys.CONTROL, "q"));
                        System.out.println("Marked as read");
                    }

                } //subject

                /* if(mailboxType.equals("inbox") && this.totalMessageCounter == 0) {
                
                 break;
                 }*/
            }

            if (isMessageChecked && moveInbox) {
                System.out.println("Move Inbox Clicked !!!");
                if (this.getWebDriver().findElements(By.xpath(".//button[@name='Not junk' and contains(@class,'CommandBarItem-link')]")).size() > 0) {

                    this.waitFluent("xpath", ".//button[@name='Not junk' and contains(@class,'CommandBarItem-link')]").click();

                    this.waitFluent("xpath", ".//button[@name='Not junk' and contains(@class,'ContextualMenu-link') and @role='menuitem']").click();
                } else {

                    this.waitFluent("xpath", ".//button[@name='Move to' and contains(@class,'ms-CommandBarItem-link')]").click();

                    this.waitFluent("xpath", ".//div[contains(@class,'ms-ContextualMenu-Callout')]//div[@title='Inbox']").click();
                }
            }
            this.getWebDriver().manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("Error in processMailsDefault " + mailboxType, "126");
        }
        return messageCount;
    }

    public boolean startProcess() throws CustomException, Exception {
        boolean processResult = false;
        try {
            System.out.println("Hotmail Start..");
            ChromeProxy chromeProxy = new ChromeProxy();
            this.setWebDriver(chromeProxy.setupProxy(this.getBean(), this.ispUrl));
            this.setMessageFound(false);
            this.signIn();
            this.checkPopupInInbox();
            this.pressEscape();
            System.out.println(this.getBean().getSubject().trim().toUpperCase());
            String team="teamG";
            switch (Integer.parseInt(this.getBean().getActionType())) {
                case 0:
                    if (!this.getWebDriver().getCurrentUrl().contains("junkemail")) {
                        this.moveSpam();
                    }

                    Thread.sleep(6000);
                    this.pressEscape();
                    this.processMailsDefault(true, false, "spam");
                    this.moveInbox();
                    this.totalMessageCounter = this.getBean().getInboxCount();
                    Thread.sleep(10000);
                    this.checkPopupInInbox();
                    this.pressEscape();
                    this.processMailsDefault(false, true, "inbox");
                    break;
                case 1:
                    if (this.getBean().getActProcessJunk().equals("1")) {
                        this.moveSpam();
                        Thread.sleep(12000);
                        this.pressEscape();
                        if (this.getBean().getActBulkNotJunk().trim().equals("1") && false) {
                            this.bulkNotJunk();
                        } else {
                            if (this.getBean().getReport_not_spam().equals("1")) {
                                this.processMailsCustomReportNotSpam(true, true, "spam");

                            } else if (this.getBean().getReport_not_spam().equals("2")) {
                                System.out.println("Report not spam selected as 2");
                                this.processMailsCustomReportNotSpam(true, true, "spam");
                                this.moveSpam();
                                System.out.println("Processing normally now");
                                this.processMailsCustom(true, false, "spam");
                            } else {
                                this.processMailsCustom(true, false, "spam");
                            }
                            Thread.sleep(6000);
                        }
                    }
                    if (this.getBean().getActProcessInbox().equals("1")) {
                        this.moveInbox();
                        this.totalMessageCounter = this.getBean().getInboxCount();

                        Thread.sleep(15000);
                        this.checkPopupInInbox();
                        this.pressEscape();
                        this.processMailsCustom(false, true, "inbox");
                    }
                    if (this.getBean().getActComposeMail().equals("1")) {
                        this.composeMail();
                    }
                    break;
                case 2:
                    this.moveSpam();
                    Thread.sleep(6000);
                    this.pressEscape();
                    this.processMailsDefault(true, false, "spam");
                    this.moveInbox();
                    this.totalMessageCounter = this.getBean().getInboxCount();
                    Thread.sleep(10000);
                    this.checkPopupInInbox();
                    this.pressEscape();
                    this.processMailsRandom(false, true, "inbox");
                    break;
            }
            this.signOut();
            processResult = this.isMessageFound();

        } catch (CustomException e) {
            e.printStackTrace();
            throw new CustomException(e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("Unknown error", "5009");
        } finally {
            if (this.getWebDriver() != null) {
                //this.getWebDriver().close();
                this.getWebDriver().quit();
            }
        }
        return processResult;
    }

    public static void main(String[] args) {
        try {

            Random randomGenerator = new Random();

            ispBean bean = new ispBean();
            bean.setEmail("yongstanford@outlook.com");
            bean.setPassword("DavidDrew");
            bean.setProxyIP("154.16.148.127");
            bean.setProxyPort("3129");
            bean.setProxyUsername("NA");
            bean.setProxyPassword("NA");
            bean.setSubject("NA");
            bean.setFromName("NA");
            bean.setActionType("1");
            bean.setActClickLink("1");
            bean.setActBulkNotJunk("0");
            bean.setActAddContact("0");
            bean.setActArchiveDelete("0");
            bean.setActChangePassword("0");
            bean.setActColorCategory("0");
            bean.setActComposeMail("0");
            bean.setActForwardMail("0");
            bean.setActMarkFlag("0");
            bean.setActReplyTo("0");
            bean.setActSafeSender("0");
            bean.setActProcessInbox("1");
            bean.setActProcessJunk("1");
            bean.setInboxCount(25);
            bean.setNotJunkCount(10);
            Hotmail hotmail = new Hotmail();
            hotmail.setBean(bean);
            hotmail.startProcess();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
