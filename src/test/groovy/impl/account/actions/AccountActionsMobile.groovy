package impl.account.actions

import impl.account.pageobjects.LoginPageObjects
import io.cify.framework.core.Device

class AccountActionsMobile implements IAccountActions{

    AccountActionsMobile (Device device){
        this.device = device
        this.loginPageObjects = new LoginPageObjects(device)
    }
}
