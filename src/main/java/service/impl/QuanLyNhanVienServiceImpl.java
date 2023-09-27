/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.List;
import model.NhanVien;
import model.TaiKhoan;
import repository.NhanVienRepository;
import service.QuanLyNhanVienService;
import viewmodel.NhanVienResponse;

/**
 *
 * @author Ma
 */
public class QuanLyNhanVienServiceImpl implements QuanLyNhanVienService {

    NhanVienRepository repo = new NhanVienRepository();

    @Override
    public String add(NhanVienResponse nvr) {
        TaiKhoan tk = new TaiKhoan(nvr.getTaiKhoan(), nvr.getMatKhau());
        NhanVien nv = new NhanVien(nvr.getId(), nvr.getHoTen(), nvr.isGioiTinh(), nvr.getSdt(), nvr.getNgaySinh(), nvr.getDiaChi(), nvr.getEmail(),
                nvr.isChucVu(), nvr.isTrangThai(), nvr.getHinhAnh(), tk);
        if (repo.add(nv) == true) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String update(NhanVienResponse nhanVienResponse, int id) {
        if (repo.update(nhanVienResponse, id) == true) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public String delete(int id) {
        if (repo.delete(id) == true) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public String recover(int id) {
        if (repo.recover(id) == true) {
            return "Khôi phục thành công";
        } else {
            return "Khôi phục thất bại";
        }
    }

    @Override
    public List<NhanVienResponse> getAll() {
        return repo.getAll();
    }

    @Override
    public List<NhanVienResponse> getAllLam() {
        return repo.getAllLam();
    }

    @Override
    public List<NhanVienResponse> getAllNghi() {
        return repo.getAllNghi();
    }

    @Override
    public List<NhanVienResponse> findByNameNVLam(String name) {
        return repo.findByNameNVLam(name);
    }

    @Override
    public List<NhanVienResponse> findByNameNVNghi(String name) {
        return repo.findByNameNVNghi(name);
    }

    @Override
    public List<NhanVienResponse> findByGioiTinhNVLam(boolean gt) {
        return repo.findByGioiTinhNVLam(gt);
    }

    @Override
    public List<NhanVienResponse> findByGioiTinhNVNghi(boolean gt) {
        return repo.findByGioiTinhNVNghi(gt);
    }

    @Override
    public List<NhanVienResponse> findByChucVuNVLam(boolean cv) {
        return repo.findByChucVuNVLam(cv);
    }

    @Override
    public List<NhanVienResponse> findByChucVuNVNghi(boolean cv) {
        return repo.findByChucVuNVNghi(cv);
    }

    @Override
    public List<NhanVienResponse> getTaiKhoanNhanVien() {
        return repo.getTaiKhoanNhanVien();
    }

    @Override
    public String updateMatKhau(NhanVienResponse nhanVienResponse, String mk) {
        if (repo.updateMatKhau(nhanVienResponse, mk) == true) {
            return "Đổi mật khẩu thành công";
        } else {
            return "Đổi mật khẩu thất bại";
        }
    }
}
