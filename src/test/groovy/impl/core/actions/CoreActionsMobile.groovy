package impl.core.actions

import impl.ActionsImpl
import io.cify.framework.core.Device
import io.cify.framework.core.DeviceManager
import org.openqa.selenium.Dimension

import static impl.Constants.ENVIRONMENT_URL


class CoreActionsMobile implements ICoreActions {
    CoreActionsMobile(Device device) {
        this.device = device
    }

    /**
     * Opens environment URL, closes welcome banner and cookie popup
     */
    @Override
    void openBrowser() {
        device.openBrowser(ENVIRONMENT_URL)
        if (device.getCapabilities().getCapability("capability").toString().toUpperCase() == "CHROME") {
            DeviceManager.getInstance().getActiveDevice().getDriver().manage().window().setSize(new Dimension(414, 736))
        }
        try {
            ActionsImpl.getBettingActions().closeWelcomeBanner()
        } catch (ignore) {
            println "Welcome banner did not appear this time"
        }
        try {
            ActionsImpl.getBettingActions().acceptCookiePopup()
        } catch (ignore) {
            println "Cookie popup did not appear this time"
        }

    }
}
