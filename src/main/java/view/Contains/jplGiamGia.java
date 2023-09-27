/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.Contains;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import view.Contains.phieuGiamGia.ThemPhieuGiamGia;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.hibernate.hql.spi.id.local.AfterUseAction;
import service.impl.PhieuGiamGiaServiceImpl;
import view.Contains.phieuGiamGia.SuaPhieuGiamGia;
import view.Login;
import viewmodel.PhieuGiamGiaResponse;
import service.PhieuGiamGiaService;
import static view.Contains.phieuGiamGia.SuaPhieuGiamGia.check;
import viewmodel.HoaDonResponse;

public class jplGiamGia extends javax.swing.JPanel {

    PhieuGiamGiaService qs;
    public static DefaultTableModel dtm;

    public jplGiamGia() {
        initComponents();
        qs = new PhieuGiamGiaServiceImpl();
        dtm = (DefaultTableModel) tblMaGiamGia.getModel();
        updateStatus();
        loadTable(qs.getall());
        rdbTatCa.setSelected(true);
        txtDoanhThu.setText(""+qs.tongTien()+" VNĐ");
        txtDonHang.setText(""+qs.tongDon());
        txtTienGiam.setText(""+qs.tienGiam()+" VNĐ");
    }

    public static void loadTable(List<PhieuGiamGiaResponse> list) {
        dtm.setRowCount(0);
        for (PhieuGiamGiaResponse phieu : list) {
            dtm.addRow(phieu.toDataRow());
        }
    }

