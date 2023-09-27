package viewmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoaiBaoHanhResponse {

    private int id;
    private String ten;
    private String dieuKien;

    public LoaiBaoHanhResponse(String ten, String dieuKien) {
        this.ten = ten;
        this.dieuKien = dieuKien;
    }

    @Override
    public String toString() {
        return "LoaiBaoHanhResponse{" + "id=" + id + ", ten=" + ten + ", dieuKien=" + dieuKien + '}';
    }

    public Object[] toRowData() {
        return new Object[]{this.id, this.ten, this.dieuKien};
    }

    public Object[] toRowPBH() {
        return new Object[]{this.ten, this.dieuKien};
    }
}
