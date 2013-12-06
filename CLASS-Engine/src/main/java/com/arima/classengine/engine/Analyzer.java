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

	public static void updateModel(int year, int grade, int term, String subject) throws Exception{
		CAnalyzer.updateModel(year, grade, term, subject);
	}

	public static Model getModel(int year, int grade, int term, String subject) throws Exception{
		return CAnalyzer.loadModelFromDatabase(year, grade, term, subject);
	}


	//it will return nearest profiles from same school (ySchool DB index numbers)
	public static ArrayList<Integer> getNearestProfiles(int schoolNo, int grade, int term, List<String> subjects, List<Integer> marks) throws Exception{	
		return NearestProfile.getNearestProfiles(schoolNo, grade, term, subjects, marks);
	}
	
	//it will return nearest profiles from all schools (central DB index numbers)
	public static ArrayList<Integer> getNearestProfiles(int grade, int term, List<String> subjects, List<Integer> marks) throws Exception{	
		return NearestProfile.getNearestProfiles(grade, term, subjects, marks);
	}


    public static ExamStandard getExamStandard(int schoolNo, int year, int grade, String subject) throws Exception {
        return SimilarityMeasures.getExamStandard(schoolNo, year, grade, subject);
    }




}
