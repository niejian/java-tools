package cn.com.code4fun.lottery;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class LotteryUtils {
    private static final Random random = new Random();
    private static final Integer MAXSOPE = 100000000;

    /**
     * 根据各个奖品的中奖概率计算数字区间
     * @param lottery
     * @param lotteryItemList
     */
    public static void calAwardProbability(Lottery lottery, List<LotteryItem> lotteryItemList) {
        Integer codeScope = 1;
        for (LotteryItem item : lotteryItemList) {
            Integer nowScope = 1;
            Double awardProbability = item.getAwardProbability();
            while (true) {
                Double test = awardProbability * nowScope;
                // 概率的精确度，调整到小数点后10位，概率太小等于不中奖，跳出
                if (test < 0.0000000001) {
                    break;
                }
                if ((test >= 1L && (test - test.longValue()) < 0.0001D) || nowScope >= MAXSOPE) {
                    if (nowScope > codeScope) {
                        // 设置中奖范围
                        codeScope = nowScope;
                    }
                    break;
                } else {
                    // 中奖数字范围以10倍进行增长
                    nowScope = nowScope * 10;
                }
            }
        }
        Integer winningStartCode = 0;
        Integer winningEndCode = winningStartCode;

        for (LotteryItem item : lotteryItemList) {
            Integer codeNum = (int) (item.getAwardProbability() * codeScope); // 获得其四舍五入的整数值
            // 无人中奖时，将中奖的起始范围设置在随机数的范围之外
            if (codeNum == 0) {
                item.setAwardStartCode(codeScope + 1);
                item.setAwardEndCode(codeScope + 1);
            } else {
                item.setAwardStartCode(winningEndCode);
                item.setAwardEndCode(winningEndCode + codeNum - 1);
                winningEndCode = winningEndCode + codeNum;
            }
        }
        // 设置用户的中奖随机码信息
        lottery.setWinningStartCode(winningStartCode);
        lottery.setWinningEndCode(winningEndCode);
        lottery.setCodeScope(codeScope);
    }

    public static LotteryItem beginLottery(Lottery lottery, List<LotteryItem> lotteryItemList) {
        // 确定活动是否有效,如果活动无效则，直接抽奖失败
        Integer randomCode = random.nextInt(lottery.getCodeScope());
        if (randomCode >= lottery.getWinningStartCode() && randomCode <= lottery.getWinningEndCode()) {
            for (LotteryItem item : lotteryItemList) {
                if (randomCode >= item.getAwardStartCode() && randomCode <= item.getAwardEndCode()) {
                    item.setAwardCode(randomCode);
                    return item;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        List<LotteryItem> lotteryItemList = new ArrayList<>();
        LotteryItem awardItem1 = new LotteryItem();
        awardItem1.setAwardName("红包10元");
        awardItem1.setAwardProbability(0.025D);
        lotteryItemList.add(awardItem1);

        LotteryItem awardItem2 = new LotteryItem();
        awardItem2.setAwardName("红包20元");
        awardItem2.setAwardProbability(0.025D);
        lotteryItemList.add(awardItem2);

        LotteryItem awardItem4 = new LotteryItem();
        awardItem4.setAwardName("红包50元");
        awardItem4.setAwardProbability(0.005D);
        lotteryItemList.add(awardItem4);

        LotteryItem awardItem3 = new LotteryItem();
        awardItem3.setAwardName("谢谢参与");
        awardItem3.setAwardProbability(0.945D);
        lotteryItemList.add(awardItem3);

        Lottery lottery = new Lottery();
        LotteryUtils.calAwardProbability(lottery, lotteryItemList);
        System.out.println("抽奖活动中奖数字范围：["+lottery.getWinningStartCode()+","+lottery.getWinningEndCode()+")");
        LotteryUtils.beginLottery(lottery, lotteryItemList);
        for (LotteryItem item : lotteryItemList) {
            System.out.println(item.getAwardName()+" 中奖数字范围：["+item.getAwardStartCode()+","+item.getAwardEndCode()+"]");
        }
        System.out.println("以下是模拟的抽奖中奖结果：");
        ExecutorService executorService = new ThreadPoolExecutor(160, 320, 10,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(10240), new ThreadPoolExecutor.DiscardPolicy());
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();
        for (int i = 0; i < 10000; i++) {
            DoLottery doLottery = new DoLottery(lottery, lotteryItemList);
            executorService.execute(doLottery);

//            LotteryItem award1 = LotteryUtils.beginLottery(lottery, lotteryItemList);
//            System.out.println("抽中的数字是："+award1.getAwardCode()+",恭喜中奖："+award1.getAwardName()+",数字落点["+award1.getAwardStartCode()+","+award1.getAwardEndCode()+"]");

        }

        System.out.println("===============>线程运行结束");
        executorService.shutdown();
    }

    static class DoLottery implements Runnable {

        private Lottery lottery;
        List<LotteryItem> lotteryItemList;

        public DoLottery(Lottery lottery, List<LotteryItem> lotteryItemList) {
            this.lottery = lottery;
            this.lotteryItemList = lotteryItemList;
        }

        @Override
        public void run() {
            LotteryItem award1 = LotteryUtils.beginLottery(this.lottery, this.lotteryItemList);


            System.out.println("当前线程：[" + Thread.currentThread().getName() +  "]抽中的数字是："+award1.getAwardCode()+",恭喜中奖："+award1.getAwardName()+",数字落点["+award1.getAwardStartCode()+","+award1.getAwardEndCode()+"]");


        }
    }

}


