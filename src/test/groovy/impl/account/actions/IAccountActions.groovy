package impl.account.actions

import impl.Account
import impl.AccountType
import impl.ActionsImpl
import impl.account.pageobjects.LoginPageObjects
import io.cify.framework.core.Device
import org.openqa.selenium.support.ui.ExpectedConditions

import static impl.ActionsWrapper.waitForCondition
import static impl.Constants.*

trait IAccountActions {
    Device device
    LoginPageObjects loginPageObjects

    void loginWithAccount(AccountType type){
        ActionsImpl.getBettingActions().closeRightDrawer()
        waitForCondition(device, {ExpectedConditions.elementToBeClickable(new LoginPageObjects(device).getMainPageLoginButton())},10)
        Account acc = Account.getAccount(type)
        sleep(2000)
        clickMainPageLoginButton()
        fillEmailField(acc.getEmail())
        fillPasswordField(acc.getPassWord())
        clickLoginFormLoginButton()
    }

    void clickMainPageLoginButton(){
        waitForCondition(device,{new LoginPageObjects(device,TIMEOUT2S).getMainPageLoginButton().click();true},10)
    }

    void clickLoginFormLoginButton(){
        loginPageObjects.getLoginFormLoginButton().click()
    }

    void fillEmailField(String email){
        loginPageObjects.getLoginEmail().sendKeys(email)
    }

    void fillPasswordField(String password){
        loginPageObjects.getLoginPassword().sendKeys(password)
    }

    boolean isUserIconDisplayed(){
        loginPageObjects.getLoggedInIcon().isDisplayed()
    }
}