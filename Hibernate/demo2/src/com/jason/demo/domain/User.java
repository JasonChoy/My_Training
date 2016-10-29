package com.jason.demo.domain;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by cjs on 2016/10/27.
 */
/*主键生成策略
JPA提供的四种标准用法为TABLE,SEQUENCE,IDENTITY,AUTO.
TABLE：使用一个特定的数据库表格来保存主键。
SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。
IDENTITY：主键由数据库自动生成（主要是自动增长型）
AUTO：主键由程序控制。*/
@Entity //不写Table默认为user，@Table(name="t_user")
public class User {
    @Id //主键
    @GeneratedValue(strategy=GenerationType.AUTO)//采用数据库自增方式生成主键
    @Column
    private int id;
    @Column
    private String name;
    @Column
    private String password;
    @Temporal(TemporalType.DATE)//生成yyyy-MM-dd类型的日期
    @Column
    private Date createTime;
    @Temporal(TemporalType.DATE)//生成yyyy-MM-dd类型的日期
    @Column
    private Date expireTime;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getExpireTime() {
        return expireTime;
    }
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

}