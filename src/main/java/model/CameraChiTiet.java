package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CameraChiTiet")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CameraChiTiet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    
    @Column(name = "CameraChinh")
    private int cameraChinh;
    
    @Column(name = "cameraPhu")
    private int cameraPhu;
    
    @Column(name = "CameraGocRong")
    private int cameraGocRong;
    
    @Column(name = "CameraTele")
    private int cameraTele;

    @Override
    public String toString() {
        return "CameraChiTiet{" + "id=" + id + ", cameraChinh=" + cameraChinh + ", cameraPhu=" + cameraPhu + ", cameraGocRong=" + cameraGocRong + ", cameraTele=" + cameraTele + '}';
    }
    
}
