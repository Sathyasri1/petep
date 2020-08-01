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
package com.warxim.petep.configuration;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.warxim.petep.common.Constant;
import com.warxim.petep.exception.ConfigurationException;
import com.warxim.petep.module.Module;
import com.warxim.petep.module.ModuleFactory;
import com.warxim.petep.module.ModuleFactoryManager;
import com.warxim.petep.persistence.Configurable;
import com.warxim.petep.persistence.Storable;
import com.warxim.petep.util.ExtensionUtils;

/** Static class for loading modules. */
public final class ModulesLoader {
  private ModulesLoader() {}

  /** Loads modules from specified configuration. */
  public static <M extends Module<?>> List<M> load(
      String path,
      ModuleFactoryManager<? extends ModuleFactory<M>> moduleFactoryManager)
      throws ConfigurationException {
    try (JsonReader reader = new JsonReader(new FileReader(path))) {
      JsonArray list = JsonParser.parseReader(reader).getAsJsonArray();
      ArrayList<M> modules = new ArrayList<>(list.size());

      for (int i = 0; i < list.size(); ++i) {
        // Load module and add it to list.
        modules.add(loadModule(list.get(i).getAsJsonObject(), moduleFactoryManager));
      }

      return modules;
    } catch (JsonParseException e) {
      throw new ConfigurationException("Could not parse configuration! (" + path + ")", e);
    } catch (NoSuchFileException e) {
      throw new ConfigurationException("Could not find configuration! (" + path + ")", e);
    } catch (IOException e) {
      throw new ConfigurationException("Could not load configuration! (" + path + ")", e);
    }
  }

  /** Loads module from json object using module factory manager. */
  private static <M extends Module<?>> M loadModule(
      JsonObject json,
      ModuleFactoryManager<? extends ModuleFactory<M>> moduleFactoryManager)
      throws ConfigurationException {
    // Get module using factory code from json.
    ModuleFactory<M> factory =
        moduleFactoryManager.getModuleFactory(json.get(Constant.CONFIG_ITEM_FACTORY).getAsString());

    // If factory has not been found, we cannot load module.
    if (factory == null) {
      throw new ConfigurationException("Module factory '"
          + json.get(Constant.CONFIG_ITEM_FACTORY).getAsString() + "' does not exist!");
    }

    // Create module using factory.
    M module = factory.createModule(json.get(Constant.CONFIG_ITEM_CODE).getAsString(),
        json.get(Constant.CONFIG_ITEM_NAME).getAsString(),
        json.get(Constant.CONFIG_ITEM_DESCRIPTION).getAsString(),
        json.get(Constant.CONFIG_ITEM_ENABLED).getAsBoolean());

    processConfig(module, json.get(Constant.CONFIG_ITEM_CONFIG));
    processStore(module, json.get(Constant.CONFIG_ITEM_STORE));

    return module;
  }

  /** Processes json store for module. */
  private static <M extends Module<?>> void processStore(M module, JsonElement store) {
    // Store does not exist.
    if (store == null) {
      return;
    }

    // Get store type using reflections.
    Type storeType = ExtensionUtils.getStoreType(module);
    if (storeType == null) {
      return;
    }

    // Deserialize store and hand it over to module.
    ((Storable<?>) module).loadStore(new GsonBuilder().create().fromJson(store, storeType));
  }

  /** Processes json config for module. */
  private static <M extends Module<?>> void processConfig(M module, JsonElement config) {
    // Config does not exist.
    if (config == null) {
      return;
    }

    // Get config type using reflections.
    Type configType = ExtensionUtils.getConfigType(module);
    if (configType == null) {
      return;
    }

    // Deserialize config and hand it over to module.
    ((Configurable<?>) module).loadConfig(new GsonBuilder().create().fromJson(config, configType));
  }
}
