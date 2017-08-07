/*
 *  Copyright (c) 2017 Otávio Santana and others
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
package org.jnosql.artemis.graph.util;

import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.jnosql.artemis.graph.ArtemisVertex;
import org.jnosql.artemis.graph.EdgeEntity;
import org.jnosql.artemis.graph.VertexConverter;
import org.jnosql.diana.api.Value;

import static java.util.Objects.requireNonNull;
import static org.apache.tinkerpop.gremlin.structure.T.id;
import static org.apache.tinkerpop.gremlin.structure.T.label;


/**
 * Utils class to integrate Artemis with TinkerPop
 */
public final class TinkerPopUtil {

    private TinkerPopUtil() {
    }

    /**
     * Converts Vertex to ArtemisVertex
     *
     * @param vertex the TinkerPop Vertex
     * @return the Artemis Vertex
     * @throws NullPointerException when vertex is null
     */
    public static ArtemisVertex toArtemisVertex(Vertex vertex) throws NullPointerException {
        requireNonNull(vertex, "vertex is required");
        ArtemisVertex artemisVertex = ArtemisVertex.of(vertex.label(), vertex.id());
        vertex.keys().stream().forEach(k -> artemisVertex.add(k, Value.of(vertex.value(k))));
        return artemisVertex;
    }

    public static Vertex toVertex(ArtemisVertex artemisVertex, Graph graph) throws NullPointerException {

        requireNonNull(artemisVertex, "artemisVertex is required");
        requireNonNull(graph, "graph is required");
        Vertex vertex = artemisVertex.getId().map(v -> graph.addVertex(label, artemisVertex.getLabel(), id, v.get()))
                .orElse(graph.addVertex(artemisVertex.getLabel()));

        artemisVertex.getProperties()
                .stream()
                .forEach(p -> vertex.property(p.getKey(), p.get()));

        return vertex;
    }


    /**
     * Converts Edge to EdgeEntity
     *
     * @param edge      the edge TinkerPop structure
     * @param converter the converts
     * @param <OUT>     the outgoing type
     * @param <IN>      the incoming type
     * @return the {@link EdgeEntity} instance
     */
    public static <OUT, IN> EdgeEntity<OUT, IN> toEdgeEntity(Edge edge, VertexConverter converter) {

        requireNonNull(edge, "edge is required");
        requireNonNull(converter, "converter is required");

        ArtemisVertex inVertex = toArtemisVertex(edge.inVertex());
        ArtemisVertex outVertex = toArtemisVertex(edge.outVertex());
        return EdgeEntity.of(converter.toEntity(outVertex), edge, converter.toEntity(inVertex));

    }
}
