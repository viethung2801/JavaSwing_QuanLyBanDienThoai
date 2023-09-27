package view.Contains;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.CameraChiTiet;
import model.DienThoai;
import model.DongSanPham;
import model.Hang;
import model.HeDieuHanh;
import model.Imei;
import model.ManHinhChiTiet;
import model.MauSac;
import model.enums.LoaiManHinh;
import repository.DienThoaiRepository;
import repository.DongSanPhamRepository;
import repository.HangRepository;
import repository.HeDieuHanhRepository;
import repository.ImeiRepository;
import repository.MauSacRepository;
import service.DienThoaiService;
import service.DongSanPhamService;
import service.HangService;
import service.HeDieuHanhService;
import service.ImeiService;
import service.MauSacService;
import service.impl.DienThoaiServiceImpl;
import service.impl.DongSanPhamServiceImpl;
import service.impl.HangServiceImpl;
import service.impl.HeDieuHanhServiceImpl;
import service.impl.ImeiServiceImpl;
import service.impl.MauSacServiceImpl;
import view.Contains.EntitySanPham.ThemDongSP;
import view.Contains.EntitySanPham.ThemHang;
import view.Contains.EntitySanPham.ThemImei;
import view.Contains.EntitySanPham.ThemMauSac;
import view.Contains.EntitySanPham.ThemHeDieuHanh;
import viewmodel.DienThoaiResponse;
import viewmodel.ImeiResponse;

public class jplSanPham extends javax.swing.JPanel {

    private DefaultTableModel dtmActive;
    private DefaultTableModel dtmInactive;

    private DefaultComboBoxModel dcbmHang;
    private DefaultComboBoxModel dcbmDongSP;
    private DefaultComboBoxModel dcbmMauSac;
    private DefaultComboBoxModel dcbmHDH;

    private DefaultComboBoxModel dcbmRam;
    private DefaultComboBoxModel dcbmRom;
    private DefaultComboBoxModel dcbmLoaiMH;

    private HangService hangService;
    private DongSanPhamService dongSanPhamService;
    private MauSacService mauSacService;
    private HeDieuHanhService heDieuHanhService;
    private DienThoaiService dienThoaiService;

    private List<DienThoaiResponse> activedienThoaiResponseList;
    private List<DienThoaiResponse> inactivedienThoaiResponseList;

    private String imagePath = "";

    private static ImeiService imeiService;
    private static DefaultComboBoxModel dcbmImei;
    private static List<ImeiResponse> imeiResponseList;

    public jplSanPham() {
        initComponents();

        dtmActive = (DefaultTableModel) tbActive.getModel();
        dtmInactive = (DefaultTableModel) tbInactive.getModel();

        dcbmHang = (DefaultComboBoxModel) cbHang.getModel();
        dcbmDongSP = (DefaultComboBoxModel) cbDongSanPham.getModel();
        dcbmMauSac = (DefaultComboBoxModel) cbMauSac.getModel();
        dcbmHDH = (DefaultComboBoxModel) cbHeDieuHanh.getModel();

        dcbmRam = (DefaultComboBoxModel) cbRam.getModel();
        dcbmRom = (DefaultComboBoxModel) cbRom.getModel();
        dcbmLoaiMH = (DefaultComboBoxModel) cbLoaiManHinh.getModel();

        hangService = new HangServiceImpl();
        dongSanPhamService = new DongSanPhamServiceImpl();
        mauSacService = new MauSacServiceImpl();
        heDieuHanhService = new HeDieuHanhServiceImpl();
        dienThoaiService = new DienThoaiServiceImpl();

        activedienThoaiResponseList = new ArrayList<>();
        activedienThoaiResponseList = dienThoaiService.getAllResponseByStatus(true);

        inactivedienThoaiResponseList = new ArrayList<>();
        inactivedienThoaiResponseList = dienThoaiService.getAllResponseByStatus(false);

        dcbmImei = (DefaultComboBoxModel) cbImei.getModel();
        imeiService = new ImeiServiceImpl();
        imeiResponseList = new ArrayList<>();

        showActiveTable(activedienThoaiResponseList);
        showInactiveTable(inactivedienThoaiResponseList);
        getDataForComboBox();
        showComboBoxLoc();
    }

    // 1
    private void getDataForComboBox() {
        // Hãng
        cbHang.removeAllItems();
        List<Hang> hangList = hangService.getAllEntityByStatus(true);
        hangList.forEach(h -> cbHang.addItem(h));

        // Màu sắc
        cbMauSac.removeAllItems();
        List<MauSac> mauSacList = mauSacService.getAllEntityByStatus(true);
        mauSacList.forEach(ms -> cbMauSac.addItem(ms));

        // Hệ điều hành
        cbHeDieuHanh.removeAllItems();
        List<HeDieuHanh> heDieuHanhList = heDieuHanhService.getAllEntityByStatus(true);
        heDieuHanhList.forEach(hdh -> cbHeDieuHanh.addItem(hdh));

        // Ram
        List<String> ramList = List.of("4", "6", "8", "12", "16");
        ramList.forEach(r -> dcbmRam.addElement(r));

        // Rom
        List<String> romList = List.of("64", "128", "256", "512", "1024");
        romList.forEach(r -> dcbmRom.addElement(r));

        // Loại màn hình
        LoaiManHinh[] loaiManHinhArr = LoaiManHinh.values();
        for (LoaiManHinh lmh : loaiManHinhArr) {
            cbLoaiManHinh.addItem(lmh);
        }
    }

    // 2
    private void showActiveTable(List<DienThoaiResponse> dienThoaiResponses) {
        int tongSoLuong = 0;
        dtmActive.setRowCount(0);
        for (int i = 0; i < dienThoaiResponses.size(); ++i) {
            DienThoaiResponse dt = dienThoaiResponses.get(i);
            dtmActive.addRow(dt.toDataRow());
            tongSoLuong += dt.getSoLuong();
        }

        lbTongSLActive.setText("Tổng SL: " + tongSoLuong);
    }

    // 3
    private void showInactiveTable(List<DienThoaiResponse> dienThoaiResponses) {
        int tongSoLuong = 0;
        dtmInactive.setRowCount(0);
        for (int i = 0; i < dienThoaiResponses.size(); ++i) {
            DienThoaiResponse dt = dienThoaiResponses.get(i);
            dtmInactive.addRow(dt.toDataRow());
            tongSoLuong += dt.getSoLuong();
        }
        lbTongSLInactive.setText("Tổng SL: " + tongSoLuong);
    }

