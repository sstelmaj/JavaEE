/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Presentacion;
import javafx.scene.control.SkinBase;
/**
 *
 * @author joaco
 */
import javafx.scene.control.SkinBase;

public class ItemListaSkin extends SkinBase<ItemLista> {

    public ItemListaSkin(ItemLista itemLista) {
        super(itemLista);
        // Configurar el aspecto del control personalizado
        // ...
        // Aplicar el estilo de borde al AnchorPane
        itemLista.getAnchorPane().setStyle("-fx-border-color: black; -fx-border-width: 1px;");
    }
}