package com.jason.demo.factory;

import com.jason.demo.dao.TestDao;
import com.jason.demo.service.TestService;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cjs on 2016/11/14.
 * 读取类路径下的配置文件
 */
public class ClassPathXmlApplicationContext implements BeanFactory{
    // 用于存放Bean
    private Map<String, Object> beans = new HashMap<String, Object>();

    public ClassPathXmlApplicationContext(String fileName) {

        this.readXML(fileName);

    }

    // 解析xml文件，通过反射将配置的beasn放到container中，并实现依赖注入
    private void readXML(String fileName) {
        // 创建SAXBuilder对象
        SAXBuilder saxBuilder = new SAXBuilder();
        // 读取资源，获得document对象
        Document doc;
        try {
            doc = saxBuilder.build(this.getClass().getClassLoader().getResourceAsStream(fileName));

            // 获取根元素
            Element rootEle = doc.getRootElement();
            // 从根元素获得所有的子元素，建立元素集合
            List listBean = XPath.selectNodes(rootEle, "/beans/bean");

            // 遍历根元素的子元素集合，扫描配置文件中的bean
            for (int i = 0; i < listBean.size(); i++) {
                Element bean = (Element) listBean.get(i);
                // 获取id属性值
                String id = bean.getAttributeValue("id");
                // 获取class属性值
                String clazz = bean.getAttributeValue("class");
                // 反射，实例化
                Object o = Class.forName(clazz).newInstance();
                beans.put(id, o);
            }

            // 依赖管理，这里还不灵活，但是原理是一样的
            TestService service = (TestService) beans.get("service");
            TestDao dao = (TestDao) beans.get("dao");
            // 依赖注入，Service实现依赖dao的实现
            //service.setDao(dao);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    /*
     * 查找组件
     *
     * @param id
     * @return
     */
    @Override
    public Object getBean(String id) {
        return beans.get(id);
    }

}
