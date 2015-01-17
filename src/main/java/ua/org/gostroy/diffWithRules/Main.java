package ua.org.gostroy.diffWithRules;

import ua.org.gostroy.diffWithRules.model.CompareFile;
import ua.org.gostroy.diffWithRules.model.Line;
import ua.org.gostroy.diffWithRules.rules.*;
import ua.org.gostroy.diffWithRules.service.CompareLineByLine;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Panov Sergey on 1/16/2015.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        CompareFile compareFile1 = new CompareFile();
        CompareFile compareFile2 = new CompareFile();
        compareFile1.deserialize(new File("src/main/resources/META-INF/static/test1.txt"));
        compareFile2.deserialize(new File("src/main/resources/META-INF/static/test2.txt"));

//        for(Line line : compareFile1.getLines()){
//            System.out.println(line);
//        }
//        System.exit(0);

        Class[] classes = {ExactMatch.class, ArrayMatch.class, TwoDimArrayMatch.class, NumberMatch.class, IgnoreCaseMatch.class};
//        Class[] classes = {ExactMatch.class, ArrayMatch.class, TwoDimArrayMatch.class, NumberMatch.class};
//        Class[] classes = {ExactMatch.class, ArrayMatch.class, TwoDimArrayMatch.class};
//        Class[] classes = {ExactMatch.class, ArrayMatch.class};
//        Class[] classes = {ArrayMatch.class};
        CompareLineByLine compareLineByLine = new CompareLineByLine(Arrays.asList(classes));

        Boolean exception = false;
        Integer lineCount = Math.max(compareFile1.getLines().size(), compareFile2.getLines().size());
        for (int i = 0; i < lineCount; i++) {
            try {
                compareLineByLine.equal(compareFile1.getLines().get(i), compareFile2.getLines().get(i));
            } catch (IndexOutOfBoundsException e) {
                exception = true;
            } finally {
                System.out.println("Line " + (i + 1) + ":");
            }
            if (compareLineByLine.getDiffType() || exception) {
                System.out.println("The lines have different types: XML, property array. Or lines isn't correct XML, property array line.");
            } else {
                if (compareLineByLine.getLine1UniqueMap().size() != 0) {
                    System.out.println("Only in first file : " + compareLineByLine.getLine1UniqueMap());
                }
                if (compareLineByLine.getLine2UniqueMap().size() != 0) {
                    System.out.println("Only in second file: " + compareLineByLine.getLine2UniqueMap());
                }
                if (compareLineByLine.getLineDiffMap().size() != 0) {
                    System.out.println("Different values   : " + compareLineByLine.getLineDiffMap());
                }
            }
        }

    }
}
