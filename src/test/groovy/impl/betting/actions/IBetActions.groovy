package impl.betting.actions

import impl.ActionsWrapper
import impl.betting.pageobjects.BettingSection
import impl.betting.pageobjects.Creditcard
import impl.betting.pageobjects.Deposit
import io.cify.framework.core.Device
import org.openqa.selenium.interactions.Actions
import static impl.Constants.TIMEOUT10S

import static impl.ActionsWrapper.waitForCondition

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
        waitForCondition(device, { new BettingSection(device).getAvailableBet().click(); true }, TIMEOUT10S)
    }

    /**
     * Closes right side drawer menu
     */
    void closeRightDrawer() {
        int offset = 5
        //try {
        Actions action = new Actions(device.getDriver())
        action.moveToElement(bettingSection.getDrawerClosingOverlay(), offset, offset).click().build().perform()
        //} catch (ignore) {
        //}
    }

    /**
     * Clicks deposit button
     */
    void clickDepositButton() {
        closeRightDrawer()
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
        new Deposit(device).getContinueButton().click()

    }

    /**
     * Selects deposit method
     * @param method
     */
    void selectDepositMethod(String method) {
        switch (method) {
            case VISA:
                ActionsWrapper.scrollIntoViewAndClick(device, depositPopup.getVisaCreditCard())
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
        bettingSection.getCookiesOKButton().click()
    }

    /**
     * Accepts welcome banner
     */
    void closeWelcomeBanner() {
        bettingSection.getWelcomeBannerCloseButton().click()
    }

    /**
     * Inserts credit card holder info
     */
    void enterCardHolderInfo() {
        creditcard.getCardHolder().sendKeys("Fob Test")
    }

    /**
     * Checks that error message has appeared in credit card frame
     * @return
     */
    boolean isCreditCardErrorDisplayed() {
        println "ERROR MESSAGE:" + new Creditcard(device).getErrorMessage().getText()
        new Creditcard(device).getErrorMessage().isDisplayed()
    }

    /**
     * Opens sport page
     */
    abstract void openSportPage()
}
