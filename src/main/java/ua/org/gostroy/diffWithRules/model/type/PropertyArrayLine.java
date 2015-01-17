package ua.org.gostroy.diffWithRules.model.type;

import ua.org.gostroy.diffWithRules.model.Line;

import java.util.HashMap;
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
    public Map getCompareMap() {
        Map map = new HashMap<>();
        map.putAll(values);

        return map;
    }

    @Override
    public String toString() {
        return "PropertyArray{" +
                "values=" + values +
                '}';
    }
}
