package Pages;

import Helpers.Manager.FileReaderManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage extends CommonPage{
    private WebDriver driver;
    @FindBy(xpath = "//a[contains(text(),'Login to use')]")
    private WebElement btnLoginToUse;
    @FindBy(id = "Input_Email")
    private WebElement txtUsername;
    @FindBy(id = "Input_Password")
    private WebElement txtPassword;
    @FindBy(xpath = "//button[contains(text(),'Sign in')]")
    private WebElement btnSignIn;
    @FindBy(id = "WO")
    private WebElement txtWO;
    @FindBy(id = "Shift")
    private WebElement lstShift;
    @FindBy(id = "TestMachineName")
    private WebElement lstTestMachine;
    @FindBy(xpath = "//button[contains(text(),'Start')]")
    private WebElement btnStart;
    public LoginPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void navigateToSF(){
        String configFilePath = "C:\\CucumberFramework\\Config\\Configuration.properties";
        String url = FileReaderManager.getInstance().getPropertyFileReader(configFilePath).getValueFromKey("URL");
        driver.get(url);
        driver.manage().window().maximize();
    }
    public void loginToSF(){
        clickToElement(btnLoginToUse);
        setTextToElement(txtUsername, "thanh.nguyen@virtium.com");
        setTextToElement(txtPassword,"@Virtium949");
        clickToElement(btnSignIn);
    }
    public void inputWOInfo(String wo, String shift, String testMachine){
        setTextToElement(txtWO, wo);
        Assert.assertTrue(String.format("The shift %s is invalid", shift), chooseShift(shift));
        Assert.assertTrue(String.format("The Test Machine %s is invalid", testMachine), chooseTestMachine(testMachine));
        clickToElement(btnStart);
    }
    public boolean chooseShift(String shift){
        boolean result = false;
        List<WebElement> shiftElements = getListOfElementsBySelect(lstShift);
        for (int rowIndex = 1; rowIndex <= shiftElements.size(); rowIndex++){
            String shiftXpath = String.format("//*[@id='Shift']/option[%d]", rowIndex);
            WebElement shiftElement = driver.findElement(By.xpath(shiftXpath));
            String shiftVal = getTextFromElement(shiftElement);
            if (shiftVal.equals(shift)){
                selectElementByText(lstShift, shiftVal);
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean chooseTestMachine(String testMachine){
        boolean result = false;
        List<WebElement> testMachineElements = getListOfElementsBySelect(lstTestMachine);
        for (int rowIndex = 1; rowIndex <= testMachineElements.size(); rowIndex++){
            String testMachineXpath = String.format("//*[@id='TestMachineName']/option[%d]", rowIndex);
            WebElement testMachineElement = driver.findElement(By.xpath(testMachineXpath));
            String testMachineVal = getTextFromElement(testMachineElement);
            if (testMachineVal.equals(testMachine)){
                selectElementByText(lstTestMachine, testMachineVal);
                result = true;
                break;
            }
        }
        return result;
    }


}
