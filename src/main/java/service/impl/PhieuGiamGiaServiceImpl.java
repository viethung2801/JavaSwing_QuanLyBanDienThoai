package service.impl;

import java.util.List;
import model.PhieuGiamGia;
import repository.PhieuGiamGiaRepository;
import viewmodel.PhieuGiamGiaResponse;
import service.PhieuGiamGiaService;
import viewmodel.HoaDonResponse;

public class PhieuGiamGiaServiceImpl implements PhieuGiamGiaService {

    PhieuGiamGiaRepository pr = new PhieuGiamGiaRepository();

    @Override
    public List<PhieuGiamGiaResponse> getall() {
        return pr.getAll();
    }

    @Override
    public String add(PhieuGiamGia phieu) {
        if (pr.add(phieu) == true) {
            return "Thêm thành công";
        }
        return "Thêm thất bại";
    }

    @Override
    public String update(PhieuGiamGiaResponse phieu) {
        if (pr.update(phieu) == true) {
            return "Sửa thành công";
        }
        return "sửa thất bại";
    }

    public static void main(String[] args) {
        PhieuGiamGiaService qs = new PhieuGiamGiaServiceImpl();
        System.out.println(qs.getall());
    }

    @Override
    public List<PhieuGiamGiaResponse> getByStatus(int tt) {
        return pr.getByStatus(tt);
    }

    @Override
    public List<PhieuGiamGiaResponse> getByName(String name) {
        return pr.getByName(name);
    }

    @Override
    public List<PhieuGiamGiaResponse> getByMa(String ma) {
        return pr.getByMa(ma);
    }

    @Override
    public List<PhieuGiamGiaResponse> getAllForView(long tongTien) {
        return pr.getAllForView(tongTien);
    }

    @Override
    public Long tongTien() {
        return pr.tongTien();
    }

    @Override
    public Long tienGiam() {
        return pr.tienGiam();
    }

    @Override
    public Long tongDon() {
        return pr.soDon();
    }

}
