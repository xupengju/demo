package J8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Milo on 2017/9/1.
 */
public class DelTrans {
    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );


        //(1) 找出2011年发生的所有交易，并按交易额排序(从低到高)。
        List<Integer> collect = transactions.stream()
                                            .filter(transaction -> transaction.getYear() == 2011)
                                            .map(trans -> trans.getValue())
                                            .sorted((t1, t2) -> t1.compareTo(t2)).collect(Collectors.toList());
        System.out.println(collect);
        //(2) 交易员都在哪些不同的城市工作过?
        List<String> citys = transactions.stream().map(Transaction::getTrader).map(trader -> trader.getCity()).distinct().collect(Collectors.toList());
        System.out.println(citys);
        //(3) 查找所有来自于剑桥的交易员，并按姓名排序。
        List<Trader> traders = transactions.stream().map(Transaction::getTrader).filter(t -> "Cambridge".equals(t.getCity())).distinct().sorted((tender1,tender2) -> tender1.getName().compareTo(tender2.getName())).collect(Collectors.toList());
        System.out.println(traders);
        //(4) 返回所有交易员的姓名字符串，按字母顺序排序。
        List<String> list = transactions.stream().map(Transaction::getTrader).distinct().sorted((tender1,tender2) -> tender1.getName().compareTo(tender2.getName())).map(trader -> trader.getName()).collect(Collectors.toList());
        System.out.println(list);
        //(5) 有没有交易员是在米兰工作的?
        boolean result = transactions.stream().map(Transaction::getTrader).anyMatch(trader -> "Milan".equals(trader.getCity()));
        System.out.println(result);
        //(6) 打印生活在剑桥的交易员的所有交易额。
        List<Trader> collect1 = transactions.stream().map(Transaction::getTrader).filter(t -> "Cambridge".equals(t.getCity())).collect(Collectors.toList());
        System.out.println(collect1);
        //(7) 所有交易中，最高的交易额是多少?
        //(8) 找到交易额最小的交易。


    }

}
