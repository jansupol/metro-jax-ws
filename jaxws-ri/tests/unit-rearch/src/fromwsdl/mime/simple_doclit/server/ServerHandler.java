/*
 * Copyright (c) 2005, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package fromwsdl.mime.simple_doclit.server;

import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import java.util.Set;
import java.util.Map;

import javax.xml.namespace.QName;

import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.WebServiceException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.activation.DataHandler;

/**
 * Used to test message context properties on the server side.
 */
public class ServerHandler implements SOAPHandler<SOAPMessageContext> {

    /*
     * The method that does the testing. If a context property is
     * missing or invalid, throw a RuntimeException and it will
     * cause the test to fail.
     */
    public boolean handleMessage(SOAPMessageContext context) {
        SOAPMessage sm = context.getMessage();
        try {
            SOAPBody sb = sm.getSOAPBody();
            Node n = sb.getFirstChild();
            if(n != null){
                if(!n.getLocalName().equals("picType") ||
                        !n.getNamespaceURI().equals("http://www.ws-i.org/SampleApplications/SupplyChainManagement/2003-07/Catalog.xsd")){
                    return true;
                }
            }else{
                return true;
            }
        } catch (SOAPException e) {
            throw new WebServiceException(e);
        }
        if ((Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)) {
            System.out.println("Server handler processing echoImageWithInfo() request!");
            Map<String, DataHandler> attachs = (Map<String, DataHandler>) context.get(MessageContext.OUTBOUND_MESSAGE_ATTACHMENTS);
            if(attachs == null)
                throw new WebServiceException("Expected 3 attacment, received null!");
            if(attachs.size() != 3)
                throw new WebServiceException("Expected 3 attacment, received :"+attachs.size());
        } else {
            System.out.println("Server handler processing echoImageWithInfo() response!");
            Map<String, DataHandler> attachs = (Map<String, DataHandler>) context.get(MessageContext.INBOUND_MESSAGE_ATTACHMENTS);
            if(attachs == null)
                throw new WebServiceException("Expected 2 attacment, received null!");
            if(attachs.size() != 2)
                throw new WebServiceException("Expected 2 attacment, received :"+attachs.size());
        }
        return true;
    }
    
    
    // empty methods below here //
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }
    
    public void close(MessageContext context) {}
    
    public Set<QName> getHeaders() {
        return null;
    }
    
    
}
