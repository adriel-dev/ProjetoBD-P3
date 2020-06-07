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
import jdbc.model.Mat_Prima;
import jdbc.model.Produto;
import jdbc.util.ConnectionFactory;

/**
 *
 * @author Tigas
 */
public class Mat_PrimaDAO {
    
    public List<Mat_Prima> lerBanco(){
        
        Connection conexao = ConnectionFactory.createConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        List<Mat_Prima> matPrimaInfo = new ArrayList<>();
        
        try {
            stmt = conexao.prepareStatement("SELECT id,nome,descricao,qtde,custo FROM mat_prima");
            result = stmt.executeQuery();
            
            while(result.next()){
                
                Mat_Prima matPrima = new Mat_Prima();
                matPrima.setId(result.getInt("id"));
                matPrima.setNome(result.getString("nome"));
                matPrima.setDescricao(result.getString("descricao"));
                matPrima.setQtde(result.getInt("qtde"));
                matPrima.setCusto(result.getDouble("custo"));
                matPrimaInfo.add(matPrima);
                
            }
        } catch (SQLException ex) {
            /*Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);*/
            JOptionPane.showMessageDialog(null, "Deu merda! (Mat_PrimaDAO)");
        }finally{
            ConnectionFactory.closeConnection(conexao, stmt, result);
        }
        return matPrimaInfo;
    }
    
}
