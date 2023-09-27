/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.type.descriptor.java.LocalDateTimeJavaDescriptor;
import utility.HibernateUtil;
import viewmodel.SanPhamThongKeResponse;

/**
 *
 * @author Ma
 */
public class SanPhamThongKeRepository {
          
    public List<Object[]> getSPTKThang(int month, int year) {
        List<Object[]> listSP = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              select 
                                              dt.tenDT, count(ct.imei)
                                              from Imei im join HoaDonChiTiet ct on im = ct.imei
                                              join DienThoai dt on dt = im.dienThoai
                                              join HoaDon hd on hd = ct.hoaDon
                                              where month(hd.ngayThanhToan) = :month and year(hd.ngayThanhToan) = :year
                                              group by dt.tenDT
                                               """);
            query.setParameter("month", month);
            query.setParameter("year", year);
            listSP = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return listSP;
    }
    
    public List<Object[]> getSPTKNam(int year) {
        List<Object[]> listSP = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              select 
                                              dt.tenDT, count(ct.imei)
                                              from Imei im join HoaDonChiTiet ct on im = ct.imei
                                              join DienThoai dt on dt = im.dienThoai
                                              join HoaDon hd on hd = ct.hoaDon
                                              where year(hd.ngayThanhToan) = :year
                                              group by dt.tenDT
                                               """);
            query.setParameter("year", year);
            listSP = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return listSP;
    }
    
    public List<Object[]> getSPTKNgay(LocalDateTime ngayDau, LocalDateTime ngayCuoi) {
        List<Object[]> listSP = new ArrayList<>();

        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Query query = session.createQuery("""
                                              select 
                                              dt.tenDT, count(ct.imei)
                                              from Imei im join HoaDonChiTiet ct on im = ct.imei
                                              join DienThoai dt on dt = im.dienThoai
                                              join HoaDon hd on hd = ct.hoaDon
                                              where hd.ngayThanhToan between :ngayDau and :ngayCuoi
                                              group by dt.tenDT
                                               """);
            query.setParameter("ngayDau", ngayDau);
            query.setParameter("ngayCuoi", ngayCuoi);
            listSP = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
        }
        return listSP;
    }
//    
//    public static void main(String[] args) {
//        //Date dau = Date."2023-2-1";
//        //LocalDate ngayDau = dau.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        LocalDateTime ngayDau = LocalDateTime.parse("2023-04-02 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        LocalDateTime ngayCuoi = LocalDateTime.parse("2023-04-05 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            //ngayCuoi = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-02-05 00:00:00");
//            
//        List<Object[]> lists = getSPTKNgay(ngayDau, ngayCuoi);
//            
//            List<SanPhamThongKeResponse> listRe = new ArrayList<>();
//        for (Object[] list : lists) {
//            String tenDT = (String) list[0];
//            Long count =  (Long) list[1];
//            System.out.println(tenDT + ": " + count);
//            listRe.add(new SanPhamThongKeResponse(tenDT, count));
//        }
//        
//        for (SanPhamThongKeResponse sp : listRe) {
//            System.out.println(sp.toString());
//        }
//
//    }
}
