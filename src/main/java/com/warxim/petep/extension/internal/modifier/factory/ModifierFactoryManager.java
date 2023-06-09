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
package com.warxim.petep.extension.internal.modifier.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Modifier factory manager.
 */
public final class ModifierFactoryManager {
    private final Map<String, ModifierFactory> factories;

    /**
     * Modifier factory manager constructor.
     */
    public ModifierFactoryManager() {
        factories = new ConcurrentHashMap<>();
    }

    /**
     * Registers modifier factory.
     * @param factory Modifier factory to be registered
     * @return {@code true} if the factory has been registered successfully
     */
    public boolean registerFactory(ModifierFactory factory) {
        return factories.putIfAbsent(factory.getCode(), factory) == null;
    }

    /**
     * Gets modifier factory by code.
     * @param code Code of modifier factory
     * @return Modifier factory with given code or empty optional if it does not exist
     */
    public Optional<ModifierFactory> getFactory(String code) {
        return Optional.ofNullable(factories.get(code));
    }

    /**
     * Gets all registered modifier factories.
     * @return List of registered modifier factories.
     */
    public List<ModifierFactory> getFactories() {
        return new ArrayList<>(factories.values());
    }
}
