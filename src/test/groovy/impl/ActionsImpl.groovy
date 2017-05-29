package impl

import impl.account.actions.IAccountActions
import impl.betslip.actions.IBetSlipActions
import impl.betting.actions.IBetActions
import impl.core.actions.ICoreActions
import io.cify.framework.Actions
import io.cify.framework.core.DeviceManager

class ActionsImpl {
    private static final String IMPLEMENTATION_PACKAGE = "impl."

    static ICoreActions getCoreActions(){
        (ICoreActions) Actions.getCustomActions(DeviceManager.getInstance().getActiveDevice(), IMPLEMENTATION_PACKAGE + "core.actions.CoreActions")
    }

    static IBetActions getBettingActions(){
        (IBetActions) Actions.getCustomActions(DeviceManager.getInstance().getActiveDevice(), IMPLEMENTATION_PACKAGE + "betting.actions.BetActions")
    }

    static IAccountActions getAccountActions(){
        (IAccountActions) Actions.getCustomActions(DeviceManager.getInstance().getActiveDevice(), IMPLEMENTATION_PACKAGE + "account.actions.AccountActions")
    }

    static IBetSlipActions getBetSlipActions(){
        (IBetSlipActions) Actions.getCustomActions(DeviceManager.getInstance().getActiveDevice(), IMPLEMENTATION_PACKAGE + "betslip.actions.BetSlipActions")
    }
}
