/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;
import model.LichSuTraGop;
import model.PhieuTraGop;

/**
 *
 * @author Administrator
 */
public interface LichSuTraGopRepository {

    List<LichSuTraGop> getByID(int ID);

    boolean insert(LichSuTraGop lstg);

    boolean update(int idLichSuTraGop, LichSuTraGop lstg);

    boolean delete(int idLichSuTraGop);

    String generateMaLSTG();
}
