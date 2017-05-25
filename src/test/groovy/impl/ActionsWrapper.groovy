package impl

import io.cify.framework.core.Device
import io.cify.framework.core.DeviceManager
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.WebDriverWait

class ActionsWrapper {

    static void waitForScrollToFinish(Device device) {
        waitForCondition(device, {
            Long init = ((JavascriptExecutor) DeviceManager.getInstance().getActiveDevice().getDriver()).executeScript("return window.pageXOffset;") as long
            sleep(1000)
            return init == ((JavascriptExecutor) DeviceManager.getInstance().getActiveDevice().getDriver()).executeScript("return window.pageXOffset;")
        }, 10)
    }

    static void scrollIntoViewAndClick(Device device, WebElement element) {
        ((JavascriptExecutor) DeviceManager.getInstance().getActiveDevice().getDriver()).executeScript("arguments[0].scrollIntoView(true);", element)
        waitForScrollToFinish(device)
        element.click()
    }

    static void waitForCondition(Device device, Closure condition, int timeOut) {
        try {
            WebDriverWait w = new WebDriverWait(device.getDriver(), timeOut)
            w.until(new ExpectedCondition<Boolean>() {
                @Override
                Boolean apply(WebDriver driver) {
                    try {
                        return condition()
                    } catch (all) {
                        println("WAITFORCONDITION:$all")
                        false
                    }
                }
            })
        } catch (ignore) {
            throw new Exception("Waiting for condition failed:" + condition)
        }
    }
}
