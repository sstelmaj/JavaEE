/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion.Controllers;

import Logica.Clases.Perfil;
import Logica.Controladores.EtiquetaController;
import Logica.Controladores.PerfilController;
import Persistencia.Conexion;
import Presentacion.RSTA;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JInternalFrame;

/**
 * FXML Controller class
 *
 * @author joaco
 */
public class CrearPerfilController implements Initializable {

    @FXML
    private TextField textNombre;
    @FXML
    private CheckBox checkCrear;
    @FXML
    private CheckBox checkSubir;
    @FXML
    private CheckBox checkDesactivar;
    @FXML
    private CheckBox checkModificar;
    @FXML
    private Button botonCancelar;
    @FXML
    private Button botonConfirmar;
    @FXML
    private Button botonVerificar;
    
    private StringProperty tipoPantallaProperty = new SimpleStringProperty("");
    
    private String tipoPantalla ="-";
    
    private Perfil perfilModificar;
    @FXML
    private Label textTitulo;
    
    public StringProperty tipoPantallaProperty() {
        return tipoPantallaProperty;
    }

    public final String getTipoPantalla() {
        return tipoPantallaProperty.get();
    }

    public final void setTipoPantalla(String tipoPantalla) {
        tipoPantallaProperty.set(tipoPantalla);
    }
    
    public final void setPerfilModificar(Perfil perfil) {
        this.perfilModificar = perfil;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        tipoPantallaProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue=="Modificar Perfil") {
                
               
                System.out.println("Modificar Perfil");
                tipoPantalla =newValue;
                textTitulo.setText(newValue);
                botonConfirmar.setText("Guardar Cambios");
                
               
                if(perfilModificar != null){
                    
                    textNombre.setText(perfilModificar.getNombre());
                    textNombre.setEditable(false);
                    checkCrear.setSelected(perfilModificar.isCrearUsuario());
                    checkSubir.setSelected(perfilModificar.isSubir());
                    checkModificar.setSelected(perfilModificar.isModificar());
                    checkDesactivar.setSelected(perfilModificar.isDesactivar());
                

                }

            }
            else if(newValue=="Subir Perfil"){
                System.out.println("Subir Perfil");
                tipoPantalla =newValue;
                textTitulo.setText(newValue);
            }
              
        });
        
        
    }    

    @FXML
    private void clickCancelar(ActionEvent event) {
    }

    @FXML
    private void clickIngresar(ActionEvent event) {
        if(textNombre.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("¡El nombre del perfil no puede estar vacio!");

                alert.showAndWait();
        }else{
        
            Perfil perfil = new Perfil();
            perfil.setNombre(textNombre.getText());
            perfil.setCrearUsuario(checkCrear.isSelected());
            perfil.setSubir(checkSubir.isSelected());
            perfil.setDesactivar(checkDesactivar.isSelected());
            perfil.setModificar(checkModificar.isSelected());

             try { 
                Conexion.getInstance().merge(perfil);
                } catch (Exception e) {
                    // Manejo de la excepción
                    e.printStackTrace();
                }
        }
        
    }

    @FXML
    private void clickVerificar(ActionEvent event) {
        boolean exist = PerfilController.getInstance().existePerfil(textNombre.getText());
            if(exist){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("¡Ya existe ese perfil!");

                alert.showAndWait();
            
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("El perfil no existe");

                alert.showAndWait();
            
            }
    }
    
}
