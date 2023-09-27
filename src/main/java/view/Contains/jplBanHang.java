package view.Contains;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.ChiTietPhieuBaoHanh;
import model.DienThoai;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.Imei;
import model.KhachHang;
import model.LichSuTraGop;
import model.LoaiBaoHanh;
import model.NhanVien;
import model.PhieuBaoHanh;
import model.PhieuGiamGia;
import model.PhieuGiamGiaChiTiet;
import model.PhieuTraGop;
import model.TheTichDiem;
import repository.DienThoaiRepository;
import repository.HoaDonRepository;
import repository.ImeiRepository;
import repository.KhachHangRepository;
import repository.LoaiBaoHanhRepository;
import repository.NhanVienRepository;
import repository.PhieuGiamGiaRepository;
import service.DienThoaiService;
import service.HangService;
import service.HoaDonService;
import service.ImeiService;
import service.KhachHangService;
import service.PhieuBaoHanhService;
import service.PhieuGiamGiaService;
import service.PhieuTraGopService;
import service.impl.DienThoaiServiceImpl;
import service.impl.HangServiceImpl;
import service.impl.HoaDonServiceImpl;
import service.impl.ImeiServiceImpl;
import service.impl.KhachHangServiceImpl;
import service.impl.PhieuBaoHanhServiceImpl;
import service.impl.PhieuGiamGiaServiceImpl;
import service.impl.PhieuTraGopServiceImpl;
import utility.ExportPdfHoaDon;
import viewmodel.DienThoaiResponse;
import viewmodel.HangResponse;
import viewmodel.HoaDonChiTietResponse;
import viewmodel.ImeiResponse;
import viewmodel.KhachHangResponse;
import viewmodel.NhanVienResponse;
import viewmodel.PhieuGiamGiaResponse;

public class jplBanHang extends javax.swing.JPanel {
    
    private DefaultTableModel dtmDienThoai;
    
    private List<DienThoaiResponse> dienThoaiResponseList;
    private List<KhachHangResponse> khachHangResponseList;
    private List<HoaDonChiTietResponse> hoaDonChiTietResponseList;
    
    private DienThoaiService dienThoaiService;
    private KhachHangService khachHangService;
    private ImeiService imeiService;
    private PhieuGiamGiaService phieuGiamGiaService;
    private HoaDonService hoaDonService;
    private PhieuBaoHanhService phieuBaoHanhService;
    private PhieuTraGopService phieuTraGopService;
    
    private NumberFormat numberFormat = NumberFormat.getInstance(new Locale("vn", "VN"));
    private static NhanVienResponse loggedNhanVienResponse = new NhanVienResponse();
    private ExportPdfHoaDon export = new ExportPdfHoaDon();
    
    public jplBanHang(NhanVienResponse nv) {
        initComponents();
        
        dtmDienThoai = (DefaultTableModel) tbDienThoai.getModel();
        
        dienThoaiResponseList = new ArrayList<>();
        khachHangResponseList = new ArrayList<>();
        hoaDonChiTietResponseList = new ArrayList<>();
        
        dienThoaiService = new DienThoaiServiceImpl();
        khachHangService = new KhachHangServiceImpl();
        imeiService = new ImeiServiceImpl();
        phieuGiamGiaService = new PhieuGiamGiaServiceImpl();
        hoaDonService = new HoaDonServiceImpl();
        phieuBaoHanhService = new PhieuBaoHanhServiceImpl();
        phieuTraGopService = new PhieuTraGopServiceImpl();
        loggedNhanVienResponse = nv;
        
        dienThoaiResponseList = dienThoaiService.getAllResponseByStatus(true);
        khachHangResponseList = khachHangService.getAllResponseByStatus(1);
        
        showDienThoaiTable(dienThoaiResponseList);
        showHangDtCombobox();

        //khởi tạo giở hàng
        int soDon = jTabbedPane1.getTabCount();
        jTabbedPane1.add(new jplDonHang(++soDon));

        //Khởi tạo cách thanh toán
        viewTable();
        setNhanVien(nv);
        chuyenKhoan(false);
        chuyenKhoan2(false);
    }
    
    private void chuyenKhoan(Boolean b) {
        lbMaGD.setVisible(b);
        txtMaGD.setVisible(b);
    }
    
    private void chuyenKhoan2(Boolean b) {
        lbMaGD2.setVisible(b);
        txtMaGD2.setVisible(b);
    }

    // 0. Set thông tin nhân viên khi đăng nhập
    private void setNhanVien(NhanVienResponse nv) {
//        lbNhanVien.setText(nv.getHoTen().substring(nv.getHoTen().lastIndexOf(" ") + 1) + " " + nv.getId());
        lbNhanVien.setText(nv.getHoTen());
    }

    // 1. hiển thị điện thoại trạng thái = true
    private void showDienThoaiTable(List<DienThoaiResponse> dienThoaiResponses) {
        dtmDienThoai.setRowCount(0);
        dienThoaiResponses.forEach(dt -> dtmDienThoai.addRow(dt.toDataRow2()));
    }

    // 2. hiển thị combobox hãng điện thoại
    private void showHangDtCombobox() {
        HangService hangService = new HangServiceImpl();
        List<HangResponse> hangResponseList = hangService.getAllResponseByStatus(true);
        
        cbHangDT.removeAllItems();
        cbHangDT.addItem("Tất cả");
        hangResponseList.forEach(h -> cbHangDT.addItem(h.getTenHang()));
    }
    
    private void viewTable() {
        JTableHeader Theader = tbDienThoai.getTableHeader();
        
        Theader.setFont(new Font("tahoma", Font.BOLD, 15));
        Theader.setBackground(new Color(47, 85, 212));
        Theader.setForeground(Color.white);
    }
    
    private void setDefault() {
        txtHoTen.setText("");
        txtEmail.setText("");
        txtSdt.setText("");
        jdateNgaySinh.setDate(null);
        txtDiaChi.setText("");
        chkTrangThai.setSelected(false);
    }
    
