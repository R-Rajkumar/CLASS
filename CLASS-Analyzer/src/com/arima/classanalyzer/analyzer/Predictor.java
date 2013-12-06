package com.arima.classanalyzer.analyzer;

import com.arima.classanalyzer.core.CFinal;
import com.arima.classanalyzer.core.ExamStandard;
import com.arima.classanalyzer.webs.client.localhost._7070.class_wsdl.EngineService;
import com.arima.classanalyzer.webs.client.localhost._7070.class_wsdl.IEngine;
import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Predictor {

    String url = "jdbc:mysql://localhost:3306/";
    String dbName = "class_analyzer";
    String driver = "com.mysql.jdbc.Driver";
    String userName = "root";
    String password = "";

    /**
     * TODO: Need to remove the main and arrange things.
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {                                  //predictorViaWeb
        List<String> subjects = new ArrayList<String>();
        List<Integer> marks = new ArrayList<Integer>();
        List<Integer> indexNoList = new ArrayList<Integer>();

        subjects.add("SAIVISM");
        subjects.add("MATHEMATICS");
//        subjects.add("SCIENCE AND TECHNOLOGY");
        subjects.add("TAMIL LANGUAGE");
        subjects.add("ENGLISH LANGUAGE");
        subjects.add("HISTORY");
        subjects.add("INFORMATION AND COMMUNICATION TECHNOLOGY");
        //       subjects.add("BUSSINESS AND ACCOUNTING");

        marks.add(90);
        marks.add(null);
//        marks.add(96);
        marks.add(90);
        marks.add(82);
        marks.add(96);
        marks.add(null);
//        marks.add(96);

        EngineService engineService = new EngineService();
        IEngine engine = engineService.getEnginePort();
       // System.out.println(ProfileMatcher.getNearestGlobalProfiles(11, 3, subjects, marks));
                              ExamStandard ex= engine.getStudentBasedStandard(11086, 2009, 11, "SCIENCE AND TECHNOLOGY")    ;


        System.out.println(ex.getGeneral());
        System.out.println(ex.getGeneralCount()[3]);
        System.out.println(ex.getJaccardIndex());
        System.out.println(ex.getSequenceAlignmentScore());
        System.out.println(ex.getTerm());
        System.out.println(ex.getTermCount()[2]);
//        System.out.println(ProfileMatcher.getNearestLocalProfiles(11086, 11, 3, subjects, marks));
//        System.out.println(engine.sayHelloCLASS());
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the dbName
     */
    public String getDbName() {
        return dbName;
    }

    /**
     * @param dbName the dbName to set
     */
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    /**
     * @return the driver
     */
    public String getDriver() {
        return driver;
    }

    /**
     * @param driver the driver to set
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Predict next term performance from past performances
     *
     * @param conn
     * @param grade
     * @param term
     * @param subject
     * @param index_no
     * @param marks
     * @return
     * @throws Exception
     */
    public String predictNextAsResults(Connection conn, int year, int grade, int term, String subject, int index_no, ArrayList<Integer> marks) throws Exception {

        return CFinal.predictNextTermAsResults(conn, year, grade, term, subject, index_no, marks);
    }

    /**
     * Returns marks ranges of results
     *
     * @param grade
     * @param term
     * @param subject
     * @return
     */
    public ArrayList<Integer> predictNext(Connection conn, int year, int grade, int term, String subject, int index_no, ArrayList<Integer> marks) throws Exception {

        return CFinal.predictNextTerm(conn, year, grade, term, subject, index_no, marks);

    }

    /**
     * Initiate connection to CLASS Database
     *
     * @param url
     * @param username
     * @param password
     * @return
     */
    public Connection connect(String url, String dbName, String username, String password) {
        try {
            Class.forName(driver).newInstance();
            Connection conn = (Connection) DriverManager.getConnection(url + dbName, username, password);
            return conn;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    public boolean close(Connection conn) {
        try {
            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


   }
