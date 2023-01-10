/*
 *  Copyright (c) 2022 Otávio Santana and others
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
 *   Alessandro Moscatelli
 */
package org.eclipse.jnosql.mapping.criteria;

import org.eclipse.jnosql.mapping.criteria.api.CriteriaFunction;
import org.eclipse.jnosql.mapping.criteria.api.CriteriaQuery;
import org.eclipse.jnosql.mapping.criteria.api.EntityQuery;
import org.eclipse.jnosql.mapping.criteria.api.Expression;
import org.eclipse.jnosql.mapping.criteria.api.ExpressionQuery;
import org.eclipse.jnosql.mapping.criteria.api.FunctionQuery;
import org.eclipse.jnosql.mapping.criteria.api.Root;
import org.eclipse.jnosql.mapping.AbstractGenericType;

/**
 * Default implementation for {@link CriteriaQuery}
 *
 * @param <T> the type of the root entity
 */
public class DefaultCriteriaQuery<T> extends AbstractGenericType<T> implements CriteriaQuery<T> {
    
    private final Root<T> from;
    
    public DefaultCriteriaQuery(Class<T> type) {
        super(type);
        this.from = new DefaultRoot<>(type);
    }
    
    @Override
    public Root<T> from() {
        return this.from;
    }    

    @Override
    public EntityQuery<T> select() {
        return new DefaultEntityQuery<>(this.getType());
    }
    
    @Override
    public FunctionQuery<T> select(CriteriaFunction<T, ?, ?, ?>... functions) {
        return new DefaultFunctionQuery<>(this.getType(), functions);
    }

    @Override
    public ExpressionQuery<T> select(Expression<T, ?, ?>... expressions) {
        return new DefaultExpressionQuery<>(this.getType(), expressions);
    }
    
}
