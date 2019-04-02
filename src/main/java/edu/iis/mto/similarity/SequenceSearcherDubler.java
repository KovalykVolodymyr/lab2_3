package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

public class SequenceSearcherDubler implements SequenceSearcher {

    private int counter = 0;

    public int getCallCounter() {
        return counter;
    }
    @Override
    public SearchResult search(int key, int[] seq) {

        counter++;
        for (int i = 0; i < seq.length; i++) {
            if (seq[i] == key) {
                return SearchResult.builder().withFound(true).withPosition(i).build();
            }
        }
        return SearchResult.builder().withFound(false).build();
    }
}
