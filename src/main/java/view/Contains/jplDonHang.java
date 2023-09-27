package view.Contains;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import viewmodel.HoaDonChiTietResponse;

public class jplDonHang extends javax.swing.JPanel {

    public static int globalClickedRow = -1;
    private List<HoaDonChiTietResponse> hoaDonChiTietResponseList;
    private DefaultTableModel dtmHDChiTiet;

    public jplDonHang(int donSo) {
        initComponents();
        this.setName("Đơn" + donSo);
        hoaDonChiTietResponseList = new ArrayList<>();
        dtmHDChiTiet = (DefaultTableModel) tbHoaDonChiTiet.getModel();
    }

    public void load() {
        dtmHDChiTiet.setRowCount(0);
        hoaDonChiTietResponseList.forEach(hd -> dtmHDChiTiet.addRow(hd.toDataRow()));
    }

    public List<HoaDonChiTietResponse> getHoaDonChiTiets() {
        return hoaDonChiTietResponseList;
    }

    public void setHoaDonChiTiets(List<HoaDonChiTietResponse> hoaDonChiTiets) {
        this.hoaDonChiTietResponseList = hoaDonChiTiets;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbHoaDonChiTiet = new javax.swing.JTable();

        tbHoaDonChiTiet.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tbHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên ĐT", "Đơn giá", "Imei"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbHoaDonChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHoaDonChiTietMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbHoaDonChiTiet);
        if (tbHoaDonChiTiet.getColumnModel().getColumnCount() > 0) {
            tbHoaDonChiTiet.getColumnModel().getColumn(1).setResizable(false);
            tbHoaDonChiTiet.getColumnModel().getColumn(1).setPreferredWidth(70);
            tbHoaDonChiTiet.getColumnModel().getColumn(2).setResizable(false);
            tbHoaDonChiTiet.getColumnModel().getColumn(2).setPreferredWidth(70);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbHoaDonChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonChiTietMouseClicked
        int clickedRow = tbHoaDonChiTiet.getSelectedRow();
        globalClickedRow = clickedRow;
//        HoaDonChiTietResponse hdct = hoaDonChiTietResponseList.get(clickRow);
//        hoaDonChiTietResponseList.remove(hdct);

    }//GEN-LAST:event_tbHoaDonChiTietMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbHoaDonChiTiet;
    // End of variables declaration//GEN-END:variables
}
