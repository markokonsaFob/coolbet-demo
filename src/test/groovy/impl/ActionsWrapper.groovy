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

    /**
     * Wait for vertical scroll to finish
     * @param device
     */
    static void waitForScrollToFinish(Device device) {
        waitForCondition(device, {
            Long init = ((JavascriptExecutor) DeviceManager.getInstance().getActiveDevice().getDriver()).executeScript("return window.pageXOffset;") as long
            sleep(1000)
            return init == ((JavascriptExecutor) DeviceManager.getInstance().getActiveDevice().getDriver()).executeScript("return window.pageXOffset;")
        }, TIMEOUT10S)
    }

    /**
     * Scrolls requested element into view and clicks it
     * @param device
     * @param element
     */
    static void scrollIntoViewAndClick(Device device, WebElement element) {
        ((JavascriptExecutor) DeviceManager.getInstance().getActiveDevice().getDriver()).executeScript("arguments[0].scrollIntoView(true);", element)
        waitForScrollToFinish(device)
        element.click()
    }

    /**
     * Waits until condition represented by closure returns true or timeout is reached
     * @param device
     * @param condition
     * @param timeOut
     * @return
     */
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

    /**
     * Checks if element is displayed
     * @param element WebElement is displayed
     * @return boolean
     */
    static boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed()
        } catch (ignored) {
            return false
        }
    }
}
