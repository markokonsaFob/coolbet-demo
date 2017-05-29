package impl

import io.cify.framework.core.Device
import io.cify.framework.core.DeviceManager
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.WebDriverWait

import static impl.Constants.TIMEOUT10S
class ActionsWrapper {

    static void waitForScrollToFinish(Device device) {
        waitForCondition(device, {
            Long init = ((JavascriptExecutor) DeviceManager.getInstance().getActiveDevice().getDriver()).executeScript("return window.pageXOffset;") as long
            sleep(1000)
            return init == ((JavascriptExecutor) DeviceManager.getInstance().getActiveDevice().getDriver()).executeScript("return window.pageXOffset;")
        }, TIMEOUT10S)
    }

    static void scrollIntoViewAndClick(Device device, WebElement element) {
        ((JavascriptExecutor) DeviceManager.getInstance().getActiveDevice().getDriver()).executeScript("arguments[0].scrollIntoView(true);", element)
        waitForScrollToFinish(device)
        element.click()
    }

    static boolean waitForCondition(Device device, Closure condition, long timeOut) {
        try {
            WebDriverWait w = new WebDriverWait(device.getDriver(), timeOut)
            w.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver d) {
                    try {
                        return condition()
                    } catch (ignore) {
                        return false
                    }
                }
            })
        } catch (all) {
            throw new Exception("Waiting for condition failed: $condition, cause:$all")
        }
    }

    /**
     * Waits for condition
     * @param condition true, false
     * @param timeOut in ms
     * @return True if condition is met
     */
    static boolean waitForCondition(Device device, ExpectedCondition condition, long timeOut) {
        try {
            WebDriverWait w = new WebDriverWait(device.getDriver(), timeOut)
            w.until(condition)
        } catch (all) {
            throw new Exception("Waiting for condition failed! " + "CONDITION: " + condition + " cause " + all.message)
        }
    }
}
