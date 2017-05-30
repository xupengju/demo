import java.util.*;

public class SecurityUtil {

    public static String authentication(Map<String , Object > srcData) {

        if(null == srcData){
            //throw new PharosException("");
        }
        List<Map.Entry<String,Object>> list = new ArrayList<Map.Entry<String,Object>>(srcData.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Object>>(){

            public int compare(Map.Entry<String,Object> o1, Map.Entry<String,Object> o2){
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        StringBuffer srcSb = new StringBuffer();
        for(Map.Entry<String , Object>srcAtom : list){
            srcSb.append(String.valueOf(srcAtom.getValue()));
        }

        String token = MD5Util.md5(srcSb.toString());
//		System.out.println(cToken);//for test
        return token;
    }

    public static void main(String[] args) {
        Map<String , Object > srcData  = new HashMap<>();
        srcData.put("userName","username");
        srcData.put("userId",123123);
        srcData.put("salt",UUID.randomUUID().toString().substring(0,5));
        System.out.println(authentication(srcData));

    }


}