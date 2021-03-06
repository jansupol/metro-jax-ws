/*
 * Copyright (c) 2005, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package fromwsdl.header.rpclit.common;

import org.w3c.dom.Node;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Set;

/**
 * Used to test message context properties on the client side.
 */
public class ClientHandler implements SOAPHandler<SOAPMessageContext> {

    public boolean handleMessage(SOAPMessageContext context) {
        SOAPMessage sm = context.getMessage();
        try {
            SOAPBody sb = sm.getSOAPBody();

            System.out.println("Inside ClientHandler...");

            Node n = sb.getFirstChild();
            if (n != null) {
                if ((Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)) {
                    if (n.getLocalName().equals("echo3")) {
                        if (!n.getNamespaceURI().equals("http://tempuri.org/wsdl"))
                            throw new WebServiceException("Expected: \"http://tempuri.org/wsdl\", got: " + n.getNamespaceURI());
                        else
                            return true;
                    }
                    if (!n.getNamespaceURI().equals("http://tempuri.org/")) {
                        throw new WebServiceException("Expected: \"http://tempuri.org/\", got: " + n.getNamespaceURI());
                    }

                } else {
                    if (n.getLocalName().equals("echo3Response")) {
                        if (!n.getNamespaceURI().equals("http://example.com/echo3"))
                            throw new WebServiceException("Expected: \"http://example.com/echo3\", got: " + n.getNamespaceURI());
                        else
                            return true;
                    }
                    if (!n.getNamespaceURI().equals("http://example.com/")) {
                        throw new WebServiceException("Expected: \"http://example.com/\", got: " + n.getNamespaceURI());
                    }

                }
            } else {
                return true;
            }
        } catch (SOAPException e) {
            throw new WebServiceException(e);
        }
        return true;
    }


    // empty methods below here //
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    public void close(MessageContext context) {
    }

    public Set<QName> getHeaders() {
        return null;
    }
}
