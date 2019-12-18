package base;
import java.text.NumberFormat;
import java.util.Locale;

public class Base {
	public static String getPriceFormat(long value) {
		return NumberFormat.getCurrencyInstance(new Locale("vn", "VN")).format(value);
	}
}
