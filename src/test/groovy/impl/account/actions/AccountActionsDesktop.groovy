package impl.account.actions

import impl.account.pageobjects.LoginPageObjects
import impl.account.pageobjects.MyAccount
import io.cify.framework.core.Device

import static impl.ActionsWrapper.waitForCondition
import static impl.Constants.TIMEOUT20S
import static impl.Constants.TIMEOUT2S

class AccountActionsDesktop implements IAccountActions{

    AccountActionsDesktop(Device device){
        this.device = device
        this.loginPageObjects = new LoginPageObjects(device)
        this.myaccount = new MyAccount(device)
    }

    /**
     * Opens my account section
     */
    void openMyAccountSection() {
        loginPageObjects.getLoggedInIconDesktop().click()
        new LoginPageObjects(device).getMyAccount().click()
    }

    /**
     * Opens transactions section
     */
    void openTransactionsSection() {
        clickTransactions()
    }

    /**
     * Checks that logged in user icon is displayed
     * @return
     */
    boolean isUserIconDisplayed() {
        try {
            waitForCondition(device, {
                new LoginPageObjects(device, TIMEOUT2S).getLoggedInIconDesktop().isDisplayed()
            }, TIMEOUT20S)
            true
        } catch (ignore) {
            false
        }
    }
}
