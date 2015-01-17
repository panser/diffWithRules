package ua.org.gostroy.diffWithRules.rules;

import ua.org.gostroy.diffWithRules.annotation.MatchRule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Panov Sergey on 1/17/2015.
 */
@MatchRule
public class NumberMatch {

    public Boolean compareRule(String str1, String str2) {
        Pattern pattern = Pattern.compile("[0-9]*.?[0-9]*");
        Matcher matcher1 = pattern.matcher(str1);
        Matcher matcher2 = pattern.matcher(str2);
        if (matcher1.matches() && matcher2.matches()) {
            double double1 = Double.parseDouble(str1);
            double double2 = Double.parseDouble(str2);

            return double1 == double2;
        }

        return false;
    }

}
