package com.company.UnionThread;

import com.company.RecThread.Receive;
import com.company.SendThread.Send;

import java.util.Map;

public class SystemThread extends Thread {
    private final Union union;
    public SystemThread(int n) {
        union = new Union(n);
        union.start();
    }
    public void run() {

        while (true) {
            try {
                Thread.sleep(10);
                makeMsg();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void makeMsg() {
            if (isReady(union.getSends())) {
                Integer[] sendMsg = new Integer[8];
                for (int index : union.getSends().keySet()) {
                    sendMsg = addIntArr(union.getSends().get(index).send(), sendMsg);
                }
                receive(sendMsg, union.getReceives());
            }
    }
    private boolean isReady(Map<Integer, Send> m) {
        boolean b = true;
        for (int index : m.keySet()) {
            b &= m.get(index).readyToSend();
        }
        return b;
    }

    private Integer[] addIntArr(Integer[] a, Integer[] b) {
        Integer[] ret = new Integer[a.length];
        for (int i = 0 ; i < a.length ; i ++) {
            ret[i] = a[i] + b[i];
        }
        return ret;
    }
    private void receive (Integer[] arr, Map<Integer, Receive> m) {
            for (int index : m.keySet()) {
                m.get(index).receive(arr);
            }
    }

    public void putMsg(int index, int msg) {
        if (index <= union.getRoom() && index > 0) {
            union.insert(index, msg);
        } else {
            union.insertall(msg);
        }
    }
}
