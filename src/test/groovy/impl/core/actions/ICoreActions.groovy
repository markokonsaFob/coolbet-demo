package impl.core.actions

import io.cify.framework.core.Device


trait ICoreActions {

    Device device
    abstract void openBrowser()
}
