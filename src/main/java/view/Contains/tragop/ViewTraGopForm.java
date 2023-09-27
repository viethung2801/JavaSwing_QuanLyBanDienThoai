package view.Contains.tragop;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import model.LichSuTraGop;
import model.PhieuTraGop;
import repository.LichSuTraGopRepository;
import repository.impl.LichSuTraGopRepositoryImpl;
import service.PhieuTraGopService;
import service.impl.PhieuTraGopServiceImpl;

public class ViewTraGopForm extends javax.swing.JPanel {

    private PhieuTraGopService phieuTraGopService = new PhieuTraGopServiceImpl();
    private LichSuTraGopRepository lstgRepository = new LichSuTraGopRepositoryImpl();

    public ViewTraGopForm() {
        initComponents();
        tinhLaiSuat();
        getPhieuTraGopForm();
    }

    private void tinhLaiSuat() {
        long tongTien = getTongTien();
        float laiSuat = getLaiSuat();
        int kyHan = getKyHan();

//        System.out.println(tongTien);
//        System.out.println(laiSuat);
//        System.out.println(kyHan);
        long traTruoc = (long) (tongTien * 0.3);
        long conLai = tongTien - traTruoc;
        long traHangThang = (long) ((conLai / kyHan) + ((conLai * laiSuat / 100) / kyHan));

//        System.out.println(traTruoc);
//        System.out.println(traHangThang);
        lblPhaiTra.setText(String.valueOf(traHangThang));
        lblTraTruoc.setText(String.valueOf(traTruoc));
    }

    private PhieuTraGop getPhieuTraGopForm() {
        //tạo Phieu Tra Gop
        PhieuTraGop ptg = new PhieuTraGop();
        //tạo lich su tra gop
        LichSuTraGop lstg = new LichSuTraGop();
        //set lich sử trả góp
        lstg.setMa(lstgRepository.generateMaLSTG());
        lstg.setGhiChu("");
        lstg.setNgayThanhToan(LocalDate.now());
        lstg.setPhieuTraGop(ptg);
        lstg.setTongTien(getTraTruoc());
        //set Phiếu trả góp
//        ptg.setHoaDon(null); // chưa có hóa đơn làm sau
        ptg.setKyHan(getKyHan());
        ptg.setLaiSuat(getLaiSuat());
        ptg.addLichSuTraGop(lstg);
        ptg.setMaPhieu("");
//        ptg.setNgayDong(LocalDate.now().getDayOfMonth());
        ptg.setNgayTao(LocalDate.now());
        ptg.setPhaiTra(getPhaiTra());
        ptg.setTongPhaiTra(getTongTien());

        System.out.println(ptg.toString());
        System.out.println(lstg.toString());
        return ptg;
    }

    private long getTongTien() {
        return Long.parseLong(lblTongTien.getText());
    }

    private float getLaiSuat() {
        return Float.parseFloat(txtLaiSuat.getText());
    }

    private int getKyHan() {
        return Integer.parseInt(cbxKiHan.getSelectedItem().toString().substring(0, 2).trim());
    }

    private long getPhaiTra() {
        return Long.parseLong(lblPhaiTra.getText().trim());
    }

    private long getTraTruoc() {
        return Long.parseLong(lblTraTruoc.getText().trim());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnThanhToan = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtKhachHang = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        txtLaiSuat = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbxKiHan = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        lblTraTruoc = new javax.swing.JLabel();
        lblPhaiTra = new javax.swing.JLabel();
        btnThanhToan1 = new javax.swing.JButton();

        jpnThanhToan.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Khách Hàng :");

        txtKhachHang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtKhachHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKhachHangKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Tổng Tiền :");

        lblTongTien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(233, 83, 83));
        lblTongTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTongTien.setText("0");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Lãi Suất :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Trả Trước :");

        btnThanhToan.setBackground(new java.awt.Color(47, 85, 212));
        btnThanhToan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-salary-male-30.png"))); // NOI18N
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        txtLaiSuat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtLaiSuat.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtLaiSuat.setText("0");
        txtLaiSuat.setToolTipText("");
        txtLaiSuat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLaiSuatKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("%");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Kì Hạn :");

