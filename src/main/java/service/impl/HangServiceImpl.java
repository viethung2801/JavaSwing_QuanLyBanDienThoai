package service.impl;

import java.util.List;
import model.Hang;
import repository.HangRepository;
import service.HangService;
import viewmodel.HangResponse;

public class HangServiceImpl implements HangService {

    private HangRepository hangRepository = new HangRepository();

    @Override
    public List<Hang> getAllEntityByStatus(boolean status) {
        return hangRepository.getAllEntityByStatus(status);
    }

    @Override
    public List<HangResponse> getAllResponseByStatus(boolean status) {
        return hangRepository.getAllResponseByStatus(status);
    }

    @Override
    public String add(Hang hang) {
        boolean addResult = hangRepository.add(hang);
        return addResult ? "Thêm mới thành công!" : "Thêm mới thất bại!";
    }

    @Override
    public Hang getByTenHang(String tenHang) {
        return hangRepository.getByTenHang(tenHang);
    }

    @Override
    public String update(HangResponse hangResponse) {
        boolean updateResult = hangRepository.update(hangResponse);
        return updateResult ? "Sửa thành công!" : "Sửa thất bại!";
    }

    @Override
    public String changeStatus(HangResponse hangResponse, boolean newStatus) {
        String message = newStatus ? "Khôi phục thành công!" : "Xóa thành công!";
        hangRepository.changeStatus(hangResponse, newStatus);
        return message;
    }

}
