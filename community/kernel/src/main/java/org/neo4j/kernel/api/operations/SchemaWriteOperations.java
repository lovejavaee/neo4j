/**
 * Copyright (c) 2002-2013 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.kernel.api.operations;

import org.neo4j.kernel.api.constraints.UniquenessConstraint;
import org.neo4j.kernel.api.exceptions.schema.DropConstraintFailureException;
import org.neo4j.kernel.api.exceptions.schema.DropIndexFailureException;
import org.neo4j.kernel.api.exceptions.schema.SchemaKernelException;
import org.neo4j.kernel.impl.api.index.IndexDescriptor;

public interface SchemaWriteOperations
{
    /**
     * Creates an index, indexing properties with the given {@code propertyKeyId} for nodes with the given
     * {@code labelId}.
     */
    IndexDescriptor indexCreate( StatementState state, long labelId, long propertyKeyId ) throws SchemaKernelException;

    /** Drops a {@link IndexDescriptor} from the database */
    void indexDrop( StatementState state, IndexDescriptor descriptor ) throws DropIndexFailureException;

    /**
     * This should not be used, it is exposed to allow an external job to clean up constraint indexes.
     * That external job should become an internal job, at which point this operation should go away.
     */
    void uniqueIndexDrop( StatementState state, IndexDescriptor descriptor ) throws DropIndexFailureException;

    UniquenessConstraint uniquenessConstraintCreate( StatementState state, long labelId, long propertyKeyId )
            throws SchemaKernelException;

    void constraintDrop( StatementState state, UniquenessConstraint constraint ) throws DropConstraintFailureException;
}