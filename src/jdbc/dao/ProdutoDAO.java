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
            stmt = conexao.prepareStatement("SELECT id,nome,descricao,qtde,preco FROM produto");
            result = stmt.executeQuery();
            
            while(result.next()){
                
                Produto produto = new Produto();
                produto.setId(result.getInt("id"));
                produto.setNome(result.getString("nome"));
                produto.setDescricao(result.getString("descricao"));
                produto.setQtde(result.getInt("qtde"));
                produto.setPreco(result.getDouble("preco"));
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
    
}
