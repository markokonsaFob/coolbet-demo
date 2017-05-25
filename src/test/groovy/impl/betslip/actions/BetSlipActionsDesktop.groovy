package impl.betslip.actions

import impl.betslip.pageobjects.BetSlip
import impl.betting.actions.IBetActions
import impl.betting.pageobjects.BettingSection
import impl.betting.pageobjects.Creditcard
import impl.betting.pageobjects.Deposit
import io.cify.framework.core.Device


class BetSlipActionsDesktop implements IBetSlipActions{

    BetSlipActionsDesktop(Device device){
        this.device = device
        this.bettingSection = new BettingSection(device)
        this.betSlip = new BetSlip(device)

    }

    /**
     * Inserts stake
     * @param stake
     */
    void insertStake(String stake) {
        betSlip.getStakeField().sendKeys(stake)
    }

}
