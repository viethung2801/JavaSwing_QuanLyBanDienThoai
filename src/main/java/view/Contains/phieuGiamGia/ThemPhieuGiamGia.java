package view.Contains.phieuGiamGia;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import javax.swing.JOptionPane;
import model.PhieuGiamGia;
import model.PhieuGiamGiaChiTiet;
import repository.PhieuGiamGiaRepository;
import service.impl.PhieuGiamGiaServiceImpl;
import viewmodel.PhieuGiamGiaResponse;
import service.PhieuGiamGiaService;
import view.Contains.jplGiamGia;

/**
 *
 * @author DELL
 */
public class ThemPhieuGiamGia extends javax.swing.JFrame {

    PhieuGiamGiaChiTiet phieuGiamGiaChiTiet;
    PhieuGiamGia phieuGiamGia;
    PhieuGiamGiaService qs;
    public static boolean check = false;
    int vali;

    public ThemPhieuGiamGia() {
        initComponents();
        setLocationRelativeTo(null);
        phieuGiamGiaChiTiet = new PhieuGiamGiaChiTiet();
        phieuGiamGia = new PhieuGiamGia();
        qs = new PhieuGiamGiaServiceImpl();
        long millis = System.currentTimeMillis();
        Date now = new Date(millis);
        txtNgayBatDau.setMinSelectableDate(now);
        txtNgayKetThuc.setMinSelectableDate(now);
        vali = 0;
    }

