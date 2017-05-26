package impl.account.pageobjects

import io.cify.framework.PageObjects
import io.cify.framework.core.Device
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindAll
import org.openqa.selenium.support.FindBy

class LoginPageObjects extends PageObjects {

    @FindAll([
        @FindBy(css = "button.login"),
        @FindBy(css = "a.login-btn")
    ])
    WebElement mainPageLoginButton

    @FindBy(css = "[name=email]")
    WebElement loginEmail

    @FindBy(css = "[name=password]")
    WebElement loginPassword

    @FindBy(css = ".login-form .action-btn")
    WebElement loginFormLoginButton

    @FindBy(css = ".icon-user")
    WebElement loggedInIcon

    LoginPageObjects(Device device) {
        super(device)
    }

    LoginPageObjects(Device device, int timeout) {
        super(device, timeout)
    }
}
