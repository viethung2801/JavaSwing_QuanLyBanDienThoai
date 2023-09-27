/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Administrator
 */
public class IconRenderer extends DefaultTableCellRenderer {

    private ImageIcon[] icons; // Mảng các đối tượng ImageIcon

    public IconRenderer(ImageIcon[] icons) {
        this.icons = icons;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Đặt giá trị của ô trong cột "Icon" là đối tượng ImageIcon tương ứng với hàng
        ((DefaultTableCellRenderer) cellComponent).setIcon(icons[row]);
        ((DefaultTableCellRenderer) cellComponent).setText("");

        return cellComponent;
    }
}
