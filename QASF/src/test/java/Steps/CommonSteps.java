package Steps;

import Pages.CommonPage;
import SharingTestContext.TestContext;
import cucumber.api.java.en.And;

public class CommonSteps {
    private TestContext testContext;
    private CommonPage commonPage;
    public CommonSteps(TestContext context){
        testContext = context;
        commonPage = testContext.getPageObjectManager().getCommonPage();
    }
    @And("^I select tab (.*)$")
    public void selectTab(String tab) throws Throwable{
        commonPage.selectTab(tab);
    }
    @And("^I click to button (.*) at (.*)$")
    public void clickToButton(String buttonLabel, String stage) throws Throwable{
        commonPage.clickToButton(buttonLabel, stage);
    }
    @And("^I scroll to tab (.*)$")
    public void scrollToElement(String tab) throws Throwable {
        commonPage.scrollToElement(tab);
    }

}
