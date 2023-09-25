package Steps;

import Pages.LoginPage;
import SharingTestContext.TestContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

public class LoginSteps {
    private TestContext testContext;
    private LoginPage loginPage;
    public LoginSteps(TestContext context){
        testContext = context;
        loginPage = testContext.getPageObjectManager().getLoginPage();
    }
    @Given("^I navigate to SF$")
    public void navigateToSF() throws Throwable{
        loginPage.navigateToSF();
    }
    @And("^I login to SF with role is Admin$")
    public void loginToSF() throws Throwable {
        loginPage.loginToSF();
    }
    @And("^I input Work Order (.*), Shift (.*), Test Machine (.*)$")
    public void inputWOInfo(String wo, String shift, String testMachine) throws Throwable {
        loginPage.inputWOInfo(wo, shift, testMachine);
    }

}
