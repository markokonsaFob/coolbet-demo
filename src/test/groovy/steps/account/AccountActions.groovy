package steps.account

import cucumber.api.groovy.EN
import impl.AccountType
import impl.ActionsImpl

this.metaClass.mixin(EN)

When(~/^user logs in with (.*) account$/) { AccountType account ->
    try {
        ActionsImpl.getBettingActions().closeRightDrawer()
    }catch(ignore){}
    ActionsImpl.getAccountActions().loginWithAccount(account)
}

And(~/^user data is displayed$/) { ->
    if (!ActionsImpl.getAccountActions().isUserIconDisplayed()) {
        throw new Exception("User icon is not displayed")
    }
}
When(~/^user opens my account view$/) { ->
    ActionsImpl.getAccountActions().openMyAccountSection()
}
Then(~/^account page is displayed$/) { ->
    if (!ActionsImpl.getAccountActions().isAccountPageDisplayed()) {
        throw new Exception("Account page should be displayed but is not")
    }
}
When(~/^user opens transactions view$/) { ->
    ActionsImpl.getAccountActions().openTransactionsSection()
}
Then(~/^list of users transactions is displayed$/) { ->
    ActionsImpl.getAccountActions().isTransactionsSectionDisplayed()
}