package view;

import view.Contains.jplBanHang;
import view.Contains.jplBaoHanh;
import view.Contains.jplGiamGia;
import view.Contains.jplHeThong;
import view.Contains.jplHoaDon;
import view.Contains.jplSanPham;
import view.Contains.jplTrangChu;
import java.awt.Color;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import service.QuanLyNhanVienService;
import service.impl.QuanLyNhanVienServiceImpl;
import view.Contains.jplKhachHang;
import view.Contains.jplTraGop;
import view.Contains.tragop.ViewTraGopForm;
import viewmodel.NhanVienResponse;

public class PhanMemBanDienThoai extends javax.swing.JFrame {

    private JPanel panel;
    private JPanel panelBanHang;
    private NhanVienResponse nhanVienOnline;
    private QuanLyNhanVienService service;

    public PhanMemBanDienThoai(NhanVienResponse nv) {
        initComponents();
        setLocationRelativeTo(null);
        service = new QuanLyNhanVienServiceImpl();
        panel = new jplTrangChu();
        setPanel(panel);
        setQuyen(nv);
        panelBanHang = new jplBanHang(nv);
        nhanVienOnline = nv;

    }

    private void setQuyen(NhanVienResponse nv) {
        lblTaiKhoan.setText(nv.getHoTen().substring(nv.getHoTen().lastIndexOf(" ") + 1));
        if (nv.isChucVu() == false) {
            btnHeThong.setVisible(true);
        } else {
            btnHeThong.setVisible(false);
        }
    }

