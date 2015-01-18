package ua.org.gostroy.diffWithRules.service;

import ua.org.gostroy.diffWithRules.model.CompareFile;
import ua.org.gostroy.diffWithRules.model.CompareResult;
import ua.org.gostroy.diffWithRules.model.Line;
import ua.org.gostroy.diffWithRules.rules.CallBackRule;

import java.util.List;

/**
 * Created by Panov Sergey on 1/18/2015.
 */
public class CompareFileService {

    public String compareLineByLine(CompareFile file1, CompareFile file2, List<CallBackRule> callBackRules) {

        StringBuilder stb = new StringBuilder();
        StringBuilder stb2 = new StringBuilder();
        Integer lineCount = Math.max(file1.getLines().size(), file2.getLines().size());

        for (int i = 0; i < lineCount; i++) {
            CompareResult compareResult = null;
            Line line1 = null;
            Line line2 = null;

            try {
                line1 = file1.getLines().get(i);
            } catch (IndexOutOfBoundsException e) {
                stb2.append("The line consist only in second file").append('\n');
                continue;
            }

            try {
                line2 = file2.getLines().get(i);
            } catch (IndexOutOfBoundsException e) {
                stb2.append("The line consist only in first file").append('\n');
                continue;
            }

            compareResult = line1.compareWithRules(line2, callBackRules);
            stb2.append(compareResult.getMessage());

            if(!stb2.toString().isEmpty()){
                stb.append("Line " + (i + 1) + ":").append('\n');
                stb.append(stb2).append('\n');
                stb2.setLength(0);
            }
        }

        return stb.toString();
    }
}
