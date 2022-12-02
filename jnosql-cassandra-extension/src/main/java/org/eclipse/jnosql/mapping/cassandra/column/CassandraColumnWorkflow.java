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

import jakarta.nosql.mapping.column.ColumnEntityConverter;
import jakarta.nosql.mapping.column.ColumnEventPersistManager;
import org.eclipse.jnosql.mapping.column.AbstractColumnWorkflow;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Typed;
import javax.inject.Inject;

@Typed(CassandraColumnWorkflow.class)
@ApplicationScoped
class CassandraColumnWorkflow extends AbstractColumnWorkflow {

    @Inject
    private ColumnEventPersistManager columnEventPersistManager;

    @Inject
    private CassandraColumnEntityConverter converter;


    @Override
    protected ColumnEventPersistManager getEventManager() {
        return columnEventPersistManager;
    }

    @Override
    protected ColumnEntityConverter getConverter() {
        return converter;
    }
}
