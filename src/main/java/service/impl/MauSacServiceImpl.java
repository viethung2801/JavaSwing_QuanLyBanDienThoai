package service.impl;

import java.util.List;
import model.MauSac;
import repository.MauSacRepository;
import service.MauSacService;
import viewmodel.MauSacResponse;

public class MauSacServiceImpl implements MauSacService {

    private MauSacRepository mauSacRepository = new MauSacRepository();

    @Override
    public List<MauSac> getAllEntityByStatus(boolean status) {
        return mauSacRepository.getAllEntityByStatus(status);
    }

    @Override
    public MauSac getByMa(String maMau) {
        return mauSacRepository.getByMa(maMau);
    }

    @Override
    public List<MauSacResponse> getAllResponseByStatus(boolean status) {
        return mauSacRepository.getAllResponseByStatus(status);
    }

    @Override
    public String add(MauSac mauSac) {
        boolean addResult = mauSacRepository.add(mauSac);
        return addResult ? "Thêm thành công!" : "Thêm thất bại!";
    }

    @Override
    public String update(MauSacResponse mauSacResponse) {
        boolean updateResult = mauSacRepository.update(mauSacResponse);
        return updateResult ? "Sửa thành công!" : "Sửa thất bại!";
    }

    @Override
    public String changeStatus(MauSacResponse mauSacResponse, boolean newStatus) {
        String message = newStatus ? "Khôi phục thành công!" : "Xóa thành công!";
        mauSacRepository.changeStatus(mauSacResponse, newStatus);
        return message;
    }

}
