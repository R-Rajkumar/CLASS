package com.arima.classengine.utils;

import com.arima.classengine.filter.CFilter;
import weka.core.Instances;

import java.util.List;

public class Utils {

    public static Instances prepareTrainData(int grade, int term, String subject) throws Exception {

        Instances train = null;

        if (grade == 10 || grade == 12) {
            train = CFilter.retrieveDatasetFromDatabase(
                    Utils.createPredictionQuery(grade, 1, subject), "root", "");
        }

        if (grade == 11 || grade == 13) {
            train = CFilter.retrieveDatasetFromDatabase(
                    Utils.createPredictionQuery(grade - 1, 1, subject), "root", "");
        }


        if (grade == 11 || grade == 13) {
            term = term + 3;
        }

        int tempGrade = grade, tempTerm = term;

        for (int i = 2; i <= term; i++) {
            tempTerm = i;

            if (grade == 11 || grade == 13) {
                if (i <= 3) {
                    tempGrade = grade - 1;
                    tempTerm = i;
                }

                if (i > 3) {
                    tempGrade = grade;
                    tempTerm = i - 3;
                }

            }

            train = Instances.mergeInstances(train,
                    CFilter.removeAttributesByIndices(CFilter.retrieveDatasetFromDatabase(
                            Utils.createPredictionQuery(tempGrade, tempTerm, subject), "root", ""), "1"));

        }
        return train;
    }

    public static Instances prepareTrainData(int schoolNo, int grade, int term, String subject) throws Exception {

        Instances train = null;

        if (grade == 10 || grade == 12) {
            train = CFilter.retrieveDatasetFromDatabase(
                    Utils.createPredictionQuery(schoolNo, grade, 1, subject), "root", "");
        }

        if (grade == 11 || grade == 13) {
            train = CFilter.retrieveDatasetFromDatabase(
                    Utils.createPredictionQuery(schoolNo, grade - 1, 1, subject), "root", "");
        }


        if (grade == 11 || grade == 13) {
            term = term + 3;
        }

        int tempGrade = grade, tempTerm = term;

        for (int i = 2; i <= term; i++) {
            tempTerm = i;

            if (grade == 11 || grade == 13) {
                if (i <= 3) {
                    tempGrade = grade - 1;
                    tempTerm = i;
                }

                if (i > 3) {
                    tempGrade = grade;
                    tempTerm = i - 3;
                }

            }

            train = Instances.mergeInstances(train,
                    CFilter.removeAttributesByIndices(CFilter.retrieveDatasetFromDatabase(
                            Utils.createPredictionQuery(schoolNo, tempGrade, tempTerm, subject), "root", ""), "1"));

        }
        return train;
    }

    public static Instances prepareStandardizedAndNormalizedTrainDataAcrossSchools(int grade, int term, String subject) throws Exception {

        Instances train = CFilter.standardize(prepareTrainData(11089, grade, term, subject));
        Instances train2 = CFilter.standardize(prepareTrainData(11086, grade, term, subject));

        for (int i = 0; i < train2.numInstances(); i++) {

            train.add(train2.instance(i));
        }

        return CFilter.normalize(train);
    }

    public static Instances prepareStandardizedAndNormalizedTrainDataAcrossSchools(int schoolNo, int grade, int term, String subject) throws Exception {

        return CFilter.normalize(CFilter.standardize(prepareTrainData(schoolNo, grade, term, subject)));
    }

    public static Instances prepareStandardizedAndNormalizedTrainDataAcrossSchoolsAndTerms(int schoolNo, int grade, int term, String subject) throws Exception {

        Instances train = prepareTrainData(schoolNo, grade, term, subject);

        train = CFilter.standardize(train);

        double zScoreMin = 100;
        double zScoreMax = -100;

        for (int i = 1; i < train.numAttributes(); i++) {
            if (train.attributeStats(i).numericStats.min < zScoreMin)
                zScoreMin = train.attributeStats(i).numericStats.min;
            if (train.attributeStats(i).numericStats.max > zScoreMax)
                zScoreMax = train.attributeStats(i).numericStats.max;
        }

        for (int i = 1; i < train.numAttributes(); i++) {
            for (int j = 0; j < train.numInstances(); j++) {

                double value = 100 * (train.instance(j).value(i) - zScoreMin) / (zScoreMax - zScoreMin);
                train.instance(j).setValue(i, value);

            }

        }


        return train;
    }

