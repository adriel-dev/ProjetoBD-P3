/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import jdbc.model.Categoria;
import jdbc.util.ConnectionFactory;

/**
 *
 * @author Tigas
 */
public class CategoriaDAO {
    
    public List<Categoria> lerBanco(){
        
        Connection conexao = ConnectionFactory.createConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        List<Categoria> categorias = new ArrayList<>();
        
        try {
            stmt = conexao.prepareStatement("SELECT id,nome FROM categoria");
            result = stmt.executeQuery();
            
            while(result.next()){
                
                Categoria categoria = new Categoria();
                categoria.setId(result.getInt("id"));
                categoria.setNome(result.getString("nome"));
                categorias.add(categoria);
                
            }
        } catch (SQLException ex) {
            /*Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);*/
            JOptionPane.showMessageDialog(null, "Deu merda! (CategoriaDAO)");
        }finally{
            ConnectionFactory.closeConnection(conexao, stmt, result);
        }
        return categorias;
    }
    
     
     public void salva(Categoria categoria){
        Connection conexao = ConnectionFactory.createConnection();
        PreparedStatement stmt;
        try {
            String sql = "INSERT INTO categoria(nome) VALUE(?)";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, categoria.getNome());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Insert realizado!");
            ConnectionFactory.closeConnection(conexao, stmt);
        }catch (SQLException ex) {
            /*Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);*/
            JOptionPane.showMessageDialog(null, "Deu merda! (CategoriaDAO)");
        }
     }
     
     public void modifica(Categoria categoria){
        Connection conexao = ConnectionFactory.createConnection();
        PreparedStatement stmt;
        try {
            String sql = "UPDATE categoria SET nome = ? WHERE id = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, categoria.getNome());
            stmt.setInt(2, categoria.getId());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Update realizado!");
            ConnectionFactory.closeConnection(conexao, stmt);
        }catch (SQLException ex) {
            /*Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);*/
            JOptionPane.showMessageDialog(null, "Deu merda! (CategoriaDAO)");
        }
     }
     
     public void deleta(Categoria categoria){
        Connection conexao = ConnectionFactory.createConnection();
        PreparedStatement stmt;
        try {
            String sql = "DELETE FROM categoria WHERE id = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, categoria.getId());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Delete realizado!");
            ConnectionFactory.closeConnection(conexao, stmt);
        }catch (SQLException ex) {
            /*Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);*/
            JOptionPane.showMessageDialog(null, "Deu merda! (CategoriaDAO)");
        }
     }
    
}
