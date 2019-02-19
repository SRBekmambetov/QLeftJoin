package ru.srb.tsc.leftjoin;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CreateQuerySortedLinkedList {

    private List<Table> linkedList;
    private List<Row> resultRowsLinkedList;

    public CreateQuerySortedLinkedList() {
        this.linkedList = new LinkedList<>();
        this.resultRowsLinkedList = new LinkedList<>();
    }

    public void createAndPrintLinkedList(List<Table> originalTablesFromFileList) {
        for (Table originalTable: originalTablesFromFileList) {
            String originalNameTable = originalTable.getNameTable();
            String originalNameId = originalTable.getNameId();
            List<String> originalNamesValuesList = originalTable.getNamesValuesList();
            List<Row> originalRowsList = originalTable.getRowsList();
            List<Row> rowsList = new LinkedList<>();
            for (Row originalRow: originalRowsList) {
                int originalId = originalRow.getId();
                List<String> valuesList = new LinkedList<>(originalRow.getValuesList());
                Row row = new Row(originalId, valuesList);
                rowsList.add(row);
            }
            Table table = new Table(originalNameTable, originalNameId, originalNamesValuesList);
            Collections.sort(rowsList);
            table.setRowsList(rowsList);
            linkedList.add(table);
        }
        System.out.println();
        System.out.println("Пример SortedLinkedList:");
        PrintList.printList(linkedList);
    }

    public void createLinkedRowsInnerJoinTable() {
        List<Row> rowsList1 = linkedList.get(0).getRowsList();
        List<Row> rowsList2 = linkedList.get(1).getRowsList();
        resultRowsLinkedList = new LinkedList<>();
        for (int i = 0; i < rowsList1.size(); i++) {
            for (int j = 0; j < rowsList2.size(); j++) {
                Row row1 = rowsList1.get(i);
                Row row2 = rowsList2.get(j);
                if (row1.getId() < row2.getId()) {
                    rowsList1.remove(row1);
                    i--;
                    break;
                } else if (row1.getId() > row2.getId()) {
                    rowsList2.remove(row2);
                } else {
                    List<String> valueList = new LinkedList<>();
                    valueList.addAll(row1.getValuesList());
                    valueList.addAll(row2.getValuesList());
                    Row row = new Row(row1.getId(), valueList);
                    resultRowsLinkedList.add(row);
                }
            }
        }
    }

    public void createAndPrintLinkedRowsLeftJoinTable(List<Table> originalTablesFromFileList) {
        List<Row> rowsList1 = originalTablesFromFileList.get(0).getRowsList();
        List<Row> rowsList2 = originalTablesFromFileList.get(1).getRowsList();
        for (int i = 0; i < rowsList1.size(); i++) {
            Row row1 = rowsList1.get(i);
            Row row = new Row(row1.getId(), row1.getValuesList());
            if (!rowsList2.contains(row)) {
                resultRowsLinkedList.add(row);
            }
        }
        System.out.println("Результат SortedLinkedList:");
        PrintList.printListResult(linkedList, resultRowsLinkedList);
    }
}
