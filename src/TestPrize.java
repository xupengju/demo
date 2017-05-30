import java.util.ArrayList;
import java.util.List;

/**
 * Created by xpj on 2016/10/20.
 */
public class TestPrize {

    public static void main(String[] args) {
        List<Prize> list = new ArrayList<>();
        list.add(new Prize(20, 1, "����"));
        list.add(new Prize(4808, 2, "лл����"));
        list.add(new Prize(4500, 3, "5Ԫ����ȯ"));
        list.add(new Prize(200, 4, "TF"));
        list.add(new Prize(20, 5, "������"));
        list.add(new Prize(2, 6, "ǩ��"));
        list.add(new Prize(0, 7, "200"));
        list.add(new Prize(20, 8, "VIP"));
        for (int i = 0; i < 10000; i++) {
            if(PercentageRandom(list) == 7){
                System.out.println("daijinqu");
            }
            if(PercentageRandom(list) == 6){
                System.out.println("qianmingzhuanji");
            }
        }
    }

    public static int PercentageRandom(List<Prize> prizes) {
        double sumWeight = 0;
        //������Ȩ��
        for (Prize rp_1 : prizes) {
            sumWeight += rp_1.getRate();
        }
        int random = (int) (Math.random() * sumWeight + 1);//�����
        int stepTotal = 0;

        for (Prize probability : prizes) {
            int proba = probability.getRate();
            int step = stepTotal + proba;

            //��������Ž���ͬ��Ͱ
            if (random > stepTotal && random <= step) {
                return probability.getLevel();
            }

            stepTotal += proba;
        }

        return 0;
    }
}

class Prize {
    public int rate;
    public int level;
    public String name;

    public Prize(int rate, int level, String name) {
        this.rate = rate;
        this.level = level;
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
