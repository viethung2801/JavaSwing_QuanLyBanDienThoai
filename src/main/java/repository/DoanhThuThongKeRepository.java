/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import utility.HibernateUtil;
import viewmodel.DoanhThuThongKeResponse;

/**
 *
 * @author Ma
 */
public class DoanhThuThongKeRepository {
    
    public List<Object[]> getDTTKThang(int year) {
        List<Object[]> listSP = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              select month(hd.ngayThanhToan), sum(hd.tongTien - hd.tienGiam)
                                              from HoaDon hd
                                              where year(hd.ngayThanhToan) = :year
                                              group by month(hd.ngayThanhToan)
                                               """);
            query.setParameter("year", year);
            listSP = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return listSP;
    }
    
    public List<Object[]> getDTTKNam() {
        List<Object[]> listSP = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              select year(hd.ngayThanhToan), sum(hd.tongTien - hd.tienGiam)
                                              from HoaDon hd
                                              group by year(hd.ngayThanhToan)
                                               """);
            listSP = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return listSP;
    }
    
    public static List<Object[]> getDTTKNgay(LocalDateTime ngayDau, LocalDateTime ngayCuoi) {
        List<Object[]> listSP = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              select DATE(hd.ngayThanhToan), sum(hd.tongTien - hd.tienGiam) 
                                              from HoaDon hd
                                              where hd.ngayThanhToan between :ngayDau and :ngayCuoi
                                              group by DATE(hd.ngayThanhToan)
                                               """);
            query.setParameter("ngayDau", ngayDau);
            query.setParameter("ngayCuoi", ngayCuoi);
            listSP = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return listSP;
    }
    
    public static List<Object[]> getDoanhThu(LocalDate ngayDau, LocalDate ngayCuoi) {
        List<Object[]> listSP = new ArrayList<>();
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              select DATE(hd.ngayThanhToan), sum(hd.tongTien - hd.tienGiam) 
                                              from HoaDon hd
                                              where DATE(hd.ngayThanhToan) between :ngayDau and :ngayCuoi
                                              group by DATE(hd.ngayThanhToan)
                                               """);
            query.setParameter("ngayDau", ngayDau);
            query.setParameter("ngayCuoi", ngayCuoi);
            listSP = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return listSP;
    }
    
    
    public static void main(String[] args) {
//        List<DoanhThuThongKeResponse> lists = new ArrayList<>();
//        LocalDateTime ngayDau = LocalDateTime.parse("2023-03-04 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        LocalDateTime ngayCuoi = LocalDateTime.parse("2023-04-06 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        for (Object[] ob : getDTTKNgay(ngayDau, ngayCuoi)) {
//            LocalDateTime ngay = (LocalDateTime) ob[0];
//            Long doanhthu = (Long) ob[1];
//            lists.add(new DoanhThuThongKeResponse(ngay, doanhthu));
//        }
//        
//        for (DoanhThuThongKeResponse list : lists) {
//            System.out.println(list.toString());
//        }
        LocalDate ngayDau = LocalDate.parse("2023-04-04");
        LocalDate ngayCuoi = LocalDate.parse("2023-04-07");
        for (Object[] ob : getDoanhThu(ngayDau, ngayCuoi)) {
            System.out.println("Thằng : " + ob);
        }
    }
}
