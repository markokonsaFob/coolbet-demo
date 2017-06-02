package impl.account.actions

import impl.ActionsImpl
import impl.account.pageobjects.LoginPageObjects
import impl.account.pageobjects.MyAccount
import impl.betting.pageobjects.BettingSection
import io.cify.framework.core.Device
import org.openqa.selenium.By
import org.openqa.selenium.Dimension
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions

import static impl.ActionsWrapper.findValueFromString
import static impl.ActionsWrapper.waitForCondition
import static impl.Constants.*
import static impl.TestDataManager.getValue

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
        waitForCondition(device, { new MyAccount(device, TIMEOUT2S).getMenuProfile().click(); true }, TIMEOUT20S)
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

    /**
     * Checks that transaction view contains recent bets id, timestamp and bet amount
     * That info must previously be saved to TestDataManager
     * @return
     */
    boolean isBetInformationVisible() {
        int created = 0
        int description = 3
        int amount = 4
        List<WebElement> transActionFields = myaccount.getLatestTransaction().findElements(By.cssSelector("td"))
        transActionFields.get(created).getText() == getValue(CREATED) &&
                findValueFromString(transActionFields.get(description).getText(), "#(.*?) ") == (getValue(ID)) &&
                transActionFields.get(amount).getText().replaceAll("-", "") as BigDecimal == getValue(AMOUNT) as BigDecimal
    }
}
