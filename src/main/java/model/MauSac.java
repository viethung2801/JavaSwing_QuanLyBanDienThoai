package model;

import java.util.Objects;
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
@Table(name = "MauSac")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MauSac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "MaMauSac")
    private String maMauSac;

    @Column(name = "TenMauSac")
    private String tenMauSac;

    @Column(name = "TrangThai")
    private boolean trangThai;

    public MauSac(String maMauSac) {
        this.maMauSac = maMauSac;
    }

    public MauSac(int id, String maMauSac, String tenMauSac) {
        this.id = id;
        this.maMauSac = maMauSac;
        this.tenMauSac = tenMauSac;
    }

    public MauSac(int id, String tenMauSac) {
        this.id = id;
        this.tenMauSac = tenMauSac;
    }

    @Override
    public String toString() {
        return this.tenMauSac;
    }

}
