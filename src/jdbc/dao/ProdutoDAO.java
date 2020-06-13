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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jdbc.model.Produto;
import jdbc.util.ConnectionFactory;

/**
 *
 * @author Tigas
 */
public class ProdutoDAO {
    
     public List<Produto> lerBanco(){
        
        Connection conexao = ConnectionFactory.createConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        List<Produto> produtos = new ArrayList<>();
        
        try {
            stmt = conexao.prepareStatement("SELECT id,nome,descricao,qtde,preco,terceiro,id_categoria FROM produto");
            result = stmt.executeQuery();
            
            while(result.next()){
                
                Produto produto = new Produto();
                produto.setId(result.getInt("id"));
                produto.setNome(result.getString("nome"));
                produto.setDescricao(result.getString("descricao"));
                produto.setQtde(result.getInt("qtde"));
                produto.setPreco(result.getDouble("preco"));
                produto.setTerceiro(result.getBoolean("terceiro"));
                produto.setIdCategoria(result.getInt("id_categoria"));
                produtos.add(produto);
                
            }
        } catch (SQLException ex) {
            /*Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);*/
            JOptionPane.showMessageDialog(null, "Deu merda! (ProdutoDAO)");
        }finally{
            ConnectionFactory.closeConnection(conexao, stmt, result);
        }
        return produtos;
    }
    
     
     public void salva(Produto produto){
        Connection conexao = ConnectionFactory.createConnection();
        PreparedStatement stmt;
        try {
            String sql = "INSERT INTO produto(nome,descricao,qtde,preco,id_categoria,terceiro) VALUES(?,?,?,?,?,?)";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setInt(3, produto.getQtde());
            stmt.setDouble(4, produto.getPreco());
            stmt.setInt(5, produto.getIdCategoria());
            stmt.setBoolean(6, produto.isTerceiro());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Insert realizado!");
            ConnectionFactory.closeConnection(conexao, stmt);
        }catch (SQLException ex) {
            /*Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);*/
            JOptionPane.showMessageDialog(null, "Deu merda! (ProdutoDAO)");
        }
     }
     
     public void modifica(Produto produto){
        Connection conexao = ConnectionFactory.createConnection();
        PreparedStatement stmt;
        try {
            String sql = "UPDATE produto SET nome = ?,descricao = ?,qtde = ?,preco = ?,id_categoria = ?,terceiro = ? WHERE id = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setInt(3, produto.getQtde());
            stmt.setDouble(4, produto.getPreco());
            stmt.setInt(5, produto.getIdCategoria());
            stmt.setBoolean(6, produto.isTerceiro());
            stmt.setInt(7, produto.getId());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Update realizado!");
            ConnectionFactory.closeConnection(conexao, stmt);
        }catch (SQLException ex) {
            /*Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);*/
            JOptionPane.showMessageDialog(null, "Deu merda! (ProdutoDAO)");
        }
     }
     
     public void deleta(Produto produto){
        Connection conexao = ConnectionFactory.createConnection();
        PreparedStatement stmt;
        try {
            String sql = "DELETE FROM produto WHERE id = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, produto.getId());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Delete realizado!");
            ConnectionFactory.closeConnection(conexao, stmt);
        }catch (SQLException ex) {
            /*Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);*/
            JOptionPane.showMessageDialog(null, "Deu merda! (ProdutoDAO)");
        }
     }
}
