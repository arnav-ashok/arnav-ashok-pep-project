package Controller;

import Model.Account;
import Model.Message;

import Service.AccountService;
import Service.MessageService;

import io.javalin.Javalin;
import io.javalin.http.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.start(8080);
        //1. Process new user registrations with a username/password
        app.post("/register", this::exampleHandler);
        //2. Process user logins that match the DB username/password
        app.post("/login", this::exampleHandler);
        //3. Process the creation of new messages
        app.post("/messages", this::exampleHandler);
        //4. Retrieve all messages
        app.get("/messages", this::exampleHandler);
        //5. Retrieve a message by ID
        app.get("/messages/{message_id}", this::exampleHandler);
        //6. Delete a message identified by ID
        app.delete("/messages/{message_id}", this::exampleHandler);
        //7. Update a message text identified by ID
        app.patch("/messages/{message_id}", this::exampleHandler);
        //8. Retrieve all messages by an account/user
        app.get("/accounts/{account_id}/messages", this::exampleHandler);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    //1. Process new user registrations with a username/password
    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     * In this case, we will be handling the register POST request.
     */
    private void registerHandler(Context context) throws JsonProcessingException {
        ObjectMapper om= new ObjectMapper();
        Account account = om.readValue(context.body(), Account.class);
        context.json("sample text");
    }

    //2. Process user logins that match the DB username/password
    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    //3. Process the creation of new messages
    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    //4. Retrieve all messages
    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    //5. Retrieve a message by ID
    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    //6. Delete a message identified by ID
    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    //7. Update a message text identified by ID
    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    //8. Retrieve all messages by an account/user
    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }


}