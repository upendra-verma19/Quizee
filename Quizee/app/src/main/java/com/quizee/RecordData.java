package com.quizee;

import java.util.Arrays;
import java.util.Collections;

public class RecordData {
    Integer[] collection = new Integer[10];
    String[] collectionIndex;

    RecordData() {
        for (int i = 0; i < collection.length; i++) {
            collection[i] = i;
        }
        Collections.shuffle(Arrays.asList(collection));

        for (int i = 0; i < collection.length; i++)
            collectionIndex[i] = collection[i].toString();
    }
}
