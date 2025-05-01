package Controller;

import Model.Account;
import Model.Message;

import Service.AccountService;
import Service.MessageService;

import io.javalin.Javalin;
import io.javalin.http.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController(){
        this.accountService=new AccountService();
        this.messageService=new MessageService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        //app.start(8080);
        //1. Process new user registrations with a username/password
        app.post("/register", this::addAccount);
        //2. Process user logins that match the DB username/password
        app.post("/login", this::loginAccount);
        //3. Process the creation of new messages
        app.post("/messages", this::createNewMessage);
        //4. Retrieve all messages
        app.get("/messages", this::getAllMessages);
        //5. Retrieve a message by ID
        app.get("/messages/{message_id}", this::getMessageByID);
        //6. Delete a message identified by ID
        app.delete("/messages/{message_id}", this::deleteMessageById);
        //7. Update a message text identified by ID
        app.patch("/messages/{message_id}", this::updateMessageById);
        //8. Retrieve all messages by an account/user
        app.get("/accounts/{account_id}/messages", this::getMessagesByAccountID);

        return app;
    }

    //1. Process new user registrations with a username/password
    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     * In this case, we will be handling the register POST request.
     */
    private void addAccount(Context context) throws JsonProcessingException {
        ObjectMapper om= new ObjectMapper();
        Account account = om.readValue(context.body(), Account.class);
        Account addedAccount = accountService.addAccount(account);
        if(addedAccount != null){
            context.json(addedAccount);
            context.status(200);
        }else{
            context.status(400);
        }

    }

    //2. Process user logins that match the DB username/password
    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void loginAccount(Context context) throws JsonProcessingException {
        ObjectMapper om= new ObjectMapper();
        Account account = om.readValue(context.body(), Account.class);
        Account loginAccount = accountService.loginAccount(account);
        if(loginAccount != null){
            context.json(loginAccount);
            context.status(200);
        }else{
            context.status(401);
        }

    }

    //3. Process the creation of new messages
    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void createNewMessage(Context context) throws JsonProcessingException {
        ObjectMapper om= new ObjectMapper();
        Message message = om.readValue(context.body(), Message.class);
        Message createdMessage = messageService.createNewMessage(message);
        if(createdMessage != null){
            context.json(createdMessage);
            context.status(200);
        }else{
            context.status(400);
        }
    }
    //4. Retrieve all messages
    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getAllMessages(Context context) {
        List<Message> messages = messageService.getAllMessages();
        context.json(messages);
    }

    //5. Retrieve a message by ID
    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getMessageByID(Context context) throws JsonProcessingException {
        int messageId = Integer.parseInt(context.pathParam("message_id"));
        Message obtainedMessage = messageService.getMessageByID(messageId);
        if(obtainedMessage != null){
            context.json(obtainedMessage);
            context.status(200);
        }else{
            context.status(400);
        }
    }

    //6. Delete a message identified by ID
    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void deleteMessageById(Context context) {
        int messageId = Integer.parseInt(context.pathParam("message_id"));
        Message deletedMessage = messageService.deleteMessageById(messageId);
        if (deletedMessage == null) {
            context.status(200);
            context.result("");  // <--- THIS CLEARS THE BODY
        } else {
            context.json(deletedMessage);
            context.status(200);
        }
    }

    //7. Update a message text identified by ID
    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void updateMessageById(Context context) throws JsonProcessingException {
        int messageId = Integer.parseInt(context.pathParam("message_id"));
        ObjectMapper om = new ObjectMapper();
        Message incomingMessage = om.readValue(context.body(), Message.class);
        incomingMessage.setMessage_id(messageId); 
    
        Message updatedMessage = messageService.updateMessageById(incomingMessage);
        if (updatedMessage != null) {
            context.json(updatedMessage);
            context.status(200);
        } else {
            context.status(400);
        }
    }

    //8. Retrieve all messages by an account/user
    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getMessagesByAccountID(Context context) {
        int accountId = Integer.parseInt(context.pathParam("account_id"));
        List<Message> messages = messageService.getMessagesByAccountID(accountId);
        context.json(messages);
        context.status(200);
        }
    }


