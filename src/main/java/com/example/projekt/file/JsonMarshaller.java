package com.example.projekt.file;

import com.example.projekt.domain.CpuEntity;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class JsonMarshaller  {

    private final String url = "jdbc:postgresql://localhost/dvdrental";
    private final String user = "root";
    private final String password = "";

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
    /*
    public void export(String query) throws SQLException {
        try(Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(query))
        {
            ResultSet res = pstmt.executeQuery(query);
            JsonArray jsonArray = convertToJSON(res);
            FileWriter fileWriter = new FileWriter("json_file.json");
            JsonObject obj = null;
            fileWriter.write("[");
            for(int i=0; i < jsonArray.length(); i++){
                obj = (JsonObject) jsonArray.get(i);
                if (i != jsonArray.length() - 1) {
                    fileWriter.write(obj.toString() + ",\n");
                } else {
                    fileWriter.write(obj.toString());
                }
            }
            fileWriter.write("]");
            fileWriter.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public JsonArray convertToJSON(ResultSet resultSet) throws SQLException {
        JsonArray jsonArray = new JsonArray();
        while(resultSet.next()){
            JsonObject obj = null;
            obj = new JsonObject();
            int total_rows = resultSet.getMetaData().getColumnCount();
            for(int i=0; i< total_rows; i++){
                obj.put(resultSet.getMetaData().getColumnLabel(i+1));
            }
            jsonArray.put(obj);
        }
        return jsonArray;
    }

     */

}

