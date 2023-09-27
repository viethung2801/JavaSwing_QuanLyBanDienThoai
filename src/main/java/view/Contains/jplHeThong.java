package view.Contains;

import java.awt.CardLayout;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import service.DoanhThuThongKeService;
import service.QuanLyNhanVienService;
import service.SanPhamThongKeService;
import service.impl.DoanhThuThongKeServiceImpl;
import service.impl.QuanLyNhanVienServiceImpl;
import service.impl.SanPhamThongKeServiceImpl;
import viewmodel.DoanhThuThongKeResponse;
import viewmodel.NhanVienResponse;
import viewmodel.SanPhamThongKeResponse;

public class jplHeThong extends javax.swing.JPanel {

    private List<NhanVienResponse> list1;
    private List<NhanVienResponse> list2;
    private DefaultTableModel dtm1;
    private DefaultTableModel dtm2;
    private DefaultTableModel dtm3;
    private DefaultTableModel dtm4;
    private DefaultComboBoxModel dcm1;
    private DefaultComboBoxModel dcm2;
    private QuanLyNhanVienService service;
    private SanPhamThongKeService serviceSPTK;
    private DoanhThuThongKeService serviceDTTK;
    private int rowSelected;
    private String strHinhAnh = null;

    public jplHeThong() {
        initComponents();

        list1 = new ArrayList<>();
        dtm1 = (DefaultTableModel) tbLamViec.getModel();
        dtm2 = (DefaultTableModel) tbNghiViec.getModel();
        dtm3 = (DefaultTableModel) tblSanPhamTK.getModel();
        dtm4 = (DefaultTableModel) tblDoanhThuTK.getModel();
        dcm1 = (DefaultComboBoxModel) cbLocGioiTinh.getModel();
        dcm2 = (DefaultComboBoxModel) cbLocChucVu.getModel();
        service = new QuanLyNhanVienServiceImpl();
        serviceSPTK = new SanPhamThongKeServiceImpl();
        serviceDTTK = new DoanhThuThongKeServiceImpl();

        showDataCboLocChucVu();
        showDataCboLocGioiTinh();

        list1 = service.getAllLam();
        showDataTblLam(list1);

        list2 = service.getAllNghi();
        showDataTblNghi(list2);

        int month = jmonth.getMonth() + 1;
        int year = jyear.getYear();
        setDataToSanPhamTKThang(pnlSPTKCot, month, year);
    }

    private void showDataTblLam(List<NhanVienResponse> lists) {
        dtm1.setRowCount(0);
        for (NhanVienResponse nv : lists) {
            dtm1.addRow(new Object[]{nv.getHoTen(), nv.isGioiTinh() ? "Nam" : "Nữ", nv.getSdt(), nv.getNgaySinh(), nv.getDiaChi(),
                nv.getEmail(), nv.isChucVu() ? "Nhân Viên" : "Quản lý", nv.isTrangThai() ? "Đang làm việc" : "Nghỉ việc",
                nv.getHinhAnh(), nv.getTaiKhoan(), nv.getMatKhau()});
        }
    }

    private void showDataTblNghi(List<NhanVienResponse> lists) {
        dtm2.setRowCount(0);
        for (NhanVienResponse nv : lists) {
            dtm2.addRow(new Object[]{nv.getHoTen(), nv.isGioiTinh() ? "Nam" : "Nữ", nv.getSdt(), nv.getNgaySinh(), nv.getDiaChi(),
                nv.getEmail(), nv.isChucVu() ? "Nhân Viên" : "Quản lý", nv.isTrangThai() ? "Đang làm việc" : "Nghỉ việc",
                nv.getHinhAnh(), nv.getTaiKhoan(), nv.getMatKhau()});
        }
    }

    private void showDataCboLocGioiTinh() {
        String[] gt = {"", "NAM", "NỮ"};
        for (String string : gt) {
            dcm1.addElement(string);
        }
    }

    private void showDataCboLocChucVu() {
        String[] gt = {"", "NHÂN VIÊN", "QUẢN LÝ"};
        for (String string : gt) {
            dcm2.addElement(string);
        }
    }

