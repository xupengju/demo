package test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Milo on 2017/5/15.
 */
public class SubContentUtil {
    public static void main(String[] args) {
        String content = "提提提提一一言一行明明[5X:0110]你明s明您格子撒事撒的发生的发生发送到发送到法师法师打发沙发沙发沙发沙发沙发沙发上大所多所[5X:0111]多所多所[5X:0112]多所多所多所多所多所多所说的圣斗士多所多所多所多所说的圣斗士多所多所多兮一心一意嘻嘻嘻嘻嘻嘻嘻嘻刚一[5X:0113]直嘻嘻嘻一下[5X:0114][5X:0115][5X:0116][5X:0117]急救会一直[5X:0108][5X:0109][5X:0106][5X:0113][5X:0118][5X:0119][5X:0112][5X:0112]同 [http://15feng.cn/p/Fh9GVDEwqmfg7SwmAsM_TCI4XAaR] [http://15feng.cn/p/Fh9GVDEwqmfg7SwmAsM_TCI4XAaR]";
        String content1 = "提提提提一一言一行明明[5X:0110]你明s明您格子撒事撒的发生的发生发送到发送到法师法师打发沙发沙发沙发沙发沙发沙发上大所多所[5X:0111]多所多所[5X:0112]多所多所多所多所多所多所说的圣斗士多所多所多所多所说的圣斗士多所多所多兮一心一意嘻嘻嘻嘻嘻[5X:0113]直嘻嘻嘻一下[5X:0114][5X:0115][5X:0116][5X:0117]急救会一直[5X:0108][5X:0109][5X:0106][5X:0113][5X:0118][5X:0119][5X:0112][5X:0112]同 [http://15feng.cn/p/Fh9GVDEwqmfg7SwmAsM_TCI4XAaR]";
        //        String targetStr = subContent(content);
//        if(targetStr.endsWith("{")){
//            targetStr = targetStr.substring(0,targetStr.length()-1);
//        }
//        List list  = expressionNum(content);
//        for (Object o :list ){
//            System.out.println(o.toString());
//            targetStr  = targetStr.replace("{}",o.toString());
//            System.out.println(targetStr);
//        }
//        System.out.println(targetStr);
        //String  content1 = "��������һһ��һ��������������������һ��һ��������������������һֱ������һ�¼��Ȼ�һֱͬ ";
        //System.out.println(content1.length());
        //   System.out.println(subContent(content));


//        String s="dsaf[323]ldsao,[sd]";
//        String pattern="(\\[[^\\)]+\\])";
//        ArrayList list=new ArrayList();
//        Pattern p=Pattern.compile(pattern);
//        Matcher m=p.matcher(s);
//        while(m.find()){
//            list.add(m.group());
//        }
//        System.out.println(list);
        System.out.println(content);
       // System.out.println(content.substring(0,140));
        //System.out.println(content1.substring(0,140));
        //System.out.println(content1.substring(130,140));
        // ... [lsj:lookatall]
        System.out.println(subStr2(content,140));

    }

    public static String subStr(String s, int length){
        StringBuilder builder = new StringBuilder();
        for (int i = 130, n = s.length(); i < n; i++) {
            char c = s.charAt(i);
            if('[' == c){
                // �����ж�
                String m = s.substring(i, Math.min(i + 9, n));
                Pattern pattern = Pattern.compile("[5X:\\d{4}]");
                Matcher matcher = pattern.matcher(m);
                if(matcher.find()){
                    // ȷ�������
                    if(i + 9 > length){
                        // ����
                        break;
                    }
                }else {
                    builder.append(c);
                }
            }else {
                builder.append(c);
            }
        }
        return s.substring(0,130)+builder.toString();
    }

    public static String subStr2(String s, int length){
        StringBuilder builder = new StringBuilder();
        for (int i = 0, n = s.length(); i < n; i++) {
            char c = s.charAt(i);
            if('[' == c){
                // �����ж�
                String m = s.substring(i, Math.min(i + 9, n));
                Pattern pattern = Pattern.compile("[5X:\\d{4}]");
                Matcher matcher = pattern.matcher(m);
                if(matcher.find()){
                    // ȷ�������
                    if(i + 9 > length){
                        // ����
                        break;
                    }
                }
            }

            builder.append(c);
        }
        return builder.toString();
    }
    public static String test(String s){
        StringBuilder builder =  new StringBuilder();
        for (int i = 0, n = s.length(); i < n; i++) {
            char a = s.charAt(i);
            if(!"[".equals(a)){
                builder.append(a);
                continue;
            }

            // [��ͷ
            // [5X:0113]
            if(i + 9 > 140){
                // ����
                builder.append("...");
                break;
            }else {
                builder.append(a);
            }
        }
        return builder.toString();
    }

    public static List expressionNum(String content) {
        ArrayList list2 = new ArrayList();
        //String regex = "(?<=\\[)(\\S+)(?=\\])";
        //String regex = "\\[.+?\\]";
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            list2.add(matcher.group(0));
        }
        return list2;
    }

    public static String repalceExpression(String content) {
        String withOutUrl = suburl(content);
        List list = expressionNum(withOutUrl);
        if (list.size() > 0) {
            int count = 0;
            for (Object o : list) {
                content = content.replace(o.toString(), "{"+count+"}");
                count ++;
            }
        }
        return content;
    }

    public static String suburl(String content) {
        Pattern pattern = Pattern.compile("\\[http(.*?)\\]");
        Matcher matcher = pattern.matcher(content);;
        String url = "";
        while (matcher.find()) {
            url = content.replace(content, matcher.group(0));
        }
        return content.replace(url,"");
    }
    //��ǰ�����м�������
    public static String subContent(String content) {

        String withOutUrl = suburl(content);
        String whislg = repalceExpression(withOutUrl);
        int count = expressionNum(withOutUrl).size();

        //������
        int dif = count * 9 + 5;
        //��ȷ�ĳ���
        int num = content.length() - dif;

        if (num <= 140) {
            return content;
        } else {
            System.out.println(content);
            return whislg.substring(0,140);
        }
    }


    //��ȡ 130 -135

}
