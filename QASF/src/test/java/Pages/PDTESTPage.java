package Pages;

import Helpers.Manager.FileReaderManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PDTESTPage extends CommonPage{
    private WebDriver driver;
    @FindBy(id = "serialNumberPassedText")
    private WebElement txtSNPassed;
    @FindBy(id = "panelIDText")
    private WebElement txtPanelID;
    @FindBy(id = "passSerialNumberLabel")
    private WebElement txtPassedSNLabel;
    @FindBy(id = "passPanelLabel")
    private WebElement txtPassedPanelLabel;
    public PDTESTPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void scanPanelIdAndSNFromExcel(int numOfBlocks)throws Throwable{
        String configFilePath = "C:\\CucumberFramework\\Config\\Configuration.properties";
        String woFilePath = FileReaderManager.getInstance().getPropertyFileReader(configFilePath).getValueFromKey("WO_FILE_PATH");
        List<Object[]> dataOfWO = FileReaderManager.getInstance().getExcelReader().readDataFromExcel(woFilePath, 0, 1, 0);
        int countPanelID = 0;
        for (int rowIndex = 0; rowIndex < dataOfWO.size(); rowIndex++){
            String topPanelID = dataOfWO.get(rowIndex)[0].toString().trim();
            if (!topPanelID.isEmpty()){
                countPanelID++;
                System.out.println(String.format("Top: %s", topPanelID));
                setTextToElement(txtPanelID, topPanelID);
                pressKey("Tab");
                Thread.sleep(5000);
                int snIndex = 2;
                for (int rowIndexSN = rowIndex*numOfBlocks; rowIndexSN < countPanelID*numOfBlocks;rowIndexSN++){
                    String sn = dataOfWO.get(rowIndexSN)[2].toString().trim();
                    System.out.println(String.format("SN: %s", sn));
                    String snXpath = String.format("//*[@id='item_SerialNumber'][@tabindex='%d']", snIndex);
                    WebElement snElement = driver.findElement(By.xpath(snXpath));
                    setTextToElement(snElement,sn);
                    pressKey("Tab");
                    Thread.sleep(5000);
                    snIndex++;
                }
                Thread.sleep(10000);
                Assert.assertTrue("Not found Mapping Passed Label", waitForElementVisibility(txtPassedPanelLabel));
                String mappingPassedLabel = getTextFromElement(txtPassedPanelLabel);
                String expectedMappingPassedLabel = String.format("%s and Serial Numbers are mapped successfully",topPanelID);
                Assert.assertTrue(expectedMappingPassedLabel.equalsIgnoreCase(mappingPassedLabel));
            }
        }
    }
    public void scanSNFromExcel() throws Throwable{
        String configFilePath = "C:\\CucumberFramework\\Config\\Configuration.properties";
        String woFilePath = FileReaderManager.getInstance().getPropertyFileReader(configFilePath).getValueFromKey("WO_FILE_PATH");
        List<Object[]> dataOfWO = FileReaderManager.getInstance().getExcelReader().readDataFromExcel(woFilePath, 0, 1, 0);
        for (int rowIndex = 0; rowIndex < dataOfWO.size(); rowIndex++){
            String sn = dataOfWO.get(rowIndex)[2].toString().trim();
            if (!sn.isEmpty()){
                System.out.println(String.format("SN: %s", sn));
                setTextToElement(txtSNPassed, sn);
                pressKey("Tab");
                Thread.sleep(5000);
                Assert.assertTrue("The browser has been suspended", waitForPageLoadComplete());
                waitForElementVisibility(txtPassedSNLabel);
                String passSNLabel = getTextFromElement(txtPassedSNLabel);
                Assert.assertTrue("The returned SN is different with inputted SN",passSNLabel.equalsIgnoreCase(sn));
            }
        }
    }
}
