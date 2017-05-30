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

    //Mobile icon
    @FindBy(css = ".account .icon-user")
    WebElement loggedInIconMobile

    //Desktop icon
    @FindBy(css = ".dropdown .icon-user")
    WebElement loggedInIconDesktop

    @FindBy(css = ".dropdown-menu li")
    WebElement myAccount

    LoginPageObjects(Device device) {
        super(device)
    }

    LoginPageObjects(Device device, long timeout) {
        super(device, timeout)
    }
}
