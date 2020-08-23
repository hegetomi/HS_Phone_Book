package phonebook.quickbinary;

import phonebook.entries.PhoneBookEntry;

import java.util.List;

public class BinarySearch {

    public static void binarySearch(List<PhoneBookEntry> rows, List<String> namesToFind) {
        int found = 0;


        for (int i = 0; i < namesToFind.size(); i++) {
            String elem = namesToFind.get(i);
            int left = 0;
            int right = rows.size() - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (elem.equals(rows.get(mid).getName())) {
                    found++;
                    break;
                } else if (elem.compareTo(rows.get(mid).getName()) < 0) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        System.out.print("Found " + found + " / " + namesToFind.size() + " entries. ");
    }
}
