package service;

import java.util.List;
import model.HeDieuHanh;
import viewmodel.HeDieuHanhResponse;

public interface HeDieuHanhService {
    
    List<HeDieuHanh> getAllEntityByStatus(boolean status);
    
    HeDieuHanh getByTen(String ten);
    
    String add(HeDieuHanh hdh);
    
    List<HeDieuHanhResponse> getAllResponse(boolean status);
    
    String update(HeDieuHanhResponse hdhResponse);
    
    String changeStatus(HeDieuHanhResponse hdhResponse, boolean newStatus);
}
