package impl.betting.pageobjects

import io.cify.framework.PageObjects
import io.cify.framework.core.Device
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class Deposit extends PageObjects{

    @FindBy(css =  ".deposit-home")
    WebElement depositContainer

    @FindBy(css = "input[ng-model='vm.PaymentService.amount']")
    WebElement depositField

    @FindBy(css = ".form-footer .btn span")
    WebElement continueButton

    @FindBy(css = ".slick-list img[ng-src*='visa']")
    WebElement visaCreditCard

    Deposit(Device device) {
        super(device)
    }
}
