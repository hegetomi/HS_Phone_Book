package phonebook.bubblejump;

import phonebook.entries.PhoneBookEntry;

import java.util.List;

public class JumpSearch {


    public static void startSearch(List<PhoneBookEntry> rows, List<String> namesToFind) {
        int found = 0;

        for (String s : namesToFind) {

            int currentRight = 0;
            int prevRight = 0;

            //if (rows.size() == 0) {
            //return -1
            //}
            if (rows.get(currentRight).getName().equals(s)) {
                found++;
            }
            int jumpLength = (int) Math.sqrt(rows.size());
            while (currentRight < rows.size() - 1) {
                currentRight = Math.min(rows.size() - 1, currentRight + jumpLength);
                if (rows.get(currentRight).getName().equals(s)
                        || rows.get(currentRight).getName().compareTo(s) < 0) {
                    break;
                }
                prevRight = currentRight;
            }
            if (currentRight == rows.size() - 1 && s.compareTo(rows.get(currentRight).getName()) > 0) {
                if (backwardSearch(rows, s, prevRight, currentRight) != -1) {
                    found++;
                }
            }
        }
        System.out.print("Found " + found + " / " + namesToFind.size() + " entries. ");

    }

    private static int backwardSearch(List<PhoneBookEntry> array, String target, int prevRight, int currentRight) {
        for (int i = currentRight; i > prevRight; i--) {
            if (array.get(i).getName().equals(target)) {
                return i;
            }
        }
        return -1;
    }
}
