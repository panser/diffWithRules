package ua.org.gostroy.diffWithRules.handler;

import ua.org.gostroy.diffWithRules.exception.ParseLineException;
import ua.org.gostroy.diffWithRules.model.Line;
import ua.org.gostroy.diffWithRules.model.type.PropertyArrayLine;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Panov Sergey on 1/17/2015.
 */
public class PropertyArrayHandler {

    static String propDelimeter = "&";
    static String keyValueDelimeter = "=";

    public static Line parseLine(String line) throws ParseLineException {

        Map<String, String> values = new HashMap<>();
        String[] propArray = line.split(propDelimeter);
        for (String prop : propArray) {
            String[] keyValue = prop.split(keyValueDelimeter);
            if (keyValue.length != 2) throw new ParseLineException();
            values.put(keyValue[0], keyValue[1]);
        }

        Line parseLine = new PropertyArrayLine(values);
        return parseLine;
    }

}
