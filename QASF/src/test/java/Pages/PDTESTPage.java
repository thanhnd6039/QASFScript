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
    private String woFilePath = "C:\\CucumberFramework\\TestData\\63023.xlsx";
    private WebDriver driver;
    @FindBy(id = "panelIDText")
    private WebElement txtPanelID;
    public PDTESTPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void scanPanelIdAndSNFromExcel(int numOfBlocks)throws Throwable{
        List<Object[]> dataOfWO = FileReaderManager.getInstance().getExcelReader().readDataFromExcel(woFilePath, 0, 1, 0);
        int countPanelID = 0;
        for (int rowIndex = 0; rowIndex < dataOfWO.size(); rowIndex++){
            String botPanelID = dataOfWO.get(rowIndex)[1].toString().trim();
            if (!botPanelID.isEmpty()){
                countPanelID++;
                System.out.println(String.format("Bot: %s", botPanelID));
                setTextToElement(txtPanelID, botPanelID);
                pressKey("Tab");
                Thread.sleep(2000);
                int snIndex = 2;
                for (int rowIndexSN = rowIndex*numOfBlocks; rowIndexSN < countPanelID*numOfBlocks;rowIndexSN++){
                    String sn = dataOfWO.get(rowIndexSN)[2].toString().trim();
                    System.out.println(String.format("SN: %s", sn));
                    String snXpath = String.format("//*[@id='item_SerialNumber'][@tabindex='%d']", snIndex);
                    WebElement snElement = driver.findElement(By.xpath(snXpath));
                    setTextToElement(snElement,sn);
                    pressKey("Tab");
                    Thread.sleep(2000);
                    snIndex++;
                }
            }
        }
    }
}
