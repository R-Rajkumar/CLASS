package com.arima.classengine.engine;

/**
 * Created with IntelliJ IDEA.
 * User: Rajkumar
 * Date: 12/6/13
 * Time: 5:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExamStandard {

    private int[] termCount;
    private int[] generalCount;
    private String term;
    private String general;
    private double jaccardIndex;
    private double sequenceAlignmentScore;

    /**
     *
     * @return the array of results counts in term exam
     */
    public int[] getTermCount() {
        return termCount;
    }

    /**
     *
     * @param termCount the array of results counts in term exam
     */
    public void setTermCount(int[] termCount) {
        this.termCount = termCount;
    }

    /**
     *
     * @return the array of results counts in general exam
     */
    public int[] getGeneralCount() {
        return generalCount;
    }

    /**
     *
     * @param generalCount the array of results counts in general exam
     */
    public void setGeneralCount(int[] generalCount) {
        this.generalCount = generalCount;
    }

    /**
     *
     * @return the String of term exam results
     */
    public String getTerm() {
        return term;
    }

    /**
     *
     * @param term the String of term exam results
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     *
     * @return the String of general exam results
     */
    public String getGeneral() {
        return general;
    }

    /**
     *
     * @param general the String of general exam results
     */
    public void setGeneral(String general) {
        this.general = general;
    }

    /**
     *
     * @return the jaccard index
     */
    public double getJaccardIndex() {
        return jaccardIndex;
    }

    /**
     *
     * @param jaccardIndex the jaccard index
     */
    public void setJaccardIndex(double jaccardIndex) {
        this.jaccardIndex = jaccardIndex;
    }

    /**
     *
     * @return the sequence alignment score
     */
    public double getSequenceAlignmentScore() {
        return sequenceAlignmentScore;
    }

    /**
     *
     * @param sequenceAlignmentScore the sequence alignment score
     */
    public void setSequenceAlignmentScore(double sequenceAlignmentScore) {
        this.sequenceAlignmentScore = sequenceAlignmentScore;
    }




}

