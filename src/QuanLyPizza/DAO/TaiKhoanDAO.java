package QuanLyPizza.DAO;

import QuanLyPizza.BUS.DangNhapBUS;
import QuanLyPizza.DTO.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TaiKhoanDAO {

    public boolean themTaiKhoan(int maNV, String tenDangNhap, String quyen) {
        try {
            Connection con = MyConnect.MyConnection();
            String sql = "INSERT INTO taikhoan(MaNV, TenDangNhap, MatKhau, Quyen) "
                    + "VALUES (?, ?, ?, ?)";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, maNV);
            pre.setString(2, tenDangNhap);
            pre.setString(3, tenDangNhap);
            pre.setString(4, quyen);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean kiemTraTrungTenDangNhap(String tenDangNhap) {
        try {
            Connection con = MyConnect.MyConnection();
            String sql = "SELECT * FROM TaiKhoan WHERE TenDangNhap = '" + tenDangNhap + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getTenDangNhapTheoMa(int maNV) {
        try {
            Connection con = MyConnect.MyConnection();
            String sql = "SELECT TenDangNhap FROM TaiKhoan WHERE MaNV=" + maNV;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
        return "";
    }

    public boolean datLaiMatKhau(int maNV, String tenDangNhap) {
        try {
            Connection con = MyConnect.MyConnection();
            String sql = "UPDATE TaiKhoan SET MatKhau=? WHERE MaNV=?";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, tenDangNhap);
            pre.setInt(2, maNV);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean datLaiQuyen(int maNV, String quyen) {
        try {
            Connection con = MyConnect.MyConnection();
            String sql = "UPDATE TaiKhoan SET Quyen=? WHERE MaNV=?";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, quyen);
            pre.setInt(2, maNV);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public String getQuyenTheoMa(int maNV) {
        try {
            Connection con = MyConnect.MyConnection();
            String sql = "SELECT Quyen FROM TaiKhoan WHERE MaNV=" + maNV;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
        return "";
    }

    public boolean khoaTaiKhoan(int maNV) {
        try {
            Connection con = MyConnect.MyConnection();
            String sql = "UPDATE TaiKhoan SET TrangThai=0 WHERE MaNV=?";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, maNV);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean moKhoaTaiKhoan(int maNV) {
        try {
            Connection con = MyConnect.MyConnection();
            String sql = "UPDATE TaiKhoan SET TrangThai=1 WHERE MaNV=?";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, maNV);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean doiMatKhau(String matKhauCu, String matKhauMoi) {
        try {
            Connection con = MyConnect.MyConnection();
            String sql = "UPDATE TaiKhoan SET MatKhau=? WHERE MaNV=? AND MatKhau=?";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, matKhauMoi);
            pre.setInt(2, DangNhapBUS.taiKhoanLogin.getMaNhanVien());
            pre.setString(3, matKhauCu);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public int getTrangThai(int ma) {
        try {
            Connection con = MyConnect.MyConnection();
            String sql = "SELECT TrangThai FROM TaiKhoan WHERE MaNV=" + ma;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return -1;
    }
}
