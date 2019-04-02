package edu.iis.mto.similarity;

import org.junit.Assert;
import org.junit.Test;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.junit.Assert.*;

public class SimilarityFinderTest {

    SequenceSearcherDubler searcher = new SequenceSearcherDubler();
    SimilarityFinder similarityFinder = new SimilarityFinder(this.searcher);

    @Test
    public void calculateSimilarityForEmptySequences() {

        int[] seq1 = {};
        int[] seq2 = {};
        double result = 1.0;
        double delta = 0.01;
        Assert.assertEquals(result, similarityFinder.calculateJackardSimilarity(seq1, seq2), delta);
    }
}