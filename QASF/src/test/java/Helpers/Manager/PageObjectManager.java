package Helpers.Manager;

import Pages.CommonPage;
import Pages.LoginPage;
import Pages.PDTESTPage;
import Pages.SMTPage;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {
    private WebDriver driver;
    private CommonPage commonPage;
    private LoginPage loginPage;
    private SMTPage smtPage;
    private PDTESTPage pdtestPage;
    public PageObjectManager(WebDriver driver){
        this.driver = driver;
    }
    public CommonPage getCommonPage(){
        return  (commonPage == null) ? commonPage = new CommonPage(driver) : commonPage;
    }
    public LoginPage getLoginPage(){
        return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
    }
    public SMTPage getSmtPage(){
        return (smtPage == null) ? smtPage = new SMTPage(driver) : smtPage;
    }
    public PDTESTPage getPdtestPage(){
        return (pdtestPage == null) ? pdtestPage = new PDTESTPage(driver) : pdtestPage;
    }

}
