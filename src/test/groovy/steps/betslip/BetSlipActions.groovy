package steps.betslip

import cucumber.api.groovy.EN
import impl.ActionsImpl

this.metaClass.mixin(EN)

Then(~/^betslip with the selected selection is displayed$/) { ->
    if (!ActionsImpl.getBetSlipActions().isCorrectBetSelectedInBetslip()) {
        throw new Exception("Betslip info differs from selection")
    }
}

Then(~/^filled betslip is displayed$/) { ->
    ActionsImpl.getBetSlipActions().clickBetSlipMenuButton()
    if (!ActionsImpl.getBetSlipActions().isBetSlipFilled()) {
        throw new Exception("Betslip should not be empty")
    }
}

When(~/^user enters (.*) into stake field$/) { String stake ->
    ActionsImpl.getBetSlipActions().insertStake(stake)
}

Then(~/^place bet button is disabled$/) { ->
    if (ActionsImpl.getBetSlipActions().isPlaceBetButtonDisabled()) {
        throw new Exception("Place bet button should be disabled")
    }
}