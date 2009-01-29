/** @file RunTab.java
 *  @author Jason Michael Hogan
 *  @brief Source-file for the class RunTab
 *  @section license License
 *
 *  Copyright (C) 2008 Jason Hogan <hogan@stanford.edu>\n
 *  This file is part of the Stanford Timing Interface (STI).
 *
 *  The STI is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  The STI is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with the STI.  If not, see <http://www.gnu.org/licenses/>.
 */

package edu.stanford.atom.sti.client.gui.RunTab;

import javax.swing.JOptionPane;
import edu.stanford.atom.sti.client.comm.corba.*;
import javax.swing.table.*;

public class RunTab extends javax.swing.JPanel {

    private ExpSequence expSequenceRef = null;
    private Parser parserRef = null;
    
    private boolean running = false;
    
    public RunTab() {
        this(null, null);
    }
    /** Creates new form RunTab
     * @param ExpSeq
     * @param parser 
     */
    public RunTab(ExpSequence ExpSeq, Parser parser) {
        setExpSequence(ExpSeq);
        setParser(parser);
        initComponents();
        
        resetLoopVariablesTable();
    }

    public void setExpSequence(ExpSequence ExpSeq) {
        expSequenceRef = ExpSeq;
    }
    public void setParser(Parser parser) {
        parserRef = parser;
    }
    
    public void resetLoopVariablesTable() {

        loopVariablesTable.getModel().setDataVector(new Object[][]{},
                new String[]{"Trial", "Done"});
    }

