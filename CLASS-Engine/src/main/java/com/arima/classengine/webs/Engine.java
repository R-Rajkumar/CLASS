package com.arima.classengine.webs;

import com.arima.classengine.comparator.SimilarityMeasures;
import com.arima.classengine.datasync.Synchronizer;
import com.arima.classengine.engine.Analyzer;
import com.arima.classengine.engine.ExamStandard;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dell
 * Date: 11/7/13
 * Time: 1:34 AM
 * To change this template use File | Settings | File Templates.
 */
@WebService(endpointInterface = "com.arima.classengine.webs.IEngine", targetNamespace = "http://localhost:7070/class?wsdl")
public class Engine implements IEngine {


    public String sayHelloCLASS() {
        return "HelloUser!";
    }

    public String insertStudents(String jsonCStudentListString) {

        Synchronizer synchronizer = new Synchronizer();
        return synchronizer.insertStudents(jsonCStudentListString);

    }

    public String insertExamPerformance(String jsonCExamString) {

        Synchronizer synchronizer = new Synchronizer();
        return synchronizer.insertExamPerformance(jsonCExamString);

    }

    public ArrayList<Integer> getNearestLocalProfiles(int schoolNo, int grade, int term, List<String> subjects, List<Integer> marks) {
        try {
            return Analyzer.getNearestProfiles(schoolNo, grade, term, subjects, marks);
        } catch (Exception e) {
            return null;


        }
    }

    public ArrayList<Integer> getNearestGlobalProfiles(int grade, int term, List<String> subjects, List<Integer> marks) {
        try {
            return Analyzer.getNearestProfiles(grade, term, subjects, marks);
        } catch (Exception e) {
            return null;


        }

    }



    public ExamStandard getStudentBasedStandard(int schoolNo, int year, int grade, String subject) {
        try {
            return Analyzer.getExamStandard(schoolNo, year, grade, subject);
        } catch (Exception e) {
            return null;


        }


    }

}