    public static Instances prepareStandardizedAndNormalizedTrainDataAcrossSchoolsAndTerms(int grade, int term, String subject) throws Exception {

        Instances train = prepareTrainData(11089, grade, term, subject);
        train = CFilter.standardize(train);
        Instances train2 = prepareTrainData(11086, grade, term, subject);
        train2 = CFilter.standardize(train2);

        for (int i = 0; i < train2.numInstances(); i++) {

            train.add(train2.instance(i));
        }

        double zScoreMin = 100;
        double zScoreMax = -100;

        for (int i = 1; i < train.numAttributes(); i++) {
            if (train.attributeStats(i).numericStats.min < zScoreMin)
                zScoreMin = train.attributeStats(i).numericStats.min;
            if (train.attributeStats(i).numericStats.max > zScoreMax)
                zScoreMax = train.attributeStats(i).numericStats.max;
        }

        for (int i = 1; i < train.numAttributes(); i++) {
            for (int j = 0; j < train.numInstances(); j++) {

                double value = 100 * (train.instance(j).value(i) - zScoreMin) / (zScoreMax - zScoreMin);
                train.instance(j).setValue(i, value);

            }

        }


        return train;
    }

    public static String getAttributeLables(int bins, boolean isLetter) {

        String lables;
        if (isLetter) {

            switch (bins) {
                case 2:
                    lables = "1,2";
                    break;
                case 3:
                    lables = "1,2,3";
                    break;
                case 4:
                    lables = "1,2,3,4";
                    break;
                case 5:
                    lables = "1,2,3,4,5";
                    break;
                default:
                    lables = "1,2,3,4,5";
                    break;
            }
        } else {

            switch (bins) {
                case 2:
                    lables = "F,S";
                    break;
                case 3:
                    lables = "F,S,C";
                    break;
                case 4:
                    lables = "F,S,C,B";
                    break;
                case 5:
                    lables = "F,S,C,B,A";
                    break;
                default:
                    lables = "F,S,C,B,A";
                    break;
            }

        }

        return lables;
    }

    public static Instances renameAttributes(Instances train, int bins) {

        switch (bins) {

            case 2:
                for (int j = 0; j < train.numAttributes(); j++) {
                    train.renameAttributeValue(j, 0, "F");
                    train.renameAttributeValue(j, 1, "S");
                }
                break;

            case 3:
                for (int j = 0; j < train.numAttributes(); j++) {
                    train.renameAttributeValue(j, 0, "F");
                    train.renameAttributeValue(j, 1, "S");
                    train.renameAttributeValue(j, 2, "C");
                }
                break;

            case 4:
                for (int j = 0; j < train.numAttributes(); j++) {
                    train.renameAttributeValue(j, 0, "F");
                    train.renameAttributeValue(j, 1, "S");
                    train.renameAttributeValue(j, 2, "C");
                    train.renameAttributeValue(j, 3, "B");
                }
                break;

            case 5:
                for (int j = 0; j < train.numAttributes(); j++) {
                    train.renameAttributeValue(j, 0, "F");
                    train.renameAttributeValue(j, 1, "S");
                    train.renameAttributeValue(j, 2, "C");
                    train.renameAttributeValue(j, 3, "B");
                    train.renameAttributeValue(j, 4, "A");
                }
                break;

            default:
                for (int j = 0; j < train.numAttributes(); j++) {
                    train.renameAttributeValue(j, 0, "F");
                    train.renameAttributeValue(j, 1, "S");
                    train.renameAttributeValue(j, 2, "C");
                    train.renameAttributeValue(j, 3, "B");
                    train.renameAttributeValue(j, 4, "A");
                }
                break;
        }

        return train;
    }

    public static Instances changeAttributeNominalRange(Instances train, String labels) throws Exception {

        for (int i = 0; i < train.numAttributes(); i++) {
            train = CFilter.changeAttributeNominalValues(train, i + 1, labels);
        }

        return train;
    }

    public static String createPredictionQuery(int grade, int term, String subject) {
        //ex.school_no = 11089 and


        String query = "select st.idstudent, mk.marks as " + subject.replaceAll(" ", "_") + "_" + grade + "_" + term +
                " from " +
                "(exam ex  join subject sub on (sub.idsubject=ex.subject_idsubject)) " +
                "join marks mk on (mk.exam_idexam=ex.idexam) " +
                "join student_performance stpe on (mk.student_performance_idstudent_performance=stpe.idstudent_performance) " +
                "join student st on (st.idstudent=stpe.student_idstudent) " +
                "where ex.grade=" + grade + " and ex.term=" + term + " and sub.subject_name='" + subject + "' ";
        return query;
    }

    public static String createPredictionQuery(int school_no, int grade, int term, String subject) {
        //ex.school_no = 11089 and


        String query = "select st.idstudent, mk.marks as " + subject.replaceAll(" ", "_") + "_" + grade + "_" + term +
                " from " +
                "(exam ex  join subject sub on (sub.idsubject=ex.subject_idsubject)) " +
                "join marks mk on (mk.exam_idexam=ex.idexam) " +
                "join student_performance stpe on (mk.student_performance_idstudent_performance=stpe.idstudent_performance) " +
                "join student st on (st.idstudent=stpe.student_idstudent) " +
                "where ex.school_no = " + school_no + " and ex.grade=" + grade + " and ex.term=" + term + " and sub.subject_name='" + subject + "' ";
        return query;
    }

