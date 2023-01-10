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
 *   Alessandro Moscatelli
 */
package org.eclipse.jnosql.mapping.criteria;

import org.eclipse.jnosql.mapping.criteria.api.Expression;
import org.eclipse.jnosql.mapping.criteria.api.Path;
import org.eclipse.jnosql.mapping.criteria.api.StringExpression;
import org.eclipse.jnosql.mapping.metamodel.api.ComparableAttribute;
import org.eclipse.jnosql.mapping.metamodel.api.EntityAttribute;
import org.eclipse.jnosql.mapping.metamodel.api.StringAttribute;
import org.eclipse.jnosql.mapping.metamodel.api.ValueAttribute;
import org.eclipse.jnosql.mapping.AbstractGenericType;
import org.eclipse.jnosql.mapping.criteria.api.ComparableExpression;
import org.eclipse.jnosql.mapping.criteria.api.NumberExpression;
import org.eclipse.jnosql.mapping.metamodel.api.Attribute;
import org.eclipse.jnosql.mapping.metamodel.api.NumberAttribute;

/**
 * Default implementation for for {@link Path}
 *
 * @param <X> the entity type referenced by the root
 * @param <Y> the destination type
 */
public class DefaultPath<X, Y> extends AbstractGenericType<X> implements Path<X, Y> {

    private Path<X, ?> parent;

    private Attribute<?, Y> attribute;
    
    public DefaultPath(Class<X> type) {
        super(type);
    }

    public DefaultPath(Class<X> type, Path<X, ?> parent, Attribute<?, Y> attribute) {
        super(type);
        this.parent = parent;
        this.attribute = attribute;
    }

    @Override
    public Path<X, ?> getParent() {
        return this.parent;
    }

    @Override
    public Attribute<?, Y> getAttribute() {
        return this.attribute;
    }

    @Override
    public <Z> Path<X, Z> get(EntityAttribute<Y, Z> attribute) {
        return new DefaultPath<>(this.getType(), this, attribute);
    }

    @Override
    public <Z> Expression<X, Y, Z> get(ValueAttribute<Y, Z> attribute) {
        return new DefaultExpression<>(this, attribute);
    }

    @Override
    public StringExpression<X, Y> get(StringAttribute<Y> attribute) {
        return new DefaultStringExpression<>(this, attribute);
    }

    @Override
    public <Z extends Comparable> ComparableExpression<X, Y, Z> get(ComparableAttribute<Y, Z> attribute) {
        return new DefaultComparableExpression<>(this, attribute);
    }

    @Override
    public <Z extends java.lang.Number & java.lang.Comparable> NumberExpression<X, Y, Z> get(NumberAttribute<Y, Z> attribute) {
        return new DefaultNumberExpression<>(this, attribute);
    }
    
}
