package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * Created by Milo on 2017/2/21.
 */
public class IntLongDoubleTest {
    public static void main(String[] args) {
//        long a = (int)3.9;
//        System.out.println(a);

//        Integer i = 1;
//        Integer s = 1;
//        Integer x = new Integer(1);
//        int y =1;
//        System.out.println(i == y);
//        System.out.println(s == i);
//        System.out.println(i == 1);
//        System.out.println(i == x);
//        System.out.println(i.equals(y));
//        System.out.println(i.equals(s));
//
//        System.out.println(i == SourceTypeEnum.TOPIC.getIndex());
//        System.out.println(i.equals(SourceTypeEnum.TOPIC.getIndex()));

//
        List list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        List list2 = new ArrayList();
        list2.add("0");
        list2.add("1");
        //list2.add('d');
        //list.removeAll(list2);
        list2.removeAll(list);
        System.out.println(list.toString());
        System.out.println(list2.toString());

//        String countStr = "[ { 'resou" +
//                "" +
//                "rce_id': 2222,'resource_type': 3},{'resource_id': 111113,'resource_type': 2},{'resource_id': 222234,'resource_type': 1}]";
//        JSONArray jsonArray = JSON.parseArray(countStr);
//        Iterator<Object> it = jsonArray.iterator();
//        List<Map<String, Integer>> list = new ArrayList<>();
//        List<Map<String, Integer>> list2 = new ArrayList<>();
//        Map<String, Integer> cc = new HashMap<>();
//        cc.put("resource_id",2222);
//        cc.put("resource_type",3);
//        list2.add(cc);
//        while (it.hasNext()) {
//            JSONObject ob = (JSONObject) it.next();
//            int resourceId = (int) ob.get("resource_id");
//            System.out.println(resourceId);
//            int resourceType = (int)ob.get("resource_type");
//            System.out.println(resourceType);
//            Map<String, Integer> map = new HashMap<>();
//            map.put("resourceId",resourceId);
//            map.put("resourceType",resourceType);
//            list.add(map);
//        }
//
//
//        System.out.println(list.size());
//        list.removeAll(list2);
//        System.out.println(list.size());
//        System.out.println(list.toString());
//

    }
}
