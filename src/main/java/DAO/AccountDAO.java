package DAO;

import Model.Account;
import Model.Message;
import Util.ConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    //Extra to find if account already exists in DB
    public boolean doesAccountExist(String username){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql="SELECT COUNT(*) FROM account WHERE username=?";
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
            statement.executeUpdate();
            ResultSet pkeyResultSet = statement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_account_id = pkeyResultSet.getInt(1);
                return new Account(generated_account_id, a.getUsername(), a.getPassword());
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
