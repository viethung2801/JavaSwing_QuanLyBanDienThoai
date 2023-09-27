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
@Table(name = "HeDieuHanh")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HeDieuHanh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "Ten")
    private String ten;
    
    @Column(name = "TrangThai")
    private boolean trangThai;

    public HeDieuHanh(String ten) {
        this.ten = ten;
    }

    public HeDieuHanh(int id, String ten) {
        this.id = id;
        this.ten = ten;
    }
    
    @Override
    public String toString() {
        return this.ten;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.ten);
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
        final HeDieuHanh other = (HeDieuHanh) obj;
        return Objects.equals(this.ten, other.ten);
    }

}
