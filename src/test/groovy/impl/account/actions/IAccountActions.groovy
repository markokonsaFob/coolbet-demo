package impl.account.actions

import impl.Account
import impl.AccountType
import impl.account.pageobjects.LoginPageObjects
import io.cify.framework.core.Device
import org.openqa.selenium.support.ui.ExpectedConditions

import static impl.ActionsWrapper.isDisplayed
import static impl.ActionsWrapper.waitForCondition
import static impl.Constants.*

trait IAccountActions {
    Device device
    LoginPageObjects loginPageObjects

    /**
     * Login with given account info
     * @param type type of account to use
     */
    void loginWithAccount(AccountType type) {
        Account acc = Account.getAccount(type)
        clickMainPageLoginButton()
        fillEmailField(acc.getEmail())
        fillPasswordField(acc.getPassWord())
        clickLoginFormLoginButton()
    }

    /**
     * Clicks login button on main page
     */
    void clickMainPageLoginButton() {
        waitForCondition(device, ExpectedConditions.elementToBeClickable(new LoginPageObjects(device, TIMEOUT2S).getMainPageLoginButton()), TIMEOUT30S)
        waitForCondition(device, {
            new LoginPageObjects(device, TIMEOUT2S).getMainPageLoginButton().click(); true
        }, TIMEOUT30S)
    }

    /**
     * Clicks login button on login form
     */
    void clickLoginFormLoginButton() {
        loginPageObjects.getLoginFormLoginButton().click()
        waitForCondition(device, {
            !isDisplayed(new LoginPageObjects(device, TIMEOUT2S).getLoginFormLoginButton())
        }, TIMEOUT20S)
    }

    /**
     * Enters email
     * @param email
     */
    void fillEmailField(String email) {
        loginPageObjects.getLoginEmail().sendKeys(email)
    }

    /**
     * Enters password
     * @param password
     */
    void fillPasswordField(String password) {
        loginPageObjects.getLoginPassword().sendKeys(password)
    }

    /**
     * Checks that logged in user icon is displayed
     * @return
     */
    boolean isUserIconDisplayed() {
        try {
            waitForCondition(device, {
                new LoginPageObjects(device, TIMEOUT2S).getLoggedInIcon().isDisplayed()
            }, TIMEOUT20S)
            true
        } catch (ignore) {
            false
        }
    }
}