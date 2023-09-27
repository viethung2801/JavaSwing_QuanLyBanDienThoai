package service;

import java.util.List;
import model.Hang;
import viewmodel.HangResponse;

public interface HangService {
    
     List<Hang> getAllEntityByStatus(boolean status);
    
    List<HangResponse> getAllResponseByStatus(boolean status);
    
    String add(Hang hang);
    
    String update(HangResponse hangResponse);
    
    Hang getByTenHang(String tenHang);
    
    String changeStatus(HangResponse hangResponse, boolean newStatus);
    
}
