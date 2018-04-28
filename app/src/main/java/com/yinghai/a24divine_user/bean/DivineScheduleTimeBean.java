package com.yinghai.a24divine_user.bean;

import java.util.List;

/**
 * Created by chenjianrun on 2018/3/27.
 */

public class DivineScheduleTimeBean {

    /**
     * code : 1
     * msg : sucess
     * data : {"timetable":["22:1","44:0","23:1","45:0","24:1","46:0","25:1","47:0","26:1","48:0","27:1","28:1","29:1","30:0","31:0","10:1","32:0","11:1","33:0","12:1","34:0","13:1","35:0","14:1","36:0","15:1","37:0","16:1","38:0","17:1","39:0","18:1","19:1","1:1","2:1","3:1","4:1","5:1","6:1","7:1","8:1","9:1","40:0","41:0","20:1","42:0","21:1","43:0"]}
     */

    private String code;
    private String msg;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String date;
        private List<String> timetable;

        public List<String> getTimetable() {
            return timetable;
        }

        public void setTimetable(List<String> timetable) {
            this.timetable = timetable;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
