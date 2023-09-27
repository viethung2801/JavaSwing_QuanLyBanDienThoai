package repository;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import model.KhachHang;
import model.TheTichDiem;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utility.HibernateUtil;
import viewmodel.KhachHangResponse;

public class KhachHangRepository {

    // 1. add
    public static boolean add(KhachHang khachHang) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(khachHang);
            transaction.commit();
            session.close();
            check = true;
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return check;
    }

    // 2. update
    public static boolean update(KhachHangResponse khachHangResponse) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();

            KhachHang khachHangInDB = session.get(KhachHang.class, khachHangResponse.getId());
            TheTichDiem tichDiem = session.get(TheTichDiem.class, khachHangResponse.getIdThe());
            khachHangInDB.setHoTen(khachHangResponse.getHoTen());
            khachHangInDB.setEmail(khachHangResponse.getEmail());
            khachHangInDB.setSdt(khachHangResponse.getSdt());
            khachHangInDB.setGioiTinh(khachHangResponse.isGioiTinh());
            khachHangInDB.setNgaySinh(khachHangResponse.getNgaySinh());
            khachHangInDB.setDiaChi(khachHangResponse.getDiaChi());
            khachHangInDB.setTrangThai(khachHangResponse.getTrangThai());
            Transaction transaction = session.beginTransaction();
            session.update(khachHangInDB);
            transaction.commit();
            check = true;
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return check;
    }

    // update trạng thái thẻ tích điểm
    public static boolean updateTichDiem(KhachHangResponse kh, int trangThai) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();

            TheTichDiem the = session.get(TheTichDiem.class, kh.getIdThe());
            the.setTrangThai(trangThai == 1 ? true : false);
            Transaction transaction = session.beginTransaction();

            session.update(the);
            transaction.commit();
            check = true;
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return check;
    }

    public static boolean updateKhoiPhuc(KhachHangResponse kh, int trangThai) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();

            KhachHang khachHangInDB = session.get(KhachHang.class, kh.getId());
            khachHangInDB.setTrangThai(trangThai);
            updateTichDiem(kh, trangThai);
            Transaction transaction = session.beginTransaction();

            session.update(khachHangInDB);
            transaction.commit();
            check = true;
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return check;
    }

    // get all
    public static List<KhachHangResponse> getAll() {
        List<KhachHangResponse> khachHangResponses = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.KhachHangResponse
                                              (kh.id, kh.hoTen, kh.email, kh.sdt, kh.gioiTinh, kh.ngaySinh, kh.diaChi, kh.trangThai, ttd.id, ttd.maThe, ttd.ngayKichHoat, ttd.soDiem, ttd.trangThai)
                                              FROM KhachHang kh
                                              INNER JOIN kh.theTichDiem ttd
                                               """);

            khachHangResponses = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return khachHangResponses;
    }

    //
    public List<KhachHangResponse> getAllResponseByStatus(int status) {
        List<KhachHangResponse> khachHangResponses = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.KhachHangResponse
                                              (kh.id, kh.hoTen, kh.email, kh.sdt, kh.gioiTinh, kh.ngaySinh, kh.diaChi, kh.trangThai, ttd.id, ttd.maThe, ttd.ngayKichHoat, ttd.soDiem, ttd.trangThai)
                                              FROM KhachHang kh
                                              INNER JOIN kh.theTichDiem ttd
                                              WHERE kh.trangThai = :status
                                               """);
            query.setParameter("status", status);
            khachHangResponses = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return khachHangResponses;
    }

    // get khách hàng by email
    public static KhachHangResponse getKhachHangByEmail(String email) {
        KhachHangResponse kh = null;

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.KhachHangResponse
                                              (kh.id, kh.hoTen, kh.email, kh.sdt, kh.gioiTinh, kh.ngaySinh, kh.diaChi, kh.trangThai, ttd.id, ttd.maThe, ttd.ngayKichHoat, ttd.soDiem, ttd.trangThai)
                                              FROM KhachHang kh
                                              INNER JOIN kh.theTichDiem ttd WHERE kh.email = :email
                                               """);
            query.setParameter("email", email);
            kh = (KhachHangResponse) query.getSingleResult();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        } catch (NoResultException e) {
            kh = null;
        }
        return kh;
    }

    // get khách hàng by email or sdt
    public static KhachHangResponse getKhachHangByEmailOrSDT(String keyword) {
        KhachHangResponse kh = null;

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.KhachHangResponse
                                              (kh.id, kh.hoTen, kh.email, kh.sdt, kh.gioiTinh, kh.ngaySinh, kh.diaChi, kh.trangThai, ttd.id, ttd.maThe, ttd.ngayKichHoat, ttd.soDiem, ttd.trangThai)
                                              FROM KhachHang kh
                                              INNER JOIN kh.theTichDiem ttd
                                              WHERE kh.email = :keyword
                                              OR kh.sdt = :keyword
                                               """);
            query.setParameter("keyword", keyword);
            kh = (KhachHangResponse) query.getSingleResult();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        } catch (NoResultException e) {
            kh = null;
        }
        return kh;
    }

    // get Khách hàng by mã thẻ
    public static KhachHangResponse getKhachHangByMaThe(String maThe) {
        KhachHangResponse kh = null;

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.KhachHangResponse
                                              (kh.id, kh.hoTen, kh.email, kh.sdt, kh.gioiTinh, kh.ngaySinh, kh.diaChi, kh.trangThai, ttd.id, ttd.maThe, ttd.ngayKichHoat, ttd.soDiem, ttd.trangThai)
                                              FROM KhachHang kh
                                              INNER JOIN kh.theTichDiem ttd WHERE ttd.maThe = :maThe
                                               """);
            query.setParameter("maThe", maThe);
            kh = (KhachHangResponse) query.getSingleResult();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        } catch (NoResultException e) {
            kh = null;
        }
        return kh;

    }

    // get khách hàng by id
    public static KhachHangResponse getKhachHangById(int id) {
        KhachHangResponse kh = null;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.KhachHangResponse
                                              (kh.id, kh.hoTen, kh.email, kh.sdt, kh.gioiTinh, kh.ngaySinh, kh.diaChi, kh.trangThai, ttd.id, ttd.maThe, ttd.ngayKichHoat, ttd.soDiem, ttd.trangThai)
                                              FROM KhachHang kh
                                              INNER JOIN kh.theTichDiem ttd WHERE kh.id = :id
                                               """);
            query.setParameter("id", id);
            kh = (KhachHangResponse) query.getSingleResult();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        } catch (NoResultException e) {
            kh = null;
        }
        return kh;
    }

    // get by SDT và trạng thái
    public static List<KhachHangResponse> findBySDT(String sdt, int trangThai) {
        List<KhachHangResponse> khachHangResponses = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.KhachHangResponse
                                              (kh.id, kh.hoTen, kh.email, kh.sdt, kh.gioiTinh, kh.ngaySinh, kh.diaChi, kh.trangThai, ttd.id, ttd.maThe, ttd.ngayKichHoat, ttd.soDiem, ttd.trangThai)
                                              FROM KhachHang kh
                                              INNER JOIN kh.theTichDiem ttd WHERE kh.trangThai = :trangThai AND kh.sdt LIKE :sdt
                                               """);
            query.setParameter("trangThai", trangThai);
            query.setParameter("sdt", "%" + sdt + "%");
            khachHangResponses = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return khachHangResponses;
    }

    // lọc theo tên
    public static List<KhachHangResponse> sortByName(Boolean c, int trangThai) {
        List<KhachHangResponse> khachHangResponses = new ArrayList<>();
        String sql = null;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            if (c == true) {
                sql = """
                       SELECT new viewmodel.KhachHangResponse
                       (kh.id, kh.hoTen, kh.email, kh.sdt, kh.gioiTinh, kh.ngaySinh, kh.diaChi, kh.trangThai, ttd.id, ttd.maThe, ttd.ngayKichHoat, ttd.soDiem, ttd.trangThai)
                       FROM KhachHang kh
                       INNER JOIN kh.theTichDiem ttd  WHERE kh.trangThai = :trangThai Order by kh.hoTen ASC
                       """;

            } else {
                sql = """
                        SELECT new viewmodel.KhachHangResponse
                        (kh.id, kh.hoTen, kh.email, kh.sdt, kh.gioiTinh, kh.ngaySinh, kh.diaChi, kh.trangThai, ttd.id, ttd.maThe, ttd.ngayKichHoat, ttd.soDiem, ttd.trangThai)
                        FROM KhachHang kh
                        INNER JOIN kh.theTichDiem ttd  WHERE kh.trangThai = :trangThai Order by kh.hoTen DESC
                         """;
            }
            Query query = session.createQuery(sql);
            query.setParameter("trangThai", trangThai);

            khachHangResponses = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return khachHangResponses;
    }

    // getAll thẻ tích điểm
    public List<KhachHangResponse> getAllTheTichDiem() {
        List<KhachHangResponse> lists = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = """
                        SELECT new viewmodel.KhachHangResponse(kh.hoTen,ttd.id, ttd.maThe, ttd.ngayKichHoat, ttd.soDiem, ttd.trangThai)
                                                                       FROM KhachHang kh
                                                                       INNER JOIN kh.theTichDiem ttd
                         """;
            Query query = session.createQuery(hql);
            lists = query.getResultList();
        }
        return lists;
    }

    // getTop 3 khách hàng
    public List<KhachHangResponse> getTop3() {
        List<KhachHangResponse> lists = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = """
                        SELECT new viewmodel.KhachHangResponse(kh.hoTen,ttd.id, ttd.maThe, ttd.ngayKichHoat, ttd.soDiem, ttd.trangThai)
                                                                       FROM KhachHang kh
                                                                       INNER JOIN kh.theTichDiem ttd Order by ttd.soDiem DESC
                         """;
            Query query = session.createQuery(hql);
            query.setMaxResults(5);
            lists = query.getResultList();
        }
        return lists;
    }

// find by Mã Tích Điểm
    public static List<KhachHangResponse> findByMa(String maThe) {
        List<KhachHangResponse> khachHangResponses = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                         SELECT new viewmodel.KhachHangResponse(kh.hoTen,ttd.id, ttd.maThe, ttd.ngayKichHoat, ttd.soDiem, ttd.trangThai)
                                                                                                FROM KhachHang kh
                                                                                                INNER JOIN kh.theTichDiem ttd Where ttd.maThe like :maThe
                         """);

            query.setParameter("maThe", maThe + "%");
            khachHangResponses = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return khachHangResponses;
    }

    // updateDiem thẻ tích lũy của khách hàng
    public static boolean updateDiemTichLuy(KhachHangResponse kh, int soDiem) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();

            TheTichDiem the = session.get(TheTichDiem.class, kh.getIdThe());
            the.setSoDiem(soDiem);
            Transaction transaction = session.beginTransaction();

            session.update(the);
            transaction.commit();
            check = true;
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return check;
    }

    // get khachHang entity by id
    public static KhachHang getKhachHangEntityById(int id) {
        KhachHang khachHang = null;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT kh
                                              FROM KhachHang kh
                                              WHERE kh.id = :id
                                               """);
            query.setParameter("id", id);
            khachHang = (KhachHang) query.getSingleResult();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        } catch (NoResultException e) {
            khachHang = null;
        }
        return khachHang;
    }

}
