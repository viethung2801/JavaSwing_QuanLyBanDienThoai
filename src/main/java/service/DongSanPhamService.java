package service;

import java.util.List;
import model.DongSanPham;
import viewmodel.DongSanPhamResponse;

public interface DongSanPhamService {

    List<DongSanPham> getAllEntityByHang(int id);

    List<DongSanPhamResponse> getAllDongSPResponseByStatus(boolean status);

    DongSanPham getByTenDongSP(String ten);

    String add(DongSanPham dongSanPham);

    String update(DongSanPhamResponse dongSanPhamResponse);

    String changeStatus(DongSanPhamResponse dongSanPhamResponse, boolean newStatus);
    
}
