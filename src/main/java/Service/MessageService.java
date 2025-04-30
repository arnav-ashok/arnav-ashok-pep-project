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
        return messageDAO.createMessage(m);
        
    }
    //4. Retrieve all messages
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessage();
        
    }
    //5. Retrieve a message by ID
    public Message getMessageWId(Message m) {
        return messageDAO.getMessageByID(m.getMessage_id());
        
    }
    //6. Delete a message identified by ID
    public Message deleteMessageWId(Message m) {
        return messageDAO.deleteMessageById(m);
        
    }
    
    //7. Update a message text identified by ID
    public Message updateMessageWId(Message m) {
        return messageDAO.updateMessageById(m);
        
    }
    
    //8. Retrieve all messages by an account/user
    public List<Message> retrieveMessagesById(Message m) {
        int account_id= m.getPosted_by();
        return messageDAO.getMessagesByAccountID(account_id);
        
    }
}