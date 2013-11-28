package com.arima.classengine.profilematcher;

import com.arima.classengine.filter.CFilter;
import com.arima.classengine.utils.Utils;
import weka.core.DistanceFunction;
import weka.core.EuclideanDistance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.neighboursearch.BallTree;

import java.util.ArrayList;
import java.util.List;


public class NearestProfileBallTree {

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
        marks.add(80);
        marks.add(65);
        marks.add(50);
        marks.add(65);
        marks.add(78);
        marks.add(71);
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
        BallTree tree = new BallTree();
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
            System.out.println(tree.getPerformanceStats().getTotalPointsVisited());
        } catch (Exception e) {
            e.printStackTrace();
        }
//		System.out.println(tree.getPerformanceStats().getTotalPointsVisited());

        // Now we can also easily compute the distances as the KDTree does it
        DistanceFunction df = tree.getDistanceFunction();

        ArrayList<Integer> profiles = new ArrayList<Integer>();
        for (int i = 0; i < neighbors.numInstances(); i++) {
            System.out.println(neighbors.instance(i));
            System.out.println("The distance between" + neighbors.instance(i) + " and " + p + " is " + df.distance(neighbors.instance(i), p));
            profiles.add(Integer.valueOf(neighbors.instance(i).toString(0)));
        }




        return profiles;
    }

}
