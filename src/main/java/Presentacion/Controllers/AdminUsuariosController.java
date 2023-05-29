package Presentacion.Controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import Logica.Clases.Usuario;
import Logica.Controladores.UsuarioController;
import Presentacion.DTOs.UsuarioWithCheckbox;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class AdminUsuariosController implements Initializable {

    
    private ObservableList<UsuarioWithCheckbox> usuarios;
    
    @FXML
    private Text txtTitle;
    @FXML
    private TableView<UsuarioWithCheckbox> tableUsuarios;
    @FXML
    private TableColumn<?, ?> colApellidos;
    @FXML
    private TableColumn<?, ?> colNombres;
    @FXML
    private TableColumn<?, ?> colCorreos;
    @FXML
    private TableColumn<?, ?> colActivos;
    @FXML
    private TableColumn<?, ?> colPerfiles;
    @FXML
    private Button btnSave;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Usuario> usuariosDB = UsuarioController.getInstance().obtenerUsuarios();
        initTable();
        loadData();
    }    
    
    private void initTable(){
        usuarios = FXCollections.observableArrayList();
        this.colNombres.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colApellidos.setCellValueFactory(new PropertyValueFactory("apellido"));
        this.colCorreos.setCellValueFactory(new PropertyValueFactory("mail"));
        this.colActivos.setCellValueFactory(new PropertyValueFactory("checkbox"));
    }
    
    private void loadData(){
        List<UsuarioWithCheckbox> usuarios = new ArrayList<>();
        
        List<Usuario> usuariosDB = UsuarioController.getInstance().obtenerUsuarios();
        usuariosDB.forEach(user -> usuarios.add(new UsuarioWithCheckbox(user)));
        usuarios.forEach(user -> user.setCheckboxAction((ActionEvent t) -> {
            // EVENTO AL CAMBIAR EL ESTADO ACTIVO. 
            // Pensar en guardar otra copia de los usuarios, con los cambios de estado y perfil y luego hacer un bulk save.
        }));
        
        this.usuarios = FXCollections.observableArrayList(usuarios);
        this.tableUsuarios.setItems(this.usuarios);
        
    }

    @FXML
    private void saveChanges() {
        //DE DONDE OBTENGO EL NUEVO USUARIO ?
        //UsuarioController.getInstance().actualizarUsuario(usuario);
        
        
    }
}
