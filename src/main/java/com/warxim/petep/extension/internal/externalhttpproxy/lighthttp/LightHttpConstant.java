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
package com.warxim.petep.extension.internal.externalhttpproxy.lighthttp;

import java.nio.charset.StandardCharsets;

/**
 * Static class with constants for Light HTTP client and server.
 */
public final class LightHttpConstant {
    /*
     * BYTES
     */
    public static final byte COLON = (byte) 0x3A;
    public static final byte SLASH = (byte) 0x2F;
    public static final byte C2S = (byte) 0x73;
    public static final byte S2C = (byte) 0x63;
    public static final byte CR = (byte) 0x0D;
    public static final byte LF = (byte) 0x0A;
    public static final byte SPACE = (byte) 0x20;
    public static final byte TAGS_SEPARATOR = ',';

    /*
     * STRINGS
     */
    public static final String TAGS_HEADER = "T";
    public static final String CONTENT_TYPE_HEADER = "Content-Type";
    public static final String CONTENT_LENGTH_HEADER = "Content-Length";
    public static final String METADATA_HEADER_START = "M-";

    /*
     * BYTE ARRAYS
     */
    public static final byte[] FIRST_LINE_START = "POST http://".getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] FIRST_LINE_END = " HTTP/1.0\r\n".getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] CONTENT_LENGTH = (CONTENT_LENGTH_HEADER + ": ").getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] CONTENT_TYPE_CHARSET =
            (CONTENT_TYPE_HEADER + ": text/plain; charset=").getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] TAGS_HEADER_BYTES = (TAGS_HEADER + ": ").getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] METADATA_HEADER_START_BYTES = METADATA_HEADER_START.getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] HEADER_COLON = ": ".getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] HEADER_END = "\r\n".getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] HEADERS_END = "\r\n\r\n".getBytes(StandardCharsets.ISO_8859_1);

    public static final byte[] DESTINATION = "destination".getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] SERVER = "server".getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] CLIENT = "client".getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] PROXY = "proxy".getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] CONNECTION = "connection".getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] INTERCEPTOR = "interceptor".getBytes(StandardCharsets.ISO_8859_1);

    /*
     * RESPONSES
     */
    public static final byte[] RESPONSE_OK = "HTTP/1.0 200 OK\r\n\r\n".getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] RESPONSE_INVALID_PDU =
            "HTTP/1.0 400 PDU not valid\r\n\r\n".getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] RESPONSE_WRONG_PROXY =
            "HTTP/1.0 404 Proxy not found\r\n\r\n".getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] RESPONSE_WRONG_CONNECTION =
            "HTTP/1.0 404 Connection not found\r\n\r\n".getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] RESPONSE_WRONG_INTERCEPTOR =
            "HTTP/1.0 404 Interceptor not found\r\n\r\n".getBytes(StandardCharsets.ISO_8859_1);
    public static final byte[] RESPONSE_DESERIALIZATION_ERROR =
            "HTTP/1.0 500 Deserialization Error\r\n\r\n".getBytes(StandardCharsets.ISO_8859_1);

    private LightHttpConstant() {
    }
}
