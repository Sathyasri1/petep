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
package com.warxim.petep.extension.internal.modifier.gui.rule;

import com.warxim.petep.extension.internal.modifier.factory.ModifierFactoryManager;
import com.warxim.petep.extension.internal.modifier.rule.ModifyRule;

import java.io.IOException;

/**
 * Edit modify rule dialog.
 */
public final class EditModifyRuleDialog extends ModifyRuleDialog {
    /**
     * Constructs modify rule dialog for editing.
     * @param factoryManager Manager of modifier factories for obtaining factories
     * @param rule Rule to edit
     * @throws IOException If the dialog template could not be loaded
     */
    public EditModifyRuleDialog(ModifierFactoryManager factoryManager, ModifyRule rule) throws IOException {
        super("Edit modify rule", "Save", factoryManager);

        nameInput.setText(rule.getName());
        descriptionInput.setText(rule.getDescription());
        tagInput.setText(rule.getTag());
        enabledInput.setSelected(rule.isEnabled());

        var processor = rule.getModifier();
        factoryInput.getSelectionModel().select(processor.getFactory());

        // Load config pane.
        var pane = createFactoryPane();
        if (pane == null) {
            return;
        }
        setFactoryPane(pane);

        // Get configuration from instance.
        var data = processor.getData();
        if (data == null) {
            return;
        }

        // Set config to pane.
        pane.setConfig(data);
    }
}
