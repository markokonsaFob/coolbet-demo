package steps.betting

import cucumber.api.groovy.EN
import impl.ActionsImpl

this.metaClass.mixin(EN)

When(~/^user clicks on first available bet selection$/) { ->
    ActionsImpl.getBettingActions().clickOnFirstAvailableBet()
}

When(~/^user clicks deposit button$/) { ->
    ActionsImpl.getBettingActions().clickDepositButton()
}

Then(~/^fast deposit popup appears$/) { ->
    if (!ActionsImpl.getBettingActions().isDepositPopupDisplayed()) {
        throw new Exception("Deposit popup should be disabled")
    }
}

When(~/^user enters (.*) into deposit field$/) { String amount ->
    ActionsImpl.getBettingActions().insertDepositAmount(amount)
}

And(~/^clicks continue button$/) { ->
    ActionsImpl.getBettingActions().clickDepositContinueButton()
}

And(~/^user selects deposit method (.*)/) { String method ->
    ActionsImpl.getBettingActions().selectDepositMethod(method)
}

And(~/^fills in (.*) info$/) { String creditcard ->
    ActionsImpl.getBettingActions().enterCreditcardInfo()
}

And(~/^clicks next button$/) { ->
    ActionsImpl.getBettingActions().clickNextButton()
}

Then(~/^error message is displayed$/) { ->
    if (!ActionsImpl.getBettingActions().isCreditCardErrorDisplayed()) {
        throw new Exception("Submitting credit card info should have resulted in an error message")
    }
}

And(~/^live sports betting view is selected$/) { ->
    ActionsImpl.getBettingActions().openSportPage()
}
When(~/^user checks the betslip$/) { ->
    if (!ActionsImpl.getBetSlipActions().isBetSlipVisible()) {
        ActionsImpl.getBetSlipActions().clickBetSlipMenuButton()
    }
}