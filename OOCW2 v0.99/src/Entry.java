import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Entry {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 0);
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
		String formatted = format1.format(cal.getTime());
		System.out.println("Current Date:"+formatted);

		new Sys().mainMenu();
	}
}