    // 4
    public static void showImeiComboBox(int idCurrentDienThoai) {
        if (idCurrentDienThoai == 0) {
            dcbmImei.removeAllElements();
            imeiResponseList = imeiService.getResponsesWithDienThoaiNull();
            imeiResponseList.forEach(i -> dcbmImei.addElement(i.getImei()));
        } else {
            dcbmImei.removeAllElements();
            imeiResponseList = imeiService.getResponsesByIdDienThoaiAndStatus(idCurrentDienThoai, 0);
            List<ImeiResponse> noneDienThoaiImeis = imeiService.getResponsesWithDienThoaiNull();
            imeiResponseList.addAll(noneDienThoaiImeis);
            imeiResponseList.forEach(i -> dcbmImei.addElement(i.getImei()));
        }
    }

    // Combo box
    // 5
    private void showComboBoxHang() {
        cbHang.removeAllItems();
        List<Hang> hangList = hangService.getAllEntityByStatus(true);
        hangList.forEach(h -> cbHang.addItem(h));
    }

    // 6
    private void showComboBoxDongSP() {
        Hang selectedHang = (Hang) cbHang.getSelectedItem();
        int hangId = selectedHang.getId();
        if (!selectedHang.isTrangThai()) {
            return;
        }

        List<DongSanPham> dongSanPhamList = dongSanPhamService.getAllEntityByHang(hangId);
        cbDongSanPham.removeAllItems();
        dongSanPhamList.forEach(dsp -> cbDongSanPham.addItem(dsp));
    }

    // 7
    private void showComboBoxMauSac() {
        cbMauSac.removeAllItems();
        List<MauSac> mauSacList = mauSacService.getAllEntityByStatus(true);
        mauSacList.forEach(ms -> cbMauSac.addItem(ms));
    }

    // 8
    private void showComboBoxHeDH() {
        cbHeDieuHanh.removeAllItems();
        List<HeDieuHanh> heDieuHanhList = heDieuHanhService.getAllEntityByStatus(true);
        heDieuHanhList.forEach(hdh -> cbHeDieuHanh.addItem(hdh));
    }

    // 9
    private void setDefaultImage() {
        imagePath = "";
        ImageIcon icon = new ImageIcon(getClass().getResource("/phoneimage/default-thumbnail.jpg"));
        Image newImage = icon.getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
        image.setIcon(new ImageIcon(newImage));
    }

    // 10. set data cho combobox lọc điện thoại
    private void showComboBoxLoc() {
        cbLocHang.removeAllItems();
        List<Hang> hangList = hangService.getAllEntityByStatus(true);
        cbLocHang.addItem(new Hang(0, "Tất cả"));
        hangList.forEach(h -> cbLocHang.addItem(h));

        cbLocMauSac.removeAllItems();
        List<MauSac> mauSacList = mauSacService.getAllEntityByStatus(true);
        cbLocMauSac.addItem(new MauSac(0, "Tất cả"));
        mauSacList.forEach(ms -> cbLocMauSac.addItem(ms));

        cbLocHeDH.removeAllItems();
        List<HeDieuHanh> heDieuHanhList = heDieuHanhService.getAllEntityByStatus(true);
        cbLocHeDH.addItem(new HeDieuHanh(0, "Tất cả"));
        heDieuHanhList.forEach(hdh -> cbLocHeDH.addItem(hdh));
    }

    // 11
    private void showComboBoxLocHeHD() {
        cbLocHeDH.removeAllItems();
        List<HeDieuHanh> heDieuHanhList = heDieuHanhService.getAllEntityByStatus(true);
        cbLocHeDH.addItem(new HeDieuHanh(0, "Tất cả"));
        heDieuHanhList.forEach(hdh -> cbLocHeDH.addItem(hdh));
    }

    // 12
    private void showComboBoxLocHang() {
        cbLocHang.removeAllItems();
        List<Hang> hangList = hangService.getAllEntityByStatus(true);
        cbLocHang.addItem(new Hang(0, "Tất cả"));
        hangList.forEach(h -> cbLocHang.addItem(h));
    }

    // 13
    private void showComboBoxLocMauSac() {
        cbLocMauSac.removeAllItems();
        List<MauSac> mauSacList = mauSacService.getAllEntityByStatus(true);
        cbLocMauSac.addItem(new MauSac(0, "Tất cả"));
        mauSacList.forEach(ms -> cbLocMauSac.addItem(ms));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        txtTimKiemTen = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbActive = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtSearchByTen = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnSapXepGiaTangDan = new javax.swing.JButton();
        btnSapXepGiaGiamDan = new javax.swing.JButton();
        cbLocMauSac = new javax.swing.JComboBox<>();
        cbLocHang = new javax.swing.JComboBox<>();
        cbLocHeDH = new javax.swing.JComboBox<>();
        lbTongSLActive = new javax.swing.JLabel();
        btnLoc = new javax.swing.JButton();
        btnXoaLoc = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbInactive = new javax.swing.JTable();
        btnKhoiPhuc = new javax.swing.JButton();
        lbTongSLInactive = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaSanPham = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtGiaNhap = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        cbImei = new javax.swing.JComboBox<>();
        btnLamMoi = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        btnThemImei = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        cbHang = new javax.swing.JComboBox<>();
        btnHang = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        cbDongSanPham = new javax.swing.JComboBox<>();
        btnDongSanPham = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        cbMauSac = new javax.swing.JComboBox<>();
        btnMauSac = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        cbHeDieuHanh = new javax.swing.JComboBox<>();
        btnHeDieuHanh = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cbRam = new javax.swing.JComboBox<>();
        cbRom = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtPin = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtCpu = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtCamChinh = new javax.swing.JTextField();
        txtCamPhu = new javax.swing.JTextField();
        txtCamGocRong = new javax.swing.JTextField();
        txtCamTele = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtKichThuoc = new javax.swing.JTextField();
        txtDoPG = new javax.swing.JTextField();
        cbLoaiManHinh = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
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

        txtTimKiemTen.setBackground(new java.awt.Color(255, 255, 255));

        tbActive.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TÊN ĐT", "HÃNG", "RAM", "ROM", "PIN", "SỐ LƯỢNG", "GIÁ BÁN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbActive.setFocusable(false);
        tbActive.setGridColor(new java.awt.Color(47, 85, 212));
        tbActive.setRowHeight(25);
        tbActive.setShowGrid(true);
        tbActive.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbActiveMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbActiveMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tbActive);

        jLabel9.setText("TÌM KIẾM THEO TÊN:");

