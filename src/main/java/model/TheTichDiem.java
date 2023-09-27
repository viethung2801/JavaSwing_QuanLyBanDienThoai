package model;

import java.time.LocalDate;
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
@Table(name = "TheTichDiem")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TheTichDiem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int idThe;

    @Column(name = "MaThe")
    private String maThe;

    @Column(name = "NgayKichHoat")
    private LocalDate ngayKichHoat;

    @Column(name = "SoDiem")
    private int soDiem;

    @Column(name = "TrangThai")
    private boolean trangThai;
}
