package spark;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Milo on 2017/5/25.
 * 140 字截取工具类
 */
public class SubContentUtils {

    private static final Pattern EXPRESSION_NUM_PATTERN = Pattern.compile("\\[(.*?)\\]");
    private static final Pattern SUBURL_PATTERN = Pattern.compile("\\[http(.*?)\\]");
    private static final Pattern SUBSTR_PATTERN = Pattern.compile("\\[5X:\\d{4}\\]");

    private static final Pattern mPatternImage = Pattern.compile("\\[http:\\/\\/15feng\\.cn\\/p\\/[^\\]]+\\]");
    private static final Pattern mPatternVideo = Pattern.compile("\\[http:\\/\\/15feng\\.cn\\/v\\/[^\\]]+\\]");
    private static final Pattern mPatternFace = Pattern.compile("\\[5X:[a-zA-Z0-9\\u4e00-\\u9fa5]+\\]");
    private static final Pattern mPatternFaceText = Pattern.compile("\\[\\$([^x00-xff]+|[A-Za-z0-9]+)\\$\\]");//表情文本形式


    private static final String chinese = "[\u0391-\uFFE5]";

    /**
     * 根据需求 模仿微博发微博字数统计规则(详情咨询产品)
     *
     * @param str
     * @param isMatcherImageLink 是否匹配图片链接长度
     * @return
     */
    public static int strLenthLikeWeiBo(String str, boolean isMatcherImageLink) {
        int valueLength = 0;
        int chineseLength = 0;
        int englishLength = 0;

        // String chinese = "[\u0391-\uFFE5]";
        if (null != str) {
            //获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
            for (int i = 0; i < str.length(); i++) {
                //获取一个字符
                String temp = str.substring(i, i + 1);
                //判断是否为中文字符
                if (temp.matches(chinese)) {
                    //中文字符长度为2
                    chineseLength += 2;
                } else {
                    //其他字符长度为1
                    englishLength += 1;
                }
            }
        }

        valueLength = chineseLength / 2 + (englishLength + 1) / 2;
        if (isMatcherImageLink) {
            Matcher matcherImage = mPatternImage.matcher(str);
            if (matcherImage != null) {
                while (matcherImage.find()) {
                    englishLength = englishLength - matcherImage.group().length() + 40;
                }
            }
            Matcher matcherVideo = mPatternVideo.matcher(str);
            if (matcherVideo != null) {
                while (matcherVideo.find()) {
                    englishLength = englishLength - matcherVideo.group().length() + 40;
                }
            }
            Matcher matcherFace = mPatternFace.matcher(str);
            if (matcherFace != null) {
                while (matcherFace.find()) {
                    englishLength = englishLength - matcherFace.group().length() + 10;
                }
            }
            Matcher matcherFaceText = mPatternFaceText.matcher(str);
            if (matcherFaceText != null) {
                while (matcherFaceText.find()) {
                    englishLength = englishLength - 4;
                    if (null != matcherFaceText.group(1) && matcherFaceText.group(1).equals("ok")) {
                        englishLength = englishLength - matcherFaceText.group(1).length() + 10;
                    } else {
                        chineseLength = chineseLength - (matcherFaceText.group().length() - 4) * 2 + 10;
                    }
                }
            }
            valueLength = chineseLength / 2 + (englishLength + 1) / 2;
        }
        return valueLength;
    }

    /**
     * 截取方法  拼接 全文语法
     *
     * @param content
     * @return
     */
    public static String tailor(String content, int length) {
        if (strLenthLikeWeiBo(content, true) > length) {
            String result = subStr(content, length);
            return result + "... [lsj:lookatall]";
        } else {
            return content;
        }
    }

    /**
     * 截取方法  不拼接 全文语法
     *
     * @param content
     * @return
     */
    public static String tailor2(String content, int length) {
        if (strLenthLikeWeiBo(content, true) > length) {
            String result = subStr(content, length);
            return result;
        } else {
            return content;
        }
    }


    public static String subStr(String s, int length) {
        StringBuilder builder = new StringBuilder();
        double k = 0;
        for (int i = 0; i < s.length(); i++) {
            System.out.println(k);

            char c = s.charAt(i);
            // System.out.println(c);
            String m = s.substring(i, Math.min(i + 9, s.length()));
            String h = s.substring(i, Math.min(i + 49, s.length()));
            Matcher matcher = SUBSTR_PATTERN.matcher(m);
            Matcher ma = SUBURL_PATTERN.matcher(h);
            if ('[' == c && matcher.find()) {
                k = k + 5;
                if (k >= length) {
                    break;
                }
                builder.append(m);
                i = i + 8;
                continue;
            }

            if ('[' == c && ma.find()) {
                k = k + 20;
                if (k > length) {
                    break;
                }
                builder.append(h);
                i = i + 48;

                continue;
            }
            if (!matcher.find() && !ma.find()) {
                if (String.valueOf(c).matches(chinese)) {

                    k++;
                } else {
                    k = k + 0.5;
                }
                builder.append(c);
            }


        }
        return builder.toString();
    }

    /**
     * 提出视频图片 url
     */
//    public static String suburl(String content) {
//        Matcher matcher = SUBURL_PATTERN.matcher(content);
//        String url = "";
//        while (matcher.find()) {
//            url = content.replace(content, matcher.group(0));
//            content =  content.replace(url, "--------------------");
//        }
//        return content;
//    }

    /**
     * 讲表情替换 算出真实长度
     *
     * @param content
     * @param list
     * @return
     */
//    public static String repalceExpression(String content, List<String> list) {
//        if (list.size() > 0) {
//            for (Object o : list) {
//                content = content.replace(o.toString(), "     ");
//            }
//        }
//        return content;
//    }

    /**
     * 提取表情 list
     *
     * @param
     * @return
     */
//    public static List expressionNum(String content) {
//        ArrayList list2 = new ArrayList();
//        //String regex = "(?<=\\[)(\\S+)(?=\\])";
//        //String regex = "\\[.+?\\]";
//        Matcher matcher = EXPRESSION_NUM_PATTERN.matcher(content);
//        while (matcher.find()) {
//            list2.add(matcher.group(0));
//        }
//        return list2;
//    }
    public static void main(String[] args) {
        // String content = "[http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImRW][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImRW][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImRW][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImRW][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImRW][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImRW][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImRW][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImRW][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImRW][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImRW][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImRW][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImRW][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImRW][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImRW]";

        String content = "[5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0104][5X:0103][5X:0103][5X:0103][5X:0103][5X:0104][5X:0104][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103]1345[5X:0103]";
        String content2 = "ss[http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImR1][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImR1][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImR1][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImR1][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImR2][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImR1][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImR0]";
        //System.out.println(tailor(content2,140));
        //System.out.println(tailor(content2,140));
        //System.out.println(content2.charAt(97));
        //System.out.println(tailor(content).length());
        System.out.println(strLenthLikeWeiBo(content, true));
        System.out.println(content);
        System.out.println(tailor(content, 140));
        System.out.println(strLenthLikeWeiBo(tailor2(content, 140), true));

        String conent3 = "ss[http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImR1][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImR1][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImR1][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImR1][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImR2][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImR1][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImR0]... [lsj:lookatall]";


    }

}