    private boolean kiemTra(int id, String email) {
        StringBuilder sb = new StringBuilder();
        KhachHangResponse kh = khachHangService.getKhachHangByEmail(email);
        if (txtHoTen.getText().trim().isBlank()) {
            sb.append("Không để trống họ và tên\n");
            
        }
        if (txtEmail.getText().trim().isBlank()) {
            sb.append("Không để trống email\n");
        } else if (!txtEmail.getText().trim().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            sb.append("vui lòng nhập đúng định dạng email\n");
        } else if (kh != null) {
            if (id == 0) {
                String str = "Email đã tồn tại\n";
                
                if (kh.getTrangThai() == 0) {
                    str = str + " trong phần đã xóa\n";
                }
                sb.append(str);
            } else if (id > 0) {
                
                String str = "";
                for (KhachHangResponse s : khachHangResponseList) {
                    if (s.getId() != id) {
                        
                        if (txtEmail.getText().trim().toLowerCase().equals(s.getEmail().toLowerCase()) == true) {
                            str = "Email đã tồn tại\n";
                            if (s.getTrangThai() == 0) {
                                str = str + " trong phần đã xóa\n";
                            }
                            sb.append(str);
                            break;
                        }
                    }
                }
            }
        }
        
        KhachHangResponse khSdt = khachHangService.getKhachHangBySdt(txtSdt.getText().trim());
        if (txtSdt.getText().trim().isBlank()) {
            sb.append("Không để trống SĐT\n");
        } else if (!txtSdt.getText().trim().matches("^(0|\\+84)[1-9][0-9]{8}$")) {
            sb.append("vui lòng nhập đúng định dạng SĐT\n");
        } else if (khSdt != null) {
            if (id == 0) {
                String str = "Sdt đã tồn tại\n";
                
                if (khSdt.getTrangThai() == 0) {
                    str = str + " trong phần đã xóa\n";
                }
                sb.append(str);
            } else if (id > 0) {
                
                String str = "";
                for (KhachHangResponse s : khachHangResponseList) {
                    if (s.getId() != id) {
                        
                        if (txtSdt.getText().trim().toLowerCase().equals(s.getEmail()) == true) {
                            
                            str = "Sdt đã tồn tại\n";
                            if (s.getTrangThai() == 0) {
                                str = str + " trong phần đã xóa\n";
                            }
                            sb.append(str);
                            break;
                        }
                    }
                }
                
            }
            
        }
        if (txtDiaChi.getText().isBlank()) {
            sb.append("Không để trống Địa Chỉ\n");
        }
        if (jdateNgaySinh.getDate() == null) {
            
            sb.append("Không để trống Ngày Sinh\n");
        }
        
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return false;
        }
        return true;
    }
    
    private static final String NUMERIC_CHARS = "0123456789";
    private static final int STRING_LENGTH = 15;
    
    public static String generateRandomNumericString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(NUMERIC_CHARS.length());
            char randomChar = NUMERIC_CHARS.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }
    
    public KhachHang getData() {
        TheTichDiem theTichDiem = new TheTichDiem();
        theTichDiem.setMaThe(generateRandomNumericString(STRING_LENGTH));
        theTichDiem.setNgayKichHoat(LocalDate.now());
        theTichDiem.setSoDiem(0);
        theTichDiem.setTrangThai(true);
        
        KhachHang khachHang = new KhachHang();
        khachHang.setHoTen(txtHoTen.getText().trim());
        khachHang.setEmail(txtEmail.getText().trim());
        khachHang.setSdt(txtSdt.getText().trim());
        if (cboNam.isSelected()) {
            khachHang.setGioiTinh(true);
        } else {
            khachHang.setGioiTinh(false);
        }
        try {
            LocalDate localDate = jdateNgaySinh.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            khachHang.setNgaySinh(localDate);
        } catch (Exception e) {
            System.out.println("alo");
        }
        
        khachHang.setDiaChi(txtDiaChi.getText().trim());
        if (chkTrangThai.isSelected()) {
            khachHang.setTrangThai(1);
        } else {
            khachHang.setTrangThai(0);
        }
        
        khachHang.setTheTichDiem(theTichDiem);
        return khachHang;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        ThemKhachHang = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cboNam = new javax.swing.JRadioButton();
        cboNu = new javax.swing.JRadioButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        chkTrangThai = new javax.swing.JCheckBox();
        btnThem = new javax.swing.JButton();
        jdateNgaySinh = new com.toedter.calendar.JDateChooser();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        menuItem1 = new javax.swing.JMenuItem();
        menuItem2 = new javax.swing.JMenuItem();
        ThemByImei = new javax.swing.JDialog();
        jLabel71 = new javax.swing.JLabel();
        btnOkThemImei = new javax.swing.JButton();
        txtThemImei = new javax.swing.JTextField();
        imeiDialog = new javax.swing.JDialog();
        jLabel48 = new javax.swing.JLabel();
        cbImeiInDialog = new javax.swing.JComboBox<>();
        btnOkImei = new javax.swing.JButton();
        txtImei = new javax.swing.JTextField();
        XemChiTiet = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        lbCTCamChinh = new javax.swing.JLabel();
        lbCTCamPhu = new javax.swing.JLabel();
        lbCTCamGocRong = new javax.swing.JLabel();
        lbCTCamTele = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        lbCTKichThuoc = new javax.swing.JLabel();
        lbCTDoPhanGiai = new javax.swing.JLabel();
        lbCTLoaiMH = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        lbCTRom = new javax.swing.JLabel();
        lbCTRam = new javax.swing.JLabel();
        lbCTSoLuong = new javax.swing.JLabel();
        lbCTGiaBan = new javax.swing.JLabel();
        lbCTTenDT = new javax.swing.JLabel();
        lbCTMaDT = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        lbCTCPU = new javax.swing.JLabel();
        lbCTPin = new javax.swing.JLabel();
        lbCTHeDH = new javax.swing.JLabel();
        lbCTHang = new javax.swing.JLabel();
        lbCTDongSP = new javax.swing.JLabel();
        lbCTMauSac = new javax.swing.JLabel();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jpnFormBanHang = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        btnSapXepGiaTangDan = new javax.swing.JButton();
        btnSapXepGiaGiamDan = new javax.swing.JButton();
        cbHangDT = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtSearchByTen = new javax.swing.JTextField();
        btnReloadDTTable = new javax.swing.JButton();
        jplGioHang = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jLabel3 = new javax.swing.JLabel();
        btnXoaHDChiTiet = new javax.swing.JButton();
        btnXoaDonHang1 = new javax.swing.JButton();
        btnTaoDonHang = new javax.swing.JButton();
        btnXoaHDChiTiet1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDienThoai = new javax.swing.JTable();
        Jpanel20 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        lbNhanVien = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        lbTongTien = new javax.swing.JLabel();
        lbSoDiem = new javax.swing.JLabel();
        lbKhachPhaiTra = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        lbTienThua = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lbTienGiam = new javax.swing.JLabel();
        lbMaGiamGia = new javax.swing.JLabel();
        chkboxSuDungDiem = new javax.swing.JCheckBox();
        lbSoTienTuDiem = new javax.swing.JLabel();
        rdTienMat = new javax.swing.JRadioButton();
        jLabel58 = new javax.swing.JLabel();
        rdChuyenKhoan = new javax.swing.JRadioButton();
        txtMaGD = new javax.swing.JTextField();
        lbMaGD = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        lbTongTien2 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        lbTienGiam2 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        lbKhachPhaiTra2 = new javax.swing.JLabel();
        btnThanhToan2 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        lbTraTruocToiThieu = new javax.swing.JLabel();
        lbConNo = new javax.swing.JLabel();
        lbTraHangThang = new javax.swing.JLabel();
        txtTienTraTruoc = new javax.swing.JTextField();
        cbKyHan = new javax.swing.JComboBox<>();
        txtLaiSuat = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        lbTongNo = new javax.swing.JLabel();
        lbMaGiamGia2 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        lbSoDiem2 = new javax.swing.JLabel();
        lbSoTienTuDiem2 = new javax.swing.JLabel();
        chkboxSuDungDiem2 = new javax.swing.JCheckBox();
        jLabel56 = new javax.swing.JLabel();
        rdTienMat2 = new javax.swing.JRadioButton();
        rdChuyenKhoan2 = new javax.swing.JRadioButton();
        lbMaGD2 = new javax.swing.JLabel();
        txtMaGD2 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtTimKH = new javax.swing.JTextField();
        btnTimKH = new javax.swing.JButton();
        lbTenKhachHang = new javax.swing.JLabel();
        lbEmailKhachHang = new javax.swing.JLabel();
        lbSdtKhachHang = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        ThemKhachHang.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("THÔNG TIN"));

        jLabel13.setText("HỌ VÀ TÊN:");

        jLabel14.setText("EMAIL:");

        jLabel15.setText("SDT:");

        txtHoTen.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtHoTen.setForeground(new java.awt.Color(51, 51, 51));
        txtHoTen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        txtEmail.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(51, 51, 51));
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        txtSdt.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtSdt.setForeground(new java.awt.Color(51, 51, 51));
        txtSdt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        jLabel16.setText("GIỚI TÍNH:");

        cboNam.setBackground(new java.awt.Color(255, 255, 255));
        cboNam.setSelected(true);
        cboNam.setText("NAM");

        cboNu.setBackground(new java.awt.Color(255, 255, 255));
        cboNu.setText("NỮ");

        jLabel17.setText("NGÀY SINH:");

        jLabel18.setText("ĐỊA CHỈ:");

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtDiaChi.setForeground(new java.awt.Color(51, 51, 51));
        txtDiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        jLabel19.setText("TRẠNG THÁI:");

        chkTrangThai.setBackground(new java.awt.Color(255, 255, 255));
        chkTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chkTrangThai.setForeground(new java.awt.Color(102, 102, 102));
        chkTrangThai.setText("Hoạt Động?");

        btnThem.setBackground(new java.awt.Color(47, 85, 212));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/add.png"))); // NOI18N
        btnThem.setText("THÊM");
        btnThem.setBorderPainted(false);
        btnThem.setFocusable(false);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jdateNgaySinh.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(30, 30, 30)
                        .addComponent(cboNam)
                        .addGap(26, 26, 26)
                        .addComponent(cboNu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(28, 28, 28)
                                .addComponent(txtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(68, 68, 68)
                                .addComponent(txtSdt))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(54, 54, 54)
                                .addComponent(txtEmail)))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19))
                        .addGap(30, 30, 30)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                    .addComponent(jdateNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(chkTrangThai)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(24, 24, 24))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(227, 227, 227)
                .addComponent(btnThem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jdateNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboNam)
                        .addComponent(cboNu)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(btnThem)
                .addContainerGap())
        );

        javax.swing.GroupLayout ThemKhachHangLayout = new javax.swing.GroupLayout(ThemKhachHang.getContentPane());
        ThemKhachHang.getContentPane().setLayout(ThemKhachHangLayout);
        ThemKhachHangLayout.setHorizontalGroup(
            ThemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 584, Short.MAX_VALUE)
            .addGroup(ThemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ThemKhachHangLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ThemKhachHangLayout.setVerticalGroup(
            ThemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(ThemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ThemKhachHangLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        menuItem1.setText("Xem chi tiết");
        menuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(menuItem1);

        menuItem2.setText("Thêm vào giỏ");
        menuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(menuItem2);

        ThemByImei.setTitle("Thêm vào giỏ hàng bằng IMEI");

        jLabel71.setText("IMEI");

        btnOkThemImei.setText("OK");
        btnOkThemImei.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkThemImeiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ThemByImeiLayout = new javax.swing.GroupLayout(ThemByImei.getContentPane());
        ThemByImei.getContentPane().setLayout(ThemByImeiLayout);
        ThemByImeiLayout.setHorizontalGroup(
            ThemByImeiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThemByImeiLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(ThemByImeiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ThemByImeiLayout.createSequentialGroup()
                        .addComponent(txtThemImei, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnOkThemImei)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        ThemByImeiLayout.setVerticalGroup(
            ThemByImeiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThemByImeiLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel71)
                .addGap(10, 10, 10)
                .addGroup(ThemByImeiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtThemImei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOkThemImei))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jLabel48.setText("Chọn IMEI");

        cbImeiInDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbImeiInDialogActionPerformed(evt);
            }
        });

        btnOkImei.setText("OK");
        btnOkImei.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkImeiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout imeiDialogLayout = new javax.swing.GroupLayout(imeiDialog.getContentPane());
        imeiDialog.getContentPane().setLayout(imeiDialogLayout);
        imeiDialogLayout.setHorizontalGroup(
            imeiDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imeiDialogLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(imeiDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(imeiDialogLayout.createSequentialGroup()
                        .addGroup(imeiDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtImei, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbImeiInDialog, javax.swing.GroupLayout.Alignment.LEADING, 0, 175, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnOkImei)))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        imeiDialogLayout.setVerticalGroup(
            imeiDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imeiDialogLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel48)
                .addGap(10, 10, 10)
                .addComponent(txtImei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(imeiDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbImeiInDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOkImei))
                .addContainerGap(199, Short.MAX_VALUE))
        );

        XemChiTiet.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        XemChiTiet.setTitle("Chi Tiết Sản Phẩm");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        image.setText("Ảnh");
        image.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("CAMERA"));

        jLabel64.setText("CHÍNH:");

        jLabel65.setText("PHỤ:");

        jLabel66.setText("GÓC RỘNG:");

        jLabel67.setText("TELE:");

        lbCTCamChinh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCTCamChinh.setForeground(new java.awt.Color(255, 51, 51));
        lbCTCamChinh.setText("jLabel77");

        lbCTCamPhu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCTCamPhu.setForeground(new java.awt.Color(255, 51, 51));
        lbCTCamPhu.setText("jLabel78");

        lbCTCamGocRong.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCTCamGocRong.setForeground(new java.awt.Color(255, 51, 51));
        lbCTCamGocRong.setText("jLabel79");

        lbCTCamTele.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCTCamTele.setForeground(new java.awt.Color(255, 51, 51));
        lbCTCamTele.setText("jLabel80");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel66)
                            .addComponent(jLabel67))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCTCamGocRong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCTCamTele, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbCTCamChinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel65)
                        .addGap(55, 55, 55)
                        .addComponent(lbCTCamPhu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCTCamChinh)
                    .addComponent(jLabel64))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(lbCTCamPhu))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(lbCTCamGocRong))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(lbCTCamTele))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("MÀN HÌNH"));

        jLabel72.setText("KÍCH THƯỚC:");

        jLabel73.setText("ĐỘ PHÂN GIẢI:");

        jLabel74.setText("LOẠI MÀN HÌNH:");

        lbCTKichThuoc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCTKichThuoc.setForeground(new java.awt.Color(255, 51, 51));
        lbCTKichThuoc.setText("jLabel68");

        lbCTDoPhanGiai.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCTDoPhanGiai.setForeground(new java.awt.Color(255, 51, 51));
        lbCTDoPhanGiai.setText("jLabel69");

        lbCTLoaiMH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCTLoaiMH.setForeground(new java.awt.Color(255, 51, 51));
        lbCTLoaiMH.setText("jLabel70");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel73)
                            .addComponent(jLabel74))
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(lbCTDoPhanGiai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lbCTLoaiMH, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel72)
                        .addGap(37, 37, 37)
                        .addComponent(lbCTKichThuoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72)
                    .addComponent(lbCTKichThuoc))
                .addGap(26, 26, 26)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(lbCTDoPhanGiai))
                .addGap(26, 26, 26)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(lbCTLoaiMH))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setText("Mã ĐT:");

        jLabel29.setText("Tên ĐT:");

        jLabel34.setText("Giá Bán:");

        jLabel45.setText("Số Lượng:");

        jLabel25.setText("RAM:");

        jLabel37.setText("ROM:");

        lbCTRom.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCTRom.setForeground(new java.awt.Color(255, 51, 51));
        lbCTRom.setText("jLabel37");

        lbCTRam.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCTRam.setForeground(new java.awt.Color(255, 51, 51));
        lbCTRam.setText("jLabel25");

        lbCTSoLuong.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCTSoLuong.setForeground(new java.awt.Color(255, 0, 0));
        lbCTSoLuong.setText("jLabel46");

        lbCTGiaBan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCTGiaBan.setForeground(new java.awt.Color(255, 0, 0));
        lbCTGiaBan.setText("jLabel36");

        lbCTTenDT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCTTenDT.setForeground(new java.awt.Color(255, 0, 0));
        lbCTTenDT.setText("jLabel31");

        lbCTMaDT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCTMaDT.setForeground(new java.awt.Color(255, 51, 51));
        lbCTMaDT.setText("jLabel11");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(39, 39, 39)
                        .addComponent(lbCTTenDT, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(41, 41, 41)
                        .addComponent(lbCTMaDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbCTGiaBan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCTSoLuong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addComponent(jLabel45))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel37))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCTRom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCTRam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lbCTMaDT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(lbCTTenDT))
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(lbCTGiaBan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(lbCTSoLuong))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel37))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lbCTRam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbCTRom)
                        .addGap(6, 6, 6)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel52.setText("Hãng:");

        jLabel50.setText("Dòng SP:");

        jLabel53.setText("Màu Sắc:");

        jLabel47.setText("HĐH:");

        jLabel57.setText("PIN:");

        jLabel59.setText("CPU:");

        lbCTCPU.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCTCPU.setForeground(new java.awt.Color(255, 51, 51));
        lbCTCPU.setText("jLabel59");

        lbCTPin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCTPin.setForeground(new java.awt.Color(255, 51, 51));
        lbCTPin.setText("jLabel57");

        lbCTHeDH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCTHeDH.setForeground(new java.awt.Color(255, 0, 0));
        lbCTHeDH.setText("jLabel55");

        lbCTHang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCTHang.setForeground(new java.awt.Color(255, 0, 0));
        lbCTHang.setText("jLabel54");

        lbCTDongSP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCTDongSP.setForeground(new java.awt.Color(255, 0, 0));
        lbCTDongSP.setText("jLabel51");

        lbCTMauSac.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCTMauSac.setForeground(new java.awt.Color(255, 0, 0));
        lbCTMauSac.setText("jLabel49");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel47)
                        .addGap(42, 42, 42)
                        .addComponent(lbCTHeDH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel59)
                            .addComponent(jLabel57))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCTPin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCTCPU, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel52)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel50)
                        .addGap(23, 23, 23)
                        .addComponent(lbCTDongSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel53)
                        .addGap(23, 23, 23)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCTHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCTMauSac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel50)
                                    .addComponent(lbCTDongSP)))
                            .addComponent(jLabel52))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53)
                            .addComponent(lbCTMauSac)))
                    .addComponent(lbCTHang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(lbCTHeDH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(lbCTPin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(lbCTCPU))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4)))
                .addGap(46, 46, 46))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout XemChiTietLayout = new javax.swing.GroupLayout(XemChiTiet.getContentPane());
        XemChiTiet.getContentPane().setLayout(XemChiTietLayout);
        XemChiTietLayout.setHorizontalGroup(
            XemChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        XemChiTietLayout.setVerticalGroup(
            XemChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));

        jpnFormBanHang.setBackground(new java.awt.Color(255, 255, 255));
        jpnFormBanHang.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnSapXepGiaTangDan.setBackground(new java.awt.Color(47, 85, 212));
        btnSapXepGiaTangDan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-sort-amount-up-reversed-20.png"))); // NOI18N
        btnSapXepGiaTangDan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSapXepGiaTangDanActionPerformed(evt);
            }
        });

        btnSapXepGiaGiamDan.setBackground(new java.awt.Color(47, 85, 212));
        btnSapXepGiaGiamDan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-descending-sorting-20.png"))); // NOI18N
        btnSapXepGiaGiamDan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSapXepGiaGiamDanActionPerformed(evt);
            }
        });

        cbHangDT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbHangDTMouseClicked(evt);
            }
        });
        cbHangDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbHangDTActionPerformed(evt);
            }
        });

        jLabel8.setText("Tìm kiếm theo tên:");

        jLabel9.setText("Phân loại theo hãng:");

        jLabel10.setText("Sắp xếp theo giá:");

        txtSearchByTen.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchByTenCaretUpdate(evt);
            }
        });

        btnReloadDTTable.setText("Reload");
        btnReloadDTTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadDTTableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtSearchByTen, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbHangDT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnReloadDTTable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnSapXepGiaTangDan, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSapXepGiaGiamDan, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10))
                .addGap(18, 18, 18))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSapXepGiaGiamDan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSapXepGiaTangDan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbHangDT)
                                .addComponent(txtSearchByTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnReloadDTTable)))))
                .addContainerGap())
        );

        jplGioHang.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(47, 85, 212));
        jLabel3.setText("DANH SÁCH HÓA ĐƠN CHỜ");

        btnXoaHDChiTiet.setBackground(new java.awt.Color(47, 85, 212));
        btnXoaHDChiTiet.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnXoaHDChiTiet.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaHDChiTiet.setText("Xóa");
        btnXoaHDChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaHDChiTietActionPerformed(evt);
            }
        });

        btnXoaDonHang1.setBackground(new java.awt.Color(47, 85, 212));
        btnXoaDonHang1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnXoaDonHang1.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaDonHang1.setText("Hủy Đơn");
        btnXoaDonHang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDonHang1ActionPerformed(evt);
            }
        });

        btnTaoDonHang.setBackground(new java.awt.Color(47, 85, 212));
        btnTaoDonHang.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnTaoDonHang.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoDonHang.setText("Tạo Đơn");
        btnTaoDonHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoDonHangActionPerformed(evt);
            }
        });

        btnXoaHDChiTiet1.setBackground(new java.awt.Color(47, 85, 212));
        btnXoaHDChiTiet1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnXoaHDChiTiet1.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaHDChiTiet1.setText("Thêm");
        btnXoaHDChiTiet1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaHDChiTiet1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jplGioHangLayout = new javax.swing.GroupLayout(jplGioHang);
        jplGioHang.setLayout(jplGioHangLayout);
        jplGioHangLayout.setHorizontalGroup(
            jplGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(jplGioHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTaoDonHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoaDonHang1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addComponent(btnXoaHDChiTiet1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoaHDChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
        jplGioHangLayout.setVerticalGroup(
            jplGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplGioHangLayout.createSequentialGroup()
                .addGroup(jplGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTaoDonHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaDonHang1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaHDChiTiet1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaHDChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tbDienThoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã ĐT", "Tên ĐT", "Giá Bán", "Số Lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDienThoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDienThoaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbDienThoai);
        if (tbDienThoai.getColumnModel().getColumnCount() > 0) {
            tbDienThoai.getColumnModel().getColumn(1).setResizable(false);
            tbDienThoai.getColumnModel().getColumn(1).setPreferredWidth(150);
            tbDienThoai.getColumnModel().getColumn(3).setResizable(false);
            tbDienThoai.getColumnModel().getColumn(3).setPreferredWidth(20);
        }

        javax.swing.GroupLayout jpnFormBanHangLayout = new javax.swing.GroupLayout(jpnFormBanHang);
        jpnFormBanHang.setLayout(jpnFormBanHangLayout);
        jpnFormBanHangLayout.setHorizontalGroup(
            jpnFormBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnFormBanHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnFormBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jplGioHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jpnFormBanHangLayout.setVerticalGroup(
            jpnFormBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnFormBanHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jplGioHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Jpanel20.setBackground(new java.awt.Color(255, 255, 255));
        Jpanel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("TẠO HÓA ĐƠN");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel5.setText("Khách Hàng:");

        jButton1.setBackground(new java.awt.Color(47, 85, 212));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-pencil-20 WHITE.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel6.setText("Nhân Viên:");

        lbNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbNhanVien.setForeground(new java.awt.Color(255, 0, 51));
        lbNhanVien.setText("Tên NV");

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setText("Tổng Tiền :");

        jLabel20.setText("Mã Giảm Giá :");

        jLabel21.setText("Thẻ tích điểm:");

        jLabel22.setText("Khách Phải Trả :");

        jLabel23.setText("Tiền Khách Đưa :");

        jLabel24.setText("Tiền thừa :");

        btnThanhToan.setBackground(new java.awt.Color(47, 85, 212));
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        lbTongTien.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbTongTien.setForeground(new java.awt.Color(255, 0, 0));
        lbTongTien.setText("0");

        lbSoDiem.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbSoDiem.setForeground(new java.awt.Color(255, 51, 51));
        lbSoDiem.setText("Số điểm");

        lbKhachPhaiTra.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbKhachPhaiTra.setForeground(new java.awt.Color(51, 51, 255));
        lbKhachPhaiTra.setText("0");

        txtTienKhachDua.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));
        txtTienKhachDua.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienKhachDuaCaretUpdate(evt);
            }
        });

        lbTienThua.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbTienThua.setForeground(new java.awt.Color(51, 51, 255));
        lbTienThua.setText("0");

        jLabel26.setText("Tiền giảm:");

        lbTienGiam.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbTienGiam.setForeground(new java.awt.Color(255, 51, 51));
        lbTienGiam.setText("0");

        lbMaGiamGia.setText("Mã GG");

        chkboxSuDungDiem.setText("Sử dụng điểm");
        chkboxSuDungDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkboxSuDungDiemActionPerformed(evt);
            }
        });

        lbSoTienTuDiem.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbSoTienTuDiem.setForeground(new java.awt.Color(255, 51, 51));
        lbSoTienTuDiem.setText("Số tiền quy đổi từ điểm");

        rdTienMat.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup4.add(rdTienMat);
        rdTienMat.setSelected(true);
        rdTienMat.setText("Tiền Mặt");
        rdTienMat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdTienMatActionPerformed(evt);
            }
        });

        jLabel58.setText("Hình thức TT:");

        rdChuyenKhoan.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup4.add(rdChuyenKhoan);
        rdChuyenKhoan.setText("Chuyển Khoản");
        rdChuyenKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdChuyenKhoanActionPerformed(evt);
            }
        });

        txtMaGD.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        lbMaGD.setText("Mã Giao Dịch:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbMaGD)
                        .addGap(266, 266, 266))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(lbTienThua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtTienKhachDua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
                                        .addComponent(lbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbMaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbSoDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbSoTienTuDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(chkboxSuDungDiem)
                                        .addComponent(txtMaGD, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(rdTienMat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(rdChuyenKhoan)
                                    .addGap(35, 35, 35))))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel58)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(lbTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbKhachPhaiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lbTongTien))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(lbMaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(lbSoDiem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbSoTienTuDiem)
                .addGap(9, 9, 9)
                .addComponent(chkboxSuDungDiem)
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(lbTienGiam))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(lbKhachPhaiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtTienKhachDua))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(lbTienThua))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdTienMat)
                    .addComponent(jLabel58)
                    .addComponent(rdChuyenKhoan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbMaGD)
                    .addComponent(txtMaGD, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(btnThanhToan)
                .addGap(45, 45, 45))
        );

        jTabbedPane2.addTab("Trả Toàn Bộ", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel30.setText("Tổng Tiền :");

        lbTongTien2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbTongTien2.setForeground(new java.awt.Color(255, 51, 51));
        lbTongTien2.setText("0");

        jLabel32.setText("Mã Giảm Giá :");

        jLabel33.setText("Tiền Giảm :");

        lbTienGiam2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbTienGiam2.setForeground(new java.awt.Color(255, 51, 51));
        lbTienGiam2.setText("0");

        jLabel35.setText("Khách Phải Trả :");

        lbKhachPhaiTra2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbKhachPhaiTra2.setForeground(new java.awt.Color(0, 51, 255));
        lbKhachPhaiTra2.setText("0");

        btnThanhToan2.setBackground(new java.awt.Color(47, 85, 212));
        btnThanhToan2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThanhToan2.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan2.setText("Thanh Toán");
        btnThanhToan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToan2ActionPerformed(evt);
            }
        });

        jLabel38.setText("Trả Trước Tối Thiểu :");

        jLabel39.setText("Số Tiền Trả Trước :");

        jLabel40.setText("Còn Nợ :");

        jLabel41.setText("Lãi Suất :");

        jLabel42.setText("Kì Hạn :");

        jLabel43.setText("Phải trả hàng tháng :");

        lbTraTruocToiThieu.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbTraTruocToiThieu.setForeground(new java.awt.Color(0, 51, 255));
        lbTraTruocToiThieu.setText("0");

        lbConNo.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbConNo.setForeground(new java.awt.Color(0, 51, 255));
        lbConNo.setText("0");

        lbTraHangThang.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbTraHangThang.setForeground(new java.awt.Color(0, 51, 255));
        lbTraHangThang.setText("0");

        txtTienTraTruoc.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));
        txtTienTraTruoc.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienTraTruocCaretUpdate(evt);
            }
        });

        cbKyHan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "4 tháng", "6 tháng", "8 tháng", "10 tháng", "12 tháng" }));
        cbKyHan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKyHanActionPerformed(evt);
            }
        });

        txtLaiSuat.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));
        txtLaiSuat.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtLaiSuatCaretUpdate(evt);
            }
        });

        jLabel44.setText("Tổng nợ:");

        lbTongNo.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbTongNo.setForeground(new java.awt.Color(0, 51, 255));
        lbTongNo.setText("0");

        lbMaGiamGia2.setText("Mã GG");

        jLabel28.setText("Thẻ tích điểm:");

        lbSoDiem2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbSoDiem2.setForeground(new java.awt.Color(255, 51, 51));
        lbSoDiem2.setText("Số điểm");

        lbSoTienTuDiem2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbSoTienTuDiem2.setForeground(new java.awt.Color(255, 51, 51));
        lbSoTienTuDiem2.setText("Số tiền quy đổi từ điểm");

        chkboxSuDungDiem2.setText("Sử dụng điểm");
        chkboxSuDungDiem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkboxSuDungDiem2ActionPerformed(evt);
            }
        });

        jLabel56.setText("Hình thức TT:");

        rdTienMat2.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup3.add(rdTienMat2);
        rdTienMat2.setSelected(true);
        rdTienMat2.setText("Tiền Mặt");
        rdTienMat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdTienMat2ActionPerformed(evt);
            }
        });

        rdChuyenKhoan2.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup3.add(rdChuyenKhoan2);
        rdChuyenKhoan2.setText("Chuyển Khoản");
        rdChuyenKhoan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdChuyenKhoan2ActionPerformed(evt);
            }
        });

        lbMaGD2.setText("Mã Giao Dịch:");

        txtMaGD2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cbKyHan, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbMaGiamGia2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbTongNo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbTongTien2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbTienGiam2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbKhachPhaiTra2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbTraTruocToiThieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTienTraTruoc, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                                        .addComponent(lbConNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbTraHangThang, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtLaiSuat, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(lbSoDiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 145, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbSoTienTuDiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkboxSuDungDiem2))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rdTienMat2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdChuyenKhoan2))
                            .addComponent(txtMaGD2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(btnThanhToan2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbMaGD2)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(lbTongTien2))
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(lbMaGiamGia2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(lbSoDiem2))
                .addGap(10, 10, 10)
                .addComponent(lbSoTienTuDiem2)
                .addGap(9, 9, 9)
                .addComponent(chkboxSuDungDiem2)
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(lbTienGiam2))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(lbKhachPhaiTra2))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(lbTraTruocToiThieu))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(txtTienTraTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(lbConNo))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(txtLaiSuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(lbTongNo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(cbKyHan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(lbTraHangThang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdTienMat2)
                        .addComponent(jLabel56))
                    .addComponent(rdChuyenKhoan2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbMaGD2)
                    .addComponent(txtMaGD2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(btnThanhToan2)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Trả Góp", jPanel3);

        jLabel27.setText("Tìm kiếm KH:");

        btnTimKH.setBackground(new java.awt.Color(47, 85, 212));
        btnTimKH.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKH.setText("Tìm");
        btnTimKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKHActionPerformed(evt);
            }
        });

        lbTenKhachHang.setText("Tên khách hàng");

        lbEmailKhachHang.setText("Email khách hàng");

        lbSdtKhachHang.setText("SĐT khách hàng");

        javax.swing.GroupLayout Jpanel20Layout = new javax.swing.GroupLayout(Jpanel20);
        Jpanel20.setLayout(Jpanel20Layout);
        Jpanel20Layout.setHorizontalGroup(
            Jpanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jpanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Jpanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jpanel20Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(111, 111, 111))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jpanel20Layout.createSequentialGroup()
                        .addComponent(jTabbedPane2)
                        .addContainerGap())
                    .addGroup(Jpanel20Layout.createSequentialGroup()
                        .addGroup(Jpanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Jpanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(Jpanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Jpanel20Layout.createSequentialGroup()
                                .addGroup(Jpanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTimKH)
                                    .addComponent(lbTenKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbEmailKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                    .addComponent(lbSdtKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(Jpanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Jpanel20Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnTimKH, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jpanel20Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1)
                                        .addGap(20, 20, 20))))
                            .addComponent(lbNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(27, 27, 27))))
        );
        Jpanel20Layout.setVerticalGroup(
            Jpanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jpanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(8, 8, 8)
                .addGroup(Jpanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Jpanel20Layout.createSequentialGroup()
                        .addGroup(Jpanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(txtTimKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimKH))
                        .addGap(5, 5, 5)
                        .addGroup(Jpanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTenKhachHang)))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbEmailKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbSdtKhachHang)
                .addGap(6, 6, 6)
                .addGroup(Jpanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(47, 85, 212));
        jLabel2.setText("BÁN HÀNG");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnFormBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Jpanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnFormBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Jpanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaoDonHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoDonHangActionPerformed
        int soDon = jTabbedPane1.getTabCount();
        jTabbedPane1.add(new jplDonHang(soDon + 1));
    }//GEN-LAST:event_btnTaoDonHangActionPerformed

    private void btnXoaHDChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaHDChiTietActionPerformed
        /*
        - chọn 1 dòng HĐCT (chính là một đt) trong giỏ hàng rồi ấn xóa
        - HĐCT sẽ bị xóa khỏi giỏ hàng
        - sau đó cần
            + cập nhật lại trạng thại imei từ 2 -> 0
            + cập nhật lại số lượng đt
         */
        
        jplDonHang jplDonHang = (jplDonHang) jTabbedPane1.getSelectedComponent();
        int index = jplDonHang.globalClickedRow;
        
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn trước khi xóa!");
            return;
        }
        
        HoaDonChiTietResponse hdctResponse = hoaDonChiTietResponseList.get(index);
        hoaDonChiTietResponseList.remove(hdctResponse);
        
        jplDonHang.setHoaDonChiTiets(hoaDonChiTietResponseList);
        jplDonHang.load();

        // khi xóa khỏi giỏ hàng thì phải:
        // 1. cập nhật lại trạng thái của imei từ 2 về 0
        String imeiStr = hdctResponse.getImei();
        imeiService.updateImeiTrangThai(imeiStr, 0);

        // 2. cập nhật lại số lượng của điện thoại và show lại bảng điện thoại
        DienThoaiRepository.updateSoLuongDienThoai(imeiStr, 1);
        dienThoaiResponseList = dienThoaiService.getAllResponseByStatus(true);
        showDienThoaiTable(dienThoaiResponseList);
        
        showHoaDonInfo1();
        showHoaDonInfo2();
        
        if (hoaDonChiTietResponseList.size() == 0) {
            lamMoiForm1();
            lamMoiForm2();
        }
    }//GEN-LAST:event_btnXoaHDChiTietActionPerformed

    private void btnSapXepGiaTangDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSapXepGiaTangDanActionPerformed
        dienThoaiResponseList = dienThoaiService.getAllResponseByGiaBan("ASC");
        showDienThoaiTable(dienThoaiResponseList);
    }//GEN-LAST:event_btnSapXepGiaTangDanActionPerformed

    private void btnSapXepGiaGiamDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSapXepGiaGiamDanActionPerformed
        dienThoaiResponseList = dienThoaiService.getAllResponseByGiaBan("DESC");
        showDienThoaiTable(dienThoaiResponseList);
    }//GEN-LAST:event_btnSapXepGiaGiamDanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ThemKhachHang.setSize(610, 350);
        ThemKhachHang.setResizable(false);
        ThemKhachHang.setLocationRelativeTo(null);
        ThemKhachHang.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
