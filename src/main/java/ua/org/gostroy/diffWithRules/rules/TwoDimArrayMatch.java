package ua.org.gostroy.diffWithRules.rules;

import ua.org.gostroy.diffWithRules.annotation.MatchRule;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Panov Sergey on 1/17/2015.
 */
@MatchRule
public class TwoDimArrayMatch implements CallBackRule {

    public Boolean compareRule(String str1, String str2) {
        Pattern pattern = Pattern.compile("([0-9][:\\,])*[0-9]");
        Matcher matcher1 = pattern.matcher(str1);
        Matcher matcher2 = pattern.matcher(str2);
        if (matcher1.matches() && matcher2.matches()) {
            String[] arrayString1 = str1.split("[:\\,]");
            String[] arrayString2 = str2.split("[:\\,]");
            if (arrayString1.length == arrayString2.length) {
                int length = arrayString1.length;
                double[] arrayDouble1 = new double[length];
                double[] arrayDouble2 = new double[length];
                for (int i = 0; i < length; i++) {
                    arrayDouble1[i] = Double.parseDouble(arrayString1[i]);
                    arrayDouble2[i] = Double.parseDouble(arrayString2[i]);
                }
                Arrays.sort(arrayDouble1);
                Arrays.sort(arrayDouble2);
                if (Arrays.equals(arrayDouble1, arrayDouble2)) {
                    return true;
                }
            }
        }

        return false;
    }
}
