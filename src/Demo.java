import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * Created by xpj on 16/6/20.
 */
public class Demo {

    private static int[] arr = new int[5];
    public  static  void main(String[] args){
//       System.out.print(setArr(406)[0]+",");
//       System.out.print(setArr(406)[1]+",");
//       System.out.print(setArr(406)[2]+",");
//       System.out.print(setArr(406)[3]+",");
//       System.out.print(setArr(406)[4]+",");
       // System.out.println(UUID.randomUUID().toString().replace("-",""));
//
//        try {
//            System.out.println(new String ("&#xFFFD;&#xFFFD;&#xFFFD;&#xFFFD;&#xFFFD;&#xFFFD;&#x13F;&#x427;&#xFFFD;&#xFFFD;&#x37C;&#xFFFD;&#xFFFD;&#xFFFD;&#xFFFD;&#xFFFD;&#xFFFD;&#xFFFD;&#xFFFD;&#xFFFD;&#xFFFD;.rar".getBytes("ISO-8859-1"),"UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        String demo = null;
        String s= 1+"_"+1 +"_";
        String[] strings = s.split("_");
        System.out.println(strings[2]);

    }
    public static int[] setArr(int a){
        if(a%5 == 0){
            int j = 4;
            for(int i = 0; i<=4; i++){
                arr[i] = a- j;
                j--;
            }
        }else{
            for(int i = 0 ;i<=4;i++){
                arr[i]= new Double(5* Math.ceil(a/5)+1+i).intValue();
            }
          //  5* Math.ceil(400/5)+1 +1234;
        }

       return arr;
    }

    public void countwave(int now){

        String newStr = String.valueOf(now);
        char lastStr  = newStr.charAt(newStr.length()-1);
        if(Integer.valueOf(lastStr)> 0 && Integer.valueOf(lastStr)<=5){

        }

    }



}
