package Steps;

import Pages.PDTESTPage;
import SharingTestContext.TestContext;
import cucumber.api.java.en.And;

public class PDTESTSteps {
    private TestContext testContext;
    private PDTESTPage pdtestPage;
    public PDTESTSteps(TestContext context){
        testContext = context;
        pdtestPage = testContext.getPageObjectManager().getPdtestPage();
    }
    @And("^I scan Panel ID and SerialNumber at labeling with number of blocks is (.*) from excel$")
    public void scanPanelIdAndSNFromExcel(int numOfBlocks) throws Throwable{
        pdtestPage.scanPanelIdAndSNFromExcel(numOfBlocks);
    }
    @And("^I scan SerialNumber at Depanel from excel$")
    public void scanSNFromExcel() throws Throwable{
        pdtestPage.scanSNFromExcel();
    }

}
