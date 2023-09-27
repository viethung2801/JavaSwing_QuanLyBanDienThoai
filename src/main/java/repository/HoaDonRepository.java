package repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import model.HoaDon;
import model.HoaDonChiTiet;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateUtil;
import viewmodel.HoaDonChiTietResponse;
import viewmodel.HoaDonResponse;

public class HoaDonRepository {

    //  add
    public boolean add(HoaDon hoaDon) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(hoaDon);
            transaction.commit();
            check = true;
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return check;
    }

    // get List<HoaDonResponse> by traGop
    public List<HoaDonResponse> getResponsesByTraGop(int traGop) {
        List<HoaDonResponse> hoaDonResponses = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query1 = session.createQuery("""
                                              SELECT new viewmodel.HoaDonResponse
                                              (hd.id, hd.maHoaDon, hd.ngayTao, hd.ngayThanhToan, hd.tienGiam, hd.tongTien, hd.tienKhachDua, hd.tienThua,
                                              hd.traGop, hd.tienTraTruoc, hd.tienThieu, hd.hinhThucThanhToan, hd.maGiaoDichChuyenKhoan,
                                              nv.hoTen, kh.hoTen, pgg.maPhieu)
                                              FROM HoaDon hd
                                              INNER JOIN hd.nhanVien nv
                                              INNER JOIN hd.khachHang kh
                                              INNER JOIN hd.phieuGiamGia pgg
                                              WHERE hd.traGop = :traGop
                                              ORDER BY hd.ngayTao
                                              """);
            Query query2 = session.createQuery("""
                                              SELECT new viewmodel.HoaDonResponse
                                              (hd.id, hd.maHoaDon, hd.ngayTao, hd.ngayThanhToan, hd.tienGiam, hd.tongTien, hd.tienKhachDua, hd.tienThua,
                                               hd.traGop, hd.tienTraTruoc, hd.tienThieu, hd.hinhThucThanhToan, hd.maGiaoDichChuyenKhoan,
                                              nv.hoTen, kh.hoTen, pgg.maPhieu)
                                              FROM HoaDon hd
                                              INNER JOIN hd.nhanVien nv
                                              INNER JOIN hd.khachHang kh
                                              INNER JOIN hd.phieuGiamGia pgg
                                              ORDER BY hd.ngayTao
                                              """);
            if (traGop == 0) {
                query1.setParameter("traGop", false);
                hoaDonResponses = query1.getResultList();
            } else if (traGop == 1) {
                query1.setParameter("traGop", true);
                hoaDonResponses = query1.getResultList();
            } else if (traGop == 2) {
                hoaDonResponses = query2.getResultList();
            }

        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return hoaDonResponses;
    }

    // get HoaDon by ID
    public HoaDon getEntityById(int id) {
        HoaDon hoaDon = null;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            hoaDon = session.get(HoaDon.class, id);
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        }
        return hoaDon;
    }

    // get HoaDon by maHoaDon
    public HoaDon getEntityByMa(String maHoaDon) {
        HoaDon hoaDon = null;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT hd
                                              FROM HoaDon hd
                                              WHERE hd.maHoaDon = :maHoaDon
                                               """);
            query.setParameter("maHoaDon", maHoaDon);
            hoaDon = (HoaDon) query.getSingleResult();
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        } catch (NoResultException e) {
            hoaDon = null;
        }
        return hoaDon;
    }

    // get List<HoaDonResponse> by keyword
    public List<HoaDonResponse> searchHoaDon(String keyword) {
        List<HoaDonResponse> hoaDonResponses = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.HoaDonResponse
                                              (hd.id, hd.maHoaDon, hd.ngayTao, hd.ngayThanhToan, hd.tienGiam, hd.tongTien, hd.tienKhachDua, hd.tienThua,
                                              hd.traGop, hd.tienTraTruoc, hd.tienThieu, hd.hinhThucThanhToan, hd.maGiaoDichChuyenKhoan,
                                              nv.hoTen, kh.hoTen, pgg.maPhieu)
                                              FROM HoaDon hd
                                              INNER JOIN hd.nhanVien nv
                                              INNER JOIN hd.khachHang kh
                                              INNER JOIN hd.phieuGiamGia pgg
                                              WHERE nv.hoTen LIKE :keyword
                                              OR kh.email LIKE :keyword
                                              OR kh.sdt LIKE :keyword
                                              OR kh.hoTen LIKE :keyword
                                              ORDER BY hd.ngayTao
                                              """);
            query.setParameter("keyword", "%" + keyword + "%");
            hoaDonResponses = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return hoaDonResponses;
    }

    // get List<HoaDonResponse> by date
    public List<HoaDonResponse> filterHoaDonByDate(LocalDateTime start, LocalDateTime end, String kieuNgay) {
        List<HoaDonResponse> hoaDonResponses = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query1 = session.createQuery("""
                                              SELECT new viewmodel.HoaDonResponse
                                              (hd.id, hd.maHoaDon, hd.ngayTao, hd.ngayThanhToan, hd.tienGiam, hd.tongTien, hd.tienKhachDua,
                                               hd.tienThua, hd.traGop, hd.tienTraTruoc, hd.tienThieu, hd.hinhThucThanhToan, hd.maGiaoDichChuyenKhoan,
                                              nv.hoTen, kh.hoTen, pgg.maPhieu)
                                              FROM HoaDon hd
                                              INNER JOIN hd.nhanVien nv
                                              INNER JOIN hd.khachHang kh
                                              INNER JOIN hd.phieuGiamGia pgg
                                              WHERE hd.ngayTao >= :start
                                              AND hd.ngayTao <= :end
                                              ORDER BY hd.ngayTao
                                              """);
            Query query2 = session.createQuery("""
                                              SELECT new viewmodel.HoaDonResponse
                                              (hd.id, hd.maHoaDon, hd.ngayTao, hd.ngayThanhToan, hd.tienGiam, hd.tongTien, hd.tienKhachDua,
                                               hd.tienThua, hd.traGop, hd.tienTraTruoc, hd.tienThieu, hd.hinhThucThanhToan, hd.maGiaoDichChuyenKhoan,
                                              nv.hoTen, kh.hoTen, pgg.maPhieu)
                                              FROM HoaDon hd
                                              INNER JOIN hd.nhanVien nv
                                              INNER JOIN hd.khachHang kh
                                              INNER JOIN hd.phieuGiamGia pgg
                                              WHERE hd.ngayThanhToan >= :start
                                              AND hd.ngayThanhToan <= :end
                                              ORDER BY hd.ngayTao
                                              """);
            if (kieuNgay.equals("NgayTao")) {
                query1.setParameter("start", start);
                query1.setParameter("end", end);
                hoaDonResponses = query1.getResultList();
            } else if(kieuNgay.equals("NgayThanhToan")) {
                query2.setParameter("start", start);
                query2.setParameter("end", end);
                hoaDonResponses = query2.getResultList();
            }
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return hoaDonResponses;
    }

    /* ---------------------- hóa đơn chi tiết ---------------------- */
    // get List<HoaDonChiTietResponse> by id hóa đơn
    public List<HoaDonChiTietResponse> getChiTietResponsesIdHoaDon(int idHoaDon) {
        List<HoaDonChiTietResponse> hoaDonChiTietResponses = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.HoaDonChiTietResponse
                                              (hdct.id, dt.tenDT, dt.giaBan, i.imei)
                                              FROM HoaDonChiTiet hdct
                                              INNER JOIN hdct.hoaDon hd
                                              INNER JOIN hdct.imei i
                                              INNER JOIN hdct.imei.dienThoai dt
                                              WHERE hd.id = :id
                                              """);
            query.setParameter("id", idHoaDon);
            hoaDonChiTietResponses = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return hoaDonChiTietResponses;
    }

    // get HoaDonChiTiet by ID
    public static HoaDonChiTiet getChiTietEntityById(int id) {
        HoaDonChiTiet hoaDonChiTiet = null;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            hoaDonChiTiet = session.get(HoaDonChiTiet.class, id);
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        }
        return hoaDonChiTiet;
    }
}
