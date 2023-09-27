/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import repository.SanPhamThongKeRepository;
import service.SanPhamThongKeService;
import viewmodel.SanPhamThongKeResponse;

/**
 *
 * @author Ma
 */
public class SanPhamThongKeServiceImpl implements SanPhamThongKeService{

    SanPhamThongKeRepository repo = new SanPhamThongKeRepository();

    @Override
    public List<SanPhamThongKeResponse> getSPTKThang(int month, int year) {
        List<Object[]> lists = repo.getSPTKThang(month, year);
        List<SanPhamThongKeResponse> listRe = new ArrayList<>();
        for (Object[] list : lists) {
            String tenDT = (String) list[0];
            Long count =  (Long) list[1];
            //System.out.println(tenDT + ": " + count);
            listRe.add(new SanPhamThongKeResponse(tenDT, count));
        }
        
        //for (SanPhamThongKeResponse sp : listRe) {
        //    System.out.println(sp.toString());
        //}
        return listRe;
    }
    
    @Override
    public List<SanPhamThongKeResponse> getSPTKNam(int year) {
        List<Object[]> lists = repo.getSPTKNam(year);
        List<SanPhamThongKeResponse> listRe = new ArrayList<>();
        for (Object[] list : lists) {
            String tenDT = (String) list[0];
            Long count =  (Long) list[1];
            listRe.add(new SanPhamThongKeResponse(tenDT, count));
        }
        return listRe;
    }
    
    @Override
    public List<SanPhamThongKeResponse> getSPTKNgay(LocalDateTime ngayDau, LocalDateTime ngayCuoi) {
        List<Object[]> lists = repo.getSPTKNgay(ngayDau, ngayCuoi);
        List<SanPhamThongKeResponse> listRe = new ArrayList<>();
        for (Object[] list : lists) {
            String tenDT = (String) list[0];
            Long count =  (Long) list[1];
            listRe.add(new SanPhamThongKeResponse(tenDT, count));
        }
        return listRe;
    }

}
