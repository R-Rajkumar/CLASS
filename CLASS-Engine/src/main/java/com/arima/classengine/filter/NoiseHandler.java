package com.arima.classengine.filter;

import com.arima.classengine.utils.Utils;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.InterquartileRange;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;
import weka.filters.unsupervised.instance.RemoveRange;
import weka.filters.unsupervised.instance.RemoveWithValues;

/**
 * Created with IntelliJ IDEA.
 * User: Rajkumar
 * Date: 12/6/13
 * Time: 6:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class NoiseHandler {

    public static void main(String[] args) throws Exception {

        Instances train = Utils.prepareTrainData(10, 1, "HISTORY");
        train = CFilter.removeAttributesByIndices(train, "1");

        ReplaceMissingValues rmv = new ReplaceMissingValues();
        rmv.setInputFormat(train);
        train = Filter.useFilter(train, rmv);

        System.out.println(RemoveNoise(train));

    }

    public static Instances RemoveNoise(Instances train) throws Exception {

        InterquartileRange interquartileRange = new InterquartileRange();
        interquartileRange.setInputFormat(train);
        interquartileRange.setExtremeValuesAsOutliers(true);
        train = Filter.useFilter(train, interquartileRange);

        RemoveWithValues removeWithValues = new RemoveWithValues();
        removeWithValues.setInputFormat(train);
        String[] s = {"-C", "last", "-L", "2"};
        removeWithValues.setOptions(s);
        train = Filter.useFilter(train, removeWithValues);
        train = CFilter.removeAttributesByIndices(train,"last");

        RemoveWithValues removeWithValues2 = new RemoveWithValues();
        removeWithValues2.setInputFormat(train);
        String[] s2 = {"-C", "last", "-L", "2"};
        removeWithValues2.setOptions(s2);
        train = Filter.useFilter(train, removeWithValues2);
        train = CFilter.removeAttributesByIndices(train,"last");

        return train;
    }
}
