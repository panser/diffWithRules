package ua.org.gostroy.diffWithRules;

import ua.org.gostroy.diffWithRules.model.CompareFile;
import ua.org.gostroy.diffWithRules.rules.*;
import ua.org.gostroy.diffWithRules.service.CompareFileService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Panov Sergey on 1/16/2015.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        CompareFile compareFile1 = new CompareFile();
        CompareFile compareFile2 = new CompareFile();
        compareFile1.deserialize(new File("src/main/resources/META-INF/static/test1.txt"));
        compareFile2.deserialize(new File("src/main/resources/META-INF/static/test2.txt"));

//        for(Line line : compareFile2.getLines()){
//            System.out.println(line);
//        }
//        System.exit(0);

        CompareFileService compareFileService = new CompareFileService();

        List<CallBackRule> callBackRules = new ArrayList<>();
        callBackRules.add(new ExactMatch());
        callBackRules.add(new NumberMatch());
        callBackRules.add(new ArrayMatch());
        callBackRules.add(new TwoDimArrayMatch());
        callBackRules.add(new IgnoreCaseMatch());

        String diff = compareFileService.compareLineByLine(compareFile1, compareFile2, callBackRules);
        System.out.println(diff);

    }
}
