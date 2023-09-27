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
@Table(name = "LoaiBaoHanh")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoaiBaoHanh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "TenLoaiBH")
    private String ten;

    @Column(name = "DieuKienBaoHanh")
    private String dieuKien;

    public LoaiBaoHanh(String ten, String dieuKien) {
        this.ten = ten;
        this.dieuKien = dieuKien;
    }

    @Override
    public String toString() {
        return "LoaiBaoHanh{" + "id=" + id + ", ten=" + ten + ", dieuKien=" + dieuKien + '}';
    }

    public Object[] toRowData() {
        return new Object[]{this.ten, this.dieuKien};
    }
}
