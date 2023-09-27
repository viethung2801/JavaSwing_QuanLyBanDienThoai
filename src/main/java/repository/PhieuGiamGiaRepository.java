package repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Query;
import model.PhieuGiamGia;
import model.PhieuGiamGiaChiTiet;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateUtil;
import viewmodel.HoaDonResponse;
import viewmodel.PhieuGiamGiaResponse;

public class PhieuGiamGiaRepository {

    // 1. add
    public static boolean add(PhieuGiamGia phieu) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(phieu);
            transaction.commit();
            check = true;
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return check;
    }

    // 2. update
    public static boolean update(PhieuGiamGiaResponse phieuGiamGiaResponse) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();

            PhieuGiamGia phieuInDB = session.get(PhieuGiamGia.class, phieuGiamGiaResponse.getId());
            // 'phieuInDB' là thằng trong db mà mình get ra bằng id của response

            phieuInDB.setMaPhieu(phieuGiamGiaResponse.getMaPhieu());
            phieuInDB.setTenPhieu(phieuGiamGiaResponse.getTenPhieu());

            PhieuGiamGiaChiTiet phieuGiamGiaChiTiet = phieuInDB.getPhieuGiamGiaChiTiet();

            phieuGiamGiaChiTiet.setNgayBatDau(phieuGiamGiaResponse.getNgayBatDau());
            phieuGiamGiaChiTiet.setNgayKetThuc(phieuGiamGiaResponse.getNgayKetThuc());
            phieuGiamGiaChiTiet.setLuotSuDung(phieuGiamGiaResponse.getLuotSuDung());
            phieuGiamGiaChiTiet.setDieuKien(phieuGiamGiaResponse.getDieuKien());
            phieuGiamGiaChiTiet.setGiaTri(phieuGiamGiaResponse.getGiaTri());
            phieuGiamGiaChiTiet.setTrangThai(phieuGiamGiaResponse.getTrangThai());

            phieuInDB.setPhieuGiamGiaChiTiet(phieuGiamGiaChiTiet);

            Transaction transaction = session.beginTransaction();
            session.update(phieuInDB);
            transaction.commit();
            check = true;
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return check;
    }

    // 3. get all
    public static List<PhieuGiamGiaResponse> getAll() {
        List<PhieuGiamGiaResponse> phieuGiamGiaResponses = new ArrayList<>();
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.PhieuGiamGiaResponse
                                              (pgg.id, pgg.maPhieu, pgg.tenPhieu, pct.ngayBatDau, pct.ngayKetThuc, pct.luotSuDung, pct.dieuKien, pct.giaTri, pct.trangThai)
                                              FROM PhieuGiamGia pgg
                                              INNER JOIN pgg.phieuGiamGiaChiTiet pct
                                               """);
            phieuGiamGiaResponses = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return phieuGiamGiaResponses;
    }

    // 4. get by status
    public static List<PhieuGiamGiaResponse> getByStatus(int tt) {
        List<PhieuGiamGiaResponse> phieuGiamGiaResponses = new ArrayList<>();
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                            SELECT new viewmodel.PhieuGiamGiaResponse
                                            (pgg.id, pgg.maPhieu, pgg.tenPhieu, pct.ngayBatDau, pct.ngayKetThuc, pct.luotSuDung, pct.dieuKien, pct.giaTri, pct.trangThai)
                                            FROM PhieuGiamGia pgg
                                            INNER JOIN pgg.phieuGiamGiaChiTiet pct where  pct.trangThai = :tt
                                            """);
            query.setParameter("tt", tt);
            phieuGiamGiaResponses = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return phieuGiamGiaResponses;
    }

    // 5. get by name
    public static List<PhieuGiamGiaResponse> getByName(String name) {
        List<PhieuGiamGiaResponse> phieuGiamGiaResponses = new ArrayList<>();
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                            SELECT new viewmodel.PhieuGiamGiaResponse
                                                                                            (pgg.id, pgg.maPhieu, pgg.tenPhieu, pct.ngayBatDau, pct.ngayKetThuc, pct.luotSuDung, pct.dieuKien, pct.giaTri, pct.trangThai)
                                                                                            FROM PhieuGiamGia pgg
                                              INNER JOIN pgg.phieuGiamGiaChiTiet pct where pgg.tenPhieu like :name
                                               """);
            query.setParameter("name", "%" + name + "%");
            phieuGiamGiaResponses = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return phieuGiamGiaResponses;
    }

    // 6
    public List<PhieuGiamGiaResponse> getByMa(String ma) {
        List<PhieuGiamGiaResponse> phieuGiamGiaResponses = new ArrayList<>();
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                            SELECT new viewmodel.PhieuGiamGiaResponse
                                            (pgg.id, pgg.maPhieu, pgg.tenPhieu, pct.ngayBatDau, pct.ngayKetThuc, pct.luotSuDung, pct.dieuKien, pct.giaTri, pct.trangThai)
                                            FROM PhieuGiamGia pgg
                                            INNER JOIN pgg.phieuGiamGiaChiTiet pct where pgg.maPhieu like :ma
                                            """);
            query.setParameter("ma", "%" + ma + "%");
            phieuGiamGiaResponses = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return phieuGiamGiaResponses;
    }

    // 7. lấy phiếu giảm giá cho view banHang
    /*
    - Điều kiện
    + ngày bắt đầu <= ngày hiện tại <= ngày kết thúc
    + lượt sử dụng > 0
    + đk truyền vào >= dieuKien
    + trangThai = 0
     */
    public List<PhieuGiamGiaResponse> getAllForView(long tongTien) {
        List<PhieuGiamGiaResponse> phieuGiamGiaResponses = new ArrayList<>();
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                            SELECT new viewmodel.PhieuGiamGiaResponse
                                            (pgg.id, pgg.maPhieu, pgg.tenPhieu, pct.ngayBatDau, pct.ngayKetThuc, pct.luotSuDung, pct.dieuKien, pct.giaTri, pct.trangThai)
                                            FROM PhieuGiamGia pgg
                                            INNER JOIN pgg.phieuGiamGiaChiTiet pct
                                            WHERE pct.luotSuDung > 0
                                            AND pct.dieuKien <= :tongTien
                                            AND pct.trangThai = 0
                                            """);
            query.setParameter("tongTien", tongTien);
            phieuGiamGiaResponses = query.getResultList();

            phieuGiamGiaResponses = phieuGiamGiaResponses.stream().filter(p -> {
                LocalDate now = LocalDate.now();
                LocalDate ngayBatDau = p.getNgayBatDau();
                LocalDate ngayKetThuc = p.getNgayKetThuc();
                int compare1 = ngayBatDau.compareTo(now);
                int compare2 = now.compareTo(ngayKetThuc);
                if (compare1 <= 0 && compare2 <= 0) {
                    return true;
                }
                return false;
            }).collect(Collectors.toList());
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return phieuGiamGiaResponses;
    }

    // 8
    public static PhieuGiamGia getPhieuByMa(String ma) {
        PhieuGiamGia phieuGiamGia = null;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                            SELECT pgg
                                            FROM PhieuGiamGia pgg
                                            WHERE pgg.maPhieu = :ma
                                            """);
            query.setParameter("ma", ma);
            phieuGiamGia = (PhieuGiamGia) query.getSingleResult();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return phieuGiamGia;
    }

    // 9. update lượt sử dụng sau khi tạo hóa đơn thành công (trong view bán hàng)
    public static void updateLuotSuDung(PhieuGiamGiaChiTiet phieuChiTiet) {
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();

            session.update(phieuChiTiet);
            transaction.commit();
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
    }

    // 10
    public static Long tongTien() {
        Long tong = 0L;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              select sum(hd.tongTien) from HoaDon hd inner join PhieuGiamGia pgg
                                              on hd.phieuGiamGia.id  = pgg.id
                                                """);
            tong = (long) query.getSingleResult();
            return tong;
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return 0L;
    }

    public static Long soDon() {
        Long tong = 0L;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              select count(hd.id) from HoaDon hd inner join PhieuGiamGia pgg
                                              on hd.phieuGiamGia.id  = pgg.id
                                                """);
            tong = (Long) query.getSingleResult();
            return tong;
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return 0L;
    }
public static Long tienGiam() {
        Long tong = 0L;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              select sum(hd.tienGiam) from HoaDon hd inner join PhieuGiamGia pgg
                                              on hd.phieuGiamGia.id  = pgg.id
                                                """);
            tong = (long) query.getSingleResult();
            return tong;
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return 0L;
    }
}
