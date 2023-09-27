package view.Contains.EntitySanPham;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import model.DongSanPham;
import model.Hang;
import repository.HangRepository;
import service.DongSanPhamService;
import service.HangService;
import service.impl.DongSanPhamServiceImpl;
import service.impl.HangServiceImpl;
import view.Contains.jplSanPham;
import viewmodel.DongSanPhamResponse;

public class ThemDongSP extends javax.swing.JFrame {

    private List<DongSanPhamResponse> activeDongSPResponses;
    private List<DongSanPhamResponse> inactiveDongSPResponses;
    private List<Hang> hangList;
    private DefaultTableModel dtmActive;
    private DefaultTableModel dtmInactive;
    private DefaultComboBoxModel dcbmHang;

    private DongSanPhamService dongSPService;
    private HangService hangService;

    public ThemDongSP() {
        initComponents();
        setLocationRelativeTo(null);
        this.setTitle("Dòng Sản Phẩm");

        dtmActive = (DefaultTableModel) tbActive.getModel();
        dtmInactive = (DefaultTableModel) tbInactive.getModel();
        dcbmHang = (DefaultComboBoxModel) cbHangDT.getModel();

        activeDongSPResponses = new ArrayList<>();
        inactiveDongSPResponses = new ArrayList<>();
        hangList = new ArrayList<>();

        dongSPService = new DongSanPhamServiceImpl();
        hangService = new HangServiceImpl();

        activeDongSPResponses = dongSPService.getAllDongSPResponseByStatus(true);
        showActiveTable(activeDongSPResponses);
        inactiveDongSPResponses = dongSPService.getAllDongSPResponseByStatus(false);
        showInactivetable(inactiveDongSPResponses);

        showCbHang();
    }

    private void showActiveTable(List<DongSanPhamResponse> dongSPResponses) {
        dtmActive.setRowCount(0);
        dongSPResponses.forEach(d -> dtmActive.addRow(d.toDataRow()));
    }

    private void showInactivetable(List<DongSanPhamResponse> dongSPResponses) {
        dtmInactive.setRowCount(0);
        dongSPResponses.forEach(d -> dtmInactive.addRow(d.toDataRow()));
    }

