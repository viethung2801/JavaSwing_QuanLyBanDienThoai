/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.List;
import model.LichSuTraGop;
import model.PhieuTraGop;
import repository.LichSuTraGopRepository;
import repository.impl.LichSuTraGopRepositoryImpl;
import service.LichSuTraGopService;

/**
 *
 * @author Administrator
 */
public class LichSuTraGopServiceImpl implements LichSuTraGopService {

    private LichSuTraGopRepository repository = new LichSuTraGopRepositoryImpl();

    @Override
    public List<LichSuTraGop> getByID(int ID) {
        return repository.getByID(ID);
    }

    @Override
    public String insert(LichSuTraGop lstg) {
        boolean check = repository.insert(lstg);
        if (check) {
            return "Thêm thành công";
        } else {
            return "Thêm Thất bại";
        }
    }

    @Override
    public String update(int idLichSuTraGop, LichSuTraGop lstg) {
        boolean check = repository.update(idLichSuTraGop, lstg);
        if (check) {
            return "Sửa thành công";
        } else {
            return "Sửa Thất bại";
        }
    }

    @Override
    public String delete(int idLichSuTraGop) {
        boolean check = repository.delete(idLichSuTraGop);
        if (check) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

}
