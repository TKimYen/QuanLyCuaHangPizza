package Main;

import QuanLyPizza.DAO.MyConnect;
import QuanLyPizza.GUI.DangNhapGUI;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        new MyConnect();
        DangNhapGUI login = new DangNhapGUI();
        login.showWindow();
    }
}
