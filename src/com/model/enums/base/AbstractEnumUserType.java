/*
 * Copyright 2012 Sebastian Prehn <sebastian.prehn@planetswebdesign.de>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.model.enums.base;

import java.io.Serializable;
import java.sql.Types;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

/**
 *
 * @author Sebastian Prehn <sebastian.prehn@planetswebdesign.de>
 */
public abstract class AbstractEnumUserType<E extends Enum> implements UserType {

    private final Class<E> clazz;
    private static final int[] SQL_TYPES = {Types.VARCHAR};

    public AbstractEnumUserType(final Class<E> clazz) {
        this.clazz = clazz;
    }

    public int[] sqlTypes() {
        return SQL_TYPES;
    }

    public Class<?> returnedClass() {
        return this.clazz;
    }

    public Object deepCopy(final Object value) throws HibernateException {
        return value;
    }

    public boolean isMutable() {
        return false;
    }

    public Object assemble(final Serializable cached, final Object owner) throws HibernateException {
        return cached;
    }

    public Serializable disassemble(final Object value) throws HibernateException {
        return (Serializable) value;
    }

    public Object replace(final Object original, final Object target, final Object owner) throws HibernateException {
        return original;
    }

    public int hashCode(final Object x) throws HibernateException {
        return x == null ? 0 : x.hashCode();
    }

    public boolean equals(final Object x, final Object y) throws HibernateException {
        if (x == y) {
            return true;
        }
        if (null == x || null == y) {
            return false;
        }
        return x.equals(y);
    }
}