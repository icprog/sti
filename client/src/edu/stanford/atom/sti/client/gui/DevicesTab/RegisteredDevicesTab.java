/** @file RegisteredDevicesTab.java
 *  @author Jason Michael Hogan
 *  @brief Source-file for the class RegisteredDevicesTab
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

package edu.stanford.atom.sti.client.gui.DevicesTab;

import edu.stanford.atom.sti.client.comm.bl.DeviceManagerListener;
import edu.stanford.atom.sti.client.comm.bl.DeviceManagerEvent;
import  javax.swing.SwingUtilities;
import edu.stanford.atom.sti.client.comm.bl.DeviceManager;

public class RegisteredDevicesTab extends javax.swing.JPanel implements DeviceManagerListener, DeviceTabListener {

    private DeviceManager deviceManager = null;
    private java.lang.Thread refreshThread = null;
    private boolean initFinished = false;

    public RegisteredDevicesTab(){
       initComponents();
       initFinished = true;
    }

    public void tabTitleChanged(int index, String title) {
        deviceTabbedPane.setTitleAt(index, title);
    }
   
    public void setDeviceManager(DeviceManager deviceManager) {
        this.deviceManager = deviceManager;
    }
    public void refreshDevices(DeviceManagerEvent event) {
        if( event.getEventType().equals(DeviceManagerEvent.DeviceEventType.StartRefresh) ) {
            stopRefreshingButton.setEnabled(true);

            //This updates the GUI and so it must use invokeLater to execute 
            //on the event dispatch thread after all repainting is finished.
            SwingUtilities.invokeLater(new Runnable() {

                public void run() {
                    deviceRefreshingBar.setIndeterminate(true);
                }
            });

        }
        if( event.getEventType().equals(DeviceManagerEvent.DeviceEventType.StopRefresh) ) {
            stopRefreshingButton.setEnabled(false);

            //This updates the GUI and so it must use invokeLater to execute
            //on the event dispatch thread after all repainting is finished.
            SwingUtilities.invokeLater(new Runnable() {

                public void run() {
                    deviceRefreshingBar.setIndeterminate(false);
                }
            });
        }       
    }
    public void addDevice(DeviceManagerEvent event) {
        deviceTabbedPane.addTab( event.getDeviceName(), event.getDevice() );
        event.getDevice().addDeviceTabListener(this);
    }
    public void removeDevice(DeviceManagerEvent event) {
        deviceTabbedPane.removeTabAt( event.getDevice().getTabIndex() );
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        refreshButton = new javax.swing.JButton();
        stopRefreshingButton = new javax.swing.JButton();
        deviceRefreshingBar = new javax.swing.JProgressBar();
        deviceTabbedPane = new javax.swing.JTabbedPane();

        setMinimumSize(new java.awt.Dimension(500, 800));

        refreshButton.setText("Refresh Devices");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        stopRefreshingButton.setText("Stop");
        stopRefreshingButton.setEnabled(false);
        stopRefreshingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopRefreshingButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(refreshButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stopRefreshingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 333, Short.MAX_VALUE)
                .addComponent(deviceRefreshingBar, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(deviceRefreshingBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(refreshButton, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                        .addComponent(stopRefreshingButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        deviceTabbedPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Registered Devices"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(deviceTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deviceTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed

        refreshThread = new Thread(new Runnable() {
            public void run() {
                deviceManager.refreshDevices();
            }
        });
        refreshThread.start();
}//GEN-LAST:event_refreshButtonActionPerformed

    private void stopRefreshingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopRefreshingButtonActionPerformed
        deviceManager.stopRefreshing();
}//GEN-LAST:event_stopRefreshingButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JProgressBar deviceRefreshingBar;
    public javax.swing.JTabbedPane deviceTabbedPane;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JButton refreshButton;
    public javax.swing.JButton stopRefreshingButton;
    // End of variables declaration//GEN-END:variables

}
