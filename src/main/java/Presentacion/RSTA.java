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
public class RSTA extends javax.swing.JInternalFrame  implements ActionListener {
    private RSyntaxTextArea textArea;
    private JTextField searchField;
    private JCheckBox regexCB;
    private JCheckBox matchCaseCB;
    /**
     * Creates new form ui
     */
    public RSyntaxTextArea getTextArea(){
        return this.textArea;
    }
    
    public void setTextAreaContenido(String contenido){
        textArea.setText(contenido);
    }
    public RSTA() {
        
        
     
        
        
        initComponents();
    
        
        
        jPanel_CP.setLayout(new BorderLayout());
     //    panel.add(jPanel_CP, constraints);
         textArea = new RSyntaxTextArea(20, 60);
        
   //    Font font = new Font("Segoe UI", Font.PLAIN, 12);
   //     textArea.setFont(font);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        
        CompletionProvider provider = createCompletionProvider();

 
        
        //para poder abrir o colapsar metodos
        textArea.setCodeFoldingEnabled(true);
        
    //    AutoCompletion ac = new AutoCompletion(provider);
    //    ac.install(textArea);
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
        
        pack();
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxLeng = new javax.swing.JComboBox<>();
        jPanel_CP = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 616));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(400, 80));

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

        jLabel1.setText("Lenguajes");

        jComboBoxLeng.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIONSCRIPT", "ASSEMBLER_X86", "BBCODE", "C", "CLOJURE", "C++", "CSHARP", "CSS", "DELPHI", "DTD", "FORTRAN", "GROOVY", "HTACCESS", "HTML", "JAVA", "JAVASCRIPT", "JSON", "JSP", "LATEX", "LISP", "LUA", "MAKEFILE", "MXML", "NSIS", "PERL", "PHP", "PROPERTIES_FILE", "PYTHON", "RUBY", "SAS", "SCALA", "SQL", "TCL", "UNIX_SHELL", "VISUAL_BASIC", "WINDOWS_BATCH", "XML" }));
        jComboBoxLeng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxLengActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxLeng, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 282, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 283, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBoxLeng, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton2)
                                .addComponent(jButton4)))))
                .addGap(558, 558, 558))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 312, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 284, Short.MAX_VALUE)))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        javax.swing.GroupLayout jPanel_CPLayout = new javax.swing.GroupLayout(jPanel_CP);
        jPanel_CP.setLayout(jPanel_CPLayout);
        jPanel_CPLayout.setHorizontalGroup(
            jPanel_CPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 565, Short.MAX_VALUE)
        );
        jPanel_CPLayout.setVerticalGroup(
            jPanel_CPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 575, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel_CP, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

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
            
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_ACTIONSCRIPT);
            break;
        case "ASSEMBLER_X86":
          
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_ASSEMBLER_X86);
            break;
        case "BBCODE":
         
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_BBCODE);
            break;
        case "C":
         
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C);
            break;
        case "CLOJURE":
            
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CLOJURE);
            break;
        case "C++":
           
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
            break;
        case "CSHARP":
           
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSHARP);
            break;
        case "CSS":
            
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSS);
            break;
        case "DELPHI":
            
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_DELPHI);
            break;
        case "DTD":
           
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_DTD);
            break;
        case "FORTRAN":
          
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_FORTRAN);
            break;
        case "GROOVY":
          
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_GROOVY);
            break;
        case "HTACCESS":
         
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTACCESS);
            break;
        case "HTML":
            
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);
            break;
        case "JAVA":
           
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
            break;
        case "JAVASCRIPT":
          
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
            break;
        case "JSON":
           
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);
            break;
        case "JSP":
          
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSP);
            break;
        case "LATEX":
          
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LATEX);
            break;
        case "LISP":
           
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LISP);
            break;
        case "LUA":
           
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LUA);
            break;
        case "MAKEFILE":
           
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_MAKEFILE);
            break;

        case "MXML":
        
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_MXML);
        break;
        
        case "NSIS":
           
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NSIS);
            break;
        case "PERL":
           
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PERL);
            break;
        case "PHP":
            
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PHP);
            break;
        case "PROPERTIES_FILE":
            
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PROPERTIES_FILE);
            break;
        case "PYTHON":
       
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PYTHON);
            break;
        case "RUBY":
            
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_RUBY);
            break;
        case "SAS":
           
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SAS);
            break;
        case "SCALA":
           
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SCALA);
            break;
        case "SQL":
           
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
            break;
        case "TCL":
           
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_TCL);
            break;
        case "UNIX_SHELL":
            
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_UNIX_SHELL);
            break;
        case "VISUAL_BASIC":
           
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_VISUAL_BASIC);
            break;
        case "WINDOWS_BATCH":
            
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_WINDOWS_BATCH);
            break;
        case "XML":
          
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
            break;
        default:
            
            break;
    }
        
    }//GEN-LAST:event_jComboBoxLengActionPerformed
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
                RSTA rsta = new RSTA();
                rsta.setVisible(true);
                rsta.pack();
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBoxLeng;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel_CP;
    // End of variables declaration//GEN-END:variables
}
