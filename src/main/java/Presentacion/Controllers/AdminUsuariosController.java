package Presentacion.Controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import Logica.Clases.Usuario;
import Logica.Controladores.PerfilController;
import Logica.Controladores.UsuarioController;
import Presentacion.DTOs.UsuarioParaGestionar;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class AdminUsuariosController implements Initializable {

    private List<Usuario> usuariosDB;
    List<String> perfilesDB;
    private ObservableList<UsuarioParaGestionar> usuarios;
    
    @FXML
    private TableView<UsuarioParaGestionar> tableUsuarios;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuariosDB = UsuarioController.getInstance().obtenerUsuarios();
        perfilesDB = PerfilController.getInstance().obtenerNombresDePerfiles();
        initTable();
        loadData();
    }    
    
    private void initTable(){
        usuarios = FXCollections.observableArrayList();
        this.colNombres.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colApellidos.setCellValueFactory(new PropertyValueFactory("apellido"));
        this.colCorreos.setCellValueFactory(new PropertyValueFactory("mail"));
        this.colActivos.setCellValueFactory(new PropertyValueFactory("checkbox"));
        this.colPerfiles.setCellValueFactory(new PropertyValueFactory("combobox"));
    }
    
    private void loadData(){
        List<UsuarioParaGestionar> usuarios = new ArrayList<>();
        usuariosDB.forEach(user -> usuarios.add(
                new UsuarioParaGestionar(user, perfilesDB)
        ));
        
        usuarios.forEach(user -> user.setCheckboxAction((ActionEvent t) -> {
            actualizarEstadoUsuario(user);
        }));
        usuarios.forEach(user -> user.setComboboxAction((ActionEvent t) -> {
            actualizarPerfilUsuario(user);
        }));
        
        this.usuarios = FXCollections.observableArrayList(usuarios);
        this.tableUsuarios.setItems(this.usuarios);
        
    }
    
    private void actualizarEstadoUsuario(UsuarioParaGestionar usuario){
        Boolean nuevoEstado = usuario.getCheckbox().isSelected();
        UsuarioController.getInstance().actualizarEstadoUsuarioPorId(usuario.getId(), nuevoEstado);
    }
    
    private void actualizarPerfilUsuario(UsuarioParaGestionar usuario){
        String nuevoPerfil = (String) usuario.getCombobox().getValue();
        UsuarioController.getInstance().actualizarPerfilUsuarioPorId(usuario.getId(), nuevoPerfil);
        
    }
}
