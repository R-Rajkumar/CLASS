package com.arima.classengine.filter;

import weka.core.Instances;

/**
 * class for letting the classification algorithm to handle the missing values
 */
public class CDiscardInstancesWithMissingValues implements
		CMissingValuesHandler {

    /**
     * discarding instances which are having missing values for any attributes
     * @param train the data set to be validated/handled for missing values
     * @return the modified data set which are not having missing values
     * @throws Exception
     */
    public Instances handleMissingValues(Instances train) throws Exception {
		
		for (int i = 0; i < train.numAttributes(); i++) {
			train.deleteWithMissing(i);
		}

        train = NoiseHandler.RemoveNoise(train);

		return train;
	}

}
