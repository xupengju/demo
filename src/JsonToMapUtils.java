

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/5/13.
 */
public class JsonToMapUtils {

    public static List<Map<String, Object>> parseJSON2List(String jsonStr) {
        JSONArray jsonArr = JSONArray.parseArray(jsonStr);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Object o : jsonArr) {
            JSONObject json2 = JSONObject.parseObject(o.toString());
            list.add(parseJSON2Map(json2.toString()));
        }
        return list;
    }


    public static Map<String, Object> parseJSON2Map(String jsonStr) {
        Map<String, Object> map = new HashMap<String, Object>();
        //最外层解析
        JSONObject json = JSONObject.parseObject(jsonStr);
        for (Object k : json.keySet()) {
            Object v = json.get(k);
            //如果内层还是数组的话，继续解析
            if (v instanceof JSONArray) {
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                for (Object o : JSONArray.parseArray(v.toString())) {
                    JSONObject json2 = JSONObject.parseObject(o.toString());
                    list.add(parseJSON2Map(json2.toString()));
                }
                map.put(k.toString(), list);
            } else {
                map.put(k.toString(), v);
            }

        }
        return map;
    }


    //test
    public static void main(String[] args) {
//        String url = "http://...";
//        List<Map<String, Object>> list = getListByUrl(url);
//        System.out.println(list);
//        String parm="{\"id\":\"555561617823de4068acbb92\",\"pipeline\":\"0.default\",\"code\":0,\"desc\":\"The fop was completed successfully\",\"reqid\":\"mH4AAP7Rk28jRt4T\",\"inputBucket\":\"test\",\"inputKey\":\"lgH-y-zEeFSHYbYi7x5gazJoWB1I\",\"items\":[{\"cmd\":\"avthumb/flv/ab/64k/ar/44100/vb/500k/s/672x378\",\"code\":0,\"desc\":\"The fop was completed successfully\",\"hash\":\"ltOdbQMXowevf5Ed8dq5pYtsXiVX\",\"key\":\"dNT2aBnH9gtY4imMvNXwA-yLxX0=/lgH-y-zEeFSHYbYi7x5gazJoWB1I\"},{\"cmd\":\"avthumb/m3u8/ab/64k/ar/44100/vb/500k/s/672x378\",\"code\":0,\"desc\":\"The fop was completed successfully\",\"hash\":\"Fi2dfvAlQyUzjKZ-HNEhbV0bM0_r\",\"key\":\"CIvP_-zX3MEdQ3jHFeWchHU1Rw4=/lgH-y-zEeFSHYbYi7x5gazJoWB1I\"},{\"cmd\":\"avthumb/mp4/ab/64k/ar/44100/vb/500k/s/672x378\",\"code\":0,\"desc\":\"The fop was completed successfully\",\"hash\":\"lq4qm0aFflL0WXuaA_F9cG0RtRSc\",\"key\":\"tbolnWfEg73NP11Rmz2augB16IA=/lgH-y-zEeFSHYbYi7x5gazJoWB1I\"},{\"cmd\":\"avthumb/mp4/ab/64k/ar/44100/vb/1.25m/s/1104x622\",\"code\":0,\"desc\":\"The fop was completed successfully\",\"hash\":\"lldXrs71ICzk1jCvsYPGDocY7HpI\",\"key\":\"9J4yWTzmlUQ_jXln7mkWckfJt3A=/lgH-y-zEeFSHYbYi7x5gazJoWB1I\"},{\"cmd\":\"vframe/jpg/offset/1\",\"code\":0,\"desc\":\"The fop was completed successfully\",\"hash\":\"Fk3TkWPi2hztby5XA5YVd3btVhui\",\"key\":\"nUyP59nq1hJe-8iSZV9huoGHk8Y=/lgH-y-zEeFSHYbYi7x5gazJoWB1I\"}]}";
        String parm="{\"connectprov\" : \"qq\", \"connectuid\" : \"E13BAF5DB32165C3D26603C17FB37570\",  \"devicemark\" : \"\", \"usertoken\" : \"\"}";
        Map<String, Object> map=parseJSON2Map(parm);
        for(Map.Entry<String, Object> entry:map.entrySet()){
            System.out.println(entry.getKey()+"--->"+entry.getValue());
        }
        System.out.println(map);

    }
}