//        setDefault();
        KhachHang kh = getData();
        if (kiemTra(0, txtEmail.getText().trim())) {
            JOptionPane.showMessageDialog(this, khachHangService.add(kh));
            
            khachHangResponseList = khachHangService.getAll();
            KhachHangResponse s = khachHangService.getKhachHangById(kh.getId());
            khachHangService.updateKhoiPhuc(s, chkTrangThai.isSelected() ? 1 : 0);
            lbTenKhachHang.setText("Họ tên: " + kh.getHoTen());
            lbEmailKhachHang.setText("Email: " + kh.getEmail());
            lbSdtKhachHang.setText("Sdt: " + kh.getSdt());
            ThemKhachHang.dispose();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    // my job
    private void tbDienThoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDienThoaiMouseClicked
        if (SwingUtilities.isRightMouseButton(evt)) {
            int row = tbDienThoai.rowAtPoint(evt.getPoint());
            if (row >= 0 && row < dienThoaiResponseList.size()) {
                tbDienThoai.setRowSelectionInterval(row, row);
                jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_tbDienThoaiMouseClicked

    private void menuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem1ActionPerformed
        XemChiTiet.setSize(1000, 500);
        XemChiTiet.setResizable(false);
        XemChiTiet.setLocationRelativeTo(null);
        XemChiTiet.setVisible(true);
        
        int clickedRowInDienThoaiTable = tbDienThoai.getSelectedRow();
        if (clickedRowInDienThoaiTable < 0 || clickedRowInDienThoaiTable > dienThoaiResponseList.size()) {
            return;
        }
        DienThoaiResponse dienThoaiResponse = dienThoaiResponseList.get(clickedRowInDienThoaiTable);
        
        lbCTTenDT.setText(dienThoaiResponse.getTenDT());
        lbCTMaDT.setText(dienThoaiResponse.getMaDT());
        lbCTGiaBan.setText(numberFormat.format(dienThoaiResponse.getGiaBan()) + " VND");
        lbCTSoLuong.setText(String.valueOf(dienThoaiResponse.getSoLuong()));
        lbCTRam.setText(String.valueOf(dienThoaiResponse.getRam()) + " px");
        lbCTRom.setText(String.valueOf(dienThoaiResponse.getRom()) + " GB");
        
        lbCTHang.setText(dienThoaiResponse.getHang());
        lbCTDongSP.setText(dienThoaiResponse.getDongSanPham());
        lbCTMauSac.setText(dienThoaiResponse.getMauSac());
        lbCTHeDH.setText(dienThoaiResponse.getHeDieuHanh());
        lbCTPin.setText(String.valueOf(dienThoaiResponse.getDungLuongPin()) + " mAh");
        lbCTCPU.setText(dienThoaiResponse.getCpu());
        
        lbCTCamChinh.setText(String.valueOf(dienThoaiResponse.getCameraChinh()) + " px");
        lbCTCamGocRong.setText(String.valueOf(dienThoaiResponse.getCameraGocRong()) + " px");
        lbCTCamPhu.setText(String.valueOf(dienThoaiResponse.getCameraPhu()) + " px");
        lbCTCamTele.setText(String.valueOf(dienThoaiResponse.getCameraTele()) + " px");
        
        lbCTKichThuoc.setText(String.valueOf(dienThoaiResponse.getKichThuoc()) + " inches");
        lbCTDoPhanGiai.setText(dienThoaiResponse.getDoPhanGiai() + " px");
        lbCTLoaiMH.setText(dienThoaiResponse.getLoaiManHinh().name());
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/phoneimage/" + dienThoaiResponse.getHinhAnh()));
        Image newImage = icon.getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
        image.setIcon(new ImageIcon(newImage));
    }//GEN-LAST:event_menuItem1ActionPerformed

    private void menuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem2ActionPerformed
        int clickedRowInDienThoaiTable = tbDienThoai.getSelectedRow();
        if (clickedRowInDienThoaiTable < 0) {
            return;
        }
        DienThoaiResponse dienThoaiResponse = dienThoaiResponseList.get(clickedRowInDienThoaiTable);
        if (dienThoaiResponse.getSoLuong() < 1) {
            JOptionPane.showMessageDialog(this, "Sản phẩm hết hàng");
            return;
        }
        List<ImeiResponse> imeiResponses = imeiService.getResponsesByIdDienThoaiAndStatus(dienThoaiResponse.getId(), 0); // trangThai IMEI ở đây = 0

        cbImeiInDialog.removeAllItems();
        imeiResponses.forEach(i -> cbImeiInDialog.addItem(i.getImei()));
        
        imeiDialog.setSize(350, 150);
        imeiDialog.setResizable(false);
        imeiDialog.setLocationRelativeTo(null);
        imeiDialog.setVisible(true);
    }//GEN-LAST:event_menuItem2ActionPerformed

    private void btnOkImeiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkImeiActionPerformed
        String selectedImeiStr = txtImei.getText().trim();
        if (selectedImeiStr.isBlank()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền IMEI!");
            return;
        }
        
        Imei imei = ImeiRepository.getByImei(selectedImeiStr);
        int clickedRowInDienThoaiTable = tbDienThoai.getSelectedRow();
        DienThoaiResponse dienThoaiResponse = dienThoaiResponseList.get(clickedRowInDienThoaiTable);
        
        if (imei == null) {
            JOptionPane.showMessageDialog(this, "Imei của điện thoại này không tồn tạ!i");
            return;
        } else if (imei.getDienThoai().getId() != dienThoaiResponse.getId()) {
            JOptionPane.showMessageDialog(this, "Imei của điện thoại này không tồn tạ!i");
            return;
        }
        
        imeiDialog.setVisible(false);

        // thay đổi số lượng tồn kho của điện thoại vừa chọn
        DienThoaiRepository.updateSoLuongDienThoai(selectedImeiStr, -1);
        // thay đổi trạng thái imei về 'đang trong giỏ hàng(2)'
        imeiService.updateImeiTrangThai(selectedImeiStr, 2);
        
        dienThoaiResponse.setSoLuong(dienThoaiResponse.getSoLuong() - 1);
        
        HoaDonChiTietResponse hoaDonChiTietResponse = new HoaDonChiTietResponse();
        hoaDonChiTietResponse.setImei(selectedImeiStr);
        hoaDonChiTietResponse.setTenDienThoai(dienThoaiResponse.getTenDT());
        hoaDonChiTietResponse.setDonGia(dienThoaiResponse.getGiaBan());
        hoaDonChiTietResponseList.add(hoaDonChiTietResponse);
        
        jplDonHang jDonHang = (jplDonHang) jTabbedPane1.getSelectedComponent();
        jDonHang.setHoaDonChiTiets(hoaDonChiTietResponseList);
        jDonHang.load();
        
        showHoaDonInfo1();
        showHoaDonInfo2();
        showDienThoaiTable(dienThoaiResponseList);
    }//GEN-LAST:event_btnOkImeiActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        jTabbedPane1.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                jplDonHang jDonHang = (jplDonHang) jTabbedPane1.getSelectedComponent();
                List<HoaDonChiTietResponse> currentList = jDonHang.getHoaDonChiTiets();
                jDonHang.load();
                
                hoaDonChiTietResponseList = new ArrayList<>();
                hoaDonChiTietResponseList.addAll(currentList);
                
                try {
                    showHoaDonInfo1();
                    showHoaDonInfo2();
                } catch (Exception ex) {
                }
            }
        });

    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void txtTienKhachDuaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienKhachDuaCaretUpdate
        // tab 1
        // Nhập tiền khách đưa -> 1. Tiền thừa

        String tienKhachDuaStr = txtTienKhachDua.getText();
        String pattern = "[1-9][0-9]*";
        if (!tienKhachDuaStr.matches(pattern) && !tienKhachDuaStr.equals("")) {
            JOptionPane.showMessageDialog(this, "Chỉ được điền chữ số");
        } else if (!tienKhachDuaStr.equals("")) {
            long tienKhachDua = Long.valueOf(txtTienKhachDua.getText());
            long tienThua = tienKhachDua - Long.valueOf(lbKhachPhaiTra.getText().replaceAll(",", ""));
            lbTienThua.setText(numberFormat.format(tienThua));
        }
    }//GEN-LAST:event_txtTienKhachDuaCaretUpdate

    private void txtTienTraTruocCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienTraTruocCaretUpdate
        // tab 2
        // txt tiền trả trước -> 1. còn nợ

        String tienTraTruocStr = txtTienTraTruoc.getText();
        String pattern = "[1-9][0-9]*";
        if (!tienTraTruocStr.matches(pattern) && !tienTraTruocStr.equals("")) {
            JOptionPane.showMessageDialog(this, "Chỉ được điền chữ số");
        } else if (!tienTraTruocStr.equals("")) {
            long tienTraTruoc = Long.valueOf(tienTraTruocStr);
            long conNo = Long.valueOf(lbKhachPhaiTra2.getText().replaceAll(",", "")) - tienTraTruoc;
            lbConNo.setText(numberFormat.format(conNo));
        }
    }//GEN-LAST:event_txtTienTraTruocCaretUpdate

    private void txtLaiSuatCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLaiSuatCaretUpdate
        //  tab 2
        // txt lãi suất -> 1. tổng nợ

        // 1. Tổng nợ
        String laiSuatStr = txtLaiSuat.getText().trim();
        String pattern = "[0-9]+([.][0-9]+)*";
        if (!laiSuatStr.matches(pattern) && !laiSuatStr.equals("")) {
            JOptionPane.showMessageDialog(this, "Sai định dạng");
        } else if (!laiSuatStr.equals("")) {
            try {
                float laiSuat = Float.valueOf(laiSuatStr);
                long conNo = Long.valueOf(lbConNo.getText().replaceAll(",", ""));
                long tongNo = Math.round(Float.valueOf(conNo) * laiSuat / 100 + Float.valueOf(conNo));
                lbTongNo.setText(numberFormat.format(tongNo));

                // làm tròn tổng nợ để tiền không bị lẻ
                String tongNoStr = lbTongNo.getText().trim().replaceAll(",", "");
                tongNoStr = tongNoStr.substring(0, tongNoStr.length() - 3).concat("000");
                tongNo = Long.valueOf(tongNoStr);
                lbTongNo.setText(numberFormat.format(tongNo));
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_txtLaiSuatCaretUpdate

    private void cbKyHanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKyHanActionPerformed
        // tab 2
        // combo box kỳ hạn -> 1. phải trả hàng tháng

        try {
            int kyHan = Integer.valueOf(String.valueOf(cbKyHan.getSelectedItem()).substring(0, 2).trim());
            long tongNo = Long.valueOf(lbTongNo.getText().replaceAll(",", ""));
            long phaiTraHangThang = tongNo / kyHan;
            lbTraHangThang.setText(numberFormat.format(phaiTraHangThang));

            // làm tròn tiền trả hàng tháng để không bị lẻ
            String phaiTraHangThangStr = lbTraHangThang.getText().trim().replaceAll(",", "");
            phaiTraHangThangStr = phaiTraHangThangStr.substring(0, phaiTraHangThangStr.length() - 3).concat("000");
            
            phaiTraHangThang = Long.valueOf(phaiTraHangThangStr);
            lbTraHangThang.setText(numberFormat.format(phaiTraHangThang));
        } catch (Exception e) {
            System.out.println("Exception khi ấn cb kỳ hạn!");
        }
    }//GEN-LAST:event_cbKyHanActionPerformed

    private void txtSearchByTenCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchByTenCaretUpdate
        String keyword = txtSearchByTen.getText().trim();
        dienThoaiResponseList = dienThoaiService.searchAllResponseByName(keyword);
        showDienThoaiTable(dienThoaiResponseList);
    }//GEN-LAST:event_txtSearchByTenCaretUpdate

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Tạo hóa đơn?", "Xác nhận tạo hóa đơn", JOptionPane.YES_NO_OPTION);
        if (confirm != 0) {
            return;
        }
        
        String check = checkHoaDon1();
        if (!check.equals("")) {
            JOptionPane.showMessageDialog(this, check);
            return;
        }
        
        HoaDon hoaDon = new HoaDon();
        String maHoaDon = getMa("HD");
        hoaDon.setMaHoaDon(maHoaDon);
        hoaDon.setNgayTao(LocalDateTime.now());
        hoaDon.setNgayThanhToan(LocalDateTime.now());
        
        long tienGiam = Long.valueOf(lbTienGiam.getText().trim().replaceAll(",", ""));
        hoaDon.setTienGiam(tienGiam);
        
        long tongTien = Long.valueOf(lbTongTien.getText().trim().replaceAll(",", ""));
        hoaDon.setTongTien(tongTien);
        
        long tienKhachDua = Long.valueOf(txtTienKhachDua.getText().trim());
        hoaDon.setTienKhachDua(tienKhachDua);
        
        long tienThua = Long.valueOf(lbTienThua.getText().trim().replaceAll(",", ""));
        hoaDon.setTienThua(tienThua);

        // hóa đơn 'không trả góp'
        hoaDon.setTraGop(false);
        hoaDon.setTienTraTruoc(0L);
        hoaDon.setTienThieu(0);
        
        if (rdTienMat.isSelected()) {
            hoaDon.setHinhThucThanhToan(true);
        } else {
            hoaDon.setHinhThucThanhToan(false);
            String maGiaoDinh = txtMaGD.getText().trim();
            hoaDon.setMaGiaoDichChuyenKhoan(maGiaoDinh);
        }

