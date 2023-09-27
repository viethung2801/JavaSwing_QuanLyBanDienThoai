package view.Contains.EntitySanPham;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.Imei;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.ImeiService;
import service.impl.ImeiServiceImpl;
import view.Contains.jplSanPham;
import viewmodel.ImeiResponse;

public class ThemImei extends javax.swing.JFrame {

    public int idCurrentDienThoai = 0;
    private ImeiService imeiService;
    private List<ImeiResponse> chuaBanImeiResponseList;
    private List<ImeiResponse> daBanImeiResponseList;
    private DefaultTableModel dtmImeiChuaBan;
    private DefaultTableModel dtmImeiDaBan;

    // Constructor 1
    public ThemImei() {
        initComponents();
        setLocationRelativeTo(null);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                jplSanPham.showImeiComboBox(idCurrentDienThoai);
            }
        });

        imeiService = new ImeiServiceImpl();
        chuaBanImeiResponseList = new ArrayList<>();
        daBanImeiResponseList = new ArrayList<>();
        dtmImeiChuaBan = (DefaultTableModel) tbImei.getModel();
        dtmImeiDaBan = (DefaultTableModel) tbImei1.getModel();

//        imeiService.deleteImeiWithDienThoaiNull();
        chuaBanImeiResponseList = imeiService.getResponsesWithDienThoaiNull();
        showImeiTableChuaBan(chuaBanImeiResponseList);
    }

    // Constructor 2
    public ThemImei(int idCurrentDienThoai) {
        initComponents();
        setLocationRelativeTo(null);
        this.idCurrentDienThoai = idCurrentDienThoai;

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                jplSanPham.showImeiComboBox(idCurrentDienThoai);
            }
        });

        imeiService = new ImeiServiceImpl();
        chuaBanImeiResponseList = new ArrayList<>();
        daBanImeiResponseList = new ArrayList<>();
        dtmImeiChuaBan = (DefaultTableModel) tbImei.getModel();
        dtmImeiDaBan = (DefaultTableModel) tbImei1.getModel();

        // hiện thị ds IMEI chưa bán
        chuaBanImeiResponseList = imeiService.getResponsesByIdDienThoaiAndStatus(idCurrentDienThoai, 0);
        // imeisWithDienThoaiNull là các imei vừa được thêm nhưng chưa set field dienThoai vào imei đó
        List<ImeiResponse> imeisWithDienThoaiNull = imeiService.getResponsesWithDienThoaiNull();
        chuaBanImeiResponseList.addAll(imeisWithDienThoaiNull);
        showImeiTableChuaBan(chuaBanImeiResponseList);

        // hiện thị ds IMEI đã bán
        daBanImeiResponseList = imeiService.getResponsesByIdDienThoaiAndStatus(idCurrentDienThoai, 1);
        showImeiTableDaBan(daBanImeiResponseList);
    }

    // 1
    private void showImeiTableChuaBan(List<ImeiResponse> imeis) {
        dtmImeiChuaBan.setRowCount(0);
        imeis.forEach(i -> dtmImeiChuaBan.addRow(i.toDataRow()));
        lbTongSo.setText(String.valueOf(imeis.size()));
    }

    // 2
    private void showImeiTableDaBan(List<ImeiResponse> imeis) {
        dtmImeiDaBan.setRowCount(0);
        imeis.forEach(i -> dtmImeiDaBan.addRow(i.toDataRow()));
        lbTongSo1.setText(String.valueOf(imeis.size()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        tab = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbImei = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lbTongSo = new javax.swing.JLabel();
        btnImportExcel = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lbTongSo1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbImei1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtImei = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("IMEI");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tab.setBackground(new java.awt.Color(255, 255, 255));
        tab.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabStateChanged(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tbImei.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Số IMEI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbImei.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbImeiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbImei);
        if (tbImei.getColumnModel().getColumnCount() > 0) {
            tbImei.getColumnModel().getColumn(0).setMinWidth(70);
            tbImei.getColumnModel().getColumn(0).setPreferredWidth(70);
            tbImei.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        jLabel3.setText("TÌM KIẾM:");

        jTextField3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        jLabel4.setText("TỔNG:");

        lbTongSo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTongSo.setForeground(new java.awt.Color(255, 51, 51));
        lbTongSo.setText("0");

        btnImportExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-microsoft-excel-25.png"))); // NOI18N
        btnImportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTongSo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnImportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel3)
                                .addGap(2, 2, 2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnImportExcel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbTongSo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        tab.addTab("Chưa bán", jPanel1);

        jLabel5.setText("TÌM KIẾM:");

        jTextField4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        jLabel6.setText("TỔNG:");

        lbTongSo1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTongSo1.setForeground(new java.awt.Color(255, 51, 51));
        lbTongSo1.setText("0");

        tbImei1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Số IMEI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbImei1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbImei1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbImei1);
        if (tbImei1.getColumnModel().getColumnCount() > 0) {
            tbImei1.getColumnModel().getColumn(0).setMinWidth(70);
            tbImei1.getColumnModel().getColumn(0).setPreferredWidth(70);
            tbImei1.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 437, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(jLabel5)
                            .addGap(18, 18, 18)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbTongSo1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(73, 73, 73))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(15, 15, 15)
                                    .addComponent(jLabel5)
                                    .addGap(2, 2, 2))
                                .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(lbTongSo1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        tab.addTab("Đã bán", jPanel2);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("THÔNG TIN"));

        jLabel1.setText("IMEI:");

        txtImei.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));

        btnThem.setBackground(new java.awt.Color(47, 85, 212));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-add-new-20.png"))); // NOI18N
        btnThem.setText("THÊM");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(47, 85, 212));
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-trash-20.png"))); // NOI18N
        btnXoa.setText("XÓA");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(47, 85, 212));
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-pencil-20 WHITE.png"))); // NOI18N
        btnSua.setText("SỬA");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(47, 85, 212));
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-new-20.png"))); // NOI18N
        btnLamMoi.setText("MỚI");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 1, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtImei, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnThem))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(16, 16, 16)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(txtImei, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnLamMoi))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua)
                    .addComponent(btnXoa))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Thêm Imei?", "Xác nhận thêm Imei", JOptionPane.YES_NO_OPTION);
        if (confirm != 0) {
            return;
        }

        String imeiStr = txtImei.getText().trim();
        String checkResult = checkInput(0, imeiStr);
        if (!checkResult.equals("")) {
            JOptionPane.showMessageDialog(this, checkResult);
            return;
        }

        Imei imei = new Imei();
        imei.setImei(imeiStr);
        imei.setTrangThai(0);

        String addResult = imeiService.add(imei);
        JOptionPane.showMessageDialog(this, addResult);

        lamMoiForm();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Xóa Imei?", "Xác nhận xóa Imei", JOptionPane.YES_NO_OPTION);
        if (confirm != 0) {
            return;
        }

        int clickedRow = tbImei.getSelectedRow();
        if (clickedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn imei trước khi xóa!");
            return;
        }

        ImeiResponse selectedImeiResponse = chuaBanImeiResponseList.get(clickedRow);
        String deleteResult = imeiService.delete(selectedImeiResponse);
        JOptionPane.showMessageDialog(this, deleteResult);

        lamMoiForm();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        lamMoiForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void tbImeiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbImeiMouseClicked
        int clickedRow = tbImei.getSelectedRow();
        if (clickedRow < 0) {
            return;
        }

        ImeiResponse imeiResponse = chuaBanImeiResponseList.get(clickedRow);
        txtImei.setText(imeiResponse.getImei());
    }//GEN-LAST:event_tbImeiMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Sửa Imei?", "Xác nhận sửa Imei", JOptionPane.YES_NO_OPTION);
        if (confirm != 0) {
            return;
        }

        int clickedRow = tbImei.getSelectedRow();
        if (clickedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn imei trước khi sửa!");
            return;
        }

        String imeiStr = txtImei.getText().trim();
        String checkResult = checkInput(0, imeiStr);
        if (!checkResult.equals("")) {
            JOptionPane.showMessageDialog(this, checkResult);
            return;
        }

        ImeiResponse selectedImeiResponse = chuaBanImeiResponseList.get(clickedRow);
        selectedImeiResponse.setImei(imeiStr);

        String updateResult = imeiService.updateImeiStr(selectedImeiResponse);
        JOptionPane.showMessageDialog(this, updateResult);

        lamMoiForm();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnImportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportExcelActionPerformed
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(null);
        int total = 0;
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                // C:\Users\T490\OneDrive - Hanoi University of Science and Technology\Documents\NetBeansProjects\phone-imei\imeis-1.xlsx
                File file = chooser.getSelectedFile();
                FileInputStream fis = new FileInputStream(file);

                XSSFWorkbook wb = new XSSFWorkbook(fis);
                XSSFSheet sheet = wb.getSheetAt(0);
                Iterator<Row> itr = sheet.iterator();
                
                while (itr.hasNext()) {
                    Row row = itr.next();
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        switch (cell.getCellType()) {
                            case STRING: {
                                String imeiStr = cell.getStringCellValue().substring(1);
                                if (imeiService.getByImei(imeiStr) == null) {
                                    Imei imei = new Imei();
                                    imei.setImei(imeiStr);
                                    imeiService.add(imei);
                                    total++;
                                }
                                break;
                            }
                        }
                    }
                }
                lamMoiForm();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                JOptionPane.showMessageDialog(this, total + " imei đã được thêm từ file excel.\nNhững imei không hợp lệ đã bị loại");
            }
        }
    }//GEN-LAST:event_btnImportExcelActionPerformed

    private void tbImei1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbImei1MouseClicked
        int clickedRow = tbImei1.getSelectedRow();
        if (clickedRow < 0) {
            return;
        }

        ImeiResponse imeiResponse = daBanImeiResponseList.get(clickedRow);
        txtImei.setText(imeiResponse.getImei());
    }//GEN-LAST:event_tbImei1MouseClicked

    private void tabStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabStateChanged
        tab.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = tab.getSelectedIndex();
                if (index == 0) {
                    setButtons(true);
                } else {
                    setButtons(false);
                }
            }
        });
    }//GEN-LAST:event_tabStateChanged

    private void setButtons(boolean boo) {
        btnLamMoi.setEnabled(boo);
        btnThem.setEnabled(boo);
        btnSua.setEnabled(boo);
        btnXoa.setEnabled(boo);
    }

    private void lamMoiForm() {
        if (idCurrentDienThoai == 0) {
            chuaBanImeiResponseList = imeiService.getResponsesWithDienThoaiNull();
            showImeiTableChuaBan(chuaBanImeiResponseList);
            txtImei.setText("");
        } else {
            chuaBanImeiResponseList = imeiService.getResponsesByIdDienThoaiAndStatus(idCurrentDienThoai, 0);
            List<ImeiResponse> noneDienThoaiImeis = imeiService.getResponsesWithDienThoaiNull();
            chuaBanImeiResponseList.addAll(noneDienThoaiImeis);
            showImeiTableChuaBan(chuaBanImeiResponseList);
            txtImei.setText("");
        }
    }

    private String checkInput(int id, String imeiStr) {
        String message = "";
        if (imeiStr.isBlank()) {
            message += "Imei không được để trống!";
            return message;
        } else {

            // Check unique imei
            Imei imei = imeiService.getByImei(imeiStr);
            if (imei != null) {
                if (id == 0) {
                    message += "Imei đã bị trùng!";
                    return message;
                } else if (id > 0) {
                    if (imei.getId() != id) {
                        message += "Imei đã bị trùng!";
                        return message;
                    }
                }
            }

            // Check pattern
            String pattern = "[0-9]{10,15}";
            if (!imeiStr.matches(pattern)) {
                message += "Imei sai định dạng!\n";
                return message;
            }
        }
        return message;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThemImei().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImportExcel;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel lbTongSo;
    private javax.swing.JLabel lbTongSo1;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTable tbImei;
    private javax.swing.JTable tbImei1;
    private javax.swing.JTextField txtImei;
    // End of variables declaration//GEN-END:variables
}
