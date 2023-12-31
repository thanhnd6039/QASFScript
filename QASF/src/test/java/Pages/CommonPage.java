package Pages;

import Helpers.KeywordWebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CommonPage extends KeywordWebUI {
    private WebDriver driver;
    public CommonPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public void selectTab(String tab) throws Throwable{
        String tabXpath = String.format("//a[@href='/vtshopfloor/%s']", tab);
        Thread.sleep(5000);
        WebElement tabElement = driver.findElement(By.xpath(tabXpath));
        clickToElement(tabElement);
    }
    public void clickToButton(String buttonLabel, String stage){
        String btnXpath = String.format("//a[@href='/vtshopfloor/%s%s']", stage, buttonLabel);
        WebElement btnElement = driver.findElement(By.xpath(btnXpath));
        clickToElement(btnElement);
    }
    public void scrollToElement(String tab){
        String tabXpath = String.format("//a[@href='/vtshopfloor/%s']", tab);
        WebElement tabElement = driver.findElement(By.xpath(tabXpath));
        scrollToElement(tabElement);
    }
}