        txtSearchByTen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(47, 85, 212)));
        txtSearchByTen.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchByTenCaretUpdate(evt);
            }
        });

        jLabel10.setText("SẮP XẾP THEO GIÁ:");

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

        cbLocMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new MauSac[]{}));
        cbLocMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbLocMauSacMouseClicked(evt);
            }
        });

        cbLocHang.setModel(new javax.swing.DefaultComboBoxModel<>(new Hang[]{}));
        cbLocHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbLocHangMouseClicked(evt);
            }
        });

        cbLocHeDH.setModel(new javax.swing.DefaultComboBoxModel<>(new HeDieuHanh[]{}));
        cbLocHeDH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbLocHeDHMouseClicked(evt);
            }
        });

        lbTongSLActive.setText("Tổng SL: ...");

        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        btnXoaLoc.setText("Xóa lọc");
        btnXoaLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaLocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout txtTimKiemTenLayout = new javax.swing.GroupLayout(txtTimKiemTen);
        txtTimKiemTen.setLayout(txtTimKiemTenLayout);
        txtTimKiemTenLayout.setHorizontalGroup(
            txtTimKiemTenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtTimKiemTenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(txtTimKiemTenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtTimKiemTenLayout.createSequentialGroup()
                        .addGroup(txtTimKiemTenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(txtSearchByTen, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(txtTimKiemTenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(txtTimKiemTenLayout.createSequentialGroup()
                                .addComponent(btnSapXepGiaTangDan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSapXepGiaGiamDan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(104, 104, 104))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtTimKiemTenLayout.createSequentialGroup()
                        .addGroup(txtTimKiemTenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(txtTimKiemTenLayout.createSequentialGroup()
                                .addComponent(cbLocHang, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbLocMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbLocHeDH, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(btnLoc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnXoaLoc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbTongSLActive, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE))
                        .addGap(11, 11, 11))))
        );
        txtTimKiemTenLayout.setVerticalGroup(
            txtTimKiemTenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtTimKiemTenLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(txtTimKiemTenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(txtTimKiemTenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnSapXepGiaTangDan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSearchByTen, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSapXepGiaGiamDan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(txtTimKiemTenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbLocHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbLocMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbLocHeDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTongSLActive)
                    .addComponent(btnXoaLoc)
                    .addComponent(btnLoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Đang hoạt động", txtTimKiemTen);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tbInactive.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TÊN", "HÃNG", "RAM", "ROM", "PIN", "SỐ LƯỢNG", "GIÁ BÁN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbInactive.setFocusable(false);
        tbInactive.setGridColor(new java.awt.Color(47, 85, 212));
        tbInactive.setRowHeight(25);
        tbInactive.setShowGrid(true);
        tbInactive.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbInactiveMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbInactive);
        if (tbInactive.getColumnModel().getColumnCount() > 0) {
            tbInactive.getColumnModel().getColumn(0).setMinWidth(100);
            tbInactive.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbInactive.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        btnKhoiPhuc.setBackground(new java.awt.Color(47, 85, 212));
        btnKhoiPhuc.setForeground(new java.awt.Color(255, 255, 255));
        btnKhoiPhuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-available-updates-20 (1).png"))); // NOI18N
        btnKhoiPhuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiPhucActionPerformed(evt);
            }
        });

        lbTongSLInactive.setText("Tổng SL: ...");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbTongSLInactive, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnKhoiPhuc, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnKhoiPhuc, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTongSLInactive))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Không hoạt động", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        image.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        image.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imageMouseClicked(evt);
            }
        });

        jLabel2.setText("MÃ ĐIỆN THOẠI:");

        txtMaSanPham.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(47, 85, 212)));

        jLabel3.setText("TÊN ĐIỆN THOẠI:");

        txtTenSanPham.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(47, 85, 212)));

        jLabel4.setText("GIÁ NHẬP:");

        txtGiaNhap.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(47, 85, 212)));

        jLabel5.setText("GIÁ BÁN:");

        txtGiaBan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(47, 85, 212)));

        jLabel6.setText("IMEI:");

        btnThem.setBackground(new java.awt.Color(47, 85, 212));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-add-new-20.png"))); // NOI18N
        btnThem.setText("THÊM");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(47, 85, 212));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-pencil-20 WHITE.png"))); // NOI18N
        btnSua.setText("SỬA");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(47, 85, 212));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-trash-20.png"))); // NOI18N
        btnXoa.setText("XÓA");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(47, 85, 212));
        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-new-20.png"))); // NOI18N
        btnLamMoi.setText("MỚI");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        jLabel7.setText("GHI CHÚ:");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane3.setViewportView(txtMoTa);

        btnThemImei.setBackground(new java.awt.Color(47, 85, 212));
        btnThemImei.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-add-new-20.png"))); // NOI18N
        btnThemImei.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemImeiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLamMoi))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThem)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel6)
                                .addComponent(jLabel5)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2)
                                .addComponent(txtMaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                                .addComponent(txtTenSanPham)
                                .addComponent(txtGiaNhap)
                                .addComponent(txtGiaBan))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cbImei, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnThemImei, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbImei, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemImei, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("LINH KIỆN");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("HÃNG"));

        cbHang.setModel(new javax.swing.DefaultComboBoxModel<>(new Hang[]{}));
        cbHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbHangMouseClicked(evt);
            }
        });
        cbHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbHangActionPerformed(evt);
            }
        });

        btnHang.setBackground(new java.awt.Color(47, 85, 212));
        btnHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-pencil-20 WHITE.png"))); // NOI18N
        btnHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHang, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("DÒNG SP"));

        cbDongSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new DongSanPham[]{}));
        cbDongSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbDongSanPhamMouseClicked(evt);
            }
        });

        btnDongSanPham.setBackground(new java.awt.Color(47, 85, 212));
        btnDongSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-pencil-20 WHITE.png"))); // NOI18N
        btnDongSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongSanPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbDongSanPham, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDongSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbDongSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDongSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("MÀU SẮC"));

        cbMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new MauSac[]{}));
        cbMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbMauSacMouseClicked(evt);
            }
        });
        cbMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMauSacActionPerformed(evt);
            }
        });

        btnMauSac.setBackground(new java.awt.Color(47, 85, 212));
        btnMauSac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-pencil-20 WHITE.png"))); // NOI18N
        btnMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMauSacActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbMauSac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMauSac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("HỆ ĐIỀU HÀNH"));

        cbHeDieuHanh.setModel(new javax.swing.DefaultComboBoxModel<>(new HeDieuHanh[]{}));
        cbHeDieuHanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbHeDieuHanhMouseClicked(evt);
            }
        });

        btnHeDieuHanh.setBackground(new java.awt.Color(47, 85, 212));
        btnHeDieuHanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-pencil-20 WHITE.png"))); // NOI18N
        btnHeDieuHanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHeDieuHanhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnHeDieuHanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbHeDieuHanh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(cbHeDieuHanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHeDieuHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setText("RAM:");

        jLabel13.setText("ROM:");

        jLabel27.setText("(GB)");

        jLabel28.setText("(GB)");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(cbRam, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(cbRom, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cbRam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(cbRom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel28))
                .addGap(14, 14, 14))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setText("PIN:");

        jLabel15.setText("CPU:");

        jLabel29.setText("(mAh)");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPin))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(8, 8, 8)
                        .addComponent(txtCpu)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29)
                .addGap(9, 9, 9))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtPin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtCpu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("CAMERA"));

        jLabel16.setText("CHÍNH:");

        jLabel17.setText("PHỤ:");

        jLabel18.setText("GÓC RỘNG:");

        jLabel19.setText("TELE:");

        jLabel1.setText("(MP)");

        jLabel8.setText("(MP)");

        jLabel23.setText("(MP)");

        jLabel24.setText("(MP)");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel19)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCamChinh)
                    .addComponent(txtCamPhu)
                    .addComponent(txtCamGocRong)
                    .addComponent(txtCamTele))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel8)
                        .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap())))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel16))
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCamChinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addGap(10, 10, 10)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel17))
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCamPhu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addGap(10, 10, 10)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel18))
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCamGocRong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel23)))
                .addGap(10, 10, 10)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel19))
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCamTele, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel24)))
                .addGap(19, 19, 19))
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("MÀN HÌNH"));

        jLabel20.setText("KÍCH THƯỚC:");

        jLabel21.setText("ĐỘ PHÂN GIẢI:");

        jLabel22.setText("LOẠI MÀN HÌNH:");

        txtDoPG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDoPGActionPerformed(evt);
            }
        });

        jLabel25.setText("(inches)");

        jLabel26.setText("(px)");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel20))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtKichThuoc)
                            .addComponent(txtDoPG))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbLoaiManHinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(51, 51, 51))))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtKichThuoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(jLabel20)))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel21)
                        .addGap(17, 17, 17)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(cbLoaiManHinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDoPG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(40, 40, 40)
                                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(29, 29, 29))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Đang hoạt động");
    }// </editor-fold>//GEN-END:initComponents

    private void btnHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHangActionPerformed
        new ThemHang().setVisible(true);
    }//GEN-LAST:event_btnHangActionPerformed

    private void btnMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMauSacActionPerformed
        new ThemMauSac().setVisible(true);
    }//GEN-LAST:event_btnMauSacActionPerformed

    private void btnDongSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongSanPhamActionPerformed
        new ThemDongSP().setVisible(true);
    }//GEN-LAST:event_btnDongSanPhamActionPerformed

    private void btnHeDieuHanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHeDieuHanhActionPerformed
        new ThemHeDieuHanh().setVisible(true);
    }//GEN-LAST:event_btnHeDieuHanhActionPerformed

    private void tbActiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbActiveMouseClicked
        try {
            int clickedRow = tbActive.getSelectedRow();
            if (clickedRow < 0) {
                return;
            }
            DienThoaiResponse dienThoaiResponse = activedienThoaiResponseList.get(clickedRow);

            imagePath = dienThoaiResponse.getHinhAnh();
            txtMaSanPham.setText(dienThoaiResponse.getMaDT());
            txtTenSanPham.setText(dienThoaiResponse.getTenDT());
            txtGiaNhap.setText(String.valueOf(dienThoaiResponse.getGiaNhap()));
            txtGiaBan.setText(String.valueOf(dienThoaiResponse.getGiaBan()));
            txtMoTa.setText(dienThoaiResponse.getMoTa());

            dcbmRam.setSelectedItem(dienThoaiResponse.getRam());
            dcbmRom.setSelectedItem(dienThoaiResponse.getRom());

            txtPin.setText(String.valueOf(dienThoaiResponse.getDungLuongPin()));
            txtCpu.setText(dienThoaiResponse.getCpu());

            if (dienThoaiResponse.getCameraChinh() > 0) {
                txtCamChinh.setText(String.valueOf(dienThoaiResponse.getCameraChinh()));
            } else {
                txtCamChinh.setText("không có");
            }

            if (dienThoaiResponse.getCameraPhu() > 0) {
                txtCamPhu.setText(String.valueOf(dienThoaiResponse.getCameraPhu()));
            } else {
                txtCamPhu.setText("không có");
            }

            if (dienThoaiResponse.getCameraGocRong() > 0) {
                txtCamGocRong.setText(String.valueOf(dienThoaiResponse.getCameraGocRong()));
            } else {
                txtCamGocRong.setText("không có");
            }

            if (dienThoaiResponse.getCameraTele() > 0) {
                txtCamTele.setText(String.valueOf(dienThoaiResponse.getCameraTele()));
            } else {
                txtCamTele.setText("không có");
            }

            txtKichThuoc.setText(String.valueOf(dienThoaiResponse.getKichThuoc()));
            txtDoPG.setText(dienThoaiResponse.getDoPhanGiai());
            dcbmLoaiMH.setSelectedItem(dienThoaiResponse.getLoaiManHinh());

            String tenHang = dienThoaiResponse.getHang();
            Hang hang = HangRepository.getByTenHang(tenHang);
            dcbmHang.setSelectedItem(hang);

            String tenDongSP = dienThoaiResponse.getDongSanPham();
            DongSanPham dongSP = DongSanPhamRepository.getByTenDongSP(tenDongSP);
            dcbmDongSP.setSelectedItem(dongSP);

            String tenMS = dienThoaiResponse.getMauSac();
            MauSac mauSac = MauSacRepository.getByTen(tenMS);
            dcbmMauSac.setSelectedItem(mauSac);

            String tenHeDH = dienThoaiResponse.getHeDieuHanh();
            HeDieuHanh hdh = HeDieuHanhRepository.getByTen(tenHeDH);
            dcbmHDH.setSelectedItem(hdh);

            // imeis
            int dienThoaiId = dienThoaiResponse.getId();
            List<ImeiResponse> imeiSet = ImeiRepository.getResponsesByIdDienThoaiAndStatus(dienThoaiId, 0);

            dcbmImei.removeAllElements();
            imeiSet.forEach(i -> cbImei.addItem(i.getImei()));

            // hình ảnh
            ImageIcon icon = new ImageIcon(getClass().getResource("/phoneimage/" + dienThoaiResponse.getHinhAnh()));
            Image newImage = icon.getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
            image.setIcon(new ImageIcon(newImage));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_tbActiveMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        lamMoiForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void lamMoiForm() {
        setDefaultImage();
        txtMaSanPham.setText("");
        txtTenSanPham.setText("");
        txtGiaNhap.setText("");
        txtGiaBan.setText("");
        txtMoTa.setText("");
        dcbmImei.removeAllElements();

        cbHang.setSelectedIndex(0);
        showComboBoxDongSP();
        cbMauSac.setSelectedIndex(0);
        cbHeDieuHanh.setSelectedIndex(0);

        cbRam.setSelectedIndex(0);
        cbRom.setSelectedIndex(0);

        txtPin.setText("");
        txtCpu.setText("");

        txtCamChinh.setText("");
        txtCamPhu.setText("");
        txtCamGocRong.setText("");
        txtCamTele.setText("");

        txtKichThuoc.setText("");
        txtDoPG.setText("");
        cbLoaiManHinh.setSelectedIndex(0);

        activedienThoaiResponseList = dienThoaiService.getAllResponseByStatus(true);
        showActiveTable(activedienThoaiResponseList);

        inactivedienThoaiResponseList = dienThoaiService.getAllResponseByStatus(false);
        showInactiveTable(inactivedienThoaiResponseList);

        imeiService.deleteImeiWithDienThoaiNull();
    }

    private String checkInput(int id) {
        String message = "";

        // hình ảnh
        if (imagePath.isBlank()) {
            message += "Hình ảnh điện thoại không được để trống\n";
        }

        // mã điện thoại
        String maDT = txtMaSanPham.getText().trim();
        if (maDT.isBlank()) {
            message += "Mã điện thoại không được để trống\n";
        } else {

            // Check unique maDienThoai
            DienThoai dienThoai = dienThoaiService.getByMaDT(maDT);
            if (dienThoai != null) {
                if (id == 0) {
                    message += "Mã điện thoại đã bị trùng";
                    if (!dienThoai.isTrangThai()) {
                        message += " (trong mục đã xóa)";
                    } else {
                        message += "\n";
                    }
                } else if (id > 0) {
                    if (dienThoai.getId() != id) {
                        message += "Mã điện thoại đã bị trùng";
                        if (!dienThoai.isTrangThai()) {
                            message += " (trong mục đã xóa)";
                        } else {
                            message += "\n";
                        }
                    }
                }
            }

            // Check pattern
            String pattern = "[a-zA-Z0-9]{1,30}";
            if (!maDT.matches(pattern)) {
                message += "Mã điện thoại không đúng định dạng!\n";
            }
        }

        // tên điện thoại
        String tenDT = txtTenSanPham.getText().trim();
        if (tenDT.isBlank()) {
            message += "Tên điện thoại không được để trống\n";
        } else {
            String pattern = "[a-zA-Z0-9 ]{1,128}";
            if (tenDT.length() > 128) {
                message += "Tên điện thoại không đúng định dạng\n";
            }
        }

        // giá nhập
        String giaNhap = txtGiaNhap.getText().trim();
        if (giaNhap.isBlank()) {
            message += "Giá nhập không được để trống\n";
        } else {
            String pattern = "[1-9][0-9]*";
            if (!giaNhap.matches(pattern)) {
                message += "Giá nhập không đúng định dạng\n";
            }
        }

        // giá bán
        String giaBan = txtGiaBan.getText().trim();
        if (giaBan.isBlank()) {
            message += "Giá bán không được để trống\n";
        } else {
            String pattern = "[1-9][0-9]*";
            if (!giaBan.matches(pattern)) {
                message += "Giá bán không đúng định dạng\n";
            }
        }

        // pin
        String pin = txtPin.getText().trim();
        if (pin.isBlank()) {
            message += "Dung lượng Pin không được để trống\n";
        } else {
            String pattern = "[1-9][0-9]*";
            if (!pin.matches(pattern)) {
                message += "Dung lượng Pin không đúng định dạng\n";
            }
        }

        // cpu
        String cpu = txtCpu.getText().trim();
        if (cpu.isBlank()) {
            message += "CPU không được để trống\n";
        } else {
            String pattern = "[a-zA-Z0-9() ]{1,64}";
            if (!cpu.matches(pattern)) {
                message += "CPU không đúng định dạng\n";
            }
        }

        // camera chính
        String camChinh = txtCamChinh.getText().trim();
        if (camChinh.equals("không có")) {
            camChinh = "";
        }
        if (!camChinh.isBlank()) {
            String pattern = "[1-9][0-9]*";
            if (!camChinh.matches(pattern)) {
                message += "Camera chính không đúng định dạng\n";
            }
        }

        // camera phụ
        String camPhu = txtCamPhu.getText().trim();
        if (camPhu.equals("không có")) {
            camPhu = "";
        }
        if (!camPhu.isBlank()) {
            String pattern = "[1-9][0-9]*";
            if (!camPhu.matches(pattern)) {
                message += "Camera phụ không đúng định dạng\n";
            }
        }

        // camera góc rộng
        String camGocRong = txtCamGocRong.getText().trim();
        if (camGocRong.equals("không có")) {
            camGocRong = "";
        }
        if (!camGocRong.isBlank()) {
            String pattern = "[1-9][0-9]*";
            if (!camGocRong.matches(pattern)) {
                message += "Camera góc rộng không đúng định dạng\n";
            }
        }

        // camera tele
        String camTele = txtCamTele.getText().trim();
        if (camTele.equals("không có")) {
            camTele = "";
        }
        if (!camTele.isBlank()) {
            String pattern = "[1-9][0-9]*";
            if (!camTele.matches(pattern)) {
                message += "Camera tele không đúng định dạng\n";
            }
        }

        // kích thước
        String kichThuoc = txtKichThuoc.getText().trim();
        if (kichThuoc.isBlank()) {
            message += "Kích thước màn hình không được để trống\n";
        } else {
            String pattern = "[1-9][0-9]*[ ]*[.][ ]*[1-9][0-9]*";
            if (!kichThuoc.matches(pattern)) {
                message += "Kích thước màn hình không đúng định dạng\n";
            }
        }

        // độ phân giải
        String doPhanGiai = txtDoPG.getText().trim();
        if (doPhanGiai.isBlank()) {
            message += "Độ phân giải màn hình không được để trống\n";
        } else {
            String pattern = "[1-9][0-9]*[ ]*[x][ ]*[1-9][0-9]*";
            if (!doPhanGiai.matches(pattern)) {
                message += "Độ phân giải màn hình không đúng định dạng\n";
            }
        }
        return message;
    }

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Thêm điện thoại?", "Xác nhận thêm điện thoại", JOptionPane.YES_NO_OPTION);
        if (confirm != 0) {
            return;
        }

        String checkResult = checkInput(0);
        if (!checkResult.equals("")) {
            JOptionPane.showMessageDialog(this, checkResult);
            return;
        }

        // Camera
        CameraChiTiet cam = new CameraChiTiet();
        String camChinh = txtCamChinh.getText().trim();
        String camPhu = txtCamPhu.getText().trim();
        String camTele = txtCamTele.getText().trim();
        String camGocRong = txtCamGocRong.getText().trim();

        if (camChinh.equals("không có")) {
            camChinh = "";
        }
        if (camPhu.equals("không có")) {
            camPhu = "";
        }
        if (camTele.equals("không có")) {
            camTele = "";
        }
        if (camGocRong.equals("không có")) {
            camGocRong = "";
        }

        if (!camChinh.isBlank()) {
            cam.setCameraChinh(Integer.valueOf(camChinh));
        } else {
            cam.setCameraChinh(0);
        }
        if (!camPhu.isBlank()) {
            cam.setCameraPhu(Integer.valueOf(camPhu));
        } else {
            cam.setCameraPhu(0);
        }
        if (!camTele.isBlank()) {
            cam.setCameraTele(Integer.valueOf(camTele));
        } else {
            cam.setCameraTele(0);
        }
        if (!camGocRong.isBlank()) {
            cam.setCameraGocRong(Integer.valueOf(camGocRong));
        } else {
            cam.setCameraGocRong(0);
        }

        // Màn hình
        ManHinhChiTiet man = new ManHinhChiTiet();
        man.setKichThuoc(Float.valueOf(txtKichThuoc.getText().trim()));
        man.setDoPhanGiai(txtDoPG.getText().trim());
        man.setLoaiManHinh((LoaiManHinh) cbLoaiManHinh.getSelectedItem());

        // Điện thoại
        DienThoai dienThoai = new DienThoai();

        dienThoai.setHinhAnh(imagePath);
        dienThoai.setMaDT(txtMaSanPham.getText().trim());
        dienThoai.setTenDT(txtTenSanPham.getText().trim());
        dienThoai.setMoTa(txtMoTa.getText().trim());
        dienThoai.setDungLuongPin(Integer.valueOf(txtPin.getText().trim()));
        dienThoai.setRam(Integer.valueOf(String.valueOf(dcbmRam.getSelectedItem())));
        dienThoai.setRom(Integer.valueOf(String.valueOf(dcbmRom.getSelectedItem())));
        dienThoai.setCpu(txtCpu.getText().trim());
        dienThoai.setGiaNhap(Long.valueOf(txtGiaNhap.getText().trim()));
        dienThoai.setGiaBan(Long.valueOf(txtGiaBan.getText().trim()));
        dienThoai.setTrangThai(true);

        Hang hang = (Hang) dcbmHang.getSelectedItem();
        DongSanPham dsp = (DongSanPham) dcbmDongSP.getSelectedItem();
        MauSac mauSac = (MauSac) dcbmMauSac.getSelectedItem();
        HeDieuHanh hdh = (HeDieuHanh) dcbmHDH.getSelectedItem();

        dienThoai.setHang(hang);
        dienThoai.setDongSanPham(dsp);
        dienThoai.setMauSac(mauSac);
        dienThoai.setHeDieuHanh(hdh);

        dienThoai.setCameraChiTiet(cam);
        dienThoai.setManHinhChiTiet(man);

        // xử lý imei
        for (ImeiResponse imeiResponse : jplSanPham.imeiResponseList) {
            Imei imei = new Imei(imeiResponse.getImei());
            dienThoai.addImei(imei);
        }
        dienThoai.setSoLuong(jplSanPham.imeiResponseList.size());

        String addResult = dienThoaiService.add(dienThoai);
        JOptionPane.showMessageDialog(this, addResult);

        // update idDienThoai cua Imei (phải add dienThoai để có ID trước)
        DienThoai dtByMa = dienThoaiService.getByMaDT(dienThoai.getMaDT());
        for (ImeiResponse imeiResponse : jplSanPham.imeiResponseList) {
            imeiResponse.setIdDienThoai(dtByMa.getId());
            imeiService.update(imeiResponse);
        }

        // after add
        lamMoiForm();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Sửa điện thoại?", "Xác nhận sửa điện thoại", JOptionPane.YES_NO_OPTION);
        if (confirm != 0) {
            return;
        }

        int clickedRow = tbActive.getSelectedRow();
        if (clickedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn điện thoại trước khi sửa!");
            return;
        }

        DienThoaiResponse selectedDienThoai = activedienThoaiResponseList.get(clickedRow);

        String checkResult = checkInput(selectedDienThoai.getId());
        if (!checkResult.equals("")) {
            JOptionPane.showMessageDialog(this, checkResult);
            return;
        }

        // xử lý imei mới
        List<ImeiResponse> imeisWithDienThoaiNull = imeiService.getResponsesWithDienThoaiNull();
        for (ImeiResponse imeiResponse : imeisWithDienThoaiNull) {
            imeiResponse.setIdDienThoai(selectedDienThoai.getId());
            imeiService.update(imeiResponse);
        }
        // đồng thời update số lượng của điện thoại tương ứng với số imei
        List<ImeiResponse> imeisWithSpecificDienThoaiId = imeiService.getResponsesByIdDienThoaiAndStatus(selectedDienThoai.getId(), 0);
        selectedDienThoai.setSoLuong(imeisWithSpecificDienThoaiId.size());

        if (!imagePath.equals("")) {
            selectedDienThoai.setHinhAnh(imagePath);
        }
        selectedDienThoai.setMaDT(txtMaSanPham.getText().trim());
        selectedDienThoai.setTenDT(txtTenSanPham.getText().trim());
        selectedDienThoai.setMoTa(txtMoTa.getText().trim());
        selectedDienThoai.setDungLuongPin(Integer.valueOf(txtPin.getText().trim()));
        selectedDienThoai.setRam(Integer.valueOf(String.valueOf(dcbmRam.getSelectedItem())));
        selectedDienThoai.setRom(Integer.valueOf(String.valueOf(dcbmRom.getSelectedItem())));
        selectedDienThoai.setCpu(txtCpu.getText().trim());
        selectedDienThoai.setGiaNhap(Long.valueOf(txtGiaNhap.getText().trim()));
        selectedDienThoai.setGiaBan(Long.valueOf(txtGiaBan.getText().trim()));

        Hang hang = (Hang) dcbmHang.getSelectedItem();
        DongSanPham dsp = (DongSanPham) dcbmDongSP.getSelectedItem();
        MauSac mauSac = (MauSac) dcbmMauSac.getSelectedItem();
        HeDieuHanh hdh = (HeDieuHanh) dcbmHDH.getSelectedItem();

        selectedDienThoai.setHang(hang.getTenHang());
        selectedDienThoai.setDongSanPham(dsp.getTen());
        selectedDienThoai.setMauSac(mauSac.getTenMauSac());
        selectedDienThoai.setHeDieuHanh(hdh.getTen());

        // Camera
        String camChinh = txtCamChinh.getText().trim();
        String camPhu = txtCamPhu.getText().trim();
        String camTele = txtCamTele.getText().trim();
        String camGocRong = txtCamGocRong.getText().trim();

        if (!camChinh.isBlank()) {
            if (camChinh.equals("không có")) {
                camChinh = "0";
            }
            selectedDienThoai.setCameraChinh(Integer.valueOf(camChinh));
        }
        if (!camPhu.isBlank()) {
            if (camPhu.equals("không có")) {
                camPhu = "0";
            }
            selectedDienThoai.setCameraPhu(Integer.valueOf(camPhu));
        }
        if (!camTele.isBlank()) {
            if (camTele.equals("không có")) {
                camTele = "0";
            }
            selectedDienThoai.setCameraTele(Integer.valueOf(camTele));
        }
        if (!camGocRong.isBlank()) {
            if (camGocRong.equals("không có")) {
                camGocRong = "0";
            }
            selectedDienThoai.setCameraGocRong(Integer.valueOf(camGocRong));
        }

        // Màn hình
        selectedDienThoai.setKichThuoc(Float.valueOf(txtKichThuoc.getText().trim()));
        selectedDienThoai.setDoPhanGiai(txtDoPG.getText().trim());
        selectedDienThoai.setLoaiManHinh((LoaiManHinh) cbLoaiManHinh.getSelectedItem());

        String updateResult = dienThoaiService.update(selectedDienThoai);
        JOptionPane.showMessageDialog(this, updateResult);

        // after update
        lamMoiForm();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnKhoiPhucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiPhucActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Khôi phục điện thoại?", "Xác nhận khôi phục điện thoại", JOptionPane.YES_NO_OPTION);
        if (confirm != 0) {
            return;
        }

        int clickedRow = tbInactive.getSelectedRow();
        if (clickedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn điện thoại trước khi khôi phục!");
            return;
        }

        DienThoaiResponse selectedDTResponse = inactivedienThoaiResponseList.get(clickedRow);

        String result = dienThoaiService.changeStatus(selectedDTResponse, true);
        JOptionPane.showMessageDialog(this, result);

        // reset table
        lamMoiForm();
    }//GEN-LAST:event_btnKhoiPhucActionPerformed

    private void tbInactiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbInactiveMouseClicked
        int clickedRow = tbInactive.getSelectedRow();
        if (clickedRow < 0) {
            return;
        }
        DienThoaiResponse dienThoaiResponse = inactivedienThoaiResponseList.get(clickedRow);

        txtMaSanPham.setText(dienThoaiResponse.getMaDT());
        txtTenSanPham.setText(dienThoaiResponse.getTenDT());
        txtGiaNhap.setText(String.valueOf(dienThoaiResponse.getGiaNhap()));
        txtGiaBan.setText(String.valueOf(dienThoaiResponse.getGiaBan()));

        dcbmRam.setSelectedItem(dienThoaiResponse.getRam());
        dcbmRom.setSelectedItem(dienThoaiResponse.getRom());

        txtPin.setText(String.valueOf(dienThoaiResponse.getDungLuongPin()));
        txtCpu.setText(dienThoaiResponse.getCpu());

        txtCamChinh.setText(String.valueOf(dienThoaiResponse.getCameraChinh()));
        txtCamPhu.setText(String.valueOf(dienThoaiResponse.getCameraPhu()));
        txtCamGocRong.setText(String.valueOf(dienThoaiResponse.getCameraGocRong()));
        txtCamTele.setText(String.valueOf(dienThoaiResponse.getCameraTele()));

        txtKichThuoc.setText(String.valueOf(dienThoaiResponse.getKichThuoc()));
        txtDoPG.setText(dienThoaiResponse.getDoPhanGiai());
        dcbmLoaiMH.setSelectedItem(dienThoaiResponse.getLoaiManHinh());

        String tenHang = dienThoaiResponse.getHang();
        Hang hang = HangRepository.getByTenHang(tenHang);
        dcbmHang.setSelectedItem(hang);

        String tenDongSP = dienThoaiResponse.getDongSanPham();
        DongSanPham dongSP = DongSanPhamRepository.getByTenDongSP(tenDongSP);
        dcbmDongSP.setSelectedItem(dongSP);

        String tenMS = dienThoaiResponse.getMauSac();
        MauSac mauSac = MauSacRepository.getByTen(tenMS);
        dcbmMauSac.setSelectedItem(mauSac);

        String tenHeDH = dienThoaiResponse.getHeDieuHanh();
        HeDieuHanh hdh = HeDieuHanhRepository.getByTen(tenHeDH);
        dcbmHDH.setSelectedItem(hdh);

        // imei
        int dienThoaiId = dienThoaiResponse.getId();
        List<ImeiResponse> imeiSet = ImeiRepository.getResponsesByIdDienThoaiAndStatus(dienThoaiId, 0);

        dcbmImei.removeAllElements();
        imeiSet.forEach(i -> cbImei.addItem(i.getImei()));

        ImageIcon icon = new ImageIcon(getClass().getResource("/phoneimage/" + dienThoaiResponse.getHinhAnh()));
        Image newImage = icon.getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
        image.setIcon(new ImageIcon(newImage));
    }//GEN-LAST:event_tbInactiveMouseClicked

    private void btnSapXepGiaTangDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSapXepGiaTangDanActionPerformed
        activedienThoaiResponseList = dienThoaiService.getAllResponseByGiaBan("ASC");
        showActiveTable(activedienThoaiResponseList);
    }//GEN-LAST:event_btnSapXepGiaTangDanActionPerformed

    private void btnSapXepGiaGiamDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSapXepGiaGiamDanActionPerformed
        activedienThoaiResponseList = dienThoaiService.getAllResponseByGiaBan("DESC");
        showActiveTable(activedienThoaiResponseList);
    }//GEN-LAST:event_btnSapXepGiaGiamDanActionPerformed

    private void txtSearchByTenCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchByTenCaretUpdate
        String keyword = txtSearchByTen.getText().trim();
        if (keyword.equals("")) {
            activedienThoaiResponseList = dienThoaiService.getAllResponseByStatus(true);
            showActiveTable(activedienThoaiResponseList);
            return;
        }
        activedienThoaiResponseList = dienThoaiService.searchAllResponseByName(keyword);
        showActiveTable(activedienThoaiResponseList);
    }//GEN-LAST:event_txtSearchByTenCaretUpdate

    private void imageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageMouseClicked
        String path = "C:/Users/T490/OneDrive - Hanoi University of Science and Technology/Documents/NetBeansProjects/Duan1-V2/DuAn1-version2/src/main/resources/phoneimage";
        JFileChooser chooser = new JFileChooser(path);
        FileFilter filter = new FileNameExtensionFilter("Tệp JPG", "jpg");
        chooser.setFileFilter(filter);
        int result = chooser.showOpenDialog(null);

