package service;

import java.time.LocalDate;
import java.util.List;
import model.PhieuTraGop;
import viewmodel.PhieuTraGopViewModel;

public interface PhieuTraGopService {

    String insert(PhieuTraGop phieuTraGop);

    PhieuTraGop getByID(int id);

    String update(int id, PhieuTraGop phieuTraGop);

    List<PhieuTraGopViewModel> getAll();

    List<PhieuTraGopViewModel> getByString(String s);

//    List<PhieuTraGopViewModel> getByTime(int index);
//    List<PhieuTraGopViewModel> getByTrangThai(int index);
    List<PhieuTraGopViewModel> getByTimeAndTrangThai(LocalDate ngayBatDauDate, LocalDate ngayKetThuc, int trangThai);
}
