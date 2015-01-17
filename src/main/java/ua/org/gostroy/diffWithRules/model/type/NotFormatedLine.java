package ua.org.gostroy.diffWithRules.model.type;

import ua.org.gostroy.diffWithRules.model.Line;

import java.util.HashMap;
import java.util.Map;

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
    public Map getCompareMap() {
        Map map = new HashMap<>();
        map.put(hashString + line, line);

        return map;
    }

    @Override
    public String toString() {
        return "NotFormatedLine{" +
                "line='" + line + '\'' +
                '}';
    }
}
