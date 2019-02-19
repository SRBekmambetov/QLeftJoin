package ru.tsc.srb.leftjoin;

import java.util.ArrayList;
import java.util.List;

public class CreateQueryArrayList {

    private List<Table> arrayList;
    private List<Row> resultRowsArrayList;

    public CreateQueryArrayList() {
        this.arrayList = new ArrayList<>();
        this.resultRowsArrayList = new ArrayList<>();
    }

    public void createAndPrintArrayList(List<Table> originalTablesFromFileList) {
        for (Table originalTable: originalTablesFromFileList) {
            String originalNameTable = originalTable.getNameTable();
            String originalNameId = originalTable.getNameId();
            List<String> originalNamesValuesList = originalTable.getNamesValuesList();
            List<Row> originalRowsList = originalTable.getRowsList();
            List<Row> rowsList = new ArrayList<>();
            for (Row originalRow: originalRowsList) {
                int originalId = originalRow.getId();
                List<String> valuesList = new ArrayList<>(originalRow.getValuesList());
                Row row = new Row(originalId, valuesList);
                rowsList.add(row);
            }
            Table table = new Table(originalNameTable, originalNameId, originalNamesValuesList);
            table.setRowsList(rowsList);
            arrayList.add(table);
        }
        System.out.println("Пример ArrayList:");
        PrintList.printList(arrayList);
    }

    public void createArrayRowsInnerJoinTable() {
        List<Row> rowsList1 = arrayList.get(0).getRowsList();
        List<Row> rowsList2 = arrayList.get(1).getRowsList();
        for (int i = 0; i < rowsList1.size(); i++) {
            for (int j = 0; j < rowsList2.size(); j++) {
                Row row1 = rowsList1.get(i);
                Row row2 = rowsList2.get(j);
                if (row1.getId() == row2.getId()) {
                    Row row = row1.createRowFromTwoTables(row1, row2);
                    resultRowsArrayList.add(row);
                }
            }
        }
    }

    public void createAndPrintArrayRowsLeftJoinTable() {
        List<Row> rowsList1 = arrayList.get(0).getRowsList();
        List<Row> rowsList2 = arrayList.get(1).getRowsList();
        for (int i = 0; i < rowsList1.size(); i++) {
            Row row1 = rowsList1.get(i);
            Row row = new Row(row1.getId(), row1.getValuesList());
            if (!rowsList2.contains(row)) {
                resultRowsArrayList.add(row);
            }
        }
        System.out.println("Результат ArrayList:");
        PrintList.printListResult(arrayList, resultRowsArrayList);
    }
}