    private void showDetail(int rowSelected, List<NhanVienResponse> lists) {
        NhanVienResponse nv = lists.get(rowSelected);
        txtHoTen.setText(nv.getHoTen());
        if (nv.isGioiTinh()) {
            rdBtnNam.setSelected(true);
        } else {
            rdBtnNu.setSelected(true);
        }
        txtSdt.setText(nv.getSdt());
        txtNgaySinh.setDate(Date.from(nv.getNgaySinh().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        txtDiaChi.setText(nv.getDiaChi());
        txtEmail.setText(nv.getEmail());
        if (nv.isChucVu()) {
            rdBtnNhanVien.setSelected(true);
        } else {
            rdBtnQuanLy.setSelected(true);
        }
        if (nv.isTrangThai()) {
            rdBtnLamViec.setSelected(true);
        } else {
            rdBtnNghiViec.setSelected(true);
        }
        ImageIcon icon = new ImageIcon(getClass().getResource("/Icon/" + nv.getHinhAnh()));
        Image newImage = icon.getImage().getScaledInstance(169, 213, Image.SCALE_SMOOTH);
        lbAnh.setIcon(new ImageIcon(newImage));
        strHinhAnh = nv.getHinhAnh();
        txtTaiKhoan.setText(nv.getTaiKhoan());
        txtMatKhau.setText(nv.getMatKhau());
    }

    private void clear() {
        txtHoTen.setText("");
        rdBtnNam.setSelected(true);
        txtSdt.setText("");
        txtNgaySinh.setDate(null);
        txtDiaChi.setText("");
        txtEmail.setText("");
        rdBtnNhanVien.setSelected(true);
        rdBtnLamViec.setSelected(true);
        lbAnh.setIcon(null);
        txtTaiKhoan.setText("");
        txtMatKhau.setText("");
    }

    private boolean validated() {
        if (txtHoTen.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phải điền họ tên");
            return false;
        }
        if (txtHoTen.getText().trim().matches(".*[0-9].*")) {
            JOptionPane.showMessageDialog(this, "Họ tên chỉ chứa chữ");
            return false;
        }
        if (txtHoTen.getText().trim().length() > 50) {
            JOptionPane.showMessageDialog(this, "Họ tên không quá 50 kí tự");
            return false;
        }
        if (txtSdt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phải điền số điện thoại");
            return false;
        }
        if (!txtSdt.getText().trim().matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "Sđt chỉ chứa số và có 10 chữ số");
            return false;
        }
        if (txtNgaySinh.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Phải điền ngày sinh");
            return false;
        }
        if (txtDiaChi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phải điền địa chỉ");
            return false;
        }
        if (txtDiaChi.getText().trim().length() > 128) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không quá 128 kí tự");
            return false;
        }
        if (txtEmail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phải điền email");
            return false;
        }
        String regexEmail = "\\w+@\\w+\\.+\\w+";
        if (txtEmail.getText().trim().length() > 50) {
            JOptionPane.showMessageDialog(this, "Email không quá 50 kí tự");
            return false;
        }
        if (strHinhAnh == null) {
            JOptionPane.showMessageDialog(this, "Phải chọn ảnh");
            return false;
        }
        if (txtTaiKhoan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phải điền tài khoản");
            return false;
        }
        if (txtTaiKhoan.getText().trim().length() > 15) {
            JOptionPane.showMessageDialog(this, "Tài khoản không quá 15 kí tự");
            return false;
        }
        if (txtMatKhau.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phải điền mật khẩu");
            return false;
        }
        if (txtMatKhau.getText().trim().length() > 50) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không quá 50 kí tự");
            return false;
        }

        return true;
    }

    private NhanVienResponse readForm() {
        Date date1 = txtNgaySinh.getDate();
        LocalDate ld1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return new NhanVienResponse(txtHoTen.getText(), rdBtnNam.isSelected(), txtSdt.getText(), ld1, txtDiaChi.getText(), txtEmail.getText(),
                rdBtnNhanVien.isSelected(), rdBtnLamViec.isSelected(), strHinhAnh, txtTaiKhoan.getText(), txtMatKhau.getText());
    }

    private void chooseImg() {
       // JFileChooser chooser = new JFileChooser();
        JFileChooser chooser = new JFileChooser("D:\\NetBeansProjects\\DuAn1\\DuAn1\\src\\main\\resources\\Icon");
        FileFilter filter = new FileNameExtensionFilter("Tệp JPG", "jpg");
        chooser.setFileFilter(filter);
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            strHinhAnh = selectedFile.getName();
            ImageIcon icon = new ImageIcon(selectedFile.getPath());
            Image newImage = icon.getImage().getScaledInstance(169, 213, Image.SCALE_SMOOTH);
            lbAnh.setIcon(new ImageIcon(newImage));
        }

