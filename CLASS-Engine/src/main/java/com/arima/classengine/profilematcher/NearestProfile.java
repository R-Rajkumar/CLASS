package com.arima.classengine.profilematcher;

import com.arima.classengine.filter.CFilter;
import com.arima.classengine.utils.Utils;
import weka.core.DistanceFunction;
import weka.core.EuclideanDistance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.neighboursearch.KDTree;

import java.util.ArrayList;
import java.util.List;


public class NearestProfile {

    public static void main(String[] args) throws Exception {

        List<String> subjects = new ArrayList<String>();
        subjects.add("SAIVISM");
        subjects.add("MATHEMATICS");
        subjects.add("SCIENCE AND TECHNOLOGY");
        subjects.add("TAMIL LANGUAGE");
        subjects.add("ENGLISH LANGUAGE");
        subjects.add("HISTORY");
//		subjects.add("INFORMATION AND COMMUNICATION TECHNOLOGY");
//		subjects.add("BUSSINESS AND ACCOUNTING");

        ArrayList<Integer> marks = new ArrayList<Integer>();
//		marks.add(40);
//		marks.add(30);
        marks.add(20);
        marks.add(25);
        marks.add(35);
        marks.add(45);
        marks.add(40);
        marks.add(26);
//		
//		System.out.println(Utils.prepareProfileMatcherData(11086, 11, 3, subjects));
//		System.exit(0);

        ArrayList<Integer> indexNumbers = getNearestProfiles(11086, 11, 3, subjects, marks);
        System.out.println(indexNumbers);
    }

    public static ArrayList<Integer> getNearestProfiles(int schoolNo, int grade, int term, List<String> subjects, List<Integer> marks) {
        Instances inst = null;
        try {
            inst = Utils.prepareProfileMatcherData(schoolNo, grade, term, subjects);
            return getProfiles(inst, marks);
        } catch (Exception e) {
            return null;
        }

    }

    public static ArrayList<Integer> getNearestProfiles(int grade, int term, List<String> subjects, List<Integer> marks) throws Exception {
        Instances inst = Utils.prepareProfileMatcherData(grade, term, subjects);
        return getProfiles(inst, marks);
    }


    public static ArrayList<Integer> getProfiles(Instances inst, List<Integer> marks) throws Exception {

//		Instances inst = Utils.prepareProfileMatcherData(schoolNo, grade, term, subjects);

//		ReplaceMissingValues rmv = new ReplaceMissingValues();
//		rmv.setInputFormat(inst);
//		inst = Filter.useFilter(inst, rmv);

        for (int i = 0; i < inst.numAttributes(); i++) {
            inst.deleteWithMissing(i);
        }

        KDTree tree = new KDTree();
        tree.setMeasurePerformance(true);

        try {
            tree.setInstances(inst);

            EuclideanDistance df = new EuclideanDistance(inst);
            df.setDontNormalize(true);
            df.setAttributeIndices("2-last");

            tree.setDistanceFunction(df);

        } catch (Exception e) {
            e.printStackTrace();
        }


        Instances neighbors = null;

        Instances test = CFilter.createInstance(112121, (ArrayList<Integer>) marks);

        Instance p = test.firstInstance();

        try {
            neighbors = tree.kNearestNeighbours(p, 50);
        } catch (Exception e) {
            e.printStackTrace();
        }
//		System.out.println(tree.getPerformanceStats().getTotalPointsVisited());


//		System.out.println(nn1 + " is the nearest neigbor for " + p);
//		System.out.println(nn2 + " is the second nearest neigbor for " + p);

        ArrayList<Integer> profiles = new ArrayList<Integer>();
        for (int i = 0; i < neighbors.numInstances(); i++) {
            System.out.println(neighbors.instance(i));
            profiles.add(Integer.valueOf(neighbors.instance(i).toString(0)));
        }


        // Now we can also easily compute the distances as the KDTree does it

        DistanceFunction df = tree.getDistanceFunction();
//		System.out.println("The distance between" + nn1 + " and " + p + " is " + df.distance(nn1, p));
//		System.out.println("The distance between" + nn2 + " and " + p + " is " + df.distance(nn2, p));
        return profiles;
    }

}