    public void parseLoopScript() {
        
        boolean corbaError = false;
        boolean parseError = true;
        String init = "from timing import *\n\nvariables=[]\nexperiments=[]\n\n";

        // String init = "from timing import *\nfrom numpy import *\n\nvariables=[]\nexperiments=[]\n\n";

        if (!running) {
            try {
                parseError = parserRef.parseLoopScript(init + scriptTextPane.getText());
            } catch (Exception e) {
                corbaError= true;
                e.printStackTrace(System.out);
            }

            if (!parseError) {
                TRow[] parsedRowData = null;
                try {
                    parsedRowData = expSequenceRef.experiments();
                } catch (Exception e) {
                    corbaError = true;
                    e.printStackTrace(System.out);
                }
                //rowData[row][col]
                Object[][] rowData = new Object[parsedRowData.length][parsedRowData[0].val.length + 2];
                //Object[][] rowData =  new Object[parsedRowData[0].val.length + 2][parsedRowData.length];

                for (int i = 0; i < rowData.length; i++) { //for every row
                    //trial number
                    rowData[i][0] = new Integer(i + 1);
                    //done status
                    rowData[i][rowData[0].length - 1] = new Boolean(parsedRowData[i].done);
                    //variable values
                    for (int j = 1; j < rowData[0].length - 1; j++) {
                        rowData[i][j] = parsedRowData[i].val[j - 1];
                    }
                }
                
                String[] variableNames = null;
                try {
                    variableNames = expSequenceRef.variables();
                } catch (Exception e) {
                    corbaError = true;
                    e.printStackTrace(System.out);
                }
                String[] columnTitles = new String[variableNames.length + 2];
                columnTitles[0] = "Trial";
                columnTitles[columnTitles.length - 1] = "Done";

                for (int i = 1; i < columnTitles.length - 1; i++) {
                    columnTitles[i] = variableNames[i - 1];
                }

                loopVariablesTable.getModel().setDataVector(rowData,
                columnTitles);
                
            } 
            else {             //parsing error
                try {
                    JOptionPane.showMessageDialog(this,
                            parserRef.errMsg(),
                            "Parsing Error",
                            JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    corbaError = true;
                    e.printStackTrace(System.out);
                }
            }
        }
        if (corbaError) {
            JOptionPane.showMessageDialog(this,
                    "Lost connection to server.",
                    "Network Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loopPanel = new javax.swing.JPanel();
        seriesProgressBar = new javax.swing.JProgressBar();
        trialProgressBar = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        continuousCheckBox = new javax.swing.JCheckBox();
        loopCountTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        loopCountSpinner = new javax.swing.JSpinner();
        loopEditorSplitPane = new javax.swing.JSplitPane();
        scriptScrollPane = new javax.swing.JScrollPane();
        scriptTextPane = new javax.swing.JTextPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        loopVariablesTable = new edu.stanford.atom.sti.client.gui.table.STITable();
        scriptPanel = new javax.swing.JPanel();
        pythonLabel = new javax.swing.JLabel();
        variablesLabel = new javax.swing.JLabel();
        experiementsLabel = new javax.swing.JLabel();
        parseButton = new javax.swing.JButton();

        jLabel3.setText("Trial");

        jLabel4.setText("Series");

        jLabel5.setText("Loop Count");

        continuousCheckBox.setText("Continuous");

        loopCountTextField.setText("0");

        jLabel6.setText("/");

        loopCountSpinner.setOpaque(false);
        loopCountSpinner.setValue(1);

        javax.swing.GroupLayout loopPanelLayout = new javax.swing.GroupLayout(loopPanel);
        loopPanel.setLayout(loopPanelLayout);
        loopPanelLayout.setHorizontalGroup(
            loopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(loopPanelLayout.createSequentialGroup()
                        .addComponent(loopCountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loopCountSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(continuousCheckBox)
                        .addContainerGap())
                    .addComponent(trialProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                    .addComponent(seriesProgressBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)))
        );
        loopPanelLayout.setVerticalGroup(
            loopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(continuousCheckBox)
                    .addComponent(jLabel6)
                    .addComponent(loopCountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loopCountSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(trialProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(seriesProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE))
                .addContainerGap())
        );

        loopEditorSplitPane.setDividerLocation(120);
        loopEditorSplitPane.setDividerSize(8);
        loopEditorSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        loopEditorSplitPane.setMinimumSize(new java.awt.Dimension(10, 56));

        scriptTextPane.setPreferredSize(new java.awt.Dimension(6, 120));
        scriptTextPane.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                scriptTextPaneKeyTyped(evt);
            }
        });
        scriptScrollPane.setViewportView(scriptTextPane);

        loopEditorSplitPane.setLeftComponent(scriptScrollPane);

        jScrollPane1.setViewportView(loopVariablesTable);

        loopEditorSplitPane.setRightComponent(jScrollPane1);

        pythonLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        pythonLabel.setText("Python loop script");

        variablesLabel.setText(" variables = []");

        experiementsLabel.setText(" experiments = []");

        parseButton.setFont(new java.awt.Font("Tahoma", 0, 14));
        parseButton.setText("Parse");
        parseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parseButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout scriptPanelLayout = new javax.swing.GroupLayout(scriptPanel);
        scriptPanel.setLayout(scriptPanelLayout);
        scriptPanelLayout.setHorizontalGroup(
            scriptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scriptPanelLayout.createSequentialGroup()
                .addGroup(scriptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(variablesLabel)
                    .addComponent(experiementsLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(parseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(scriptPanelLayout.createSequentialGroup()
                .addComponent(pythonLabel)
                .addContainerGap(122, Short.MAX_VALUE))
        );
        scriptPanelLayout.setVerticalGroup(
            scriptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scriptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, scriptPanelLayout.createSequentialGroup()
                    .addGap(43, 43, 43)
                    .addComponent(parseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, scriptPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pythonLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(6, 6, 6)
                    .addComponent(variablesLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(experiementsLabel)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(loopEditorSplitPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 588, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scriptPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(loopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(loopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scriptPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loopEditorSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void scriptTextPaneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_scriptTextPaneKeyTyped
        // TODO add your handling code here:
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_TAB) {
            System.out.println("tab");
        }
    }//GEN-LAST:event_scriptTextPaneKeyTyped

    
    
    private void parseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parseButtonActionPerformed
        parseLoopScript();
        
}//GEN-LAST:event_parseButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JCheckBox continuousCheckBox;
    javax.swing.JLabel experiementsLabel;
    javax.swing.JLabel jLabel3;
    javax.swing.JLabel jLabel4;
    javax.swing.JLabel jLabel5;
    javax.swing.JLabel jLabel6;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JSpinner loopCountSpinner;
    javax.swing.JTextField loopCountTextField;
    javax.swing.JSplitPane loopEditorSplitPane;
    javax.swing.JPanel loopPanel;
    edu.stanford.atom.sti.client.gui.table.STITable loopVariablesTable;
    javax.swing.JButton parseButton;
    javax.swing.JLabel pythonLabel;
    javax.swing.JPanel scriptPanel;
    javax.swing.JScrollPane scriptScrollPane;
    javax.swing.JTextPane scriptTextPane;
    javax.swing.JProgressBar seriesProgressBar;
    javax.swing.JProgressBar trialProgressBar;
    javax.swing.JLabel variablesLabel;
    // End of variables declaration//GEN-END:variables

}
