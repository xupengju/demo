package spark;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Milo on 2017/8/9.
 */
public class TestPattern {
   private static final Pattern pattern = Pattern
            .compile("(http://|ftp://|https://|www){0,1}[^\u4e00-\u9fa5\\s]*?\\.(com|net|cn|me|tw|fr)[^\u4e00-\u9fa5\\s]*");

    public static void main(String[] args) {
        String comment = "你们 91flba.com 91flba.com 别 www.baidu.com www.xxxxxbbs.com";
        Matcher matcher =pattern.matcher(comment);
        List<String> replaceList = Lists.newArrayList();
        while (matcher.find()){
            boolean result = ReplaceURLInCommentUtil.doWhile(matcher.group(0));
            System.out.println(result);
            if(result){
                replaceList.add(matcher.group(0));
            }
        }
        System.out.println(replaceList.size());
        System.out.println(replaceList);
        for (String str : replaceList){
            comment = comment.replace(str,"");
        }
        System.out.println(comment);

    }
}
