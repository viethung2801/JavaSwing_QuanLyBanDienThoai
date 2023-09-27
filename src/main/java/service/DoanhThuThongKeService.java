/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import viewmodel.DoanhThuThongKeResponse;

/**
 *
 * @author Ma
 */
public interface DoanhThuThongKeService {
    
    List<DoanhThuThongKeResponse> getDTTKThang(int year);
    
    List<DoanhThuThongKeResponse> getDTTKNam();
}
