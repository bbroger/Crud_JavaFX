/*
    Criei uma nova Classe Java e implementei o Initializable.
    Isso serve para quando a interface for aberta, chama esse método.
    Na interface que irá ser chamada, clica em Controller.
    No Controller Class você coloca o caminho dessa classe control.CrudFuncionarioControl
 */
package control;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Funcionario;
import model.FuncionarioDAO;

public class CrudFuncionarioControl implements Initializable {
    @FXML 
    private TabPane abas;
    
    @FXML
    private TextField cadastrar_nome;

    @FXML
    private TextField atualizar_salario;

    @FXML
    private TableView<Funcionario> consultar_tabela_funcionarios;

    @FXML
    private TableColumn<Funcionario, Integer> consultar_fun_id;

    @FXML
    private TableColumn<Funcionario, String> consultar_fun_nome;

    @FXML
    private TableColumn<Funcionario, Date> consultar_fun_nascimento;

    @FXML
    private TableColumn<Funcionario, String> consultar_fun_cargo;

    @FXML
    private TableColumn<Funcionario, BigDecimal> consultar_fun_salario;

    @FXML
    private TableColumn<Funcionario, Date> consultar_fun_criado;

    @FXML
    private DatePicker atualizar_nascimento;

    @FXML
    private TextField cadastrar_salario;

    @FXML
    private TextField atualizar_cargo;

    @FXML
    private Tab cadastrar;

    @FXML
    private TextField cadastrar_cargo;

    @FXML
    private Tab atualizar;

    @FXML
    private TextField consultar_funcionario_nome;

    @FXML
    private TextField atualizar_nome;

    @FXML
    private Tab consultar;

    @FXML
    private DatePicker cadastrar_nascimento;

    private FuncionarioDAO dao;
    
    private Funcionario funcionarioSelecionado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dao = new FuncionarioDAO();

        consultar_fun_id.setCellValueFactory(new PropertyValueFactory<>("fun_funcionario_id"));
        consultar_fun_nome.setCellValueFactory(new PropertyValueFactory<>("fun_nome"));
        consultar_fun_nascimento.setCellValueFactory(new PropertyValueFactory<>("fun_nascimento"));
        consultar_fun_cargo.setCellValueFactory(new PropertyValueFactory<>("fun_cargo"));
        consultar_fun_salario.setCellValueFactory(new PropertyValueFactory<>("fun_salario"));
        consultar_fun_criado.setCellValueFactory(new PropertyValueFactory<>("fun_criado"));
        
        consultar_functionario();

    }

    @FXML
    void gerenciar_abas() {
        if (cadastrar.isSelected() || consultar.isSelected()) {
            atualizar.setDisable(true);
            atualizar_limpar();
        }
    }

    @FXML
    void cadastrar_funcionario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setFun_nome(cadastrar_nome.getText());
        funcionario.setFun_nascimento(Date.valueOf(cadastrar_nascimento.getValue()));
        funcionario.setFun_cargo(cadastrar_cargo.getText());
        funcionario.setFun_salario(new BigDecimal(cadastrar_salario.getText()));

        try {
            dao.cadastrar(funcionario);
            exibirDialogoInformacao("Funcionario cadastrado com sucesso!");
            cadastrar_limpar();
        } catch (Exception e) {
            exibirDialogoErro("Falha ao cadastrar o funcionário");
            e.printStackTrace();
        }
    }

    @FXML
    void cadastrar_limpar() {
        cadastrar_nome.clear();
        cadastrar_nascimento.setValue(null);
        cadastrar_cargo.clear();
        cadastrar_salario.clear();
    }

    private void atualizar_limpar() {
        atualizar_nome.clear();
        atualizar_nascimento.setValue(null);
        atualizar_cargo.clear();
    }

    @FXML
    void consultar_functionario() {
        List<Funcionario> result = dao.consultar(consultar_funcionario_nome.getText());
        
        try {
            if (result.isEmpty()) {
                exibirDialogoErro("Nenhum funcionário encontrado!");
            } else {
                consultar_tabela_funcionarios.setItems(FXCollections.observableList(result));
            }
        } catch (Exception e) {
            exibirDialogoErro("Falha ao encontrar funcionário!");
            e.printStackTrace();
        }
    }

    @FXML
    void deletar_funcionario() {
        if(consultar_tabela_funcionarios.getSelectionModel().getSelectedItem() == null){
            exibirDialogoErro("Não tem funcionário selecionado");
        } else{
            if(exibirDialogoConfirmacao("Confirma a exclusão do funcinário selecionado?")){
                try {
                    dao.deletar(consultar_tabela_funcionarios.getSelectionModel().getSelectedItem().getFun_funcionario_id());
                    exibirDialogoInformacao("Deletado com sucesso");
                    consultar_functionario();
                } catch (Exception e) {
                    exibirDialogoErro("Falha ao deletar funcionário");
                    e.printStackTrace();
                }
            }
        }
        
        
    }

    @FXML
    void exibir_aba_atualizar() {
        funcionarioSelecionado = consultar_tabela_funcionarios.getSelectionModel().getSelectedItem();
        
        if (funcionarioSelecionado == null) {
            exibirDialogoErro("Não tem funcionário selecionado");
        } else{
            atualizar.setDisable(false);
            atualizar_nome.setText(funcionarioSelecionado.getFun_nome());
            atualizar_nascimento.setValue(funcionarioSelecionado.getFun_nascimento().toLocalDate());
            atualizar_cargo.setText(funcionarioSelecionado.getFun_cargo());
            atualizar_salario.setText(funcionarioSelecionado.getFun_salario().toString());
            abas.getSelectionModel().select(atualizar);
        }
    }

    @FXML
    void atualizar_funcionario() {
        funcionarioSelecionado.setFun_nome(atualizar_nome.getText());
        funcionarioSelecionado.setFun_nascimento(Date.valueOf(atualizar_nascimento.getValue()));
        funcionarioSelecionado.setFun_cargo(atualizar_cargo.getText());
        funcionarioSelecionado.setFun_salario(new BigDecimal(atualizar_salario.getText()));
        
        try {
            dao.atualizar(funcionarioSelecionado);
            exibirDialogoInformacao("Funcionário Atualizado com sucesso!");
            abas.getSelectionModel().select(consultar);
            consultar_functionario();
            atualizar.setDisable(true);
        } catch (Exception e) {
            exibirDialogoErro("Falha ao atualizar funcionário!");
            e.printStackTrace();
        }
    }

    private void exibirDialogoInformacao(String info) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(info);

        alert.showAndWait();
    }

    private void exibirDialogoErro(String erro) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(erro);

        alert.showAndWait();
    }
    
    private boolean exibirDialogoConfirmacao(String conf) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirme");
        alert.setHeaderText(null);
        alert.setContentText(conf);

        Optional<ButtonType> opcao= alert.showAndWait();
        
        if(opcao.get() == ButtonType.OK){
            return true;
        }
        
        return false;
    }

}
