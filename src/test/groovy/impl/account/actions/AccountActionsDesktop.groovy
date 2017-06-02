package impl.account.actions

import impl.account.pageobjects.LoginPageObjects
import impl.account.pageobjects.MyAccount
import io.cify.framework.core.Device
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import static impl.ActionsWrapper.findValueFromString
import static impl.ActionsWrapper.waitForCondition
import static impl.Constants.*
import static impl.TestDataManager.getValue

class AccountActionsDesktop implements IAccountActions {

    AccountActionsDesktop(Device device) {
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

    /**
     * Checks that transaction view contains recent bets id, timestamp and bet amount
     * That info must previously be saved to TestDataManager
     * @return
     */
    boolean isBetInformationVisible() {
        int created = 0
        int description = 2
        int amount = 4
        List<WebElement> transActionFields = myaccount.getLatestTransaction().findElements(By.cssSelector("td"))
        transActionFields.get(created).getText() == getValue(CREATED) &&
                findValueFromString(transActionFields.get(description).getText(), "#(.*?) ") == (getValue(ID)) &&
                transActionFields.get(amount).getText().replaceAll("-", "") as BigDecimal == getValue(AMOUNT) as BigDecimal
    }
}