    private Boolean kiemTraDoiMatKhau() {
        StringBuilder sb = new StringBuilder();
        if (txtMatKhauCu.getText().isBlank()) {
            sb.append("Không để trống mật khẩu cũ\n");
        } else if (!txtMatKhauCu.getText().trim().equals(nhanVienOnline.getMatKhau())) {
            sb.append("Sai mật khẩu cũ\n");
        }

        if (txtMatKhauMoi.getText().isBlank()) {
            sb.append("Không để trống mật khẩu mới\n");
        }
        if (txtNhapLaiMatKhau.getText().isBlank()) {
            sb.append("Không để trống ô nhập lại mật khẩu\n");
        }

        if (!txtMatKhauMoi.getText().trim().equals(txtNhapLaiMatKhau.getText().trim())) {
            sb.append("Vui lòng nhập mật khẩu mới và nhập  lại giống nhau\n");
        }

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return false;
        }
        return true;
    }

    public PhanMemBanDienThoai(JPanel panel) {
        setPanel(panel);
    }

    public PhanMemBanDienThoai() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DoiMatKhau = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMatKhauCu = new javax.swing.JTextField();
        txtMatKhauMoi = new javax.swing.JTextField();
        txtNhapLaiMatKhau = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        ThongTinNhanVien = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lbHoTen = new javax.swing.JLabel();
        lbGioiTinh = new javax.swing.JLabel();
        lbSdt = new javax.swing.JLabel();
        lbNgaySinh = new javax.swing.JLabel();
        lbDiaChi = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        lbAnh = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lbChucVu = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lbTrangThai = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnTrangChu = new javax.swing.JPanel();
        lblTrangChu = new javax.swing.JLabel();
        btnSanPham = new javax.swing.JPanel();
        lblSanPham = new javax.swing.JLabel();
        btnBanHang = new javax.swing.JPanel();
        lblBanHang = new javax.swing.JLabel();
        btnKhachHang = new javax.swing.JPanel();
        lblKhachHang = new javax.swing.JLabel();
        btnHoaDon = new javax.swing.JPanel();
        lblHoaDon = new javax.swing.JLabel();
        btnGiamGia = new javax.swing.JPanel();
        lblGiamGia = new javax.swing.JLabel();
        btnTraGop = new javax.swing.JPanel();
        lblTraGop = new javax.swing.JLabel();
        btnBaoHanh = new javax.swing.JPanel();
        lblBaoHanh = new javax.swing.JLabel();
        btnHeThong = new javax.swing.JPanel();
        lblHeThong = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTaiKhoan = new javax.swing.JLabel();
        lblDoiMatKhau = new javax.swing.JLabel();
        lblDangXuat = new javax.swing.JLabel();
        jplContain = new javax.swing.JPanel();

        DoiMatKhau.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DoiMatKhau.setTitle("Đổi Mật Khẩu");
        DoiMatKhau.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(47, 85, 212));
        jLabel3.setText("ĐỔI MẬT KHẨU");

        jLabel4.setText("Mật Khẩu Cũ:");

        jLabel5.setText("Mật Khẩu Mới:");

        jLabel6.setText("Nhập lại:");

        txtMatKhauCu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        txtMatKhauMoi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        txtNhapLaiMatKhau.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        jButton1.setBackground(new java.awt.Color(47, 85, 212));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Đổi Mật Khẩu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMatKhauCu)
                    .addComponent(txtMatKhauMoi)
                    .addComponent(txtNhapLaiMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(142, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jLabel3))
                .addGap(122, 122, 122))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMatKhauCu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNhapLaiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout DoiMatKhauLayout = new javax.swing.GroupLayout(DoiMatKhau.getContentPane());
        DoiMatKhau.getContentPane().setLayout(DoiMatKhauLayout);
        DoiMatKhauLayout.setHorizontalGroup(
            DoiMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        DoiMatKhauLayout.setVerticalGroup(
            DoiMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        ThongTinNhanVien.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ThongTinNhanVien.setTitle("THÔNG TIN NHÂN VIÊN");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setText("HỌ VÀ TÊN:");

        jLabel8.setText("GIỚI TÍNH:");

        jLabel9.setText("SDT:");

        jLabel10.setText("NGÀY SINH:");

        jLabel11.setText("ĐỊA CHỈ");

        jLabel12.setText("EMAIL:");

        lbHoTen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbHoTen.setForeground(new java.awt.Color(255, 51, 51));
        lbHoTen.setText("jLabel13");

        lbGioiTinh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbGioiTinh.setForeground(new java.awt.Color(255, 51, 51));
        lbGioiTinh.setText("jLabel14");

        lbSdt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbSdt.setForeground(new java.awt.Color(255, 51, 51));
        lbSdt.setText("jLabel15");

        lbNgaySinh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbNgaySinh.setForeground(new java.awt.Color(255, 51, 51));
        lbNgaySinh.setText("jLabel16");

        lbDiaChi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbDiaChi.setForeground(new java.awt.Color(255, 51, 51));
        lbDiaChi.setText("jLabel17");

        lbEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbEmail.setForeground(new java.awt.Color(255, 51, 51));
        lbEmail.setText("jLabel18");

        lbAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAnh.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel20.setText("CHỨC VỤ:");

        lbChucVu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbChucVu.setForeground(new java.awt.Color(255, 51, 51));
        lbChucVu.setText("jLabel21");

        jLabel22.setText("TRẠNG THÁI:");

        lbTrangThai.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTrangThai.setForeground(new java.awt.Color(255, 51, 51));
        lbTrangThai.setText("jLabel23");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel20)
                    .addComponent(jLabel22))
                .addGap(55, 55, 55)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lbTrangThai)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lbChucVu)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbSdt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbGioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                        .addComponent(lbAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lbHoTen))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(lbGioiTinh))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(lbSdt))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(lbNgaySinh))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(lbDiaChi))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(lbEmail)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(lbAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(lbChucVu))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(lbTrangThai))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ThongTinNhanVienLayout = new javax.swing.GroupLayout(ThongTinNhanVien.getContentPane());
        ThongTinNhanVien.getContentPane().setLayout(ThongTinNhanVienLayout);
        ThongTinNhanVienLayout.setHorizontalGroup(
            ThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ThongTinNhanVienLayout.setVerticalGroup(
            ThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PHẦN MỀM QUẢN LÝ BÁN ĐIỆN THOẠI 1.0");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(38, 68, 170));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/logo.png"))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(38, 68, 170));
        jPanel3.setLayout(new java.awt.GridLayout(9, 1, 0, 10));

        btnTrangChu.setBackground(new java.awt.Color(38, 68, 170));
        btnTrangChu.setLayout(new java.awt.GridLayout(1, 1));

        lblTrangChu.setBackground(new java.awt.Color(38, 68, 170));
        lblTrangChu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTrangChu.setForeground(new java.awt.Color(255, 255, 255));
        lblTrangChu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/home.png"))); // NOI18N
        lblTrangChu.setText("TRANG CHỦ");
        lblTrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTrangChuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblTrangChuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblTrangChuMouseExited(evt);
            }
        });
        btnTrangChu.add(lblTrangChu);

        jPanel3.add(btnTrangChu);

        btnSanPham.setBackground(new java.awt.Color(38, 68, 170));
        btnSanPham.setLayout(new java.awt.GridLayout(1, 1));

        lblSanPham.setBackground(new java.awt.Color(38, 68, 170));
        lblSanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/product.png"))); // NOI18N
        lblSanPham.setText("SẢN PHẨM");
        lblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSanPhamMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblSanPhamMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblSanPhamMouseExited(evt);
            }
        });
        btnSanPham.add(lblSanPham);

        jPanel3.add(btnSanPham);

        btnBanHang.setBackground(new java.awt.Color(38, 68, 170));
        btnBanHang.setLayout(new java.awt.GridLayout(1, 1));

        lblBanHang.setBackground(new java.awt.Color(38, 68, 170));
        lblBanHang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblBanHang.setForeground(new java.awt.Color(255, 255, 255));
        lblBanHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBanHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/cart.png"))); // NOI18N
        lblBanHang.setText("BÁN HÀNG");
        lblBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBanHangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblBanHangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblBanHangMouseExited(evt);
            }
        });
        btnBanHang.add(lblBanHang);

        jPanel3.add(btnBanHang);

        btnKhachHang.setBackground(new java.awt.Color(38, 68, 170));
        btnKhachHang.setLayout(new java.awt.GridLayout(1, 1));

        lblKhachHang.setBackground(new java.awt.Color(38, 68, 170));
        lblKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        lblKhachHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/khachhang.png"))); // NOI18N
        lblKhachHang.setText("KHÁCH HÀNG");
        lblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblKhachHangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblKhachHangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblKhachHangMouseExited(evt);
            }
        });
        btnKhachHang.add(lblKhachHang);

        jPanel3.add(btnKhachHang);

        btnHoaDon.setBackground(new java.awt.Color(38, 68, 170));
        btnHoaDon.setLayout(new java.awt.GridLayout(1, 1));

        lblHoaDon.setBackground(new java.awt.Color(38, 68, 170));
        lblHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        lblHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/HOADON.png"))); // NOI18N
        lblHoaDon.setText("HÓA ĐƠN");
        lblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHoaDonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblHoaDonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblHoaDonMouseExited(evt);
            }
        });
        btnHoaDon.add(lblHoaDon);

        jPanel3.add(btnHoaDon);

        btnGiamGia.setBackground(new java.awt.Color(38, 68, 170));
        btnGiamGia.setLayout(new java.awt.GridLayout(1, 1));

        lblGiamGia.setBackground(new java.awt.Color(38, 68, 170));
        lblGiamGia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblGiamGia.setForeground(new java.awt.Color(255, 255, 255));
        lblGiamGia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGiamGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-shopping-cart-promotion-40.png"))); // NOI18N
        lblGiamGia.setText("GIẢM GIÁ");
        lblGiamGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGiamGiaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblGiamGiaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblGiamGiaMouseExited(evt);
            }
        });
        btnGiamGia.add(lblGiamGia);

        jPanel3.add(btnGiamGia);

        btnTraGop.setBackground(new java.awt.Color(38, 68, 170));
        btnTraGop.setLayout(new java.awt.GridLayout(1, 1));

        lblTraGop.setBackground(new java.awt.Color(38, 68, 170));
        lblTraGop.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTraGop.setForeground(new java.awt.Color(255, 255, 255));
        lblTraGop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTraGop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-cash-in-hand-40.png"))); // NOI18N
        lblTraGop.setText("TRẢ GÓP");
        lblTraGop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTraGopMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblTraGopMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblTraGopMouseExited(evt);
            }
        });
        btnTraGop.add(lblTraGop);

        jPanel3.add(btnTraGop);

        btnBaoHanh.setBackground(new java.awt.Color(38, 68, 170));
        btnBaoHanh.setLayout(new java.awt.GridLayout(1, 1));

        lblBaoHanh.setBackground(new java.awt.Color(38, 68, 170));
        lblBaoHanh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblBaoHanh.setForeground(new java.awt.Color(255, 255, 255));
        lblBaoHanh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBaoHanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-online-maintenance-portal-40.png"))); // NOI18N
        lblBaoHanh.setText("BẢO HÀNH");
        lblBaoHanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBaoHanhMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblBaoHanhMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblBaoHanhMouseExited(evt);
            }
        });
        btnBaoHanh.add(lblBaoHanh);

        jPanel3.add(btnBaoHanh);

        btnHeThong.setBackground(new java.awt.Color(38, 68, 170));
        btnHeThong.setLayout(new java.awt.GridLayout(1, 1));

        lblHeThong.setBackground(new java.awt.Color(38, 68, 170));
        lblHeThong.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblHeThong.setForeground(new java.awt.Color(255, 255, 255));
        lblHeThong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeThong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-settings-40.png"))); // NOI18N
        lblHeThong.setText("HỆ THỐNG");
        lblHeThong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHeThongMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblHeThongMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblHeThongMouseExited(evt);
            }
        });
        btnHeThong.add(lblHeThong);

        jPanel3.add(btnHeThong);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Xin Chào,");

        lblTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        lblTaiKhoan.setText("Bình");
        lblTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTaiKhoanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblTaiKhoanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblTaiKhoanMouseExited(evt);
            }
        });

        lblDoiMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDoiMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        lblDoiMatKhau.setText("Đổi Mật Khẩu");
        lblDoiMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDoiMatKhauMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblDoiMatKhauMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDoiMatKhauMouseExited(evt);
            }
        });

        lblDangXuat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDangXuat.setForeground(new java.awt.Color(255, 255, 255));
        lblDangXuat.setText("Đăng Xuất?");
        lblDangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDangXuatMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblDangXuatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDangXuatMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTaiKhoan)
                .addGap(32, 32, 32))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDoiMatKhau)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblDangXuat)))
                .addGap(54, 54, 54))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblTaiKhoan))
                .addGap(18, 18, 18)
                .addComponent(lblDoiMatKhau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                .addContainerGap())
        );

        jplContain.setBackground(new java.awt.Color(255, 255, 255));
        jplContain.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jplContain.setLayout(new javax.swing.BoxLayout(jplContain, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jplContain, javax.swing.GroupLayout.DEFAULT_SIZE, 965, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jplContain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setColor(JPanel p) {
        p.setBackground(new Color(170, 190, 255));
    }

    private void resetColor(JPanel p) {
        p.setBackground(new Color(38, 68, 170));
    }

    private void setPanel(JPanel p) {
        jplContain.removeAll();
        jplContain.add(p);
        jplContain.updateUI();
    }
    private void lblTrangChuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTrangChuMouseExited
        resetColor(btnTrangChu);

    }//GEN-LAST:event_lblTrangChuMouseExited

    private void lblTrangChuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTrangChuMouseEntered
        setColor(btnTrangChu);
    }//GEN-LAST:event_lblTrangChuMouseEntered

    private void lblDoiMatKhauMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoiMatKhauMouseEntered
        lblDoiMatKhau.setForeground(Color.red);
    }//GEN-LAST:event_lblDoiMatKhauMouseEntered

    private void lblDoiMatKhauMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoiMatKhauMouseExited
        lblDoiMatKhau.setForeground(Color.WHITE);
    }//GEN-LAST:event_lblDoiMatKhauMouseExited

    private void lblDoiMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoiMatKhauMouseClicked
        DoiMatKhau.setSize(400, 340);
        DoiMatKhau.setLocationRelativeTo(null);
        DoiMatKhau.setVisible(true);
    }//GEN-LAST:event_lblDoiMatKhauMouseClicked

    private void lblSanPhamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSanPhamMouseEntered
        setColor(btnSanPham);
    }//GEN-LAST:event_lblSanPhamMouseEntered

    private void lblSanPhamMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSanPhamMouseExited
        resetColor(btnSanPham);
    }//GEN-LAST:event_lblSanPhamMouseExited

    private void lblBanHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBanHangMouseEntered
        setColor(btnBanHang);
    }//GEN-LAST:event_lblBanHangMouseEntered

    private void lblBanHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBanHangMouseExited
        resetColor(btnBanHang);
    }//GEN-LAST:event_lblBanHangMouseExited

    private void lblHoaDonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHoaDonMouseEntered
        setColor(btnHoaDon);
    }//GEN-LAST:event_lblHoaDonMouseEntered

    private void lblHoaDonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHoaDonMouseExited
        resetColor(btnHoaDon);
    }//GEN-LAST:event_lblHoaDonMouseExited

    private void lblGiamGiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGiamGiaMouseEntered
        setColor(btnGiamGia);
    }//GEN-LAST:event_lblGiamGiaMouseEntered

    private void lblGiamGiaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGiamGiaMouseExited
        resetColor(btnGiamGia);
    }//GEN-LAST:event_lblGiamGiaMouseExited

    private void lblBaoHanhMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBaoHanhMouseEntered
        setColor(btnBaoHanh);
    }//GEN-LAST:event_lblBaoHanhMouseEntered

    private void lblBaoHanhMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBaoHanhMouseExited
        resetColor(btnBaoHanh);
    }//GEN-LAST:event_lblBaoHanhMouseExited

    private void lblHeThongMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHeThongMouseEntered
        setColor(btnHeThong);
    }//GEN-LAST:event_lblHeThongMouseEntered

    private void lblHeThongMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHeThongMouseExited
        resetColor(btnHeThong);
    }//GEN-LAST:event_lblHeThongMouseExited

    private void lblTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTrangChuMouseClicked
        panel = new jplTrangChu();
        setPanel(panel);
    }//GEN-LAST:event_lblTrangChuMouseClicked

    private void lblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSanPhamMouseClicked
        panel = new jplSanPham();
        setPanel(panel);
    }//GEN-LAST:event_lblSanPhamMouseClicked

    private void lblBanHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBanHangMouseClicked

//        panel = new ViewTraGopForm();
        setPanel(panelBanHang);
    }//GEN-LAST:event_lblBanHangMouseClicked

    private void lblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHoaDonMouseClicked
        panel = new jplHoaDon();
        setPanel(panel);
    }//GEN-LAST:event_lblHoaDonMouseClicked

    private void lblGiamGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGiamGiaMouseClicked
        panel = new jplGiamGia();
        setPanel(panel);
    }//GEN-LAST:event_lblGiamGiaMouseClicked

    private void lblBaoHanhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBaoHanhMouseClicked
        panel = new jplBaoHanh();
        setPanel(panel);
    }//GEN-LAST:event_lblBaoHanhMouseClicked

    private void lblHeThongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHeThongMouseClicked
        panel = new jplHeThong();
        setPanel(panel);
    }//GEN-LAST:event_lblHeThongMouseClicked

    private void lblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKhachHangMouseClicked
        panel = new jplKhachHang();
        setPanel(panel);
    }//GEN-LAST:event_lblKhachHangMouseClicked

    private void lblKhachHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKhachHangMouseEntered
        setColor(btnKhachHang);
    }//GEN-LAST:event_lblKhachHangMouseEntered

    private void lblKhachHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKhachHangMouseExited
        resetColor(btnKhachHang);
    }//GEN-LAST:event_lblKhachHangMouseExited

    private void lblTraGopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTraGopMouseClicked
        panel = new jplTraGop();
        setPanel(panel);
    }//GEN-LAST:event_lblTraGopMouseClicked

    private void lblTraGopMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTraGopMouseEntered
        setColor(btnTraGop);
    }//GEN-LAST:event_lblTraGopMouseEntered

    private void lblTraGopMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTraGopMouseExited
        resetColor(btnTraGop);
    }//GEN-LAST:event_lblTraGopMouseExited

    private void lblDangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDangXuatMouseClicked
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblDangXuatMouseClicked

    private void lblDangXuatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDangXuatMouseEntered
        lblDangXuat.setForeground(Color.red);
    }//GEN-LAST:event_lblDangXuatMouseEntered

    private void lblDangXuatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDangXuatMouseExited
        lblDangXuat.setForeground(Color.WHITE);
    }//GEN-LAST:event_lblDangXuatMouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (kiemTraDoiMatKhau()) {
            JOptionPane.showMessageDialog(this, service.updateMatKhau(nhanVienOnline, txtMatKhauMoi.getText().trim()));

        }
        DoiMatKhau.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void lblTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTaiKhoanMouseClicked
        ThongTinNhanVien.setSize(650, 350);
        ThongTinNhanVien.setLocationRelativeTo(null);
        ThongTinNhanVien.setResizable(false);
        ThongTinNhanVien.setVisible(true);
        lbHoTen.setText(nhanVienOnline.getHoTen());
        lbGioiTinh.setText(nhanVienOnline.isGioiTinh() ? "Nam" : "Nữ");
        lbSdt.setText(nhanVienOnline.getSdt());
        // Lấy ngày sinh từ đối tượng nhanVienOnline
        LocalDate ngaySinh = nhanVienOnline.getNgaySinh();

        // Chuyển đổi ngày sinh thành đối tượng Instant
        Instant instant = ngaySinh.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();

        // Chuyển đổi Instant sang Date
        Date date = Date.from(instant);

        // Chuyển đổi ngày sinh thành chuỗi sử dụng định dạng mong muốn
        String ngaySinhString = new SimpleDateFormat("dd/MM/yyyy").format(date);
        lbNgaySinh.setText(ngaySinhString);
        lbDiaChi.setText(nhanVienOnline.getDiaChi());
        lbEmail.setText(nhanVienOnline.getEmail());
        lbChucVu.setText(nhanVienOnline.isChucVu() == false ? "Quản Lý" : "Nhân Viên");
        lbTrangThai.setText(nhanVienOnline.isTrangThai() == true ? "Đang Làm" : "Nghỉ Việc");
        ImageIcon icon = new ImageIcon(getClass().getResource("/Icon/" + nhanVienOnline.getHinhAnh()));
        Image newImage = icon.getImage().getScaledInstance(169, 213, Image.SCALE_SMOOTH);
        lbAnh.setIcon(new ImageIcon(newImage));

    }//GEN-LAST:event_lblTaiKhoanMouseClicked

    private void lblTaiKhoanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTaiKhoanMouseEntered
        lblTaiKhoan.setForeground(Color.red);
    }//GEN-LAST:event_lblTaiKhoanMouseEntered

    private void lblTaiKhoanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTaiKhoanMouseExited
        lblTaiKhoan.setForeground(Color.white);
    }//GEN-LAST:event_lblTaiKhoanMouseExited