    public int getStatus(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
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

    private void updateStatus() {
        for (PhieuGiamGiaResponse phieu : qs.getall()) {
            int trangThai = 0;
            trangThai = getStatus(phieu.getNgayBatDau(), phieu.getNgayKetThuc());
            phieu.setTrangThai(trangThai);
            qs.update(phieu);
        }
//        loadTable(qs.getall());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDoanhThu = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtDonHang = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtTienGiam = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        rdbTatCa = new javax.swing.JRadioButton();
        rdbSapBatDau = new javax.swing.JRadioButton();
        rdbDangDienRa = new javax.swing.JRadioButton();
        rdbDaKetThuc = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMaGiamGia = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        cbbTimKiem = new javax.swing.JComboBox<>();
        btnTaoPhieu = new javax.swing.JButton();

        jButton2.setBackground(new java.awt.Color(47, 85, 212));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/add.png"))); // NOI18N
        jButton2.setText("THÊM");
        jButton2.setBorderPainted(false);
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(935, 738));

        jPanel1.setBackground(new java.awt.Color(246, 246, 246));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("MÃ GIẢM GIÁ CỦA SHOP");

        jPanel2.setLayout(new java.awt.GridLayout(1, 4, 10, 10));

        jPanel3.setBackground(new java.awt.Color(246, 246, 246));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(204, 204, 204)));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Doanh thu.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("DOANH THU");

        txtDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtDoanhThu.setForeground(new java.awt.Color(50, 160, 239));
        txtDoanhThu.setText("0VNĐ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(33, 33, 33))
        );

        jPanel2.add(jPanel3);

        jPanel4.setBackground(new java.awt.Color(246, 246, 246));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 3, new java.awt.Color(204, 204, 204)));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("ĐƠN HÀNG ");

        txtDonHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtDonHang.setForeground(new java.awt.Color(50, 239, 148));
        txtDonHang.setText("0");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/sanpham.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDonHang, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDonHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(36, 36, 36))
        );

        jPanel2.add(jPanel4);

        jPanel5.setBackground(new java.awt.Color(246, 246, 246));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 3, new java.awt.Color(204, 204, 204)));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/baohanh.png"))); // NOI18N

        txtTienGiam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTienGiam.setForeground(new java.awt.Color(239, 220, 50));
        txtTienGiam.setText("0");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("TỔNG TIỀN GIẢM");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTienGiam, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTienGiam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(34, 34, 34))
        );

        jPanel2.add(jPanel5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(246, 246, 246));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("DANH SÁCH MÃ GIẢM GIÁ");

        jPanel8.setLayout(new java.awt.GridLayout(1, 4, 10, 10));

        rdbTatCa.setBackground(new java.awt.Color(246, 246, 246));
        buttonGroup1.add(rdbTatCa);
        rdbTatCa.setText("Tất cả");
        rdbTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbTatCaActionPerformed(evt);
            }
        });

        rdbSapBatDau.setBackground(new java.awt.Color(246, 246, 246));
        buttonGroup1.add(rdbSapBatDau);
        rdbSapBatDau.setText("Sắp diễn ra");
        rdbSapBatDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbSapBatDauActionPerformed(evt);
            }
        });

        rdbDangDienRa.setBackground(new java.awt.Color(246, 246, 246));
        buttonGroup1.add(rdbDangDienRa);
        rdbDangDienRa.setText("Đang diễn ra");
        rdbDangDienRa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbDangDienRaActionPerformed(evt);
            }
        });

        rdbDaKetThuc.setBackground(new java.awt.Color(246, 246, 246));
        buttonGroup1.add(rdbDaKetThuc);
        rdbDaKetThuc.setText("Đã kết thúc");
        rdbDaKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbDaKetThucActionPerformed(evt);
            }
        });

        tblMaGiamGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Tên voucher", "Mã voucher", "Giảm giá", "Điều kiện", "Lượt sử dụng tối đa", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMaGiamGia.setGridColor(new java.awt.Color(48, 85, 212));
        tblMaGiamGia.setShowGrid(true);
        tblMaGiamGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMaGiamGiaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMaGiamGia);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        cbbTimKiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tên Voucher", "Mã Voucher" }));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbbTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel9Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbbTimKiem, txtTimKiem});

        btnTaoPhieu.setBackground(new java.awt.Color(47, 85, 212));
        btnTaoPhieu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTaoPhieu.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoPhieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/add.png"))); // NOI18N
        btnTaoPhieu.setText("TẠO");
        btnTaoPhieu.setBorderPainted(false);
        btnTaoPhieu.setFocusable(false);
        btnTaoPhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoPhieuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(52, 52, 52))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(rdbTatCa)
                                        .addGap(31, 31, 31)
                                        .addComponent(rdbDangDienRa)
                                        .addGap(40, 40, 40)
                                        .addComponent(rdbSapBatDau)
                                        .addGap(39, 39, 39)
                                        .addComponent(rdbDaKetThuc)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(btnTaoPhieu)
                        .addGap(24, 24, 24))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdbTatCa)
                                .addComponent(rdbDangDienRa))
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdbSapBatDau)
                                .addComponent(rdbDaKetThuc)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTaoPhieu)
                        .addGap(18, 18, 18)))
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnTaoPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoPhieuActionPerformed
        // TODO add your handling code here:
        new ThemPhieuGiamGia().setVisible(true);
        if (SuaPhieuGiamGia.check) {
            loadTable(qs.getall());
        }
    }//GEN-LAST:event_btnTaoPhieuActionPerformed

    private void tblMaGiamGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMaGiamGiaMouseClicked
        // TODO add your handling code here:
        int index = tblMaGiamGia.getSelectedRow();
        PhieuGiamGiaResponse phieu = qs.getall().get(index);
        new SuaPhieuGiamGia(phieu).setVisible(true);
        if (SuaPhieuGiamGia.check) {
            loadTable(qs.getall());
        }

    }//GEN-LAST:event_tblMaGiamGiaMouseClicked

    private void rdbTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbTatCaActionPerformed
        // TODO add your handling code here:
        loadTable(qs.getall());
        txtTimKiem.setText("");

    }//GEN-LAST:event_rdbTatCaActionPerformed

    private void rdbDangDienRaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbDangDienRaActionPerformed
        // TODO add your handling code here:
        loadTable(qs.getByStatus(0));
        txtTimKiem.setText("");

    }//GEN-LAST:event_rdbDangDienRaActionPerformed

    private void rdbSapBatDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbSapBatDauActionPerformed
        // TODO add your handling code here:
        loadTable(qs.getByStatus(2));
        txtTimKiem.setText("");

    }//GEN-LAST:event_rdbSapBatDauActionPerformed

    private void rdbDaKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbDaKetThucActionPerformed
        // TODO add your handling code here:
        loadTable(qs.getByStatus(1));
        txtTimKiem.setText("");
    }//GEN-LAST:event_rdbDaKetThucActionPerformed

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        // TODO add your handling code here:
        String tim = txtTimKiem.getText();
        if (cbbTimKiem.getSelectedIndex() == 0) {
            loadTable(qs.getByName(tim));
        } else if (cbbTimKiem.getSelectedIndex() == 1) {
            loadTable(qs.getByMa(tim));
        }
    }//GEN-LAST:event_txtTimKiemCaretUpdate

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTaoPhieu;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbTimKiem;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdbDaKetThuc;
    private javax.swing.JRadioButton rdbDangDienRa;
    private javax.swing.JRadioButton rdbSapBatDau;
    private javax.swing.JRadioButton rdbTatCa;
    private javax.swing.JTable tblMaGiamGia;
    private javax.swing.JLabel txtDoanhThu;
    private javax.swing.JLabel txtDonHang;
    private javax.swing.JLabel txtTienGiam;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
