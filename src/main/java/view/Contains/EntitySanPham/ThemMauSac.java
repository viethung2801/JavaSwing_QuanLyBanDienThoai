package view.Contains.EntitySanPham;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import model.MauSac;
import service.MauSacService;
import service.impl.MauSacServiceImpl;
import viewmodel.MauSacResponse;

public class ThemMauSac extends javax.swing.JFrame {

    private MauSacService mauSacService;
    private List<MauSacResponse> activeMauSacResponseList;
    private List<MauSacResponse> inactiveMauSacResponseList;
    private DefaultTableModel dtmActive;
    private DefaultTableModel dtmInactive;

    public ThemMauSac() {
        initComponents();
        setLocationRelativeTo(null);

        mauSacService = new MauSacServiceImpl();
        activeMauSacResponseList = new ArrayList<>();
        inactiveMauSacResponseList = new ArrayList<>();
        dtmActive = (DefaultTableModel) tbActive.getModel();
        dtmInactive = (DefaultTableModel) tbInactive.getModel();

        activeMauSacResponseList = mauSacService.getAllResponseByStatus(true);
        showActiveTable(activeMauSacResponseList);

        inactiveMauSacResponseList = mauSacService.getAllResponseByStatus(false);
        showInactiveTable(inactiveMauSacResponseList);
    }

    // 1
    private void showActiveTable(List<MauSacResponse> mauSacResponses) {
        dtmActive.setRowCount(0);
        mauSacResponses.forEach(m -> dtmActive.addRow(m.toDataRow()));
    }

