package impl.betting.actions

import impl.betting.pageobjects.BettingSection
import impl.betting.pageobjects.Creditcard
import impl.betting.pageobjects.Deposit
import io.cify.framework.core.Device

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
        bettingSection.getMenuButton().click()
        bettingSection.getSports().click()
        sleep(2000)
    }
}
