package com.company.UnionThread;

import com.company.RecThread.Receive;
import com.company.SendThread.Send;

import java.util.*;

public class Union extends Thread {
    public void insertall(int msg) {
        for (int i = 0 ; i < room; i++) {
            insert(i, msg);
        }
    }

    class message {
        private Integer index;
        private Integer code;
        public message (Integer index, Integer code) {
            this.code = code;
            this.index = index;
        }

        public Integer getIndex() {
            return index;
        }


        public Integer getCode() {
            return code;
        }
    }
    private final static int capacity = 4;
    private int room;
    private final static String[] keys = {"-1,-1,-1,1,1,-1,1,1",
    "-1,-1,1,-1,1,1,1,-1",
    "-1,1,-1,1,1,1,-1,-1",
    "-1,1,-1,-1,-1,-1,1,-1"};
    private final Map<Integer, Receive> receives;
    private final Map<Integer, Send> sends;
    private Queue<message> sendings;
    public Union(int n) {
        if (n > capacity) room = capacity;
        else room = n;
        receives = new HashMap<>();
        sends = new HashMap<>();
        for (int i = 0 ; i < room ; i ++) {
            receives.put(i, new Receive(keys[i]));
            sends.put(i, new Send(keys[i]));
        }
        sendings = new ArrayDeque<>();
        for (int index : sends.keySet()) {
            sends.get(index).start();
            receives.get(index).start();
        }
    }
    @Override
    public void run() {

        System.out.println("完成初始化CDMA进程");
        System.out.println("启动转接器");
        while (true) {
            Send se;
            if (sendings.size() > 0) {
                message msg = sendings.poll();
                if (msg.index < room && msg.index >= 0) {
                    se = sends.get(msg.index);
                    se.insert(msg.code);
                }
            }
        }
    }

    private void insert(message msg) {
        this.sendings.add(msg);
    }
    public void insert(int index, int code) {
        insert(new message(index, code));
    }
    public int getRoom() {
        return room;
    }

    public Map<Integer, Receive> getReceives() {
        return receives;
    }

    public Map<Integer, Send> getSends() {
        return sends;
    }

    public static String[] getKeys() {
        return keys;
    }
}
