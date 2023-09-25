package Helpers;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class KeywordWebUI {
    private WebDriver driver;
    private WebDriverWait wait;
    public KeywordWebUI(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 60);
    }
    public boolean waitForElementVisibility(WebElement element){
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        }catch (Exception e){
            System.out.println(String.format("Error: %s", e.getMessage()));
            return false;
        }
    }

    public String getTextFromElement(WebElement element){
        String text = null;
        try {
            text = element.getText();
        }catch (Exception e){
            System.out.println(String.format("Error: %s", e.getMessage()));
        }
        return text;
    }
    public boolean waitForElementIsEnabled(WebElement element){
        waitForElementVisibility(element);
        try{
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver){
                    if (element.isEnabled()){
                        return true;
                    }else {
                        return false;
                    }
                }
            });
            return true;
        }catch (Exception e){
            System.out.println(String.format("Error: %s", e.getMessage()));
            return false;
        }
    }
    public boolean clickToElement(WebElement element){
        try{
            if (waitForElementVisibility(element) == false){
                return false;
            }
            if (waitForElementIsEnabled(element) == false){
                return false;
            }
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            return true;
        }
        catch (Exception e){
            System.out.println(String.format("Error: %s", e.getMessage()));
            return false;
        }
    }
    public void setTextToElement(WebElement element, String inputText){
        waitForElementVisibility(element);
        try{
            element.clear();
            element.sendKeys(inputText);
        }catch (Exception e){
            System.out.println(String.format("Error: %s", e.getMessage()));
        }
    }
    public List<WebElement> getListOfElementsBySelect(WebElement element){
        waitForElementVisibility(element);
        List<WebElement> listOfElements = null;

        try {
            Select select = new Select(element);
            listOfElements = select.getOptions();
        }catch (Exception e){
            System.out.println(String.format("Error: %s", e.getMessage()));
        }
        return listOfElements;
    }
    public void selectElementByValue(WebElement element, String value){
        waitForElementIsEnabled(element);
        try{
            Select select = new Select(element);
            select.selectByValue(value);
        }catch (Exception e){
            System.out.println(String.format("Error: %s", e.getMessage()));
        }
    }

    public void selectElementByText(WebElement element, String text){
        waitForElementIsEnabled(element);
        try{
            clickToElement(element);
            Select select = new Select(element);
            select.selectByVisibleText(text);
        }catch (Exception e){
            System.out.println(String.format("Error: %s", e.getMessage()));
        }
    }
    public void pressKey(String key){
        try{
            Actions builder = new Actions(driver);
            if (key.equalsIgnoreCase("Enter")){
                builder.sendKeys(Keys.ENTER).build().perform();
            } else if (key.equalsIgnoreCase("Tab")) {
                builder.sendKeys(Keys.TAB).build().perform();
            } else {
                throw new RuntimeException(String.format("The %s key is not supported or it is invalid", key));
            }
        }catch (Exception e){
            System.out.println(String.format("Error: %s", e.getMessage()));
        }
    }

}