//        JFileChooser chooser = new JFileChooser();
//        chooser.setCurrentDirectory(new java.io.File("./src/main/resources"));
//        chooser.setDialogTitle("Chọn thư mục resources");
//        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        chooser.setAcceptAllFileFilterUsed(false);
        if (result == JFileChooser.APPROVE_OPTION) {
//        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            imagePath = selectedFile.getName();

            // Hiển thị ảnh lên khung
            ImageIcon icon = new ImageIcon(selectedFile.getPath());
            Image newImage = icon.getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
            image.setIcon(new ImageIcon(newImage));
        }
    }//GEN-LAST:event_imageMouseClicked

    private void tbActiveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbActiveMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbActiveMouseEntered

    private void cbHeDieuHanhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbHeDieuHanhMouseClicked
        showComboBoxHeDH();
    }//GEN-LAST:event_cbHeDieuHanhMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Xóa điện thoại?", "Xác nhận xóa điện thoại", JOptionPane.YES_NO_OPTION);
        if (confirm != 0) {
            return;
        }

        int clickedRow = tbActive.getSelectedRow();
        if (clickedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn điện thoại trước khi xóa!");
            return;
        }
        DienThoaiResponse selectedDTResponse = activedienThoaiResponseList.get(clickedRow);
        String deleteResult = dienThoaiService.changeStatus(selectedDTResponse, false);
        JOptionPane.showMessageDialog(this, deleteResult);

        lamMoiForm();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void txtDoPGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDoPGActionPerformed

    }//GEN-LAST:event_txtDoPGActionPerformed

    private void btnThemImeiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemImeiActionPerformed
        int clickedActiveRow = tbActive.getSelectedRow();
        if (clickedActiveRow != -1) {
            DienThoaiResponse dienThoaiResponse = activedienThoaiResponseList.get(clickedActiveRow);
            int dienThoaiId = dienThoaiResponse.getId();
            new ThemImei(dienThoaiId).setVisible(true);
        } else {
            new ThemImei().setVisible(true);
        }
    }//GEN-LAST:event_btnThemImeiActionPerformed

    private void cbHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbHangActionPerformed
        Hang selectedHang = (Hang) cbHang.getSelectedItem();
        int hangId = 0;
        if (selectedHang != null) {
            hangId = selectedHang.getId();
        } else {
            return;
        }
        if (!selectedHang.isTrangThai()) {
            return;
        }

        List<DongSanPham> dongSanPhamList = dongSanPhamService.getAllEntityByHang(hangId);
        cbDongSanPham.removeAllItems();
        dongSanPhamList.forEach(dsp -> cbDongSanPham.addItem(dsp));
    }//GEN-LAST:event_cbHangActionPerformed

    private void cbHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbHangMouseClicked
        showComboBoxHang();
    }//GEN-LAST:event_cbHangMouseClicked

    private void cbDongSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbDongSanPhamMouseClicked
        showComboBoxDongSP();
    }//GEN-LAST:event_cbDongSanPhamMouseClicked

    private void cbMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMauSacActionPerformed

    }//GEN-LAST:event_cbMauSacActionPerformed

    private void cbMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbMauSacMouseClicked
        showComboBoxMauSac();
    }//GEN-LAST:event_cbMauSacMouseClicked

    private void cbLocHeDHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbLocHeDHMouseClicked
        showComboBoxLocHeHD();
    }//GEN-LAST:event_cbLocHeDHMouseClicked

    private void cbLocHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbLocHangMouseClicked
        showComboBoxLocHang();
    }//GEN-LAST:event_cbLocHangMouseClicked

    private void cbLocMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbLocMauSacMouseClicked
        showComboBoxLocMauSac();
    }//GEN-LAST:event_cbLocMauSacMouseClicked

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        Hang hang = (Hang) cbLocHang.getSelectedItem();
        MauSac mauSac = (MauSac) cbLocMauSac.getSelectedItem();
        HeDieuHanh heDieuHanh = (HeDieuHanh) cbLocHeDH.getSelectedItem();

        if (hang.getId() == 0 && mauSac.getId() == 0 && heDieuHanh.getId() == 0) {
            return;
        }

        activedienThoaiResponseList = dienThoaiService.filterResponses(hang.getId(), mauSac.getId(), heDieuHanh.getId());
        showActiveTable(activedienThoaiResponseList);
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnXoaLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaLocActionPerformed
        cbLocHang.setSelectedIndex(0);
        cbLocMauSac.setSelectedIndex(0);
        cbLocHeDH.setSelectedIndex(0);
        activedienThoaiResponseList = dienThoaiService.getAllResponseByStatus(true);
        showActiveTable(activedienThoaiResponseList);
    }//GEN-LAST:event_btnXoaLocActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        jTabbedPane1.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = jTabbedPane1.getSelectedIndex();
                if (index == 0) {
                    setButtons(true);
                } else {
                    setButtons(false);
                }
            }
        });
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void setButtons(boolean boo) {
        btnLamMoi.setEnabled(boo);
        btnThem.setEnabled(boo);
        btnSua.setEnabled(boo);
        btnXoa.setEnabled(boo);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDongSanPham;
    private javax.swing.JButton btnHang;
    private javax.swing.JButton btnHeDieuHanh;
    private javax.swing.JButton btnKhoiPhuc;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnMauSac;
    private javax.swing.JButton btnSapXepGiaGiamDan;
    private javax.swing.JButton btnSapXepGiaTangDan;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemImei;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaLoc;
    private javax.swing.JComboBox<DongSanPham> cbDongSanPham;
    private javax.swing.JComboBox<Hang> cbHang;
    private javax.swing.JComboBox<HeDieuHanh> cbHeDieuHanh;
    private javax.swing.JComboBox<String> cbImei;
    private javax.swing.JComboBox<LoaiManHinh> cbLoaiManHinh;
    private javax.swing.JComboBox<Hang> cbLocHang;
    private javax.swing.JComboBox<HeDieuHanh> cbLocHeDH;
    private javax.swing.JComboBox<MauSac> cbLocMauSac;
    private javax.swing.JComboBox<MauSac> cbMauSac;
    private javax.swing.JComboBox<String> cbRam;
    private javax.swing.JComboBox<String> cbRom;
    private javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbTongSLActive;
    private javax.swing.JLabel lbTongSLInactive;
    private javax.swing.JTable tbActive;
    private javax.swing.JTable tbInactive;
    private javax.swing.JTextField txtCamChinh;
    private javax.swing.JTextField txtCamGocRong;
    private javax.swing.JTextField txtCamPhu;
    private javax.swing.JTextField txtCamTele;
    private javax.swing.JTextField txtCpu;
    private javax.swing.JTextField txtDoPG;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtKichThuoc;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtPin;
    private javax.swing.JTextField txtSearchByTen;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JPanel txtTimKiemTen;
    // End of variables declaration//GEN-END:variables
}
