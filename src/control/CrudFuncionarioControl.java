/*
    Criei uma nova Classe Java e implementei o Initializable.
    Isso serve para quando a interface for aberta, chama esse método.
    Na interface que irá ser chamada, clica em Controller.
    No Controller Class você coloca o caminho dessa classe control.CrudFuncionarioControl
*/
package control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CrudFuncionarioControl implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    @FXML
    private TextField cadastrar_nome;

    @FXML
    private TableColumn<?, ?> consultar_fun_salario;

    @FXML
    private TextField atualizar_cadastro;

    @FXML
    private TableColumn<?, ?> consultar_fun_id;

    @FXML
    private TableColumn<?, ?> consultar_fun_criado;

    @FXML
    private TableView<?> consultar_tabela_funcionarios;

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
    private TableColumn<?, ?> consultar_fun_nome;

    @FXML
    private Tab atualizar;

    @FXML
    private TextField consultar_funcionario_nome;

    @FXML
    private TextField atualizar_nome;

    @FXML
    private Tab consultar;

    @FXML
    private TableColumn<?, ?> consultar_fun_nascimento;

    @FXML
    private TableColumn<?, ?> consultar_fun_cargo;

    @FXML
    private DatePicker cadastrar_nascimento;

    @FXML
    void gerenciar_abas(MouseEvent event) {

    }

    @FXML
    void cadastrar_funcionario(ActionEvent event) {

    }

    @FXML
    void cadastrar_limpar(ActionEvent event) {

    }

    @FXML
    void consultar_functionario(ActionEvent event) {

    }

    @FXML
    void deletar_funcionario(ActionEvent event) {

    }

    @FXML
    void exibir_aba_atualizar(ActionEvent event) {

    }

    @FXML
    void atualizar_funcionario(ActionEvent event) {

    }
    
}
