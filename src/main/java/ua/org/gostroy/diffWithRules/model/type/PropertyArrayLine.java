package ua.org.gostroy.diffWithRules.model.type;

import ua.org.gostroy.diffWithRules.model.CompareResult;
import ua.org.gostroy.diffWithRules.model.Line;
import ua.org.gostroy.diffWithRules.rules.CallBackRule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Panov Sergey on 1/16/2015.
 */
public class PropertyArrayLine implements Line {
    private Map<String, String> values;

    public PropertyArrayLine(Map<String, String> values) {
        this.values = values;
    }

    public Map<String, String> getValues() {
        return values;
    }

    @Override
    public String toString() {
        return "PropertyArray{" +
                "values=" + values +
                '}';
    }

    @Override
    public CompareResult compareWithRules(Line line, List<CallBackRule> callBackRules) {
        CompareResult compareResult = new CompareResult();
        StringBuilder stb = new StringBuilder();

        if (this == line) return compareResult;
        if (!(line instanceof PropertyArrayLine)) {
            stb.append("Lines have different type.Can't compare them").append('\n');
            compareResult.setMessage(stb.toString());
            return compareResult;
        }

        PropertyArrayLine that = (PropertyArrayLine) line;

        Map<String, String> uniqueMap = new HashMap<>();
        Map<String, Map<String, String>> diffMap = new HashMap<>();
        Map<String, String> diffValue;
        for (Map.Entry<String, String> entry : values.entrySet()) {
            if (!that.getValues().containsKey(entry.getKey())) {
                uniqueMap.put(entry.getKey(), entry.getValue());
            }
        }
        if (!uniqueMap.isEmpty()) {
            stb.append("PropertyArray: attributes only in first file: " + uniqueMap).append('\n');
            uniqueMap.clear();
        }

        for (Map.Entry<String, String> entry : that.getValues().entrySet()) {
            if (!values.containsKey(entry.getKey())) {
                uniqueMap.put(entry.getKey(), entry.getValue());
            } else {
                String line1Value = values.get(entry.getKey());
                Boolean equal = false;
                for (CallBackRule callBackRule : callBackRules) {
                    if (callBackRule.compareRule(line1Value, entry.getValue())) {
                        equal = true;
                        break;
                    }
                }
                if (!equal) {
                    diffValue = new HashMap<>();
                    diffValue.put("file1", line1Value);
                    diffValue.put("file2", entry.getValue());
                    diffMap.put(entry.getKey(), diffValue);
                }
            }
        }
        if (!uniqueMap.isEmpty()) {
            stb.append("PropertyArray: attributes only in second file: " + uniqueMap).append('\n');
            uniqueMap.clear();
        }
        if (!diffMap.isEmpty()) {
            stb.append("PropertyArray: attributes have different values: " + diffMap).append('\n');
            diffMap.clear();
        }


        compareResult.setMessage(stb.toString());
        return compareResult;
    }
}
