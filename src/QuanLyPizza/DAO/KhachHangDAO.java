/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLyPizza.DAO;

import QuanLyPizza.DTO.KhachHang;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author User
 */
public class KhachHangDAO {

    public ArrayList<KhachHang> getListKhachHang() {
        try {
            Connection con = MyConnect.MyConnection();
            String sql = "SELECT * FROM khachhang WHERE TinhTrang=1";
            PreparedStatement pre = con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<KhachHang> dskh = new ArrayList<>();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getInt(1));
                kh.setHo(rs.getString(2));
                kh.setTen(rs.getString(3));
                kh.setGioiTinh(rs.getString(4));
                kh.setTongChiTieu(rs.getInt(5));
                dskh.add(kh);
            }
            MyConnect.closeConnection(con);
            return dskh;
        } catch (SQLException ex) {
        }
        return null;
    }

    public KhachHang getKhachHang(int maKH) {
        KhachHang kh = null;
        try {
            Connection con = MyConnect.MyConnection();
            String sql = "SELECT * FROM khachhang WHERE MaKH=? AND TinhTrang=1";
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setInt(1, maKH);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                kh = new KhachHang();
                kh.setMaKH(rs.getInt(1));
                kh.setHo(rs.getString(2));
                kh.setTen(rs.getString(3));
                kh.setGioiTinh(rs.getString(4));
                kh.setTongChiTieu(rs.getInt(5));
            }
            MyConnect.closeConnection(con);
        } catch (SQLException ex) {
            return null;
        }
        return kh;
    }

    public boolean addKhachHang(KhachHang kh) {
        boolean result = false;
        try {
            Connection con = MyConnect.MyConnection();
            String sql = "INSERT INTO khachhang VALUES(?,?,?,?,?,1)";
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setInt(1, kh.getMaKH());
            prep.setString(2, kh.getHo());
            prep.setString(3, kh.getTen());
            prep.setString(4, kh.getGioiTinh());
            prep.setInt(5, kh.getTongChiTieu());
            result = prep.executeUpdate() > 0;
            MyConnect.closeConnection(con);
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean deleteKhachHang(int maKH) {
        boolean result = false;
        try {
            Connection con = MyConnect.MyConnection();
            String sql = "UPDATE khachhang SET TinhTrang=0 WHERE MaKH=?";
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setInt(1, maKH);
            result = prep.executeUpdate() > 0;
            MyConnect.closeConnection(con);
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean updateKhachHang(int maKH, KhachHang kh) {
        boolean result = false;
        try {
            Connection con = MyConnect.MyConnection();
            String sql = "UPDATE khachhang SET Ho=?, Ten=?, GioiTinh=? WHERE MaKH=?";
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setString(1, kh.getHo());
            prep.setString(2, kh.getTen());
            prep.setString(3, kh.getGioiTinh());
            prep.setInt(4, maKH);
            result = prep.executeUpdate() > 0;
            MyConnect.closeConnection(con);
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean updateTongChiTieu(int maKH, int tongChiTieu) {
        boolean result = false;
        try {
            Connection con = MyConnect.MyConnection();
            String sql = "UPDATE khachhang SET TongChiTieu=" + tongChiTieu + " WHERE MaKH=" + maKH;
            Statement stmt = con.createStatement();
            result = stmt.executeUpdate(sql) > 0;
            MyConnect.closeConnection(con);
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }
}
