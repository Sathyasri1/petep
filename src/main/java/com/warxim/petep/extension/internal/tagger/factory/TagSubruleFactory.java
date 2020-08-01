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
package com.warxim.petep.extension.internal.tagger.factory;

import java.io.IOException;
import java.lang.reflect.Type;
import com.warxim.petep.extension.PetepAPI;

/** Tag subrule factory. */
@PetepAPI
public abstract class TagSubruleFactory {
  /** Returns factory code (for configuration purposes). */
  public abstract String getCode();

  /** Returns factory name (visible for user). */
  public abstract String getName();

  /** Creates subrule using given data. */
  public abstract TagSubrule createSubrule(TagSubruleData data);

  /** Creates config pane for subrule data. */
  public abstract TagSubruleConfigurator createConfigPane() throws IOException;

  /** Returns type of configuration, so it can be deserialized from JSON configuration. */
  public abstract Type getConfigType();

  @Override
  public String toString() {
    return getName();
  }
}
