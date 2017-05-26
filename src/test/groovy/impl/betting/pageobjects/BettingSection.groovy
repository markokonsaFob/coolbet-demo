package impl.betting.pageobjects

import io.cify.framework.PageObjects
import io.cify.framework.core.Device
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class BettingSection extends PageObjects {

    @FindBy(css = "sport-matchlist .odds-item:not(.suspended):not(.line)")
    WebElement availableBet

    @FindBy(css = ".match .odds-item.active")
    WebElement selectedMatch

    By matchTitle = By.cssSelector(".match-name h2")

    @FindBy(css = ".match")
    WebElement allMatches

    @FindBy(css = ".backdrop-overflow")
    WebElement drawerClosingOverlay

    @FindBy(css = ".deposit-btn")
    WebElement depositButton

    @FindBy(css = "li .sport")
    WebElement sports

    //Mobile only
    @FindBy(css = ".m-menu")
    WebElement menuButton

    @FindBy(css = ".cookie-message .ok-btn")
    WebElement cookiesOKButton

    @FindBy(css = ".welcome-banner .ns-close")
    WebElement welcomeBannerCloseButton

    //Desktop only
    @FindBy(css = "nav a[title=Sports]")
    WebElement navigationSports

    BettingSection(Device device) {
        super(device)
    }

    BettingSection(Device device, int timeout) {
        super(device, timeout)
    }
}
