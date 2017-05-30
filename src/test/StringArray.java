package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Milo on 2017/5/24.
 */
public class StringArray {
    public static void main(String[] args) {
        String s = "[22849, 228649]";
        List<String> strings = Arrays.asList(s);
        System.out.println(strings);
        char a[]=s.toCharArray();
        System.out.println(a);
        System.out.println(a.length);
        JSONArray jsonArray = JSON.parseArray(s);
        System.out.println(jsonArray);
        System.out.println(jsonArray.get(0));
        System.out.println(jsonArray.size());
    }
}
