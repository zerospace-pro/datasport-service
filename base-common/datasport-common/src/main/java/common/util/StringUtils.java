package common.util;

/**
 * <p>
 *
 * </p>
 *
 * @author nathan
 * @version 2019-07-04
 * @see
 * @since
 */
public class StringUtils {

    public static boolean isEmpty(CharSequence value) {
        return value == null || value.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence value) {
        return !isEmpty(value);
    }
}
