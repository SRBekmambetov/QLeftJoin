package ru.tsc.srb.leftjoin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateQueryMap {

    private Map<Integer, List<Row>> map1;
    private Map<Integer, List<Row>> map2;
    private Map<Integer, List<Row>> resultMap;
    private List<Row> resultMapList;

    public CreateQueryMap() {
        this.map1 = new HashMap<>();
        this.map2 = new HashMap<>();
        this.resultMap = new HashMap<>();
        this.resultMapList = new ArrayList<>();
    }

    public void createMap(Map<Integer, List<Row>> map, Table table) {
        List<Row> originalRowsList = table.getRowsList();
        for (Row originalRow: originalRowsList) {
            int originalId = originalRow.getId();
            List<String> valuesList = new ArrayList<>(originalRow.getValuesList());
            Row row = new Row(originalId, valuesList);
            if (map.containsKey(originalId)) {
                List<Row> rowsList = map.get(originalId);
                rowsList.add(row);
            } else {
                List<Row> rowsList = new ArrayList<>();
                rowsList.add(row);
                map.put(originalId, rowsList);
            }
        }
    }

    public void createMaps(List<Table> originalTablesFromFileList) {
        createMap(map1, originalTablesFromFileList.get(0));
        createMap(map2, originalTablesFromFileList.get(1));
    }

    public void printMaps(List<Table> originalTablesFromFileList) {
        System.out.println();
        System.out.println("Пример Map:");
        System.out.println();
        System.out.println("A");
        System.out.println("ID VALUE ");
        for (List<Row> rowsList1: map1.values()) {
            PrintList.printRowsList(rowsList1);
        }
        System.out.println();
        System.out.println("B");
        System.out.println("ID VALUE ");
        for (List<Row> rowsList2: map2.values()) {
            PrintList.printRowsList(rowsList2);
        }
    }

    public void createMapRowsInnerJoinTable() {
        for (Integer id1 : map1.keySet()) {
            for (Integer id2 : map2.keySet()) {
                if (id1 == id2) {
                    resultMapList = new ArrayList<>();
                    List<Row> rowsList1 = map1.get(id1);
                    List<Row> rowsList2 = map2.get(id2);
                    for (int i = 0; i < rowsList1.size(); i++) {
                        for (int j = 0; j < rowsList2.size(); j++) {
                            Row row1 = rowsList1.get(i);
                            Row row = row1.createRowFromTwoTables(row1, rowsList2.get(j));
                            resultMapList.add(row);
                        }
                    }
                    resultMap.put(id1, resultMapList);
                }
            }
        }
    }

    public void createAndPrintMapRowsLeftJoinTable() {
        for (Integer id1 : map1.keySet()) {
            if (!resultMap.containsKey(id1)) {
                resultMap.put(id1, map1.get(id1));
            }
        }
        printResultMap();
    }

    public void printResultMap() {
        System.out.println();
        System.out.println("Результат Map:");
        System.out.println();
        System.out.println("ID A.VALUE B.VALUE ");
        for (List<Row> rowsList: resultMap.values()) {
            PrintList.printRowsList(rowsList);
        }
    }
}
