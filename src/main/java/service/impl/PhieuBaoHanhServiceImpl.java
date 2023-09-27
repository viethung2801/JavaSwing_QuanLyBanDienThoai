package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import model.ChiTietPhieuBaoHanh;
import model.LoaiBaoHanh;
import model.PhieuBaoHanh;
import repository.LoaiBaoHanhRepository;
import repository.PhieuBaoHanhRepository;
import service.PhieuBaoHanhService;
import viewmodel.LoaiBaoHanhResponse;
import viewmodel.PhieuBaoHanhResponse;

public class PhieuBaoHanhServiceImpl implements PhieuBaoHanhService {

    private LoaiBaoHanhRepository lbh = new LoaiBaoHanhRepository();
    private PhieuBaoHanhRepository repository = new PhieuBaoHanhRepository();

    @Override
    public List<PhieuBaoHanhResponse> getAll() {
        return repository.getAll();
    }

    @Override
    public List<PhieuBaoHanhResponse> getAllStatus(boolean status) {
        return repository.getList(status);
    }

    @Override
    public List<PhieuBaoHanhResponse> getAllListSearch(String tenKH) {
        return repository.getListSearch(tenKH);
    }

    @Override
    public List<String> getAllLoaiBaoHanh() {
        List<LoaiBaoHanhResponse> list = lbh.getAll();
        List<String> listString = new ArrayList<>();
        for (LoaiBaoHanhResponse lbh : list) {
            listString.add(lbh.getTen());
        }
        return listString;
    }

    @Override
    public String add(PhieuBaoHanh pbh, ChiTietPhieuBaoHanh ctPBH) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String updateMoTa(PhieuBaoHanhResponse pbh, int id) {
        boolean check = repository.updateMotaPBH(pbh, id);
        return check == true ? "Update thành công" : "Update thất bại";
    }

    @Override
    public PhieuBaoHanhResponse getPBHByID(int id) {
        return repository.getPBHByID(id);
    }

    @Override
    public void add(PhieuBaoHanh phieuBaoHanh) {
        repository.add(phieuBaoHanh);
    }

    @Override
    public Set<LoaiBaoHanh> getAllLBH(int id) {
        return repository.getAllLBHByPBHID(id);
    }

}
