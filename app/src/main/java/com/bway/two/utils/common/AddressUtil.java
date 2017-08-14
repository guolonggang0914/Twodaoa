package com.bway.two.utils.common;

import com.bway.two.model.bean.CityData;

import java.util.Comparator;

/**
 * 类描述：
 * 创建人：卢程
 * 创建时间：2017/8/14
 */

public class AddressUtil {

    public static class AddressCompartor implements Comparator<CityData> {

        @Override
        public int compare(CityData o1, CityData o2) {
            String o1f = PinyinUtils.getFirstLetter(o1.getAreaname());
            String o2f = PinyinUtils.getFirstLetter(o2.getAreaname());
            if (o1f.equals("#")) {
                return 1;
            } else if (o2f.equals("#")) {
                return -1;
            } else {
                return o1f.compareTo(o2f);
            }
        }
    }

}
