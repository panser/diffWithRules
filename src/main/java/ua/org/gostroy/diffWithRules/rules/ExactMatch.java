package ua.org.gostroy.diffWithRules.rules;

/**
 * Created by Panov Sergey on 1/16/2015.
 */
public class ExactMatch implements CallBackRule {

    public Boolean compareRule(String str1, String str2) {
        return str1.equals(str2);
    }
}
