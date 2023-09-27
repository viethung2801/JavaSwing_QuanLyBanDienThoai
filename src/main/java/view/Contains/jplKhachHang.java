package view.Contains;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.KhachHang;
import model.TheTichDiem;
import service.HoaDonService;
import service.impl.KhachHangServiceImpl;
import viewmodel.KhachHangResponse;
import service.KhachHangService;
import service.impl.HoaDonServiceImpl;
import viewmodel.HoaDonResponse;

public class jplKhachHang extends javax.swing.JPanel {

    private List<KhachHangResponse> listKhachHang = new ArrayList<>();
    private List<KhachHangResponse> listTheTichDiem = new ArrayList<>();
    private List<HoaDonResponse> listHoaDon = new ArrayList<>();
    private KhachHangService service = new KhachHangServiceImpl();
    private HoaDonService serviceHoaDon = new HoaDonServiceImpl();
    private DefaultTableModel dtm = new DefaultTableModel();
    private DefaultTableModel dtmDaXoa = new DefaultTableModel();
    private DefaultTableModel dtmTichDiem = new DefaultTableModel();
    private DefaultTableModel dtmLichSuMuaHang = new DefaultTableModel();

    public jplKhachHang() {
        initComponents();
        viewTable();
        dtm = (DefaultTableModel) tblKhachHang.getModel();
        dtmDaXoa = (DefaultTableModel) tblKhachHangDaXoa.getModel();
        dtmTichDiem = (DefaultTableModel) tblTheTichDiem.getModel();
        dtmLichSuMuaHang = (DefaultTableModel) tblLichSuMuaHang.getModel();
        listKhachHang = service.getAll();
        showData(listKhachHang);

        showDataRemove(listKhachHang);
        listTheTichDiem = service.getAllTheTichDiem();
        showDataTichDiem(listTheTichDiem);

    }

    private void khoiPhuc() {
        int rowIndex = tblKhachHangDaXoa.getSelectedRow();
        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng muốn khôi phục");
            return;
        }
        int id = (int) tblKhachHangDaXoa.getValueAt(rowIndex, 0);

        KhachHangResponse kh = service.getKhachHangById(id);

