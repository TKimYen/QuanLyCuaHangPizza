package QuanLyPizza.DAO;

import QuanLyPizza.DTO.TaiKhoan;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DangNhapDAO {

    public TaiKhoan dangNhap(TaiKhoan tk) {
        try {
            Connection con = MyConnect.MyConnection();
            String sql = "SELECT * FROM taikhoan WHERE TenDangNhap=? AND MatKhau=? AND TrangThai=1";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, tk.getTenDangNhap());
            pre.setString(2, tk.getMatKhau());
            ResultSet rs = pre.executeQuery();
            TaiKhoan tkLogin = null;
            if (rs.next()) {
                tkLogin = tk;
                tkLogin.setMaNhanVien(rs.getInt("MaNV"));
                tkLogin.setQuyen(rs.getString("Quyen"));
            }
            MyConnect.closeConnection(con);
            return tkLogin;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tk;
    }
}