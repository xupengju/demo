package test;

/**
 * Created by Milo on 2017/2/22.
 */
public class Monkey {
    public static void main(String[] args) {
        int num = 1;
        int time = 5;
        while(true){
            num = num*5 + 1;
            num = num - num/5;
            if(time == 0){
                break;
            }
            System.out.println(num);
            time--;
        }
    }
}
