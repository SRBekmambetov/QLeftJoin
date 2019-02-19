package ru.srb.tsc.leftjoin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreatingQueries {

    private List<Table> originalTablesFromFileList;

    public CreatingQueries() {
        this.originalTablesFromFileList = new ArrayList<>();
    }

    public void createTableFromFile(String fileTable) throws IOException {
        File file = new File(fileTable);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        int z = 1;
        String nameTable = "";
        Table tableFromFile = null;
        List<Row> rowsList = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            if (z == 1) {
                nameTable = line;
            } else {
                String[] arr = line.split("\t");
                if (z == 2) {
                    String nameId = arr[0];
                    List<String> namesValuesList = new ArrayList<>();
                    for (int i = 1; i < arr.length; i++) {
                        namesValuesList.add(arr[i]);
                    }
                    tableFromFile = new Table(nameTable, nameId, namesValuesList);
                } else {
                    int id = Integer.parseInt(arr[0]);
                    List<String> valuesList = new ArrayList<>();
                    for (int i = 1; i < arr.length; i++) {
                        valuesList.add(arr[i]);
                    }
                    Row row = new Row(id, valuesList);
                    rowsList.add(row);
                }
            }
            z++;
        }
        tableFromFile.setRowsList(rowsList);
        originalTablesFromFileList.add(tableFromFile);
    }

    public void createQueryList() {
        CreateQueryArrayList createQueryArrayList = new CreateQueryArrayList();
        createQueryArrayList.createAndPrintArrayList(originalTablesFromFileList);
        createQueryArrayList.createArrayRowsInnerJoinTable();
        createQueryArrayList.createAndPrintArrayRowsLeftJoinTable();
        CreateQuerySortedLinkedList createQuerySortedLinkedList = new CreateQuerySortedLinkedList();
        createQuerySortedLinkedList.createAndPrintLinkedList(originalTablesFromFileList);
        createQuerySortedLinkedList.createLinkedRowsInnerJoinTable();
        createQuerySortedLinkedList.createAndPrintLinkedRowsLeftJoinTable(originalTablesFromFileList);
        CreateQueryMap createQueryMap = new CreateQueryMap();
        createQueryMap.createMaps(originalTablesFromFileList);
        createQueryMap.createMapRowsInnerJoinTable();
        createQueryMap.createAndPrintMapRowsLeftJoinTable();
        createQueryMap.printMaps(originalTablesFromFileList);
        createQueryMap.createAndPrintMapRowsLeftJoinTable();
    }
}
