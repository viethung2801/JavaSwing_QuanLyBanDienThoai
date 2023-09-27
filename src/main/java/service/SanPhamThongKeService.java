/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.time.LocalDateTime;
import java.util.List;
import viewmodel.SanPhamThongKeResponse;

/**
 *
 * @author Ma
 */
public interface SanPhamThongKeService {
    List<SanPhamThongKeResponse> getSPTKThang(int month, int year);
    
    List<SanPhamThongKeResponse> getSPTKNam(int year);
    
    List<SanPhamThongKeResponse> getSPTKNgay(LocalDateTime ngayDau, LocalDateTime ngayCuoi);
}
