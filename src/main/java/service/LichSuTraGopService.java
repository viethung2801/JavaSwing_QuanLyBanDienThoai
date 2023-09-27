/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.LichSuTraGop;
import model.PhieuTraGop;

/**
 *
 * @author Administrator
 */
public interface LichSuTraGopService {

    List<LichSuTraGop> getByID(int ID);

    String insert(LichSuTraGop lstg);

    String update(int idLichSuTraGop, LichSuTraGop lstg);

    String delete(int idLichSuTraGop);
}
