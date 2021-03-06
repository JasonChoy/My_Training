package com.jasoncai.firstssm.service.impl;

import com.jasoncai.firstssm.dao.ItemsMapperCustom;
import com.jasoncai.firstssm.proxy.ItemsCustom;
import com.jasoncai.firstssm.service.ItemsService;
import com.jasoncai.firstssm.vo.ItemsQueryVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cjs on 2016/10/10.
 */
@Service
public class ItemsServiceImpl implements ItemsService {

    @Resource
    private ItemsMapperCustom itemsMapperCustom;

    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
        return itemsMapperCustom.findItemsList(itemsQueryVo);
    }

    @Override
    public ItemsCustom findItemById(Integer id) {
        return itemsMapperCustom.findItemById(id);
    }

    @Override
    public void updateItem(ItemsCustom itemsCustom) {
        itemsMapperCustom.updateItem(itemsCustom);
    }

    @Override
    public void deleteItems(Integer[] items_id) {
        itemsMapperCustom.deleteItems(items_id);
    }

}

/*

@Autowired 与@Resource的区别：
        1、 @Autowired与@Resource都可以用来装配bean. 都可以写在字段上,或写在setter方法上。

        2、 @Autowired默认按类型装配（这个注解是属业spring的），默认情况下必须要求依赖对象必须存在，如果要允许null值，可以设置它的required属性为false，如：@Autowired(required=false) ，如果我们想使用名称装配可以结合@Qualifier注解进行使用，如下：

        Java代码

@Autowired() @Qualifier("baseDao")
private BaseDao baseDao;
@Autowired() @Qualifier("baseDao") private BaseDao baseDao;
        3、@Resource（这个注解属于J2EE的），默认安装名称进行装配，名称可以通过name属性进行指定，如果没有指定name属性，当注解写在字段上时，默认取字段名进行安装名称查找，如果注解写在setter方法上默认取属性名进行装配。当找不到与名称匹配的bean时才按照类型进行装配。但是需要注意的是，如果name属性一旦指定，就只会按照名称进行装配。
        Java代码

@Resource(name="baseDao")
private BaseDao baseDao;
@Resource(name="baseDao") private BaseDao baseDao;
        推荐使用：@Resource注解在字段上，这样就不用写setter方法了，并且这个注解是属于J2EE的，减少了与spring的耦合。这样代码看起就比较优雅。*/
