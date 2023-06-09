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
package com.warxim.booleanexpressioninterpreter;

/**
 * Variable expression.
 * <p>
 *     Represents constant value (true/false).
 * </p>
 */
public final class VariableExpression implements Expression {
    private boolean expression;

    /**
     * Variable expression.
     * @param expression constant value of the expression
     */
    public VariableExpression(boolean expression) {
        this.expression = expression;
    }

    /**
     * Sets value of expression.
     */
    public void setValue(boolean expression) {
        this.expression = expression;
    }

    /**
     * @return Saved constant value
     */
    @Override
    public boolean solve() {
        return expression;
    }
}