        cbxKiHan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cbxKiHan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3 Tháng", "6 Tháng", "9 Tháng", "12 Tháng" }));
        cbxKiHan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxKiHanItemStateChanged(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Phải trả / Tháng :");

        lblTraTruoc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTraTruoc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTraTruoc.setText("0");

        lblPhaiTra.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPhaiTra.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPhaiTra.setText("0");

        btnThanhToan1.setBackground(new java.awt.Color(47, 85, 212));
        btnThanhToan1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThanhToan1.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-print-30.png"))); // NOI18N
        btnThanhToan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToan1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnThanhToanLayout = new javax.swing.GroupLayout(jpnThanhToan);
        jpnThanhToan.setLayout(jpnThanhToanLayout);
        jpnThanhToanLayout.setHorizontalGroup(
            jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnThanhToanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnThanhToanLayout.createSequentialGroup()
                        .addGroup(jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnThanhToanLayout.createSequentialGroup()
                                .addGroup(jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(33, 33, 33))
                            .addGroup(jpnThanhToanLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jpnThanhToanLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(9, 9, 9)))
                        .addGroup(jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnThanhToanLayout.createSequentialGroup()
                                .addGroup(jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtKhachHang))
                                .addGap(12, 12, 12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnThanhToanLayout.createSequentialGroup()
                                .addGroup(jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpnThanhToanLayout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(cbxKiHan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jpnThanhToanLayout.createSequentialGroup()
                                                .addGap(59, 59, 59)
                                                .addComponent(txtLaiSuat, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(lblPhaiTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblTraTruoc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(btnThanhToan1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15))))
                    .addGroup(jpnThanhToanLayout.createSequentialGroup()
                        .addGroup(jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnThanhToanLayout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(28, 28, 28))
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jpnThanhToanLayout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(8, 8, 8)))
                        .addGap(143, 143, 143))))
        );
        jpnThanhToanLayout.setVerticalGroup(
            jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnThanhToanLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnThanhToanLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblTongTien))
                .addGap(20, 20, 20)
                .addGroup(jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnThanhToanLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpnThanhToanLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtLaiSuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnThanhToanLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(cbxKiHan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnThanhToanLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblPhaiTra))
                .addGap(16, 16, 16)
                .addGroup(jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnThanhToanLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblTraTruoc))
                .addGap(18, 18, 18)
                .addGroup(jpnThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThanhToan1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jpnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jpnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        JOptionPane.showMessageDialog(this, phieuTraGopService.insert(getPhieuTraGopForm()));

        //thanhToan();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtLaiSuatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLaiSuatKeyReleased
        // TODO add your handling code here:
        try {
            tinhLaiSuat();
        } catch (Exception e) {
//            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lãi suất không hơp lệ");
        }
    }//GEN-LAST:event_txtLaiSuatKeyReleased

    private void cbxKiHanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxKiHanItemStateChanged
        // TODO add your handling code here:
        try {
            tinhLaiSuat();
        } catch (Exception e) {
//            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lãi suất không hơp lệ");
        }
    }//GEN-LAST:event_cbxKiHanItemStateChanged

    private void btnThanhToan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThanhToan1ActionPerformed

    private void txtKhachHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKhachHangKeyReleased
        //        // TODO add your handling code here:
        //        jPopupMenu1.removeAll();
        //        jPopupMenu1.add(jpnlSearchKH);
        //        listKH = khachHangService.getKhachHang(txtKhachHang.getText().trim());
        //        if (listKH.size() >= 0) {
        //            dlmKH.removeAllElements();
        //            for (KhachHang khachHang : listKH) {
        //                dlmKH.addElement(khachHang.getMaKH() + " - " + khachHang.getHo() + " " + khachHang.getTenDem() + " " + khachHang.getTen());
        //            }
        //            jPopupMenu1.show(txtKhachHang, 0, txtSearchSP.getHeight());
        //        }
    }//GEN-LAST:event_txtKhachHangKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThanhToan1;
    private javax.swing.JComboBox<String> cbxKiHan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jpnThanhToan;
    private javax.swing.JLabel lblPhaiTra;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblTraTruoc;
    private javax.swing.JTextField txtKhachHang;
    private javax.swing.JTextField txtLaiSuat;
    // End of variables declaration//GEN-END:variables
}
