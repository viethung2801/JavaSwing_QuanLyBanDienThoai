package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "IMEI")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Imei {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    
    @Column(name = "IMEI")
    private String imei;
    
    @Column(name = "TrangThai")
    private int trangThai;
    /*
    0 - chưa bán
    1 - đã bán
    2 - đang trong giỏ
    */
    
    @ManyToOne
    @JoinColumn(name = "IdDienThoai")
    private DienThoai dienThoai;

    public Imei(String imei) {
        this.imei = imei;
    }
    
}
