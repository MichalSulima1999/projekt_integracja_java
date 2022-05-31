package com.example.projekt.file;

import com.example.projekt.domain.CpuEntity;

import java.sql.*;
import java.util.List;

public class JsonMarshaller  {

    private final String url = "jdbc:postgresql://localhost/dvdrental";
    private final String user = "postgres";
    private final String password = "postgres";

    public void insert(List<CpuEntity> list){
        String SQL = "INSERT INTO cpu(cores,threads,name,launch_date,lithography,base_frequency,turbo_frequency,tdp,socket,manufacturer)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        long id = 0;

        try(Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL))
        {
            int count = 0;
            for(CpuEntity cpu: list){
                pstmt.setInt(1,cpu.getCores());
                pstmt.setInt(2,cpu.getThreads());
                pstmt.setString(3,cpu.getName());
                pstmt.setDate(4, (Date) cpu.getLaunchDate());
                pstmt.setInt(5,cpu.getLithography());
                pstmt.setInt(6,cpu.getBase_frequency());
                pstmt.setString(7,cpu.getTurbo_frequency());
                pstmt.setInt(8,cpu.getTdp());
                pstmt.setString(9,cpu.getSocket());
                pstmt.setString(10,cpu.getManufacturer());

                pstmt.addBatch();
                count++;
                if (count % 100 == 0 || count == list.size()) {
                    pstmt.executeBatch();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}

