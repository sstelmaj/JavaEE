/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.*;
import javax.swing.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import org.fife.ui.autocomplete.*;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;


/**
 *
 * @author joaco
 */
public class RSTA_TEXT extends javax.swing.JInternalFrame  {
    private RSyntaxTextArea textArea;
    private JTextField searchField;
    private JCheckBox regexCB;
    private JCheckBox matchCaseCB;
    /**
     * Creates new form ui
     */
    public RSTA_TEXT() {
        
        
        try {
           UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        
        
        
        initComponents();

        
        jPanel_CP.setLayout(new BorderLayout());
         textArea = new RSyntaxTextArea(20, 60);
        
      
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        //para poder abrir o colapsar metodos
        textArea.setCodeFoldingEnabled(true);
        
       CompletionProvider provider = createCompletionProvider();


      AutoCompletion ac = new AutoCompletion(provider);
      ac.setShowDescWindow(true);
    ac.setParameterAssistanceEnabled(true);
    
    ac.setAutoCompleteEnabled(true);
    ac.setAutoActivationEnabled(true);
    ac.setAutoCompleteSingleChoices(true);
    ac.setAutoActivationDelay(800);
      ac.install(textArea);
        RTextScrollPane sp = new RTextScrollPane(textArea);
        jPanel_CP.add(sp);
        
        SwingUtilities.invokeLater(() -> {
            toFront();
            requestFocus();
        });
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_CP = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel_CPLayout = new javax.swing.GroupLayout(jPanel_CP);
        jPanel_CP.setLayout(jPanel_CPLayout);
        jPanel_CPLayout.setHorizontalGroup(
            jPanel_CPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 421, Short.MAX_VALUE)
        );
        jPanel_CPLayout.setVerticalGroup(
            jPanel_CPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 321, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel_CP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(269, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(jPanel_CP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(245, 245, 245))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private CompletionProvider createCompletionProvider() {

      // A DefaultCompletionProvider is the simplest concrete implementation
      // of CompletionProvider. This provider has no understanding of
      // language semantics. It simply checks the text entered up to the
      // caret position for a match against known completions. This is all
      // that is needed in the majority of cases.
      DefaultCompletionProvider provider = new DefaultCompletionProvider();
        provider.setAutoActivationRules(true, "@");

      // Add completions for all Java keywords. A BasicCompletion is just
      // a straightforward word completion.
      provider.addCompletion(new BasicCompletion(provider, "abstract"));
      provider.addCompletion(new BasicCompletion(provider, "assert"));
      provider.addCompletion(new BasicCompletion(provider, "break"));
      provider.addCompletion(new BasicCompletion(provider, "case"));
      // ... etc ...
      provider.addCompletion(new BasicCompletion(provider, "transient"));
      provider.addCompletion(new BasicCompletion(provider, "try"));
      provider.addCompletion(new BasicCompletion(provider, "void"));
      provider.addCompletion(new BasicCompletion(provider, "volatile"));
      provider.addCompletion(new BasicCompletion(provider, "while"));

      // Add a couple of "shorthand" completions. These completions don't
      // require the input text to be the same thing as the replacement text.
      provider.addCompletion(new ShorthandCompletion(provider, "sysout",
            "System.out.println(", "System.out.println("));
      provider.addCompletion(new ShorthandCompletion(provider, "syserr",
            "System.err.println(", "System.err.println("));

      return provider;

   }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RSTA_TEXT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RSTA_TEXT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RSTA_TEXT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RSTA_TEXT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RSTA_TEXT().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel_CP;
    // End of variables declaration//GEN-END:variables
}
