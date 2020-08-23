package phonebook;

import phonebook.bubblejump.BubbleSort;
import phonebook.bubblejump.JumpSearch;
import phonebook.entries.PhoneBookEntry;
import phonebook.hashtable.HashTable;
import phonebook.linear.LinearSearch;
import phonebook.quickbinary.BinarySearch;
import phonebook.quickbinary.QuickSort;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static phonebook.TimeConverter.*;


public class Main {
    static List<PhoneBookEntry> rows = new ArrayList<>();
    static List<String> namesToFind = new ArrayList<>();
    static long linearTimeTaken;

    public static void main(String[] args) {


        String pathToDirectory = "C:\\Users\\thegedus\\Downloads\\directory.txt";
        String pathToFind = "C:\\Users\\thegedus\\Downloads\\find.txt";

        readInFiles(pathToDirectory, pathToFind);

        linearDemo();

        System.out.println();
        jumpDemo();
        System.out.println();
        rows.clear();
        namesToFind.clear();
        readInFiles(pathToDirectory, pathToFind);
        binaryDemo();
        System.out.println();

        hashtableDemo();


    }

    private static void hashtableDemo() {

        System.out.println("Start searching (hash table)...");
        HashTable<String, String> table = new HashTable<>(rows.size() * 3);
        long sortStart = System.currentTimeMillis();

        for (PhoneBookEntry row : rows) {
            table.put(row.getName(), row.getPhoneNum());
        }

        long sortEnd = System.currentTimeMillis();
        long startTime = System.currentTimeMillis();
        int found = 0;
        for (String s : namesToFind) {
            if (table.get(s) != null) {
                found++;
            }
        }
        long endTime = System.currentTimeMillis();

        //SearchTime
        long timeTaken = endTime - startTime;
        long sortTimeTaken = sortEnd - sortStart;
        long allTimeTaken = (endTime + sortEnd) - (startTime + sortStart);

        System.out.print("Found " + found + " / " + namesToFind.size() + " entries. ");
        TimeConverter.calcTime(allTimeTaken);
        System.out.println("Time taken: " + minutes + " min. " + seconds + " sec. " + milliseconds + " ms.");
        TimeConverter.calcTime(sortTimeTaken);
        System.out.println("Creating time: " + minutes + " min. " + seconds + " sec. " + milliseconds + " ms.");
        TimeConverter.calcTime(timeTaken);
        System.out.println("Searching time: " + minutes + " min. " + seconds + " sec. " + milliseconds + " ms.");
    }

    private static void linearDemo() {
        long startTime = System.currentTimeMillis();
        System.out.println("Start searching (linear search)...");
        LinearSearch.startSearch(namesToFind, rows);
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        linearTimeTaken = timeTaken;
        TimeConverter.calcTime(timeTaken);

        System.out.println("Time taken: " + minutes + " min. " + seconds + " sec. " + milliseconds + " ms.");
    }

    private static void binaryDemo() {
        System.out.println("Start searching (quick sort + binary search)...");

        long sortStart = System.currentTimeMillis();
        QuickSort.quickSort(rows, 0, rows.size() - 1);
        long sortEnd = System.currentTimeMillis();

        long startTime = System.currentTimeMillis();
        BinarySearch.binarySearch(rows,namesToFind);
        long endTime = System.currentTimeMillis();

        long sortTimeTaken = sortEnd - sortStart;
        long timeTaken = endTime - startTime;
        long allTimeTaken = (endTime + sortEnd) - (startTime + sortStart);
        TimeConverter.calcTime(allTimeTaken);
        System.out.println("Time taken: " + minutes + " min. " + seconds + " sec. " + milliseconds + " ms.");
        TimeConverter.calcTime(sortTimeTaken);
        System.out.println("Sorting time: " + minutes + " min. " + seconds + " sec. " + milliseconds + " ms.");
        TimeConverter.calcTime(timeTaken);
        System.out.println("Searching time: " + minutes + " min. " + seconds + " sec. " + milliseconds + " ms.");
    }

    private static void readInFiles(String pathToDirectory, String pathToFind) {
        try {
            Files.lines(Paths.get(pathToDirectory))
                    .map(row -> row.split("[0-9]+ "))
                    .forEach(row -> rows.add(new PhoneBookEntry(row[0], row[1])));

            Files.lines(Paths.get(pathToFind))
                    .forEach(namesToFind::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void jumpDemo() {

        System.out.println("Start searching (bubble sort + jump search)...");

        long sortStart = System.currentTimeMillis();
        if (BubbleSort.startSort(rows, sortStart, sortStart + (linearTimeTaken * 10)) < 0) {
            long sortEnd = System.currentTimeMillis();

            long linearStart = System.currentTimeMillis();
            LinearSearch.startSearch(namesToFind, rows);
            long linearEnd = System.currentTimeMillis();

            long sortTimeTaken = sortEnd - sortStart;
            long linearTimeTaken = linearEnd - linearStart;
            long allTimeTaken = (linearEnd + sortEnd) - (linearStart + sortStart);

            TimeConverter.calcTime(allTimeTaken);
            System.out.print("Time taken: " + minutes + " min. " + seconds + " sec. " + milliseconds + " ms.\n");
            TimeConverter.calcTime(sortTimeTaken);
            System.out.println("Sorting time: " + minutes + " min. " + seconds + " sec. " + milliseconds + " ms. - STOPPED, moved to linear search");
            TimeConverter.calcTime(linearTimeTaken);
            System.out.println("Searching time: " + minutes + " min. " + seconds + " sec. " + milliseconds + " ms.");

        } else {
            long sortEnd = System.currentTimeMillis();

            long startTime = System.currentTimeMillis();
            JumpSearch.startSearch(rows, namesToFind);
            long endTime = System.currentTimeMillis();

            long sortTimeTaken = sortEnd - sortStart;
            long searchTimeTaken = endTime - startTime;
            long allTimeTaken = (endTime + sortEnd) - (startTime + sortStart);

            TimeConverter.calcTime(allTimeTaken);
            System.out.println("Time taken: " + TimeConverter.minutes + " min. " + TimeConverter.seconds + " sec. " + TimeConverter.milliseconds + " ms.");
            TimeConverter.calcTime(sortTimeTaken);
            System.out.println("Sorting time: " + TimeConverter.minutes + " min. " + TimeConverter.seconds + " sec. " + TimeConverter.milliseconds + " ms.");
            TimeConverter.calcTime(searchTimeTaken);
            System.out.println("Searching time: " + TimeConverter.minutes + " min. " + seconds + " sec. " + milliseconds + " ms.");

        }
    }

}
