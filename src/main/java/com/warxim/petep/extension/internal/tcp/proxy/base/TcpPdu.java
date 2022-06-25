/*
 * PEnetration TEsting Proxy (PETEP)
 *
 * Copyright (C) 2020 Michal Válka
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <https://www.gnu.org/licenses/>.
 */
package com.warxim.petep.extension.internal.tcp.proxy.base;

import com.warxim.petep.core.connection.Connection;
import com.warxim.petep.core.pdu.DefaultPdu;
import com.warxim.petep.core.pdu.PduDestination;
import com.warxim.petep.proxy.worker.Proxy;

import java.nio.charset.Charset;
import java.util.Set;

/**
 * Simple TCP PDU based on DefaultPdu.
 */
public class TcpPdu extends DefaultPdu {
    /**
     * Constructs TCP PDU with empty tag set.
     * @param proxy Proxy
     * @param connection Connection
     * @param destination Destination of the PDU
     * @param buffer Data buffer
     * @param size Size of the data in the buffer
     * @param charset Charset of the data in the buffer
     */
    public TcpPdu(
            Proxy proxy,
            Connection connection,
            PduDestination destination,
            byte[] buffer,
            int size,
            Charset charset) {
        super(proxy, connection, destination, buffer, size, charset);
    }

    /**
     * Constructs TCP PDU.
     * @param proxy Proxy
     * @param connection Connection
     * @param destination Destination of the PDU
     * @param buffer Data buffer
     * @param size Size of the data in the buffer
     * @param charset Charset of the data in the buffer
     * @param tags Set of tags
     */
    public TcpPdu(
            Proxy proxy,
            Connection connection,
            PduDestination destination,
            byte[] buffer,
            int size,
            Charset charset,
            Set<String> tags) {
        super(proxy, connection, destination, buffer, size, charset, tags);
    }
}
