package Pages;

import Helpers.Manager.FileReaderManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SMTPage extends CommonPage{
    private WebDriver driver;
    @FindBy(id = "PanelID")
    private WebElement txtPanelIDPassed;
    @FindBy(id = "passPanelLabel")
    private WebElement txtPassPanelLabel;

    public SMTPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void scanPanelIDFromExcel()throws Throwable{
        String configFilePath = "C:\\CucumberFramework\\Config\\Configuration.properties";
        String woFilePath = FileReaderManager.getInstance().getPropertyFileReader(configFilePath).getValueFromKey("WO_FILE_PATH");
        List<Object[]> dataOfWO = FileReaderManager.getInstance().getExcelReader().readDataFromExcel(woFilePath, 0, 1, 0);
        for (int rowIndex = 0; rowIndex < dataOfWO.size(); rowIndex++){
            String topPanelID = dataOfWO.get(rowIndex)[0].toString().trim();
            String botPanelID = dataOfWO.get(rowIndex)[1].toString().trim();
            String passPanelLabel = "";
            if (!topPanelID.isEmpty() && !botPanelID.isEmpty()){
                System.out.println(String.format("Top: %s", topPanelID));
                setTextToElement(txtPanelIDPassed, topPanelID);
                pressKey("Tab");
                Thread.sleep(5000);
                Assert.assertTrue("The browser has been suspended", waitForPageLoadComplete());
                waitForElementVisibility(txtPassPanelLabel);
                passPanelLabel = getTextFromElement(txtPassPanelLabel);
                System.out.println(String.format("passPanelLabel: %s", passPanelLabel));
                Assert.assertTrue("The return Panel ID is different with inputted Panel ID", passPanelLabel.equalsIgnoreCase(topPanelID));
                System.out.println(String.format("Bot: %s", botPanelID));
                setTextToElement(txtPanelIDPassed, botPanelID);
                pressKey("Tab");
                Thread.sleep(5000);
                Assert.assertTrue("The browser has been suspended", waitForPageLoadComplete());
                waitForElementVisibility(txtPassPanelLabel);
                passPanelLabel = getTextFromElement(txtPassPanelLabel);
                System.out.println(String.format("passPanelLabel: %s", passPanelLabel));
                Assert.assertTrue("The return Panel ID is different with inputted Panel ID", passPanelLabel.equalsIgnoreCase(botPanelID));
            }
        }
    }
}
