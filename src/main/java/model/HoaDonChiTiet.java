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
@Table(name = "HoaDonChiTiet")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HoaDonChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "IdHoaDon")
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "IdImei")
    private Imei imei;

    @Override
    public String toString() {
        return "HoaDonChiTiet{" + "id=" + id + ", hoaDon=" + hoaDon + ", imei=" + imei + '}';
    }

}
