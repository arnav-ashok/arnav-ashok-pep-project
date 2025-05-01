package Service;

import DAO.MessageDAO;
import Model.Message;
import java.util.List;

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
        if(m != null && m.getMessage_text() != null && m.getMessage_text().length()<=255&& !m.getMessage_text().trim().isEmpty()){
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
    public Message getMessageByID(int message_id) {
        return messageDAO.getMessageByID(message_id);
        
    }
    //6. Delete a message identified by ID
    public Message deleteMessageById(int message_id) {
        Message retrievedMessage= messageDAO.getMessageByID(message_id);
        if(retrievedMessage==null){
            return null;
        }
        boolean check = messageDAO.deleteMessageById(message_id);
        if(check){
            return retrievedMessage;
        }
        return null;
    }

        
    
    
    //7. Update a message text identified by ID
    public Message updateMessageById(Message m) {
        if (m != null && m.getMessage_text() != null && m.getMessage_text().length()<=255 && !m.getMessage_text().trim().isEmpty()){
            return messageDAO.updateMessageById(m);
        }else{
            return null;
        }
        
    }
    
    //8. Retrieve all messages by an account/user
    public List<Message> getMessagesByAccountID(int id) {
        return messageDAO.getMessagesByAccountID(id);
        
    }
}