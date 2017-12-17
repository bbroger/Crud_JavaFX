package model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FuncionarioDAO {
    
    private Connection con;

    public FuncionarioDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    public void cadastrar(Funcionario funcionario){
        String sql= "INSERT INTO fun_funcionarios (fun_nome, fun_nascimento, fun_cargo, fun_salario) "
                + "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt= con.prepareStatement(sql);
            
            stmt.setString(1, funcionario.getFun_nome());
            stmt.setDate(2, funcionario.getFun_nascimento());
            stmt.setString(3, funcionario.getFun_cargo());
            stmt.setBigDecimal(4, funcionario.getFun_salario());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void atualizar(Funcionario funcionario){
        String sql= "UPDATE fun_funcionarios SET fun_nome= ?, fun_nascimento= ?, fun_cargo= ?, fun_salario= ? WHERE fun_funcionario_id= ?";
        try {
            PreparedStatement stmt= con.prepareStatement(sql);
            
            stmt.setString(1, funcionario.getFun_nome());
            stmt.setDate(2, funcionario.getFun_nascimento());
            stmt.setString(3, funcionario.getFun_cargo());
            stmt.setBigDecimal(4, funcionario.getFun_salario());
            stmt.setInt(5, funcionario.getFun_funcionario_id());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deletar(int funcionario_id){
        String sql= "DELETE FROM fun_funcionarios WHERE fun_funcionario_id = ?";
        try {
            PreparedStatement stmt= con.prepareStatement(sql);
            
            stmt.setInt(1, funcionario_id);
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Funcionario> consultar(String funcionario_nome){
        List<Funcionario> funcionarios = new ArrayList<>();
        
        String sql= "SELECT * FROM fun_funcionarios WHERE fun_nome LIKE '%"+funcionario_nome+"%'";
        try {
            PreparedStatement stmt= con.prepareStatement(sql);
            
            ResultSet rs= stmt.executeQuery();
            
            while (rs.next()) {                
                Funcionario funcionario = new Funcionario();
                
                funcionario.setFun_funcionario_id(rs.getInt("fun_funcionario_id"));
                funcionario.setFun_nome(rs.getString("fun_nome"));
                funcionario.setFun_nascimento(rs.getDate("fun_nascimento"));
                funcionario.setFun_cargo(rs.getString("fun_cargo"));
                funcionario.setFun_salario(rs.getBigDecimal("fun_salario"));
                funcionario.setFun_criado(rs.getDate("fun_criado"));
                
                funcionarios.add(funcionario);
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return funcionarios;
    }
}
