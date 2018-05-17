package hyy.com.demo.data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaofeng.apple on 17/2/24.
 */

public class PepoleStateCodeMapHelper {



    private static Map<String,String> innerMap;
    private static Map<String,String> reverseInnerMap;


    public static final String KEY_1 = "外出就业";
    public static final String KEY_2 = "返乡";
    public static final String KEY_3 = "自主创业";
    public static final String KEY_4 = "务农";
    public static final String KEY_5 = "在校学生";
    public static final String KEY_6 = "其他（保障）";


    public static final String VALUE_1 = "1";
    public static final String VALUE_2 = "2";
    public static final String VALUE_3 = "3";
    public static final String VALUE_4 = "4";
    public static final String VALUE_5 = "5";
    public static final String VALUE_6 = "6";




    private PepoleStateCodeMapHelper(){

    }

    public static Map<String,String> getInnerMap(){

        if(innerMap == null){
            innerMap = new LinkedHashMap<String,String>();
            innerMap.put(KEY_1, VALUE_1);
            innerMap.put(KEY_2, VALUE_2);
            innerMap.put(KEY_3, VALUE_3);
            innerMap.put(KEY_4, VALUE_4);
            innerMap.put(KEY_5, VALUE_5);
            innerMap.put(KEY_6, VALUE_6);

        }

        return innerMap;
    }
}
