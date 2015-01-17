package ua.org.gostroy.diffWithRules.model.type;

import ua.org.gostroy.diffWithRules.model.Line;

import java.util.HashMap;
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
    public Map getCompareMap() {
        Map map = new HashMap<>();
        map.putAll(attributes);
        map.put(hashString + "xmlNodeName", nodeName);
        map.put(hashString + "xmlTextContent", textContent);

        return map;
    }

    @Override
    public String toString() {
        return "XmlNode{" +
                "nodeName='" + nodeName + '\'' +
                ", attributes=" + attributes +
                ", textContent='" + textContent + '\'' +
                '}';
    }

}
