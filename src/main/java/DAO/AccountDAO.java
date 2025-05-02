package DAO;

import Model.Account;
import Util.ConnectionUtil;
import java.sql.*;

public class AccountDAO {
    //Extra to find if account already exists in DB
    public boolean doesAccountExist(String username){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql="SELECT COUNT(*) as count FROM account WHERE username=?";
            PreparedStatement statement= connection.prepareStatement(sql);
            statement.setString(1, username);
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

    //1. Process new user registrations with a username/password
    public Account addAccount(Account a){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql="INSERT INTO account (username, password) VALUES (?,?);";
            PreparedStatement statement=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, a.getUsername());
            statement.setString(2, a.getPassword());
            int rowsAffected=statement.executeUpdate();
            if(rowsAffected>0){
                ResultSet keys = statement.getGeneratedKeys();
                if (keys.next()) {
                    int generatedId = keys.getInt(1);
                    return new Account(generatedId, a.getUsername(), a.getPassword());
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;

    }
    //2. Process user logins that match the DB username/password
    public Account loginAccount(Account a){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql= "SELECT*FROM account WHERE username=? AND password=?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, a.getUsername());
            statement.setString(2, a.getPassword());
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                Account account = new Account(
                    rs.getInt("account_id"), 
                    rs.getString("username"), 
                    rs.getString("password"));
                return account;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;

    }
    
}
