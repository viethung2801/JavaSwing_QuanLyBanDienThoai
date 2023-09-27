package service.impl;

import java.util.List;
import model.HeDieuHanh;
import repository.HeDieuHanhRepository;
import service.HeDieuHanhService;
import viewmodel.HeDieuHanhResponse;

public class HeDieuHanhServiceImpl implements HeDieuHanhService {
    
    private HeDieuHanhRepository heDieuHanhRepository = new HeDieuHanhRepository();

    @Override
    public List<HeDieuHanh> getAllEntityByStatus(boolean status) {
        return heDieuHanhRepository.getAllEntityByStatus(status);
    }

    @Override
    public HeDieuHanh getByTen(String ten) {
        return heDieuHanhRepository.getByTen(ten);
    }

    @Override
    public String add(HeDieuHanh hdh) {
        boolean addResult = heDieuHanhRepository.add(hdh);
        return addResult ? "Thêm thành công!" : "Thêm thất bại!";
    }

    @Override
    public List<HeDieuHanhResponse> getAllResponse(boolean status) {
        return heDieuHanhRepository.getAllResponse(status);
    }

    @Override
    public String update(HeDieuHanhResponse hdhResponse) {
        boolean updateResult = heDieuHanhRepository.update(hdhResponse);
        return updateResult ? "Sửa thành công!" : "Sửa thất bại!";
    }

    @Override
    public String changeStatus(HeDieuHanhResponse hdhResponse, boolean newStatus) {
        String message = newStatus ? "Khôi phục thành công!" : "Xóa thành công!";
        heDieuHanhRepository.changeStatus(hdhResponse, newStatus);
        return message;
    }
    
}
