package service;

import java.util.List;
import model.Imei;
import viewmodel.ImeiResponse;

public interface ImeiService {

    Imei getByImei(String imei);

    String add(Imei imei);

    List<ImeiResponse> getResponsesWithDienThoaiNull();

    void update(ImeiResponse imeiResponse);

    List<ImeiResponse> getResponsesByIdDienThoaiAndStatus(int dienThoaiId, int trangThai);

    void deleteImeiWithDienThoaiNull();

    String delete(ImeiResponse imeiResponse);

    String updateImeiStr(ImeiResponse imeiResponse);

    void updateImeiTrangThai(String imeiStr, int trangThai);
}
