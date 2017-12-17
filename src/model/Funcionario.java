package model;

import java.math.BigDecimal;
import java.sql.Date;

public class Funcionario {
    private int fun_funcionario_id;
    private String fun_nome;
    private Date fun_nascimento;
    private String fun_cargo;
    private BigDecimal fun_salario;
    private Date fun_criado;

    public int getFun_funcionario_id() {
        return fun_funcionario_id;
    }

    public void setFun_funcionario_id(int fun_funcionario_id) {
        this.fun_funcionario_id = fun_funcionario_id;
    }

    public String getFun_nome() {
        return fun_nome;
    }

    public void setFun_nome(String fun_nome) {
        this.fun_nome = fun_nome;
    }

    public Date getFun_nascimento() {
        return fun_nascimento;
    }

    public void setFun_nascimento(Date fun_nascimento) {
        this.fun_nascimento = fun_nascimento;
    }

    public String getFun_cargo() {
        return fun_cargo;
    }

    public void setFun_cargo(String fun_cargo) {
        this.fun_cargo = fun_cargo;
    }

    public BigDecimal getFun_salario() {
        return fun_salario;
    }

    public void setFun_salario(BigDecimal fun_salario) {
        this.fun_salario = fun_salario;
    }

    public Date getFun_criado() {
        return fun_criado;
    }

    public void setFun_criado(Date fun_criado) {
        this.fun_criado = fun_criado;
    }    
    
}
