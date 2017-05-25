package steps.core

import cucumber.api.groovy.EN
import cucumber.api.groovy.Hooks
import impl.ActionsImpl
import io.cify.framework.core.DeviceCategory
import io.cify.framework.core.DeviceManager

this.metaClass.mixin(Hooks)
this.metaClass.mixin(EN)

Given(~/^home page is open$/) { ->
    DeviceManager.getInstance().createDevice(DeviceCategory.BROWSER)
    ActionsImpl.getCoreActions().openBrowser()
}
After(){
    DeviceManager.getInstance().quitAllDevices()
}