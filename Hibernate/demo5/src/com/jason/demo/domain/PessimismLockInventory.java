package com.jason.demo.domain;

/**
 * Created by cjs on 2016/11/7.
 * 实体内容和乐观锁相同，不同的是在实体中不需要添加版本号属性，因为它不是通过判断版本号来限制操作的。
 */
public class PessimismLockInventory {
    //主键id，标识号
    private String itemNo;
    public String getItemNo() {
        return itemNo;
    }
    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    //列表名称
    private String itemName;
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    //个数
    private int quantity;
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
