package com.boss;

import com.boss.syn.A;

import java.util.*;
import java.util.concurrent.*;


public class testCallable2 {
    private final static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        try {
            Long start = System.currentTimeMillis();
            List<String> list = new ArrayList();
            list.add("A");
            list.add("B");
            list.add("C");
            completionServiceCount(list);
            Long end = System.currentTimeMillis();
            System.out.println("耗时："+(start-end));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    /**
     * 使用completionService收集callable结果
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void completionServiceCount(List<String> list) throws InterruptedException, ExecutionException {
        //ExecutorService executorService = Executors.newCachedThreadPool();
        CompletionService<String> completionService = new ExecutorCompletionService<>(
                executorService);
        for (String str : list) {
            completionService.submit(getTask(str));
        }


        //处理数据
        Map<String,String> map = new HashMap<>();
        for (String str : list) {
            String temp = completionService.take().get();
            map.put(temp.split(",")[0],temp.substring(temp.indexOf(",")+1));
        }
        System.out.println(map);

        System.out.println("CompletionService all is : " + 1);
    }

    public static Callable<String> getTask(String str) {
        final Random rand = new Random();
        Callable<String> task = () -> {
            int time = rand.nextInt(30)*100;
            int count1 = rand.nextInt(10)*100;
            int count2 = rand.nextInt(10)*100;
            int count3 = rand.nextInt(10)*100;
            System.out.println("thead:"+str+" time is:"+time);
            Thread.sleep(time);
            String result =str+","+count1+","+count2+","+count3;
            return result;
        };
        return task;
    }
}
