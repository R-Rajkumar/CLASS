package com.arima.classanalyzer.core;

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

    public int[] getTermCount() {
        return termCount;
    }

    public void setTermCount(int[] termCount) {
        this.termCount = termCount;
    }

    public int[] getGeneralCount() {
        return generalCount;
    }

    public void setGeneralCount(int[] generalCount) {
        this.generalCount = generalCount;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    public double getJaccardIndex() {
        return jaccardIndex;
    }

    public void setJaccardIndex(double jaccardIndex) {
        this.jaccardIndex = jaccardIndex;
    }

    public double getSequenceAlignmentScore() {
        return sequenceAlignmentScore;
    }

    public void setSequenceAlignmentScore(double sequenceAlignmentScore) {
        this.sequenceAlignmentScore = sequenceAlignmentScore;
    }




}

