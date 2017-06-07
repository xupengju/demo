package spark;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Milo on 2017/6/6.
 */
public class SubAtContentUtils {
    public static void main(String[] args) {
        String content1 = "sssss";
        String content = "甘_SPARK:[5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101][5X:0101]//@我的测试:[5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157][5X:0157] [http://15feng.cn/p/FkuBj7Fjpe-yy2HHuodviD7sAagk]//@我的测试:[5X:0152][5X:0152][5X:0152][5X:0152][5X:0152][5X:0152][5X:0152][5X:0152][5X:0152][5X:0152][5X:0152][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153][5X:0153]//@我的测试:[5X:0101][5X:0102][5X:0103][5X:0104][5X:0105][5X:0106][5X:0107][5X:0108][5X:0109][5X:0110][5X:0111][5X:0112][5X:0113][5X:0114][5X:0115][5X:0116][5X:0117][5X:0118][5X:0119][5X:0120][5X:0121][5X:0122][5X:0123]@甘_SPARK #老司机出品#[5X:0141]\uD83D\uDE00\uD83D\uDC4D\uD83C\uDFFB\uD83D\uDE97\uD83D\uDCAF✡️\uD83C\uDF60\uD83C\uDF49\uD83E\uDD81";
        if(SubContentUtils.strLenthLikeWeiBo(content1+"//@"+content,true) > 140){

            if (content.contains("//@")) {
                List<String> subStrs = Arrays.asList(content.split("//@")).stream().map(s -> s.trim()).collect(Collectors.toList());
                for (String str : subStrs) {
                    System.out.println(str);
                    if (SubContentUtils.strLenthLikeWeiBo(content1+"//@"+str, true) > 140) {
                        break;
                    }else{
                        content1 += "//@" + str;
                    }
                    System.out.println(SubContentUtils.strLenthLikeWeiBo(content1,true));
                }
            }
        }
        System.out.println(content1);
        System.out.println(SubContentUtils.strLenthLikeWeiBo(content1,true));
    }

}
