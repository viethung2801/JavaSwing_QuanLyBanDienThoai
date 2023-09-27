package view.Contains.tragop;

public class jplFormThanhToan extends javax.swing.JPanel {

    public jplFormThanhToan() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jplFormThanhToanMain = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblPhaiTra = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTienDua = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lblTienThua = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        lblTongTien1 = new javax.swing.JLabel();
        btnThanhToan1 = new javax.swing.JButton();

        jplFormThanhToanMain.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Hóa Đơn:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Tổng Tiền :");

        lblTongTien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(233, 83, 83));
        lblTongTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTongTien.setText("0");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Khách Phải Trả :");

        lblPhaiTra.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPhaiTra.setForeground(new java.awt.Color(0, 204, 102));
        lblPhaiTra.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPhaiTra.setText("0");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Tiền Khách Đưa :");

        txtTienDua.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTienDua.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTienDua.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));
        txtTienDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienDuaActionPerformed(evt);
            }
        });
        txtTienDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienDuaKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Tiền Thừa :");

        lblTienThua.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTienThua.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTienThua.setText("0");

        btnThanhToan.setBackground(new java.awt.Color(47, 85, 212));
        btnThanhToan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-cash-30.png"))); // NOI18N
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Ghi Chú:");

        txtGhiChu.setColumns(1);
        txtGhiChu.setRows(5);
        jScrollPane4.setViewportView(txtGhiChu);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Mã Giảm Giá:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblTongTien1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTongTien1.setForeground(new java.awt.Color(233, 83, 83));
        lblTongTien1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTongTien1.setText("0");

        btnThanhToan1.setBackground(new java.awt.Color(47, 85, 212));
        btnThanhToan1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThanhToan1.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-print-30.png"))); // NOI18N
        btnThanhToan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToan1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jplFormThanhToanMainLayout = new javax.swing.GroupLayout(jplFormThanhToanMain);
        jplFormThanhToanMain.setLayout(jplFormThanhToanMainLayout);
        jplFormThanhToanMainLayout.setHorizontalGroup(
            jplFormThanhToanMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplFormThanhToanMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jplFormThanhToanMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jplFormThanhToanMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jplFormThanhToanMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jplFormThanhToanMainLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTienDua, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jplFormThanhToanMainLayout.createSequentialGroup()
                                .addGroup(jplFormThanhToanMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(17, 17, 17)
                                .addGroup(jplFormThanhToanMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblPhaiTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jplFormThanhToanMainLayout.createSequentialGroup()
                                .addGroup(jplFormThanhToanMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                                .addGap(29, 29, 29)
                                .addGroup(jplFormThanhToanMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(lblTongTien1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jplFormThanhToanMainLayout.createSequentialGroup()
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThanhToan1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 11, Short.MAX_VALUE))
            .addGroup(jplFormThanhToanMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jplFormThanhToanMainLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(165, Short.MAX_VALUE)))
        );
        jplFormThanhToanMainLayout.setVerticalGroup(
            jplFormThanhToanMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplFormThanhToanMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTongTien1)
                .addGap(11, 11, 11)
                .addGroup(jplFormThanhToanMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplFormThanhToanMainLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE))
                    .addComponent(lblTongTien))
                .addGap(18, 18, 18)
                .addGroup(jplFormThanhToanMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jplFormThanhToanMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplFormThanhToanMainLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE))
                    .addComponent(lblPhaiTra))
                .addGap(11, 11, 11)
                .addGroup(jplFormThanhToanMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jplFormThanhToanMainLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE))
                    .addComponent(txtTienDua))
                .addGap(12, 12, 12)
                .addGroup(jplFormThanhToanMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplFormThanhToanMainLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE))
                    .addComponent(lblTienThua))
                .addGap(11, 11, 11)
                .addGroup(jplFormThanhToanMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplFormThanhToanMainLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                        .addGap(39, 39, 39))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jplFormThanhToanMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(btnThanhToan1, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                .addGap(9, 9, 9))
            .addGroup(jplFormThanhToanMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jplFormThanhToanMainLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel5)
                    .addContainerGap(298, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jplFormThanhToanMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jplFormThanhToanMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed

        //        thanhToan();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtTienDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienDuaKeyReleased
        // TODO add your handling code here:
        //        try {
        //            int inputKhachTra = Integer.parseInt(txtTienDua.getText().trim());
        //
        //            int phaiTra = convertToNumber(lblPhaiTra.getText().trim());
        //
        //            lblTienThua.setText(convertToVND(inputKhachTra - phaiTra));
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //            lblTienThua.setText("0");
        //        }
    }//GEN-LAST:event_txtTienDuaKeyReleased

    private void txtTienDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienDuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienDuaActionPerformed

    private void btnThanhToan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThanhToan1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThanhToan1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel jplFormThanhToanMain;
    private javax.swing.JLabel lblPhaiTra;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblTongTien1;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtTienDua;
    // End of variables declaration//GEN-END:variables
}
