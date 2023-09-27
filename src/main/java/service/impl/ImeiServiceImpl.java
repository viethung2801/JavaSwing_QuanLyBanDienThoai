package service.impl;

import java.util.List;
import model.Imei;
import repository.ImeiRepository;
import service.ImeiService;
import viewmodel.ImeiResponse;

public class ImeiServiceImpl implements ImeiService {

    private ImeiRepository imeiRepository = new ImeiRepository();

    @Override
    public Imei getByImei(String imei) {
        return imeiRepository.getByImei(imei);
    }

    @Override
    public String add(Imei imei) {
        boolean addResult = imeiRepository.add(imei);
        return addResult ? "Thêm mới thành công!" : "Thêm mới thất bại!";
    }

    @Override
    public List<ImeiResponse> getResponsesWithDienThoaiNull() {
        return imeiRepository.getResponsesWithDienThoaiNull();
    }

    @Override
    public void update(ImeiResponse imeiResponse) {
        imeiRepository.update(imeiResponse);
    }

    @Override
    public List<ImeiResponse> getResponsesByIdDienThoaiAndStatus(int dienThoaiId, int trangThai) {
        return imeiRepository.getResponsesByIdDienThoaiAndStatus(dienThoaiId, trangThai);
    }

    @Override
    public void deleteImeiWithDienThoaiNull() {
        imeiRepository.deleteImeiWithDienThoaiNull();
    }

    @Override
    public String delete(ImeiResponse imeiResponse) {
        boolean deleteResult = imeiRepository.delete(imeiResponse);
        return deleteResult ? "Xóa thành công!" : "Xóa thất bại!";
    }

    @Override
    public String updateImeiStr(ImeiResponse imeiResponse) {
        boolean updateResult = imeiRepository.updateImeiStr(imeiResponse);
        return updateResult ? "Sửa thành công!" : "Sửa thất bại!";
    }

    @Override
    public void updateImeiTrangThai(String imeiStr, int trangThai) {
        imeiRepository.updateImeiTrangThai(imeiStr, trangThai);
    }

}
