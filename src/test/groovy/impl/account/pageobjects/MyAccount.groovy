package impl.account.pageobjects

import io.cify.framework.PageObjects
import io.cify.framework.core.Device
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class MyAccount extends PageObjects {


    @FindBy(css = ".profile-content form[name=profileForm]")
    WebElement profileForm

    @FindBy(css = ".profile-content form[name=changePswForm]")
    WebElement passwordForm

    @FindBy(css = "a[ui-sref*='root.account.transactions']")
    WebElement transactionsButton

    @FindBy(css = ".transactions-table")
    WebElement transActionsTable

    @FindBy(css = ".profile-menu a[ui-sref*='root.account.home']")
    WebElement menuProfile

    @FindBy(css = ".backdrop-overflow")
    WebElement drawerClosingOverlay

    MyAccount(Device device) {
        super(device)
    }
}
