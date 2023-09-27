/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.text.pdf.BaseFont;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import javax.swing.JTable;
import model.LichSuTraGop;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Administrator
 */
public class PhieuTraGopUtil {

    public static String convertVND(long value) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        String fomatValue = numberFormat.format(value);
        return fomatValue;
    }

    public static void xuatEXCEL(JTable table, String filePath) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Sheet1");
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
            // Ghi tệp Excel
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void xuatPhieuThuPDF(LichSuTraGop lstg, String filePath) {
//        Document document = null;
//        try {
//            document = new Document();
//            PdfWriter.getInstance(document, new FileOutputStream(filePath));
//            document.open();
//
//            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
//            Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
//
//            Paragraph title = new Paragraph("PHIEU THU", boldFont);
//            title.setAlignment(Paragraph.ALIGN_CENTER);
//            document.add(title);
//
//            document.add(new Paragraph("\n"));
//
//            Paragraph maPhieu = new Paragraph("Ma phieu: " + lstg.getMa(), normalFont);
//            document.add(maPhieu);
//
//            document.add(new Paragraph("\n"));
//
//            Paragraph date = new Paragraph("Ngay: " + lstg.getNgayThanhToan(), normalFont);
//            document.add(date);
//
//            document.add(new Paragraph("\n"));
//
//            String khachHangStr = lstg.getPhieuTraGop().getHoaDon().getKhachHang().getHoTen() + " - " + lstg.getPhieuTraGop().getHoaDon().getKhachHang().getSdt();
//            khachHangStr = Normalizer.normalize(khachHangStr, Normalizer.Form.NFD)
//                    .replaceAll("\\p{M}", "");
//            Paragraph customerInfo = new Paragraph("Khach hang: " + khachHangStr, normalFont);
//            document.add(customerInfo);
//
//            document.add(new Paragraph("\n"));
//
//            Paragraph soTien = new Paragraph("So tien: " + convertVND(lstg.getTongTien()) + " VND", normalFont);
//            document.add(soTien);
//
//            document.add(new Paragraph("\n"));
//
//            String ghiChuStr = lstg.getGhiChu();
//            ghiChuStr = Normalizer.normalize(ghiChuStr, Normalizer.Form.NFD)
//                    .replaceAll("\\p{M}", "");
//            Paragraph ghiChu = new Paragraph("Ghi chu: " + ghiChuStr, normalFont);
//            document.add(ghiChu);
//
//            document.add(new Paragraph("\n"));
//
//            Paragraph chuKy = new Paragraph("                 Khach Hang                                              Dai Dien", normalFont);
//            document.add(chuKy);
//
//            document.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            document.close();
//        }
//    }
    public String pathUnicode = getClass().getClassLoader().getResource("font/unicode.ttf").getPath();

    public void exportLichSuTraGop(LichSuTraGop lstg, String filePath) {
        try {
            System.out.println(pathUnicode);
            System.out.println(filePath);
            File file = new File(filePath);
            com.itextpdf.kernel.pdf.PdfWriter pdfWriter = new com.itextpdf.kernel.pdf.PdfWriter(filePath);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdfDocument);
            float col = 280f;
            float columWidth[] = {col, col};

            PdfFont font = PdfFontFactory.createFont(pathUnicode, BaseFont.IDENTITY_H);

            Table table = new Table(columWidth);
            table.setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE);
            table.setFont(font);

            table.addCell(new com.itextpdf.layout.element.Cell().add("PHONE POLY").setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setFontSize(30f)
                    .setBorder(Border.NO_BORDER));
            table.addCell(new com.itextpdf.layout.element.Cell().add("Mã Phiếu thu: " + lstg.getMa() + "\nPHONE POLY").setTextAlignment(TextAlignment.RIGHT)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setBorder(Border.NO_BORDER)
                    .setMarginRight(10f));

            float colWidth[] = {80, 230, 200, 200};

            // Thông tin khách hàng đặt hàng
            Table khachHangTable = new Table(colWidth);
            khachHangTable.setFont(font);
            khachHangTable.addCell(new com.itextpdf.layout.element.Cell(0, 4).add("Thông tin khách hàng").setBold().setBorder(Border.NO_BORDER));

            khachHangTable.addCell(new com.itextpdf.layout.element.Cell().add("Họ tên:").setBorder(Border.NO_BORDER));
            khachHangTable.addCell(new com.itextpdf.layout.element.Cell().add(lstg.getPhieuTraGop().getHoaDon().getKhachHang().getHoTen()).setBorder(Border.NO_BORDER));
            khachHangTable.addCell(new com.itextpdf.layout.element.Cell().add("SĐT:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            khachHangTable.addCell(new com.itextpdf.layout.element.Cell().add(lstg.getPhieuTraGop().getHoaDon().getKhachHang().getSdt()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            khachHangTable.addCell(new com.itextpdf.layout.element.Cell().add("Email:").setBorder(Border.NO_BORDER));
            khachHangTable.addCell(new com.itextpdf.layout.element.Cell().add(lstg.getPhieuTraGop().getHoaDon().getKhachHang().getEmail()).setBorder(Border.NO_BORDER));
            khachHangTable.addCell(new com.itextpdf.layout.element.Cell().add("Địa chỉ:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            khachHangTable.addCell(new com.itextpdf.layout.element.Cell().add(lstg.getPhieuTraGop().getHoaDon().getKhachHang().getDiaChi()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));

            Table phieuThuTable = new Table(colWidth);
            phieuThuTable.setFont(font);
            phieuThuTable.addCell(new com.itextpdf.layout.element.Cell(0, 4).add("Thông tin Phiếu thu").setBold().setBorder(Border.NO_BORDER));

            phieuThuTable.addCell(new com.itextpdf.layout.element.Cell().add("Ngày: ").setBorder(Border.NO_BORDER));
            phieuThuTable.addCell(new com.itextpdf.layout.element.Cell().add(lstg.getNgayThanhToan().toString()).setBorder(Border.NO_BORDER));
            phieuThuTable.addCell(new com.itextpdf.layout.element.Cell().add("Ghi chú:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            phieuThuTable.addCell(new com.itextpdf.layout.element.Cell().add(lstg.getGhiChu()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            phieuThuTable.addCell(new com.itextpdf.layout.element.Cell().add("Số tiền:").setBorder(Border.NO_BORDER));
            phieuThuTable.addCell(new com.itextpdf.layout.element.Cell().add(convertVND(lstg.getTongTien())).setBorder(Border.NO_BORDER));
            phieuThuTable.addCell(new com.itextpdf.layout.element.Cell().add("Còn nợ:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            phieuThuTable.addCell(new com.itextpdf.layout.element.Cell().add(convertVND(lstg.getPhieuTraGop().getTongPhaiTra() - lstg.getPhieuTraGop().getTongTienDaTra())).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));

            // Lưu ý
//            float colWidthLoiChao12[] = {80, 220, 230, 200};
//            Table customerLuuY = new Table(colWidthLoiChao12);
//            customerLuuY.setFont(font);
//            customerLuuY.addCell(new com.itextpdf.layout.element.Cell(0, 4).add("Lưu ý: Quý khách hãy giữ lại hóa đơn,\nNếu sản phẩm gặp vấn đề gì có thể trả hàng trong vòng 3 ngày,\n chỉ thực hiện trả hàng cho những sản phẩm không áp dụng khuyến mại.\nNhững sản phẩm được đánh dấu (*) ở giá bán là những sản phẩm đã có giảm giá khuyến mại").setItalic().setFontColor(Color.RED).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
            float colWidth1[] = {80, 220, 230, 200};
            Table customer1 = new Table(colWidth1);
            customer1.setFont(font);
            customer1.addCell(new com.itextpdf.layout.element.Cell(0, 4)
                    .add("----------------------------------------------------------").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            float colWidthLoiChao[] = {80, 220, 230, 200};
            Table customerLoiChao = new Table(colWidthLoiChao);
            customerLoiChao.setFont(font);
            customerLoiChao.addCell(new com.itextpdf.layout.element.Cell(0, 4).add("Trường cao đẳng FPT Polytechnich, P.Trịnh Văn Bô,\nP.Phương Canh, Q.Nam Từ Liêm, TP.Hà Nội").setItalic().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            Table customer3 = new Table(colWidth1);
            customer1.setFont(font);
            customer1.addCell(new com.itextpdf.layout.element.Cell(0, 4).add("Cảm ơn quý khách và hẹn gặp lại\nHotline: 0702230722").setItalic().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            document.add(table);
            document.add(new com.itextpdf.layout.element.Paragraph("\n"));
            document.add(khachHangTable);
            document.add(new com.itextpdf.layout.element.Paragraph("\n"));
            document.add(phieuThuTable);
            document.add(new com.itextpdf.layout.element.Paragraph("\n"));
            document.add(new com.itextpdf.layout.element.Paragraph("Khách Hàng                                                                                       Nhân Viên"));
            document.add(new com.itextpdf.layout.element.Paragraph("\n"));
            document.add(new com.itextpdf.layout.element.Paragraph("\n"));
            document.add(new com.itextpdf.layout.element.Paragraph("\n"));
            document.add(customer1);
            document.add(customerLoiChao);
            document.add(customer3);

            if (!Desktop.isDesktopSupported()) {
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) {
                desktop.open(file);
            }
            document.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
