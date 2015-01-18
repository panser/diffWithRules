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
public class XmlNodeLine implements Line {
    private String nodeName;
    private Map<String, String> attributes;
    private String textContent;

    public XmlNodeLine(String nodeName, Map<String, String> attributes, String textContent) {
        this.nodeName = nodeName;
        this.attributes = attributes;
        this.textContent = textContent;
    }

    public String getNodeName() {
        return nodeName;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public String getTextContent() {
        return textContent;
    }

    @Override
    public String toString() {
        return "XmlNode{" +
                "nodeName='" + nodeName + '\'' +
                ", attributes=" + attributes +
                ", textContent='" + textContent + '\'' +
                '}';
    }

    public CompareResult compareWithRules(Line line, List<CallBackRule> callBackRules) {
        CompareResult compareResult = new CompareResult();
        StringBuilder stb = new StringBuilder();

        if (this == line) return compareResult;
        if (!(line instanceof XmlNodeLine)) {
            stb.append("Lines have different type.Can't compare them").append('\n');
            compareResult.setMessage(stb.toString());
            return compareResult;
        }

        XmlNodeLine that = (XmlNodeLine) line;

        if (!nodeName.equals(that.getNodeName())) {
            stb.append("XmlNode: different nodename: " + nodeName).append('\n');
            compareResult.setMessage(stb.toString());
            return compareResult;
        }
        if (!textContent.equals(that.getTextContent())) {
            stb.append("XmlNode: different textContent: " + textContent).append('\n');
        }

        Map<String, String> uniqueMap = new HashMap<>();
        Map<String, Map<String, String>> diffMap = new HashMap<>();
        Map<String, String> diffValue;
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            if (!that.getAttributes().containsKey(entry.getKey())) {
                uniqueMap.put(entry.getKey(), entry.getValue());
            }
        }
        if (!uniqueMap.isEmpty()) {
            stb.append("XmlNode: attributes only in first file: " + uniqueMap).append('\n');
            uniqueMap.clear();
        }

        for (Map.Entry<String, String> entry : that.getAttributes().entrySet()) {
            if (!attributes.containsKey(entry.getKey())) {
                uniqueMap.put(entry.getKey(), entry.getValue());
            } else {
                String line1Value = attributes.get(entry.getKey());
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
            stb.append("XmlNode: attributes only in second file: " + uniqueMap).append('\n');
            uniqueMap.clear();
        }
        if (!diffMap.isEmpty()) {
            stb.append("XmlNode: attributes have different values: " + diffMap).append('\n');
            diffMap.clear();
        }


        compareResult.setMessage(stb.toString());
        return compareResult;
    }

}
