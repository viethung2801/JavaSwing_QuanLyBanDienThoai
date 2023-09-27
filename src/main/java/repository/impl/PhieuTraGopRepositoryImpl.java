package repository.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import model.PhieuTraGop;
import org.hibernate.Session;
import repository.LichSuTraGopRepository;
import utility.HibernateUtil;

public class PhieuTraGopRepositoryImpl {

    private LichSuTraGopRepository lstgRepository = new LichSuTraGopRepositoryImpl();

    public boolean update(int id, PhieuTraGop phieuTraGop) {
        boolean check = false;
        PhieuTraGop ptg = getByID(id);
        ptg.setTrangThai(phieuTraGop.isTrangThai());
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            session.beginTransaction();
            session.update(ptg);
            session.getTransaction().commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check;
    }

    public boolean insert(PhieuTraGop phieuTraGop) {
        phieuTraGop.setMaPhieu(genarateMaPhieu());

        boolean check = false;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            session.beginTransaction();
            session.save(phieuTraGop);
            session.getTransaction().commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check;
    }

    public List<PhieuTraGop> getAll() {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "From PhieuTraGop";
            session.beginTransaction();
            Query query = session.createQuery(hql);
            List<PhieuTraGop> listAll = query.getResultList();
            session.getTransaction().commit();

            return listAll;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public PhieuTraGop getByID(int id) {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "From PhieuTraGop Where id = :id";
            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            PhieuTraGop ptg = (PhieuTraGop) query.getSingleResult();
            session.getTransaction().commit();

            return ptg;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public String genarateMaPhieu() {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT COUNT(*) FROM PhieuTraGop";
            session.beginTransaction();
            Query query = session.createQuery(hql);
            int count = Integer.parseInt(query.getSingleResult().toString());
            session.getTransaction().commit();
            return ("PTG" + ++count);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<PhieuTraGop> getByString(String s) {
        List<PhieuTraGop> listAll = new ArrayList<>();
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT ptg FROM PhieuTraGop ptg \n"
                    + "JOIN ptg.hoaDon hd\n"
                    + "JOIN hd.khachHang kh \n"
                    + "WHERE kh.hoTen LIKE :hoTen or kh.sdt LIKE :sdt \n"
                    + "		or ptg.maPhieu LIKE :maPhieu or hd.maHoaDon LIKE :maHoaDon";
            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("hoTen", "%" + s + "%");
            query.setParameter("sdt", "%" + s + "%");
            query.setParameter("maPhieu", "%" + s + "%");
            query.setParameter("maHoaDon", "%" + s + "%");
            listAll = query.getResultList();
            session.getTransaction().commit();

            return listAll;

        } catch (Exception e) {
            e.printStackTrace();
            return listAll;
        }
    }

    public List<PhieuTraGop> getByTimeAndTrangThai(LocalDate ngayBatDauDate, LocalDate ngayKetThuc, int trangThai) {
        String hql = "FROM PhieuTraGop\n"
                + "WHERE NgayTao BETWEEN '" + ngayBatDauDate + "' and '" + ngayKetThuc + "' ";
        switch (trangThai) {
            case 0:
                hql = hql;
                break;
            case 1:
                hql = hql + "AND TrangThai = 0";
                break;
            case 2:
                hql = hql + "AND TrangThai = 1";
                break;
            default:
                hql = hql;
        }
        System.out.println(trangThai);
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            session.beginTransaction();
            Query query = session.createQuery(hql);
            List<PhieuTraGop> listAll = query.getResultList();
            session.getTransaction().commit();

            return listAll;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void main(String[] args) {
        PhieuTraGopRepositoryImpl repositoryImpl = new PhieuTraGopRepositoryImpl();

        List<PhieuTraGop> list = repositoryImpl.getByString("1");
        for (PhieuTraGop phieuTraGop : list) {
            System.out.println(phieuTraGop.toString());
        }
    }
}
