package com.bway.two.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class RecyclerViewItem {

    /**
     * code : 1000
     * descirption : 系统处理成功
     * object : [{"consumeLower":0,"consumeUpper":20000,"recordCoding":"A20170801","integral":0,"integralStyle":"http://dadao-file.oss-cn-beijing.aliyuncs.com/dadao/1502359324680.png","cashbackSpecificDate":1504540800000},{"consumeLower":20001,"consumeUpper":5000000,"recordCoding":"B20171002","integral":0,"integralStyle":"http://dadao-file.oss-cn-beijing.aliyuncs.com/dadao/1502360023391.png","cashbackSpecificDate":1499184000000}]
     */

    private String code;
    private String descirption;
    private List<ObjectBean> object;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescirption() {
        return descirption;
    }

    public void setDescirption(String descirption) {
        this.descirption = descirption;
    }

    public List<ObjectBean> getObject() {
        return object;
    }

    public void setObject(List<ObjectBean> object) {
        this.object = object;
    }

    public static class ObjectBean {
        /**
         * consumeLower : 0
         * consumeUpper : 20000
         * recordCoding : A20170801
         * integral : 0
         * integralStyle : http://dadao-file.oss-cn-beijing.aliyuncs.com/dadao/1502359324680.png
         * cashbackSpecificDate : 1504540800000
         */

        private int consumeLower;
        private int consumeUpper;
        private String recordCoding;
        private int integral;
        private String integralStyle;
        private long cashbackSpecificDate;

        public int getConsumeLower() {
            return consumeLower;
        }

        public void setConsumeLower(int consumeLower) {
            this.consumeLower = consumeLower;
        }

        public int getConsumeUpper() {
            return consumeUpper;
        }

        public void setConsumeUpper(int consumeUpper) {
            this.consumeUpper = consumeUpper;
        }

        public String getRecordCoding() {
            return recordCoding;
        }

        public void setRecordCoding(String recordCoding) {
            this.recordCoding = recordCoding;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String getIntegralStyle() {
            return integralStyle;
        }

        public void setIntegralStyle(String integralStyle) {
            this.integralStyle = integralStyle;
        }

        public long getCashbackSpecificDate() {
            return cashbackSpecificDate;
        }

        public void setCashbackSpecificDate(long cashbackSpecificDate) {
            this.cashbackSpecificDate = cashbackSpecificDate;
        }
    }
}
