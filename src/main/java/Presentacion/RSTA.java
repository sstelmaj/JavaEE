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

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;


/**
 *
 * @author joaco
 */
public class RSTA extends javax.swing.JInternalFrame  implements ActionListener {
    private RSyntaxTextArea textArea;
    private JTextField searchField;
    private JCheckBox regexCB;
    private JCheckBox matchCaseCB;
    /**
     * Creates new form ui
     */
    public RSTA() {
        
        
    //    try {
    //       UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    //    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
    //        ex.printStackTrace();
    //    }
        
        
        
        initComponents();

        
        jPanel_CP.setLayout(new BorderLayout());
         textArea = new RSyntaxTextArea(20, 60);
        
   //    Font font = new Font("Segoe UI", Font.PLAIN, 12);
   //     textArea.setFont(font);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        //para poder abrir o colapsar metodos
        textArea.setCodeFoldingEnabled(true);
        RTextScrollPane sp = new RTextScrollPane(textArea);
        jPanel_CP.add(sp);
        
        //Creamos la tool bar, que se va a agregar al panel con las opciones de busqueda
         JToolBar toolBar = new JToolBar();
         searchField = new JTextField(30);
         toolBar.add(searchField);
         final JButton nextButton = new JButton("Find Next");
         nextButton.setActionCommand("FindNext");
         nextButton.addActionListener(this);
          toolBar.add(nextButton);
          
        searchField.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            nextButton.doClick(0);
         }
        });
        JButton prevButton = new JButton("Find Previous");
        prevButton.setActionCommand("FindPrev");
        prevButton.addActionListener(this);
        toolBar.add(prevButton);
        regexCB = new JCheckBox("WW");
        toolBar.add(regexCB);
        matchCaseCB = new JCheckBox("Match Case");
        toolBar.add(matchCaseCB);
        jPanel_CP.add(toolBar, BorderLayout.NORTH);

        
    }

    @Override
     public void actionPerformed(ActionEvent e) {

      // "FindNext" => search forward, "FindPrev" => search backward
      String command = e.getActionCommand();
      boolean forward = "FindNext".equals(command);

      // Create an object defining our search parameters.
      SearchContext context = new SearchContext();
      String text = searchField.getText();
      if (text.length() == 0) {
         return;
      }
      context.setSearchFor(text);
      context.setMatchCase(matchCaseCB.isSelected());
      //context.setRegularExpression(regexCB.isSelected());
      context.setSearchForward(forward);
      //context.setWholeWord(false);
      context.setRegularExpression(false);
      context.setWholeWord(regexCB.isSelected());
      boolean found = SearchEngine.find(textArea, context).wasFound();
      if (!found) {
         JOptionPane.showMessageDialog(this, "Text not found");
      }

   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBoxLeng = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel_CP = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setText("Copy");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setText("Modo oscuro");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setText("Paste");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBoxLeng.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIONSCRIPT", "ASSEMBLER_X86", "BBCODE", "C", "CLOJURE", "CPLUSPLUS", "CSHARP", "CSS", "DELPHI", "DTD", "FORTRAN", "GROOVY", "HTACCESS", "HTML", "JAVA", "JAVASCRIPT", "JSON", "JSP", "LATEX", "LISP", "LUA", "MAKEFILE", "MXML", "NSIS", "PERL", "PHP", "PROPERTIES_FILE", "PYTHON", "RUBY", "SAS", "SCALA", "SQL", "TCL", "UNIX_SHELL", "VISUAL_BASIC", "WINDOWS_BATCH", "XML" }));
        jComboBoxLeng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxLengActionPerformed(evt);
            }
        });

        jLabel1.setText("Lenguajes");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxLeng, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel_CP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(227, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jComboBoxLeng, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel_CP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(245, 245, 245))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here: (copy)
        String text = textArea.getText();
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here: (paste)
        
        // Creamos un objeto Clipboard que representa el portapapeles del sistema
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        // Obtenemos los datos del portapapeles y los guardamos en un objeto Transferable
        Transferable transferable = clipboard.getContents(null);

        // Verificamos si los datos son de tipo string
        if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            try {
                // Obtenemos los datos como un string
                String data = (String) transferable.getTransferData(DataFlavor.stringFlavor);

                // Insertamos el texto en el JTextArea
                textArea.insert(data, textArea.getCaretPosition());
                
             
            } catch (UnsupportedFlavorException | IOException ex) {
                System.out.println(ex);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
             try {
            Theme theme = Theme.load(getClass().getResourceAsStream(
                    "/org/fife/ui/rsyntaxtextarea/themes/dark.xml"));
            theme.apply(textArea);
           
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBoxLengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxLengActionPerformed
        // TODO add your handling code here:
        String selectedLanguage = (String)jComboBoxLeng.getSelectedItem();
        
        switch (selectedLanguage) {
        case "ACTIONSCRIPT":
            System.out.println("ActionScript");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_ACTIONSCRIPT);
            break;
        case "ASSEMBLER_X86":
            System.out.println("Assembler x86");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_ASSEMBLER_X86);
            break;
        case "BBCODE":
            System.out.println("BBCode");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_BBCODE);
            break;
        case "C":
            System.out.println("C");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C);
            break;
        case "CLOJURE":
            System.out.println("Clojure");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CLOJURE);
            break;
        case "C++":
            System.out.println("C++");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
            break;
        case "CSHARP":
            System.out.println("C#");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSHARP);
            break;
        case "CSS":
            System.out.println("CSS");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSS);
            break;
        case "DELPHI":
            System.out.println("Delphi/Pascal");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_DELPHI);
            break;
        case "DTD":
            System.out.println("DTD");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_DTD);
            break;
        case "FORTRAN":
            System.out.println("Fortran");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_FORTRAN);
            break;
        case "GROOVY":
            System.out.println("Groovy");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_GROOVY);
            break;
        case "HTACCESS":
            System.out.println(".htaccess");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTACCESS);
            break;
        case "HTML":
            System.out.println("HTML");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);
            break;
        case "JAVA":
            System.out.println("Java");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
            break;
        case "JAVASCRIPT":
            System.out.println("JavaScript");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
            break;
        case "JSON":
            System.out.println("JSON");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);
            break;
        case "JSP":
            System.out.println("JSP");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSP);
            break;
        case "LATEX":
            System.out.println("LaTeX");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LATEX);
            break;
        case "LISP":
            System.out.println("Lisp");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LISP);
            break;
        case "LUA":
            System.out.println("Lua");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LUA);
            break;
        case "MAKEFILE":
            System.out.println("Makefile");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_MAKEFILE);
            break;

        case "MXML":
        System.out.println("MXML");
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_MXML);
        break;
        
        case "NSIS":
            System.out.println("NSIS");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NSIS);
            break;
        case "PERL":
            System.out.println("Perl");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PERL);
            break;
        case "PHP":
            System.out.println("PHP");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PHP);
            break;
        case "PROPERTIES_FILE":
            System.out.println("Properties File");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PROPERTIES_FILE);
            break;
        case "PYTHON":
            System.out.println("Python");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PYTHON);
            break;
        case "RUBY":
            System.out.println("Ruby");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_RUBY);
            break;
        case "SAS":
            System.out.println("SAS");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SAS);
            break;
        case "SCALA":
            System.out.println("Scala");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SCALA);
            break;
        case "SQL":
            System.out.println("SQL");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
            break;
        case "TCL":
            System.out.println("Tcl");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_TCL);
            break;
        case "UNIX_SHELL":
            System.out.println("UNIX Shell");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_UNIX_SHELL);
            break;
        case "VISUAL_BASIC":
            System.out.println("Visual Basic");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_VISUAL_BASIC);
            break;
        case "WINDOWS_BATCH":
            System.out.println("Windows Batch");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_WINDOWS_BATCH);
            break;
        case "XML":
            System.out.println("XML");
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
            break;
        default:
            // Código para el caso por defecto o cualquier otro caso
            break;
    }
        
    }//GEN-LAST:event_jComboBoxLengActionPerformed

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
            java.util.logging.Logger.getLogger(RSTA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RSTA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RSTA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RSTA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RSTA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBoxLeng;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel_CP;
    // End of variables declaration//GEN-END:variables
}
