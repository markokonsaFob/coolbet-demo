package impl.betslip.actions

import impl.betslip.pageobjects.BetSlip
import impl.betting.pageobjects.BettingSection
import io.cify.framework.core.Device

import static impl.ActionsWrapper.waitForCondition
import static impl.Constants.TIMEOUT10S
import static impl.Constants.TIMEOUT2S

trait IBetSlipActions {

    private static String MSG_EMPTY_BETLIST = "Your betslip is currently empty"
    Device device
    BettingSection bettingSection
    BetSlip betSlip

    /**
     * Opens bet slip drawer if it is not already open
     */
    void clickBetSlipMenuButton() {
        if (!isBetSlipVisible()) {
            sleep(1000)
            waitForCondition(device, { new BetSlip(device, TIMEOUT2S).getBetSlipButton().click(); true }, TIMEOUT10S)
            waitForCondition(device, { new BetSlip(device, TIMEOUT2S).getBetSlipButton().isDisplayed() }, TIMEOUT10S)
        }
    }

    /**
     * Checks that selected bet amounts are equal on betting page amd betslip
     * @return true/false
     */
    boolean isCorrectBetSelectedInBetslip() {
        clickBetSlipMenuButton()
        bettingSection.getSelectedMatch().getText() == betSlip.getBetAmount().getText()
    }

    /**
     * Checks that bet slip contains bets
     * @return true/false
     */
    boolean isBetSlipFilled() {
        try {
            !new BetSlip(device, TIMEOUT2S).getMessage().getText().contains(MSG_EMPTY_BETLIST)
        } catch (ignore) {
            true
        }
    }

    /**
     * Inserts stake
     * @param stake
     */
    abstract void insertStake(String stake)

    /**
     * Checks that place a bet button is disabled on bet slip
     */
    boolean isPlaceBetButtonDisabled() {
        try {
            betSlip.getPlaceBetButton().getAttribute("disabled") == "disabled"
        } catch (ignore) {
            false
        }

    }

    /**
     * Checks if bet slip section is visible
     * @return
     */
    boolean isBetSlipVisible() {
        try {
            new BetSlip(device, TIMEOUT2S).getBetSlip().isDisplayed()
        } catch (ignore) {
            false
        }
    }
}