    public int setTrangThai(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        LocalDate homNay = LocalDate.now();

        if (homNay.compareTo(ngayBatDau) >= 0 && homNay.compareTo(ngayKetThuc) < 0) {
            return 0;
        } else if (homNay.compareTo(ngayBatDau) < 0) {
            return 2;
        } else if (homNay.compareTo(ngayBatDau) > 0 && homNay.compareTo(ngayKetThuc) >= 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public void validates() {
        String tenPhieu = txtTenPhieu.getText();
        String MaPhieu = txtMaVoucher.getText();
        String mucGiam = txtMucGiam.getText();
        String toiThieu = txtGiaTriToiThieu.getText();
        String luotDung = txtLuotDung.getText();
        if (tenPhieu.trim().equals("") || MaPhieu.trim().equals("")
                || mucGiam.trim().equals("") || toiThieu.trim().equals("")
                || luotDung.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn cần nhập tất cả thông tin");
            vali = 1;
            return;
        }
        if (!MaPhieu.matches("\\w+")) {
            JOptionPane.showMessageDialog(this, "Mã không hợp lệ");
            vali = 1;
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date bd;
        bd = txtNgayBatDau.getDate();
        Date kt;
        kt = txtNgayKetThuc.getDate();
        if (bd == null || kt == null) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn ngày bắt đầu và ngày kết thúc");
            vali = 1;
            return;
        }
        long millis = System.currentTimeMillis();
        Date now = new Date(millis);
        System.out.println(now);
        if (bd.compareTo(kt) >= 0) {
            JOptionPane.showMessageDialog(this, "Chương trình giảm giá phải kéo dài ít nhất 1 ngày");
            vali = 1;
            return;
        }
        vali = 0;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTenPhieu = new javax.swing.JTextField();
        txtMaVoucher = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNgayBatDau = new com.toedter.calendar.JDateChooser();
        txtNgayKetThuc = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMucGiam = new javax.swing.JTextField();
        txtGiaTriToiThieu = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtLuotDung = new javax.swing.JTextField();
        btnHuy = new javax.swing.JButton();
        btnXacNhan = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        jButton1.setText("Hello");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Thêm phiếu giảm giá");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Tên phiếu giảm giá:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã Voucher:");

        txtTenPhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenPhieuActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Thông tin cơ bản");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Ngày bắt đầu:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Ngày kết thúc:");

        txtNgayBatDau.setDateFormatString("yyyy-MM-dd");

        txtNgayKetThuc.setDateFormatString("yyyy-MM-dd");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Thiết lập mã giảm giá");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Mức giảm:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Giá trị đơn hàng tối thiểu:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Lượt sử dụng:");

        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnXacNhan.setBackground(new java.awt.Color(51, 51, 255));
        btnXacNhan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXacNhan.setForeground(new java.awt.Color(255, 255, 255));
        btnXacNhan.setText("Xác nhận");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtGiaTriToiThieu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                                    .addComponent(txtMucGiam, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtLuotDung)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(364, 364, 364)
                                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXacNhan))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaVoucher)
                            .addComponent(txtTenPhieu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNgayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))))
                .addGap(24, 24, 24))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtMaVoucher, txtNgayBatDau, txtNgayKetThuc});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnHuy, btnXacNhan});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTenPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMaVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(38, 38, 38))
                        .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(jLabel7)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtMucGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtGiaTriToiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtLuotDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtMaVoucher, txtNgayBatDau, txtNgayKetThuc, txtTenPhieu});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnHuy, btnXacNhan});

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel3.setText("Tạo mã giảm giá mới");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenPhieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenPhieuActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        dispose();

    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        // TODO add your handling code here:
        validates();
        if (vali == 1) {
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String batDau = sdf.format(txtNgayBatDau.getDate());
        String ketThuc = sdf.format(txtNgayKetThuc.getDate());
        int luot = 0;
        int trangThai = 0;
        float mucGiam = 0;
        long dieuKien = 0;
        String luotDung = txtLuotDung.getText();
        try {
            mucGiam = Float.valueOf(txtMucGiam.getText());
            if (mucGiam < 0) {
                JOptionPane.showMessageDialog(this, "Mức giảm phải lớn hơn 0");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Mức giảm phải là số");
            return;
        }
        try {
            dieuKien = Long.parseLong(txtGiaTriToiThieu.getText());
            if (dieuKien < 0) {
                JOptionPane.showMessageDialog(this, "Giá trị đơn hàng phải lớn hơn hoặc bằng 0");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Giá trị đơn hàng phải là số");
            return;
        }
        try {
            luot = Integer.parseInt(luotDung);
            if (luot <= 0) {
                JOptionPane.showMessageDialog(this, "Lượt sử dụng phải lớn hơn 0");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lượt Sử dụng phải là số");
            return;
        }

        phieuGiamGiaChiTiet.setNgayBatDau(LocalDate.parse(batDau));
        phieuGiamGiaChiTiet.setNgayKetThuc(LocalDate.parse(ketThuc));
        trangThai = setTrangThai(LocalDate.parse(batDau), LocalDate.parse(ketThuc));
        phieuGiamGiaChiTiet.setLuotSuDung(luot);
        phieuGiamGiaChiTiet.setDieuKien(dieuKien);
        phieuGiamGiaChiTiet.setGiaTri(mucGiam);
        phieuGiamGiaChiTiet.setTrangThai(trangThai);
        String ma = txtMaVoucher.getText();
        for (PhieuGiamGiaResponse pr : qs.getall()) {
            if (ma.equalsIgnoreCase(pr.getMaPhieu())) {
                JOptionPane.showMessageDialog(this, "Mã đã tồn tại");
                return;
            }
        }
        String ten = txtTenPhieu.getText();
        for (PhieuGiamGiaResponse pr : qs.getall()) {
            if (ten.equalsIgnoreCase(pr.getTenPhieu())) {
                JOptionPane.showMessageDialog(this, "Tên đã tồn tại");
                return;
            }
        }
        phieuGiamGia.setMaPhieu(ma);
        phieuGiamGia.setTenPhieu(ten);
        phieuGiamGia.setPhieuGiamGiaChiTiet(phieuGiamGiaChiTiet);
        JOptionPane.showMessageDialog(this, qs.add(phieuGiamGia));
        check = true;
        dispose();
        jplGiamGia.loadTable(new PhieuGiamGiaServiceImpl().getall());


    }//GEN-LAST:event_btnXacNhanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtGiaTriToiThieu;
    private javax.swing.JTextField txtLuotDung;
    private javax.swing.JTextField txtMaVoucher;
    private javax.swing.JTextField txtMucGiam;
    private com.toedter.calendar.JDateChooser txtNgayBatDau;
    private com.toedter.calendar.JDateChooser txtNgayKetThuc;
    private javax.swing.JTextField txtTenPhieu;
    // End of variables declaration//GEN-END:variables
}
