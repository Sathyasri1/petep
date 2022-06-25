/*
 * PEnetration TEsting Proxy (PETEP)
 *
 * Copyright (C) 2021 Michal Válka
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
package com.warxim.petep.extension.internal.scripter.rule;

import com.warxim.petep.extension.internal.scripter.helper.ScriptHelperFactory;
import com.warxim.petep.util.FileUtils;
import lombok.Getter;

/**
 * String based script. (Script is stored in string variable in configuration of scripter.)
 * <p>String script runs in context of the project directory.</p>
 */
@Getter
public class StringScript extends Script {
    private final String script;

    /**
     * Constructs script.
     * @param name Name of the script
     * @param description Description of the script
     * @param enabled {@code true} if the script should be used
     * @param language Language of the script
     * @param factory Factory for creation of script helpers
     * @param script String script content
     */
    public StringScript(String name, String description, boolean enabled, String language, ScriptHelperFactory factory, String script) {
        super(name, description, enabled, language);
        this.script = script;
        if (enabled) {
            initContext(factory);
        }
    }

    /**
     * Initializes context (creates and binds helper, ...).
     */
    private void initContext(ScriptHelperFactory factory) {
        var contextPath = FileUtils.getProjectDirectory();
        initContext(factory, script, contextPath);
    }

    @Override
    public ScriptType getType() {
        return ScriptType.STRING;
    }

    @Override
    public Script copy(ScriptHelperFactory factory) {
        return new StringScript(name, description, enabled, language, factory, script);
    }
}