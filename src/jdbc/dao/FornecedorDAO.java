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
import jdbc.model.Fornecedor;
import jdbc.util.ConnectionFactory;

/**
 *
 * @author Tigas
 */
public class FornecedorDAO {
    
     public List<Fornecedor> lerBanco(){
        
        Connection conexao = ConnectionFactory.createConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        List<Fornecedor> fornecedores = new ArrayList<>();
        
        try {
            stmt = conexao.prepareStatement("SELECT id,nome,descricao,cnpj FROM fornecedor");
            result = stmt.executeQuery();
            
            while(result.next()){
                
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(result.getInt("id"));
                fornecedor.setNome(result.getString("nome"));
                fornecedor.setDescricao(result.getString("descricao"));
                fornecedor.setCnpj(result.getString("cnpj"));
                fornecedores.add(fornecedor);
                
            }
        } catch (SQLException ex) {
            /*Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);*/
            JOptionPane.showMessageDialog(null, "Deu merda! (ProdutoDAO)");
        }finally{
            ConnectionFactory.closeConnection(conexao, stmt, result);
        }
        return fornecedores;
    }
    
     
     public void salva(Fornecedor fornecedor){
        Connection conexao = ConnectionFactory.createConnection();
        PreparedStatement stmt;
        try {
            String sql = "INSERT INTO fornecedor(nome,descricao,cnpj) VALUES(?,?,?)";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getDescricao());
            stmt.setString(3, fornecedor.getCnpj());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Insert realizado!");
            ConnectionFactory.closeConnection(conexao, stmt);
        }catch (SQLException ex) {
            /*Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);*/
            JOptionPane.showMessageDialog(null, "Deu merda! (FornecedorDAO)");
        }
     }
     
     public void modifica(Fornecedor fornecedor){
        Connection conexao = ConnectionFactory.createConnection();
        PreparedStatement stmt;
        try {
            String sql = "UPDATE fornecedor SET cnpj = ?,nome = ?,descricao = ? WHERE id = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, fornecedor.getCnpj()); System.out.println(fornecedor.getCnpj());
            stmt.setString(2, fornecedor.getNome()); System.out.println(fornecedor.getNome());
            stmt.setString(3, fornecedor.getDescricao()); System.out.println(fornecedor.getDescricao());
            stmt.setInt(4, fornecedor.getId()); System.out.println(fornecedor.getId());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Update realizado!");
            ConnectionFactory.closeConnection(conexao, stmt);
        }catch (SQLException ex) {
            /*Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);*/
            JOptionPane.showMessageDialog(null, "Deu merda! (FornecedorDAO)");
        }
     }
     
     public void deleta(Fornecedor fornecedor){
        Connection conexao = ConnectionFactory.createConnection();
        PreparedStatement stmt;
        try {
            String sql = "DELETE FROM fornecedor WHERE id = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, fornecedor.getId());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Delete realizado!");
            ConnectionFactory.closeConnection(conexao, stmt);
        }catch (SQLException ex) {
            /*Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);*/
            JOptionPane.showMessageDialog(null, "Deu merda! (FornecedorDAO)");
        }
     }
    
}
