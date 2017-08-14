package spark;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Milo on 2017/5/25.
 * 140 字截取工具类
 */
public class SubAtContentUtils {

    private static final Pattern EXPRESSION_NUM_PATTERN = Pattern.compile("\\[(.*?)\\]");
    private static final Pattern SUBURL_PATTERN = Pattern.compile("\\[http(.*?)\\]");
    private static final Pattern SUBSTR_PATTERN = Pattern.compile("\\[5X:\\d{4}\\]");
    private static final Pattern SUBN_PATTERN = Pattern.compile("[.\\\\n]*");
    private static final Pattern pattern = Pattern
            .compile("(http://|ftp://|https://|www){0,1}[^\u4e00-\u9fa5\\s]*?\\.(com|net|cn|me|tw|fr)[^\u4e00-\u9fa5\\s]*");

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
                    if (null != (matcherFaceText.group(1)) && matcherFaceText.group(1).equals("ok")) {
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
        //待截取字符串长度
        int strLength = strLenthLikeWeiBo(content, true);
        //待字符串含有换行个数
        int linefeednumber = countStr(content, "\n");
        if (linefeednumber > 6 && strLength < length) {
            return lineFeed(content) + "... [lsj:lookatall]";
        }
        if (strLength > length) {
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
        // i 坐标  k 字符数量
        for (int i = 0; i < s.length(); i++) {
            // System.out.println(k);

            char c = s.charAt(i);
            String m = s.substring(i, Math.min(i + 9, s.length()));
            String h = s.substring(i, Math.min(i + 49, s.length()));

            Matcher matcher = SUBSTR_PATTERN.matcher(m);
            Matcher mi = mPatternImage.matcher(h);
            Matcher mv = mPatternVideo.matcher(h);
            if ('[' == c && matcher.find()) {
                k = k + 5;
                if (k >= length) {
                    break;
                }
                builder.append(m);
                i = i + 8;
                continue;
            }

            if ('[' == c && mi.find()) {
                k = k + 20;
                if (k > length) {
                    break;
                }
                builder.append(h);
                i = i + 48;

                continue;
            }

            if ('[' == c && mv.find()) {
                k = k + 20;
                if (k > length) {
                    break;
                }
                builder.append(h);
                i = i + 48;

                continue;
            }
            int urlend = urlEnd(s, i);
            if(urlend != -1){
                String url = s.substring(i, urlend);
                Matcher urlMatcher = pattern.matcher(url);
                if (urlMatcher.find()) {
                    k = k + urlend - i;
                    if (k > length) {
                        break;
                    }
                    builder.append(url);
                    i = urlend;
                    continue;
                }
            }


            if (!matcher.find() && !mi.find() && !mv.find()) {
                if (String.valueOf(c).matches(chinese)) {

                    k++;
                } else {
                    k = k + 0.5;
                }
                if (k > length) {
                    break;
                }
                builder.append(c);
            }


        }
        return builder.toString();
    }

    /**
     * 评论同时转发 截取工具
     */
    public static String commentAndForwardAtSameTimeTailorUtils(String original, String atContent) {

        // String content = "甘_SPARK:[5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101]//@我的测试:[5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157] [http://15feng.cn/p/FkuBj7Fjpe-yy2HHuodviD7sAagk]//@我的测试:[5X:0152][5X:0152][5X:0152][5X:0152][5X:0152][5X:0152][5X:0152][5X:0152][5X:0152][5X:0152][5X:0152][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153]//@我的测试:[5X:0101][5X:0102][5X:0103][5X:0104][5X:0105][5X:0106][5X:0107][5X:0108][5X:0109][5X:0110][5X:0111][5X:0112][5X:0113][5X:0114][5X:0115][5X:0116][5X:0117][5X:0118][5X:0119][5X:0120][5X:0121][5X:0122][5X:0123]@甘_SPARK #老司机出品#[5X:0141]\uD83D\uDE00\uD83D\uDC4D\uD83C\uDFFB\uD83D\uDE97\uD83D\uDCAF✡️\uD83C\uDF60\uD83C\uDF49\uD83E\uDD81";
        if (SubContentUtils.strLenthLikeWeiBo(original + "//@" + atContent, true) > 140) {

            if (atContent.contains("//@")) {
                //List<String> subStrs = Arrays.asList(AtContent.split("//@")).stream().map(s -> s.trim()).collect(Collectors.toList());
                String[] subStrs = atContent.split("//@");
                for (String str : subStrs) {
                    // System.out.println(str);
                    if (SubContentUtils.strLenthLikeWeiBo(original + "//@" + str, true) > 140) {
                        break;
                    } else {
                        original += "//@" + str;
                    }
                    // System.out.println(SubContentUtils.strLenthLikeWeiBo(original,true));
                }
            }
        } else {
            return original + "//@" + atContent;
        }
        //System.out.println(original);
        //System.out.println(SubContentUtils.strLenthLikeWeiBo(original,true));
        return original;
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
        String content = "[5X:0103][5X:0103][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImRW][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImRW][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImRW][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImRW][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImRW][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImRW][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImRW][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImRW][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImRW][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImRW][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImRW][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImRW][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImRW][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImRW]";
        String str1 = "94946779\n" +
                "\n" +
                "1111\n" +
                "11 11\n" +
                "1111\n" +
                "2222\n" +
                "去试试";
//        System.out.println(str1.contains("\n"));
//        System.out.println(countStr(str1, "\n"));
//        System.out.println(tailor(str1, 140));
//        System.out.println(urlEnd(str1, 7));
//        System.out.println(str1.charAt(17));
         //String content = "[5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0104][5X:0103][5X:0103][5X:0103][5X:0103][5X:0104][5X:0104][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103]1345[5X:0103]";
        String content2 = "ss[http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImR1][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImR1][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImR1][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImR1][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImR2][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImR1][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImR0]";
        String conent3 = "ss[http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImR1][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImR1][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImR1][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImR1][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImR2][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImR1][http://15feng.cn/v/lhb2xubIosWUO-fxK6_qrHpeImR0]";
        String content5 = "提议一下嘻嘻嘻[5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103][5X:0103]//@救火队员:集体嘻嘻嘻嘻吱吱吱YY[5X:0103]//@甘_SPARK:[5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101]//@我的测试:[5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157] [http://15feng.cn/p/FkuBj7Fjpe-yy2HHuodviD7sAagk]//@我的测试:[5X:0152][5X:0152][5X:0152][5X:0152][5X:0152][5X:0152][5X:0152][5X:0152][5X:0152][5X:0152][5X:0152][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153]//@我的测试:[5X:0101][5X:0102][5X:0103][5X:0104][5X:0105][5X:0106][5X:0107][5X:0108][5X:0109][5X:0110][5X:0111][5X:0112][5X:0113][5X:0114][5X:0115][5X:0116][5X:0117][5X:0118][5X:0119][5X:0120][5X:0121][5X:0122][5X:0123]@甘_SPARK #老司机出品#[5X:0141]\uD83D\uDE00\uD83D\uDC4D\uD83C\uDFFB\uD83D\uDE97\uD83D\uDCAF✡️\uD83C\uDF60\uD83C\uDF49\uD83E\uDD81";
        String test1= "http://ywfm.laosiji.com/thread/146201.html\n" +
                "http://test.xxxxxbbs.com/thread/146201.html\n" +
                "http://ywfm.laosiji.com\n" +
                "http://15feng.cn/p/FtozYtc-m1Dbg_qltFFEKE50x7MK\n" +
                "http://15feng.cn/v/FvlKgdtEDsjZ-dpJRlUkFW9yDDG0\n" +
                "http://www.yidianzixun.com/article/0H1fRSKi\n" +
                "http://www.int-spark.com/\n" +
                "http://www.autohome.com.cn/news/201708/905553.html#pvareaid=2023111\n" +
                "http://club.autohome.com.cn/bbs/thread-c-614-65340349-1.html\n" +
                "http://news.163.com/17/0809/01/CRC2VFKI0001899O.html\n" +
                "http://v.163.com/\n" +
                "http://ent.163.com/tv/\n" +
                "http://weibo.com/nextevofficial?is_all=1#_rnd1502329468302\n" +
                "http://weibo.com/hanlu235959?refer_flag=0000015012_&from=feed&loc=nickname\n" +
                "https://m.weibo.cn/status/4139156961582618?wm=3333_2001&from=1077193010&sourcetype=qq&featurecode=newtitle\n" +
                "http://www.360.cn/newslist/zxzx/sqggmhhspgqlgmthnrcqw.html\n" +
                "https://www.zhihu.com/question/63346947/answer/210827768";
        System.out.println(tailor(content,140));
        System.out.println(tailor(content2,140));
        System.out.println(tailor(conent3,140));
        System.out.println(tailor(content5,140));
        System.out.println(tailor(test1,140));
    }

    private static int counter = 0;

    public static int countStr(String str1, String str2) {
        if (str1.indexOf(str2) == -1) {
            return 0;
        } else if (str1.indexOf(str2) != -1) {
            counter++;
            countStr(str1.substring(str1.indexOf(str2) +
                    str2.length()), str2);
            return counter;
        }
        return 0;
    }

    public static String lineFeed(String content) {
        int i = 0;
        int si = -1;
        int begin = 0;
        while (i++ < 6 && (si = content.indexOf('\n', begin)) != -1) {
            begin = si + 1;
        }
        return content.substring(0, si);

    }


    public static int urlEnd(String content, int index) {
        int index1 = content.indexOf(" ", index);
        int index2 = content.indexOf("\n", index);
        if(index1 == -1){
            return index2;
        }
        if(index2 == -1){
            return index1;
        }
        if(index2 != -1 && index1 != -1){
            return Math.min(index1,index2);
        }
        return -1;
    }

}
