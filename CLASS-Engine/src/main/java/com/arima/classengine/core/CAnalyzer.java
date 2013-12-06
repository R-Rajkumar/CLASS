package com.arima.classengine.core;

import java.awt.BorderLayout;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.arima.classengine.classifier.*;
import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.DatabaseConnection;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

import com.arima.classengine.engine.Model;
import com.arima.classengine.evaluator.CCrossValidateEvaluator;
import com.arima.classengine.evaluator.CEvaluator;
import com.arima.classengine.filter.CDefaultMissingValueHandler;
import com.arima.classengine.filter.CDiscardInstancesWithMissingValues;
import com.arima.classengine.filter.CFilter;
import com.arima.classengine.filter.CMissingValuesHandler;
import com.arima.classengine.filter.CReplaceMissingValuesByMean;
import com.arima.classengine.utils.Utils;
import com.mysql.jdbc.PreparedStatement;

public class CAnalyzer {

	private final double accuracyThreshold = 75;
	private final boolean isTest = true;
	private CClassifier classifierType;
	private CEvaluator evaluatorType;
	private  int binSize = 5;
	private  Classifier model;
	private  Instances train = null;
	private Evaluation eval = null;
	private double accuracy = 0;
	private CMissingValuesHandler missingValueHandlerType;
	private Classifier tempModel;
	private double timeToBuild = 0;

    /**
     * @return the classifierType
     */
    private CClassifier getClassifierType() {
        return classifierType;
    }

    /**
     * @param classifierType the classifierType to set
     */
    private void setClassifierType(CClassifier classifierType) {
        this.classifierType = classifierType;
    }

    /**
     * @return the evaluatorType
     */
    private CEvaluator getEvaluatorType() {
        return evaluatorType;
    }

    /**
     * @param evaluatorType the evaluatorType to set
     */
    private void setEvaluatorType(CEvaluator evaluatorType) {
        this.evaluatorType = evaluatorType;
    }

    /**
     * @return the binSize
     */
    private int getBinSize() {
        return binSize;
    }

    /**
     * @param binSize the binSize to set
     */
    private void setBinSize(int binSize) {
        this.binSize = binSize;
    }

    /**
     * @return the model
     */
    private Classifier getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    private void setModel(Classifier model) {
        this.model = model;
    }

    /**
     * @return the train
     */
    private Instances getTrain() {
        return train;
    }

    /**
     * @param train the train to set
     */
    private void setTrain(Instances train) {
        this.train = train;
    }

    /**
     * @return the eval
     */
    private Evaluation getEval() {
        return eval;
    }

    /**
     * @param eval the eval to set
     */
    private void setEval(Evaluation eval) {
        this.eval = eval;
    }

    /**
     * @return the accuracy
     */
    private double getAccuracy() {
        return accuracy;
    }

    /**
     * @param accuracy the accuracy to set
     */
    private void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    /**
     * @return the accuracyThreshold
     */
    private double getAccuracyThreshold() {
        return accuracyThreshold;
    }

    /**
     * @return the missingValueHandlerType
     */
    private CMissingValuesHandler getMissingValueHandlerType() {
        return missingValueHandlerType;
    }

    /**
     * @param missingValueHandlerType the missingValueHandlerType to set
     */
    private void setMissingValueHandlerType(
            CMissingValuesHandler missingValueHandlerType) {
        this.missingValueHandlerType = missingValueHandlerType;
    }

    /**
     * @return the isTest
     */
    private boolean isTest() {
        return isTest;
    }

    //for stats file naming only , subject parameter is there

    /**
     * @return the tempModel
     */
    private Classifier getTempModel() {
        return tempModel;
    }

    /**
     * @param tempModel the tempModel to set
     */
    private void setTempModel(Classifier tempModel) {
        this.tempModel = tempModel;
    }


