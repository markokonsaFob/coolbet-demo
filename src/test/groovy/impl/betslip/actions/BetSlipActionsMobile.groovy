package impl.betslip.actions

import impl.betslip.pageobjects.BetSlip
import impl.betting.pageobjects.BettingSection
import io.cify.framework.core.Device

class BetSlipActionsMobile implements IBetSlipActions {

    BetSlipActionsMobile(Device device) {
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
        int dotIndex = 10
        int zeroIndex = 9
        betSlip.getStakeField().click()
        stake.each {
            switch (it) {
                case '.': betSlip.getStakeNumpadButtons().get(dotIndex).click()
                    break
                case '0': betSlip.getStakeNumpadButtons().get(zeroIndex).click()
                    break
                default: betSlip.getStakeNumpadButtons().get((it as int) - indexCorrector).click()
            }
        }
        betSlip.getStakeNumpadButtons().last().click()

    }

}
