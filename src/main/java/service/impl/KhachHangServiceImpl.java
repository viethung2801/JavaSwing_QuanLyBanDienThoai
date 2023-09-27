package service.impl;

import java.util.List;
import model.KhachHang;
import repository.KhachHangRepository;
import viewmodel.KhachHangResponse;
import service.KhachHangService;

public class KhachHangServiceImpl implements KhachHangService {

    private KhachHangRepository repo = new KhachHangRepository();

    @Override
    public List<KhachHangResponse> getAll() {
        return repo.getAll();
    }

    @Override
    public String add(KhachHang kh) {
        if (repo.add(kh) == true) {
            return "Thêm thành công";
        }
        return "Thêm thất bại";
    }

    @Override
    public String update(KhachHangResponse kh) {
        if (repo.update(kh) == true) {
            return "Sửa thành công";
        }
        return "Sửa thất bại";
    }

    @Override
    public void updateKhoiPhuc(KhachHangResponse kh, int trangThai) {
        repo.updateKhoiPhuc(kh, trangThai);
    }

    @Override
    public List<KhachHangResponse> findBySdt(String sdt, int trangThai) {
        return repo.findBySDT(sdt, trangThai);
    }

    @Override
    public List<KhachHangResponse> sortByName(Boolean c, int trangThai) {
        return repo.sortByName(c, trangThai);
    }

    @Override
    public List<KhachHangResponse> getAllTheTichDiem() {
        return repo.getAllTheTichDiem();
    }

    @Override
    public List<KhachHangResponse> findByMa(String maThe) {
        return repo.findByMa(maThe);

    }

    @Override
    public KhachHangResponse getKhachHangByEmail(String email) {
        return repo.getKhachHangByEmail(email);
    }

    @Override
    public KhachHangResponse getKhachHangById(int id) {
        return repo.getKhachHangById(id);
    }

    @Override
    public KhachHangResponse getKhachHangByMaThe(String maThe) {
        return repo.getKhachHangByMaThe(maThe);
    }

    @Override
    public String updateDiem(KhachHangResponse kh, int soDiem) {
        if (repo.updateDiemTichLuy(kh, soDiem) == true) {
            return "Sửa thành công";
        }
        return "Sửa thất bại";
    }

    @Override
    public List<KhachHangResponse> getTop3KhachHang() {
        return repo.getTop3();
    }

    @Override
    public List<KhachHangResponse> getAllResponseByStatus(int status) {
        return repo.getAllResponseByStatus(status);
    }

    @Override
    public KhachHangResponse getKhachHangBySdt(String sdt) {
        return repo.getKhachHangByEmailOrSDT(sdt);
    }

}
