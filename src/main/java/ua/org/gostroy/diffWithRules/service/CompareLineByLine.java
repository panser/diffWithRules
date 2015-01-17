package ua.org.gostroy.diffWithRules.service;

import ua.org.gostroy.diffWithRules.annotation.MatchRule;
import ua.org.gostroy.diffWithRules.model.Line;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Panov Sergey on 1/16/2015.
 */
public class CompareLineByLine {

    private List<Class> rulesClasses;

    private Boolean diffType;
    private Map<String, String> line1UniqueMap;
    private Map<String, String> line2UniqueMap;
    private Map<String, Map<String, String>> lineDiffMap;

    public CompareLineByLine(List<Class> rulesClasses) {
        this.rulesClasses = rulesClasses;
    }

    public Boolean equal(Line line1, Line line2) {
        diffType = false;
        line1UniqueMap = new HashMap<>();
        line2UniqueMap = new HashMap<>();
        lineDiffMap = new HashMap<>();

        if (!compateType(line1, line2)) {
            return false;
        }

        return compare(line1.getCompareMap(), line2.getCompareMap());
    }

    private Boolean compateType(Line line1, Line line2) {
        if (!(line1.getClass().equals(line2.getClass()))) {
            diffType = true;
            return false;
        }

        return true;
    }

    private Boolean compare(Map<String, String> map1, Map<String, String> map2) {

        for (Map.Entry<String, String> line1entry : map1.entrySet()) {
            if (!map2.containsKey(line1entry.getKey())) {
                String key = (line1entry.getKey().startsWith(Line.hashString)) ? line1entry.getKey().split(Line.hashString)[1] : line1entry.getKey();
                line1UniqueMap.put(key, line1entry.getValue());
            } else {
                String line2value = map2.get(line1entry.getKey());
                Map<String, String> diffValue = new HashMap<>();
                if (!compareByRules(line2value, line1entry.getValue())) {
                    diffValue.put("file1", line1entry.getValue());
                    diffValue.put("file2", line2value);
                    String key = (line1entry.getKey().startsWith(Line.hashString)) ? line1entry.getKey().split(Line.hashString)[1] : line1entry.getKey();
                    lineDiffMap.put(key, diffValue);
                }
            }
        }
        for (Map.Entry<String, String> line2entry : map2.entrySet()) {
            if (!map1.containsKey(line2entry.getKey())) {
                String key = (line2entry.getKey().startsWith(Line.hashString)) ? line2entry.getKey().split(Line.hashString)[1] : line2entry.getKey();
                line2UniqueMap.put(key, line2entry.getValue());
            }
        }

        if (line1UniqueMap.size() != 0 || line2UniqueMap.size() != 0 || lineDiffMap.size() != 0) {
            return false;
        }

        return true;
    }

    private Boolean compareByRules(String str1, String str2) {

        for (Class aClass : rulesClasses) {
            Annotation[] annotations = aClass.getAnnotations();

            try {
                for (Annotation annotation : annotations) {
                    if (annotation instanceof MatchRule) {
                        Method method = aClass.getMethod("compareRule", String.class, String.class);
                        Boolean result = (Boolean) method.invoke(aClass.newInstance(), str1, str2);
                        if (result) {
                            return true;
                        }
                    }
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {

            }

        }

        return false;
    }

    public Boolean getDiffType() {
        return diffType;
    }

    public Map<String, String> getLine1UniqueMap() {
        return line1UniqueMap;
    }

    public Map<String, String> getLine2UniqueMap() {
        return line2UniqueMap;
    }

    public Map<String, Map<String, String>> getLineDiffMap() {
        return lineDiffMap;
    }

}
