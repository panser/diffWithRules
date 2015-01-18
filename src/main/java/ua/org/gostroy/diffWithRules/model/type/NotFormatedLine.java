package ua.org.gostroy.diffWithRules.model.type;

import ua.org.gostroy.diffWithRules.model.CompareResult;
import ua.org.gostroy.diffWithRules.model.Line;
import ua.org.gostroy.diffWithRules.rules.CallBackRule;

import java.util.List;

/**
 * Created by Panov Sergey on 1/16/2015.
 */
public class NotFormatedLine implements Line {
    private String line;

    public NotFormatedLine(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
    }

    @Override
    public String toString() {
        return "NotFormatedLine{" +
                "line='" + line + '\'' +
                '}';
    }

    @Override
    public CompareResult compareWithRules(Line line, List<CallBackRule> callBackRules) {
        CompareResult compareResult = new CompareResult();
        StringBuilder stb = new StringBuilder();

        if (this == line) return compareResult;
        if (!(line instanceof NotFormatedLine)) {
            stb.append("Lines have different type.Can't compare them").append('\n');
            compareResult.setMessage(stb.toString());
            return compareResult;
        }

        NotFormatedLine that = (NotFormatedLine) line;

        if (!line.equals(that.getLine())) {
            stb.append("NotFormatedLine: lines can't parse by known types and they're not equal").append('\n');
            compareResult.setMessage(stb.toString());
            return compareResult;
        }

        compareResult.setMessage(stb.toString());
        return compareResult;
    }
}
