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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.HibernateException;

/**
 *
 * @author Sebastian Prehn <sebastian.prehn@planetswebdesign.de>
 */
public abstract class AbstractStringEnumUserType<E extends Enum> extends AbstractEnumUserType{


    public AbstractStringEnumUserType(final Class<E> clazz) {
       super(clazz);
    }

    public Object nullSafeGet(final ResultSet resultSet, final String[] names, final Object owner) throws HibernateException, SQLException {
        if (!resultSet.wasNull()) {
            return convert(resultSet.getString(names[0]));
        }
        return null;
    }

    public void nullSafeSet(final PreparedStatement preparedStatement, final Object value, final int index) throws HibernateException, SQLException {
        if (null == value) {
            preparedStatement.setNull(index, Types.VARCHAR);
        } else {
            preparedStatement.setString(index, convert((E) value));
        }
    }

    private String convert(final E value) {
        for (final Entry<String, E> stringToEnum : this.getConversionMap().entrySet()) {
            if (stringToEnum.getValue().equals(value)) {
                return stringToEnum.getKey();
            }
        }
        return null;
    }
    
    private E convert(final String value) {
        final E result = this.getConversionMap().get(value);
        if (result == null) {
            throw new IllegalArgumentException("unkown value: " + value);
        }
        return result;
    }

    /**
     * Maps the string representation in the database to a enum in java.
     *
     * @return the string to enum map
     */
    protected abstract Map<String, E> getConversionMap();
}