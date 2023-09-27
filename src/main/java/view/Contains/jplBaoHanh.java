package view.Contains;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.LoaiBaoHanh;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import service.PhieuBaoHanhService;
import service.impl.PhieuBaoHanhServiceImpl;
import view.Contains.entitybaohanh.QuanLyLoaiBaoHanh;
import viewmodel.PhieuBaoHanhResponse;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class jplBaoHanh extends javax.swing.JPanel {

    DefaultTableModel dtm;
    DefaultTableModel dtm1;
    PhieuBaoHanhService service;
    DefaultComboBoxModel dcbm;

    public jplBaoHanh() {
        initComponents();
        viewTable();
        dtm = new DefaultTableModel();
        dtm = (DefaultTableModel) this.tbCTPBH.getModel();
        dtm1 = new DefaultTableModel();
        dtm1 = (DefaultTableModel) this.tbLBH.getModel();
        service = new PhieuBaoHanhServiceImpl();
        dcbm = new DefaultComboBoxModel();
        dcbm = (DefaultComboBoxModel) this.cbbLBH.getModel();
        showDataTable(service.getAll());
        showComboboxData(service.getAllLoaiBaoHanh());
        viewTable();
    }

    private void viewTable() {
        JTableHeader Theader = tbCTPBH.getTableHeader();
        JTableHeader TheaderLBH = tbLBH.getTableHeader();

        Theader.setFont(new Font("tahoma", Font.BOLD, 15));
        Theader.setBackground(new Color(47, 85, 212));
        Theader.setForeground(Color.white);
        TheaderLBH.setFont(new Font("tahoma", Font.BOLD, 15));
        TheaderLBH.setBackground(new Color(47, 85, 212));
        TheaderLBH.setForeground(Color.white);

    }

    private void showComboboxData(List<String> lbh) {
        cbbLBH.removeAllItems();
        for (String cbb : lbh) {
            cbbLBH.addItem(cbb);
        }
    }

    private void showDataTable(List<PhieuBaoHanhResponse> list) {
        dtm.setRowCount(0);
        for (PhieuBaoHanhResponse pbh : list) {
            dtm.addRow(pbh.toRowData());
        }
    }

    private void showDataLBH(int id) {
        dtm1.setRowCount(0);
        for (LoaiBaoHanh lbh : service.getAllLBH(id)) {
            dtm1.addRow(lbh.toRowData());
        }
    }

    private void sendEmailWithAttachment(String recipientEmail, String subject, String body, String filePath) throws MessagingException, IOException {
        // Bật giao thức TLS 1.2
        System.setProperty("https.protocols", "TLSv1.2");

        // Cấu hình thông tin email server
        String senderEmail = "hieupvph29564@fpt.edu.vn";
        String senderPassword = "Phieu2002";
        String emailSMTPserver = "smtp.gmail.com";
        String emailServerPort = "587";

        Properties props = new Properties();
        props.put("mail.smtp.host", emailSMTPserver);
        props.put("mail.smtp.port", emailServerPort);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        // Tạo session gửi email
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        // Tạo nội dung email
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
        message.setSubject(subject);

        // Tạo phần thân email
        MimeMultipart multipart = new MimeMultipart();
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(body, "text/html; charset=utf-8");
        multipart.addBodyPart(messageBodyPart);

        // Đính kèm file Excel vào email
        MimeBodyPart attachmentPart = new MimeBodyPart();
        attachmentPart.attachFile(new File(filePath));
        multipart.addBodyPart(attachmentPart);

        // Thiết lập phần thân cho email
        message.setContent(multipart);

        // Gửi email
        Transport.send(message);
        System.out.println("Gửi thành công");
    }

    private void exportExcel() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");
        int rowNum = 0;
        for (int i = 0; i < tbCTPBH.getRowCount(); i++) {
            Row row = sheet.createRow(rowNum++);
            for (int j = 0; j < tbCTPBH.getColumnCount(); j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(tbCTPBH.getValueAt(i, j).toString());
            }
        }
        //xuất file excel ra desktop
        FileOutputStream outputStream;
        try {
            System.out.println("Creating Excel file...");
            outputStream = new FileOutputStream("C:\\Users\\virus\\OneDrive\\Máy tính\\PhieuBaoHanh.xlsx");
            workbook.write(outputStream);
            outputStream.close();
            JOptionPane.showMessageDialog(this, "Excel file created successfully.");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbCTPBH = new javax.swing.JTable();
        btnLoad = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        cbbLBH = new javax.swing.JComboBox<>();
        btnAddLoaiBaoHanh = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rdConHan = new javax.swing.JRadioButton();
        rdHetHan = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtSearchTenKH = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnImportExcel = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnUpdateMota = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbLBH = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tbCTPBH.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        tbCTPBH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbCTPBH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Imei", "Tên KH", "SĐT", "Tên ĐT", "Thời Hạn", "Ngày Mua", "Ngày Hết Hạn", "Mô Tả", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbCTPBH.setRowHeight(25);
        tbCTPBH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCTPBHMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbCTPBH);
        if (tbCTPBH.getColumnModel().getColumnCount() > 0) {
            tbCTPBH.getColumnModel().getColumn(0).setPreferredWidth(10);
        }

        btnLoad.setBackground(new java.awt.Color(47, 85, 212));
        btnLoad.setForeground(new java.awt.Color(255, 255, 255));
        btnLoad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-available-updates-20 (1).png"))); // NOI18N
        btnLoad.setText("Load");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        jLabel5.setText("Danh Sách Phiếu Bảo Hành");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLoad)))
                .addGap(23, 23, 23))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLoad)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("LOẠI BẢO HÀNH"));

        btnAddLoaiBaoHanh.setBackground(new java.awt.Color(47, 85, 212));
        btnAddLoaiBaoHanh.setForeground(new java.awt.Color(255, 255, 255));
        btnAddLoaiBaoHanh.setText("Quản Lý Loại Bảo Hành");
        btnAddLoaiBaoHanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddLoaiBaoHanhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbLBH, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddLoaiBaoHanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(cbbLBH, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAddLoaiBaoHanh, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc Phiếu Bảo Hành"));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Trạng Thái:");

        rdConHan.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdConHan);
        rdConHan.setText("Còn Hạn");
        rdConHan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdConHanMouseClicked(evt);
            }
        });
        rdConHan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdConHanActionPerformed(evt);
            }
        });

        rdHetHan.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdHetHan);
        rdHetHan.setText("Hết Hạn");
        rdHetHan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdHetHanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(47, 47, 47)
                .addComponent(rdConHan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(rdHetHan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(rdConHan)
                    .addComponent(rdHetHan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm Kiếm Phiếu Bảo Hành"));

        jLabel2.setText("Số ĐT KH:");

        txtSearchTenKH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(47, 85, 212)));
        txtSearchTenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchTenKHActionPerformed(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(47, 85, 212));
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Tìm Kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtSearchTenKH)
                .addGap(18, 18, 18)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        btnImportExcel.setBackground(new java.awt.Color(47, 85, 212));
        btnImportExcel.setForeground(new java.awt.Color(255, 255, 255));
        btnImportExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-microsoft-excel-30.png"))); // NOI18N
        btnImportExcel.setText("Export");
        btnImportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportExcelActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(47, 85, 212));
        jLabel3.setText("BẢO HÀNH");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cập nhật Phiếu Bảo Hành"));

        btnUpdateMota.setText("Cập Nhật");
        btnUpdateMota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateMotaActionPerformed(evt);
            }
        });

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        jLabel4.setText("Mô Tả:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(btnUpdateMota)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(btnUpdateMota)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Loại Bảo Hành"));

        tbLBH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Tên Loại Bảo Hành", "Điều Kiện BH"
            }
        ));
        jScrollPane4.setViewportView(tbLBH);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImportExcel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(33, 33, 33))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(btnImportExcel)))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnImportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportExcelActionPerformed
        showDataTable(service.getAll());
        int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn sau khi xuất file excel thì gửi báo cáo cho quản lý qua mail hay không?");
        if (choice == 0) {
            exportExcel();
            try {
                String path = "C:\\Users\\virus\\OneDrive\\Máy tính\\PhieuBaoHanh.xlsx";
                sendEmailWithAttachment("binhpvph29510@fpt.edu.vn", "Báo cáo phiếu bảo hành", "Danh sách phiếu bảo hành", path);
                JOptionPane.showMessageDialog(this, "Gửi báo cáo thành công!");
            } catch (MessagingException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi cố gắng gửi file hoặc mail");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi cố gắng cấu hình, khởi tạo nội dung mail");
            }
        } else if (choice == 1) {
            exportExcel();
        }
    }//GEN-LAST:event_btnImportExcelActionPerformed

    private void btnAddLoaiBaoHanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddLoaiBaoHanhActionPerformed
        // TODO add your handling code here:
        QuanLyLoaiBaoHanh qllbh = new QuanLyLoaiBaoHanh();
        qllbh.setVisible(true);
    }//GEN-LAST:event_btnAddLoaiBaoHanhActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        if (txtSearchTenKH.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Không được để trống tìm kiếm");
        } else {
            String pattern = "^0\\d{9}$";
            if (!txtSearchTenKH.getText().matches(pattern)) {
                JOptionPane.showMessageDialog(this, "Số điện thoại phải bắt đầu bằng 0 và có 10 kí tự số");
            } else {
                if (service.getAllListSearch(txtSearchTenKH.getText()).isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng!");
                    dtm.setRowCount(0);
                } else {
                    showDataTable(service.getAllListSearch(txtSearchTenKH.getText()));
                }
            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        // TODO add your handling code here:
        showDataTable(service.getAll());
        showComboboxData(service.getAllLoaiBaoHanh());
        dtm1.setRowCount(0);
        txtMoTa.setText("");
        txtSearchTenKH.setText("");
        buttonGroup1.clearSelection();
    }//GEN-LAST:event_btnLoadActionPerformed

    private void rdConHanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdConHanMouseClicked
        // TODO add your handling code here:
        List<PhieuBaoHanhResponse> listPBH = service.getAll();
        List<PhieuBaoHanhResponse> list = new ArrayList<>();
        if (listPBH.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có phiếu bảo hành nào ở trong cửa hàng!!");
        } else {
            for (PhieuBaoHanhResponse pbh : listPBH) {
                if ("Còn Hạn".equals(pbh.getStatus(pbh.getNgayHetHan()))) {
                    list.add(pbh);
                }
            }
            showDataTable(list);
        }
    }//GEN-LAST:event_rdConHanMouseClicked

    private void rdHetHanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdHetHanMouseClicked
        // TODO add your handling code here:
        List<PhieuBaoHanhResponse> listPBH = service.getAll();
        List<PhieuBaoHanhResponse> list = new ArrayList<>();
        if (listPBH.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có phiếu bảo hành nào ở trong cửa hàng!!");
        } else {
            for (PhieuBaoHanhResponse pbh : listPBH) {
                if ("Hết Hạn".equals(pbh.getStatus(pbh.getNgayHetHan()))) {
                    list.add(pbh);
                }
            }
            showDataTable(list);
        }
    }//GEN-LAST:event_rdHetHanMouseClicked

    private void txtSearchTenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchTenKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchTenKHActionPerformed

    private void rdConHanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdConHanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdConHanActionPerformed

    private void btnUpdateMotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateMotaActionPerformed
        // TODO add your handling code here:
        int index = tbCTPBH.getSelectedRow();
        int id = Integer.parseInt(tbCTPBH.getValueAt(index, 0).toString());
        int choice = JOptionPane.showConfirmDialog(null, "Bạn muốn cập nhật mô tả của Phiếu Bảo Hành?");
        if (choice == 0) {
            if (txtMoTa.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "Không được để trống mô tả!");
            } else {
                PhieuBaoHanhResponse pbh = service.getPBHByID(id);
                pbh.setMoTa(txtMoTa.getText());
                JOptionPane.showMessageDialog(null, service.updateMoTa(pbh, id));
                showDataTable(service.getAll());
            }
        } else {
            JOptionPane.showMessageDialog(rdConHan, "Đã huỷ");
        }
    }//GEN-LAST:event_btnUpdateMotaActionPerformed

    private void tbCTPBHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCTPBHMouseClicked
        // TODO add your handling code here:
        int index = tbCTPBH.getSelectedRow();
        int id = Integer.parseInt(tbCTPBH.getValueAt(index, 0).toString());
        showDataLBH(id);
    }//GEN-LAST:event_tbCTPBHMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddLoaiBaoHanh;
    private javax.swing.JButton btnImportExcel;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdateMota;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbLBH;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JRadioButton rdConHan;
    private javax.swing.JRadioButton rdHetHan;
    private javax.swing.JTable tbCTPBH;
    private javax.swing.JTable tbLBH;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtSearchTenKH;
    // End of variables declaration//GEN-END:variables
}
