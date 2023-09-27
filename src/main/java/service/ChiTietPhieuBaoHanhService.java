package service;

import java.util.List;
import viewmodel.ChiTietPhieuBaoHanhReponse;

/**
 *
 * @author virus
 */
public interface ChiTietPhieuBaoHanhService {

    List<ChiTietPhieuBaoHanhReponse> getAllChiTietPhieuBaoHanh();

    String updateChiTietPhieuBaoHanh(ChiTietPhieuBaoHanhReponse pbh, int id);

    List<ChiTietPhieuBaoHanhReponse> searchChiTietPhieuBaoHanh(String tenKH);
}
