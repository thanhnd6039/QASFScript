package Steps;

import Pages.SMTPage;
import SharingTestContext.TestContext;
import cucumber.api.java.en.And;

public class SMTSteps {
    private TestContext testContext;
    private SMTPage smtPage;
    public SMTSteps(TestContext context){
        testContext = context;
        smtPage = testContext.getPageObjectManager().getSmtPage();
    }

    @And("^I scan Panel ID at SMTPASSED from excel$")
    public void scanPanelIDFromExcel()throws Throwable{
        smtPage.scanPanelIDFromExcel();
    }

}
