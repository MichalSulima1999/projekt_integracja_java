package com.example.projekt.file;

import com.example.projekt.api.CpuResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class XmlMarshaller {
    static Procesory procesory = new Procesory();
    static{
        procesory.setProcesory(new ArrayList<Procesor>());
        Procesor p1 = new Procesor();
        Procesor p2 = new Procesor();

        procesory.getProcesory().add(p1);
        procesory.getProcesory().add(p2);
    }

    // Zapis do XML
    public static void marshall(String fileName) throws JAXBException, SQLException {
        JAXBContext ctx = JAXBContext.newInstance(Procesory.class);
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Statement stat = null;
        ResultSet rs = stat.executeQuery("select * from schedule");
        while(rs.next()){
            Procesor p1 = new Procesor(rs.getInt(0),rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDate(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getString(9),rs.getString(10));
            procesory.getProcesory().add(p1);
        }

        marshaller.marshal(procesory, new File(fileName));
    }

    // Odczyt z XML
    public static void unmarshall(String fileName) throws JAXBException {
        JAXBContext ctx = JAXBContext.newInstance(Procesory.class);
        Unmarshaller umarshaller = ctx.createUnmarshaller();

        Procesory procesory = (Procesory) umarshaller.unmarshal(new File(fileName));

        for(Procesor proc: procesory.getProcesory()){
            System.out.println(proc.getName());
        }

    }
}
