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
package com.warxim.petep.extension.internal.tcp.proxy;

import com.warxim.petep.core.connection.Connection;
import com.warxim.petep.core.pdu.PDU;
import com.warxim.petep.core.pdu.PduDestination;
import com.warxim.petep.extension.Extension;
import com.warxim.petep.extension.internal.tcp.TcpConfig;
import com.warxim.petep.extension.internal.tcp.TcpConfigurator;
import com.warxim.petep.extension.internal.tcp.proxy.base.TcpPdu;
import com.warxim.petep.gui.component.ConfigPane;
import com.warxim.petep.gui.component.PduMetadataPane;
import com.warxim.petep.persistence.Configurator;
import com.warxim.petep.proxy.factory.ProxyModuleFactory;
import com.warxim.petep.proxy.module.ProxyModule;
import com.warxim.petep.proxy.serizalization.ProxyDeserializer;
import com.warxim.petep.proxy.serizalization.ProxySerializer;
import com.warxim.petep.proxy.worker.Proxy;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * TCP proxy module.
 */
public final class TcpProxyModuleFactory extends ProxyModuleFactory implements Configurator<TcpConfig>, ProxySerializer, ProxyDeserializer {
    /**
     * Constructs TCP proxy module factory.
     * @param extension Extension that owns this factory
     */
    public TcpProxyModuleFactory(Extension extension) {
        super(extension);
    }

    @Override
    public String getName() {
        return "TCP";
    }

    @Override
    public String getCode() {
        return "tcp";
    }

    @Override
    public ProxyModule createModule(String code, String name, String description, boolean enabled) {
        return new TcpProxyModule(this, code, name, description, enabled);
    }

    @Override
    public ConfigPane<TcpConfig> createConfigPane() throws IOException {
        return new TcpConfigurator();
    }

    @Override
    public Optional<PduMetadataPane> createPduMetadataPane() {
        return Optional.empty();
    }

    @Override
    public ProxySerializer getSerializer() {
        return this;
    }

    @Override
    public ProxyDeserializer getDeserializer() {
        return this;
    }

    @Override
    public Map<String, String> serializePduMetadata(PDU pdu) {
        return Map.of();
    }

    @Override
    public Optional<PDU> deserializePdu(
            Proxy proxy,
            Connection connection,
            PduDestination destination,
            byte[] buffer,
            int size,
            Charset charset,
            Set<String> tags,
            Map<String, String> serializedMetadata) {
        return Optional.of(new TcpPdu(proxy, connection, destination, buffer, size, charset, tags));
    }
}
