package edu.iis.mto.similarity;

import org.junit.Assert;
import org.junit.Test;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.junit.Assert.*;

public class SimilarityFinderTest {

    SequenceSearcherDubler searcher = new SequenceSearcherDubler();
    SimilarityFinder similarityFinder = new SimilarityFinder(searcher);

    @Test
    public void calculateSimilarityForEmptySequences() {

        int[] seq1 = {};
        int[] seq2 = {};
        double result = 1.0;
        double delta = 0.01;
        Assert.assertEquals(result, similarityFinder.calculateJackardSimilarity(seq1, seq2), delta);
    }

    @Test public void sequenceSearcherCallsCounts() {
        int[] seq1 = {1, 2, 3, 6, 7, 8};
        int[] seq2 = {1, 2, 3, 7, 8, 9};
        similarityFinder.calculateJackardSimilarity(seq1, seq2);
        int counter = searcher.getCallCounter();
        Assert.assertEquals(counter, seq1.length);

    }

    @Test
    public void calculateSimilarityForSameSequence() {
        int[] seq1 = {1,2,3};
        int[] seq2 = {1,2,3};
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        Assert.assertEquals(result, similarityFinder.calculateJackardSimilarity(seq1, seq2), 0.01);

    }

    @Test
    public void calculationSymmetryForDifferentSizedSequence() {
        int[] seq1 = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] seq2 = {1, 2, 3, 7, 8, 9};
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        Assert.assertEquals(result, similarityFinder.calculateJackardSimilarity(seq1, seq2), 0.01);
    }


}