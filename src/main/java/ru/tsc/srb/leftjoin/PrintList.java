package ru.tsc.srb.leftjoin;

import java.util.List;

public class PrintList {

    public static void printList(List<Table> arrayList) {
        System.out.println();
        for (Table tableFromFile: arrayList) {
            System.out.println(tableFromFile.getNameTable());
            System.out.print(tableFromFile.getNameId() + " ");
            List<String> namesValuesList = tableFromFile.getNamesValuesList();
            for (String nameValue: namesValuesList) {
                System.out.print(nameValue + " ");
            }
            System.out.println();
            printRowsList(tableFromFile.getRowsList());
            System.out.println();
        }
    }

    public static void printRowsList(List<Row> rowsList) {
        for (Row row: rowsList) {
            System.out.print(row.getId() + " ");
            List<String> valuesList = row.getValuesList();
            for (String value: valuesList) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    public static void printListResult(List<Table> arrayList, List<Row> resultRowsList) {
        System.out.println();
        System.out.print(arrayList.get(0).getNameId() + " ");
        for (Table tableFromFile: arrayList) {
            System.out.print(tableFromFile.getNameTable() + ".");
            List<String> namesValuesList = tableFromFile.getNamesValuesList();
            for (String nameValue : namesValuesList) {
                System.out.print(nameValue + " ");
            }
        }
        System.out.println();
        printRowsList(resultRowsList);
    }
}
