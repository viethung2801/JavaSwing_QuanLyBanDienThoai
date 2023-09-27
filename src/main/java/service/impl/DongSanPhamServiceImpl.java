package service.impl;

import java.util.List;
import model.DongSanPham;
import repository.DongSanPhamRepository;
import service.DongSanPhamService;
import viewmodel.DongSanPhamResponse;

public class DongSanPhamServiceImpl implements DongSanPhamService {

    private DongSanPhamRepository dongSanPhamRepository = new DongSanPhamRepository();

    @Override
    public List<DongSanPham> getAllEntityByHang(int id) {
        return dongSanPhamRepository.getAllEntityByHang(id);
    }

    @Override
    public List<DongSanPhamResponse> getAllDongSPResponseByStatus(boolean status) {
        return dongSanPhamRepository.getDongSPResponseByStatus(status);
    }

    @Override
    public DongSanPham getByTenDongSP(String ten) {
        return dongSanPhamRepository.getByTenDongSP(ten);
    }

    @Override
    public String add(DongSanPham dongSanPham) {
        boolean addResult = dongSanPhamRepository.add(dongSanPham);
        return addResult ? "Thêm mới thành công!" : "Thêm mới thất bại!";
    }

    @Override
    public String update(DongSanPhamResponse dongSanPhamResponse) {
        boolean updateResult = dongSanPhamRepository.update(dongSanPhamResponse);
        return updateResult ? "Sửa thành công!" : "Sửa thất bại!";
    }

    @Override
    public String changeStatus(DongSanPhamResponse dongSanPhamResponse, boolean newStatus) {
        dongSanPhamRepository.changeStatus(dongSanPhamResponse, newStatus);
        String message = newStatus ? "Khôi phục thành công!" : "Xóa thành công!";
        return message;
    }

}
