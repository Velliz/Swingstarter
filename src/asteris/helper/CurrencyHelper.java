package asteris.helper;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyHelper {
    
    private static final Locale locale = new Locale("id", "ID");
    private static final NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
    
    public static String FormatRupiah(Object number) {
        return formatter.format(number);
    }
    
}
