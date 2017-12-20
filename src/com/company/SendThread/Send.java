package com.company.SendThread;

import com.company.Util;

import java.util.ArrayDeque;
import java.util.Queue;

public class Send extends Thread {
    private Queue<Integer> toSend;
    private Queue<Integer[]> readyToSend;
    private final Integer[] key;
    public Send (String key) {
        this.key = Util.toIntArr(key);
        this.readyToSend = new ArrayDeque<>();
        this.toSend = new ArrayDeque<>();
    }
    @Override
    public void run() {
        while (true) {
            if (toSend.size()  > 0) {
                int signal = toSend.poll();
                if (signal > 0) {
                    readyToSend.add(makeSend(1));
                } else if (signal < 0){
                    readyToSend.add(makeSend(-1));
                }
            }
        }
    }
    public boolean readyToSend() {
        return readyToSend.size() > 0;
    }
    public  Integer[] send () {
        return readyToSend.poll();
    }
    private Integer[] makeSend(Integer i) {
        Integer[] ret = new Integer[key.length];
        int index = 0;
        for (Integer in : this.key) {
            ret[index] = in * i;
            index ++;
        }
        return ret;
    }

    public void insert (int s) {
        toSend.add(s);
    }
}
