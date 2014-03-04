package exam.query;

/**
 * Created by gevorg on 3/3/14.
 */
public enum Measure {
    COUNT,
    TOTALCOST;

    public static Measure validate(String key) throws IllegalArgumentException {
        try {
            // Checking if modified key is in enumeration.
            return valueOf(key.replaceAll(" ", "").toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid measure: " + key);
        }
    }
}
