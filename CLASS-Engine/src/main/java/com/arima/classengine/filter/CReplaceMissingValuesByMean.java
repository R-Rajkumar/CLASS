package com.arima.classengine.filter;

import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;

/**
 * class for letting the classification algorithm to handle the missing values
 */
public class CReplaceMissingValuesByMean implements CMissingValuesHandler {

    /**
     * replacing missing values my means of each attributes
     * @param train the data set to be validated/handled for missing values
     * @return the modified data set which are not having missing values
     * @throws Exception
     */
	public Instances handleMissingValues(Instances train) throws Exception {

			ReplaceMissingValues rmv = new ReplaceMissingValues();
			rmv.setInputFormat(train);
			train = Filter.useFilter(train, rmv);

        train = NoiseHandler.RemoveNoise(train);

		return train;
	}

}
