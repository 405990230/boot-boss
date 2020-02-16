import com.alibaba.fastjson.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class test {
    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        System.out.println(list.toString());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String timespan = "2019-08";
        Date date = sdf.parse(timespan);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        System.out.println(sdf.format(calendar.getTime()));

        String str = "2019-06-12T00:00:00Z";
        System.out.println(str.split("T")[0]);
        System.out.println(str.substring(0, str.indexOf("T")));
    }
}
