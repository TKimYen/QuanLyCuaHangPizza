package QuanLyPizza.DAO;

import QuanLyPizza.DTO.HoaDon;

import java.sql.*;
import java.util.ArrayList;

public class HoaDonDAO {
    public ArrayList<HoaDon> getListHoaDon(){
        ArrayList<HoaDon> dshd = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Connection con = MyConnect.MyConnection();
            String sql = "SELECT * FROM hoadon";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getInt(1));
                hd.setMaKH(rs.getInt(2));
                hd.setMaNV(rs.getInt(3));
                hd.setNgayLap(rs.getDate(4));
                hd.setTongTien(rs.getInt(5));
                hd.setGhiChu(rs.getString(6));
                dshd.add(hd);
            }
            MyConnect.closeConnection(con);
        } catch(SQLException ex) {
            ex.printStackTrace();
        } 
        return dshd;
    }

    public boolean addHoaDon(HoaDon hd) {
        boolean result = false;
        try {
            Connection con = MyConnect.MyConnection();
            String sql1 = "UPDATE KhachHang SET TongChiTieu=TongChiTieu+" + hd.getTongTien() + " WHERE MaKH=" + hd.getMaKH();
            Statement st = con.createStatement();
            st.executeUpdate(sql1);
            String sql = "INSERT INTO hoadon(MaKH, MaNV, NgayLap, TongTien, GhiChu) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setInt(1, hd.getMaKH());
            prep.setInt(2, hd.getMaNV());
            prep.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
            prep.setInt(4, hd.getTongTien());
            prep.setString(5, hd.getGhiChu());
            result = prep.executeUpdate() > 0;
            MyConnect.closeConnection(con);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return result;
    }

    public int getMaHoaDonMoiNhat() {
        try {
            Connection con = MyConnect.MyConnection();
            String sql = "SELECT MAX(maHD) FROM hoadon";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next())
                return rs.getInt(1);
            MyConnect.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public ArrayList<HoaDon> getListHoaDon(Date dateMin, Date dateMax) {
        try {
            Connection con = MyConnect.MyConnection();
            String sql = "SELECT * FROM hoadon WHERE NgayLap BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setDate(1, dateMin);
            pre.setDate(2, dateMax);
            ResultSet rs = pre.executeQuery();

            ArrayList<HoaDon> dshd = new ArrayList<>();

            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getInt(1));
                hd.setMaKH(rs.getInt(2));
                hd.setMaNV(rs.getInt(3));
                hd.setNgayLap(rs.getDate(4));
                hd.setTongTien(rs.getInt(5));
                hd.setGhiChu(rs.getString(6));
                dshd.add(hd);
            }
            MyConnect.closeConnection(con);
            return dshd;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
