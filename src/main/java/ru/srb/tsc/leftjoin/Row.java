package ru.srb.tsc.leftjoin;

import java.util.List;
import java.util.Objects;

public class Row implements Comparable<Row> {

    private int id;
    private List<String> valueList;

    public Row(int id, List<String> valueList) {
        this.id = id;
        this.valueList = valueList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getValuesList() {
        return valueList;
    }

    public void setValueList(List<String> valueList) {
        this.valueList = valueList;
    }

    public int compareTo(Row o) {
        return this.id - o.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Row row = (Row) o;
        return id == row.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Row{" +
                "id=" + id +
                ", valueList=" + valueList +
                '}';
    }
}
