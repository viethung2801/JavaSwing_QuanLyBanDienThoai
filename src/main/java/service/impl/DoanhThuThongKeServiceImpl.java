/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.ArrayList;
import java.util.List;
import repository.DoanhThuThongKeRepository;
import service.DoanhThuThongKeService;
import viewmodel.DoanhThuThongKeResponse;

/**
 *
 * @author Ma
 */
public class DoanhThuThongKeServiceImpl implements DoanhThuThongKeService{

    DoanhThuThongKeRepository repo = new DoanhThuThongKeRepository();
    @Override
    public List<DoanhThuThongKeResponse> getDTTKThang(int year) {
        List<Object[]> lists = repo.getDTTKThang(year);
        List<DoanhThuThongKeResponse> listRe = new ArrayList<>();
        for (Object[] ob : lists) {
            int thang = (Integer) ob[0];
            Long doanhthu = (Long) ob[1];
            listRe.add(new DoanhThuThongKeResponse(thang, doanhthu));
        }
        return listRe;
    }

    @Override
    public List<DoanhThuThongKeResponse> getDTTKNam() {
        List<Object[]> lists = repo.getDTTKNam();
        List<DoanhThuThongKeResponse> listRe = new ArrayList<>();
        for (Object[] ob : lists) {
            int thang = (Integer) ob[0];
            Long doanhthu = (Long) ob[1];
            listRe.add(new DoanhThuThongKeResponse(thang, doanhthu));
        }
        return listRe;
    }
    
}
