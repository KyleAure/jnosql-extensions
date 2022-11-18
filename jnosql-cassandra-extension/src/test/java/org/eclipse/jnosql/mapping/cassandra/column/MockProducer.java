/*
 *  Copyright (c) 2022 Contributors to the Eclipse Foundation
 *   All rights reserved. This program and the accompanying materials
 *   are made available under the terms of the Eclipse Public License v1.0
 *   and Apache License v2.0 which accompanies this distribution.
 *   The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *   and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 *   You may elect to redistribute this code under either of these licenses.
 *
 *   Contributors:
 *
 *   Otavio Santana
 */
package org.eclipse.jnosql.mapping.cassandra.column;


import jakarta.nosql.column.Column;
import jakarta.nosql.column.ColumnEntity;
import org.eclipse.jnosql.communication.cassandra.column.CassandraColumnManager;
import org.mockito.Mockito;

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.interceptor.Interceptor;

import java.util.function.Supplier;

import static org.mockito.Mockito.when;

@Alternative
@Priority(Interceptor.Priority.APPLICATION)
public class MockProducer implements Supplier<CassandraColumnManager> {


    @Produces
    @Override
    public CassandraColumnManager get() {
        CassandraColumnManager manager = Mockito.mock(CassandraColumnManager.class);
        ColumnEntity entity = ColumnEntity.of("Person");
        entity.add(Column.of("name", "Ada"));
        when(manager.insert(Mockito.any(ColumnEntity.class))).thenReturn(entity);
        return manager;
    }

}