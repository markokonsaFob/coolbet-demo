package impl.betslip.actions

import impl.betslip.pageobjects.BetSlip
import impl.betting.actions.IBetActions
import impl.betting.pageobjects.BettingSection
import impl.betting.pageobjects.Creditcard
import impl.betting.pageobjects.Deposit
import io.cify.framework.core.Device


class BetSlipActionsMobile implements IBetSlipActions{

    BetSlipActionsMobile(Device device){
        this.device = device
        this.bettingSection = new BettingSection(device)
        this.betSlip = new BetSlip(device)
    }

    /**
     * Inserts stake
     * @param stake
     */
    void insertStake(String stake) {
        int indexCorrector = 1
        betSlip.getStakeField().click()
        stake.each {
            betSlip.getStakeNumpadButtons().get((it as int) - indexCorrector).click()
        }
        betSlip.getStakeNumpadButtons().last().click()

    }
}
