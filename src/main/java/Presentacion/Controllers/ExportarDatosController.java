package Presentacion.Controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import Logica.Clases.Solucion;
import Logica.Controladores.ErrorController;
import Logica.Controladores.SolucionController;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
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
 *| 
 * @author Usuario
 */
public class ExportarDatosController implements Initializable {
    
    @FXML
    private Button btnError;

    @FXML
    private Button btnSolucion;
    
    @FXML
    private AnchorPane container;
    
    private final SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy"); 
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnError.setOnAction(event -> exportErrores());
        btnSolucion.setOnAction(event -> exportSoluciones());
    }  

    // Métodos de evento para los botones
    @FXML
    private void exportErrores() {
        
        
        List<Logica.Clases.Error> errores = ErrorController.getInstance().obtenerErrores();
        // Creo un libro excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Errores");
        // Encabezados
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Codigo");
        headerRow.createCell(2).setCellValue("Consola");
        headerRow.createCell(3).setCellValue("Descripcion");
        headerRow.createCell(4).setCellValue("FechaSubida");
        headerRow.createCell(5).setCellValue("Link");
        headerRow.createCell(6).setCellValue("Repositorio");
        headerRow.createCell(7).setCellValue("Titulo");
        headerRow.createCell(8).setCellValue("Usuario_Mail");
        headerRow.createCell(9).setCellValue("LENGUAJE");
        
        int columnWidth = 15;
         for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            sheet.setColumnWidth(i, columnWidth * 256); // 256 es el factor de escala para unidades de caracteres
        }
        // Lleno las filas
        int rowNum = 1;   
        for (Logica.Clases.Error error : errores) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(error.getId());
            row.createCell(1).setCellValue(error.getCodigo());
            row.createCell(2).setCellValue(error.getConsola());
            row.createCell(3).setCellValue(error.getDescripcion());
            row.createCell(4).setCellValue(formatter.format(error.getFechaSubida()));
            row.createCell(5).setCellValue(error.getLink());
            row.createCell(6).setCellValue(error.getRepositorio());
            row.createCell(7).setCellValue(error.getTitulo());
            row.createCell(8).setCellValue(error.getUsuario().getMail());
            row.createCell(9).setCellValue(error.getLenguaje());
        }
        // Guardar archivo excel
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Guardar archivo Excel");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos Excel", "*.xlsx"));

            Stage stage = (Stage) btnError.getScene().getWindow();
            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                FileOutputStream fileOut = null;
            try {
                // Guardar el libro de Excel en el archivo seleccionado
                fileOut = new FileOutputStream(file);
                workbook.write(fileOut);
                fileOut.close();
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("Los errores se han exportado correctamente a un archivo Excel.");
                alert.showAndWait();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ExportarDatosController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ExportarDatosController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fileOut.close();
                } catch (IOException ex) {
                    Logger.getLogger(ExportarDatosController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            }

        //CERRAR
        closePopup();
    }

    @FXML
    private void exportSoluciones() {
        
         List<Solucion> soluciones = SolucionController.getInstance().obtenerSoluciones();
        // Creo un libro excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Soluciones");
        // Encabezados
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Codigo");
        headerRow.createCell(2).setCellValue("Descripcion");
        headerRow.createCell(3).setCellValue("FechaSubida");
        headerRow.createCell(4).setCellValue("Link");
        headerRow.createCell(5).setCellValue("Puntos");
        headerRow.createCell(6).setCellValue("ID Usuario");
        headerRow.createCell(7).setCellValue("Mail Usuario");
        headerRow.createCell(8).setCellValue("Error ID");
        headerRow.createCell(9).setCellValue("Error titulo");
        headerRow.createCell(10).setCellValue("LENGUAJE");
        
        int columnWidth = 15;
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            sheet.setColumnWidth(i, columnWidth * 256); // 256 es el factor de escala para unidades de caracteres
        }
        
        // Lleno las filas
        int rowNum = 1;   
        for (Logica.Clases.Solucion solucion : soluciones) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(solucion.getId());
            row.createCell(1).setCellValue(solucion.getCodigo());
            row.createCell(2).setCellValue(solucion.getDescripcion());
            row.createCell(3).setCellValue(formatter.format(solucion.getFechaSubida()));
            row.createCell(4).setCellValue(solucion.getLink());
            row.createCell(5).setCellValue(solucion.getPuntos());
            row.createCell(6).setCellValue(solucion.getUsuario().getId());
            row.createCell(7).setCellValue(solucion.getUsuario().getMail());
            row.createCell(8).setCellValue(solucion.getError().getId());
            row.createCell(9).setCellValue(solucion.getError().getTitulo());
            row.createCell(10).setCellValue(solucion.getLenguaje());
        }
        
        // Guardar archivo excel
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Guardar archivo Excel");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos Excel", "*.xlsx"));

            Stage stage = (Stage) btnError.getScene().getWindow();
            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                FileOutputStream fileOut = null;
            try {
                // Guardar el libro de Excel en el archivo seleccionado
                fileOut = new FileOutputStream(file);
                workbook.write(fileOut);
                fileOut.close();
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("Las soluciones se han exportado correctamente a un archivo Excel.");
                alert.showAndWait();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ExportarDatosController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ExportarDatosController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fileOut.close();
                } catch (IOException ex) {
                    Logger.getLogger(ExportarDatosController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            }

        //CERRAR
        closePopup();
    }

    // Método para cerrar la ventana emergente
    private void closePopup() {
        Stage stage = (Stage) btnError.getScene().getWindow();
        stage.close();
    }

}
