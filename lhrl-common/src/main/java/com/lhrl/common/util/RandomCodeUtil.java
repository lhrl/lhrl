package com.lhrl.common.util;

import java.util.Random;

/**
 *
 * @author Mei Xianzhi 2015-1-4 14:28:43
 */
public class RandomCodeUtil {

    public static String getRand() {
        Random random = new Random();
        StringBuilder sRand = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand.append(rand);
        }
        return sRand.toString();
    }

    public static String getRand3() {
        Random random = new Random();
        StringBuilder sRand = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand.append(rand);
        }
        return sRand.toString();
    }
}
