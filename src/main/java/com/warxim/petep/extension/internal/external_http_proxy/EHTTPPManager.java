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
package com.warxim.petep.extension.internal.external_http_proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import com.warxim.petep.core.pdu.PduQueue;
import com.warxim.petep.extension.internal.external_http_proxy.lighthttp.LightHttpClient;
import com.warxim.petep.extension.internal.external_http_proxy.lighthttp.LightHttpServer;
import com.warxim.petep.helper.PetepHelper;

/** External HTTP Proxy manager. */
public final class EHTTPPManager {
  private final EHTTPPConfig config;
  private final List<LightHttpClient> clients;
  private final ExecutorService executor;

  private LightHttpServer server;

  /** External HTTP Proxy manager constructor. */
  public EHTTPPManager(EHTTPPConfig config) {
    this.config = config;
    clients = new ArrayList<>();
    executor = Executors.newCachedThreadPool();
  }

  /** Starts HTTP server and clients. */
  public void start(PetepHelper helper) {
    // Create and start server.
    server = new LightHttpServer(helper, config);
    executor.submit(server::run);

    // Start clients.
    for (LightHttpClient client : clients) {
      executor.submit(client::start);
    }

    Logger.getGlobal().info("HTTPP client-server started!");
  }

  /** Creates a client for specified queue. */
  public void createClient(PduQueue queue, int targetId) {
    clients.add(new LightHttpClient(config, queue, targetId));
  }

  /** Stops HTTP server and clients. */
  public void stop() {
    Logger.getGlobal().info("HTTPP client-server stopping...");

    server.stop();

    clients.parallelStream().forEach(LightHttpClient::stop);

    executor.shutdownNow();

    Logger.getGlobal().info("HTTPP client-server stopped!");
  }
}
