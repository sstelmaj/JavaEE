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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
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
    
    @FXML
    private Button templateSoluciones;
    
    @FXML
    private Button templateErrores;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnError.setOnAction(event -> importErrores());
        btnSolucion.setOnAction(event -> importSoluciones());
        templateSoluciones.setOnAction(event -> templateSoluciones());
        templateErrores.setOnAction(event -> templateErrores());
    }    
    
    void importErrores() {
        try {
            String cadenaAviso = "";
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

                // Definir el mapeo de columnas esperado
                Map<String, Integer> columnMapping = new HashMap<>();
                columnMapping.put("ID", -1); // Asignar un valor negativo para las columnas no encontradas
                columnMapping.put("Codigo", -1);
                columnMapping.put("Descripcion", -1);
                columnMapping.put("FechaSubida", -1);
                columnMapping.put("Link", -1);
                columnMapping.put("Repositorio", -1);
                columnMapping.put("Titulo", -1);
                columnMapping.put("Usuario_Mail", -1);

                // Obtener la primera fila del archivo Excel
                Row headerRow = sheet.getRow(0);
                if (headerRow != null) {
                    // Iterar sobre las celdas de la primera fila y mapear las columnas
                    for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                        Cell cell = headerRow.getCell(i);
                        if (cell != null && cell.getCellType() == CellType.STRING) {
                            String columnName = cell.getStringCellValue();
                            if (columnMapping.containsKey(columnName)) {
                                columnMapping.put(columnName, i); // Actualizar el índice de la columna en el mapeo
                            }
                        }
                    }
                }

                // Iterar sobre las filas restantes y procesar los datos
                SimpleDateFormat formatoOriginal = new SimpleDateFormat("d/M/yyyy");
                SimpleDateFormat formatoNuevo = new SimpleDateFormat("d-M-yyyy");
                
                for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                    Row row = sheet.getRow(rowNum);

                    // Obtener los datos de las celdas según el mapeo de columnas
                    Double id = getNumericCellValue(row, columnMapping.get("ID"));
                    String codigo = getStringCellValue(row, columnMapping.get("Codigo"));
                    String descripcion = getStringCellValue(row, columnMapping.get("Descripcion"));
                    String fechaSubida = getStringCellValue(row, columnMapping.get("FechaSubida"));
                    String link = getStringCellValue(row, columnMapping.get("Link"));
                    String repositorio = getStringCellValue(row, columnMapping.get("Repositorio"));
                    String titulo = getStringCellValue(row, columnMapping.get("Titulo"));
                    String usuarioMail = getStringCellValue(row, columnMapping.get("Usuario_Mail"));
                   
                    if (usuarioMail == null){
                        usuarioMail = "";
                    }
                    if (id == null){
                        id = 0.0;
                    }
                    
                    if (ErrorController.getInstance().obtenerError(id.longValue()) != null){
                        cadenaAviso = cadenaAviso + "El error de ID: "+id+" ya existe en la Base de Datos\n";
                        continue;
                    } else if (UsuarioController.getInstance().obtenerUsuario(usuarioMail) == null) {
                        cadenaAviso = cadenaAviso + "El error de ID: "+id+" Debe de tener un usuario que lo crea\n";
                        continue;
                    }
                    
                    Logica.Clases.Error error = new Logica.Clases.Error();
                    
                    if (descripcion != null) {
                        error.setDescripcion(descripcion);
                    }
                    if (link != null){
                        error.setLink(link);
                    }
                    if (link != null){
                        error.setRepositorio(repositorio);
                    }

                    error.setId(id.longValue());
                    error.setCodigo(codigo);
                    error.setTitulo(titulo);
                    
                    error.setUsuario(UsuarioController.getInstance().obtenerUsuario(usuarioMail));
                    
                    error.setActive(Boolean.TRUE);
                    try {
                        Date fecha = formatoOriginal.parse(fechaSubida);
                        String fechaFormateada = formatoNuevo.format(fecha);
                        Date fechaFormateadaDate = formatoNuevo.parse(fechaFormateada);
                        error.setFechaSubida(fechaFormateadaDate);
                    } catch (ParseException ex) {
                        Logger.getLogger(ImportarDatosController.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
                
                if (!"".equals(cadenaAviso)) {
                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Aviso!");
                    alert.setHeaderText(null);
                    alert.setContentText(cadenaAviso);
                    alert.showAndWait();
                }
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
    
    void importSoluciones() {
        try {
            String cadenaAviso = "";
            // Crear un cuadro de diálogo para seleccionar el archivo Excel a importar
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar archivo Excel");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos Excel", "*.xlsx"));

            // Obtener el archivo seleccionado por el usuario
            Stage stage = (Stage) btnSolucion.getScene().getWindow();
            File file = fileChooser.showOpenDialog(stage);

            if (file != null) {
                // Crear un flujo de entrada para el archivo Excel
                FileInputStream fileIn = new FileInputStream(file);

                // Crear un libro de Excel a partir del archivo
                Workbook workbook = new XSSFWorkbook(fileIn);

                // Obtener la hoja de trabajo deseada del libro (por ejemplo, la primera hoja)
                Sheet sheet = workbook.getSheetAt(0);

                // Definir el mapeo de columnas esperado
                Map<String, Integer> columnMapping = new HashMap<>();
                columnMapping.put("ID", -1);
                columnMapping.put("Codigo", -1);
                columnMapping.put("Descripcion", -1);
                columnMapping.put("FechaSubida", -1);
                columnMapping.put("Link", -1);
                columnMapping.put("Puntos", -1);
                columnMapping.put("Mail Usuario", -1);
                columnMapping.put("Error ID", -1);

                // Obtener la primera fila del archivo Excel
                Row headerRow = sheet.getRow(0);
                if (headerRow != null) {
                    // Iterar sobre las celdas de la primera fila y mapear las columnas
                    for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                        Cell cell = headerRow.getCell(i);
                        if (cell != null && cell.getCellType() == CellType.STRING) {
                            String columnName = cell.getStringCellValue();
                            if (columnMapping.containsKey(columnName)) {
                                columnMapping.put(columnName, i); // Actualizar el índice de la columna en el mapeo
                            }
                        }
                    }
                }

                SimpleDateFormat formatoOriginal = new SimpleDateFormat("d/M/yyyy");
                SimpleDateFormat formatoNuevo = new SimpleDateFormat("d-M-yyyy");

                // Iterar sobre las filas restantes y procesar los datos
                for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                    Row row = sheet.getRow(rowNum);

                    // Obtener los datos de las celdas según el mapeo de columnas
                    Double id = getNumericCellValue(row, columnMapping.get("ID"));
                    String codigo = getStringCellValue(row, columnMapping.get("Codigo"));
                    String descripcion = getStringCellValue(row, columnMapping.get("Descripcion"));
                    String fechaSubida = getStringCellValue(row, columnMapping.get("FechaSubida"));
                    String link = getStringCellValue(row, columnMapping.get("Link"));
                    Double puntos = getNumericCellValue(row, columnMapping.get("Puntos"));
                    String usuarioMail = getStringCellValue(row, columnMapping.get("Mail Usuario"));
                    Double errorId = getNumericCellValue(row, columnMapping.get("Error ID"));
                    //String errorTitulo = getStringCellValue(row, columnMapping.get("Error titulo"));
                    
                    if (id == null){
                        id = 0.0;
                    }
                    if (usuarioMail == null){
                        usuarioMail = "";
                    }
                    if (errorId == null){
                        errorId = 0.0;
                    }
                    
                    if (SolucionController.getInstance().obtenerSolucion(id.longValue()) != null){
                        cadenaAviso = cadenaAviso + "La solucion de ID "+id+" ya existe en la Base de Datos\n";
                        continue;
                    } else if (ErrorController.getInstance().obtenerError(errorId.longValue()) == null){
                        cadenaAviso = cadenaAviso + "La solucion de ID "+id+" no puede asociarse al error "+errorId+" ya que este no existe en la base de datos, por favor ingrese el Error primero\n";
                        continue;
                    } else if (UsuarioController.getInstance().obtenerUsuario(usuarioMail) == null){
                        cadenaAviso = cadenaAviso + "La solucion de ID "+id+" no puede asociarse al usuario "+usuarioMail+" ya que este no existe en la base de datos, por favor ingrese el Usuario primero\n";
                        continue;
                    }
                    
                    Solucion solucion = new Solucion();
                    solucion.setId(id.longValue());
                    solucion.setCodigo(codigo);
                    
                    if (descripcion != null) {
                        solucion.setDescripcion(descripcion);
                    }
                    if (link != null){
                        solucion.setLink(link);
                    }
                    if (puntos != null){
                        solucion.setPuntos(puntos.intValue());
                    }
                    if (fechaSubida != null) {
                        try {
                            Date fecha = formatoOriginal.parse(fechaSubida);
                            String fechaFormateada = formatoNuevo.format(fecha);
                            Date fechaFormateadaDate = formatoNuevo.parse(fechaFormateada);
                            solucion.setFechaSubida(fechaFormateadaDate);
                        } catch (ParseException ex) {
                            Logger.getLogger(ImportarDatosController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    solucion.setUsuario(UsuarioController.getInstance().obtenerUsuario(usuarioMail));
                    solucion.setError(ErrorController.getInstance().obtenerError(errorId.longValue()));
                    // ...

                    Conexion.getInstance().persist(solucion);
                }

                // Cerrar el flujo de entrada del archivo
                fileIn.close();

                // Mostrar un mensaje de éxito
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("Los datos se han importado correctamente desde el archivo Excel.");
                alert.showAndWait();
                if (!"".equals(cadenaAviso)) {
                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Aviso");
                    alert.setHeaderText(null);
                    alert.setContentText(cadenaAviso);
                    alert.showAndWait();
                }
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
    
    void templateSoluciones() {
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
                alert.setContentText("Template exportado correctamente a un archivo Excel.");
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
    }
    
    void templateErrores() {
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
    }
    
    // Método para cerrar la ventana emergente
    private void closePopup() {
        Stage stage = (Stage) btnError.getScene().getWindow();
        stage.close();
    }
    
    // Método para obtener el valor numérico de una celda
    private Double getNumericCellValue(Row row, Integer columnIndex) {
        if (columnIndex != null && columnIndex >= 0 && row != null) {
            Cell cell = row.getCell(columnIndex);
            if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                return cell.getNumericCellValue();
            }
        }
        return null;
    }
    
    // Método para obtener el valor de cadena de una celda
    private String getStringCellValue(Row row, Integer columnIndex) {
        if (columnIndex != null && columnIndex >= 0 && row != null) {
            Cell cell = row.getCell(columnIndex);
            if (cell != null && cell.getCellType() == CellType.STRING) {
                return cell.getStringCellValue();
            }
        }
        return null;
    }

}
