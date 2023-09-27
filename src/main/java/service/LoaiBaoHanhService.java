package service;

import java.util.List;
import viewmodel.LoaiBaoHanhResponse;

public interface LoaiBaoHanhService {

    List<LoaiBaoHanhResponse> getAllLoaiBaoHanh();

    String addLoaiBaoHanh(LoaiBaoHanhResponse lbh);

    String updateLoaiBaoHanh(LoaiBaoHanhResponse lbh, int id);

    LoaiBaoHanhResponse getOneLoaiBaoHanh(int id);
}
