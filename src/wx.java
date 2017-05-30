import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by xpj on 2016/11/3.
 */
public class wx {

    private static final String oauth2Url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=";
    private static final String appid = "wxf50cdfdc46b1a927";//第三方用户唯一凭证

    public static void main(String[] args) {
        // System.out.println(oauthUrl("wechat.yinyuetai.com"));
        try {
            System.out.println(oauthUrl("http://wechat.yinyuetai.com/authorization/SHOP"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static String oauthUrl(String redirect_uri) throws UnsupportedEncodingException {
        StringBuffer oathUrl = new StringBuffer();
        String encodeUrl = "";
        if (redirect_uri != null) {
            try {
                encodeUrl = URLEncoder.encode(redirect_uri, "utf-8");
            } catch (UnsupportedEncodingException e) {
            }
        }
        oathUrl.append(oauth2Url).append(appid)
                .append("&redirect_uri=").append(encodeUrl).append("&response_type=").append("code").append("&scope=")
                .append("snsapi_userinfo").append("&state=").append("http://shop.m.yinyuetai.com/bind?redirectUrl=/app/index.html")
                .append("#wechat_redirect");

        System.out.println("-------->" + oathUrl.toString() + "");
        return oathUrl.toString();
    }
}
