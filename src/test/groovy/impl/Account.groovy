package impl

import static AccountType.*

class Account {
    String userName
    String passWord
    String email
    String phoneNumber

    Account(String user, String pass, String email, String phone){
        this.userName= user
        this.passWord= pass
        this.email = email
        this.phoneNumber = phone
    }


    static Account getAccount(AccountType type){
        switch(type){
            case VALID:
                return new Account("fobtester","FobTest123", "fob.test@fob-solutions.com","56706497")
            case INVALID:
                return new Account("fobfalse","", "","")

        }
    }
}
