package com.company;

import com.company.RecThread.Receive;
import com.company.UnionThread.SystemThread;

public class Main {

    public static void main(String[] args) {
	// write your code here
        SystemThread st = new SystemThread(4);
        st.start();
        st.putMsg(-1, 1);

        st.putMsg(-1, 1);

        st.putMsg(-1, 1);

        st.putMsg(-1, 1);

        st.putMsg(-1, -1);

        st.putMsg(-1, 0);
    }
}
