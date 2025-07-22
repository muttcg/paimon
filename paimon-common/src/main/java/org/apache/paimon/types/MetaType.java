/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.paimon.types;

/** Internal map type to carry extra information about IDs to generate Iceberg schema correctly. */
public class MetaType extends DataType {

    private final int id;

    private final DataType dataType;

    public MetaType(boolean isNullable, int id, DataType dataType) {
        super(isNullable, dataType.getTypeRoot());
        this.id = id;
        this.dataType = dataType;
    }

    public MetaType(int id, DataType dataType) {
        this(dataType.isNullable(), id, dataType);
    }

    public DataType getDataType() {
        return dataType;
    }

    public int getId() {
        return id;
    }

    @Override
    public int defaultSize() {
        return dataType.defaultSize();
    }

    @Override
    public DataType copy(boolean isNullable) {
        return new MetaType(isNullable, id, dataType.copy(isNullable));
    }

    @Override
    public String asSQLString() {
        return dataType.asSQLString();
    }

    @Override
    public <R> R accept(DataTypeVisitor<R> visitor) {
        return dataType.accept(visitor);
    }
}
