package test;

import com.alibaba.fastjson.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Created by Milo on 2017/1/4.
 */
public class TestWX {
    public static String getAccessToken() {
        String access_token = "";
        String grant_type = "client_credential";//��ȡaccess_token��дclient_credential
        String AppId = "wxf50cdfdc46b1a927";//�������û�Ψһƾ֤
        String secret = "512bdfcbc584ea4c4b187e2b2c49a126";//�������û�Ψһƾ֤��Կ����appsecret
        //���url���ӵ�ַ�Ͳ����Բ��ܱ�
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=" + grant_type + "&appid=" + AppId + "&secret=" + secret;

        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET"); // ������get��ʽ����
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// ���ӳ�ʱ30��
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // ��ȡ��ʱ30��
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");
            JSONObject demoJson = JSONObject.parseObject(message);
            System.out.println("JSON�ַ�����" + demoJson);
            access_token = demoJson.getString("access_token");
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return access_token;
    }

    public static String getTicket(String access_token) {
        String ticket = null;
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";//���url���ӺͲ������ܱ�
        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET"); // ������get��ʽ����
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// ���ӳ�ʱ30��
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // ��ȡ��ʱ30��
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");
            JSONObject demoJson = JSONObject.parseObject(message);
            System.out.println("JSON�ַ�����" + demoJson);
            ticket = demoJson.getString("ticket");
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticket;
    }


    public static String SHA1(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // �ֽ�����ת��Ϊ ʮ������ ��
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        //1����ȡAccessToken
        String accessToken = getAccessToken();

        //2����ȡTicket
        String jsapi_ticket = getTicket(accessToken);

        //3��ʱ���������ַ���
        String noncestr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);//����ַ���
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);//ʱ���

        System.out.println("accessToken:" + accessToken + "\njsapi_ticket:" + jsapi_ticket + "\nʱ�����" + timestamp + "\n����ַ�����" + noncestr);

        //4����ȡurl
        String url = "http://www.luiyang.com/add.html";
        /*����JSSDK����Ĺ�����м��㣬����Ƚϼ򵥣��Ҿ��ֶ�д��
        String[] ArrTmp = {"jsapi_ticket","timestamp","nonce","url"};
        Arrays.sort(ArrTmp);
        StringBuffer sf = new StringBuffer();
        for(int i=0;i<ArrTmp.length;i++){
            sf.append(ArrTmp[i]);
        }
        */

        //5������������ƴ���ַ���
        String str = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;

        //6�����ַ�������sha1����
        String signature = SHA1(str);
        System.out.println("������" + str + "\nǩ����" + signature);
    }


}
