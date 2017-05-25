package steps.account

import cucumber.api.groovy.EN
import impl.AccountType
import impl.ActionsImpl

this.metaClass.mixin(EN)

When(~/^user logs in with (.*) account$/) { AccountType account ->
    ActionsImpl.getAccountActions().loginWithAccount(account)
}

And(~/^user data is displayed$/) { ->
    if (!ActionsImpl.getAccountActions().isUserIconDisplayed()) {
        throw new Exception("User icon is not displayed")
    }
}