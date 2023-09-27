package repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import model.DongSanPham;
import model.Hang;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateUtil;
import viewmodel.DongSanPhamResponse;

public class DongSanPhamRepository {

    // 1. get all entity by hangDienThoai
    public List<DongSanPham> getAllEntityByHang(int hangId) {
        List<DongSanPham> dongSanPhams = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new model.DongSanPham
                                              (dsp.id, dsp.ten, dsp.hangDienThoai)
                                              FROM DongSanPham dsp
                                              WHERE dsp.hangDienThoai.id = :hangId AND dsp.trangThai = true
                                              ORDER BY dsp.ten
                                               """);
            query.setParameter("hangId", hangId);
            dongSanPhams = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return dongSanPhams;
    }

    // 2. get by id
    public static DongSanPham getById(int id) {
        DongSanPham dongSanPham = null;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            dongSanPham = session.get(DongSanPham.class, id);
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        }
        return dongSanPham;
    }

    // 3. get all dongSanPhamResponse by status
    public List<DongSanPhamResponse> getDongSPResponseByStatus(boolean status) {
        List<DongSanPhamResponse> dongSanPhamResponses = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.DongSanPhamResponse
                                              (dsp.id, dsp.ten, dsp.trangThai, hdt.tenHang)
                                              FROM DongSanPham dsp
                                              INNER JOIN dsp.hangDienThoai hdt
                                              WHERE dsp.trangThai = :status
                                              ORDER BY dsp.ten
                                               """);
            query.setParameter("status", status);
            dongSanPhamResponses = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return dongSanPhamResponses;
    }
    
    // 6. get by tenDongSP
    public static DongSanPham getByTenDongSP(String tenDongSP) {
        DongSanPham dongSanPham = null;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT dsp
                                              FROM DongSanPham dsp
                                              WHERE dsp.ten = :tenDongSP
                                               """);
            query.setParameter("tenDongSP", tenDongSP);
            dongSanPham = (DongSanPham) query.getSingleResult();
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        } catch (NoResultException e) {
            dongSanPham = null;
        }
        return dongSanPham;
    }

    // 5. add
    public boolean add(DongSanPham dsp) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(dsp);
            transaction.commit();
            check = true;
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return check;
    }

    // 7. update dongSanPham
    public static boolean update(DongSanPhamResponse dongSanPhamResponse) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();

            DongSanPham dsp = session.get(DongSanPham.class, dongSanPhamResponse.getId());
            dsp.setTen(dongSanPhamResponse.getTen());
            Hang hang = HangRepository.getByTenHang(dongSanPhamResponse.getTenHang());
            dsp.setHangDienThoai(hang);

            session.update(dsp);
            transaction.commit();

            check = true;
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        }
        return check;
    }
    
    // 8. changeStatus dongSanPham
    public void changeStatus(DongSanPhamResponse dongSanPhamResponse, boolean newStatus) {
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();

            DongSanPham dsp = session.get(DongSanPham.class, dongSanPhamResponse.getId());
            dsp.setTrangThai(newStatus);

            session.update(dsp);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        }
    }

    public static void main(String[] args) {
        DongSanPhamResponse dongSanPhamResponse = new DongSanPhamResponse();
        dongSanPhamResponse.setId(1);
        dongSanPhamResponse.setTen("Samsung S Series");
        dongSanPhamResponse.setTenHang("Samsung");

        System.out.println(update(dongSanPhamResponse));

//        System.out.println("ID: " + dsp.getId());
//        System.out.println("Ten: " + dsp.getTen());
//        System.out.println("TT: " + dsp.isTrangThai());
//        DongSanPham dsp = new DongSanPham();
//        dsp.setTen("Samsung A Series");
//        dsp.setTrangThai(true);
//        dsp.setHangDienThoai(HangRepository.getByTenHang("SamSung"));
//
//        System.out.println(add(dsp));
//        List<DongSanPhamResponse> dongSanPhamResponses = getAllActiveDongSP();
//        dongSanPhamResponses.forEach(d -> System.out.println(d.toString()));
//        List<DongSanPham> dongSanPhams = getAllByHang(1);
//        System.out.println(dongSanPhams.size());
//        dongSanPhams.forEach(d -> System.out.println(d.toString()));
//        DongSanPham dsp3 = getById(3);
//        System.out.println(dsp3.getTen());
    }
}
