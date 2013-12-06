package com.arima.classengine.engine;

import java.util.ArrayList;
import java.util.List;

import com.arima.classengine.comparator.SimilarityMeasure;
import com.arima.classengine.comparator.SimilarityMeasures;
import com.arima.classengine.core.CAnalyzer;
import com.arima.classengine.profilematcher.NearestProfile;
import com.arima.classengine.comparator.JaccardIndex;
import com.arima.classengine.comparator.HammingDistance;

public class Analyzer {
    /**
     * update existing models in central database / create new models if not already exists in the database
     * @param year the year
     * @param grade the grade to be predicted
     * @param term the term to be predicted
     * @param subject the subject to be predicted
     * @throws Exception
     */
	public static void updateModel(int year, int grade, int term, String subject) throws Exception{
		CAnalyzer.updateModel(year, grade, term, subject);
	}

    /**
     * load the model from central database
     * @param year the year
     * @param grade the grade to be predicted
     * @param term the term to be predicted
     * @param subject the subject to be predicted
     * @return
     * @throws Exception
     */
	public static Model getModel(int year, int grade, int term, String subject) throws Exception{
		return CAnalyzer.loadModelFromDatabase(year, grade, term, subject);
	}


    /**
     * get nearest profiles within the same school
     * @param schoolNo the school number to be searched
     * @param grade the grade to be considered to match profiles
     * @param term the term to be considered to match the profiles
     * @param subjects the list of subjects to be considered to match the profiles
     * @param marks the list of marks of a given student
     * @return the list of nearest profiles' yschool index numbers
     * @throws Exception
     */
	public static ArrayList<Integer> getNearestProfiles(int schoolNo, int grade, int term, List<String> subjects, List<Integer> marks) throws Exception{	
		return NearestProfile.getNearestProfiles(schoolNo, grade, term, subjects, marks);
	}

    /**
     *  get nearest profiles among schools
     * @param grade the grade to be considered to match profiles
     * @param term the term to be considered to match the profiles
     * @param subjects the list of subjects to be considered to match the profiles
     * @param marks the list of marks of a given student
     * @return the list of nearest profiles' class index numbers
     * @throws Exception
     */
	public static ArrayList<Integer> getNearestProfiles(int grade, int term, List<String> subjects, List<Integer> marks) throws Exception{	
		return NearestProfile.getNearestProfiles(grade, term, subjects, marks);
	}

    /**
     * get the standard of the exams
     * @param schoolNo the school number
     * @param year the year of the exam to be compared
     * @param grade the grade of the exam to be compared
     * @param subject the subject to be compared
     * @return an ExamStandard instance with overall similarity, individual similarity, count of results
     * @throws Exception
     */
    public static ExamStandard getExamStandard(int schoolNo, int year, int grade, String subject) throws Exception {
        return SimilarityMeasures.getExamStandard(schoolNo, year, grade, subject);
    }




}
