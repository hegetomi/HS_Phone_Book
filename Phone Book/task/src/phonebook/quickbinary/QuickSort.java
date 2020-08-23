package phonebook.quickbinary;

import phonebook.entries.PhoneBookEntry;

import java.util.List;

public class QuickSort {

    public static void quickSort(List<PhoneBookEntry> rows, int left, int right) {

        if (left < right) {
            int pivotIndex = partition(rows, left, right);
            quickSort(rows, left, pivotIndex - 1);
            quickSort(rows,pivotIndex + 1, right);
        }
    }

    private static int partition(List<PhoneBookEntry> rows, int left, int right) {
        String pivot = rows.get(right).getName();
        int partitionIndex = left;

        for (int i = left; i < right; i++) {
            if (rows.get(i).getName().compareTo(pivot) < 0) {
                swap(rows, i, partitionIndex);
                partitionIndex++;
            }
        }
        swap(rows, partitionIndex, right);
        return partitionIndex;

    }

    private static void swap(List<PhoneBookEntry> rows, int i, int j) {
        PhoneBookEntry temp = rows.get(i);
        rows.set(i, rows.get(j));
        rows.set(j, temp);

    }


}
