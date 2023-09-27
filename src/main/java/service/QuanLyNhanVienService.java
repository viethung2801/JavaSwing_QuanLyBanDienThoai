/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.NhanVien;
import viewmodel.NhanVienResponse;

/**
 *
 * @author Ma
 */
public interface QuanLyNhanVienService {

    String add(NhanVienResponse nhanVienResponse);

    String update(NhanVienResponse nhanVienResponse, int id);

    String updateMatKhau(NhanVienResponse nhanVienResponse, String mk);

    String delete(int id);

    String recover(int id);

    List<NhanVienResponse> getAll();

    List<NhanVienResponse> getAllLam();

    List<NhanVienResponse> getAllNghi();

    List<NhanVienResponse> getTaiKhoanNhanVien();

    List<NhanVienResponse> findByNameNVLam(String name);

    List<NhanVienResponse> findByNameNVNghi(String name);

    List<NhanVienResponse> findByGioiTinhNVLam(boolean gt);

    List<NhanVienResponse> findByGioiTinhNVNghi(boolean gt);

    List<NhanVienResponse> findByChucVuNVLam(boolean cv);

    List<NhanVienResponse> findByChucVuNVNghi(boolean cv);

}
