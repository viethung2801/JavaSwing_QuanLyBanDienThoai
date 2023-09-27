package service.impl;

import java.util.List;
import model.DienThoai;
import repository.DienThoaiRepository;
import service.DienThoaiService;
import viewmodel.DienThoaiResponse;

public class DienThoaiServiceImpl implements DienThoaiService {

    private DienThoaiRepository dienThoaiRepository = new DienThoaiRepository();

    @Override
    public List<DienThoaiResponse> getAllResponseByStatus(boolean status) {
        return dienThoaiRepository.getAllResponseByStatus(status);
    }

    @Override
    public String add(DienThoai dienThoai) {
        boolean addResult = dienThoaiRepository.add(dienThoai);
        return addResult ? "Thêm thành công" : "Thêm thất bại";
    }

    @Override
    public DienThoai getByMaDT(String maDT) {
        return dienThoaiRepository.getByMaDT(maDT);
    }

    @Override
    public String update(DienThoaiResponse dienThoaiResponse) {
        boolean updateResult = dienThoaiRepository.update(dienThoaiResponse);
        return updateResult ? "Sửa thành công" : "Sửa thất bại";
    }

    @Override
    public String changeStatus(DienThoaiResponse dienThoaiResponse, boolean newStatus) {
        String message = newStatus ? "Khôi phục thành công!" : "Xóa thành công!";
        dienThoaiRepository.changeStatus(dienThoaiResponse, newStatus);
        return message;
    }

    @Override
    public List<DienThoaiResponse> getAllResponseByGiaBan(String order) {
        return dienThoaiRepository.getAllResponseByGiaBan(order);
    }

    @Override
    public List<DienThoaiResponse> searchAllResponseByName(String keyword) {
        return dienThoaiRepository.searchAllResponseByName(keyword);
    }

    @Override
    public List<DienThoaiResponse> getSanPhamHetHang() {
        return dienThoaiRepository.get5SanPhamHetHang();
    }

    @Override
    public List<DienThoaiResponse> getResponsesByHang(String tenHang) {
        return dienThoaiRepository.getResponsesByHang(tenHang);
    }

    @Override
    public List<DienThoaiResponse> filterResponses(int hangId, int mauSacId, int heDieuHanhId) {
        return dienThoaiRepository.filterResponses(hangId, mauSacId, heDieuHanhId);
    }

}
