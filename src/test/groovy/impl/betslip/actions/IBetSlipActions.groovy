package impl.betslip.actions

import impl.TestDataManager
import impl.betslip.pageobjects.BetSlip
import impl.betting.pageobjects.BettingSection
import io.cify.framework.core.Device

import static impl.ActionsWrapper.*
import static impl.Constants.*

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
            waitForCondition(device, { new BetSlip(device, TIMEOUT2S).getBetSlipButton().click(); true }, TIMEOUT20S)
            waitForCondition(device, { new BetSlip(device, TIMEOUT2S).getBetSlip().isDisplayed() }, TIMEOUT20S)
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

    /**
     * Clicks place bet button
     */
    void clickPlaceBetButton() {
        if (isPlaceBetButtonDisabled()) {
            clickoffsChangeCheckbox()
        }
        betSlip.getPlaceBetButton().click()

    }

    /**
     * Checks that successful bet message is displayed
     */
    boolean isBetSuccessful() {
        try {
            waitForCondition(device, { isDisplayed(new BetSlip(device).getBetSuccessfulMessage()) }, TIMEOUT30S)
            String betAmount = betSlip.getOutcomeTable().last().getText()
            String messageText = betSlip.getBetdate().getText()

            TestDataManager.setTestData(CREATED, findValueFromString(messageText, "/ (.*)"))
            TestDataManager.setTestData(AMOUNT, betAmount)
            TestDataManager.setTestData(ID, findValueFromString(messageText, "BET ID: (.*?) "))
            true
        } catch (ignore) {
            false
        }
    }

    /**
     * Clicks accept odds checkbox
     */
    void clickoffsChangeCheckbox() {
        new BetSlip(device).getAcceptOddsChanges().click()
    }

    /**
     * Clicks bet confirmation button
     */
    void clickConfirmBetButton() {
        new BetSlip(device).getConfirmButton().click()
    }
}
