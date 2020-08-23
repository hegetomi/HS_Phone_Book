package phonebook.linear;

import phonebook.entries.PhoneBookEntry;
import java.util.List;

public class LinearSearch {

    private LinearSearch(){}


    public static void startSearch(List<String> namesToFind, List<PhoneBookEntry> rows){
        int found = 0;
        for (String s : namesToFind) {
            for (PhoneBookEntry row : rows) {
                if (row.getName().equals(s)) {
                    found++;
                    break;
                }
            }
        }
        System.out.print("Found " + found + " / " + namesToFind.size() + " entries. ");

    }

}
