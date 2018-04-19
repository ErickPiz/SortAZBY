package com.pizware.principal;

import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author Erick Piz
 */
public class Principal extends javax.swing.JFrame {

    public Principal() {
        initComponents();

    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaWords = new javax.swing.JTextArea();
        btnAcept = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaOutput = new javax.swing.JTextArea();
        rbtnEnter = new javax.swing.JRadioButton();
        rbtnComma = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Write the words here:");

        txaWords.setColumns(10);
        txaWords.setRows(5);
        jScrollPane1.setViewportView(txaWords);

        btnAcept.setText("Accept");
        btnAcept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptActionPerformed(evt);
            }
        });

        txaOutput.setEditable(false);
        txaOutput.setColumns(20);
        txaOutput.setRows(5);
        jScrollPane2.setViewportView(txaOutput);

        buttonGroup.add(rbtnEnter);
        rbtnEnter.setText("Enter");

        buttonGroup.add(rbtnComma);
        rbtnComma.setSelected(true);
        rbtnComma.setText("comma \",\"");

        jLabel2.setText("Select the word separation method:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(btnAcept)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(rbtnEnter)
                                .addGap(18, 18, 18)
                                .addComponent(rbtnComma)))
                        .addGap(0, 249, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(rbtnEnter)
                    .addComponent(rbtnComma))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAcept)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptActionPerformed
        
        separator = getSeparator();//returns the seperator character

        String[] words = txaWords.getText().toLowerCase().split(separator);//converts the string into substrings and transforms the small characters to save them in an array
        int length = words.length;//saves the array's length
        trim(words);//removes spaces in the elements
        Arrays.sort(words);//sorts the elements
        char ch = words[0].trim().charAt(0);//saves the first char of the element
        ArrayList<Integer> group = new ArrayList<Integer>();//saves the position of every group of elements
        group.add(0);//saves he first group's position

        //saves the other positions of the groups
        for (int i = 0; i < length; i++) {
            if (words[i].trim().charAt(0) != ch) {
                group.add(i);//adds the new beginning of the group
                ch = words[i].trim().charAt(0);//updates the comparator character
            }
        }
        int flag = 0;//indicates where the concatenation will continue (0 = beginning 1= end)
        while (group.size() > 0) {//until the elements of the group are finished the program will continue to concatenate
            if (flag == 0) {
                if (group.size() > 2) {//If only one element remains, just adds it if not complete the cycle to add the full group
                    for (int i = group.get(0); i < group.get(1); i++) {//takes as reference the beginning of the first group and the beginning of the second group to finalize
                        addString(words[i].trim());//concatenates the group
                    }
                } else {
                    addString(words[group.get(0)].trim());//concatenates the group
                }
                group.remove(0);//remove the last group used
                flag = 1;// change the flag
            } else {
                for (int i = group.get(group.size() - 1); i < length; i++) {//takes as reference the beginning of the last group and the end of it to end the concatenation
                    addString(words[i].trim());//concatenates the group
                }
                length = group.get(group.size() - 1);//change the end of the new group by the beginning of the last group
                group.remove(group.size() - 1);//remove the last group used
                flag = 0;// change the flag
            }
        }
        txaOutput.setText("");//cleans the output text area
        txaOutput.setText(sortedWords);//print the result on the text area
    }//GEN-LAST:event_btnAceptActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAcept;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rbtnComma;
    private javax.swing.JRadioButton rbtnEnter;
    private javax.swing.JTextArea txaOutput;
    private javax.swing.JTextArea txaWords;
    // End of variables declaration//GEN-END:variables
    //Variables
    private String separator;
    String sortedWords = "";//saves the output string

    //identifies the type of separator that will be used
    private String getSeparator() {
        if (rbtnEnter.isSelected()) {
            return "\n";
        } else {
            return ",";
        }
    }

    //returns the array without spaces in the elements
    private void trim(String[] words) {
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].trim();
        }
    }
    
    //performs concatenation depending on the separator
    private void addString(String str) {
        if (rbtnEnter.isSelected()) {//select the concatenation method
            sortedWords = sortedWords + str + "\n";
        } else {
            sortedWords = sortedWords + str + ", ";
        }
    }
}