        service.updateKhoiPhuc(kh, 1);
        listKhachHang = service.getAll();
        showDataRemove(listKhachHang);
        JOptionPane.showMessageDialog(this, "Khôi phục thành công");
        showData(listKhachHang);
    }

    private void viewTable() {
        JTableHeader Theader = tblKhachHang.getTableHeader();
        JTableHeader TheaderXoa = tblKhachHangDaXoa.getTableHeader();
        JTableHeader TheaderTichDiem = tblTheTichDiem.getTableHeader();
        Theader.setFont(new Font("tahoma", Font.BOLD, 15));
        Theader.setBackground(new Color(47, 85, 212));
        Theader.setForeground(Color.white);

        TheaderXoa.setFont(new Font("tahoma", Font.BOLD, 15));
        TheaderXoa.setBackground(new Color(47, 85, 212));
        TheaderXoa.setForeground(Color.white);

        TheaderTichDiem.setFont(new Font("tahoma", Font.BOLD, 15));
        TheaderTichDiem.setBackground(new Color(47, 85, 212));
        TheaderTichDiem.setForeground(Color.white);
    }

    private void showDataLichSu(List<HoaDonResponse> list, int row) {

        String hoTen = tblKhachHang.getValueAt(row, 1).toString();
        lbTenKH.setText(hoTen);
        dtmLichSuMuaHang.setRowCount(0);
        int i = 0;
        for (HoaDonResponse s : list) {

            if (hoTen.equals(s.getTenKhachHang())) {
                dtmLichSuMuaHang.addRow(new Object[]{
                    i, s.getTenKhachHang(), s.getNgayTao(), s.getTongTien(), s.getTenNhanVien()

                });
                i++;
            }
        }
        lbSoLan.setText(i + " Lần");
    }

    private void showData(List<KhachHangResponse> list) {
        dtm.setRowCount(0);
        for (KhachHangResponse s : list) {
            if (s.getTrangThai() == 1) {
                dtm.addRow(s.toDataRow());
            }
        }
    }

    private void showDataRemove(List<KhachHangResponse> list) {
        dtmDaXoa.setRowCount(0);
        for (KhachHangResponse s : list) {
            if (s.getTrangThai() == 0) {
                dtmDaXoa.addRow(s.toDataRow());
            }
        }
    }

    private void showDataTichDiem(List<KhachHangResponse> list) {
        dtmTichDiem.setRowCount(0);
        for (KhachHangResponse s : list) {
            dtmTichDiem.addRow(s.toDataRowTheTichDiem());
        }
    }

    public void showDataToText(int rowIndex) {
        int id = (int) tblKhachHang.getValueAt(rowIndex, 0);
        KhachHangResponse kh = service.getKhachHangById(id);

        txtHoTen.setText(kh.getHoTen());
        txtEmail.setText(kh.getEmail());
        txtSdt.setText(kh.getSdt());

        cldNgaySinh.setDate(Date.from(kh.getNgaySinh().atStartOfDay().toInstant(ZoneOffset.UTC)));
        txtDiaChi.setText(kh.getDiaChi());
        txtMathe.setText(kh.getMaThe());
        if (kh.getTrangThai() == 1) {
            chkTrangThai.setSelected(true);
        } else {
            chkTrangThai.setSelected(false);
        }
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
            LocalDate localDate = cldNgaySinh.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
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

    private Boolean kiemTra(int id, String email, String sdt) {

        StringBuilder sb = new StringBuilder();
        KhachHangResponse kh = service.getKhachHangByEmail(email);
        if (txtHoTen.getText().isBlank()) {
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
                for (KhachHangResponse s : listKhachHang) {
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

        KhachHangResponse khSdt = service.getKhachHangBySdt(txtSdt.getText().trim());
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
                for (KhachHangResponse s : listKhachHang) {
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
        if (cldNgaySinh.getDate() == null) {

            sb.append("Không để trống Ngày Sinh\n");
        }

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return false;
        }
        return true;
    }

    private void setDefault() {
        txtHoTen.setText("");
        txtEmail.setText("");
        txtSdt.setText("");
        cldNgaySinh.setDate(null);
        txtDiaChi.setText("");
        txtMathe.setText("");
        chkTrangThai.setSelected(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        tangDiem = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtSoDiem = new javax.swing.JTextField();
        btnCongDiem = new javax.swing.JButton();
        truDiem = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtTruDiem = new javax.swing.JTextField();
        btnTruDiem = new javax.swing.JButton();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        Lichsumuahang = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblLichSuMuaHang = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        lbTenKH = new javax.swing.JLabel();
        lbSoLan = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhachHangDaXoa = new javax.swing.JTable();
        txtSearchDaXoa = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnSortDaXoaAZ = new javax.swing.JButton();
        btnSortDaXoaZA = new javax.swing.JButton();
        btnKhoiPhuc = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtSearchTichDiem = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTheTichDiem = new javax.swing.JTable();
        btnTangDiem = new javax.swing.JButton();
        btnGiamDiem = new javax.swing.JButton();
        ThemKhachHang = new javax.swing.JPanel();
        jplThongTin = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cboNam = new javax.swing.JRadioButton();
        cboNu = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtMathe = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        chkTrangThai = new javax.swing.JCheckBox();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        cldNgaySinh = new com.toedter.calendar.JDateChooser();
        btnNew = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(47, 85, 212));
        jLabel15.setText("CỘNG ĐIỂM");

        jLabel16.setText("SỐ ĐIỂM:");

        txtSoDiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        btnCongDiem.setBackground(new java.awt.Color(47, 85, 212));
        btnCongDiem.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnCongDiem.setForeground(new java.awt.Color(255, 255, 255));
        btnCongDiem.setText("CỘNG");
        btnCongDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCongDiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSoDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jLabel15)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnCongDiem)
                .addGap(77, 77, 77))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSoDiem, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnCongDiem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tangDiemLayout = new javax.swing.GroupLayout(tangDiem.getContentPane());
        tangDiem.getContentPane().setLayout(tangDiemLayout);
        tangDiemLayout.setHorizontalGroup(
            tangDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        tangDiemLayout.setVerticalGroup(
            tangDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(47, 85, 212));
        jLabel17.setText("TRỪ ĐIỂM");

        jLabel18.setText("SỐ ĐIỂM:");

        txtTruDiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        btnTruDiem.setBackground(new java.awt.Color(47, 85, 212));
        btnTruDiem.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnTruDiem.setForeground(new java.awt.Color(255, 255, 255));
        btnTruDiem.setText("TRỪ");
        btnTruDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTruDiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTruDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jLabel17)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnTruDiem)
                .addGap(77, 77, 77))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTruDiem, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnTruDiem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout truDiemLayout = new javax.swing.GroupLayout(truDiem.getContentPane());
        truDiem.getContentPane().setLayout(truDiemLayout);
        truDiemLayout.setHorizontalGroup(
            truDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        truDiemLayout.setVerticalGroup(
            truDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jMenuItem1.setText("Lịch sử mua hàng");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        Lichsumuahang.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Lichsumuahang.setTitle("LỊCH SỬA MUA HÀNG");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setText("LỊCH SỬ MUA HÀNG");

        tblLichSuMuaHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "HỌ VÀ TÊN", "NGÀY MUA", "TỔNG TIỀN", "NHÂN VIÊN BÁN HÀNG"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblLichSuMuaHang);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setText("Khách hàng");

        lbTenKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTenKH.setForeground(new java.awt.Color(255, 51, 51));
        lbTenKH.setText("jLabel21");

        lbSoLan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbSoLan.setForeground(new java.awt.Color(255, 51, 51));
        lbSoLan.setText("0 Lần");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setText("Đã mua");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel19))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(207, 207, 207)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbTenKH)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel23)
                                .addGap(18, 18, 18)
                                .addComponent(lbSoLan)))
                        .addGap(0, 206, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel19)
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(lbTenKH)
                    .addComponent(lbSoLan)
                    .addComponent(jLabel23))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout LichsumuahangLayout = new javax.swing.GroupLayout(Lichsumuahang.getContentPane());
        Lichsumuahang.getContentPane().setLayout(LichsumuahangLayout);
        LichsumuahangLayout.setHorizontalGroup(
            LichsumuahangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        LichsumuahangLayout.setVerticalGroup(
            LichsumuahangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "HỌ VÀ TÊN", "EMAIL", "SDT", "GIỚI TÍNH", "NGÀY SINH", "ĐỊA CHỈ", "ĐIỂM", "TRẠNG THÁI"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHang.setFocusable(false);
        tblKhachHang.setGridColor(new java.awt.Color(47, 85, 212));
        tblKhachHang.setRowHeight(25);
        tblKhachHang.setShowGrid(true);
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        tblKhachHang.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                tblKhachHangCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);
        if (tblKhachHang.getColumnModel().getColumnCount() > 0) {
            tblKhachHang.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblKhachHang.getColumnModel().getColumn(1).setPreferredWidth(50);
            tblKhachHang.getColumnModel().getColumn(2).setPreferredWidth(50);
            tblKhachHang.getColumnModel().getColumn(3).setPreferredWidth(40);
            tblKhachHang.getColumnModel().getColumn(4).setPreferredWidth(20);
            tblKhachHang.getColumnModel().getColumn(5).setPreferredWidth(40);
            tblKhachHang.getColumnModel().getColumn(6).setPreferredWidth(30);
            tblKhachHang.getColumnModel().getColumn(7).setPreferredWidth(30);
            tblKhachHang.getColumnModel().getColumn(8).setPreferredWidth(30);
        }

        jLabel2.setText("TÌM KIẾM SĐT:");

        txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));
        txtSearch.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchCaretUpdate(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(47, 85, 212));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-sort-alpha-up-20.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(47, 85, 212));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-sort-alpha-up-reversed-20.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel12.setText("SẮP XẾP THEO TÊN:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch)
                        .addGap(66, 66, 66)
                        .addComponent(jLabel12)
                        .addGap(35, 35, 35)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(81, 81, 81))
                    .addComponent(jScrollPane1))
                .addGap(11, 11, 11))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("KHÁCH HÀNG", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblKhachHangDaXoa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tblKhachHangDaXoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "HỌ VÀ TÊN", "EMAIL", "SDT", "GIỚI TÍNH", "NGÀY SINH", "ĐỊA CHỈ", "ĐIỂM", "TRẠNG THÁI", "Khôi Phục"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHangDaXoa.setFocusable(false);
        tblKhachHangDaXoa.setGridColor(new java.awt.Color(47, 85, 212));
        tblKhachHangDaXoa.setRowHeight(25);
        tblKhachHangDaXoa.setShowGrid(true);
        tblKhachHangDaXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangDaXoaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKhachHangDaXoa);
        if (tblKhachHangDaXoa.getColumnModel().getColumnCount() > 0) {
            tblKhachHangDaXoa.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblKhachHangDaXoa.getColumnModel().getColumn(1).setPreferredWidth(50);
            tblKhachHangDaXoa.getColumnModel().getColumn(2).setPreferredWidth(50);
            tblKhachHangDaXoa.getColumnModel().getColumn(3).setPreferredWidth(40);
            tblKhachHangDaXoa.getColumnModel().getColumn(4).setPreferredWidth(20);
            tblKhachHangDaXoa.getColumnModel().getColumn(5).setPreferredWidth(40);
            tblKhachHangDaXoa.getColumnModel().getColumn(6).setPreferredWidth(30);
            tblKhachHangDaXoa.getColumnModel().getColumn(7).setPreferredWidth(30);
            tblKhachHangDaXoa.getColumnModel().getColumn(8).setPreferredWidth(30);
        }

        txtSearchDaXoa.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));
        txtSearchDaXoa.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchDaXoaCaretUpdate(evt);
            }
        });

        jLabel13.setText("TÌM KIẾM SĐT:");

        jLabel14.setText("SẮP XẾP THEO TÊN:");

        btnSortDaXoaAZ.setBackground(new java.awt.Color(47, 85, 212));
        btnSortDaXoaAZ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-sort-alpha-up-20.png"))); // NOI18N
        btnSortDaXoaAZ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSortDaXoaAZMouseClicked(evt);
            }
        });

        btnSortDaXoaZA.setBackground(new java.awt.Color(47, 85, 212));
        btnSortDaXoaZA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-sort-alpha-up-reversed-20.png"))); // NOI18N
        btnSortDaXoaZA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSortDaXoaZAMouseClicked(evt);
            }
        });

        btnKhoiPhuc.setBackground(new java.awt.Color(47, 85, 212));
        btnKhoiPhuc.setForeground(new java.awt.Color(255, 255, 255));
        btnKhoiPhuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-available-updates-20 (1).png"))); // NOI18N
        btnKhoiPhuc.setText("KHÔI PHỤC");
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
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearchDaXoa)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addGap(35, 35, 35)
                        .addComponent(btnSortDaXoaAZ)
                        .addGap(18, 18, 18)
                        .addComponent(btnSortDaXoaZA)
                        .addGap(27, 27, 27)
                        .addComponent(btnKhoiPhuc, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addGap(11, 11, 11))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSearchDaXoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSortDaXoaAZ, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSortDaXoaZA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnKhoiPhuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("ĐÃ XÓA", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("TÌM KIẾM THEO MÃ");

        txtSearchTichDiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));
        txtSearchTichDiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchTichDiemCaretUpdate(evt);
            }
        });

        tblTheTichDiem.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tblTheTichDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "MÃ THẺ", "TÊN KHÁCH HÀNG", "NGÀY KÍCH HOẠT", "SỐ ĐIỂM", "TRẠNG  THÁI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTheTichDiem.setRowHeight(25);
        jScrollPane3.setViewportView(tblTheTichDiem);

        btnTangDiem.setBackground(new java.awt.Color(47, 85, 212));
        btnTangDiem.setForeground(new java.awt.Color(255, 255, 255));
        btnTangDiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-add-new-20.png"))); // NOI18N
        btnTangDiem.setText("TĂNG ĐIỂM");
        btnTangDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTangDiemActionPerformed(evt);
            }
        });

        btnGiamDiem.setBackground(new java.awt.Color(47, 85, 212));
        btnGiamDiem.setForeground(new java.awt.Color(255, 255, 255));
        btnGiamDiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-subtract-20.png"))); // NOI18N
        btnGiamDiem.setText("TRỪ ĐIỂM");
        btnGiamDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiamDiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(27, 27, 27)
                        .addComponent(txtSearchTichDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTangDiem)
                        .addGap(41, 41, 41)
                        .addComponent(btnGiamDiem)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearchTichDiem, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addComponent(btnTangDiem)
                        .addComponent(btnGiamDiem)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(179, 179, 179))
        );

        jTabbedPane1.addTab("QUẢN LÝ TÍCH ĐIỂM", jPanel3);

        ThemKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        ThemKhachHang.setLayout(new javax.swing.BoxLayout(ThemKhachHang, javax.swing.BoxLayout.LINE_AXIS));

        jplThongTin.setBackground(new java.awt.Color(255, 255, 255));
        jplThongTin.setBorder(javax.swing.BorderFactory.createTitledBorder("THÔNG TIN"));

        jLabel4.setText("HỌ VÀ TÊN:");

        jLabel5.setText("EMAIL:");

        jLabel6.setText("SDT:");

        txtHoTen.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtHoTen.setForeground(new java.awt.Color(51, 51, 51));
        txtHoTen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        txtEmail.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(51, 51, 51));
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        txtSdt.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtSdt.setForeground(new java.awt.Color(51, 51, 51));
        txtSdt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        jLabel7.setText("GIỚI TÍNH:");

        cboNam.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(cboNam);
        cboNam.setSelected(true);
        cboNam.setText("NAM");

        cboNu.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(cboNu);
        cboNu.setText("NỮ");

        jLabel8.setText("NGÀY SINH:");

        jLabel9.setText("ĐỊA CHỈ:");

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtDiaChi.setForeground(new java.awt.Color(51, 51, 51));
        txtDiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        jLabel10.setText("THẺ TÍCH ĐIỂM:");

        txtMathe.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtMathe.setForeground(new java.awt.Color(204, 0, 0));
        txtMathe.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));
        txtMathe.setEnabled(false);

        jLabel11.setText("TRẠNG THÁI:");

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

        btnSua.setBackground(new java.awt.Color(47, 85, 212));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/EDIT.png"))); // NOI18N
        btnSua.setText("SỬA");
        btnSua.setBorderPainted(false);
        btnSua.setFocusable(false);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(47, 85, 212));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-remove-30.png"))); // NOI18N
        btnXoa.setText("XÓA");
        btnXoa.setBorderPainted(false);
        btnXoa.setFocusable(false);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        cldNgaySinh.setDateFormatString("yyyy-MM-dd");
        cldNgaySinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        btnNew.setBackground(new java.awt.Color(47, 85, 212));
        btnNew.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNew.setForeground(new java.awt.Color(255, 255, 255));
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-new-20.png"))); // NOI18N
        btnNew.setText("MỚI");
        btnNew.setBorderPainted(false);
        btnNew.setFocusable(false);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jplThongTinLayout = new javax.swing.GroupLayout(jplThongTin);
        jplThongTin.setLayout(jplThongTinLayout);
        jplThongTinLayout.setHorizontalGroup(
            jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplThongTinLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplThongTinLayout.createSequentialGroup()
                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jplThongTinLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(28, 28, 28)
                                .addComponent(txtHoTen))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jplThongTinLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(68, 68, 68)
                                .addComponent(txtSdt))
                            .addGroup(jplThongTinLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(54, 54, 54)
                                .addComponent(txtEmail)))
                        .addGap(78, 78, 78)
                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jplThongTinLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(30, 30, 30)
                        .addComponent(cboNam)
                        .addGap(26, 26, 26)
                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jplThongTinLayout.createSequentialGroup()
                                .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(82, 82, 82)
                                .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jplThongTinLayout.createSequentialGroup()
                                .addComponent(cboNu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11)))
                        .addGap(32, 32, 32)))
                .addGap(30, 30, 30)
                .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplThongTinLayout.createSequentialGroup()
                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jplThongTinLayout.createSequentialGroup()
                                .addComponent(chkTrangThai)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtDiaChi)
                            .addComponent(txtMathe)
                            .addComponent(cldNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(205, 205, 205))
                    .addGroup(jplThongTinLayout.createSequentialGroup()
                        .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(73, 73, 73)
                        .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(111, 111, 111))))
        );
        jplThongTinLayout.setVerticalGroup(
            jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplThongTinLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cldNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtSdt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jplThongTinLayout.createSequentialGroup()
                        .addComponent(txtMathe, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplThongTinLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboNam)
                                .addComponent(cboNu)
                                .addComponent(chkTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jplThongTinLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        ThemKhachHang.add(jplThongTin);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(47, 85, 212));
        jLabel1.setText("KHÁCH HÀNG");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ThemKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(ThemKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        int rowIndex = tblKhachHang.getSelectedRow();
        showDataToText(rowIndex);
        if (SwingUtilities.isRightMouseButton(evt)) {
            int row = tblKhachHang.rowAtPoint(evt.getPoint());
            if (row >= 0 && row < listKhachHang.size()) {
                tblKhachHang.setRowSelectionInterval(row, row);
                jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
                listHoaDon = serviceHoaDon.getResponsesByTraGop(2);
                showDataLichSu(listHoaDon, rowIndex);

            }
        }

    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        KhachHang kh = getData();

        if (kiemTra(0, txtEmail.getText().trim(), txtSdt.getText().trim())) {
            JOptionPane.showMessageDialog(this, service.add(kh));

            listKhachHang = service.getAll();
            KhachHangResponse s = service.getKhachHangById(kh.getId());
            service.updateKhoiPhuc(s, chkTrangThai.isSelected() ? 1 : 0);
            showData(listKhachHang);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int rowIndex = tblKhachHang.getSelectedRow();
        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng muốn sửa");
            return;
        }

        int id = (int) tblKhachHang.getValueAt(rowIndex, 0);
        KhachHangResponse kh = service.getKhachHangById(id);

        if (kiemTra(kh.getId(), txtEmail.getText().trim(), txtSdt.getText().trim())) {
            int choose = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn sửa khách hàng này không?", "UPDATE", JOptionPane.YES_NO_CANCEL_OPTION);
            if (choose == 0) {
                KhachHang s = getData();
                JOptionPane.showMessageDialog(this, service.update(new KhachHangResponse(kh.getId(), s.getHoTen(), s.getEmail(), s.getSdt(), s.isGioiTinh(), s.getNgaySinh(), s.getDiaChi(), s.getTrangThai())));
                if (s.getTrangThai() == 0) {
                    service.updateKhoiPhuc(kh, 0);
                }
                listKhachHang = service.getAll();
                showData(listKhachHang);
                showDataRemove(listKhachHang);
            }
        }

    }//GEN-LAST:event_btnSuaActionPerformed

    private void tblKhachHangDaXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangDaXoaMouseClicked
        int rowIndex = tblKhachHangDaXoa.getSelectedRow();
        showDataToText(rowIndex);
        if (SwingUtilities.isRightMouseButton(evt)) {
            int row = tblKhachHang.rowAtPoint(evt.getPoint());
            if (row >= 0 && row < listKhachHang.size()) {
                tblKhachHang.setRowSelectionInterval(row, row);
                jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
                listHoaDon = serviceHoaDon.getResponsesByTraGop(2);
                showDataLichSu(listHoaDon, rowIndex);

            }
        }
    }//GEN-LAST:event_tblKhachHangDaXoaMouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        setDefault();
        listKhachHang = service.getAll();
        showData(listKhachHang);
        showDataRemove(listKhachHang);
        listTheTichDiem = service.getAllTheTichDiem();
        showDataTichDiem(listTheTichDiem);
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int rowIndex = tblKhachHang.getSelectedRow();

        if (rowIndex < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng muốn xóa");
            return;
        }
        int id = (int) tblKhachHang.getValueAt(rowIndex, 0);
        System.out.println(id);
        KhachHangResponse kh = service.getKhachHangById(id);
        int choose = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xóa khách hàng này không?", "Delete", JOptionPane.YES_NO_CANCEL_OPTION);
        if (choose == 0) {
            service.updateKhoiPhuc(kh, 0);
            listKhachHang = service.getAll();
            JOptionPane.showMessageDialog(this, "Xóa thành công");
            showData(listKhachHang);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtSearchCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchCaretUpdate
        listKhachHang = service.findBySdt(txtSearch.getText().trim(), 1);
        showData(listKhachHang);
    }//GEN-LAST:event_txtSearchCaretUpdate

    private void tblKhachHangCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tblKhachHangCaretPositionChanged

    }//GEN-LAST:event_tblKhachHangCaretPositionChanged

    private void txtSearchDaXoaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchDaXoaCaretUpdate
        listKhachHang = service.findBySdt(txtSearchDaXoa.getText().trim(), 0);
        showDataRemove(listKhachHang);
    }//GEN-LAST:event_txtSearchDaXoaCaretUpdate

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        listKhachHang = service.sortByName(true, 1);
        JOptionPane.showMessageDialog(this, "Sort thành công");
        showData(listKhachHang);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        listKhachHang = service.sortByName(false, 1);
        JOptionPane.showMessageDialog(this, "Sort thành công");
        showData(listKhachHang);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnSortDaXoaAZMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSortDaXoaAZMouseClicked
        listKhachHang = service.sortByName(true, 0);
        JOptionPane.showMessageDialog(this, "Sort thành công");
        showDataRemove(listKhachHang);
    }//GEN-LAST:event_btnSortDaXoaAZMouseClicked

    private void btnSortDaXoaZAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSortDaXoaZAMouseClicked
        listKhachHang = service.sortByName(false, 0);
        JOptionPane.showMessageDialog(this, "Sort thành công");
        showDataRemove(listKhachHang);
    }//GEN-LAST:event_btnSortDaXoaZAMouseClicked
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
    private void txtSearchTichDiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchTichDiemCaretUpdate
        if (txtSearchTichDiem.getText().isBlank()) {
            listTheTichDiem = service.getAllTheTichDiem();
            showDataTichDiem(listTheTichDiem);
            return;
        }
        System.out.println(txtSearchTichDiem.getText());
        listTheTichDiem = service.findByMa(txtSearchTichDiem.getText().trim());
        showDataTichDiem(listTheTichDiem);
    }//GEN-LAST:event_txtSearchTichDiemCaretUpdate

    private void btnTangDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTangDiemActionPerformed
        tangDiem.setSize(300, 200);
        tangDiem.setLocationRelativeTo(null);
        tangDiem.setVisible(true);
        truDiem.setResizable(false);
    }//GEN-LAST:event_btnTangDiemActionPerformed

    private void btnCongDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCongDiemActionPerformed

        if (!txtSoDiem.getText().trim().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số");
            txtSoDiem.setText("");
            tangDiem.dispose();

        } else {
            int sodiem = Integer.parseInt(txtSoDiem.getText().trim());
            int rowIndex = tblTheTichDiem.getSelectedRow();
            if (rowIndex < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng muốn tăng điểm");
                return;
            }
            String mathe = (String) tblTheTichDiem.getValueAt(rowIndex, 1);
            KhachHangResponse kh = service.getKhachHangByMaThe(mathe);
            int choose = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn thêm điểm cho khách hàng này không?", "Tăng Điểm", JOptionPane.YES_NO_CANCEL_OPTION);
            if (choose == 0) {
                JOptionPane.showMessageDialog(this, service.updateDiem(kh, kh.getSoDiem() + sodiem));
                listTheTichDiem = service.getAllTheTichDiem();
                showDataTichDiem(listTheTichDiem);

            }
            tangDiem.dispose();
        }

    }//GEN-LAST:event_btnCongDiemActionPerformed

    private void btnGiamDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiamDiemActionPerformed
        truDiem.setSize(300, 200);
        truDiem.setLocationRelativeTo(null);
        truDiem.setVisible(true);
        truDiem.setResizable(false);
    }//GEN-LAST:event_btnGiamDiemActionPerformed

    private void btnTruDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTruDiemActionPerformed
        if (!txtTruDiem.getText().trim().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số");
            txtTruDiem.setText("");
            tangDiem.dispose();

        } else {
            int sodiem = Integer.parseInt(txtTruDiem.getText().trim());
            int rowIndex = tblTheTichDiem.getSelectedRow();
            if (rowIndex < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng muốn trù điểm");
                return;
            }
            System.out.println(rowIndex);
            String mathe = (String) tblTheTichDiem.getValueAt(rowIndex, 1);
            KhachHangResponse kh = service.getKhachHangByMaThe(mathe);
            int choose = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn trừ điểm cho khách hàng này không?", "Tăng Điểm", JOptionPane.YES_NO_CANCEL_OPTION);
            if (choose == 0) {
                if (kh.getSoDiem() - sodiem < 0) {
                    JOptionPane.showMessageDialog(this, service.updateDiem(kh, 0));

                } else {
                    JOptionPane.showMessageDialog(this, service.updateDiem(kh, kh.getSoDiem() - sodiem));

                }

            }
            listTheTichDiem = service.getAllTheTichDiem();
            showDataTichDiem(listTheTichDiem);
            truDiem.dispose();
        }

    }//GEN-LAST:event_btnTruDiemActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        setDefault();

    }//GEN-LAST:event_btnNewActionPerformed

    private void btnKhoiPhucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiPhucActionPerformed
        khoiPhuc();
    }//GEN-LAST:event_btnKhoiPhucActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Lichsumuahang.setSize(650, 400);
        Lichsumuahang.setLocationRelativeTo(null);
        Lichsumuahang.setResizable(false);
        Lichsumuahang.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Lichsumuahang;
    private javax.swing.JPanel ThemKhachHang;
    private javax.swing.JButton btnCongDiem;
    private javax.swing.JButton btnGiamDiem;
    private javax.swing.JButton btnKhoiPhuc;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSortDaXoaAZ;
    private javax.swing.JButton btnSortDaXoaZA;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTangDiem;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTruDiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton cboNam;
    private javax.swing.JRadioButton cboNu;
    private javax.swing.JCheckBox chkTrangThai;
    private com.toedter.calendar.JDateChooser cldNgaySinh;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JLabel jLabel23;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jplThongTin;
    private javax.swing.JLabel lbSoLan;
    private javax.swing.JLabel lbTenKH;
    private javax.swing.JDialog tangDiem;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTable tblKhachHangDaXoa;
    private javax.swing.JTable tblLichSuMuaHang;
    private javax.swing.JTable tblTheTichDiem;
    private javax.swing.JDialog truDiem;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMathe;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearchDaXoa;
    private javax.swing.JTextField txtSearchTichDiem;
    private javax.swing.JTextField txtSoDiem;
    private javax.swing.JTextField txtTruDiem;
    // End of variables declaration//GEN-END:variables
}
