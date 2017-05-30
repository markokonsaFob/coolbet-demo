package impl.betting.actions

import impl.betting.pageobjects.BettingSection
import impl.betting.pageobjects.Creditcard
import impl.betting.pageobjects.Deposit
import io.cify.framework.core.Device

import static impl.ActionsWrapper.scrollIntoViewAndClick
import static impl.ActionsWrapper.waitForCondition
import static impl.Constants.*

trait IBetActions {

    static final String VISA = "VISA"
    Device device
    BettingSection bettingSection
    Deposit depositPopup
    Creditcard creditcard

    /**
     * Clicks on first available active bet
     */
    void clickOnFirstAvailableBet() {
        waitForCondition(device, {
            scrollIntoViewAndClick(device, new BettingSection(device, TIMEOUT2S).getAvailableBet()); true
        }, TIMEOUT10S)
    }

    /**
     * Closes right side drawer menu
     */
    abstract void closeRightDrawer()

    /**
     * Clicks deposit button
     */
    void clickDepositButton() {
        bettingSection.getDepositButton().click()
    }

    /**
     * Checks that deposit popup has appeared
     * @return true/false
     */
    boolean isDepositPopupDisplayed() {
        depositPopup.getDepositContainer().isDisplayed()
    }

    /**
     * Inserts deposit amount
     * @param amount
     */
    void insertDepositAmount(String amount) {
        depositPopup.getDepositField().sendKeys(amount)
    }

    /**
     * Clicks continue button in deposit popup
     */
    void clickDepositContinueButton() {
        try {
            waitForCondition(device, { new Deposit(device, TIMEOUT2S).getContinueButton().isDisplayed() }, TIMEOUT10S)
            new Deposit(device).getContinueButton().click()
            waitForCondition(device, { new Deposit(device, TIMEOUT2S).getContinueButton().isDisplayed() }, TIMEOUT10S)
            new Deposit(device).getContinueButton().click()
        } catch (ignore) {
        }

    }

    /**
     * Selects deposit method
     * @param method
     */
    void selectDepositMethod(String method) {
        switch (method) {
            case VISA:
                scrollIntoViewAndClick(device, depositPopup.getVisaCreditCard())
                break
            default: throw new Exception("$method is not a supported deposit method")
        }
    }

    /**
     * Inserts creditcard info
     */
    void enterCreditcardInfo() {
        if (device.getCapabilities().getCapability("capability") == "chrome") {
            waitForCondition(device, { creditcard.getDepositIFrame().isDisplayed() }, TIMEOUT10S)
            device.getDriver().switchTo().frame(creditcard.getDepositIFrame())
        }
        enterCardHolderInfo()
    }

    /**
     * Clicks next button in creditcard frame
     */
    void clickNextButton() {
        creditcard.getNextButton().click()
    }

    /**
     * Accepts cookie popup
     */
    void acceptCookiePopup() {
        new BettingSection(device, TIMEOUT2S).getCookiesOKButton().click()
    }

    /**
     * Accepts welcome banner
     */
    void closeWelcomeBanner() {
        new BettingSection(device, TIMEOUT10S).getWelcomeBannerCloseButton().click()
    }

    /**
     * Inserts credit card holder info
     */
    void enterCardHolderInfo() {
        new Creditcard(device, TIMEOUT30S).getCardHolder().sendKeys("Fob Test")
    }

    /**
     * Checks that error message has appeared in credit card frame
     * @return
     */
    boolean isCreditCardErrorDisplayed() {
        new Creditcard(device).getErrorMessage().isDisplayed()
    }

    /**
     * Opens sport page
     */
    abstract void openSportPage()
}
