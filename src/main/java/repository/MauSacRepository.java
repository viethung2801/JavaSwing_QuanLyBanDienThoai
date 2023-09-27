package repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import model.MauSac;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateUtil;
import viewmodel.MauSacResponse;

public class MauSacRepository {

    // 1. get all entity by trangThai
    public List<MauSac> getAllEntityByStatus(boolean status) {
        List<MauSac> mauSacs = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new model.MauSac
                                              (ms.id, ms.maMauSac, ms.tenMauSac, ms.trangThai)
                                              FROM MauSac ms
                                              WHERE ms.trangThai = :status
                                              ORDER BY ms.tenMauSac
                                               """);
            query.setParameter("status", status);
            mauSacs = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return mauSacs;
    }

    // 2. get by id
    public static MauSac getById(int id) {
        MauSac mauSac = null;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            mauSac = session.get(MauSac.class, id);
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        }
        return mauSac;
    }
    
    // 4. get mauSac by tenMauSac
    public static MauSac getByTen(String tenMauSac) {
        MauSac mauSac = null;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT ms
                                              FROM MauSac ms
                                              WHERE ms.tenMauSac = :tenMauSac
                                               """);
            query.setParameter("tenMauSac", tenMauSac);
            mauSac = (MauSac) query.getSingleResult();
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        } catch (NoResultException e) {
            mauSac = null;
        }
        return mauSac;
    }
    
    // 4. get mauSac by maMauSac
    public static MauSac getByMa(String maMauSac) {
        MauSac mauSac = null;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT ms
                                              FROM MauSac ms
                                              WHERE ms.maMauSac = :maMauSac
                                               """);
            query.setParameter("maMauSac", maMauSac);
            mauSac = (MauSac) query.getSingleResult();
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        } catch (NoResultException e) {
            mauSac = null;
        }
        return mauSac;
    }
    
    // 5. get all response by trangThai
    public List<MauSacResponse> getAllResponseByStatus(boolean status) {
        List<MauSacResponse> mauSacs = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              SELECT new viewmodel.MauSacResponse
                                              (ms.id, ms.maMauSac, ms.tenMauSac, ms.trangThai)
                                              FROM MauSac ms
                                              WHERE ms.trangThai = :status
                                               """);
            query.setParameter("status", status);
            mauSacs = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return mauSacs;
    }

    // 3. add
    public static boolean add(MauSac mauSac) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(mauSac);
            transaction.commit();
            check = true;
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return check;
    }
    
    public static void main(String[] args) {
        MauSac ms = new MauSac();
        ms.setMaMauSac("RE001");
        ms.setTenMauSac("Đỏ");
        
        System.out.println(add(ms));
    }

    // 6. update
    public boolean update(MauSacResponse mauSacResponse) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();

            MauSac mauSac = session.get(MauSac.class, mauSacResponse.getId());
            mauSac.setMaMauSac(mauSacResponse.getMaMauSac());
            mauSac.setTenMauSac(mauSacResponse.getTenMauSac());

            session.update(mauSac);
            transaction.commit();

            check = true;
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        }
        return check;
    }
    
    // 7. change status
    public void changeStatus(MauSacResponse mauSacResponse, boolean newStatus) {
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction transaction = session.beginTransaction();

            MauSac mauSac = session.get(MauSac.class, mauSacResponse.getId());
            mauSac.setTrangThai(newStatus);

            session.update(mauSac);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace(System.out);
        }
    }

//    public static void main(String[] args) {
        // 1
//        MauSacResponse mauSacResponse = new MauSacResponse();
//        mauSacResponse.setId(3);
//        mauSacResponse.setMaMauSac("BL09");
//        mauSacResponse.setTenMauSac("Xanh dương");
//
//        System.out.println(update(mauSacResponse));

//        List<MauSacResponse> mauSacs = getAllResponse();
//        mauSacs.forEach(m -> System.out.println(m.toString()));

        // get by ma
//        MauSac ms = getByMa("YE12");
//        System.out.println(ms.getId());
//        System.out.println(ms.getTenMauSac());
        
//        System.out.println(ms.getTenMauSac());
//        MauSac ms = new MauSac();
//        ms.setMaMauSac("PI12");
//        ms.setTenMauSac("Hồng");
//        ms.setTrangThai(true);
//        System.out.println(add(ms)); 
//        List<MauSac> mauSacs = getAllEntity();
//        mauSacs.forEach(ms -> System.out.println(ms.toString()));
//        MauSac ms1 = getById(1);
//        System.out.println(ms1.getTenMauSac());
//    }
}
