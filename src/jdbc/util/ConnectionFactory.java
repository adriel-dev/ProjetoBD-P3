/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 *
 * @author Tigas
 */
public class ConnectionFactory {

    public static Connection createConnection() {
        String stringDeConexao = "jdbc:mysql://localhost:3306/projeto_final?useTimezone=true&serverTimezone=UTC";
        String usuario = "root";
        String senha = "";//coloque a senha do seu BD
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(stringDeConexao, usuario, senha);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar conexão com o BD!");
        }
        return conexao;
    }
    
    public static void closeConnection(Connection con){
        try {
            if(con != null){
                con.close();
            }
        } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt){
        closeConnection(con);
        try {
            if(stmt != null){
                stmt.close();
            }
        } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet result){
        closeConnection(con,stmt);
        try {
            if(result != null){
                result.close();
            }
        } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

}
