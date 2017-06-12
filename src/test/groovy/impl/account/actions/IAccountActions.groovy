package impl.account.actions

import impl.Account
import impl.AccountType
import impl.account.pageobjects.LoginPageObjects
import impl.account.pageobjects.MyAccount
import io.cify.framework.core.Device
import org.openqa.selenium.support.ui.ExpectedConditions

import static impl.ActionsWrapper.isDisplayed
import static impl.ActionsWrapper.waitForCondition
import static impl.Constants.*

trait IAccountActions {
    Device device
    LoginPageObjects loginPageObjects
    MyAccount myaccount

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
    abstract boolean isUserIconDisplayed()

    /**
     * Opens my account section
     */
    abstract void openMyAccountSection()

    /**
     * Checks that password and profile forms are visible on account page
     * @return
     */
    boolean isAccountPageDisplayed() {
        isDisplayed(myaccount.getPasswordForm()) && isDisplayed(myaccount.getProfileForm())
    }

    /**
     * Clicks "transactions" link in left side menu
     */
    void clickTransactions() {
        waitForCondition(device, { new MyAccount(device, TIMEOUT2S).getTransactionsButton().click(); true }, TIMEOUT20S)
    }

    /**
     * Checks that transactions table is displayed
     * @return
     */
    boolean isTransactionsSectionDisplayed() {
        isDisplayed(myaccount.getTransActionsTable())
    }

    /**
     * Clicks filter button "Today"
     */
    void showOnlyTodays() {
        myaccount.getTransActionsFilterToday().click()
        sleep(2000)
        //waitForCondition(device, {
        //    !new MyAccount(device, TIMEOUT2S).getTransActionsFilterAll().getAttribute("class").contains("active")
        //}, TIMEOUT20S)
    }

    /**
     * Opens transactions section via menu
     */

    abstract void openTransactionsSection()

    /**
     * Checks that transaction view contains recent bets id, timestamp and bet amount
     * That info must previously be saved to TestDataManager
     * @return
     */
    abstract boolean isBetInformationVisible()
}