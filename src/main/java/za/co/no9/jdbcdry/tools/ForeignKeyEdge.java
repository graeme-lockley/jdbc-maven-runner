package za.co.no9.jdbcdry.tools;

import za.co.no9.jdbcdry.util.ListUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ForeignKeyEdge {
    private final Optional<String> name;
    private final TableName tableName;
    private final Collection<FieldMetaData> columns;

    private ForeignKeyEdge(Optional<String> name, TableName tableName, Collection<FieldMetaData> columns) {
        this.name = name;
        this.tableName = tableName;
        this.columns = columns;
    }

    public static ForeignKeyEdge from(Optional<String> name, TableName tableName, Iterable<FieldMetaData> columnNames) {
        return new ForeignKeyEdge(name, tableName, ListUtils.fromIterable(columnNames));
    }

    public ForeignKeyEdge addColumn(FieldMetaData column) {
        List<FieldMetaData> newColumns = new ArrayList<>(columns);
        newColumns.add(column);

        return new ForeignKeyEdge(name, tableName, newColumns);
    }

    public Iterable<FieldMetaData> columns() {
        return columns;
    }

    public Optional<String> name() {
        return name;
    }

    public TableName tableName() {
        return tableName;
    }

    public String columnNames(String separator) {
        StringBuilder sb = new StringBuilder();
        for (FieldMetaData column : columns) {
            sb.append(separator).append(column.name());
        }
        return sb.substring(separator.length());
    }
}
