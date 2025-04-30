package Service;
import DAO.AccountDAO;
import Model.Account;
import java.util.List;
import java.util.ArrayList;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO=new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    //1. Process new user registrations with a username/password
    public Account addAccount(Account a) {
        if(a != null && a.getUsername() != null && a.getPassword() != null &&
        a.getUsername().length() > 0 && a.getPassword().length() > 4){
            return accountDAO.insertNewAccount(a);
        }else{
            return null;
        }
    }
    
    //2. Process user logins that match the DB username/password
    public Account loginAccount(Account a) {
        return accountDAO.accountLoginRequest(a);
    }
    //3. Process the creation of new messages
    
    //4. Retrieve all messages
    
    //5. Retrieve a message by ID
    
    //6. Delete a message identified by ID
    
    //7. Update a message text identified by ID
    
    //8. Retrieve all messages by an account/user
    
    
}