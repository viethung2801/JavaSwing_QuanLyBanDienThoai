package view.Contains;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.HoaDon;
import model.PhieuGiamGia;
import service.HoaDonService;
import service.impl.HoaDonServiceImpl;
import utility.ExportPdfHoaDon;
import viewmodel.HoaDonChiTietResponse;
import viewmodel.HoaDonResponse;

public class jplHoaDon extends javax.swing.JPanel {

    private List<HoaDonResponse> hoaDonResponseList;
    private List<HoaDonChiTietResponse> hoaDonChiTietResponseList;
    private DefaultTableModel dtmHoaDon;
    private DefaultTableModel dtmHoaDonChiTiet;
    private HoaDonService hoaDonService;
    private ExportPdfHoaDon export;

    public jplHoaDon() {
        initComponents();
        viewTable();

        hoaDonResponseList = new ArrayList<>();
        hoaDonChiTietResponseList = new ArrayList<>();
        dtmHoaDon = (DefaultTableModel) tblHoaDon.getModel();
        dtmHoaDonChiTiet = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        hoaDonService = new HoaDonServiceImpl();
        export = new ExportPdfHoaDon();

        hoaDonResponseList = hoaDonService.getResponsesByTraGop(2);

        showHoaDonTable(hoaDonResponseList);
        startDate.setDate(new Date());
        endDate.setDate(new Date());
    }

    // 1
    private void showHoaDonTable(List<HoaDonResponse> hoaDonResponses) {
        dtmHoaDon.setRowCount(0);
        hoaDonResponses.forEach(h -> dtmHoaDon.addRow(h.toDataRow()));
    }

    // 2
    private void showHoaDonChiTietTable(List<HoaDonChiTietResponse> hoaDonChiTietResponses) {
        dtmHoaDonChiTiet.setRowCount(0);
        hoaDonChiTietResponses.forEach(h -> dtmHoaDonChiTiet.addRow(h.toDataRow()));
    }