//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new PhanMemBanDienThoai().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DoiMatKhau;
    private javax.swing.JDialog ThongTinNhanVien;
    private javax.swing.JPanel btnBanHang;
    private javax.swing.JPanel btnBaoHanh;
    private javax.swing.JPanel btnGiamGia;
    private javax.swing.JPanel btnHeThong;
    private javax.swing.JPanel btnHoaDon;
    private javax.swing.JPanel btnKhachHang;
    private javax.swing.JPanel btnSanPham;
    private javax.swing.JPanel btnTraGop;
    private javax.swing.JPanel btnTrangChu;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jplContain;
    private javax.swing.JLabel lbAnh;
    private javax.swing.JLabel lbChucVu;
    private javax.swing.JLabel lbDiaChi;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbGioiTinh;
    private javax.swing.JLabel lbHoTen;
    private javax.swing.JLabel lbNgaySinh;
    private javax.swing.JLabel lbSdt;
    private javax.swing.JLabel lbTrangThai;
    private javax.swing.JLabel lblBanHang;
    private javax.swing.JLabel lblBaoHanh;
    private javax.swing.JLabel lblDangXuat;
    private javax.swing.JLabel lblDoiMatKhau;
    private javax.swing.JLabel lblGiamGia;
    private javax.swing.JLabel lblHeThong;
    private javax.swing.JLabel lblHoaDon;
    private javax.swing.JLabel lblKhachHang;
    private javax.swing.JLabel lblSanPham;
    private javax.swing.JLabel lblTaiKhoan;
    private javax.swing.JLabel lblTraGop;
    private javax.swing.JLabel lblTrangChu;
    private javax.swing.JTextField txtMatKhauCu;
    private javax.swing.JTextField txtMatKhauMoi;
    private javax.swing.JTextField txtNhapLaiMatKhau;
    // End of variables declaration//GEN-END:variables
}
