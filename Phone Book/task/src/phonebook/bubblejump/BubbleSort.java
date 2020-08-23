package phonebook.bubblejump;

import phonebook.entries.PhoneBookEntry;

import java.util.List;

public class BubbleSort {


    public static int startSort(List<PhoneBookEntry> array, long startTime,long runUntil){

        for (int i = 0; i < array.size() - 1; i++) {
            for (int j = 0; j < array.size() - 1 - i; j++) {
                if (startTime < runUntil) {
                    startTime = System.currentTimeMillis();
                    if (array.get(j).getName().compareTo(array.get(j + 1).getName()) > 0) {
                        PhoneBookEntry temp = array.get(j);
                        array.set(j, array.get(j + 1));
                        array.set(j + 1, temp);
                    }
                } else {
                    return -1;
                }
            }
        }
        return 1;
    }
    }

