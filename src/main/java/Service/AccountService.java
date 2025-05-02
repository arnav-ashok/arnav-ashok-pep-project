package Service;
import DAO.AccountDAO;
import Model.Account;


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
        if(a != null 
        && a.getUsername() != null 
        && a.getPassword() != null 
        &&a.getUsername().length() > 0 
        && a.getPassword().length() >= 4 
        && !a.getUsername().trim().isEmpty() 
        && !a.getPassword().trim().isEmpty()){
            if (!accountDAO.doesAccountExist(a.getUsername())){
                return accountDAO.addAccount(a);

            }
        }
        return null;
    }
    
    //2. Process user logins that match the DB username/password
    public Account loginAccount(Account a) {
        if (a == null || a.getUsername() == null || a.getPassword() == null) {
            return null; 
        }else{
            return accountDAO.loginAccount(a);
        }
    }
    
    
}