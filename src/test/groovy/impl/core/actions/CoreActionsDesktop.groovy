package impl.core.actions

import impl.ActionsImpl
import io.cify.framework.core.Device
import io.cify.framework.core.DeviceManager
import org.openqa.selenium.Dimension

import static impl.Constants.ENVIRONMENT_URL

class CoreActionsDesktop implements ICoreActions{

    CoreActionsDesktop(Device device){
        this.device =  device
    }
    @Override
    void openBrowser() {
        device.openBrowser(ENVIRONMENT_URL)
        DeviceManager.getInstance().getActiveDevice().getDriver().manage().window().setSize(new Dimension(1920,1200))
        ActionsImpl.getBettingActions().closeWelcomeBanner()
        ActionsImpl.getBettingActions().acceptCookiePopup()
        ActionsImpl.getBettingActions().openSportPage()
    }
}
