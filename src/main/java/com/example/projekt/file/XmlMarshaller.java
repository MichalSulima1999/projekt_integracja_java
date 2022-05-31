package com.example.projekt.file;

import com.example.projekt.domain.CpuEntity;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class XmlMarshaller {
    static Procesory procesory = new Procesory();

    // Zapis do XML
    public void marshall(String filePath) throws JAXBException, SQLException {
        JAXBContext ctx = JAXBContext.newInstance(Procesory.class);
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Statement stat = null;
        // Wyciągnięcie z bazy
        ResultSet rs = stat.executeQuery("select * from cpu");
        while(rs.next()){
            CpuEntity p1 = new CpuEntity(rs.getInt(0),rs.getInt(1),rs.getString(2),
                    rs.getDate(3),rs.getInt(4),rs.getInt(5),rs.getString(6),
                    rs.getInt(7),rs.getString(8),rs.getString(9));
            procesory.getProcesory().add(p1);
        }

        marshaller.marshal(procesory, new File(filePath));
    }

    // Odczyt z XML
    public void unmarshall(String filePath) throws JAXBException {
        JAXBContext ctx = JAXBContext.newInstance(Procesory.class);
        Unmarshaller umarshaller = ctx.createUnmarshaller();

        Procesory procesory = (Procesory) umarshaller.unmarshal(new File(filePath));
        List<CpuEntity> dane = null;
        for(CpuEntity proc: procesory.getProcesory()){
            System.out.println(proc.getName());
            dane.add(proc);
            // DODANIE DO BAZY
        }
        procesory.setProcesory(dane);

    }
}
