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

package org.apache.paimon.iceberg.manifest;

import org.apache.paimon.types.DataField;
import org.apache.paimon.types.DataType;
import org.apache.paimon.types.DataTypes;
import org.apache.paimon.types.MetaType;

/** Creates wrapper for Iceberg schema filed with meta info. */
public class IcebergSchemaField {

    private static final String FIELD_ID = "field-id";
    private static final String ELEMENT_ID = "element-id";

    private IcebergSchemaField() {}

    public static DataField create(int id, String name, DataType dataType) {
        return new DataField(id, name, new MetaType(id, FIELD_ID, dataType));
    }

    public static DataField createArray(int id, String name, int arrayId, DataType dataType) {
        return new DataField(
                id,
                name,
                new MetaType(
                        id,
                        FIELD_ID,
                        DataTypes.ARRAY(new MetaType(arrayId, ELEMENT_ID, dataType))));
    }

    public static DataField createMap(
            int id,
            String name,
            int keyId,
            DataType keyDataType,
            int valueId,
            DataType valueDataType) {
        return new DataField(
                id,
                name,
                new MetaType(
                        id,
                        FIELD_ID,
                        DataTypes.MAP(
                                new MetaType(keyId, FIELD_ID, keyDataType),
                                new MetaType(valueId, FIELD_ID, valueDataType))));
    }
}
