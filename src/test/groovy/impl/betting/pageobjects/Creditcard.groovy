package impl.betting.pageobjects

import io.cify.framework.PageObjects
import io.cify.framework.core.Device
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindAll
import org.openqa.selenium.support.FindBy

class Creditcard extends PageObjects {

    @FindBy(css = "#cardholder")
    WebElement cardHolder

    @FindBy(css = "#pan")
    WebElement cardNumber

    @FindBy(css = "#expMonth")
    WebElement expiresMonth

    @FindBy(css = "#expYear")
    WebElement expiresYear

    @FindBy(css = "#cvc")
    WebElement securityCode

    @FindBy(css = "#button_next")
    WebElement nextButton

    @FindBy(css ="#deposit-iframe")
    WebElement depositIFrame
    
    @FindAll([
        @FindBy(css = ".error_text"),
        @FindBy(css = ".content-primary .ui-body-e")
    ])
    WebElement errorMessage

    Creditcard(Device device) {
        super(device)
    }

    Creditcard(Device device, long timeout) {
        super(device, timeout)
    }
}
