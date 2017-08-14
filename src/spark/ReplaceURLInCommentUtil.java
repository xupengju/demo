package spark;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Milo on 2017/8/9.
 */
public class ReplaceURLInCommentUtil {
    private static final Pattern pattern = Pattern
            .compile("(http://|ftp://|https://|www){0,1}[^\u4e00-\u9fa5\\s]*?\\.(com|net|cn|me|tw|fr)[^\u4e00-\u9fa5\\s]*");

    public static String replaceURL(String comment) {

        Matcher matcher =pattern.matcher(comment);
        List<String> replaceList = Lists.newArrayList();
        while (matcher.find()){
            boolean result = ReplaceURLInCommentUtil.doWhile(matcher.group(0));
            System.out.println(result);
            if(result){
                replaceList.add(matcher.group(0));
            }
        }
        for (String str : replaceList){
            comment = comment.replace(str,"");
        }
       return comment;

    }

    public static boolean doWhile(String comment) {
        SensitivewordFilter filter = new SensitivewordFilter();
        Set<String> set = filter.getSensitiveWord(comment, 1);
        System.out.println(set.size());
        return set.size()>0?false:true;
    }

    public static void main(String[] args) {
        String comment = "http://15feng.cn/p/FtozYtc-m1Dbg_qltFFEKE50x7MK\n" +
                "http://15feng.cn/v/FvlKgdtEDsjZ-dpJRlUkFW9yDDG0";
        String result =  replaceURL(comment);
        System.out.println(result);
    }


}
