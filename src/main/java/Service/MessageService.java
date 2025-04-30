package Service;
import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Account;
import Model.Message;
import java.util.List;
import java.util.ArrayList;

public class MessageService {
    private MessageDAO messageDAO;

    public MessageService(){
        messageDAO=new MessageDAO();
    }

    public MessageService(MessageDAO MessageDAO){
        this.messageDAO = MessageDAO;
    }
    
    //3. Process the creation of new messages
    public Message createNewMessage(Message m) {
        int posted_by= m.getPosted_by();
        if(m != null && m.getMessage_text() != null && m.getMessage_text().length()<=255){
            if (messageDAO.doesAccountExist(posted_by)){
                return messageDAO.createNewMessage(m);

            }
        }
        return null;
        
    }
    //4. Retrieve all messages
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
        
    }
    //5. Retrieve a message by ID
    public Message getMessageByID(Message m) {
        return messageDAO.getMessageByID(m.getMessage_id());
        
    }
    //6. Delete a message identified by ID
    public Message deleteMessageById(Message m) {
        Message deletedMessage= messageDAO.getMessageByID(m.getMessage_id());
        boolean check = messageDAO.deleteMessageById(m.getMessage_id());
        if(check && deletedMessage!=null){
            return deletedMessage;
        }else{
            return null;
        }

        
    }
    
    //7. Update a message text identified by ID
    public Message updateMessageById(Message m) {
        if (m != null && m.getMessage_text() != null && m.getMessage_text().length()<=255){
            return messageDAO.updateMessageById(m);
        }else{
            return null;
        }
        
    }
    
    //8. Retrieve all messages by an account/user
    public List<Message> getMessagesByAccountID(Message m) {
        int account_id= m.getPosted_by();
        return messageDAO.getMessagesByAccountID(account_id);
        
    }
}