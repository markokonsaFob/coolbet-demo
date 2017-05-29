package steps.core

import cucumber.api.Scenario
import cucumber.api.groovy.EN
import cucumber.api.groovy.Hooks
import impl.ActionsImpl
import io.cify.framework.core.DeviceCategory
import io.cify.framework.core.DeviceManager

this.metaClass.mixin(Hooks)
this.metaClass.mixin(EN)

Before() { Scenario scenario ->
    System.setProperty("scenario", scenario.getName() as String)
}

Given(~/^home page is open$/) { ->
    DeviceManager.getInstance().createDevice(DeviceCategory.BROWSER, System.getProperty("scenario"))
    ActionsImpl.getCoreActions().openBrowser()
}
Given(~/^home page is open on (.*)$/) { String platform ->
    DeviceManager.getInstance().createDevice(DeviceCategory.valueOf(platform.toUpperCase()), System.getProperty("scenario"))
    ActionsImpl.getCoreActions().openBrowser()
}
After() {
    DeviceManager.getInstance().quitAllDevices()
}