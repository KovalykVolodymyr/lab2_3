package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;
import org.junit.Assert;
import org.junit.Test;


public class SimilarityFinderTest {


    @Test
    public void calculateSimilarityForEmptySequences() {

        int[] seq1 = {};
        int[] seq2 = {};
        double result = 1.0;
        double delta = 0.01;
        SequenceSearcher searcher = (key, seq) -> {
            return SearchResult.builder().withFound(false).build();
        };
        SimilarityFinder similarityFinder = new SimilarityFinder(searcher);
        Assert.assertEquals(result, similarityFinder.calculateJackardSimilarity(seq1, seq2), delta);
    }

    @Test
    public void sequenceSearcherCallsCounts() {
        int[] seq1 = {1, 2, 3, 6, 7, 8};
        int[] seq2 = {1, 2, 3, 7, 8, 9};
        final int[] callCount = {0};
        SequenceSearcher searcher = (key, seq) -> {
            callCount[0]++;
            return SearchResult.builder().withFound(false).build();

        };
        SimilarityFinder similarityFinder = new SimilarityFinder(searcher);
        similarityFinder.calculateJackardSimilarity(seq1, seq2);

        Assert.assertEquals(callCount[0], seq1.length);

    }


    @Test
    public void calculateSimilarityForSameSequence() {
        int[] seq1 = {1, 2, 3};
        int[] seq2 = {1, 2, 3};
        SequenceSearcher searcher = (key, seq) -> {
            return SearchResult.builder().withFound(true).build();
        };
        SimilarityFinder similarityFinder = new SimilarityFinder(searcher);
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        Assert.assertEquals(result, 1, 0.01);

    }

    @Test
    public void calculateSimilarityForOnlyOneEmptySequence() {
        int[] seq1 = {1, 2, 3};
        int[] seq2 = {};
        SequenceSearcher searcher = (key, seq) -> {
            return SearchResult.builder().withFound(false).build();
        };
        SimilarityFinder similarityFinder = new SimilarityFinder(searcher);
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        Assert.assertEquals(result, 0, 0.01);

    }


    @Test
    public void calculationSymmetryForDifferentSizedSequence() {
        int[] seq1 = {1, 2, 3, 4, 5, 6};
        int[] seq2 = {1, 2, 3, 4};

        SequenceSearcher searcher = (key, seq) -> {
            if (key < 5) {
                return SearchResult.builder().withFound(true).build();
            } else {
                return SearchResult.builder().withFound(false).build();
            }

        };
        SimilarityFinder similarityFinder = new SimilarityFinder(searcher);
        double resultLeft = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        double resultRight = similarityFinder.calculateJackardSimilarity(seq2, seq1);
        Assert.assertEquals(resultLeft, resultRight, 0.01);
    }


    @Test
    public void calculateSimilarityhNegativeNumberSequence() {
        int[] seq1 = {-1, -2, -3, -6, -7, -8};
        int[] seq2 = {-1, -2, -3, -6, -7, -8};
        SequenceSearcher searcher = (key, seq) -> {

            return SearchResult.builder().withFound(true).build();


        };
        SimilarityFinder similarityFinder = new SimilarityFinder(searcher);
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        Assert.assertEquals(result, 1, 0.01);

    }

}