    public static String createPredictionQuery(int school_no, int year, int grade, int term, String subject) {
        //ex.school_no = 11089 and
        String date = year + "-01-01";

        String query = "select st.idstudent, mk.marks as " + subject.replaceAll(" ", "_") + "_" + grade + "_" + term +
                " from " +
                "(exam ex  join subject sub on (sub.idsubject=ex.subject_idsubject)) " +
                "join marks mk on (mk.exam_idexam=ex.idexam) " +
                "join student_performance stpe on (mk.student_performance_idstudent_performance=stpe.idstudent_performance) " +
                "join student st on (st.idstudent=stpe.student_idstudent) " +
                "where ex.school_no = " + school_no + " and ex.date = '" + date + "' and ex.grade=" + grade + " and ex.term=" + term + " and sub.subject_name='" + subject + "' ";
        return query;
    }

    public static String createComparisionQuery(int school_no, int grade, int term, String subject, int lower, int upper) {
        //ex.school_no = 11089 and


        String query = "select st.idstudent, mk.marks as " + subject.replaceAll(" ", "_") + "_" + grade + "_" + term +
                " from " +
                "(exam ex  join subject sub on (sub.idsubject=ex.subject_idsubject)) " +
                "join marks mk on (mk.exam_idexam=ex.idexam) " +
                "join student_performance stpe on (mk.student_performance_idstudent_performance=stpe.idstudent_performance) " +
                "join student st on (st.idstudent=stpe.student_idstudent) " +
                "where ex.school_no = " + school_no +
                " and ex.grade=" + grade +
                " and ex.term=" + term +
                " and sub.subject_name='" + subject + "' " +
                "and mk.marks BETWEEN  " + lower + " AND " + upper;
        return query;
    }

    public static String createComparisionQuery(int school_no, int year, int grade, int term, String subject, int lower, int upper) {
        //ex.school_no = 11089 and
        String date = year + "-01-01";

        String query = "select st.idstudent, mk.marks as " + subject.replaceAll(" ", "_") + "_" + grade + "_" + term +
                " from " +
                "(exam ex  join subject sub on (sub.idsubject=ex.subject_idsubject)) " +
                "join marks mk on (mk.exam_idexam=ex.idexam) " +
                "join student_performance stpe on (mk.student_performance_idstudent_performance=stpe.idstudent_performance) " +
                "join student st on (st.idstudent=stpe.student_idstudent) " +
                "where ex.school_no = " + school_no +
                " and ex.date='" + date +
                "' and ex.grade=" + grade +
                " and ex.term=" + term +
                " and sub.subject_name='" + subject + "' " +
                "and mk.marks BETWEEN  " + lower + " AND " + upper;
        return query;
    }

    public static Instances prepareProfileMatcherData(int grade, int term, List<String> subjects) throws Exception {


        Instances instances = CFilter.retrieveDatasetFromDatabase(
                Utils.createPredictionQuery(grade, term, subjects.get(0)), "root", "");
//		instances = CFilter.removeAttributesByIndices(instances, "1");

        Instances instance = null;

        for (int i = 1; i < subjects.size(); i++) {
            instance = CFilter.retrieveDatasetFromDatabase(
                    Utils.createPredictionQuery(grade, term, subjects.get(i)), "root", "");
            instance = CFilter.removeAttributesByIndices(instance, "1");
            instances = Instances.mergeInstances(instances, instance);
        }

        return instances;

    }

    public static Instances prepareProfileMatcherData(int schoolNo, int grade, int term, List<String> subjects) throws Exception {


        Instances instances = CFilter.retrieveDatasetFromDatabase(
                Utils.createProfileMatchingQuery(schoolNo, grade, term, subjects.get(0)), "root", "");
        instances = CFilter.removeAttributesByIndices(instances, "1");

        Instances instance = null;

        for (int i = 1; i < subjects.size(); i++) {
            instance = CFilter.retrieveDatasetFromDatabase(
                    Utils.createProfileMatchingQuery(schoolNo, grade, term, subjects.get(i)), "root", "");
            instance = CFilter.removeAttributesByIndices(instance, "1,2");
            instances = Instances.mergeInstances(instances, instance);
        }

        return instances;

    }

    public static String createProfileMatchingQuery(int school_no, int grade, int term, String subject) {
        //TODO: have to remove st.idstudent <= 406 condition after creating complete database
        //st.idstudent <= 406 AND

        String query = "select st.idstudent, st.student_school_id, mk.marks as " + subject.replaceAll(" ", "_") + "_" + grade + "_" + term +
                " from " +
                "(exam ex  join subject sub on (sub.idsubject=ex.subject_idsubject)) " +
                "join marks mk on (mk.exam_idexam=ex.idexam) " +
                "join student_performance stpe on (mk.student_performance_idstudent_performance=stpe.idstudent_performance) " +
                "join student st on (st.idstudent=stpe.student_idstudent) " +
                "where st.idstudent <= 406 AND ex.school_no = " + school_no + " and ex.grade=" + grade + " and ex.term=" + term + " and sub.subject_name='" + subject + "' ";
        return query;
    }

}
