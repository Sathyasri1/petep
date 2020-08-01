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
package com.warxim.petep.wizard.project;

import com.google.gson.JsonElement;

/** Project extension. */
public final class WizardProjectExtension {
  private final String path;
  private final JsonElement store;

  /** Project extension constructor. */
  public WizardProjectExtension(String path, JsonElement store) {
    this.path = path;
    this.store = store;
  }

  public String getPath() {
    return path;
  }

  /** Returns extension store. */
  public JsonElement getStore() {
    return store;
  }

  public String toString() {
    return path;
  }
}
