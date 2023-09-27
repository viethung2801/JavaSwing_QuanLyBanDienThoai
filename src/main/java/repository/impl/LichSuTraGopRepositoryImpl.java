/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.util.List;
import javax.persistence.Query;
import model.LichSuTraGop;
import model.PhieuTraGop;
import org.hibernate.Session;
import repository.LichSuTraGopRepository;
import utility.HibernateUtil;

/**
 *
 * @author Administrator
 */
public class LichSuTraGopRepositoryImpl implements LichSuTraGopRepository {

    @Override
    public List<LichSuTraGop> getByID(int ID) {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "From LichSuTraGop Where idPhieuTraGop = :id";

            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("id", ID);
            List<LichSuTraGop> list = query.getResultList();
            session.getTransaction().commit();

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public LichSuTraGop getByIDLSTG(int ID) {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "From LichSuTraGop Where id = :id";

            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("id", ID);
            LichSuTraGop lstg = (LichSuTraGop) query.getSingleResult();
            session.getTransaction().commit();

            return lstg;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insert(LichSuTraGop lstg) {
        if (lstg.getMa().equalsIgnoreCase("")) {
            lstg.setMa(generateMaLSTG());
        }
        boolean check = false;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            session.beginTransaction();
            session.save(lstg);
            session.getTransaction().commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check;
    }

    @Override
    public boolean update(int idLichSuTraGop, LichSuTraGop lstg) {
        //lay thang can update
        LichSuTraGop lstgUpdate = getByIDLSTG(idLichSuTraGop);
        //set du lieu
        lstgUpdate.setMa(lstg.getMa());
        lstgUpdate.setGhiChu(lstg.getGhiChu());
        lstgUpdate.setNgayThanhToan(lstg.getNgayThanhToan());
        lstgUpdate.setTongTien(lstg.getTongTien());

        boolean check = false;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            session.beginTransaction();
            session.update(lstgUpdate);

            session.getTransaction().commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check;
    }

    @Override
    public boolean delete(int idLichSuTraGop) {

        boolean check = false;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "DELETE FROM LichSuTraGop WHERE id = :id";
            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("id", idLichSuTraGop);
            int result = query.executeUpdate();
            session.getTransaction().commit();
            if (result > 0) {
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check;
    }

    @Override
    public String generateMaLSTG() {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT COUNT(*) FROM LichSuTraGop";
            session.beginTransaction();
            Query query = session.createQuery(hql);
            int count = Integer.parseInt(query.getSingleResult().toString());
            session.getTransaction().commit();
            return ("LSTG" + ++count);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    public static void main(String[] args) {
//        System.out.println(new LichSuTraGopRepositoryImpl().generateMaLSTG());
//    }
}
