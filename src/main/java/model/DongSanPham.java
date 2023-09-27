package model;

import java.util.Objects;
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
@Table(name = "DongSanPham")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DongSanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "Ten")
    private String ten;
    
    @Column(name = "TrangThai")
    private boolean trangThai;

    @ManyToOne
    @JoinColumn(name = "IdHang")
    private Hang hangDienThoai;

    public DongSanPham(String ten) {
        this.ten = ten;
    }
    
    public DongSanPham(int id, String ten) {
        this.id = id;
        this.ten = ten;
    }

    public DongSanPham(int id, String ten, Hang hangDienThoai) {
        this.id = id;
        this.ten = ten;
        this.hangDienThoai = hangDienThoai;
    }

    @Override
    public String toString() {
        return this.ten;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.ten);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DongSanPham other = (DongSanPham) obj;
        return Objects.equals(this.ten, other.ten);
    }
    
}
