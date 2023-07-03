package Presentacion.Controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import Logica.Clases.Perfil;
import Logica.Clases.Usuario;
import Logica.Controladores.PerfilController;
import Logica.Controladores.UsuarioController;
import Persistencia.Sesion;
import Presentacion.DTOs.UsuarioParaGestionar;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

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
    @FXML
    private Button btnCrearUsuario;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnCrearPerfil;
    @FXML
    private Button btnModificarPerfil;
    @FXML
    private AnchorPane root;
    
    private ObservableList<Perfil> perfiles_;
    
    private Perfil perfil_seleccionado;
    
    //Correo
    private String usuario_selecionado;
    @FXML
    private TableView<Perfil> tablaPerfiles;
    @FXML
    private TableColumn <Perfil,?> colNombre ;
    @FXML
    private TableColumn <Perfil,?> colCrear;
    @FXML
    private TableColumn <Perfil,?> colSubir;
    @FXML
    private TableColumn <Perfil,?> colDesactivar;
    @FXML
    private TableColumn <Perfil,?> colModificar;
    @FXML
    private Label labelPerfil;

    public AnchorPane getRoot() {
        return root;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String mailAdminLogueado = Sesion.getInstance().getUser();
        usuariosDB = UsuarioController.getInstance().obtenerUsuariosParaGestionar(mailAdminLogueado);
        perfilesDB = PerfilController.getInstance().obtenerNombresDePerfiles();
           perfiles_ = FXCollections.observableArrayList();
        //asocia las columnas con el tituo
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colCrear.setCellValueFactory(new PropertyValueFactory("crearUsuario"));
        this.colSubir.setCellValueFactory(new PropertyValueFactory("subir"));
        this.colDesactivar.setCellValueFactory(new PropertyValueFactory("desactivar"));
        this.colModificar.setCellValueFactory(new PropertyValueFactory("modificar"));
        
        List<Perfil> perfiles = PerfilController.getInstance().listaPerfiles();
        
        for (Perfil perfil : perfiles) {
           System.out.println(perfil.getNombre());
           perfiles_.add(perfil);
        }
        
         this.tablaPerfiles.setItems(perfiles_);
         
        TableColumn<Perfil, String>[] columnas = new TableColumn[] {colCrear, colSubir, colDesactivar, colModificar};

        for (TableColumn<Perfil, String> columna : columnas) {
            columna.setCellValueFactory(cellData -> {
                Perfil perfil = cellData.getValue();
                Boolean valor = obtenerValorBooleano(columna, perfil);
                String texto = convertirBooleanToString(valor);
                return new SimpleStringProperty(texto);
            });
        }
        
        tablaPerfiles.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Aquí puedes realizar las acciones necesarias con el elemento seleccionado
                System.out.println("Elemento seleccionado: " + newSelection.toString());
                this.perfil_seleccionado = newSelection;
                btnModificarPerfil.setDisable(false);
            }
        });
        
        initTable();
        loadData();
        
        tableUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Aquí puedes realizar las acciones necesarias con el elemento seleccionado
                System.out.println("Elemento seleccionado: " + newSelection.toString());
                this.usuario_selecionado = newSelection.getMail();
                btnModificar.setDisable(false);
            }
        });
        
        
        if(Sesion.getInstance().isIsFullHD()){
                tablaPerfiles.setLayoutX(1030.0);
                tableUsuarios.setLayoutX(100.0);
                tablaPerfiles.setPrefHeight(630.0);
                labelPerfil.setLayoutX(1160.0);
//                colNombre.setPrefWidth(80.0);
//                colCrear.setPrefWidth(80.0);
//                colSubir.setPrefWidth(80.0);
//                colDesactivar.setPrefWidth(80.0);
//                colModificar.setPrefWidth(80.0);
                
                tableUsuarios.setPrefHeight(630.0);
            //    tableUsuarios.setPrefWidth(920.0);
        }
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
    
    private Boolean obtenerValorBooleano(TableColumn<Perfil, String> columna, Perfil perfil) {
        if (columna == colCrear) {
            return perfil.isCrearUsuario();
        } else if (columna == colSubir) {
            return perfil.isSubir();
        } else if (columna == colDesactivar) {
            return perfil.isDesactivar();
        } else if (columna == colModificar) {
            return perfil.isModificar();
        }
        return null;
    }
    private String convertirBooleanToString(Boolean valor) {
        return valor ? "Sí" : "No";
    }
    
    private void actualizarEstadoUsuario(UsuarioParaGestionar usuario){
        Boolean nuevoEstado = usuario.getCheckbox().isSelected();
        UsuarioController.getInstance().actualizarEstadoUsuarioPorMail(usuario.getMail(), nuevoEstado);
    }
    
    private void actualizarPerfilUsuario(UsuarioParaGestionar usuario){
        String nuevoPerfil = (String) usuario.getCombobox().getValue();
        UsuarioController.getInstance().actualizarPerfilUsuarioPorMail(usuario.getMail(), nuevoPerfil);
        
    }

    @FXML
    private void crearUsuario(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/crearUsuario.fxml"));
        Parent nuevaVista = loader.load();
        CrearUsuarioController crearUsuario=(CrearUsuarioController)loader.getController();
        
        AdminDashboardRootController dashboardController = AdminDashboardRootController.getInstance();
        

        dashboardController.setControladorAnterior(this);
        dashboardController.setControladorSiguiente(crearUsuario);

        dashboardController.getAnchorPane().getChildren().setAll(nuevaVista);
    }

    @FXML
    private void modificar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/crearUsuario.fxml"));
        Parent nuevaVista = loader.load();
        CrearUsuarioController crearUsuarioController = (CrearUsuarioController)loader.getController();
         
            Usuario usuario = UsuarioController.getInstance().obtenerUsuario(this.usuario_selecionado);
            if(usuario != null){
                System.out.println("llega");
                crearUsuarioController.setUsuarioModificar(usuario);
            }else{
                System.out.println("no llega");
            }
            crearUsuarioController.setTipoPantalla("Modificar Usuario");
//                    
            AdminDashboardRootController dashboardController = AdminDashboardRootController.getInstance();
        


        dashboardController.getAnchorPane().getChildren().setAll(nuevaVista);
        
    }

    @FXML
    private void crearPerfil(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/crearPerfil.fxml"));
        Parent nuevaVista = loader.load();
        CrearPerfilController crearPerfil=(CrearPerfilController)loader.getController();
        
        AdminDashboardRootController dashboardController = AdminDashboardRootController.getInstance();
        

//        dashboardController.setControladorAnterior(this);
//        dashboardController.setControladorSiguiente(crearUsuario);

        dashboardController.getAnchorPane().getChildren().setAll(nuevaVista);
    }

    @FXML
    private void modificarPerfil(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/crearPerfil.fxml"));
        Parent nuevaVista = loader.load();
        CrearPerfilController crearPerfilController = (CrearPerfilController)loader.getController();
         
          
            crearPerfilController.setPerfilModificar(this.perfil_seleccionado);
            crearPerfilController.setTipoPantalla("Modificar Perfil");
            
            AdminDashboardRootController dashboardController = AdminDashboardRootController.getInstance();
        


        dashboardController.getAnchorPane().getChildren().setAll(nuevaVista);
        
    }
}
