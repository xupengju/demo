package test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Milo on 2017/5/25.
 *  140 字截取工具类
 */
public class SubContentUtils {

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        String content = "提提提提一一言一行明明[5X:0110]你明s明您格子撒事撒的发生的发生发送到发送到法师法师打发沙发沙发沙发沙发沙发沙发上大所多所[5X:0111]多所多所[5X:0112]多所多所多所多所多所多所说的圣斗士多所多所多所多所说的圣斗士多所多所多兮一心一意嘻嘻嘻嘻嘻嘻嘻嘻刚一[5X:0113]直嘻嘻嘻一下[5X:0115][5X:0116][5X:0117]急救会一直[5X:0108][5X:0109][5X:0106][5X:0113][5X:0118][5X:0119][5X:0112][5X:0112]同 [http://15feng.cn/p/Fh9GVDEwqmfg7SwmAsM_TCI4XAaR] [http://15feng.cn/p/Fh9GVDEwqmfg7SwmAsM_TCI4XAaR]";
        String content1 = "提提提提一一言一行明明阿斯顿你明s明您格子撒事撒的发生的发生送到发送到法师法师打发沙发沙是否锁定防守打法发沙发沙发沙发沙发上大所多所阿斯顿发送到多所多所阿斯顿发生多所多所多所多所多所多所说的圣斗士多所多所多所多所说的圣斗士多所多所多兮一心一意嘻嘻嘻嘻嘻阿斯顿发生直嘻嘻嘻一下[5X:0115]斯顿发阿斯顿发斯蒂芬急救会一直[5X:0108][5X:0109][5X:0106][5X:0113][5X:0118][5X:0119][5X:0112][5X:0112]同 [http://15feng.cn/p/Fh9GVDEwqmfg7SwmAsM_TCI4XAaR]";
        String content2 = "提提提提一一言一行明明阿斯顿你明s明您格子撒事撒的发生的发生送到发送到法师法师打发沙发沙是否锁定防守打法发沙发沙发沙发沙发上大所多所阿斯顿发送到多所多所阿斯顿发生多所多所多所多所多所多所说的圣斗士多所多所多所多所说的圣斗士多所多所多兮一心一意嘻嘻嘻嘻嘻阿斯顿发生直嘻嘻嘻一下斯顿发阿斯顿发斯蒂 是滴是滴芬急救会一直[[0119][5X:0112][5X:0112]同 [http://15feng.cn/p/Fh9GVDEwqmfg7SwmAsM_TCI4XAaR]";
        //System.out.println(content1);
//        System.out.println(content1.substring(0,140));
//        System.out.println(content1.substring(0, 146));
//        System.out.println(tailor(content1));
//        System.out.println(tailor(content1).length());
        System.out.println(tailor(content));
        System.out.println(tailor(content2));
        System.out.println(tailor(content2).length());
        System.out.println(System.currentTimeMillis());

    }

    public static String tailor(String content) {
        String withoutUrlContent = suburl(content);
        List<String> list = expressionNum(withoutUrlContent);
        String tailorContent = repalceExpression(withoutUrlContent, list);
        String result = "";
        //System.out.println(list.size());
        if (tailorContent.length() > 140) {
            //切
            result = subStr(withoutUrlContent, 140);
        }
        return result+"... [lsj:lookatall]";
    }

    public static String subStr(String s, int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0, n = s.length(), k = 0; i < n; i++) {
            char c = s.charAt(i);
            if ('[' == c) {
                // 区域判断
                String m = s.substring(i, Math.min(i + 9, n));
                Pattern pattern = Pattern.compile("[5X:\\d{4}]");
                Matcher matcher = pattern.matcher(m);
                if (matcher.find()) {
                    k++;
                    if (i + 9 > length && i > 140 + k * 6 - 1) {
                        builder.append(m);
                        // 超过
                        break;
                    }
                }
            }
            if (i > 140 + k * 6 - 1) {
                break;
            }

            builder.append(c);
        }
        return builder.toString();
    }

    /**
     * 提出图片 url
     */
    public static String suburl(String content) {
        Pattern pattern = Pattern.compile("\\[http(.*?)\\]");
        Matcher matcher = pattern.matcher(content);
        ;
        String url = "";
        while (matcher.find()) {
            url = content.replace(content, matcher.group(0));
        }
        return content.replace(url, "");
    }

    public static String repalceExpression(String content, List<String> list) {
        if (list.size() > 0) {
            for (Object o : list) {
                content = content.replace(o.toString(), "   ");
            }
        }
        return content;
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

}
