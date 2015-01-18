package ua.org.gostroy.diffWithRules.service;

import ua.org.gostroy.diffWithRules.model.CompareFile;
import ua.org.gostroy.diffWithRules.model.CompareResult;
import ua.org.gostroy.diffWithRules.rules.CallBackRule;

import java.util.List;

/**
 * Created by Panov Sergey on 1/18/2015.
 */
public class CompareFileService {

    public String compareLineByLine(CompareFile file1, CompareFile file2, List<CallBackRule> callBackRules) {

        StringBuilder stb = new StringBuilder();
        Boolean exception = false;
        Integer lineCount = Math.max(file1.getLines().size(), file2.getLines().size());

        for (int i = 0; i < lineCount; i++) {
            CompareResult compareResult = null;
            try {
                compareResult = file1.getLines().get(i).compareWithRules(file2.getLines().get(i), callBackRules);
            } catch (IndexOutOfBoundsException e) {
                exception = true;
            } finally {
                stb.append("Line " + (i + 1) + ":").append('\n');
            }
            if (exception) {
                stb.append("The line consist only in one file").append('\n');
            } else {
                stb.append(compareResult.getMessage()).append('\n');
            }
        }

        return stb.toString();
    }
}
