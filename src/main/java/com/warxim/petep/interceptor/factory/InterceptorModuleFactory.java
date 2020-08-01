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
package com.warxim.petep.interceptor.factory;

import com.warxim.petep.extension.Extension;
import com.warxim.petep.extension.PetepAPI;
import com.warxim.petep.interceptor.module.InterceptorModule;
import com.warxim.petep.module.ModuleFactory;

/** Interceptor module interface. */
@PetepAPI
public abstract class InterceptorModuleFactory extends ModuleFactory<InterceptorModule> {
  public InterceptorModuleFactory(Extension extension) {
    super(extension);
  }
}