//         khách hàng, nhân viên, phiếu giảm giá
//         1. khách hàng
//        KhachHangResponse khResponse = KhachHangRepository.getKhachHangByEmailOrSDT(lbTenKhachHang.getText().trim());
//        if (khResponse == null) {
//            khResponse = KhachHangRepository.getKhachHangByEmailOrSDT(lbEmailKhachHang.getText().trim());
//        }
        String emailKhachHang = lbEmailKhachHang.getText().trim().substring(7);
        String sdtKhachHang = lbSdtKhachHang.getText().trim().substring(5);
        
        KhachHangResponse khResponse = KhachHangRepository.getKhachHangByEmailOrSDT(emailKhachHang);
        if (khResponse == null) {
            khResponse = KhachHangRepository.getKhachHangByEmailOrSDT(sdtKhachHang);
        }
        KhachHang khachHang = KhachHangRepository.getKhachHangEntityById(khResponse.getId());
        hoaDon.setKhachHang(khachHang);

        // 2. nhân viên (đang login)
        NhanVien nhanVien = NhanVienRepository.getEntityById(loggedNhanVienResponse.getId());
        hoaDon.setNhanVien(nhanVien);

        // 3. phiếu giảm giá
        String maPhieu = lbMaGiamGia.getText().split(" - ")[0];
        PhieuGiamGia phieuGiamGia = PhieuGiamGiaRepository.getPhieuByMa(maPhieu);
        if (phieuGiamGia != null) {
            hoaDon.setPhieuGiamGia(phieuGiamGia);
        }

        // chi tiết hóa đơn
        hoaDonChiTietResponseList.forEach(h -> {
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            
            hoaDonChiTiet.setHoaDon(hoaDon);
            Imei imei = ImeiRepository.getByImei(h.getImei());
            hoaDonChiTiet.setImei(imei);
            hoaDon.addHoaDonChiTiet(hoaDonChiTiet);
        });
        
        String addResult = hoaDonService.add(hoaDon);
        JOptionPane.showMessageDialog(this, addResult);

        // sau khi add hóa đơn thành công thì phải:
        // 1. update trạng thái của tất cả imei trong hóa đơn về 1 - 'đã bán'
        hoaDonChiTietResponseList.forEach(h -> {
            imeiService.updateImeiTrangThai(h.getImei(), 1);
        });

        // 2. xóa giỏ hàng
        hoaDonChiTietResponseList = new ArrayList<>();
        jplDonHang jDonHang = (jplDonHang) jTabbedPane1.getSelectedComponent();
        jDonHang.setHoaDonChiTiets(new ArrayList<>());
        jDonHang.load();
        
        int indexDon = jTabbedPane1.getSelectedIndex();
        // sẽ không thể xóa khi còn 1 đơn duy nhất
        if (jTabbedPane1.getTabCount() > 1) {
            jTabbedPane1.remove(indexDon);
        }

        // 3. tạo phiếu bảo hành
        HoaDon hoaDonByMa = hoaDonService.getEntityByMa(maHoaDon);
        List<HoaDonChiTietResponse> hoaDonChiTietResponses = hoaDonService.getChiTietResponsesIdHoaDon(hoaDonByMa.getId());
        for (int i = 0; i < hoaDonChiTietResponses.size(); ++i) {
            HoaDonChiTietResponse hoaDonChiTietResponse = hoaDonChiTietResponses.get(i);
            List<LoaiBaoHanh> loaiBaoHanhsMacDinh = LoaiBaoHanhRepository.getListLoaiBHMacDinh();
            
            ChiTietPhieuBaoHanh chiTietPhieuBaoHanh = new ChiTietPhieuBaoHanh();
            chiTietPhieuBaoHanh.setImei(hoaDonChiTietResponse.getImei());
            chiTietPhieuBaoHanh.setThoiHanBaoHanh(12);
            chiTietPhieuBaoHanh.setNgayMuaHang(LocalDate.now());
            chiTietPhieuBaoHanh.setNgayHetHan(LocalDate.now().plusMonths(12));
            chiTietPhieuBaoHanh.setMoTa("...");
            chiTietPhieuBaoHanh.setTrangThai(true);
            
            PhieuBaoHanh phieuBaoHanh = new PhieuBaoHanh();
            HoaDonChiTiet hoaDonChiTiet = HoaDonRepository.getChiTietEntityById(hoaDonChiTietResponse.getId());
            phieuBaoHanh.setHoaDonChiTiet(hoaDonChiTiet);
            phieuBaoHanh.setChiTietPhieuBaoHanh(chiTietPhieuBaoHanh);
            loaiBaoHanhsMacDinh.forEach(lbh -> phieuBaoHanh.addLoaiBaoHanh(lbh));
            
            phieuBaoHanhService.add(phieuBaoHanh);
        }

        // 4. trừ lượt sử dụng của phiếu giảm giá
        if (phieuGiamGia != null) {
            PhieuGiamGiaChiTiet phieuChiTiet = phieuGiamGia.getPhieuGiamGiaChiTiet();
            phieuChiTiet.setLuotSuDung(phieuChiTiet.getLuotSuDung() - 1);
            PhieuGiamGiaRepository.updateLuotSuDung(phieuChiTiet);
        }

        // 5. trừ điểm tích lũy (trừ nếu đã dùng)
        if (chkboxSuDungDiem.isSelected()) {
            KhachHangRepository.updateDiemTichLuy(khResponse, 0);
        }

        // 6. cộng điểm tích lũy
        double diemTichLuy = 0;
        diemTichLuy = (tongTien - tienGiam) / 50000;
        
        Double diemTichLuyDou = Double.valueOf(diemTichLuy);
        int diemTichLuyInt = diemTichLuyDou.intValue();
        if (!chkboxSuDungDiem.isSelected()) {
            diemTichLuyInt += khachHang.getTheTichDiem().getSoDiem();
        }
        KhachHangRepository.updateDiemTichLuy(khResponse, diemTichLuyInt);

        // 7. làm mới form
        lamMoiForm1();
        lamMoiForm2();

        // 8. In hóa đơn
        int confirm1 = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn không?");
        if (confirm1 == JOptionPane.YES_OPTION) {
            JFileChooser chooser = new JFileChooser("D:\\");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Giới hạn chỉ chọn đc thư mục
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
                export.exportBill3(hoaDon, hoaDonChiTietResponses, path);
                JOptionPane.showMessageDialog(this, "In hóa đơn thành công");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "In hóa đơn thất bại");
            }
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed
    
    private String checkHoaDon1() {
        String message = "";

        // khách hàng
        String emailKhachHang = lbEmailKhachHang.getText().trim().substring(7);
        String sdtKhachHang = lbSdtKhachHang.getText().trim().substring(5);
        
        KhachHangResponse khResponse = KhachHangRepository.getKhachHangByEmailOrSDT(emailKhachHang);
        if (khResponse == null) {
            khResponse = KhachHangRepository.getKhachHangByEmailOrSDT(sdtKhachHang);
        }
        if (khResponse == null) {
            message += "Chưa có thông tin khách hàng!\n";
        }

        // số lượng đt trong giỏ hàng
        if (hoaDonChiTietResponseList.size() == 0) {
            message += "Giỏ hàng chưa có sản phẩm!\n";
        }

        // tiền khách đưa (không được trống, đúng định dạng, phải lớn hơn hoặc bằng tiền phải trả)
        String tienKhachDuaStr = txtTienKhachDua.getText().trim();
        if (tienKhachDuaStr.isBlank()) {
            message += "Tiền khách đưa không được để trống!\n";
        } else {
            String pattern = "[1-9][0-9]*";
            if (!tienKhachDuaStr.matches(pattern)) {
                message += "Tiền khách đưa không đúng định dạng!\n";
            } else {
                long tienThua = Long.valueOf(lbTienThua.getText().trim().replaceAll(",", ""));
                if (tienThua < 0) {
                    message += "Tiền khách đưa không đủ!\n";
                }
            }
        }

        // nếu chuyển khoản, check mã GD
        if (rdChuyenKhoan.isSelected()) {
            String maGiaoDich = txtMaGD.getText().trim();
            if (maGiaoDich.isBlank()) {
                message += "Mã giao dịch không được để trống!\n";
            }
        }
        return message;
    }
    
    private String getMa(String loaiMa) {
        LocalDateTime ldt = LocalDateTime.now();
        String year = String.valueOf(ldt.getYear());
        String month = String.valueOf(ldt.getMonthValue());
        String day = String.valueOf(ldt.getDayOfMonth());
        String hour = String.valueOf(ldt.getHour());
        String minute = String.valueOf(ldt.getMinute());
        String second = String.valueOf(ldt.getSecond());
        
        String ma = loaiMa + year;
        if (month.length() < 2) {
            month = "0" + month;
            ma += month;
        } else {
            ma += month;
        }
        
        if (day.length() < 2) {
            day = "0" + day;
            ma += day + "T";
        } else {
            ma += day + "T";
        }
        
        if (hour.length() < 2) {
            hour = "0" + hour;
            ma += hour;
        } else {
            ma += hour;
        }
        
        if (minute.length() < 2) {
            minute = "0" + minute;
            ma += minute;
        } else {
            ma += minute;
        }
        
        if (second.length() < 2) {
            second = "0" + second;
            ma += second;
        } else {
            ma += second;
        }
        
        return ma;
    }
    
    private void lamMoiForm1() {
        txtTimKH.setText("");
        lbTenKhachHang.setText("Tên khách hàng");
        lbSdtKhachHang.setText("Email khách hàng");
        lbEmailKhachHang.setText("SĐT khách hàng");
        
        lbTongTien.setText("0");
        lbMaGiamGia.setText("Mã GG");
        lbSoDiem.setText("Số điểm");
        lbSoTienTuDiem.setText("Số tiền quy đổi từ điểm");
        chkboxSuDungDiem.setSelected(false);
        lbTienGiam.setText("0");
        lbKhachPhaiTra.setText("0");
        txtTienKhachDua.setText("");
        lbTienThua.setText("0");
    }

    private void btnThanhToan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToan2ActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Tạo hóa đơn?", "Xác nhận tạo hóa đơn", JOptionPane.YES_NO_OPTION);
        if (confirm != 0) {
            return;
        }
        
        String check = checkHoaDon2();
        if (!check.equals("")) {
            JOptionPane.showMessageDialog(this, check);
            return;
        }
        
        HoaDon hoaDon = new HoaDon();
        String maHoaDon = getMa("HD");
        hoaDon.setMaHoaDon(maHoaDon);
        hoaDon.setNgayTao(LocalDateTime.now());
        hoaDon.setNgayThanhToan(LocalDateTime.now());
        
        long tienGiam = Long.valueOf(lbTienGiam2.getText().trim().replaceAll(",", ""));
        hoaDon.setTienGiam(tienGiam);
        
        long tongTien = Long.valueOf(lbTongTien2.getText().trim().replaceAll(",", ""));
        hoaDon.setTongTien(tongTien);
        
        long tienTraTruoc = Long.valueOf(txtTienTraTruoc.getText().trim());
        hoaDon.setTienTraTruoc(tienTraTruoc);
        
        long tienThieu = Long.valueOf(lbConNo.getText().trim().replaceAll(",", ""));
        hoaDon.setTienThieu(tienThieu);
        
        hoaDon.setTraGop(true);
        hoaDon.setTienKhachDua(0L);
        hoaDon.setTienThua(0);
        
        if (rdTienMat2.isSelected()) {
            hoaDon.setHinhThucThanhToan(true);
        } else {
            hoaDon.setHinhThucThanhToan(false);
            String maGiaoDinh = txtMaGD2.getText().trim();
            hoaDon.setMaGiaoDichChuyenKhoan(maGiaoDinh);
        }

        // khách hàng, nhân viên, phiếu giảm giá
        // 1. khách hàng
        String emailKhachHang = lbEmailKhachHang.getText().trim().substring(7);
        String sdtKhachHang = lbSdtKhachHang.getText().trim().substring(5);
        
        KhachHangResponse khResponse = KhachHangRepository.getKhachHangByEmailOrSDT(emailKhachHang);
        if (khResponse == null) {
            khResponse = KhachHangRepository.getKhachHangByEmailOrSDT(sdtKhachHang);
        }
        KhachHang khachHang = KhachHangRepository.getKhachHangEntityById(khResponse.getId());
        hoaDon.setKhachHang(khachHang);

        // 2. nhân viên (đang login)
        NhanVien nhanVien = NhanVienRepository.getEntityById(loggedNhanVienResponse.getId());
        hoaDon.setNhanVien(nhanVien);

        // 3. phiếu giảm giá
        String maPhieu = lbMaGiamGia2.getText().split(" - ")[0];
        PhieuGiamGia phieuGiamGia = PhieuGiamGiaRepository.getPhieuByMa(maPhieu);
        if (phieuGiamGia != null) {
            hoaDon.setPhieuGiamGia(phieuGiamGia);
        }
        
        hoaDonChiTietResponseList.forEach(h -> {
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            
            hoaDonChiTiet.setHoaDon(hoaDon);
            Imei imei = ImeiRepository.getByImei(h.getImei());
            hoaDonChiTiet.setImei(imei);
            hoaDon.addHoaDonChiTiet(hoaDonChiTiet);
        });
        
        String addResult = hoaDonService.add(hoaDon);
        JOptionPane.showMessageDialog(this, addResult);

        // sau khi add hóa đơn thành công thì phải:
        // 1. update trạng thái của tất cả imei trong hóa đơn về 1 - 'đã bán'
        hoaDonChiTietResponseList.forEach(h -> {
            imeiService.updateImeiTrangThai(h.getImei(), 1);
        });

        // 2. xóa giỏ hàng
        hoaDonChiTietResponseList = new ArrayList<>();
        jplDonHang jDonHang = (jplDonHang) jTabbedPane1.getSelectedComponent();
        jDonHang.setHoaDonChiTiets(new ArrayList<>());
        jDonHang.load();
        
        int indexDon = jTabbedPane1.getSelectedIndex();
        // sẽ không thể xóa khi còn 1 đơn duy nhất
        if (jTabbedPane1.getTabCount() > 1) {
            jTabbedPane1.remove(indexDon);
        }

        // 3. trừ lượt sử dụng của phiếu giảm giá
        if (phieuGiamGia != null) {
            PhieuGiamGiaChiTiet phieuChiTiet = phieuGiamGia.getPhieuGiamGiaChiTiet();
            phieuChiTiet.setLuotSuDung(phieuChiTiet.getLuotSuDung() - 1);
            PhieuGiamGiaRepository.updateLuotSuDung(phieuChiTiet);
        }

        // 4. trừ điểm tích lũy (trừ nếu đã dùng)
        if (chkboxSuDungDiem2.isSelected()) {
            KhachHangRepository.updateDiemTichLuy(khResponse, 0);
        }

        // 5. cộng điểm tích lũy
        double diemTichLuy = 0;
        diemTichLuy = tienTraTruoc / 50000;
        Double diemTichLuyDou = Double.valueOf(diemTichLuy);
        int diemTichLuyInt = diemTichLuyDou.intValue();
        if (!chkboxSuDungDiem2.isSelected()) {
            diemTichLuyInt += khachHang.getTheTichDiem().getSoDiem();
        }
        KhachHangRepository.updateDiemTichLuy(khResponse, diemTichLuyInt);

        // 6. tạo phiếu bảo hành (Hiếu)
        HoaDon hoaDonByMa = hoaDonService.getEntityByMa(maHoaDon);
        List<HoaDonChiTietResponse> hoaDonChiTietResponses = hoaDonService.getChiTietResponsesIdHoaDon(hoaDonByMa.getId());
        for (int i = 0; i < hoaDonChiTietResponses.size(); ++i) {
            HoaDonChiTietResponse hoaDonChiTietResponse = hoaDonChiTietResponses.get(i);
            List<LoaiBaoHanh> loaiBaoHanhsMacDinh = LoaiBaoHanhRepository.getListLoaiBHMacDinh();
            
            ChiTietPhieuBaoHanh chiTietPhieuBaoHanh = new ChiTietPhieuBaoHanh();
            chiTietPhieuBaoHanh.setImei(hoaDonChiTietResponse.getImei());
            chiTietPhieuBaoHanh.setThoiHanBaoHanh(12);
            chiTietPhieuBaoHanh.setNgayMuaHang(LocalDate.now());
            chiTietPhieuBaoHanh.setNgayHetHan(LocalDate.now().plusMonths(12));
            chiTietPhieuBaoHanh.setMoTa("...");
            chiTietPhieuBaoHanh.setTrangThai(true);
            
            PhieuBaoHanh phieuBaoHanh = new PhieuBaoHanh();
            HoaDonChiTiet hoaDonChiTiet = HoaDonRepository.getChiTietEntityById(hoaDonChiTietResponse.getId());
            phieuBaoHanh.setHoaDonChiTiet(hoaDonChiTiet);
            phieuBaoHanh.setChiTietPhieuBaoHanh(chiTietPhieuBaoHanh);
            loaiBaoHanhsMacDinh.forEach(lbh -> phieuBaoHanh.addLoaiBaoHanh(lbh));
            
            phieuBaoHanhService.add(phieuBaoHanh);
        }

        // 7. tạo phiếu trả góp, lịch sử trả góp (Hùng)
        LichSuTraGop lichSuTraGop = new LichSuTraGop();
        lichSuTraGop.setMa(getMa("LS"));
        lichSuTraGop.setNgayThanhToan(LocalDate.now());
        lichSuTraGop.setTongTien(tienTraTruoc);
        lichSuTraGop.setGhiChu("...");
        
        PhieuTraGop phieuTraGop = new PhieuTraGop();
        phieuTraGop.setMaPhieu(getMa("PTG"));
        phieuTraGop.setTongPhaiTra(Long.valueOf(lbKhachPhaiTra2.getText().trim().replaceAll(",", "")));
        phieuTraGop.setLaiSuat(Float.valueOf(txtLaiSuat.getText().trim()));
        String kyHanItem = String.valueOf(cbKyHan.getSelectedItem()).substring(0, 1);
        phieuTraGop.setKyHan(Integer.valueOf(kyHanItem));
        phieuTraGop.setNgayTao(LocalDate.now());
        phieuTraGop.setPhaiTra(Long.valueOf(lbTraHangThang.getText().trim().replaceAll(",", "")));
        phieuTraGop.setTrangThai(false);
        phieuTraGop.setHoaDon(hoaDonByMa);
        phieuTraGop.addLichSuTraGop(lichSuTraGop);
        
        phieuTraGopService.insert(phieuTraGop);

        // 8. làm mới form
        lamMoiForm2();

        // 9. in hóa đơn
        int confirm1 = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn không?");
        if (confirm1 == JOptionPane.YES_OPTION) {
            JFileChooser chooser = new JFileChooser("D:\\");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Giới hạn chỉ chọn đc thư mục
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
                export.exportBill3(hoaDon, hoaDonChiTietResponses, path);
                JOptionPane.showMessageDialog(this, "In hóa đơn thành công");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "In hóa đơn thất bại");
            }
        }
    }//GEN-LAST:event_btnThanhToan2ActionPerformed
    
    private String checkHoaDon2() {
        String message = "";

        // khách hàng
        String emailKhachHang = lbEmailKhachHang.getText().trim().substring(7);
        String sdtKhachHang = lbSdtKhachHang.getText().trim().substring(5);
        
        KhachHangResponse khResponse = KhachHangRepository.getKhachHangByEmailOrSDT(emailKhachHang);
        if (khResponse == null) {
            khResponse = KhachHangRepository.getKhachHangByEmailOrSDT(sdtKhachHang);
        }
        if (khResponse == null) {
            message += "Chưa có thông tin khách hàng!\n";
        }

        // số lượng đt trong giỏ hàng
        if (hoaDonChiTietResponseList.size() == 0) {
            message += "Giỏ hàng chưa có sản phẩm!\n";
        }

        // tiền khách đưa (không được trống, đúng định dạng, phải lớn hơn hoặc bằng tiền phải trả)
        String tienTraTruocStr = txtTienTraTruoc.getText().trim();
        if (tienTraTruocStr.isBlank()) {
            message += "Tiền trả trước không được để trống!\n";
        } else {
            String pattern = "[1-9][0-9]*";
            if (!tienTraTruocStr.matches(pattern)) {
                message += "Tiền trả trước không đúng định dạng!\n";
            } else {
                long traTruocToiThieu = Long.valueOf(lbTraTruocToiThieu.getText().trim().replaceAll(",", ""));
                long tienTraTruoc = Long.valueOf(tienTraTruocStr);
                if (tienTraTruoc < traTruocToiThieu) {
                    message += "Tiền trả trước không đủ!\n";
                }
            }
        }
        
        String laiSuatStr = txtLaiSuat.getText().trim();
        if (laiSuatStr.isBlank()) {
            message += "Lãi suất không được để trống!\n";
        }

        // nếu chuyển khoản, check mã GD
        if (rdChuyenKhoan.isSelected()) {
            String maGiaoDich = txtMaGD.getText().trim();
            if (maGiaoDich.isBlank()) {
                message += "Mã giao dịch không được để trống!\n";
            }
        }
        return message;
    }
    
    private void lamMoiForm2() {
        txtTimKH.setText("");
        lbTenKhachHang.setText("Tên khách hàng");
        lbEmailKhachHang.setText("Email khách hàng");
        lbSdtKhachHang.setText("SĐT khách hàng");
        
        lbTongTien2.setText("0");
        lbMaGiamGia2.setText("Mã GG");
        lbSoDiem2.setText("Số điểm");
        lbSoTienTuDiem2.setText("Số tiền quy đổi từ điểm");
        chkboxSuDungDiem2.setSelected(false);
        
        lbTienGiam2.setText("0");
        lbKhachPhaiTra2.setText("0");
        lbTraTruocToiThieu.setText("0");
        txtTienTraTruoc.setText("");
        lbConNo.setText("0");
        txtLaiSuat.setText("");
        lbTongNo.setText("0");
        cbKyHan.setSelectedIndex(0);
        lbTraHangThang.setText("0");
        
    }

    private void btnXoaDonHang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaDonHang1ActionPerformed
        if (jTabbedPane1.getTabCount() > 1) {
            int chooser = JOptionPane.showConfirmDialog(this, "Hệ thống sẽ xóa tất cả dữ liệu của đơn hàng này?", "Xóa đơn hàng chờ", JOptionPane.YES_NO_OPTION);
            if (chooser == JOptionPane.YES_OPTION) {
                for (int i = 0; i < hoaDonChiTietResponseList.size(); ++i) {
                    HoaDonChiTietResponse hdctResponse = hoaDonChiTietResponseList.get(i);
                    String imeiStr = hdctResponse.getImei();
                    imeiService.updateImeiTrangThai(imeiStr, 0);
                    DienThoaiRepository.updateSoLuongDienThoai(imeiStr, 1);
                    
                    Imei imei = ImeiRepository.getByImei(imeiStr);
                    DienThoai dienThoai = imei.getDienThoai();
                    
                    DienThoaiResponse dienThoaiResponse = getDienThoaiResponse(dienThoai.getId());
                    dienThoaiResponse.setSoLuong(dienThoaiResponse.getSoLuong() + 1);
                    showDienThoaiTable(dienThoaiResponseList);
                }
                jTabbedPane1.remove(jTabbedPane1.getSelectedIndex());

                // sau khi xóa, hiển thị lại các hóa đơn ct đang hiện lên màn hình
                jplDonHang jDonHang = (jplDonHang) jTabbedPane1.getSelectedComponent();
                List<HoaDonChiTietResponse> currentList = jDonHang.getHoaDonChiTiets();
                jDonHang.load();
                
                hoaDonChiTietResponseList = new ArrayList<>();
                hoaDonChiTietResponseList.addAll(currentList);
                
                try {
                    showHoaDonInfo1();
                    showHoaDonInfo2();
                } catch (Exception ex) {
                }
            }
        } else if (jTabbedPane1.getTabCount() == 1) {
            int chooser = JOptionPane.showConfirmDialog(this, "Hệ thống sẽ xóa tất cả dữ liệu của đơn hàng này?", "Xóa đơn hàng chờ", JOptionPane.YES_NO_OPTION);
            if (chooser == JOptionPane.YES_OPTION) {
                for (int i = 0; i < hoaDonChiTietResponseList.size(); ++i) {
                    HoaDonChiTietResponse hdctResponse = hoaDonChiTietResponseList.get(i);
                    String imeiStr = hdctResponse.getImei();
                    imeiService.updateImeiTrangThai(imeiStr, 0);
                    DienThoaiRepository.updateSoLuongDienThoai(imeiStr, 1);
                    
                    Imei imei = ImeiRepository.getByImei(imeiStr);
                    DienThoai dienThoai = imei.getDienThoai();
                    
                    DienThoaiResponse dienThoaiResponse = getDienThoaiResponse(dienThoai.getId());
                    dienThoaiResponse.setSoLuong(dienThoaiResponse.getSoLuong() + 1);
                    showDienThoaiTable(dienThoaiResponseList);
                }

                // sau khi xóa, hiển thị lại các hóa đơn ct đang hiện lên màn hình
                jplDonHang jDonHang = (jplDonHang) jTabbedPane1.getSelectedComponent();
                jDonHang.setHoaDonChiTiets(new ArrayList<>());
                jDonHang.load();
                
                hoaDonChiTietResponseList = new ArrayList<>();
                try {
                    showHoaDonInfo1();
                    showHoaDonInfo2();
                } catch (Exception ex) {
                }
            }
        }
    }//GEN-LAST:event_btnXoaDonHang1ActionPerformed
    
    private DienThoaiResponse getDienThoaiResponse(int dienThoaiId) {
        for (int i = 0; i < dienThoaiResponseList.size(); ++i) {
            DienThoaiResponse dienThoaiResponse = dienThoaiResponseList.get(i);
            if (dienThoaiResponse.getId() == dienThoaiId) {
                return dienThoaiResponse;
            }
        }
        return null;
    }
    private void cbImeiInDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbImeiInDialogActionPerformed
        String selectedImei = (String) cbImeiInDialog.getSelectedItem();
        txtImei.setText(selectedImei);
    }//GEN-LAST:event_cbImeiInDialogActionPerformed

    private void cbHangDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbHangDTActionPerformed
        String selectedTenHang = (String) cbHangDT.getSelectedItem();
        if (selectedTenHang != null) {
            if (selectedTenHang.equals("Tất cả")) {
                dienThoaiResponseList = dienThoaiService.getAllResponseByStatus(true);
                showDienThoaiTable(dienThoaiResponseList);
                return;
            }
        }
        
        dienThoaiResponseList = dienThoaiService.getResponsesByHang(selectedTenHang);
        showDienThoaiTable(dienThoaiResponseList);
    }//GEN-LAST:event_cbHangDTActionPerformed

    private void btnTimKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKHActionPerformed
        String sdtOrEmail = txtTimKH.getText().trim();
        KhachHangResponse khachHangResponse = KhachHangRepository.getKhachHangByEmailOrSDT(sdtOrEmail);
        
        if (khachHangResponse == null) {
            JOptionPane.showMessageDialog(this, "Khách hàng không tồn tại!");
            return;
        }
        
        lbTenKhachHang.setText("Họ tên: " + khachHangResponse.getHoTen());
        lbEmailKhachHang.setText("Email: " + khachHangResponse.getEmail());
        lbSdtKhachHang.setText("SĐT: " + khachHangResponse.getSdt());
        
        int soDiem = khachHangResponse.getSoDiem();
        lbSoDiem.setText(String.valueOf(soDiem));
        lbSoDiem2.setText(String.valueOf(soDiem));
        
        long soTienTuDiem = soDiem * 1000;
        lbSoTienTuDiem.setText("(" + numberFormat.format(soTienTuDiem) + ")");
        lbSoTienTuDiem2.setText("(" + numberFormat.format(soTienTuDiem) + ")");
    }//GEN-LAST:event_btnTimKHActionPerformed

    private void chkboxSuDungDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkboxSuDungDiemActionPerformed
        if (lbSoDiem.getText().equals("Số điểm")) {
            return;
        }
        
        float soTienTuDiem = Float.valueOf(lbSoDiem.getText()) * 1000;
        float tienGiam = Float.valueOf(lbTienGiam.getText().trim().replaceAll(",", ""));
        
        if (chkboxSuDungDiem.isSelected()) {
            tienGiam += soTienTuDiem;
        } else {
            tienGiam -= soTienTuDiem;
        }
        lbTienGiam.setText(numberFormat.format(tienGiam));
        
        long tongTien = Long.valueOf(lbTongTien.getText().replaceAll(",", ""));
        long khachPhaiTra = tongTien - Long.valueOf(lbTienGiam.getText().replaceAll(",", ""));
        lbKhachPhaiTra.setText(numberFormat.format(khachPhaiTra));
    }//GEN-LAST:event_chkboxSuDungDiemActionPerformed

    private void chkboxSuDungDiem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkboxSuDungDiem2ActionPerformed
        if (lbSoDiem2.getText().equals("Số điểm")) {
            return;
        }
        
        float soTienTuDiem = Float.valueOf(lbSoDiem2.getText()) * 1000;
        float tienGiam = Float.valueOf(lbTienGiam2.getText().trim().replaceAll(",", ""));
        
        if (chkboxSuDungDiem2.isSelected()) {
            tienGiam += soTienTuDiem;
        } else {
            tienGiam -= soTienTuDiem;
        }
        lbTienGiam2.setText(numberFormat.format(tienGiam));
        
        long tongTien = Long.valueOf(lbTongTien2.getText().replaceAll(",", ""));
        long khachPhaiTra = tongTien - Long.valueOf(lbTienGiam2.getText().replaceAll(",", ""));
        lbKhachPhaiTra2.setText(numberFormat.format(khachPhaiTra));
        
        long traTruocToiThieu = Long.valueOf(lbKhachPhaiTra2.getText().replaceAll(",", "")) / 2;
        lbTraTruocToiThieu.setText(numberFormat.format(traTruocToiThieu));
        
        String traTruocToiThieuStr = lbTraTruocToiThieu.getText().replaceAll(",", "");
        traTruocToiThieuStr = traTruocToiThieuStr.substring(0, traTruocToiThieuStr.length() - 3).concat("000");
        traTruocToiThieu = Long.valueOf(traTruocToiThieuStr);
        lbTraTruocToiThieu.setText(numberFormat.format(traTruocToiThieu));
    }//GEN-LAST:event_chkboxSuDungDiem2ActionPerformed

    private void btnXoaHDChiTiet1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaHDChiTiet1ActionPerformed
        ThemByImei.setSize(330, 130);
        ThemByImei.setResizable(false);
        ThemByImei.setLocationRelativeTo(null);
        ThemByImei.setVisible(true);
    }//GEN-LAST:event_btnXoaHDChiTiet1ActionPerformed

    private void rdChuyenKhoan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdChuyenKhoan2ActionPerformed
        chuyenKhoan2(true);
    }//GEN-LAST:event_rdChuyenKhoan2ActionPerformed

    private void rdTienMat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdTienMat2ActionPerformed
        chuyenKhoan2(false);
    }//GEN-LAST:event_rdTienMat2ActionPerformed

    private void rdTienMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdTienMatActionPerformed
        chuyenKhoan(false);
    }//GEN-LAST:event_rdTienMatActionPerformed

    private void rdChuyenKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdChuyenKhoanActionPerformed
        chuyenKhoan(true);
    }//GEN-LAST:event_rdChuyenKhoanActionPerformed

    private void btnOkThemImeiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkThemImeiActionPerformed
        String imeiStr = txtThemImei.getText().trim();
        if (imeiStr.isBlank()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền IMEI!");
            return;
        }
        
        Imei imei = ImeiRepository.getByImei(imeiStr);
        if (imei == null) {
            JOptionPane.showMessageDialog(this, "Imei không tồn tại!");
            return;
        }
        
        ThemByImei.setVisible(false);
        
        DienThoai dienThoai = imei.getDienThoai();
        DienThoaiResponse dienThoaiResponse = new DienThoaiResponse();
        for (int i = 0; i < dienThoaiResponseList.size(); ++i) {
            if (dienThoaiResponseList.get(i).getId() == dienThoai.getId()) {
                dienThoaiResponse = dienThoaiResponseList.get(i);
                break;
            }
        }

        // thay đổi trạng thái imei về 'đang trong giỏ hàng - 2'
        imeiService.updateImeiTrangThai(imeiStr, 2);
        // thay đổi số lượng tồn kho của điện thoại vừa chọn
        DienThoaiRepository.updateSoLuongDienThoai(imeiStr, -1);
        
        dienThoaiResponse.setSoLuong(dienThoaiResponse.getSoLuong() - 1);
        
        HoaDonChiTietResponse hoaDonChiTietResponse = new HoaDonChiTietResponse();
        hoaDonChiTietResponse.setImei(imeiStr);
        hoaDonChiTietResponse.setTenDienThoai(dienThoaiResponse.getTenDT());
        hoaDonChiTietResponse.setDonGia(dienThoaiResponse.getGiaBan());
        hoaDonChiTietResponseList.add(hoaDonChiTietResponse);
        
        jplDonHang jDonHang = (jplDonHang) jTabbedPane1.getSelectedComponent();
        jDonHang.setHoaDonChiTiets(hoaDonChiTietResponseList);
        jDonHang.load();
        
        showHoaDonInfo1();
        showHoaDonInfo2();
        showDienThoaiTable(dienThoaiResponseList);
    }//GEN-LAST:event_btnOkThemImeiActionPerformed

    private void cbHangDTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbHangDTMouseClicked
        HangService hangService = new HangServiceImpl();
        List<HangResponse> hangResponseList = hangService.getAllResponseByStatus(true);
        
        cbHangDT.removeAllItems();
        cbHangDT.addItem("Tất cả");
        hangResponseList.forEach(h -> cbHangDT.addItem(h.getTenHang()));
    }//GEN-LAST:event_cbHangDTMouseClicked

    private void btnReloadDTTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadDTTableActionPerformed
        dienThoaiResponseList = dienThoaiService.getAllResponseByStatus(true);
        showDienThoaiTable(dienThoaiResponseList);
        txtSearchByTen.setText("");
        cbHangDT.setSelectedIndex(0);
    }//GEN-LAST:event_btnReloadDTTableActionPerformed
    
    private void showHoaDonInfo1() {
        try {
            // Tab 1
            // Thêm ĐT/ Xóa ĐT -> 1. Tổng tiền -> 2. phiếu GG -> 3. Tiền giảm -> 4. Tiền khách phải trả

            // 1. Tổng tiền
            long tongTien = 0L;
            for (int i = 0; i < hoaDonChiTietResponseList.size(); ++i) {
                tongTien += hoaDonChiTietResponseList.get(i).getDonGia();
            }
            lbTongTien.setText(numberFormat.format(tongTien));

            // 2. Phiếu giảm giá
            List<PhieuGiamGiaResponse> phieuGiamGiaResponseList = phieuGiamGiaService.getAllForView(tongTien);
            PhieuGiamGiaResponse pgg = phieuGiamGiaResponseList
                    .stream()
                    .max(Comparator.comparing(PhieuGiamGiaResponse::getGiaTri))
                    .orElse(null);
            if (pgg != null) {
                lbMaGiamGia.setText(pgg.getMaPhieu() + " - " + pgg.getGiaTri() + "%");
            }

            // 3. Tiền giảm
            if (pgg != null) {
                // tiền giảm từ điểm
                float soTienTuDiem = 0.0f;
                if (!lbSoDiem.getText().equals("Số điểm")) {
                    soTienTuDiem = Float.valueOf(lbSoDiem.getText()) * 1000;
                }

                // tiền giảm từ mã
                float giaTri = pgg.getGiaTri();
                float tienGiam = Math.round(Float.valueOf(tongTien) * giaTri / 100);

                // check xem có dùng điểm tích lũy hay không:
                if (chkboxSuDungDiem.isSelected()) {
                    tienGiam += soTienTuDiem;
                }
                lbTienGiam.setText(numberFormat.format(tienGiam));

                // làm tròn để tiền không bị lẻ
                String tienGiamStr = lbTienGiam.getText().replaceAll(",", "");
                tienGiamStr = tienGiamStr.substring(0, tienGiamStr.length() - 3).concat("000");
                tienGiam = Float.valueOf(tienGiamStr);
                lbTienGiam.setText(numberFormat.format(tienGiam));

                // 4. Tiền khách phải trả
                long khachPhaiTra = tongTien - Long.valueOf(lbTienGiam.getText().replaceAll(",", ""));
                lbKhachPhaiTra.setText(numberFormat.format(khachPhaiTra));
            }
        } catch (Exception e) {
        }
    }
    
    private void showHoaDonInfo2() {
        try {
            // Tab 2
            // Thêm ĐT/ Xóa ĐT -> 1. Tổng tiền -> 2. phiếu GG -> 3. Tiền giảm -> 4. Tiền khách phải trả -> 5.Trả trước tối thiều

            // 1. Tổng tiền
            long tongTien = 0L;
            for (int i = 0; i < hoaDonChiTietResponseList.size(); ++i) {
                tongTien += hoaDonChiTietResponseList.get(i).getDonGia();
            }
            lbTongTien2.setText(numberFormat.format(tongTien));

            // 2. Phiếu giảm giá
            List<PhieuGiamGiaResponse> phieuGiamGiaResponseList = phieuGiamGiaService.getAllForView(tongTien);
            PhieuGiamGiaResponse pgg = phieuGiamGiaResponseList
                    .stream()
                    .max(Comparator.comparing(PhieuGiamGiaResponse::getGiaTri))
                    .orElse(null);
            if (pgg != null) {
                lbMaGiamGia2.setText(pgg.getMaPhieu() + " - " + pgg.getGiaTri() + "%");
            }

            // 3. Tiền giảm
            if (pgg != null) {
                // tiền giảm từ điểm
                float soTienTuDiem = 0.0f;
                if (!lbSoDiem2.getText().equals("Số điểm")) {
                    soTienTuDiem = Float.valueOf(lbSoDiem2.getText()) * 1000;
                }

                // tiền giảm từ mã
                float giaTri = pgg.getGiaTri();
                float tienGiam = Math.round(Float.valueOf(tongTien) * giaTri / 100);

                // check xem có dùng điểm tích lũy hay không:
                if (chkboxSuDungDiem2.isSelected()) {
                    tienGiam += soTienTuDiem;
                }
                lbTienGiam2.setText(numberFormat.format(tienGiam));

                // làm tròn tiền giảm để tiền không bị lẻ
                String tienGiamStr = lbTienGiam2.getText().replaceAll(",", "");
                tienGiamStr = tienGiamStr.substring(0, tienGiamStr.length() - 3).concat("000");
                tienGiam = Float.valueOf(tienGiamStr);
                lbTienGiam2.setText(numberFormat.format(tienGiam));

                // 4. Tiền khách phải trả
                long khachPhaiTra2 = tongTien - Long.valueOf(lbTienGiam2.getText().replaceAll(",", ""));
                lbKhachPhaiTra2.setText(numberFormat.format(khachPhaiTra2));
            }

            // 5. Trả trước tối thiểu
            long traTruocToiThieu = Long.valueOf(lbKhachPhaiTra2.getText().replaceAll(",", "")) / 2;
            lbTraTruocToiThieu.setText(numberFormat.format(traTruocToiThieu));

            // làm tròn tiền trả trước tối thiểu để không bị lẻ
            String traTruocToiThieuStr = lbTraTruocToiThieu.getText().replaceAll(",", "");
            traTruocToiThieuStr = traTruocToiThieuStr.substring(0, traTruocToiThieuStr.length() - 3).concat("000");
            traTruocToiThieu = Long.valueOf(traTruocToiThieu);
            lbTraTruocToiThieu.setText(numberFormat.format(traTruocToiThieu));
        } catch (Exception e) {
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Jpanel20;
    private javax.swing.JDialog ThemByImei;
    private javax.swing.JDialog ThemKhachHang;
    private javax.swing.JDialog XemChiTiet;
    private javax.swing.JButton btnOkImei;
    private javax.swing.JButton btnOkThemImei;
    private javax.swing.JButton btnReloadDTTable;
    private javax.swing.JButton btnSapXepGiaGiamDan;
    private javax.swing.JButton btnSapXepGiaTangDan;
    private javax.swing.JButton btnTaoDonHang;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThanhToan2;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKH;
    private javax.swing.JButton btnXoaDonHang1;
    private javax.swing.JButton btnXoaHDChiTiet;
    private javax.swing.JButton btnXoaHDChiTiet1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JComboBox<String> cbHangDT;
    private javax.swing.JComboBox<String> cbImeiInDialog;
    private javax.swing.JComboBox<String> cbKyHan;
    private javax.swing.JRadioButton cboNam;
    private javax.swing.JRadioButton cboNu;
    private javax.swing.JCheckBox chkTrangThai;
    private javax.swing.JCheckBox chkboxSuDungDiem;
    private javax.swing.JCheckBox chkboxSuDungDiem2;
    private javax.swing.JLabel image;
    private javax.swing.JDialog imeiDialog;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private com.toedter.calendar.JDateChooser jdateNgaySinh;
    private javax.swing.JPanel jplGioHang;
    private javax.swing.JPanel jpnFormBanHang;
    private javax.swing.JLabel lbCTCPU;
    private javax.swing.JLabel lbCTCamChinh;
    private javax.swing.JLabel lbCTCamGocRong;
    private javax.swing.JLabel lbCTCamPhu;
    private javax.swing.JLabel lbCTCamTele;
    private javax.swing.JLabel lbCTDoPhanGiai;
    private javax.swing.JLabel lbCTDongSP;
    private javax.swing.JLabel lbCTGiaBan;
    private javax.swing.JLabel lbCTHang;
    private javax.swing.JLabel lbCTHeDH;
    private javax.swing.JLabel lbCTKichThuoc;
    private javax.swing.JLabel lbCTLoaiMH;
    private javax.swing.JLabel lbCTMaDT;
    private javax.swing.JLabel lbCTMauSac;
    private javax.swing.JLabel lbCTPin;
    private javax.swing.JLabel lbCTRam;
    private javax.swing.JLabel lbCTRom;
    private javax.swing.JLabel lbCTSoLuong;
    private javax.swing.JLabel lbCTTenDT;
    private javax.swing.JLabel lbConNo;
    private javax.swing.JLabel lbEmailKhachHang;
    private javax.swing.JLabel lbKhachPhaiTra;
    private javax.swing.JLabel lbKhachPhaiTra2;
    private javax.swing.JLabel lbMaGD;
    private javax.swing.JLabel lbMaGD2;
    private javax.swing.JLabel lbMaGiamGia;
    private javax.swing.JLabel lbMaGiamGia2;
    private javax.swing.JLabel lbNhanVien;
    private javax.swing.JLabel lbSdtKhachHang;
    private javax.swing.JLabel lbSoDiem;
    private javax.swing.JLabel lbSoDiem2;
    private javax.swing.JLabel lbSoTienTuDiem;
    private javax.swing.JLabel lbSoTienTuDiem2;
    private javax.swing.JLabel lbTenKhachHang;
    private javax.swing.JLabel lbTienGiam;
    private javax.swing.JLabel lbTienGiam2;
    private javax.swing.JLabel lbTienThua;
    private javax.swing.JLabel lbTongNo;
    private javax.swing.JLabel lbTongTien;
    private javax.swing.JLabel lbTongTien2;
    private javax.swing.JLabel lbTraHangThang;
    private javax.swing.JLabel lbTraTruocToiThieu;
    private javax.swing.JMenuItem menuItem1;
    private javax.swing.JMenuItem menuItem2;
    private javax.swing.JRadioButton rdChuyenKhoan;
    private javax.swing.JRadioButton rdChuyenKhoan2;
    private javax.swing.JRadioButton rdTienMat;
    private javax.swing.JRadioButton rdTienMat2;
    private javax.swing.JTable tbDienThoai;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtImei;
    private javax.swing.JTextField txtLaiSuat;
    private javax.swing.JTextField txtMaGD;
    private javax.swing.JTextField txtMaGD2;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtSearchByTen;
    private javax.swing.JTextField txtThemImei;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienTraTruoc;
    private javax.swing.JTextField txtTimKH;
    // End of variables declaration//GEN-END:variables
}
