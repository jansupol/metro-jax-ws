/*
 * Copyright (c) 2004, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package client.dispatch.wsdl_hello_lit.server;

import java.util.List;


/**
 * Impl class for interface generated by wscompile -import.
 * This class will overwrite the impl class generated by wscompile.
 */

@javax.jws.WebService(endpointInterface = "client.dispatch.wsdl_hello_lit.server.Hello")

public class Hello_PortType_Impl implements Hello {
    public HelloResponse hello(Hello_Type req) {
        System.out.println("Hello_PortType_Impl received: " + req.getArgument() +
            ", " + req.getExtra());
        if (req.getArgument().equals("foo")) {
            HelloResponse resp = new HelloResponse();
            resp.setName("vivek");
            resp.setArgument(req.getArgument());
            resp.setExtra(req.getExtra());
            return resp;
        } else
            throw new java.lang.IllegalArgumentException("Bad argument value");


    }

    public VoidType voidTest(VoidType req) {
        if (req == null)
            return null;
        return new VoidType();
    }

    public void echoArray(javax.xml.ws.Holder<NameType> name) {
    }

    public void echoArray1(javax.xml.ws.Holder<NameType> name) {
        NameType resp = name.value;
        resp.getName().add("EA");
    }

    public void echoArray2(javax.xml.ws.Holder<NameType> name) {
    }

    public void echoArray3(javax.xml.ws.Holder<List<String>> name) {

    }

    public NameType1 echoArray4(NameType1 request) {
        NameType1 resp = new NameType1();
        HelloType ht = new HelloType();
        ht.setArgument("arg1");
        ht.setExtra("extra1");


        HelloType ht1 = new HelloType();
        ht1.setArgument("arg2");
        ht1.setExtra("extra2");
        resp.getName().add(ht);
        resp.getName().add(ht1);
        return resp;
    }
}
