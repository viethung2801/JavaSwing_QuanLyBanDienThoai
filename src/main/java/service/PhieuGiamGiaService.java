package service;

import java.util.List;
import model.PhieuGiamGia;
import viewmodel.HoaDonResponse;
import viewmodel.PhieuGiamGiaResponse;

public interface PhieuGiamGiaService {

    List<PhieuGiamGiaResponse> getall();

    String add(PhieuGiamGia phieu);

    String update(PhieuGiamGiaResponse phieu);

    List<PhieuGiamGiaResponse> getByStatus(int tt);

    List<PhieuGiamGiaResponse> getByName(String name);

    List<PhieuGiamGiaResponse> getByMa(String ma);

    List<PhieuGiamGiaResponse> getAllForView(long tongTien);

    Long tongTien();

    Long tienGiam();

    Long tongDon();

}
