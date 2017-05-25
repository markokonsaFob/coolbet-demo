package impl.account.actions

import impl.account.pageobjects.LoginPageObjects
import io.cify.framework.core.Device

class AccountActionsDesktop implements IAccountActions{

    AccountActionsDesktop(Device device){
        this.device = device
        this.loginPageObjects = new LoginPageObjects(device)
    }
}
