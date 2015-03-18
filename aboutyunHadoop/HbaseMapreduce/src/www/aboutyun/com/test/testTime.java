
package www.aboutyun.com.test;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class testTime {

        public static void StringResolves(String line) throws ParseException {
                String ipField, dateField, urlField, browserField;

                // 获取ip地址
                ipField = line.split("- -")[0].trim();

                // 获取时间,并转换格式
                int getTimeFirst = line.indexOf("[");
                int getTimeLast = line.indexOf("]");
                String time = line.substring(getTimeFirst + 1, getTimeLast).trim();
                Date dt = null;
                DateFormat df1 = DateFormat.getDateTimeInstance(DateFormat.LONG,
                                DateFormat.LONG);
                dt = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z", Locale.US)
                                .parse(time);
                dateField = df1.format(dt);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHMM");
                System.out.println(sdf.format(dt));  
                // 获取url
                String[] getUrl = line.split("\"");

                String firtGeturl = getUrl[1].substring(3).trim();

                String secondGeturl = getUrl[3].trim();
                urlField = firtGeturl + "分隔符" + secondGeturl;

                // 获取浏览器
                String[] getBrowse = line.split("\"");
                String strBrowse = getBrowse[5].toString();
                String str = "(KHTML, like Gecko)";
                int i = strBrowse.indexOf(str);
                strBrowse = strBrowse.substring(i);
                String strBrowse1[] = strBrowse.split("\\/");
                strBrowse = strBrowse1[0].toString();
                String strBrowse2[] = strBrowse.split("\\)");
                strBrowse = strBrowse2[1].trim();

                System.out.println(ipField);
                System.out.println(dateField);
                System.out.println(urlField);
                System.out.println(strBrowse);

        }

        public static void main(String[] args) throws ParseException {
                // TODO Auto-generated method stub
                String browser = "203.100.80.88 - - [01/Aug/2014:19:04:58 +0800] \"GET /uc_server/avatar.php?uid=3841&size=small HTTP/1.1\" 301 463 \"http://www.aboutyun.com/forum.php\" \"Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36 SE 2.X MetaSr 1.0";

                testTime.StringResolves(browser );

        }

}
