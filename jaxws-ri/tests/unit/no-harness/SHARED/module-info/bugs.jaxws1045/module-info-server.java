/*
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

module server {
    requires java.xml.ws;
     requires jdk.httpserver;
      requires java.logging; 

    // generated by WebServiceWrapperGenerator
    exports bugs.jaxws1045.server.jaxws;

}
