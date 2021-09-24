package py.una.pol.ejb.utils;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateHelper {
    private static final String ISO_FORMAT_DATE = "yyyy-MM-dd";
    private static final String INPUT_FORMAT = "dd/MM/yyyy";

    public static String getDateISO8601(Date fecha){
        SimpleDateFormat sDFormat = new SimpleDateFormat(ISO_FORMAT_DATE);
        return sDFormat.format(fecha);     
    }

    public static Date stringToDate(String fecha){
        SimpleDateFormat sDFormat = new SimpleDateFormat(INPUT_FORMAT);
        try {
            return sDFormat.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;   
        }   
        
    }
    
}
