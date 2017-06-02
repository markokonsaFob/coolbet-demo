package impl.betslip.pageobjects

import io.cify.framework.PageObjects
import io.cify.framework.core.Device
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class BetSlip extends PageObjects{

    @FindBy(css = ".bets-list")
    WebElement betSlip

    @FindBy(css = "button.betslip-btn")
    WebElement betSlipButton

    @FindBy(css = ".bets-list .btn")
    WebElement placeBetButton

    @FindBy(css =".odds-item.active .odds-item-odd")
    WebElement betAmount

    @FindBy(css = ".bets-list .info-msg")
    WebElement message

    @FindBy(css = "input[ng-model='selectedMarket.stake']")
    WebElement stakeField

    @FindBy(css = ".betslip-numpad button")
    List<WebElement> stakeNumpadButtons

    @FindBy(css = ".bet-receipt .header h1")
    WebElement betSuccessfulMessage

    @FindBy(css = "#acceptAnyOddsChanges")
    WebElement acceptOddsChanges

    @FindBy(css = ".bet-receipt .header span")
    WebElement betdate

    @FindBy(css = ".outcomes-table td")
    List<WebElement> outcomeTable

    BetSlip(Device device){
        super(device)
    }

    BetSlip(Device device, long timeout) {
        super(device, timeout)
    }
}
