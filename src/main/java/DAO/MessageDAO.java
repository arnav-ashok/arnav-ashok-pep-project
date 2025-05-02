package DAO;

import Model.Message;
import Util.ConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    //Extra to see if message exists
    public boolean doesAccountExist(int posted_by){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql="SELECT COUNT(*) as count FROM account WHERE account_id=?";
            PreparedStatement statement= connection.prepareStatement(sql);
            statement.setInt(1, posted_by);
            ResultSet rs=statement.executeQuery();
            if(rs.next()){
                int count=rs.getInt("count");
                return count>0;

            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    //3. Process the creation of new messages
    public Message createNewMessage(Message m){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql= "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, m.getPosted_by());
            statement.setString(2, m.getMessage_text());
            statement.setLong(3, m.getTime_posted_epoch());
            statement.executeUpdate();
            ResultSet rs=statement.getGeneratedKeys();
            if(rs.next()){
                int generated_message_id = rs.getInt(1);
                return new Message(generated_message_id, m.getPosted_by(), m.getMessage_text(), m.getTime_posted_epoch());
            }

            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;

    }
    
    //4. Retrieve all messages
    public List<Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            String sql="SELECT*FROM message;";
            PreparedStatement statement =connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs= statement.executeQuery();
            while(rs.next()){
                Message message = new Message(
                    rs.getInt("message_id"), 
                    rs.getInt("posted_by"), 
                    rs.getString("message_text"), 
                    rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return messages;

    }

    //5. Retrieve a message by ID
    public Message getMessageByID(int message_id){
        Connection connection=ConnectionUtil.getConnection();
        try{
            String sql="SELECT*FROM message WHERE message_id=?";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, message_id);
            ResultSet rs= statement.executeQuery();
            while(rs.next()){
                Message message = new Message(
                    rs.getInt("message_id"), 
                    rs.getInt("posted_by"), 
                    rs.getString("message_text"), 
                    rs.getLong("time_posted_epoch"));
                return message;
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    //6. Delete a message identified by ID
    public boolean deleteMessageById(int message_id){
        Connection connection=ConnectionUtil.getConnection();
        try{
            String sql="DELETE FROM message WHERE message_id=?;";
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setInt(1,message_id);
            int rowsAffected=statement.executeUpdate();
            return rowsAffected>0;
        


        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    //7. Update a message text identified by ID
    public Message updateMessageById(Message m){
        Connection connection=ConnectionUtil.getConnection();
        try{
            String sql="UPDATE message SET message_text=? WHERE message_id=?;";
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setString(1,m.getMessage_text());
            statement.setInt(2,m.getMessage_id());
            int rowsAffected=statement.executeUpdate();
            if(rowsAffected>0){
                String sql1="SELECT*FROM message WHERE message_id=?;";
                PreparedStatement statement1=connection.prepareStatement(sql1);
                statement1.setInt(1, m.getMessage_id());
                ResultSet rs=statement1.executeQuery();
                while(rs.next()){
                    return new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"), rs.getLong("time_posted_epoch"));
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    //8. Retrieve all messages by an account/user
    public List<Message> getMessagesByAccountID(int account_id){
        Connection connection=ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try{
            String sql="SELECT*FROM message WHERE posted_by=?;";
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setInt(1,account_id);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                Message message = new Message(
                    rs.getInt("message_id"), 
                    rs.getInt("posted_by"), 
                    rs.getString("message_text"), 
                    rs.getLong("time_posted_epoch"));
                messages.add(message);   
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }
    
}
