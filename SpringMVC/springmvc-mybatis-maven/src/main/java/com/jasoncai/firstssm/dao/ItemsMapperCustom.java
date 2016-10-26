package com.jasoncai.firstssm.dao;

import com.jasoncai.firstssm.proxy.ItemsCustom;
import com.jasoncai.firstssm.vo.ItemsQueryVo;

import java.util.List;

/**
 * Created by cjs on 2016/10/10.
 */
public interface ItemsMapperCustom {

    List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;

    ItemsCustom findItemById(Integer id);

    void updateItem(ItemsCustom itemsCustom);

    void deleteItems(Integer[] items_id);
}
