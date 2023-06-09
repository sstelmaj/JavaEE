/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion.Controllers;

import Logica.Clases.Solucion;
import Logica.Controladores.ErrorController;
import Logica.Controladores.SolucionController;
import Logica.Controladores.UsuarioController;
import Persistencia.Conexion;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//imports para apache.poi
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class ImportarDatosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btnError;

    @FXML
    private Button btnSolucion;
    
    @FXML
    private AnchorPane container;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnError.setOnAction(event -> importErrores());
        btnSolucion.setOnAction(event -> importSoluciones());
    }    
    
    void importErrores(){
        
        try {
            // Crear un cuadro de diálogo para seleccionar el archivo Excel a importar
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar archivo Excel");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos Excel", "*.xlsx"));

            // Obtener el archivo seleccionado por el usuario
            Stage stage = (Stage) btnError.getScene().getWindow();
            File file = fileChooser.showOpenDialog(stage);

            if (file != null) {
                // Crear un flujo de entrada para el archivo Excel
                FileInputStream fileIn = new FileInputStream(file);

                // Crear un libro de Excel a partir del archivo
                Workbook workbook = new XSSFWorkbook(fileIn);

                // Obtener la hoja de trabajo deseada del libro (por ejemplo, la primera hoja)
                Sheet sheet = workbook.getSheetAt(0);

                // Iterar sobre las filas de la hoja de trabajo
                SimpleDateFormat formatter = new SimpleDateFormat("d-M-yyyy");  
                Iterator<Row> rowIterator = sheet.iterator();
                rowIterator.next();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    
                    // Leer los datos de cada celda de la fila y procesarlos según tus necesidades
                    // Por ejemplo, puedes obtener los valores de las celdas y guardarlos en tu base de datos
                    Cell cell1 = row.getCell(0);
                    Cell cell2 = row.getCell(1);
                    Cell cell3 = row.getCell(2);
                    Cell cell4 = row.getCell(3);
                    Cell cell5 = row.getCell(4);
                    Cell cell6 = row.getCell(5);
                    Cell cell7 = row.getCell(6);
                    Cell cell8 = row.getCell(7);
                    Cell cell9 = row.getCell(8);
                    // ...

                    // Procesar los datos de las celdas
                    Double value1 = cell1.getNumericCellValue();
                    String value2 = cell2.getStringCellValue();
                    String value3 = cell3.getStringCellValue();
                    String value4 = cell4.getStringCellValue();
                    String value5 = cell5.getStringCellValue();
                    String value6 = cell6.getStringCellValue();
                    String value7 = cell7.getStringCellValue();
                    String value8 = cell8.getStringCellValue();
                    Double value9 = cell9.getNumericCellValue();
                    // ...
                    
                    // Realizar las operaciones necesarias con los datos leídos
                    // (por ejemplo, guardarlos en tu base de datos)
                    Logica.Clases.Error error = new Logica.Clases.Error();
                    error.setId(value1.longValue());
                    error.setCodigo(value2);
                    error.setConsola(value3);
                    error.setDescripcion(value4);
                    try {
                        error.setFechaSubida(formatter.parse(value5));
                    } catch (ParseException ex) {
                        Logger.getLogger(ImportarDatosController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    error.setLink(value6);
                    error.setRepositorio(value7);
                    error.setTitulo(value8);
                    error.setUsuario(UsuarioController.getInstance().obtenerUsuario(value9.longValue()));
                    
                    Conexion.getInstance().persist(error);
                    
                }

                
                // Cerrar el flujo de entrada del archivo
                fileIn.close();

                // Mostrar un mensaje de éxito
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("Los datos se han importado correctamente desde el archivo Excel.");
                alert.showAndWait();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            // Mostrar un mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Se produjo un error al importar los datos desde el archivo Excel.");
            alert.showAndWait();
        }

    }
    
    void importSoluciones(){
        try {
            // Crear un cuadro de diálogo para seleccionar el archivo Excel a importar
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar archivo Excel");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos Excel", "*.xlsx"));

            // Obtener el archivo seleccionado por el usuario
            Stage stage = (Stage) btnError.getScene().getWindow();
            File file = fileChooser.showOpenDialog(stage);

            if (file != null) {
                // Crear un flujo de entrada para el archivo Excel
                FileInputStream fileIn = new FileInputStream(file);

                // Crear un libro de Excel a partir del archivo
                Workbook workbook = new XSSFWorkbook(fileIn);

                // Obtener la hoja de trabajo deseada del libro (por ejemplo, la primera hoja)
                Sheet sheet = workbook.getSheetAt(0);

                SimpleDateFormat formatter = new SimpleDateFormat("d-M-yyyy");  
                Iterator<Row> rowIterator = sheet.iterator();
                rowIterator.next();
                // Iteracion sobre todas las filas de la hoja excel
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    
                    // Obtengo los valores de las celdas
                    Cell cell1 = row.getCell(0);
                    Cell cell2 = row.getCell(1);
                    Cell cell3 = row.getCell(2);
                    Cell cell4 = row.getCell(3);
                    Cell cell5 = row.getCell(4);
                    Cell cell6 = row.getCell(5);
                    Cell cell7 = row.getCell(6);

                    Double value1 = cell1.getNumericCellValue();
                    String value2 = cell2.getStringCellValue();
                    String value3 = cell3.getStringCellValue();
                    String value4 = cell4.getStringCellValue();
                    String value5 = cell5.getStringCellValue();
                    Double value6 = cell6.getNumericCellValue();
                    Double value7 = cell7.getNumericCellValue();
                    // ...
                    
                    // Guardo la solucion en la base de datos
                    Solucion solucion = new Solucion();
                    solucion.setId(value1.longValue());
                    solucion.setCodigo(value2);
                    solucion.setDescripcion(value3);
                    try {
                        solucion.setFechaSubida(formatter.parse(value4));
                    } catch (ParseException ex) {
                        Logger.getLogger(ImportarDatosController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    solucion.setLink(value5);
                    solucion.setPuntos(value6.intValue());
                    solucion.setUsuario(UsuarioController.getInstance().obtenerUsuario(value7.longValue()));
                    
                    Conexion.getInstance().persist(solucion);
                    
                }

                // Cerrar el flujo de entrada del archivo
                fileIn.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("Los datos se han importado correctamente desde el archivo Excel.");
                alert.showAndWait();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Se produjo un error al importar los datos desde el archivo Excel.");
            alert.showAndWait();
        }
    }
    
    // Método para cerrar la ventana emergente
    private void closePopup() {
        Stage stage = (Stage) btnError.getScene().getWindow();
        stage.close();
    }

}
