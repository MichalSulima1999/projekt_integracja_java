package com.example.projekt.soap.cpu;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pl.bykowski.springbootsoapexample.student
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetResponse }
     *
     */
    public GetResponse createGetResponse() {
        return new GetResponse();
    }

    /**
     * Create an instance of {@link Cpu }
     *
     */
    public Cpu createCpu() {
        return new Cpu();
    }

    /**
     * Create an instance of {@link GetCpu }
     *
     */
    public GetCpu createGetStudent() {
        return new GetCpu();
    }

}