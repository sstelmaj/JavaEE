/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion.Controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

public class ConfirmationDialog extends Dialog<Boolean> {
    public ConfirmationDialog(String message) {
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);
        
        setContentText(message);
        getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        
        setResultConverter(buttonType -> buttonType == ButtonType.YES);
    }
    
    public static boolean show(String message) {
        ConfirmationDialog dialog = new ConfirmationDialog(message);
        return dialog.showAndWait().orElse(false);
    }
}