    public static void main(String[] args) throws Exception {

		updateModel(2008, 10, 2, "SCIENCE AND TECHNOLOGY");
		updateModel(2008, 10, 3, "SCIENCE AND TECHNOLOGY");
		updateModel(2008, 11, 1, "SCIENCE AND TECHNOLOGY");
		updateModel(2008, 11, 2, "SCIENCE AND TECHNOLOGY");
		updateModel(2008, 11, 3, "SCIENCE AND TECHNOLOGY");

		updateModel(2008, 10, 2, "SAIVISM");
		updateModel(2008, 10, 3, "SAIVISM");
		updateModel(2008, 11, 1, "SAIVISM");
		updateModel(2008, 11, 2, "SAIVISM");
		updateModel(2008, 11, 3, "SAIVISM");

		updateModel(2008, 10, 2, "TAMIL LANGUAGE");
		updateModel(2008, 10, 3, "TAMIL LANGUAGE");
		updateModel(2008, 11, 1, "TAMIL LANGUAGE");
		updateModel(2008, 11, 2, "TAMIL LANGUAGE");
		updateModel(2008, 11, 3, "TAMIL LANGUAGE");

		updateModel(2008, 10, 2, "ENGLISH LANGUAGE");
		updateModel(2008, 10, 3, "ENGLISH LANGUAGE");
		updateModel(2008, 11, 1, "ENGLISH LANGUAGE");
		updateModel(2008, 11, 2, "ENGLISH LANGUAGE");
		updateModel(2008, 11, 3, "ENGLISH LANGUAGE");

		updateModel(2008, 10, 2, "MATHEMATICS");
		updateModel(2008, 10, 3, "MATHEMATICS");
		updateModel(2008, 11, 1, "MATHEMATICS");
		updateModel(2008, 11, 2, "MATHEMATICS");
		updateModel(2008, 11, 3, "MATHEMATICS");

		updateModel(2008, 10, 2, "HISTORY");
		updateModel(2008, 10, 3, "HISTORY");
		updateModel(2008, 11, 1, "HISTORY");
		updateModel(2008, 11, 2, "HISTORY");
		updateModel(2008, 11, 3, "HISTORY");

		updateModel(2008, 10, 2, "BUSSINESS AND ACCOUNTING");
		updateModel(2008, 10, 3, "BUSSINESS AND ACCOUNTING");
		updateModel(2008, 11, 1, "BUSSINESS AND ACCOUNTING");
		updateModel(2008, 11, 2, "BUSSINESS AND ACCOUNTING");
		updateModel(2008, 11, 3, "BUSSINESS AND ACCOUNTING");

		updateModel(2008, 10, 2, "INFORMATION AND COMMUNICATION TECHNOLOGY");
		updateModel(2008, 10, 3, "INFORMATION AND COMMUNICATION TECHNOLOGY");
		updateModel(2008, 11, 1, "INFORMATION AND COMMUNICATION TECHNOLOGY");
		updateModel(2008, 11, 2, "INFORMATION AND COMMUNICATION TECHNOLOGY");
		updateModel(2008, 11, 3, "INFORMATION AND COMMUNICATION TECHNOLOGY");

	}

    /**
     * select the best model and update it on central data base
     * @param year the current year
     * @param grade the grade of the given examination
     * @param term the term of the given examination
     * @param subject the subject
     * @throws Exception
     */
	public static void updateModel(int year, int grade, int term, String subject) throws Exception{


		String statFileName = "F:/Projects/CLASS/CLASS-Engine/data/Classifiers-Stats/"+
								year + " " + subject + " grade " + grade + " term " + term +  ".txt";

        //get the best model for the given train data
		CAnalyzer analyzer = getModel(Utils.prepareTrainData(grade, term, subject), statFileName);

		//if it is not running in the test mode
        if(!analyzer.isTest()){

        //load the model from central database for the given subject
		Model model = loadModelFromDatabase(year, grade, term, subject);
        //if it is not in the database, then create new entry, else update the existing model
		if(model.getClassifier() == null){
			boolean b = saveModelToDatabase("jdbc:mysql://localhost:3306/class", "root", "", year, grade, term, subject, analyzer.getModel(), analyzer.getModel().getClass().getName(), analyzer.getBinSize());
			System.out.println("done " + b);
		}else{
			boolean b = updateModelInDatabase("jdbc:mysql://localhost:3306/class", "root", "", year, grade, term, subject, analyzer.getModel(), analyzer.getModel().getClass().getName(), analyzer.getBinSize());
			System.out.println("done " + b);
		}

		}

	}

    /**
     * load the model from central database for the given year, grade, term and subject
     * @param year the current year
     * @param grade the grade
     * @param term the term
     * @param subject the subject
     * @return the Model/Classifier
     * @throws Exception
     */
	public static Model loadModelFromDatabase(int year, int grade, int term, String subject) throws Exception{

		Model model = new Model();
		int bins=-1;
		Classifier cls=null;

		DatabaseConnection dc = new DatabaseConnection();
		dc.setDatabaseURL("jdbc:mysql://localhost:3306/class");
		dc.setUsername("root");
		dc.setPassword("");
		dc.connectToDatabase();

		dc.execute("SELECT model, bins, type FROM  class_analyzer_classifier where year = "+ year +" and grade = " + grade
				+" and term = "
				+term+ " and subject = '" +subject+"'");

		ResultSet rs = dc.getResultSet();;
		while(rs.next()){

			InputStream is = rs.getBinaryStream("model");
			cls =  (Classifier) weka.core.SerializationHelper.read(is);

			model.setYear(year);
			model.setGrade(grade);
			model.setTerm(term);
			model.setSubject(subject);
			model.setClassifier(cls);
			model.setType(rs.getString("type"));
			model.setBins(rs.getInt("bins"));
		}
		dc.close();
		return model;
	}

