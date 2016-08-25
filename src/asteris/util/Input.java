package asteris.util;

import java.text.NumberFormat;
import java.util.Locale;

public class Input {

    private static String reason;
    private static int errorCount;
    
    private static NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    public Input() {
        Input.reason = "";
        Input.errorCount = 0;
    }

    public static String getReason() {
        return Input.reason;
    }

    public static int getErrorCount() {
        return Input.errorCount;
    }

    public static boolean Validate(Object... values) {

        boolean result = true;
        Input.reason = "";
        Input.errorCount = 0;

        for (Object value : values) {
            if (value == null) {
                Input.reason += value.getClass().getSimpleName() + " not defined.\n";
                result = false;
                Input.errorCount++;
            } else if (value instanceof String) {
                if (value.equals("")) {
                    Input.reason += value.getClass().getSimpleName() + " is empty.\n";
                    result = false;
                    Input.errorCount++;
                }
            } else if (value instanceof Integer) {
                if ((int) value < 0) {
                    Input.reason += value.getClass().getSimpleName() + " must equal to zero or positive.\n";
                    result = false;
                    Input.errorCount++;
                }
            } else if (value instanceof Double) {
                if ((double) value < 0) {
                    Input.reason += value.getClass().getSimpleName() + " must equal to zero or positive.\n";
                    result = false;
                    Input.errorCount++;
                }
            }
        }

        return result;
    }

    public static void Logger(Object... values) {
        for (Object value : values) {
            System.out.println(value.toString());
        }
    }
    
    public static String indonesianCurrency(int data){
        return formatter.format(data);
    }

}
