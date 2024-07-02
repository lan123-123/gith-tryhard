/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import java.sql.*;
import java.util.ArrayList;
import model.ChuyenDe;
import until.ConnectDB;
/**
 *
 * @author TONG THI NHUNG
 */
public class ChuyenDeRepository {
    private Connection conn;
    
    public ChuyenDeRepository(){
        conn = ConnectDB.getConnection();
        
    }
    
    public ArrayList<ChuyenDe> getDaTa() throws SQLException{
        ArrayList<ChuyenDe> list = new ArrayList<>();
        String query = "Select * from chuyenDe";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {            
            ChuyenDe cd = new ChuyenDe();
            cd.setMaCD(rs.getInt("maCD"));
            cd.setTenCD(rs.getString("tenCD"));
            cd.setHocPhi(rs.getFloat("hocPhi"));
            cd.setThoiLuong(rs.getInt("thoiLuong"));
            cd.setHinh(rs.getString("hinh"));
            cd.setMoTa(rs.getString("moTa"));
            list.add(cd);
        }
        return list;
    }
    public void remove(String maCD) throws SQLException{
        String query = "delete from chuyende where maCD=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, Integer.valueOf(maCD));
        ps.execute();
    }
    public void insert(ChuyenDe cd) throws SQLException{
        String query ="insert into chuyende values(?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, cd.getMaCD());
        ps.setString(2, cd.getTenCD());
        ps.setFloat(3, cd.getHocPhi());
        ps.setInt(4, cd.getThoiLuong());
        ps.setString(5, cd.getHinh());
        ps.setString(6, cd.getMoTa());
        ps.execute();
    }
    public void update(ChuyenDe cd) throws SQLException{
        String query ="Update chuyende set tencd=?, hocphi=?, thoiluong=?, hinh=?, mota=? where macd=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, cd.getTenCD());
        ps.setFloat(2, cd.getHocPhi());
        ps.setInt(3, cd.getThoiLuong());
        ps.setString(4, cd.getHinh());
        ps.setString(5, cd.getMoTa());
        ps.setInt(6, cd.getMaCD());
        ps.execute();
    }
}
