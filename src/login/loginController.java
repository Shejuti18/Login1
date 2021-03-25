/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class loginController implements Initializable {
    
   
    @FXML
    private AnchorPane pane_login;

    @FXML
    private TextField txt_username;

    @FXML
    private PasswordField txt_pass;

    @FXML
    private Button btn_login;

    @FXML
    private AnchorPane pane_signup;

    @FXML
    private TextField txt_username_up;

    @FXML
    private TextField txt_pass_up;

    @FXML
    private TextField txt_email_up;

    @FXML
    private Button btn_signup;
    
    Connection conn = null;
    ResultSet rs=null;
    PreparedStatement pst = null;
    
    
    
    public void LoginpaneShow(){
        
        pane_login.setVisible(true);
        pane_signup.setVisible(false);
        
    }
    
    public void SignuppaneShow(){
        
        pane_login.setVisible(false);
        pane_signup.setVisible(true);
        
    }
    
    @FXML
    
    private void Login (ActionEvent event ) throws Exception{
        conn = mysqlconnect.ConnectDb();
        String sql = "Select * from users where username = ? and password = ? ";
        
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,txt_username.getText());
            pst.setString(2, txt_pass.getText());
            rs= pst.executeQuery();
            
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "username and password is correct");
               
            }
            else{
                JOptionPane.showMessageDialog(null,"invalid username and password");
            }
    }catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
        
    }
    
    public void add_users(ActionEvent event) throws SQLException{
        
        conn = mysqlconnect.ConnectDb();
        String sql = "insert into users (username,password,email) values (?,?,?)";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_username_up.getText());
            pst.setString(2, txt_pass_up.getText());
            pst.setString(3, txt_email_up.getText());
            pst.execute();
            
            JOptionPane.showMessageDialog(null,"Saved");  
        }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
    }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    }
  