    private void showCbHang() {
        hangList = hangService.getAllEntityByStatus(true);
        hangList.forEach(h -> cbHangDT.addItem(h));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        tab = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbActive = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnKhoiPhuc = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbInactive = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDongSP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnLamMoi = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        cbHangDT = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("HÃNG");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tab.setBackground(new java.awt.Color(255, 255, 255));
        tab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabMouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tbActive.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "DÒNG SP", "HÃNG"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbActive.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbActiveMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbActive);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tab.addTab("Đang hoạt động", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnKhoiPhuc.setBackground(new java.awt.Color(47, 85, 212));
        btnKhoiPhuc.setForeground(new java.awt.Color(255, 255, 255));
        btnKhoiPhuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-available-updates-20 (1).png"))); // NOI18N
        btnKhoiPhuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiPhucActionPerformed(evt);
            }
        });

        tbInactive.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "DÒNG SP", "HÃNG"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbInactive.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbInactiveMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbInactive);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 272, Short.MAX_VALUE)
                        .addComponent(btnKhoiPhuc, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnKhoiPhuc, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab.addTab("Không hoạt động", jPanel2);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("THÔNG TIN"));

        jLabel1.setText("TÊN DÒNG SP:");

        txtDongSP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        jLabel2.setText("HÃNG ĐIỆN THOẠI:");

        btnLamMoi.setBackground(new java.awt.Color(47, 85, 212));
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-new-20.png"))); // NOI18N
        btnLamMoi.setText("MỚI");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(47, 85, 212));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-add-new-20.png"))); // NOI18N
        btnThem.setText("THÊM");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(47, 85, 212));
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-pencil-20 WHITE.png"))); // NOI18N
        btnSua.setText("SỬA");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(47, 85, 212));
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-trash-20.png"))); // NOI18N
        btnXoa.setText("XÓA");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        cbHangDT.setModel(new javax.swing.DefaultComboBoxModel<>(new Hang[]{}));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(txtDongSP, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLamMoi)
                            .addComponent(btnSua))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(23, 23, 23))
                    .addComponent(cbHangDT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbHangDT, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLamMoi)
                    .addComponent(btnThem))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua)
                    .addComponent(btnXoa))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKhoiPhucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiPhucActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Khôi phục dòng SP?", "Xác nhận khôi phục dòng SP", JOptionPane.YES_NO_OPTION);
        if (confirm != 0) {
            return;
        }

        int clickedRow = tbInactive.getSelectedRow();
        if (clickedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng SP trước khi khôi phục!");
            return;
        }

        DongSanPhamResponse selectedDongSPhamResponse = inactiveDongSPResponses.get(clickedRow);

        String result = dongSPService.changeStatus(selectedDongSPhamResponse, true);
        JOptionPane.showMessageDialog(this, result);

        // reset table
        activeDongSPResponses = dongSPService.getAllDongSPResponseByStatus(true);
        showActiveTable(activeDongSPResponses);

        inactiveDongSPResponses = dongSPService.getAllDongSPResponseByStatus(false);
        showInactivetable(inactiveDongSPResponses);

        txtDongSP.setText("");
        cbHangDT.setSelectedIndex(0);
    }//GEN-LAST:event_btnKhoiPhucActionPerformed

    private void tabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabMouseClicked
        tab.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = tab.getSelectedIndex();
                if (index == 0) {
                    setButtons(true);
                } else {
                    setButtons(false);
                }
            }
        });
    }//GEN-LAST:event_tabMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        txtDongSP.setText("");
        cbHangDT.setSelectedIndex(0);

        activeDongSPResponses = dongSPService.getAllDongSPResponseByStatus(true);
        showActiveTable(activeDongSPResponses);

        inactiveDongSPResponses = dongSPService.getAllDongSPResponseByStatus(false);
        showInactivetable(inactiveDongSPResponses);
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Thêm dòng SP?", "Xác nhận thêm dòng SP", JOptionPane.YES_NO_OPTION);
        if (confirm != 0) {
            return;
        }

        String tenDongSp = txtDongSP.getText().trim();

        String checkResult = checkInput(0, tenDongSp);
        if (!checkResult.equals("")) {
            JOptionPane.showMessageDialog(this, checkResult);
            return;
        }

        DongSanPham newDongSP = new DongSanPham();
        newDongSP.setTen(tenDongSp);

        Hang selectedHang = HangRepository.getByTenHang(String.valueOf(dcbmHang.getSelectedItem()));
        newDongSP.setHangDienThoai(selectedHang);
        newDongSP.setTrangThai(true);

        String addResult = dongSPService.add(newDongSP);
        JOptionPane.showMessageDialog(this, addResult);

        // reset table
        activeDongSPResponses = dongSPService.getAllDongSPResponseByStatus(true);
        showActiveTable(activeDongSPResponses);
        txtDongSP.setText("");
        cbHangDT.setSelectedIndex(0);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Sửa dòng SP?", "Xác nhận sửa dòng SP", JOptionPane.YES_NO_OPTION);
        if (confirm != 0) {
            return;
        }

        int clickedRow = tbActive.getSelectedRow();
        if (clickedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng SP trước khi sửa!");
            return;
        }

        DongSanPhamResponse selectedDongSP = activeDongSPResponses.get(clickedRow);
        String tenDongSp = txtDongSP.getText().trim();
        String message = checkInput(selectedDongSP.getId(), tenDongSp);

        if (!message.equals("")) {
            JOptionPane.showMessageDialog(this, message);
            return;
        }

        selectedDongSP.setTen(tenDongSp);
        selectedDongSP.setTenHang(String.valueOf(dcbmHang.getSelectedItem()));

        String updateResult = dongSPService.update(selectedDongSP);
        JOptionPane.showMessageDialog(this, updateResult);

        // reset table
        activeDongSPResponses = dongSPService.getAllDongSPResponseByStatus(true);
        showActiveTable(activeDongSPResponses);

        txtDongSP.setText("");
        cbHangDT.setSelectedIndex(0);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void tbActiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbActiveMouseClicked
        int clickedRow = tbActive.getSelectedRow();
        if (clickedRow < 0) {
            return;
        }

        DongSanPhamResponse selectedDongSP = activeDongSPResponses.get(clickedRow);
        txtDongSP.setText(selectedDongSP.getTen());
        Hang hang = HangRepository.getByTenHang(selectedDongSP.getTenHang());
        cbHangDT.setSelectedItem(hang);
    }//GEN-LAST:event_tbActiveMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Xóa dòng SP?", "Xác nhận xóa dòng SP", JOptionPane.YES_NO_OPTION);
        if (confirm != 0) {
            return;
        }

        int clickedRow = tbActive.getSelectedRow();
        if (clickedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng SP trước khi xóa!");
            return;
        }

        DongSanPhamResponse selectedDongSP = activeDongSPResponses.get(clickedRow);

        String result = dongSPService.changeStatus(selectedDongSP, false);
        JOptionPane.showMessageDialog(this, result);

        // reset table
        activeDongSPResponses = dongSPService.getAllDongSPResponseByStatus(true);
        showActiveTable(activeDongSPResponses);

        inactiveDongSPResponses = dongSPService.getAllDongSPResponseByStatus(false);
        showInactivetable(inactiveDongSPResponses);

        txtDongSP.setText("");
        cbHangDT.setSelectedIndex(0);
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tbInactiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbInactiveMouseClicked
        int clickedRow = tbInactive.getSelectedRow();
        if (clickedRow < 0) {
            return;
        }

        DongSanPhamResponse selectedDongSP = inactiveDongSPResponses.get(clickedRow);
        txtDongSP.setText(selectedDongSP.getTen());
        Hang hang = HangRepository.getByTenHang(selectedDongSP.getTenHang());
        cbHangDT.setSelectedItem(hang);
    }//GEN-LAST:event_tbInactiveMouseClicked

    private String checkInput(int id, String tenDongSp) {
        String message = "";
        if (tenDongSp.isBlank()) {
            message += "Tên dòng SP không được để trống!";
            return message;
        } else {

            // Check unique tenDongSP
            DongSanPham dsp = dongSPService.getByTenDongSP(tenDongSp);
            if (dsp != null) {
                if (id == 0) {
                    message += "Tên dòng SP đã bị trùng!";
                    if (!dsp.isTrangThai()) {
                        message += " (trong mục đã xóa)";
                    }
                    return message;
                } else if (id > 0) {
                    if (dsp.getId() != id) {
                        message += "Tên dòng SP đã bị trùng!";
                        if (!dsp.isTrangThai()) {
                            message += " (trong mục đã xóa)";
                        }
                        return message;
                    }
                }
            }

            // Check pattern
            String pattern = "[a-zA-Z0-9 ]{1,30}";
            if (!tenDongSp.matches(pattern)) {
                message += "Tên dòng SP sai định dạng!\n";
                return message;
            }
        }
        return message;
    }

    private void setButtons(boolean boo) {
        btnLamMoi.setEnabled(boo);
        btnThem.setEnabled(boo);
        btnSua.setEnabled(boo);
        btnXoa.setEnabled(boo);
    }

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
            java.util.logging.Logger.getLogger(ThemDongSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemDongSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemDongSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemDongSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThemDongSP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKhoiPhuc;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<Hang> cbHangDT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTable tbActive;
    private javax.swing.JTable tbInactive;
    private javax.swing.JTextField txtDongSP;
    // End of variables declaration//GEN-END:variables

}
