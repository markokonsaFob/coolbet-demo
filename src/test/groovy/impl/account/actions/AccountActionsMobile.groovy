package impl.account.actions

import impl.ActionsImpl
import impl.account.pageobjects.LoginPageObjects
import impl.account.pageobjects.MyAccount
import impl.betting.pageobjects.BettingSection
import io.cify.framework.core.Device
import org.openqa.selenium.Dimension
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions

import static impl.ActionsWrapper.waitForCondition
import static impl.Constants.TIMEOUT20S
import static impl.Constants.TIMEOUT2S

class AccountActionsMobile implements IAccountActions {

    AccountActionsMobile(Device device) {
        this.device = device
        this.loginPageObjects = new LoginPageObjects(device)
        this.myaccount = new MyAccount(device)
    }

    /**
     * Opens my account section
     */
    void openMyAccountSection() {
        loginPageObjects.getLoggedInIconMobile().click()
        new MyAccount(device).getMenuProfile().click()
        closeLeftDrawer()
    }

    /**
     * Closes left drawer
     */
    void closeLeftDrawer() {
        int offset = 5
        Actions action = new Actions(device.getDriver())
        if (device.getCapabilities().getCapability("capability") == "iPhone") {
            new BettingSection(device).getDrawerClosingOverlay().click()
        } else {
            Dimension location = myaccount.getDrawerClosingOverlay().getSize()
            action.moveToElement(myaccount.getDrawerClosingOverlay(), location.getWidth() - offset, offset).click().build().perform()
        }
        waitForCondition(device, ExpectedConditions.not(ExpectedConditions.elementToBeClickable(new MyAccount(device).getMenuProfile())), TIMEOUT20S)
    }

    /**
     * Checks that logged in user icon is displayed
     * @return
     */
    boolean isUserIconDisplayed() {
        try {
            waitForCondition(device, {
                new LoginPageObjects(device, TIMEOUT2S).getLoggedInIconMobile().isDisplayed()
            }, TIMEOUT20S)
            true
        } catch (ignore) {
            false
        }
    }

    /**
     * Opens transactions section via menu
     */
    void openTransactionsSection() {
        ActionsImpl.getBettingActions().openMenu()
        clickTransactions()
    }
}