    // 2
    private void showInactiveTable(List<MauSacResponse> mauSacResponses) {
        dtmInactive.setRowCount(0);
        mauSacResponses.forEach(m -> dtmInactive.addRow(m.toDataRow()));
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tbInactive = new javax.swing.JTable();
        btnKhoiPhuc = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaMau = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenMau = new javax.swing.JTextField();
        btnLamMoi = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MÀU SẮC");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tab.setBackground(new java.awt.Color(255, 255, 255));
        tab.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabStateChanged(evt);
            }
        });
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
                "MÃ MÀU", "TÊN MÀU"
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tab.addTab("Đang hoạt động", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tbInactive.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "MÃ MÀU", "TÊN MÀU"
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

        btnKhoiPhuc.setBackground(new java.awt.Color(47, 85, 212));
        btnKhoiPhuc.setForeground(new java.awt.Color(255, 255, 255));
        btnKhoiPhuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-available-updates-20 (1).png"))); // NOI18N
        btnKhoiPhuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiPhucActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 282, Short.MAX_VALUE)
                        .addComponent(btnKhoiPhuc, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(btnKhoiPhuc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tab.addTab("Không hoạt động", jPanel2);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("THÔNG TIN"));

        jLabel1.setText("MÃ MÀU");

        txtMaMau.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        jLabel2.setText("TÊN MÀU:");

        txtTenMau.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTenMau, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(txtMaMau, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSua)
                            .addComponent(btnLamMoi))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(33, 33, 33)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtMaMau, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTenMau, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
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
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKhoiPhucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiPhucActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Khôi phục màu sắc?", "Xác nhận khôi phục màu sắc", JOptionPane.YES_NO_OPTION);
        if (confirm != 0) {
            return;
        }

        int clickedRow = tbInactive.getSelectedRow();
        if (clickedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn màu sắc trước khi khôi phục!");
            return;
        }

        MauSacResponse selectedMauSacResponse = inactiveMauSacResponseList.get(clickedRow);

        String result = mauSacService.changeStatus(selectedMauSacResponse, true);
        JOptionPane.showMessageDialog(this, result);

        // reset table
        activeMauSacResponseList = mauSacService.getAllResponseByStatus(true);
        showActiveTable(activeMauSacResponseList);

        inactiveMauSacResponseList = mauSacService.getAllResponseByStatus(false);
        showInactiveTable(inactiveMauSacResponseList);

        txtMaMau.setText("");
        txtTenMau.setText("");
    }//GEN-LAST:event_btnKhoiPhucActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        activeMauSacResponseList = mauSacService.getAllResponseByStatus(true);
        showActiveTable(activeMauSacResponseList);

        inactiveMauSacResponseList = mauSacService.getAllResponseByStatus(false);
        showInactiveTable(inactiveMauSacResponseList);

        txtMaMau.setText("");
        txtTenMau.setText("");
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Thêm màu sắc?", "Xác nhận thêm màu sắc", JOptionPane.YES_NO_OPTION);
        if (confirm != 0) {
            return;
        }
        String maMau = txtMaMau.getText().trim();
        String tenMau = txtTenMau.getText().trim();

        String checkResult = checkMaMau(0, maMau);

        if (!checkResult.equals("")) {
            JOptionPane.showMessageDialog(this, checkResult);
            return;
        }

        MauSac newMauSac = new MauSac();
        newMauSac.setMaMauSac(maMau);
        newMauSac.setTenMauSac(tenMau);
        newMauSac.setTrangThai(true);

        String addResult = mauSacService.add(newMauSac);
        JOptionPane.showMessageDialog(this, addResult);

        // reset table
        activeMauSacResponseList = mauSacService.getAllResponseByStatus(true);
        showActiveTable(activeMauSacResponseList);

        txtMaMau.setText("");
        txtTenMau.setText("");
    }//GEN-LAST:event_btnThemActionPerformed

    private void tbInactiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbInactiveMouseClicked
        int clickedRow = tbInactive.getSelectedRow();
        if (clickedRow < 0) {
            return;
        }

        MauSacResponse selectedMauSac = inactiveMauSacResponseList.get(clickedRow);
        txtMaMau.setText(selectedMauSac.getMaMauSac());
        txtTenMau.setText(selectedMauSac.getTenMauSac());
    }//GEN-LAST:event_tbInactiveMouseClicked

    private void tbActiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbActiveMouseClicked
        int clickedRow = tbActive.getSelectedRow();
        if (clickedRow < 0) {
            return;
        }

        MauSacResponse selectedMauSac = activeMauSacResponseList.get(clickedRow);
        txtMaMau.setText(selectedMauSac.getMaMauSac());
        txtTenMau.setText(selectedMauSac.getTenMauSac());
    }//GEN-LAST:event_tbActiveMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Sửa màu sắc?", "Xác nhận sửa màu sắc", JOptionPane.YES_NO_OPTION);
        if (confirm != 0) {
            return;
        }

        int clickedRow = tbActive.getSelectedRow();
        if (clickedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn màu sắc trước khi sửa!");
            return;
        }

        MauSacResponse selectedMauSacResponse = activeMauSacResponseList.get(clickedRow);
        String maMau = txtMaMau.getText().trim();
        String tenMau = txtTenMau.getText().trim();
        String message = checkMaMau(selectedMauSacResponse.getId(), maMau);

        if (!message.equals("")) {
            JOptionPane.showMessageDialog(this, message);
            return;
        }

        selectedMauSacResponse.setMaMauSac(maMau);
        selectedMauSacResponse.setTenMauSac(tenMau);

        String updateResult = mauSacService.update(selectedMauSacResponse);
        JOptionPane.showMessageDialog(this, updateResult);

        // reset table
        activeMauSacResponseList = mauSacService.getAllResponseByStatus(true);
        showActiveTable(activeMauSacResponseList);

        txtMaMau.setText("");
        txtTenMau.setText("");
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Xóa màu sắc?", "Xác nhận xóa màu sắc", JOptionPane.YES_NO_OPTION);
        if (confirm != 0) {
            return;
        }

        int clickedRow = tbActive.getSelectedRow();
        if (clickedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn màu sắc trước khi xóa!");
            return;
        }

        MauSacResponse selectedMauSacResponse = activeMauSacResponseList.get(clickedRow);

        String result = mauSacService.changeStatus(selectedMauSacResponse, false);
        JOptionPane.showMessageDialog(this, result);

        // reset table
        activeMauSacResponseList = mauSacService.getAllResponseByStatus(true);
        showActiveTable(activeMauSacResponseList);

        inactiveMauSacResponseList = mauSacService.getAllResponseByStatus(false);
        showInactiveTable(inactiveMauSacResponseList);

        txtMaMau.setText("");
        txtTenMau.setText("");
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabMouseClicked
    }//GEN-LAST:event_tabMouseClicked

    private void tabStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabStateChanged
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
    }//GEN-LAST:event_tabStateChanged

    private void setButtons(boolean boo) {
        btnLamMoi.setEnabled(boo);
        btnThem.setEnabled(boo);
        btnSua.setEnabled(boo);
        btnXoa.setEnabled(boo);
    }

    private String checkMaMau(int id, String maMau) {
        String message = "";
        if (maMau.isBlank()) {
            message += "Mã màu không được để trống!";
            return message;
        } else {

            // Check unique maMau
            MauSac mauSac = mauSacService.getByMa(maMau);
            if (mauSac != null) {
                if (id == 0) {
                    message += "Mã màu đã bị trùng!";
                    if (!mauSac.isTrangThai()) {
                        message += " (trong mục đã xóa)";
                    }
                    return message;
                } else if (id > 0) {
                    if (mauSac.getId() != id) {
                        message += "Mã màu đã bị trùng!";
                        if (!mauSac.isTrangThai()) {
                            message += " (trong mục đã xóa)";
                        }
                        return message;
                    }
                }
            }

            // Check pattern
            String pattern = "[a-zA-Z0-9]{1,10}";
            if (!maMau.matches(pattern)) {
                message += "Tên hãng sai định dạng!\n";
                return message;
            }
        }
        return message;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThemMauSac().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKhoiPhuc;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
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
    private javax.swing.JTextField txtMaMau;
    private javax.swing.JTextField txtTenMau;
    // End of variables declaration//GEN-END:variables
}
