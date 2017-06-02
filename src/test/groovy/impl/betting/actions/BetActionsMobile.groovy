package impl.betting.actions

import impl.betslip.pageobjects.BetSlip
import impl.betting.pageobjects.BettingSection
import impl.betting.pageobjects.Creditcard
import impl.betting.pageobjects.Deposit
import io.cify.framework.core.Device
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions

import static impl.ActionsWrapper.waitForCondition
import static impl.Constants.TIMEOUT20S
import static impl.Constants.TIMEOUT2S

class BetActionsMobile implements IBetActions {

    BetActionsMobile(Device device) {
        this.device = device
        this.bettingSection = new BettingSection(device)
        this.depositPopup = new Deposit(device)
        this.creditcard = new Creditcard(device)
    }

    /**
     * Opens sport page
     */
    void openSportPage() {
        openMenu()
        bettingSection.getSports().click()
        waitForCondition(device, { !new BettingSection(device, TIMEOUT2S).getSports().isDisplayed() }, TIMEOUT20S)
        new BettingSection(device).getSoonButton().click()
    }

    void closeRightDrawer() {
        int offset = 5
        Actions action = new Actions(device.getDriver())
        if (device.getCapabilities().getCapability("capability") == "iPhone") {
            new BettingSection(device).getDrawerClosingOverlay().click()
        } else {
            action.moveToElement(bettingSection.getDrawerClosingOverlay(), offset, offset).click().build().perform()
        }
        waitForCondition(device, ExpectedConditions.not(ExpectedConditions.elementToBeClickable(new BetSlip(device, TIMEOUT2S).getBetSlip())), TIMEOUT20S)
    }
}
