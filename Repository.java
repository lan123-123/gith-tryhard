/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asm2_ph51821.Repository;

import asm2_ph51821.Model.SinhVien;
import asm2_ph51821.Model.SinhVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TONG THI NHUNG
 */
public class Repository {

    private Connection conn;

    public Repository() {
        conn = ConnectDB.getConnection();
    }

    public List<SinhVien> getListSinhVien() {

        ArrayList<SinhVien> list = new ArrayList<>();
        String query = "select MaSV, HoTen, Email, SoDT, GioiTinh, DiaChi  from SinhVien";
        try {

            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet resultSet = stm.executeQuery();

            while (resultSet.next()) {
                SinhVien sv = new  SinhVien();
                sv.setMaSV(resultSet.getString(1));
                sv.setHoTen(resultSet.getString(2));
                sv.setEmail(resultSet.getString(3));
                sv.setSoDT(resultSet.getString(4));
                sv.setGioiTinh(resultSet.getBoolean(5));
                sv.setDiaChi(resultSet.getString(6));
                

                list.add(sv);

            }

        } catch (Exception e) {
            System.out.println("Error" + e);

        }
        return list;
    }

        public int saveSinhVien(SinhVien m) {

        String query = "insert into SinhVien(masv,hoten,email,sodt,gioitinh,diachi) values(?, ?, ?, ?, ?,?)";
        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, m.getMaSV());
            st.setString(2, m.getHoTen());
            st.setString(3, m.getEmail());
            st.setString(4, m.getSoDT());
            st.setBoolean(5, m.isGioiTinh());
            st.setString(6, m.getDiaChi());

            st.executeUpdate(); //dùng cho insert, update, delete
        } catch (Exception e) {
            System.out.println("Lỗi" + e);

        }
        return 1;
    }

    public int deleteSinhVien(SinhVien m) {

        String query = "Delete from SinhVien where masv = ?";
        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, m.getMaSV());

            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("Lỗi" + e);

        }
        return 1;
    }

    public int updateSinhVien(SinhVien m) {
        //thêm đối tượng m vào databsae
        String query = "Update  SinhVien set  hoten =?, Email =?, SoDT=?, gioiTinh =?, diaChi=? where maSV=?";
        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, m.getHoTen());
            st.setString(2, m.getEmail());
            st.setString(3, m.getSoDT());
            st.setInt(4, m.isGioiTinh()?1:0);
            st.setString(5, m.getDiaChi());
            st.setString(6, m.getMaSV());

            st.executeUpdate(); //dùng cho insert, update, delete
        } catch (Exception e) {
            System.out.println("Lỗi" + e);

        }
        return 1;
    }
}