//        JFileChooser chooser = new JFileChooser("D:\\NetBeansProjects\\DuAn1\\DuAn1\\src\\main\\resources\\Icon");
//        JFileChooser chooser = new JFileChooser();
//        FileFilter filter = new FileNameExtensionFilter("Tệp JPG", "jpg");
//        chooser.setFileFilter(filter);
//        int result = chooser.showOpenDialog(null);
//        if(result == JFileChooser.APPROVE_OPTION) {
//            File selectedFile = chooser.getSelectedFile();
//            strHinhAnh = selectedFile.getName();
//            File newFile = new File("D:\\NetBeansProjects\\DuAn1\\DuAn1\\src\\main\\resources\\Icon\\" + strHinhAnh);
//            selectedFile.renameTo(newFile);
//            ImageIcon icon = new ImageIcon(newFile.getPath());
//            Image newImage = icon.getImage().getScaledInstance(148, 184, Image.SCALE_SMOOTH);
//            lbAnh.setIcon(new ImageIcon(newImage));
//        }
    }

    private void setDataToSanPhamTKThang(JPanel jpn, int month, int year) {
        List<SanPhamThongKeResponse> listSP = serviceSPTK.getSPTKThang(month, year);
        if (listSP != null) {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (SanPhamThongKeResponse sptk : listSP) {
                dataset.addValue(sptk.getSlSanPham(), "  Sản phẩm", sptk.getTenSp());
            }

            JFreeChart chart = ChartFactory.createBarChart("Biểu đồ thống kê số lượng sản phẩm bán được trong tháng " + month + " năm " + year, "Tên sản phẩm", "Số lượng", dataset);

            ChartPanel chartPanel = new ChartPanel(chart);

            jpn.removeAll();
            jpn.setLayout(new CardLayout());
            jpn.add(chartPanel);
            jpn.validate();
            jpn.repaint();
        }
    }

    private void setDataToSanPhamTKNam(JPanel jpn, int year) {
        List<SanPhamThongKeResponse> listSP = serviceSPTK.getSPTKNam(year);
        if (listSP != null) {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (SanPhamThongKeResponse sptk : listSP) {
                dataset.addValue(sptk.getSlSanPham(), "  Sản phẩm", sptk.getTenSp());
            }

            JFreeChart chart = ChartFactory.createBarChart("Biểu đồ thống kê số lượng sản phẩm bán được trong năm " + year, "Tên sản phẩm", "Số lượng", dataset);

            ChartPanel chartPanel = new ChartPanel(chart);

            jpn.removeAll();
            jpn.setLayout(new CardLayout());
            jpn.add(chartPanel);
            jpn.validate();
            jpn.repaint();
        }
    }
    
    private void setDataToSanPhamTKNgay(JPanel jpn, LocalDateTime ngayDau, LocalDateTime ngayCuoi) {
        List<SanPhamThongKeResponse> listSP = serviceSPTK.getSPTKNgay(ngayDau, ngayCuoi);
        if (listSP != null) {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (SanPhamThongKeResponse sptk : listSP) {
                dataset.addValue(sptk.getSlSanPham(), "  Sản phẩm", sptk.getTenSp());
            }

            JFreeChart chart = ChartFactory.createBarChart("Biểu đồ thống kê số lượng sản phẩm bán được từ " + ngayDau.toLocalDate() + " đến " + ngayCuoi.toLocalDate(), "Tên sản phẩm", "Số lượng", dataset);

            ChartPanel chartPanel = new ChartPanel(chart);

            jpn.removeAll();
            jpn.setLayout(new CardLayout());
            jpn.add(chartPanel);
            jpn.validate();
            jpn.repaint();
        }
    }
       
    private void showDataToTableSanPhamTK(List<SanPhamThongKeResponse> lists) {
        dtm3.setRowCount(0);
        for (SanPhamThongKeResponse sptk : lists) {
            dtm3.addRow(new Object[]{sptk.getTenSp(), sptk.getSlSanPham()});
        }
    }

    private void setDataToDoanhThuTKThang(JPanel jpn, int nam) {
        List<DoanhThuThongKeResponse> listDT = serviceDTTK.getDTTKThang(nam);
        if (listDT != null) {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (DoanhThuThongKeResponse dttk : listDT) {
                dataset.addValue(dttk.getDoanhThu(), "  Doanh thu", "Tháng " + dttk.getThangNam());
            }

            JFreeChart chart = ChartFactory.createBarChart("Biểu đồ thống kê doanh thu theo các tháng trong năm " + nam, "Thời gian", "Doanh thu", dataset);

            ChartPanel chartPanel = new ChartPanel(chart);

            jpn.removeAll();
            jpn.setLayout(new CardLayout());
            jpn.add(chartPanel);
            jpn.validate();
            jpn.repaint();
        }
    }
    
    private void setDataToDoanhThuTKNam(JPanel jpn) {
        List<DoanhThuThongKeResponse> listDT = serviceDTTK.getDTTKNam();
        if (listDT != null) {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (DoanhThuThongKeResponse dttk : listDT) {
                dataset.addValue(dttk.getDoanhThu(), "  Doanh thu", "Năm " + dttk.getThangNam());
            }

            JFreeChart chart = ChartFactory.createBarChart("Biểu đồ thống kê doanh thu theo các năm ", "Thời gian", "Doanh thu", dataset);

            ChartPanel chartPanel = new ChartPanel(chart);

            jpn.removeAll();
            jpn.setLayout(new CardLayout());
            jpn.add(chartPanel);
            jpn.validate();
            jpn.repaint();
        }
    }
    
    private void showDataToTableDoanhThuTK1(List<DoanhThuThongKeResponse> lists) {
        dtm4.setRowCount(0);
        for (DoanhThuThongKeResponse dttk : lists) {
            dtm4.addRow(new Object[]{dttk.getThangNam(), dttk.getDoanhThu()});
        }
    }
    
    private void showDataToTableDoanhThuTK2(List<DoanhThuThongKeResponse> lists) {
        dtm4.setRowCount(0);
        for (DoanhThuThongKeResponse sptk : lists) {
            dtm4.addRow(new Object[]{sptk.getThangNam(), sptk.getDoanhThu()});
        }
    }
    
    public void exportTable(JTable table) {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(this);
            File saveFile = fileChooser.getSelectedFile();
            if(saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("Thống kê");
                Row headerRow = sheet.createRow(0);
                // Xuất tiêu đề cột
                for (int i = 0; i < table.getColumnCount(); i++) {
                    String columnName = table.getColumnName(i);
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(columnName);
                }
                // Xuất dữ liệu từng dòng
                for (int i = 0; i < table.getRowCount(); i++) {
                    Row row = sheet.createRow(i + 1);
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        Object value = table.getValueAt(i, j);
                        Cell cell = row.createCell(j);
                        if (value != null) {
                            cell.setCellValue(value.toString());
                        }
                    }
                }
                FileOutputStream outputStream = new FileOutputStream(new File(saveFile.toString()));
                workbook.write(outputStream);
                workbook.close();
                JOptionPane.showMessageDialog(this, "Xuất file Excel thành công");
            }else {
                //JOptionPane.showMessageDialog(this, "Xuất thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpGioiTinh = new javax.swing.ButtonGroup();
        grpChucVu = new javax.swing.ButtonGroup();
        grpTrangThai = new javax.swing.ButtonGroup();
        grpThoiGian = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jplThongTin = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        rdBtnNam = new javax.swing.JRadioButton();
        rdBtnNu = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        lbAnh = new javax.swing.JLabel();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        txtTaiKhoan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JTextField();
        btnLamMoi = new javax.swing.JButton();
        txtEmail = new javax.swing.JTextField();
        rdBtnNhanVien = new javax.swing.JRadioButton();
        rdBtnQuanLy = new javax.swing.JRadioButton();
        rdBtnLamViec = new javax.swing.JRadioButton();
        rdBtnNghiViec = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        cbLocGioiTinh = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        cbLocChucVu = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbLamViec = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbNghiViec = new javax.swing.JTable();
        btnKhoiPhuc = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jmonth = new com.toedter.calendar.JMonthChooser();
        jyear = new com.toedter.calendar.JYearChooser();
        rdoThang = new javax.swing.JRadioButton();
        rdoNam = new javax.swing.JRadioButton();
        rdoNgay = new javax.swing.JRadioButton();
        jDateCuoi = new com.toedter.calendar.JDateChooser();
        jDateDau = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        pnlSanPhamTK = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        pnlSPTKCot = new javax.swing.JPanel();
        pnlSPTKBang = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPhamTK = new javax.swing.JTable();
        pnlDoanhThuTK = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        pnlDTTKCot = new javax.swing.JPanel();
        pnlSPTKBang1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDoanhThuTK = new javax.swing.JTable();
        btnExcel = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jplThongTin.setBackground(new java.awt.Color(255, 255, 255));
        jplThongTin.setBorder(javax.swing.BorderFactory.createTitledBorder("THÔNG TIN"));

        jLabel4.setText("HỌ TÊN");

        jLabel5.setText("GIỚI TÍNH");

        jLabel6.setText("SĐT");

        txtHoTen.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtHoTen.setForeground(new java.awt.Color(51, 51, 51));
        txtHoTen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        txtSdt.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtSdt.setForeground(new java.awt.Color(51, 51, 51));
        txtSdt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        jLabel7.setText("NGÀY SINH");

        rdBtnNam.setBackground(new java.awt.Color(255, 255, 255));
        grpGioiTinh.add(rdBtnNam);
        rdBtnNam.setSelected(true);
        rdBtnNam.setText("NAM");

        rdBtnNu.setBackground(new java.awt.Color(255, 255, 255));
        grpGioiTinh.add(rdBtnNu);
        rdBtnNu.setText("NỮ");

        jLabel8.setText("EMAIL");

        jLabel9.setText("CHỨC VỤ");

        jLabel10.setText("TRẠNG THÁI");

        jLabel11.setText("TÀI KHOẢN");

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

        lbAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbAnhMouseClicked(evt);
            }
        });

        txtNgaySinh.setDateFormatString("yyyy-MM-dd");
        txtNgaySinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel2.setText("ĐỊA CHỈ");

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtDiaChi.setForeground(new java.awt.Color(51, 51, 51));
        txtDiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        txtTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtTaiKhoan.setForeground(new java.awt.Color(51, 51, 51));
        txtTaiKhoan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        jLabel3.setText("MẬT KHẨU");

        txtMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtMatKhau.setForeground(new java.awt.Color(51, 51, 51));
        txtMatKhau.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        btnLamMoi.setBackground(new java.awt.Color(47, 85, 212));
        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-available-updates-20 (1).png"))); // NOI18N
        btnLamMoi.setText("LÀM MỚI");
        btnLamMoi.setBorderPainted(false);
        btnLamMoi.setFocusable(false);
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        txtEmail.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(51, 51, 51));
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        rdBtnNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        grpChucVu.add(rdBtnNhanVien);
        rdBtnNhanVien.setSelected(true);
        rdBtnNhanVien.setText("NHÂN VIÊN");

        rdBtnQuanLy.setBackground(new java.awt.Color(255, 255, 255));
        grpChucVu.add(rdBtnQuanLy);
        rdBtnQuanLy.setText("QUẢN LÝ");

        rdBtnLamViec.setBackground(new java.awt.Color(255, 255, 255));
        grpTrangThai.add(rdBtnLamViec);
        rdBtnLamViec.setSelected(true);
        rdBtnLamViec.setText("LÀM VIỆC");

        rdBtnNghiViec.setBackground(new java.awt.Color(255, 255, 255));
        grpTrangThai.add(rdBtnNghiViec);
        rdBtnNghiViec.setText("NGHỈ VIỆC");
        rdBtnNghiViec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdBtnNghiViecActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jplThongTinLayout = new javax.swing.GroupLayout(jplThongTin);
        jplThongTin.setLayout(jplThongTinLayout);
        jplThongTinLayout.setHorizontalGroup(
            jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplThongTinLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplThongTinLayout.createSequentialGroup()
                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jplThongTinLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(rdBtnNam)
                                .addGap(18, 18, 18)
                                .addComponent(rdBtnNu)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jplThongTinLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtHoTen)
                                    .addComponent(txtSdt, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(51, 51, 51))))
                    .addGroup(jplThongTinLayout.createSequentialGroup()
                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jplThongTinLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jplThongTinLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(32, 32, 32)
                                .addComponent(txtDiaChi)))
                        .addGap(51, 51, 51)))
                .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplThongTinLayout.createSequentialGroup()
                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel3))
                        .addGap(31, 31, 31)
                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTaiKhoan)
                            .addComponent(txtMatKhau)))
                    .addGroup(jplThongTinLayout.createSequentialGroup()
                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addGap(25, 25, 25)
                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jplThongTinLayout.createSequentialGroup()
                                .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdBtnNhanVien)
                                    .addComponent(rdBtnLamViec))
                                .addGap(18, 18, 18)
                                .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdBtnNghiViec)
                                    .addComponent(rdBtnQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtEmail))))
                .addGap(18, 18, 18)
                .addComponent(lbAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jplThongTinLayout.setVerticalGroup(
            jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplThongTinLayout.createSequentialGroup()
                .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplThongTinLayout.createSequentialGroup()
                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jplThongTinLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnThem)
                                .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jplThongTinLayout.createSequentialGroup()
                                        .addGap(58, 58, 58)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jplThongTinLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSua)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnXoa))))
                            .addGroup(jplThongTinLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jplThongTinLayout.createSequentialGroup()
                                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(rdBtnNam)
                                            .addComponent(rdBtnNu))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jplThongTinLayout.createSequentialGroup()
                                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(21, 21, 21)
                                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(rdBtnNhanVien)
                                            .addComponent(rdBtnQuanLy))
                                        .addGap(18, 18, 18)
                                        .addComponent(rdBtnNghiViec))))
                            .addGroup(jplThongTinLayout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdBtnLamViec))))
                        .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jplThongTinLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)))
                            .addGroup(jplThongTinLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jplThongTinLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jplThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jplThongTinLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3))
                                    .addGroup(jplThongTinLayout.createSequentialGroup()
                                        .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jplThongTinLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("LỌC"));

        jLabel13.setText("GIỚI TÍNH");
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        cbLocGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLocGioiTinhActionPerformed(evt);
            }
        });

        jLabel14.setText("CHỨC VỤ");
        jLabel14.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        cbLocChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLocChucVuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(cbLocGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(cbLocChucVu, 0, 201, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 12, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbLocGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbLocChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(4, 4, 4)))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("TÌM KIẾM"));

        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtTimKiem.setForeground(new java.awt.Color(51, 51, 51));
        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel15.setText("HỌ TÊN");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        tbLamViec.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tbLamViec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "HỌ TÊN", "GIỚI TÍNH", "SĐT", "NGÀY SINH", "ĐỊA CHỈ", "EMAIL", "CHỨC VỤ", "TRẠNG THÁI", "HÌNH ẢNH", "TÀI KHOẢN", "MẬT KHẨU"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbLamViec.setFocusable(false);
        tbLamViec.setGridColor(new java.awt.Color(47, 85, 212));
        tbLamViec.setRowHeight(25);
        tbLamViec.setShowGrid(true);
        tbLamViec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbLamViecMouseClicked(evt);
            }
        });
        tbLamViec.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                tbLamViecCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jScrollPane1.setViewportView(tbLamViec);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("ĐANG LÀM VIỆC", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        tbNghiViec.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tbNghiViec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "HỌ TÊN", "GIỚI TÍNH", "SĐT", "NGÀY SINH", "ĐỊA CHỈ", "EMAIL", "CHỨC VỤ", "TRẠNG THÁI", "HÌNH ẢNH", "TÀI KHOẢN", "MẬT KHẨU"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbNghiViec.setFocusable(false);
        tbNghiViec.setGridColor(new java.awt.Color(47, 85, 212));
        tbNghiViec.setRowHeight(25);
        tbNghiViec.setShowGrid(true);
        tbNghiViec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNghiViecMouseClicked(evt);
            }
        });
        tbNghiViec.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                tbNghiViecCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jScrollPane2.setViewportView(tbNghiViec);

        btnKhoiPhuc.setBackground(new java.awt.Color(47, 85, 212));
        btnKhoiPhuc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnKhoiPhuc.setForeground(new java.awt.Color(255, 255, 255));
        btnKhoiPhuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-available-updates-20 (1).png"))); // NOI18N
        btnKhoiPhuc.setText("KHÔI PHỤC");
        btnKhoiPhuc.setBorderPainted(false);
        btnKhoiPhuc.setFocusable(false);
        btnKhoiPhuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiPhucActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnKhoiPhuc, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnKhoiPhuc, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("NGHỈ VIỆC", jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane2)
                    .addComponent(jplThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jplThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23)
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        jTabbedPane2.getAccessibleContext().setAccessibleName("ĐANG LÀM VIỆC");
        jTabbedPane2.getAccessibleContext().setAccessibleDescription("");

        jTabbedPane1.addTab("QUẢN LÝ NHÂN VIÊN", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("TIÊU CHÍ"));

        jmonth.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jmonthMouseClicked(evt);
            }
        });
        jmonth.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jmonthPropertyChange(evt);
            }
        });

        jyear.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jyearPropertyChange(evt);
            }
        });

        rdoThang.setBackground(new java.awt.Color(255, 255, 255));
        grpThoiGian.add(rdoThang);
        rdoThang.setText("THÁNG");
        rdoThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoThangActionPerformed(evt);
            }
        });

        rdoNam.setBackground(new java.awt.Color(255, 255, 255));
        grpThoiGian.add(rdoNam);
        rdoNam.setText("NĂM");
        rdoNam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoNamMouseClicked(evt);
            }
        });

        rdoNgay.setBackground(new java.awt.Color(255, 255, 255));
        grpThoiGian.add(rdoNgay);
        rdoNgay.setText("NGÀY");

        jDateCuoi.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateCuoiPropertyChange(evt);
            }
        });

        jDateDau.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateDauPropertyChange(evt);
            }
        });

        jLabel1.setText("NGÀY  ĐẦU");

        jLabel12.setText("NGÀY CUỐI");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(rdoNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdoThang, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(rdoNam))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jmonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jyear, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 16, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateCuoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoThang)
                    .addComponent(rdoNam)
                    .addComponent(rdoNgay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(jDateCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jmonth, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jyear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        jTabbedPane3.setBackground(new java.awt.Color(255, 255, 255));

        pnlSanPhamTK.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane4.setBackground(new java.awt.Color(255, 255, 255));

        pnlSPTKCot.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlSPTKCotLayout = new javax.swing.GroupLayout(pnlSPTKCot);
        pnlSPTKCot.setLayout(pnlSPTKCotLayout);
        pnlSPTKCotLayout.setHorizontalGroup(
            pnlSPTKCotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlSPTKCotLayout.setVerticalGroup(
            pnlSPTKCotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane4.addTab("CỘT", pnlSPTKCot);

        pnlSPTKBang.setBackground(new java.awt.Color(255, 255, 255));

        tblSanPhamTK.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tblSanPhamTK.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "TÊN SẢN PHẨM", "SỐ LƯỢNG"
            }
        ));
        tblSanPhamTK.setFocusable(false);
        tblSanPhamTK.setGridColor(new java.awt.Color(47, 85, 212));
        tblSanPhamTK.setRowHeight(25);
        tblSanPhamTK.setShowGrid(true);
        jScrollPane3.setViewportView(tblSanPhamTK);

        javax.swing.GroupLayout pnlSPTKBangLayout = new javax.swing.GroupLayout(pnlSPTKBang);
        pnlSPTKBang.setLayout(pnlSPTKBangLayout);
        pnlSPTKBangLayout.setHorizontalGroup(
            pnlSPTKBangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSPTKBangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlSPTKBangLayout.setVerticalGroup(
            pnlSPTKBangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSPTKBangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane4.addTab("BẢNG", pnlSPTKBang);

        javax.swing.GroupLayout pnlSanPhamTKLayout = new javax.swing.GroupLayout(pnlSanPhamTK);
        pnlSanPhamTK.setLayout(pnlSanPhamTKLayout);
        pnlSanPhamTKLayout.setHorizontalGroup(
            pnlSanPhamTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamTKLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4)
                .addContainerGap())
        );
        pnlSanPhamTKLayout.setVerticalGroup(
            pnlSanPhamTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamTKLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("SẢN PHẨM", pnlSanPhamTK);

        jTabbedPane5.setBackground(new java.awt.Color(255, 255, 255));

        pnlDTTKCot.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlDTTKCotLayout = new javax.swing.GroupLayout(pnlDTTKCot);
        pnlDTTKCot.setLayout(pnlDTTKCotLayout);
        pnlDTTKCotLayout.setHorizontalGroup(
            pnlDTTKCotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlDTTKCotLayout.setVerticalGroup(
            pnlDTTKCotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("CỘT", pnlDTTKCot);

        pnlSPTKBang1.setBackground(new java.awt.Color(255, 255, 255));

        tblDoanhThuTK.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tblDoanhThuTK.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "THỜI GIAN", "DOANH THU"
            }
        ));
        tblDoanhThuTK.setFocusable(false);
        tblDoanhThuTK.setGridColor(new java.awt.Color(47, 85, 212));
        tblDoanhThuTK.setRowHeight(25);
        tblDoanhThuTK.setShowGrid(true);
        jScrollPane4.setViewportView(tblDoanhThuTK);

        javax.swing.GroupLayout pnlSPTKBang1Layout = new javax.swing.GroupLayout(pnlSPTKBang1);
        pnlSPTKBang1.setLayout(pnlSPTKBang1Layout);
        pnlSPTKBang1Layout.setHorizontalGroup(
            pnlSPTKBang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSPTKBang1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlSPTKBang1Layout.setVerticalGroup(
            pnlSPTKBang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSPTKBang1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane5.addTab("BẢNG", pnlSPTKBang1);

        javax.swing.GroupLayout pnlDoanhThuTKLayout = new javax.swing.GroupLayout(pnlDoanhThuTK);
        pnlDoanhThuTK.setLayout(pnlDoanhThuTKLayout);
        pnlDoanhThuTKLayout.setHorizontalGroup(
            pnlDoanhThuTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoanhThuTKLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane5)
                .addContainerGap())
        );
        pnlDoanhThuTKLayout.setVerticalGroup(
            pnlDoanhThuTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoanhThuTKLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("DOANH THU", pnlDoanhThuTK);

        btnExcel.setBackground(new java.awt.Color(47, 85, 212));
        btnExcel.setForeground(new java.awt.Color(255, 255, 255));
        btnExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-microsoft-excel-30.png"))); // NOI18N
        btnExcel.setText("Export");
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcel))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnExcel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.getAccessibleContext().setAccessibleName("Sản phẩm");

        jTabbedPane1.addTab("THỐNG KÊ", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        for (NhanVienResponse nhanVienResponse : service.getAll()) {
            if (nhanVienResponse.getTaiKhoan().equalsIgnoreCase(txtTaiKhoan.getText())) {
                JOptionPane.showMessageDialog(this, "Tài khoản này đã tồn tại");
                return;
            }
        }
        if (validated()) {
            JOptionPane.showMessageDialog(this, service.add(readForm()));
            list1 = service.getAllLam();
            showDataTblLam(list1);

            list2 = service.getAllNghi();
            showDataTblNghi(list2);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        rowSelected = tbLamViec.getSelectedRow();
        System.out.println(list1.get(rowSelected).getId());
        if (rowSelected >= 0) {
            for (NhanVienResponse nhanVienResponse : service.getAll()) {
                if (list1.get(rowSelected).getTaiKhoan().equalsIgnoreCase(txtTaiKhoan.getText())) {
                    break;
                }
                if (nhanVienResponse.getTaiKhoan().equalsIgnoreCase(txtTaiKhoan.getText())) {
                    JOptionPane.showMessageDialog(this, "Tài khoản này đã tồn tại");
                    return;
                }
            }
            int choice = JOptionPane.showConfirmDialog(this, "Confirm", "Bạn có chắc chắn không?", JOptionPane.YES_NO_CANCEL_OPTION);
            if (choice == 0) {
                int id = list1.get(rowSelected).getId();
                JOptionPane.showMessageDialog(this, service.update(readForm(), id));

                list1 = service.getAllLam();
                showDataTblLam(list1);

                list2 = service.getAllNghi();
                showDataTblNghi(list2);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Phải chọn nhân viên đang làm việc muốn sửa");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        rowSelected = tbLamViec.getSelectedRow();
        if (rowSelected >= 0) {
            int choice = JOptionPane.showConfirmDialog(this, "Confirm", "Bạn có chắc chắn không?", JOptionPane.YES_NO_CANCEL_OPTION);
            if (choice == 0) {
                JOptionPane.showMessageDialog(this, service.delete(list1.get(rowSelected).getId()));

                list1 = service.getAllLam();
                showDataTblLam(list1);

                list2 = service.getAllNghi();
                showDataTblNghi(list2);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Phải chọn nhân viên đang làm việc muốn xóa");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        clear();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void rdBtnNghiViecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdBtnNghiViecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdBtnNghiViecActionPerformed

    private void tbLamViecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLamViecMouseClicked
        rowSelected = tbLamViec.getSelectedRow();
        list1 = service.getAllLam();
        showDetail(rowSelected, list1);
    }//GEN-LAST:event_tbLamViecMouseClicked

    private void tbLamViecCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tbLamViecCaretPositionChanged

    }//GEN-LAST:event_tbLamViecCaretPositionChanged

    private void tbNghiViecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNghiViecMouseClicked
        rowSelected = tbNghiViec.getSelectedRow();
        list2 = service.getAllNghi();
        showDetail(rowSelected, list2);
    }//GEN-LAST:event_tbNghiViecMouseClicked

    private void tbNghiViecCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tbNghiViecCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tbNghiViecCaretPositionChanged

    private void btnKhoiPhucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiPhucActionPerformed
        rowSelected = tbNghiViec.getSelectedRow();
        if (rowSelected >= 0) {
            int choice = JOptionPane.showConfirmDialog(this, "Confirm", "Bạn có chắc chắn không?", JOptionPane.YES_NO_CANCEL_OPTION);
            if (choice == 0) {
                JOptionPane.showMessageDialog(this, service.recover(list2.get(rowSelected).getId()));

                list1 = service.getAllLam();
                showDataTblLam(list1);

                list2 = service.getAllNghi();
                showDataTblNghi(list2);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Phải chọn nhân viên muốn khôi phục");
        }
    }//GEN-LAST:event_btnKhoiPhucActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        list1 = service.findByNameNVLam(txtTimKiem.getText());
        showDataTblLam(list1);
        list2 = service.findByNameNVNghi(txtTimKiem.getText());
        showDataTblNghi(list2);
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void cbLocGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLocGioiTinhActionPerformed
        if (cbLocGioiTinh.getSelectedIndex() == 0) {
            list1 = service.getAllLam();
            showDataTblLam(list1);
            list2 = service.getAllNghi();
            showDataTblNghi(list2);
            return;
        }
        boolean gt = false;
        if (cbLocGioiTinh.getSelectedItem().toString().equalsIgnoreCase("Nam")) {
            gt = true;
        }
        list1 = service.findByGioiTinhNVLam(gt);
        showDataTblLam(list1);
        list2 = service.findByGioiTinhNVNghi(gt);
        showDataTblNghi(list2);
    }//GEN-LAST:event_cbLocGioiTinhActionPerformed

    private void cbLocChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLocChucVuActionPerformed
        if (cbLocChucVu.getSelectedIndex() == 0) {
            list1 = service.getAllLam();
            showDataTblLam(list1);
            list2 = service.getAllNghi();
            showDataTblNghi(list2);
            return;
        }
        boolean cv = false;
        if (cbLocChucVu.getSelectedItem().toString().equalsIgnoreCase("Nhân viên")) {
            cv = true;
        }
        list1 = service.findByChucVuNVLam(cv);
        showDataTblLam(list1);
        list2 = service.findByChucVuNVNghi(cv);
        showDataTblNghi(list2);
    }//GEN-LAST:event_cbLocChucVuActionPerformed

    private void lbAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAnhMouseClicked
        chooseImg();
    }//GEN-LAST:event_lbAnhMouseClicked

    private void rdoThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoThangActionPerformed

    }//GEN-LAST:event_rdoThangActionPerformed

    private void jmonthMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jmonthMouseClicked

    }//GEN-LAST:event_jmonthMouseClicked

    private void jmonthPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jmonthPropertyChange
        // TODO add your handling code here:
        //System.out.println(jmoChooser.getMonth() + 1 );
        //System.out.println(jyeChooser.getYear() + 1);
        if (rdoThang.isSelected()) {
            int month = jmonth.getMonth() + 1;
            int year = jyear.getYear();
            System.out.println("tháng: " + month + " năm: " + year);
            setDataToSanPhamTKThang(pnlSPTKCot, month, year);
            showDataToTableSanPhamTK(serviceSPTK.getSPTKThang(month, year));
        }
    }//GEN-LAST:event_jmonthPropertyChange

    private void jyearPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jyearPropertyChange
        // TODO add your handling code here:
        if (rdoNam.isSelected()) {
            int year = jyear.getYear();
            System.out.println("năm: " + year);
            setDataToSanPhamTKNam(pnlSPTKCot, year);
            showDataToTableSanPhamTK(serviceSPTK.getSPTKNam(year));
        }
        if (rdoThang.isSelected()) {
            int month = jmonth.getMonth() + 1;
            int year = jyear.getYear();
            System.out.println("tháng: " + month + " năm: " + year);
            
            setDataToSanPhamTKThang(pnlSPTKCot, month, year);
            showDataToTableSanPhamTK(serviceSPTK.getSPTKThang(month, year));
            
            setDataToDoanhThuTKThang(pnlDTTKCot, year);
            showDataToTableDoanhThuTK1(serviceDTTK.getDTTKThang(year));
        }
    }//GEN-LAST:event_jyearPropertyChange

    private void jDateCuoiPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateCuoiPropertyChange
        // TODO add your handling code here:
        if(rdoNgay.isSelected()) {
            try {
                LocalDateTime ngayDau = jDateDau.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atTime(LocalTime.of(0, 0, 0));
                LocalDateTime ngayCuoi = jDateCuoi.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atTime(LocalTime.of(23, 59, 59));
                System.out.println("Nháp : " + jDateDau.getDate());
                System.out.println("Nháp 2: " + ngayDau);
                
                setDataToSanPhamTKNgay(pnlSPTKCot, ngayDau, ngayCuoi); 
                showDataToTableSanPhamTK(serviceSPTK.getSPTKNgay(ngayDau, ngayCuoi));
            } catch (Exception e) {
                System.out.println("Tại null ý");
            }
            //LocalDateTime ngayDau = LocalDateTime.parse(jDateDau.getDate() + " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            //LocalDateTime ngayCuoi = LocalDateTime.parse(jDateCuoi.getDate() + " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));        
        }
    }//GEN-LAST:event_jDateCuoiPropertyChange

    private void jDateDauPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateDauPropertyChange
        // TODO add your handling code here:
        if(rdoNgay.isSelected()) {
            try {
                LocalDateTime ngayDau = jDateDau.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atTime(LocalTime.of(0, 0, 0));
                LocalDateTime ngayCuoi = jDateCuoi.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atTime(LocalTime.of(23, 59, 59));
                
                setDataToSanPhamTKNgay(pnlSPTKCot, ngayDau, ngayCuoi); 
                showDataToTableSanPhamTK(serviceSPTK.getSPTKNgay(ngayDau, ngayCuoi));
            } catch (Exception e) {
                System.out.println("Tại null ý");
            }     
        }
    }//GEN-LAST:event_jDateDauPropertyChange

    private void rdoNamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoNamMouseClicked
        // TODO add your handling code here:
        setDataToDoanhThuTKNam(pnlDTTKCot);
        showDataToTableDoanhThuTK1(serviceDTTK.getDTTKNam());
    }//GEN-LAST:event_rdoNamMouseClicked

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        int index = jTabbedPane3.getSelectedIndex();
        if(index == 0) {
            exportTable(tblSanPhamTK);
        } else {
            exportTable(tblDoanhThuTK);
        }
    }//GEN-LAST:event_btnExcelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcel;
    private javax.swing.JButton btnKhoiPhuc;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbLocChucVu;
    private javax.swing.JComboBox<String> cbLocGioiTinh;
    private javax.swing.ButtonGroup grpChucVu;
    private javax.swing.ButtonGroup grpGioiTinh;
    private javax.swing.ButtonGroup grpThoiGian;
    private javax.swing.ButtonGroup grpTrangThai;
    private com.toedter.calendar.JDateChooser jDateCuoi;
    private com.toedter.calendar.JDateChooser jDateDau;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private com.toedter.calendar.JMonthChooser jmonth;
    private javax.swing.JPanel jplThongTin;
    private com.toedter.calendar.JYearChooser jyear;
    private javax.swing.JLabel lbAnh;
    private javax.swing.JPanel pnlDTTKCot;
    private javax.swing.JPanel pnlDoanhThuTK;
    private javax.swing.JPanel pnlSPTKBang;
    private javax.swing.JPanel pnlSPTKBang1;
    private javax.swing.JPanel pnlSPTKCot;
    private javax.swing.JPanel pnlSanPhamTK;
    private javax.swing.JRadioButton rdBtnLamViec;
    private javax.swing.JRadioButton rdBtnNam;
    private javax.swing.JRadioButton rdBtnNghiViec;
    private javax.swing.JRadioButton rdBtnNhanVien;
    private javax.swing.JRadioButton rdBtnNu;
    private javax.swing.JRadioButton rdBtnQuanLy;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNgay;
    private javax.swing.JRadioButton rdoThang;
    private javax.swing.JTable tbLamViec;
    private javax.swing.JTable tbNghiViec;
    private javax.swing.JTable tblDoanhThuTK;
    private javax.swing.JTable tblSanPhamTK;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMatKhau;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTaiKhoan;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