    private void viewTable() {
        JTableHeader headerHoaDon = tblHoaDon.getTableHeader();
        JTableHeader headerHoaDonCT = tblHoaDonChiTiet.getTableHeader();

        //Header table hóa đơn
        headerHoaDon.setFont(new Font("tahoma", Font.BOLD, 11));
        headerHoaDon.setBackground(new Color(47, 85, 212));
        headerHoaDon.setForeground(Color.white);

        //Header table hóa đơn ct
        headerHoaDonCT.setFont(new Font("tahoma", Font.BOLD, 11));
        headerHoaDonCT.setBackground(new Color(47, 85, 212));
        headerHoaDonCT.setForeground(Color.white);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        hoaDonChiTiet = new javax.swing.JDialog();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tienKhachDua = new javax.swing.JLabel();
        tienThua = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        kieuThanhToan = new javax.swing.JLabel();
        tienTraTruoc = new javax.swing.JLabel();
        tienThieu = new javax.swing.JLabel();
        maGD = new javax.swing.JLabel();
        lbMaHoaDon = new javax.swing.JLabel();
        lbNgayTao = new javax.swing.JLabel();
        lbNgayThanhToan = new javax.swing.JLabel();
        lbTongTien = new javax.swing.JLabel();
        lbTienGiam = new javax.swing.JLabel();
        lbKhachPhaiTra = new javax.swing.JLabel();
        lbTienKhachDua = new javax.swing.JLabel();
        lbTienThua = new javax.swing.JLabel();
        lbKieuThanhToan = new javax.swing.JLabel();
        lbTienTraTruoc = new javax.swing.JLabel();
        lbTienThieu = new javax.swing.JLabel();
        lbHinhThucThanhToan = new javax.swing.JLabel();
        lbMaGD = new javax.swing.JLabel();
        lbKhachHang = new javax.swing.JLabel();
        lbNhanVien = new javax.swing.JLabel();
        kieuThanhToan2 = new javax.swing.JLabel();
        lbKieuThanhToan2 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lbPhieuGG = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        startDate = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        endDate = new com.toedter.calendar.JDateChooser();
        cbLoc = new javax.swing.JComboBox<>();
        btnXoaLoc = new javax.swing.JButton();
        btnLoc = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbKieuHoaDon = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDonChiTiet = new javax.swing.JTable();

        jMenuItem1.setText("Xem chi tiết");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jLabel7.setText("Mã hóa đơn:");

        jLabel8.setText("Ngày tạo:");

        jLabel9.setText("Ngày thanh toán:");

        jLabel10.setText("Tổng tiền:");

        jLabel11.setText("Số tiền được giảm:");

        jLabel12.setText("Khách phải trả:");

        tienKhachDua.setText("Tiền khách đưa:");

        tienThua.setText("Tiền thừa:");

        jLabel15.setText("Hình thức thanh toán:");

        jLabel16.setText("Khách hàng:");

        jLabel17.setText("Nhân viên:");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setText("Chi Tiết Hóa Đơn");

        kieuThanhToan.setText("Kiểu thanh toán:");

        tienTraTruoc.setText("Tiền trả trước:");

        tienThieu.setText("Tiền thiếu:");

        maGD.setText("Mã giao dịch:");

        lbMaHoaDon.setText("Mã hóa đơn");

        lbNgayTao.setText("Mã hóa đơn");

        lbNgayThanhToan.setText("Mã hóa đơn");

        lbTongTien.setText("Mã hóa đơn");

        lbTienGiam.setText("Mã hóa đơn");

        lbKhachPhaiTra.setText("Mã hóa đơn");

        lbTienKhachDua.setText("Mã hóa đơn");

        lbTienThua.setText("Mã hóa đơn");

        lbKieuThanhToan.setText("Mã hóa đơn");

        lbTienTraTruoc.setText("Mã hóa đơn");

        lbTienThieu.setText("Mã hóa đơn");

        lbHinhThucThanhToan.setText("Mã hóa đơn");

        lbMaGD.setText("Mã hóa đơn");

        lbKhachHang.setText("Mã hóa đơn");

        lbNhanVien.setText("Mã hóa đơn");

        kieuThanhToan2.setText("Kiểu thanh toán:");

        lbKieuThanhToan2.setText("Mã hóa đơn");

        jLabel19.setText("Phiếu giảm giá:");

        lbPhieuGG.setText("Mã hóa đơn");

        javax.swing.GroupLayout hoaDonChiTietLayout = new javax.swing.GroupLayout(hoaDonChiTiet.getContentPane());
        hoaDonChiTiet.getContentPane().setLayout(hoaDonChiTietLayout);
        hoaDonChiTietLayout.setHorizontalGroup(
            hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hoaDonChiTietLayout.createSequentialGroup()
                .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(hoaDonChiTietLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(hoaDonChiTietLayout.createSequentialGroup()
                                .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(70, 70, 70)
                                .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbMaHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                    .addComponent(lbNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbNgayThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(hoaDonChiTietLayout.createSequentialGroup()
                                .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(kieuThanhToan2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(tienThua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tienKhachDua, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(tienTraTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tienThieu, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(kieuThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(69, 69, 69)
                                .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbKieuThanhToan2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbKieuThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbTienTraTruoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(hoaDonChiTietLayout.createSequentialGroup()
                                        .addComponent(lbTienThieu)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(lbTienKhachDua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(hoaDonChiTietLayout.createSequentialGroup()
                                .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(maGD, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48)
                                .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lbHinhThucThanhToan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbMaGD, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addGroup(hoaDonChiTietLayout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)
                                .addComponent(lbKhachPhaiTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(hoaDonChiTietLayout.createSequentialGroup()
                                .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(61, 61, 61)
                                .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbTienGiam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(hoaDonChiTietLayout.createSequentialGroup()
                                .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(82, 82, 82)
                                .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(hoaDonChiTietLayout.createSequentialGroup()
                                        .addComponent(lbPhieuGG, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(hoaDonChiTietLayout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        hoaDonChiTietLayout.setVerticalGroup(
            hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hoaDonChiTietLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel18)
                .addGap(33, 33, 33)
                .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lbMaHoaDon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lbNgayTao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lbNgayThanhToan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbKhachHang)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNhanVien)
                    .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbPhieuGG)
                    .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lbTongTien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lbTienGiam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lbKhachPhaiTra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tienKhachDua)
                    .addComponent(lbTienKhachDua))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tienThua)
                    .addComponent(lbTienThua))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kieuThanhToan)
                    .addComponent(lbKieuThanhToan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tienTraTruoc)
                    .addComponent(lbTienTraTruoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tienThieu)
                    .addComponent(lbTienThieu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kieuThanhToan2)
                    .addComponent(lbKieuThanhToan2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lbHinhThucThanhToan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maGD)
                    .addComponent(lbMaGD))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(47, 85, 212));
        jLabel1.setText("HÓA ĐƠN");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức Năng"));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Lọc theo ngày:");

        jLabel5.setText("Từ");

        startDate.setDateFormatString("yyyy-MM-dd");

        jLabel6.setText("Đến");

        endDate.setDateFormatString("yyyy-MM-dd");

        cbLoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ngày tạo", "Ngày thanh toán" }));
        cbLoc.setLightWeightPopupEnabled(false);
        cbLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLocActionPerformed(evt);
            }
        });

        btnXoaLoc.setText("Xóa lọc");
        btnXoaLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaLocActionPerformed(evt);
            }
        });

        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLoc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoaLoc))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cbLoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addGap(12, 12, 12)
                        .addComponent(endDate, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(btnLoc))
                    .addComponent(btnXoaLoc, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(startDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbLoc)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(endDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Kiểu Hóa Đơn");

        cbKieuHoaDon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Trả góp", "Trả toàn bộ" }));
        cbKieuHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKieuHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(cbKieuHoaDon, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbKieuHoaDon)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Tìm Kiếm:");

        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));
        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtTimKiem))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setBackground(new java.awt.Color(47, 85, 212));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-microsoft-excel-30.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(27, 27, 27)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Tên KH", "Ngày tạo", "Ngày thanh toán", "Tổng tiền", "Số tiền giảm", "Thu ngân"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa Đơn Chi Tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        tblHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên ĐT", "Đơn giá", "IMEI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblHoaDonChiTiet);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                .addGap(267, 267, 267))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbLocActionPerformed

    private void cbKieuHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKieuHoaDonActionPerformed
        String kieuHoaDon = (String) cbKieuHoaDon.getSelectedItem();
        if (kieuHoaDon.equals("Tất cả")) {
            hoaDonResponseList = hoaDonService.getResponsesByTraGop(2);
        } else if (kieuHoaDon.equals("Trả góp")) {
            hoaDonResponseList = hoaDonService.getResponsesByTraGop(1);
        } else if (kieuHoaDon.equals("Trả toàn bộ")) {
            hoaDonResponseList = hoaDonService.getResponsesByTraGop(0);
        }
        showHoaDonTable(hoaDonResponseList);
    }//GEN-LAST:event_cbKieuHoaDonActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        if (SwingUtilities.isRightMouseButton(evt)) {
            int row = tblHoaDon.rowAtPoint(evt.getPoint());
            if (row >= 0) {
                tblHoaDon.setRowSelectionInterval(row, row);
                jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());

            }
        } else {
            int clickedRow = tblHoaDon.getSelectedRow();
            if (clickedRow < 0 || clickedRow > hoaDonResponseList.size()) {
                return;
            }

            HoaDonResponse hoaDonResponse = hoaDonResponseList.get(clickedRow);
            int idHoaDon = hoaDonResponse.getId();
            hoaDonChiTietResponseList = hoaDonService.getChiTietResponsesIdHoaDon(idHoaDon);
            showHoaDonChiTietTable(hoaDonChiTietResponseList);
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        String keyword = txtTimKiem.getText().trim();
        if (!keyword.equals("")) {
            hoaDonResponseList = hoaDonService.searchHoaDon(keyword);
            showHoaDonTable(hoaDonResponseList);
        } else {
            hoaDonResponseList = hoaDonService.getResponsesByTraGop(2);
            showHoaDonTable(hoaDonResponseList);
        }
    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void btnXoaLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaLocActionPerformed
        startDate.setDate(new Date());
        endDate.setDate(new Date());

        hoaDonResponseList = hoaDonService.getResponsesByTraGop(2);
        showHoaDonTable(hoaDonResponseList);
    }//GEN-LAST:event_btnXoaLocActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        hoaDonChiTiet.setSize(420, 540);
        hoaDonChiTiet.setResizable(false);
        hoaDonChiTiet.setLocationRelativeTo(null);
        hoaDonChiTiet.setVisible(true);

        int row = tblHoaDon.getSelectedRow();
        if (row >= 0) {
            HoaDonResponse hd = hoaDonResponseList.get(row);
            HoaDon hoaDon = hoaDonService.getEntityById(hd.getId());

            NumberFormat nf = NumberFormat.getInstance(new Locale("vn", "VN"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            lbMaHoaDon.setText(hd.getMaHoaDon());
            lbNgayTao.setText(formatter.format(hd.getNgayTao()));
            lbNgayThanhToan.setText(formatter.format(hd.getNgayThanhToan()));
            lbKhachHang.setText(hd.getTenKhachHang());
            lbNhanVien.setText(hd.getTenNhanVien());
            if (hoaDon.getPhieuGiamGia() != null) {
                PhieuGiamGia pgg = hoaDon.getPhieuGiamGia();
                lbPhieuGG.setText(pgg.getMaPhieu() + " - " + pgg.getPhieuGiamGiaChiTiet().getGiaTri() + "%");
            } else {
                lbPhieuGG.setText("Không có");
            }
            lbTongTien.setText(nf.format(hd.getTongTien()));
            lbTienGiam.setText(nf.format(hd.getTienGiam()));
            lbKhachPhaiTra.setText(nf.format(hd.getTongTien() - hd.getTienGiam()));

            if (!hd.isTraGop()) {
                lbKieuThanhToan.setText("Trả toàn bộ");
                lbTienKhachDua.setText(nf.format(hd.getTienKhachDua()));
                lbTienThua.setText(nf.format(hd.getTienThua()));
                lbHinhThucThanhToan.setText(hd.isHinhThucThanhToan() ? "Tiền mặt" : "Chuyển khoản");

                displayTraToanBo(true);
                displayTraGop(false);

                if (!hd.isHinhThucThanhToan()) {
                    lbMaGD.setText(hd.getMaGiaoDichChuyenKhoan());
                    displayMaGD(true);
                } else {
                    displayMaGD(false);
                }
            } else {
                lbKieuThanhToan2.setText("Trả góp");
                lbTienTraTruoc.setText(nf.format(hd.getTienTraTruoc()));
                lbTienThieu.setText(nf.format(hd.getTienThieu()));
                lbHinhThucThanhToan.setText(hd.isHinhThucThanhToan() ? "Tiền mặt" : "Chuyển khoản");

                displayTraToanBo(false);
                displayTraGop(true);

                if (!hd.isHinhThucThanhToan()) {
                    lbMaGD.setText(hd.getMaGiaoDichChuyenKhoan());
                    displayMaGD(true);
                } else {
                    displayMaGD(false);
                }
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int clickHoaDonRow = tblHoaDon.getSelectedRow();
        if (clickHoaDonRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn để in");
            return;
        }
        HoaDonResponse hoaDonResponse = hoaDonResponseList.get(clickHoaDonRow);

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn " + hoaDonResponse.getMaHoaDon() + " không?");
        if (confirm == JOptionPane.YES_OPTION) {
            JFileChooser chooser = new JFileChooser("D:\\");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            FileNameExtensionFilter avatarFilter = new FileNameExtensionFilter("Exel File", "xlsx");
            chooser.setFileFilter(avatarFilter);
            chooser.setAcceptAllFileFilterUsed(false);
            int selectFileCheck = chooser.showOpenDialog(this);
            File selectedFile = chooser.getSelectedFile();
            if (!(selectFileCheck == JFileChooser.APPROVE_OPTION)) {
                return;
            }
            String path = selectedFile.getAbsolutePath();
            try {
                HoaDon hoaDon = hoaDonService.getEntityById(hoaDonResponse.getId());
                hoaDonChiTietResponseList = hoaDonService.getChiTietResponsesIdHoaDon(hoaDon.getId());
                export.exportBill3(hoaDon, hoaDonChiTietResponseList, path);
                JOptionPane.showMessageDialog(this, "In hóa đơn thành công");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "In hóa đơn thất bại");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        Date start = startDate.getDate();
        Date end = endDate.getDate();

        if (start == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày tạo trước khi lọc!");
            return;
        } else if (end == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày thanh toán trước khi lọc!");
            return;
        }

        if (start.getTime() > end.getTime()) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước ngày kết thúc!");
            return;
        }

        LocalDate ldStart = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ldEnd = end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        LocalDateTime ldtStart = ldStart.atStartOfDay();
        LocalDateTime ldtEnd = ldEnd.atStartOfDay();
        System.out.println(ldtStart);
        System.out.println(ldtEnd);
        String kieuNgay = (String) cbLoc.getSelectedItem();
        if (kieuNgay.equals("Ngày tạo")) {
            kieuNgay = "NgayTao";
        } else {
            kieuNgay = "NgayThanhToan";
        }
        System.out.println(kieuNgay);
        hoaDonResponseList = hoaDonService.filterHoaDonByDate(ldtStart, ldtEnd, kieuNgay);
        showHoaDonTable(hoaDonResponseList);
    }//GEN-LAST:event_btnLocActionPerformed

    private void displayTraToanBo(boolean b) {
        kieuThanhToan.setVisible(b);
        lbKieuThanhToan.setVisible(b);
        tienKhachDua.setVisible(b);
        tienThua.setVisible(b);
        lbTienKhachDua.setVisible(b);
        lbTienThua.setVisible(b);
    }

    private void displayTraGop(boolean b) {
        kieuThanhToan2.setVisible(b);
        lbKieuThanhToan2.setVisible(b);
        tienTraTruoc.setVisible(b);
        tienThieu.setVisible(b);
        lbTienTraTruoc.setVisible(b);
        lbTienThieu.setVisible(b);
    }

    private void displayMaGD(boolean b) {
        lbMaGD.setVisible(b);
        maGD.setVisible(b);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnXoaLoc;
    private javax.swing.JComboBox<String> cbKieuHoaDon;
    private javax.swing.JComboBox<String> cbLoc;
    private com.toedter.calendar.JDateChooser endDate;
    private javax.swing.JDialog hoaDonChiTiet;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel kieuThanhToan;
    private javax.swing.JLabel kieuThanhToan2;
    private javax.swing.JLabel lbHinhThucThanhToan;
    private javax.swing.JLabel lbKhachHang;
    private javax.swing.JLabel lbKhachPhaiTra;
    private javax.swing.JLabel lbKieuThanhToan;
    private javax.swing.JLabel lbKieuThanhToan2;
    private javax.swing.JLabel lbMaGD;
    private javax.swing.JLabel lbMaHoaDon;
    private javax.swing.JLabel lbNgayTao;
    private javax.swing.JLabel lbNgayThanhToan;
    private javax.swing.JLabel lbNhanVien;
    private javax.swing.JLabel lbPhieuGG;
    private javax.swing.JLabel lbTienGiam;
    private javax.swing.JLabel lbTienKhachDua;
    private javax.swing.JLabel lbTienThieu;
    private javax.swing.JLabel lbTienThua;
    private javax.swing.JLabel lbTienTraTruoc;
    private javax.swing.JLabel lbTongTien;
    private javax.swing.JLabel maGD;
    private com.toedter.calendar.JDateChooser startDate;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDonChiTiet;
    private javax.swing.JLabel tienKhachDua;
    private javax.swing.JLabel tienThieu;
    private javax.swing.JLabel tienThua;
    private javax.swing.JLabel tienTraTruoc;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
