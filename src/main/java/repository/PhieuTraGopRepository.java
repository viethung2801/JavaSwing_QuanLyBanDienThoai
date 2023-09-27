package repository;

public class PhieuTraGopRepository {

    // 1. add
//    public static boolean add(PhieuTraGop phieuTraGop) {
//        boolean check = false;
//        try {
//            Session session = HibernateUtil.getFACTORY().openSession();
//            Transaction transaction = session.beginTransaction();
//            session.save(phieuTraGop);
//            transaction.commit();
//            check = true;
//        } catch (HibernateException ex) {
//            ex.printStackTrace(System.out);
//        }
//        return check;
//    }
//
//    // 2. get by id
//    public static PhieuTraGop getById(int id) {
//        PhieuTraGop phieuTraGop = null;
//        try {
//            Session session = HibernateUtil.getFACTORY().openSession();
//            phieuTraGop = session.get(PhieuTraGop.class, id);
//        } catch (HibernateException ex) {
//            ex.printStackTrace(System.out);
//        }
//        return phieuTraGop;
//    }
//
//    // 3. update
//    public static boolean update() {
//        return true;
//    }
//
//    // 4. add new LichSuTraGop
//    public static boolean addNewLichSuTraGop() {
//        return false;
//    }
//
//    // 5. get all
//    public static List<PhieuTraGopResponse> getAll() {
//        List<PhieuTraGopResponse> phieuTraGopResponses = new ArrayList<>();
//
//        try {
//            Session session = HibernateUtil.getFACTORY().openSession();
//            Query query = session.createQuery("""
//                                              SELECT new viewmodel.PhieuTraGopResponse
//                                              (ptg.id, ptg.maPhieu, ptg.tongPhaiTra, ptg.laiSuat, ptg.kyHan, ptg.ngayTao, ptg.ngayDong, ptg.phaiTra, ptg.trangThai)
//                                              FROM PhieuTraGop ptg
//                                               """);
//            phieuTraGopResponses = query.getResultList();
//        } catch (HibernateException ex) {
//            ex.printStackTrace(System.out);
//        }
//        return phieuTraGopResponses;
//    }
//
//    public static void main(String[] args) {
//        List<PhieuTraGopResponse> phieuTraGopResponses = getAll();
//        System.out.println("Size: " + phieuTraGopResponses.size());
//        phieuTraGopResponses.forEach(ptg -> System.out.println(ptg.toString()));
//        System.out.println("2");
//
//        PhieuTraGop ptg2 = getById(2);
//
//        LichSuTraGop lstg2 = new LichSuTraGop();
//        lstg2.setMa("LS002");
//        lstg2.setNgayThanhToan(LocalDate.now());
//        lstg2.setTongTien(6_000_000L);
//        lstg2.setGhiChu("ghi chu 1");
//
//        ptg2.addLichSuTraGop(lstg2);
//        System.out.println(add(ptg2));
//
//        PhieuTraGop phieuTraGop = new PhieuTraGop();
//
//        phieuTraGop.setMaPhieu("PTG0001");
//        phieuTraGop.setTongPhaiTra(22_000_000L);
//        phieuTraGop.setLaiSuat(2.5F);
//        phieuTraGop.setKyHan(2);
//        phieuTraGop.setNgayTao(LocalDate.now());
//        phieuTraGop.setNgayDong(23);
//        phieuTraGop.setPhaiTra(15_000_000L);
//        phieuTraGop.setTrangThai(true);
////        phieuTraGop.setHoaDon(new HoaDon());
//
//        LichSuTraGop lstg1 = new LichSuTraGop();
//        lstg1.setMa("LS001");
//        lstg1.setNgayThanhToan(LocalDate.now());
//        lstg1.setTongTien(7_000_000L);
//        lstg1.setGhiChu("ghi chu 1");
//
//        phieuTraGop.addLichSuTraGop(lstg1);
//
//        System.out.println(add(phieuTraGop));
//    }
//    public boolean delete(String id);
//
//    boolean update(String id, PhieuTraGop phieuTraGop);
//
//    boolean insert(PhieuTraGop phieuTraGop);
//
//    List<PhieuTraGop> getAll();
//
//    List<PhieuTraGop> getByString(String s);
//
//    List<PhieuTraGop> getByTime(int index);
//
//    List<PhieuTraGop> getByTrangThai(int index);
}
