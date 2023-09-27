package viewmodel;

public class SanPhamThongKeResponse {
    private String tenSp;
    private long slSanPham;

    public SanPhamThongKeResponse() {
    }

    public SanPhamThongKeResponse(String tenSp, long slSanPham) {
        this.tenSp = tenSp;
        this.slSanPham = slSanPham;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public long getSlSanPham() {
        return slSanPham;
    }

    public void setSlSanPham(long slSanPham) {
        this.slSanPham = slSanPham;
    }

    @Override
    public String toString() {
        return "SanPhamThongKeResponse{" + "tenSp=" + tenSp + ", slSanPham=" + slSanPham + '}';
    }
    
}
