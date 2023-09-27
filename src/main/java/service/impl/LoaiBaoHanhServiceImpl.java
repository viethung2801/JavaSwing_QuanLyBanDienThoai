package service.impl;

import java.util.List;
import model.LoaiBaoHanh;
import repository.LoaiBaoHanhRepository;
import service.LoaiBaoHanhService;
import viewmodel.LoaiBaoHanhResponse;

public class LoaiBaoHanhServiceImpl implements LoaiBaoHanhService {

    private LoaiBaoHanhRepository repository = new LoaiBaoHanhRepository();

    @Override
    public List<LoaiBaoHanhResponse> getAllLoaiBaoHanh() {
        return repository.getAll();
    }

    @Override
    public String addLoaiBaoHanh(LoaiBaoHanhResponse lbh) {
        boolean check = repository.add(new LoaiBaoHanh(lbh.getTen(), lbh.getDieuKien()));
        return check == true ? "Thêm thành công" : "Thêm thất bại";
    }

    @Override
    public String updateLoaiBaoHanh(LoaiBaoHanhResponse lbh, int id) {
        boolean check = repository.update(new LoaiBaoHanhResponse(id, lbh.getTen(), lbh.getDieuKien()));
        return check == true ? "Update thành công" : "Update thất bại";
    }

    @Override
    public LoaiBaoHanhResponse getOneLoaiBaoHanh(int id) {
        LoaiBaoHanh lbh = repository.getById(id);
        return new LoaiBaoHanhResponse(lbh.getTen(), lbh.getDieuKien());
    }

}
