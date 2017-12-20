package com.company.RecThread;

import com.company.Util;

import java.util.ArrayDeque;
import java.util.Queue;

public class Receive extends Thread{
    private final Integer[] key;
    private Queue<Integer> rec; //CDMA解码后
    private Queue<Integer[]> get; //获取上部传来的讯息
    public Receive(String key) {
        this.key = Util.toIntArr(key);
        this.rec = new ArrayDeque<>();
        this.get = new ArrayDeque<>();
    }
    @Override
    public void run() {
        while (true) {
            if (get.size() != 0) {
                Integer[] flag = get.poll();
                int i = get(flag);
                if (i == 0) {
                    System.out.print(toString());

                }
                else {
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    rec.add(i);
                }
            }
        }
    }
    private int get(Integer[] array) {
        int index = 0;
        int sum = 0;
        for (int i : array) {
            sum += i * this.key[index];
            index ++;
        }
        return sum / array.length;
    }
    public void receive(String arr) {
        receive(Util.toIntArr(arr));
    }
    public  void receive(Integer[] array) {
        get.add(array);
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        while (rec.size() > 0) {
            sb.append(rec.poll());
        }
        return sb.toString();
    }
}
