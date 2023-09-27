/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import utility.HibernateUtil;
import viewmodel.DienThoaiBanChayViewModel;

/**
 *
 * @author Administrator
 */
public class TrangChuRepository {

    public long getDoanhThu() {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT SUM(hd.tongTien) FROM HoaDon hd WHERE CONVERT(DATE, hd.ngayThanhToan) = :today";
            Query query = session.createQuery(hql);
            query.setParameter("today", LocalDate.now());
            Long tongTien = (Long) query.getSingleResult();
            return tongTien;

        } catch (Exception e) {
            //e.printStackTrace();
            return 0;
        }
    }

    public int getSoDonHangTrongNgay() {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT Count(*) FROM HoaDon hd WHERE CONVERT(DATE, hd.ngayThanhToan) = :today";
            Query query = session.createQuery(hql);
            query.setParameter("today", LocalDate.now());
            Long soDon = (Long) query.getSingleResult();
            return Integer.parseInt(soDon.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSoDonBaoHanhTrongNgay() {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT count(*) FROM ChiTietPhieuBaoHanh ctphb WHERE CONVERT(DATE, ctphb.ngayMuaHang) = :today";
            Query query = session.createQuery(hql);
            query.setParameter("today", LocalDate.now());
            Long soDon = (Long) query.getSingleResult();
            return Integer.parseInt(soDon.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSoDonHuyTrongNgay() {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            String hql = "SELECT Count(*) FROM HoaDon hd WHERE CONVERT(DATE, hd.ngayThanhToan) = :today ";
            Query query = session.createQuery(hql);
            query.setParameter("today", LocalDate.now());
            Long soDon = (Long) query.getSingleResult();
            return Integer.parseInt(soDon.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<DienThoaiBanChayViewModel> getTop5DienThoaiBanChay() {
        List<DienThoaiBanChayViewModel> dienThoaiBanChayViewModels = new ArrayList<>();
        LocalDate today = LocalDate.now();
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              Select new viewmodel.DienThoaiBanChayViewModel( dt.tenDT, COUNT(im.id)) From Imei im
                                              INNER JOIN HoaDonChiTiet ct on im = ct.imei
                                              INNER JOIN DienThoai dt on dt = im.dienThoai
                                              INNER JOIN HoaDon hd on hd = ct.hoaDon
                                              WHERE im.trangThai = 1 and CONVERT(DATE, hd.ngayThanhToan) = :today
                                              GROUP BY dt.tenDT
                                              ORDER BY COUNT(im.id) DESC
                                               """);
            query.setParameter("today", today);
            query.setMaxResults(5);
            dienThoaiBanChayViewModels = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return dienThoaiBanChayViewModels;
    }

    public List<DienThoaiBanChayViewModel> getTop20DienThoaiSapHetHang() {
        return null;
    }

    public List<DienThoaiBanChayViewModel> getTop5KhachHang() {
        return null;
    }

//    public static void main(String[] args) {
////        long temp = new TrangChuRepository().getSoDonBaoHanhTrongNgay();
//        new TrangChuRepository().getTop5DienThoaiBanChay().forEach((t) -> {
//            System.out.println(t.toString());
//        });
//    }
}
