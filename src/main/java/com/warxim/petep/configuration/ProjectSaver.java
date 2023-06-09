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

import com.warxim.petep.common.Constant;
import com.warxim.petep.exception.ConfigurationException;
import com.warxim.petep.project.Project;
import com.warxim.petep.util.GsonUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

/**
 * Static class for project saving.
 */
public final class ProjectSaver {
    private ProjectSaver() {
    }

    /**
     * Saves project to specified path.
     * @param path Path to project configuration file
     * @param project Project to be stored
     * @throws ConfigurationException If the project could not be saved
     */
    public static void save(String path, Project project) throws ConfigurationException {
        var gson = GsonUtils.getGson();

        try (var writer = gson.newJsonWriter(new FileWriter(path, Constant.FILE_CHARSET))) {
            gson.toJson(gson.toJsonTree(project), writer);
        } catch (NoSuchFileException e) {
            throw new ConfigurationException("Could not found project configuration!", e);
        } catch (IOException e) {
            throw new ConfigurationException("Could not save project configuration!", e);
        }
    }
}
