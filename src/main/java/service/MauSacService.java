package service;

import java.util.List;
import model.MauSac;
import viewmodel.MauSacResponse;

public interface MauSacService {

    List<MauSac> getAllEntityByStatus(boolean status);
    
    MauSac getByMa(String maMau);
    
    List<MauSacResponse> getAllResponseByStatus(boolean status);
    
    String add(MauSac mauSac);
    
    String update(MauSacResponse mauSacResponse);
    
    String changeStatus(MauSacResponse mauSacResponse, boolean newStatus);
    
}
