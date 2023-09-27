package utility;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.text.pdf.BaseFont;
import java.awt.Desktop;
import java.io.File;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import model.HoaDon;
import viewmodel.HoaDonChiTietResponse;

public class ExportPdfHoaDon {

    public final String pathUnicode = getClass().getClassLoader().getResource("font/unicode.ttf").getPath();

    public void exportBill3(HoaDon hoaDon, List<HoaDonChiTietResponse> hdctResponseList, String pathFile) {
        System.out.println(pathFile);
        try {
            System.out.println(pathFile);
            String path = pathFile + "\\" + "hoa_don" + Calendar.getInstance().getTimeInMillis() + ".pdf";
            File file = new File(path);
            PdfWriter pdfWriter = new PdfWriter(path);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
            float col = 280f;
            float columWidth[] = {col, col};

            PdfFont font = PdfFontFactory.createFont(new ExportPdfHoaDon().pathUnicode, BaseFont.IDENTITY_H);

            Table table = new Table(columWidth);
            table.setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE);
            table.setFont(font);

            table.addCell(new Cell().add("PHONE POLY").setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setFontSize(30f)
                    .setBorder(Border.NO_BORDER));
            table.addCell(new Cell().add("Mã hóa đơn: " + hoaDon.getMaHoaDon() + "\nPHONE POLY").setTextAlignment(TextAlignment.RIGHT)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setBorder(Border.NO_BORDER)
                    .setMarginRight(10f));

            float colWidth[] = {80, 230, 200, 200};

            // Thông tin khách hàng đặt hàng
            Table khachHangTable = new Table(colWidth);
            khachHangTable.setFont(font);
            khachHangTable.addCell(new Cell(0, 4).add("Thông tin khách hàng").setBold().setBorder(Border.NO_BORDER));

            khachHangTable.addCell(new Cell().add("Họ tên:").setBorder(Border.NO_BORDER));
            khachHangTable.addCell(new Cell().add(hoaDon.getKhachHang().getHoTen()).setBorder(Border.NO_BORDER));
            khachHangTable.addCell(new Cell().add("SĐT:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            khachHangTable.addCell(new Cell().add(hoaDon.getKhachHang().getSdt()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            khachHangTable.addCell(new Cell().add("Email:").setBorder(Border.NO_BORDER));
            khachHangTable.addCell(new Cell().add(hoaDon.getKhachHang().getEmail()).setBorder(Border.NO_BORDER));
            khachHangTable.addCell(new Cell().add("Địa chỉ:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            khachHangTable.addCell(new Cell().add(hoaDon.getKhachHang().getDiaChi()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));

            // Thông tin người tạo hóa đơn
            Table nguoiDungTable = new Table(colWidth);
            nguoiDungTable.setFont(font);
            nguoiDungTable.addCell(new Cell(0, 4).add("Thông tin người tạo hóa đơn").setBold().setBorder(Border.NO_BORDER));

            nguoiDungTable.addCell(new Cell().add("Họ tên:").setBorder(Border.NO_BORDER));
            nguoiDungTable.addCell(new Cell().add(hoaDon.getNhanVien().getHoTen()).setBorder(Border.NO_BORDER));
            nguoiDungTable.addCell(new Cell().add("SĐT:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            nguoiDungTable.addCell(new Cell().add(hoaDon.getNhanVien().getSdt()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            nguoiDungTable.addCell(new Cell().add("Email:").setBorder(Border.NO_BORDER));
            nguoiDungTable.addCell(new Cell().add(hoaDon.getNhanVien().getEmail()).setBorder(Border.NO_BORDER));
            nguoiDungTable.addCell(new Cell().add("Chức vụ:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            nguoiDungTable.addCell(new Cell().add(hoaDon.getNhanVien().isChucVu() ? "Nhân viên" : "Quản lý").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));

            // Thông tin mã giảm giá
            DecimalFormat df = new DecimalFormat("#,###");
            Table phieuGiamGiaTable = new Table(colWidth);
            if (hoaDon.getPhieuGiamGia() != null) {
                nguoiDungTable.setFont(font);
                nguoiDungTable.addCell(new Cell(0, 4).add("Thông tin phiếu giảm").setBold().setBorder(Border.NO_BORDER));

                nguoiDungTable.addCell(new Cell().add("Mã phiếu:").setBorder(Border.NO_BORDER));
                nguoiDungTable.addCell(new Cell().add(hoaDon.getPhieuGiamGia().getMaPhieu()).setBorder(Border.NO_BORDER));
                nguoiDungTable.addCell(new Cell().add("Điều kiện:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
                nguoiDungTable.addCell(new Cell().add(df.format(hoaDon.getPhieuGiamGia().getPhieuGiamGiaChiTiet().getDieuKien()) + " VND").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
                nguoiDungTable.addCell(new Cell().add("Giá trị:").setBorder(Border.NO_BORDER));
                nguoiDungTable.addCell(new Cell().add(String.valueOf(hoaDon.getPhieuGiamGia().getPhieuGiamGiaChiTiet().getGiaTri()) + "%").setBorder(Border.NO_BORDER));
            }

            // Hóa đơn chi tiết
            float itemColWidth[] = {30, 300, 190, 190};
            Table itemTable = new Table(itemColWidth);
            itemTable.setFont(font);
            itemTable.addCell(new Cell().add("STT").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Tên sản phẩm").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("IMEI").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Giá bán").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));

            int index = 1;

            for (HoaDonChiTietResponse ct : hdctResponseList) {
                itemTable.addCell(new Cell().add(String.valueOf(index++)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(ct.getTenDienThoai()).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(ct.getImei()).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(df.format(ct.getDonGia()) + " VND").setBorder(Border.NO_BORDER));
            }

            // -- thành tiền
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Thành tiền").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            itemTable.addCell(new Cell().add(df.format(hoaDon.getTongTien()) + " VND").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

            // -- tiền giảm
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Tiền giảm").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            itemTable.addCell(new Cell().add(df.format(hoaDon.getTienGiam()) + " VND").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

            // -- tiền phải trả:
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Tiền phải trả").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            itemTable.addCell(new Cell().add(df.format(hoaDon.getTongTien() - hoaDon.getTienGiam()) + " VND").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

            if (!hoaDon.isTraGop()) {
                // -- kiểu thanh toán
                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add("-- Kiểu hóa đơn").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
                itemTable.addCell(new Cell().add("Trả toàn bộ").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

                // -- tiền khách đưa:
                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add("Tiền khách đưa").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
                itemTable.addCell(new Cell().add(df.format(hoaDon.getTienKhachDua()) + " VND").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

                // -- tiền thừa:
                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add("Tiền thừa").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
                itemTable.addCell(new Cell().add(df.format(hoaDon.getTienThua()) + " VND").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            } else {
                // -- kiểu thanh toán
                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add("-- Kiểu hóa đơn").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
                itemTable.addCell(new Cell().add("Trả góp").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

                // -- trả trước tối thiểu
                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add("Tiền trả trước tối thiểu").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
                itemTable.addCell(new Cell().add(df.format((hoaDon.getTongTien() - hoaDon.getTienGiam()) / 2) + " VND").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

                // -- tiền trả trước:
                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add("Tiền trả trước").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
                itemTable.addCell(new Cell().add(df.format(hoaDon.getTienTraTruoc()) + " VND").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

                // -- tiền thiếu:
                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add("Tiền thiếu").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
                itemTable.addCell(new Cell().add(df.format(hoaDon.getTienThieu()) + " VND").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            }

            // -- hình thức thanh toán
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("-- Hình thức thành toán").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            itemTable.addCell(new Cell().add(hoaDon.isHinhThucThanhToan() ? "Tiền mặt" : "Chuyển khoản").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

            // -- mã giao dịch(nếu là chuyển khoản)
            if (!hoaDon.isHinhThucThanhToan()) {
                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add("-- Mã giao dịch").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
                itemTable.addCell(new Cell().add(hoaDon.getMaGiaoDichChuyenKhoan()).setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            }

            // -- ngày tạo, ngày thanh toán
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Ngày tạo hóa đơn").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            itemTable.addCell(new Cell().add(formatter.format(hoaDon.getNgayTao())).setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Ngày thanh toán").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            itemTable.addCell(new Cell().add(formatter.format(hoaDon.getNgayThanhToan())).setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

            // Lưu ý
            float colWidthLoiChao12[] = {80, 220, 230, 200};
            Table customerLuuY = new Table(colWidthLoiChao12);
            customerLuuY.setFont(font);
            customerLuuY.addCell(new Cell(0, 4).add("Lưu ý: Quý khách hãy giữ lại hóa đơn,\nNếu sản phẩm gặp vấn đề gì có thể trả hàng trong vòng 3 ngày,\n chỉ thực hiện trả hàng cho những sản phẩm không áp dụng khuyến mại.\nNhững sản phẩm được đánh dấu (*) ở giá bán là những sản phẩm đã có giảm giá khuyến mại").setItalic().setFontColor(Color.RED).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            float colWidth1[] = {80, 220, 230, 200};
            Table customer1 = new Table(colWidth1);
            customer1.setFont(font);
            customer1.addCell(new Cell(0, 4)
                    .add("----------------------------------------------------------").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            float colWidthLoiChao[] = {80, 220, 230, 200};
            Table customerLoiChao = new Table(colWidthLoiChao);
            customerLoiChao.setFont(font);
            customerLoiChao.addCell(new Cell(0, 4).add("Trường cao đẳng FPT Polytechnich, P.Trịnh Văn Bô,\nP.Phương Canh, Q.Nam Từ Liêm, TP.Hà Nội").setItalic().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            Table customer3 = new Table(colWidth1);
            customer1.setFont(font);
            customer1.addCell(new Cell(0, 4).add("Cảm ơn quý khách và hẹn gặp lại\nHotline: 0702230722").setItalic().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            document.add(table);
            document.add(new Paragraph("\n"));
            document.add(khachHangTable);
            document.add(nguoiDungTable);
            if (hoaDon.getPhieuGiamGia() != null) {
                document.add(phieuGiamGiaTable);
            }
            document.add(new Paragraph("\n"));
            document.add(itemTable);
//            if (!hoaDon.getKhachHang().getMa().equals("KH000")) {
//                document.add(customerLuuY);
//            }
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

    public static void main(String[] args) {
        System.out.println(new ExportPdfHoaDon().pathUnicode);
    }
}
