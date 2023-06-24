/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion.Componentes;

import javafx.scene.control.SkinBase;

/**
 *
 * @author paulo
 */
public class ItemNotaSkin extends SkinBase<ItemNota> {
    public ItemNotaSkin(ItemNota itemNota)  {
        super(itemNota);
        itemNota.getAnchorPane().setStyle("-fx-border-color: black; -fx-border-width: 1px;");
    }
    
}