    /**
     * save a new model in central database
     * @param dbURL the central database URL
     * @param username the central database username
     * @param password the central database password
     * @param year the year of exam held
     * @param grade the grade
     * @param term the term
     * @param subject the subject
     * @param cls the model to be saved
     * @param type the type of the model
     * @param bins the number of bins
     * @return true if successful, else false
     * @throws IOException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
	public static boolean saveModelToDatabase(String dbURL, String username,String password, int year, int grade, int term, String subject,Classifier cls,String type,int bins ) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Boolean re=true;

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(cls);
		byte[] tableAsBytes = baos.toByteArray();
		ByteArrayInputStream bais =
		new ByteArrayInputStream(tableAsBytes);


		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = null;
		try {

			connection = DriverManager.getConnection(dbURL, username, password);
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement("INSERT INTO class_analyzer_classifier (year,grade,term,subject,model,type,bins) VALUES (?,?,?,?,?,?,?)");
			statement.setInt(1,year);
			statement.setInt(2,grade);
			statement.setInt(3,term);
			statement.setString(4, subject);
			statement.setBinaryStream(5,bais, (long) tableAsBytes.length);
			statement.setString(6, type);
			statement.setInt(7,bins);
			statement.executeUpdate();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
			re= false;
		}

		return re;
	}

    /**
     * update/replace the existing model in the central database
     * @param dbURL the central database URL
     * @param username the central database username
     * @param password the central database password
     * @param year the year of exam held
     * @param grade the grade
     * @param term the term
     * @param subject the subject
     * @param cls the model to be replaced
     * @param type the type of the model
     * @param bins the number of bins
     * @return true if successful, else false
     * @throws IOException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
	public static boolean updateModelInDatabase(String dbURL, String username,String password, int year, int grade, int term, String subject,Classifier cls,String type,int bins ) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Boolean re=true;

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(cls);
		byte[] tableAsBytes = baos.toByteArray();
		ByteArrayInputStream bais =
				new ByteArrayInputStream(tableAsBytes);


		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = null;
		try {

			connection = DriverManager.getConnection(dbURL, username, password);
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement("UPDATE class_analyzer_classifier SET model = ? , type = ? ,  bins = ? WHERE year = ? AND grade = ? AND term = ? AND subject = ?");

			statement.setBinaryStream(1,bais, (long) tableAsBytes.length);
			statement.setString(2, type);
			statement.setInt(3,bins);
			statement.setInt(4,year);
			statement.setInt(5,grade);
			statement.setInt(6,term);
			statement.setString(7, subject);

			statement.executeUpdate();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
			re= false;
		}

		return re;
	}

    /**
     * build, evaluate and select the best model for the given train data
     * @param train the data to be trained
     * @param statFileName the file name to update the statistics
     * @return the instance of CAnalyzer
     * @throws Exception
     */
    public static CAnalyzer getModel(Instances train, String statFileName) throws Exception{

		String header;
		CAnalyzer analyzer = new CAnalyzer();
		analyzer.setTrain(train);

        //list of classification algorithms to run
		List<CClassifier> classifiers = new ArrayList<CClassifier>();
		classifiers.add(new CJ48Classifier());
		classifiers.add(new CNaiveBayesClassifier());
//		classifiers.add(new CMultiLayerPerceptronClassifier());
		classifiers.add(new CBoostJ48());

        //list of ways of handling missing values
		List<CMissingValuesHandler> missingValueHandlers = new ArrayList<CMissingValuesHandler>();
		missingValueHandlers.add(new CDefaultMissingValueHandler());
		missingValueHandlers.add(new CReplaceMissingValuesByMean());
		missingValueHandlers.add(new CDiscardInstancesWithMissingValues());

		binsloop:
			for (int bins = 5; bins >= 2; bins--) {

				for(int i=1; i<=classifiers.size(); i++){

					for(int x=1; x<=missingValueHandlers.size(); x++){

						analyzer.timeToBuild = 0;
						train = analyzer.getTrain();
						train = CFilter.removeAttributesByNames(train, "idstudent");

						//handling missing values using different methods
						analyzer.setMissingValueHandlerType(missingValueHandlers.get(x-1));
						train = analyzer.missingValueHandlerType.handleMissingValues(train);

						train = CFilter.numeric2nominal(train, "first-last",bins);

						//Changing nominal labels so that every attribute will have the same
						train = Utils.changeAttributeNominalRange(train, Utils.getAttributeLables(bins, true));

						//Renaming attribute values to different labels such as A,B,C,S,F
						train = Utils.renameAttributes(train, bins);

						analyzer.setClassifierType(classifiers.get(i-1));
						analyzer.setEvaluatorType(new CCrossValidateEvaluator());

						analyzer.timeToBuild = System.nanoTime();
						analyzer.setTempModel(analyzer.classifierType.buildClassifier(train));
						analyzer.setEval(analyzer.evaluatorType.evaluator(analyzer.getTempModel(), train, 10, 1));
						analyzer.timeToBuild = (System.nanoTime() - analyzer.timeToBuild)/1e6;

						if(analyzer.isTest() || ( (!analyzer.isTest()) && (analyzer.getAccuracy() < analyzer.getEval().pctCorrect()) )){
							analyzer.setBinSize(bins);
							analyzer.setModel(analyzer.getTempModel());
							analyzer.setAccuracy(analyzer.getEval().pctCorrect());
						}

						if(!analyzer.isTest()){
							System.out.println("#########################################################");
							System.out.println("Current bins : "+ bins);
							System.out.println("Current model : "+ analyzer.getTempModel().getClass());
							System.out.println("Current model's accuracy : "+ analyzer.getEval().pctCorrect());
							System.out.println("Selected bins : "+ analyzer.getBinSize());
							System.out.println("Selected model upto now : "+ analyzer.getModel().getClass());
							System.out.println("Selected model's accuracy : "+ analyzer.getAccuracy());
							System.out.println("#########################################################");
							System.out.println();
							System.out.println();
						}

                        //if it is not running in test mode, update the stats file
						if(analyzer.isTest()){
							header = "Classifier : " + analyzer.getTempModel().getClass().getName()
									+
									"\n\n"
									+
									"Number of bins : " + analyzer.getBinSize()
									+
									"\n\n"
									+
									"Handling missing values" + analyzer.getMissingValueHandlerType().getClass().getName()
									+
									"\n\n"
									+
									"Time to build the model : " + analyzer.timeToBuild + " ms"
									+
									"\n"
									;
							System.out.println(header);
							System.out.println(analyzer.getAccuracy());
							CFilter.appendfile(statFileName, header);
							CFilter.appendfile(statFileName, analyzer.getEval().toSummaryString());
							CFilter.appendfile(statFileName, "###################################################################" + "\n");
						}
					}
				}

                //if one model passes threshold accuracy, break the loop
				if(!analyzer.isTest() && analyzer.getAccuracy() >= analyzer.getAccuracyThreshold()){
					break binsloop;
				}

			}

        // default voting classifier, in case if there is no good classifier

        if(!analyzer.isTest() && analyzer.getAccuracy() < analyzer.getAccuracyThreshold()){
            int bin = 3;
            train = analyzer.getTrain();
            train = CFilter.removeAttributesByNames(train, "idstudent");

            analyzer.setMissingValueHandlerType(new CDiscardInstancesWithMissingValues());
            train = analyzer.missingValueHandlerType.handleMissingValues(train);

            train = CFilter.numeric2nominal(train, "first-last",bin);

            train = Utils.changeAttributeNominalRange(train, Utils.getAttributeLables(bin, true));

            train = Utils.renameAttributes(train, bin);

            analyzer.setClassifierType(new CVoteClassifier());

            analyzer.setBinSize(bin);
            analyzer.setModel(analyzer.classifierType.buildClassifier(train));

        }

		return analyzer;

	}

    /**
     * select the most important attributes in the given train data
     * @param data the data to be select the attributes
     * @return array of indexes of the most important attributes
     * @throws Exception
     */
    public static int[] selectAttributes(Instances data) throws Exception {

		// setup attribute selection
		AttributeSelection attsel = new AttributeSelection();
		InfoGainAttributeEval eval = new InfoGainAttributeEval();
		Ranker search = new Ranker();
		search.setNumToSelect(5);
		search.setGenerateRanking(true);

		attsel.setEvaluator(eval);
		attsel.setSearch(search);
		attsel.SelectAttributes(data);
		attsel.setRanking(true);
		System.out.println(attsel.toResultsString());
		int[] indices = attsel.selectedAttributes();

        return indices;
	}


}
