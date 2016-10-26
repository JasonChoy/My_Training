package com.jason.demo.vo;

import com.jason.demo.domain.User;

import java.util.List;

/**
 * Created by cjs on 2016/9/23.
 *
 */
public class UserQueryVo extends User {
    //这里可以进行扩展
    private int voId;
    private String voUserName;// 用户姓名
    private List<Integer> ids;

    public int getVoId() {
        return voId;
    }

    public void setVoId(int voId) {
        this.voId = voId;
    }

    public String getVoUserName() {
        return voUserName;
    }

    public void setVoUserName(String voUserName) {
        this.voUserName = voUserName;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "UserQueryVo{" +
                "voId=" + voId +
                ", voUserName='" + voUserName + '\'' +
                '}';
    }
}
