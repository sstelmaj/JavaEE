/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion.Controllers;

import Logica.Clases.AjustesUsuario;
import Logica.Clases.Archivo;
import Logica.Clases.Etiqueta;
import Logica.Clases.Perfil;
import Logica.Clases.Usuario;
import Logica.Controladores.EtiquetaController;
import Logica.Controladores.PerfilController;
import Logica.Controladores.UsuarioController;
import Persistencia.Conexion;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author joaco
 */
public class CrearUsuarioController implements Initializable {

    @FXML
    private TextField textCorreo;
    @FXML
    private TextField textNombre;
    @FXML
    private TextField textApellido;
    @FXML
    private TextField textPass;
    @FXML
    private TextField textPass2;
    @FXML
    private TableView<Perfil> tablaPerfiles;
    
    private ObservableList<Perfil> perfiles_;
    
    private Perfil perfil_seleccionado;
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
    private TextField textPerfil;
    @FXML
    private Button botonCancelar;
    @FXML
    private Button botonAceptar;
    @FXML
    private Button botonSeleccionarPerfil;
   
    private StringProperty tipoPantallaProperty = new SimpleStringProperty("");
    
    private String tipoPantalla ="-";
    
    private Usuario usuarioModificar;
    
    @FXML
    private Label textTitulo;
    
    private DashboardController dash;
    @FXML
    private Tooltip toolPass1;
    @FXML
    private Tooltip toolPass2;
    @FXML
    private AnchorPane root;
    
    public void setDashboard(DashboardController dash){
        this.dash = dash;
    }
    
    public StringProperty tipoPantallaProperty() {
        return tipoPantallaProperty;
    }

    public final String getTipoPantalla() {
        return tipoPantallaProperty.get();
    }

    public final void setTipoPantalla(String tipoPantalla) {
        tipoPantallaProperty.set(tipoPantalla);
    }
    
    public final void setUsuarioModificar(Usuario usuario) {
        this.usuarioModificar = usuario;
    }
    
    public AnchorPane getRoot() {
        return root;
    }
    
    private Long idtemporal;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         toolPass1.setShowDelay(Duration.millis(0));
         toolPass2.setShowDelay(Duration.millis(0));
         
        //inicializar la tabla de perfiles
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
        
        
        
         tipoPantallaProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue=="Modificar Usuario") {
                
               
                System.out.println("Modificar Usuario");
                tipoPantalla =newValue;
                textTitulo.setText(newValue);
                botonAceptar.setText("Guardar Cambios");
                
               
                if(usuarioModificar != null){
                    
                    textNombre.setText(usuarioModificar.getNombre());
                    textApellido.setText(usuarioModificar.getApellido());
                    textCorreo.setText(usuarioModificar.getMail());
                    textCorreo.setEditable(false);
                    textPass.setText(usuarioModificar.getPassword());
                    if(usuarioModificar.getPerfil()!=null){
                        textPerfil.setText(usuarioModificar.getPerfil().getNombre());
                        this.perfil_seleccionado= usuarioModificar.getPerfil();
                    }
                    idtemporal = usuarioModificar.getId();
                    
                

                }

            }
            else if(newValue=="Crear Usuario"){
                System.out.println("Crear Usuario");
                tipoPantalla =newValue;
                textTitulo.setText(newValue);
            }
              
        });
    }    
    
    //utilidad para cambiar los valores de true y false a si y no
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

    @FXML
    private void crearUsuario(ActionEvent event) {
        Usuario usuario = new Usuario();
        
        usuario.setNombre(textNombre.getText());
        usuario.setApellido(textApellido.getText());
        usuario.setMail(textCorreo.getText());
        usuario.setPassword(textPass.getText());
        if(this.perfil_seleccionado !=null){
            usuario.setPerfil(perfil_seleccionado);
        }
        
        //patron para el correo
        String patron = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        // Compilar el patrón en un objeto Pattern
        Pattern pattern = Pattern.compile(patron);

        // Crear un objeto Matcher para buscar coincidencias en el texto
        Matcher matcher = pattern.matcher(textCorreo.getText());

        // Verificar si el correo cumple con el patrón
        if (matcher.matches()) {
            System.out.println("El correo es válido.");
        } else {
            System.out.println("El correo no es válido.");
             Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Información");
                        alert.setHeaderText(null);
                        alert.setContentText("El correo no es valido!");
                        alert.showAndWait();
              textCorreo.requestFocus();
            return;
        }
        
        if(UsuarioController.getInstance().existeUsuario(textCorreo.getText())){
             Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Información");
                        alert.setHeaderText(null);
                        alert.setContentText("El usuario con ese correo ya existe!");
                        alert.showAndWait();
              textCorreo.requestFocus();
            return;
        }
        
        if(textPass.getText().equals(textPass2.getText())){
            String patronPass = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,10}$";
           

            // Verificar si la contraseña cumple con el patrón
            if (textPass.getText().matches(patronPass)) {
                System.out.println("La contraseña cumple con los requisitos.");
            } else {
                 Alert alert = new Alert(Alert.AlertType.WARNING);
                       alert.setTitle("Información");
                       alert.setHeaderText(null);
                       alert.setContentText("Una de las contraseñas no cumple con los requisitos!");
                       alert.showAndWait();
                    textPass.requestFocus();
                   return;
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
                       alert.setTitle("Información");
                       alert.setHeaderText(null);
                       alert.setContentText("Las contraseñas no coinciden!");
                       alert.showAndWait();
             textPass.requestFocus();
            return;
        }
        
        if(textPerfil.getText().equals("")){
             Alert alert = new Alert(Alert.AlertType.WARNING);
                       alert.setTitle("Información");
                       alert.setHeaderText(null);
                       alert.setContentText("Debe asignar un perfil!");
                       alert.showAndWait();
             textPass.requestFocus();
             return;
        }
        
        
        
        
        
        
        if(tipoPantalla.equals("Modificar Usuario")){
            System.out.println("Modifica el usuario");
            usuario.setId(idtemporal);
            try { 
                Conexion.getInstance().merge(usuario);
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Información");
                        alert.setHeaderText(null);
                        alert.setContentText("Se ha modificado el usuario con exito!");
                        alert.showAndWait();
                } catch (Exception e) {
                    // Manejo de la excepción
                    e.printStackTrace();
                }
        }else{
        
            try { 
                AjustesUsuario ajustes = new AjustesUsuario();
                ajustes.setFullHD(true);
                usuario.setAjustes(ajustes);
                Conexion.getInstance().merge(usuario);
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Información");
                        alert.setHeaderText(null);
                        alert.setContentText("Se ha creado el usuario con exito!");
                        alert.showAndWait();
                } catch (Exception e) {
                    // Manejo de la excepción
                    e.printStackTrace();
                }
        
        }
         
    }

    @FXML
    private void seleccionarPerfil(ActionEvent event) {
         this.perfil_seleccionado = tablaPerfiles.getSelectionModel().getSelectedItem();
         
         if(this.perfil_seleccionado != null){
             textPerfil.setText(this.perfil_seleccionado.getNombre());
         }
    }
}
