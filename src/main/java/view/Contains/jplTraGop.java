package view.Contains;

import java.awt.FlowLayout;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.PhieuTraGop;
import service.PhieuTraGopService;
import service.impl.PhieuTraGopServiceImpl;
import utility.PhieuTraGopUtil;
import view.Contains.tragop.ViewLichSuTraGop;
import viewmodel.PhieuTraGopViewModel;

public class jplTraGop extends javax.swing.JPanel {

    private DefaultTableModel dtm;
    private List<PhieuTraGopViewModel> listView;
    private PhieuTraGopService service;

    public jplTraGop() {
        initComponents();
        dtm = (DefaultTableModel) this.tblDanhSachTraGop.getModel();
        service = new PhieuTraGopServiceImpl();
        listView = service.getAll();
        showDataTable(listView);
    }

    private void showDataTable(List<PhieuTraGopViewModel> list) {
        dtm.setRowCount(0);
        for (PhieuTraGopViewModel phieuTraGopViewModel : list) {
            dtm.addRow(phieuTraGopViewModel.toDataRow());
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        mnuChiTiet = new javax.swing.JMenuItem();
        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        cbxTrangThai = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        dateNgayBatDau = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        dateNgayKetThuc = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSachTraGop = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnXuatDanhSach = new javax.swing.JButton();

        mnuChiTiet.setText("Xem chi tiết");
        mnuChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuChiTietActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mnuChiTiet);

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("Danh Sách Trả Góp");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/search.png"))); // NOI18N

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        cbxTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Chưa Hoàn thành", "Hoàn Thành" }));
        cbxTrangThai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTrangThaiItemStateChanged(evt);
            }
        });

        jLabel3.setText("Từ :");

        jLabel4.setText("Đến :");

        jButton1.setText("Tìm Kiếm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateNgayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateNgayKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                .addGap(26, 26, 26)
                .addComponent(cbxTrangThai, 0, 136, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSearch)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateNgayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateNgayKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbxTrangThai)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(2, 2, 2)))))
                .addContainerGap())
        );

        tblDanhSachTraGop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Ngày Tạo", "Mã Phiếu", "Khách Hàng", "Đơn Hàng", "Số Tiền", "Đã Trả", "Còn Nợ", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSachTraGop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachTraGopMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDanhSachTraGop);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnXuatDanhSach.setBackground(new java.awt.Color(47, 85, 212));
        btnXuatDanhSach.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXuatDanhSach.setForeground(new java.awt.Color(255, 255, 255));
        btnXuatDanhSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-microsoft-excel-30.png"))); // NOI18N
        btnXuatDanhSach.setText("Xuất Danh Sách");
        btnXuatDanhSach.setBorderPainted(false);
        btnXuatDanhSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatDanhSachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXuatDanhSach)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnXuatDanhSach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblDanhSachTraGopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachTraGopMouseClicked
        if (evt.getClickCount() == 2) {
            int row = tblDanhSachTraGop.getSelectedRow();
            if (row >= 0 && row < tblDanhSachTraGop.getRowCount()) {
                int id = listView.get(tblDanhSachTraGop.getSelectedRow()).getId();
                new ViewLichSuTraGop(id).setVisible(true);
            }
        }
        if (SwingUtilities.isRightMouseButton(evt)) {
            int row = tblDanhSachTraGop.rowAtPoint(evt.getPoint());
            if (row >= 0 && row < tblDanhSachTraGop.getRowCount()) {
                tblDanhSachTraGop.setRowSelectionInterval(row, row);//đánh dấu
                jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }

    }//GEN-LAST:event_tblDanhSachTraGopMouseClicked

    private void mnuChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuChiTietActionPerformed
        int id = listView.get(tblDanhSachTraGop.getSelectedRow()).getId();
        new ViewLichSuTraGop(id).setVisible(true);
    }//GEN-LAST:event_mnuChiTietActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        listView = service.getByString(txtSearch.getText().trim());
        showDataTable(listView);
    }//GEN-LAST:event_txtSearchKeyReleased

    private void cbxTrangThaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTrangThaiItemStateChanged
        // TODO add your handling code here:
//        LocalDate ngayBat
//        listView = service.getByTimeAndTrangThai(, cbxTrangThai.getSelectedIndex());
//        showDataTable(listView);
    }//GEN-LAST:event_cbxTrangThaiItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        LocalDate ngayBatDau;
        LocalDate ngayKetThuc;

        try {
            ngayBatDau = dateNgayBatDau.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không hợp lệ");
            return;
        }
        try {
            ngayKetThuc = dateNgayKetThuc.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc không hợp lệ");
            return;
        }

        listView = service.getByTimeAndTrangThai(ngayBatDau, ngayKetThuc, cbxTrangThai.getSelectedIndex());
        showDataTable(listView);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnXuatDanhSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatDanhSachActionPerformed
        // Tạo đối tượng JFileChooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Excel File");

        // Chỉ cho phép chọn tập tin với định dạng .xlsx
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel file (*.xlsx)", "xlsx");
        fileChooser.setFileFilter(filter);
        //set Tên cho File

        fileChooser.setSelectedFile(new File("DS Tra Gop " + LocalDate.now() + ".xlsx"));
        // Hiển thị hộp thoại chọn vị trí và tên tập tin
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String path = fileToSave.getAbsolutePath();
            JTable table = tblDanhSachTraGop;

            PhieuTraGopUtil.xuatEXCEL(table, path);
        }
    }//GEN-LAST:event_btnXuatDanhSachActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXuatDanhSach;
    private javax.swing.JComboBox<String> cbxTrangThai;
    private com.toedter.calendar.JDateChooser dateNgayBatDau;
    private com.toedter.calendar.JDateChooser dateNgayKetThuc;
    private javax.swing.JButton jButton1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem mnuChiTiet;
    private javax.swing.JTable tblDanhSachTraGop;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
