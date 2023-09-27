package service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import model.HoaDon;
import viewmodel.HoaDonChiTietResponse;
import viewmodel.HoaDonResponse;

public interface HoaDonService {

    String add(HoaDon hoaDon);

    List<HoaDonResponse> getResponsesByTraGop(int traGop);
    
    HoaDon getEntityById(int id);
    
    List<HoaDonChiTietResponse> getChiTietResponsesIdHoaDon(int idHoaDon);
    
    List<HoaDonResponse> searchHoaDon(String keyword);
    
    List<HoaDonResponse> filterHoaDonByDate(LocalDateTime start, LocalDateTime end, String kieuNgay);
    
    HoaDon getEntityByMa(String maHoaDon);
    
}
