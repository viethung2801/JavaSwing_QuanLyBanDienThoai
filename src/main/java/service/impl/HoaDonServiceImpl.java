package service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import model.HoaDon;
import repository.HoaDonRepository;
import service.HoaDonService;
import viewmodel.HoaDonChiTietResponse;
import viewmodel.HoaDonResponse;

public class HoaDonServiceImpl implements HoaDonService {

    private HoaDonRepository hoaDonRepository = new HoaDonRepository();

    @Override
    public String add(HoaDon hoaDon) {
        boolean addResult = hoaDonRepository.add(hoaDon);
        return addResult ? "Tạo hóa đơn thành công" : "Tạo hóa đơn thất bại";
    }

    @Override
    public List<HoaDonResponse> getResponsesByTraGop(int traGop) {
        return hoaDonRepository.getResponsesByTraGop(traGop);
    }

    @Override
    public HoaDon getEntityById(int id) {
        return hoaDonRepository.getEntityById(id);
    }

    @Override
    public List<HoaDonChiTietResponse> getChiTietResponsesIdHoaDon(int idHoaDon) {
        return hoaDonRepository.getChiTietResponsesIdHoaDon(idHoaDon);
    }

    @Override
    public List<HoaDonResponse> searchHoaDon(String keyword) {
        return hoaDonRepository.searchHoaDon(keyword);
    }

    @Override
    public List<HoaDonResponse> filterHoaDonByDate(LocalDateTime start, LocalDateTime end, String kieuNgay) {
        return hoaDonRepository.filterHoaDonByDate(start, end, kieuNgay);
    }

    @Override
    public HoaDon getEntityByMa(String maHoaDon) {
        return hoaDonRepository.getEntityByMa(maHoaDon);
    }

}
