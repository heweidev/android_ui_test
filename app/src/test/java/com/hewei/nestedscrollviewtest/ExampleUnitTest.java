package com.hewei.nestedscrollviewtest;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    private static DecimalFormat RATE_DECIMAL_FORMAT = new DecimalFormat("#0.#####");// 收益格式

    /** 收益率，显示小数点后5位小数 */
    public static String rateFormatString(BigDecimal n){
        if (n != null) {
            return RATE_DECIMAL_FORMAT.format(n.setScale(5, BigDecimal.ROUND_HALF_UP));
        }else{
            return "";
        }
    }

    @Test
    public void testBig() {
        BigDecimal v = new BigDecimal("0");
        BigDecimal newD = v.setScale(2, RoundingMode.FLOOR);
        System.out.println(newD.toString());
        System.out.println(rateFormatString(newD));
    }
}