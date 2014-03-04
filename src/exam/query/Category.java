package exam.query;

/**
 * Created by gevorg on 3/3/14.
 */
public enum Category {
    CLIENT,
    COUNTRY,
    PRODUCT,
    PRODUCTTYPE,
    YEAR;

    public static Category validate(String key) throws IllegalArgumentException {
        try {
            // Checking if modified key is in enumeration.
            return valueOf(key.replaceAll(" ", "").toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid category: " + key);
        }
    }
}
