import test.OrderStatus;

public class Test {

    public static void main(String[] args) throws Exception {
        System.out.println(OrderStatus.unshipped.name);
        System.out.println(OrderStatus.unshipped.getName());
        System.out.println(OrderStatus.unshipped.name());
        System.out.println(OrderStatus.unshipped.ordinal());
        //String str = "&#19978;&#28023;&#22768;&#20687;&#20986;";
        //String newStr = StringEscapeUtils.unescapeXml(str);
// �±����Ҳ��
// String newStr=StringEscapeUtils.unescapeHtml(str);
        //ת��֮����ַ�
       // System.out.println(newStr);



























        String str1 = "1,2,31,12,4,34";
        String str2 = "1,2,32,12,4,34";
        String str3 = "1,2,33,12,4,34";
        String str4 = "1,2,34,12,4,34";
        String[] strings1 = str1.split(",");
        String[] strings2 = str2.split(",");
        String[] strings3 = str3.split(",");
        String[] strings4 = str4.split(",");
        for(int j = 0;j<strings1.length;j++){
            System.out.println(strings1[j]+strings2[j]+strings3[j]+strings4[j]);
        }

        System.out.println(String.format("%s%04d", 20161123, 12));



        Integer a = 127;
        Integer b = 127;
        System.out.println(a == b);
        Integer c = 128;
        Integer d = 128;
        System.out.println(c == d );
        System.out.println(c.equals(d));

    }
}