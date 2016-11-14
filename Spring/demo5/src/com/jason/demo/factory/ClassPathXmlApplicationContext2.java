package com.jason.demo.factory;

import com.jason.demo.dao.TestDao;
import com.jason.demo.factory.pojo.BeanDefinition;
import com.jason.demo.service.TestService;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cjs on 2016/11/14.
 * 读取类路径下的配置文件
 */
public class ClassPathXmlApplicationContext2 implements BeanFactory{

    // 用于存放Bean
    private List<BeanDefinition> beanDefines = new ArrayList<BeanDefinition>();
    // 用于存放Bean的实例
    private Map<String, Object> sigletons =new HashMap<String, Object>();


    public ClassPathXmlApplicationContext2(String fileName) {

        this.readXML(fileName);

        this.instanceBeans();

        this.injectObject();
    }

    /**
     * 依赖注入，为bean对象的属性注入值
     * 这里还不灵活，但是原理是一样的
     */
    private void injectObject() {
        TestService service = (TestService) this.sigletons.get("service");
        TestDao dao = (TestDao) this.sigletons.get("dao");
        //依赖注入，Service实现依赖dao的实现
        //service.setDao(dao);
    }

    /**
     * 完成bean的实例化
     */
    private void instanceBeans() {
        for(BeanDefinition beanDefinition : beanDefines){
            try {
                if(beanDefinition.getClassName() != null && !"".equals(beanDefinition.getClassName().trim())){
                    sigletons.put(beanDefinition.getId(),Class.forName(beanDefinition.getClassName()).newInstance() );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取xml配置文件
     */
    private void readXML(String fileName) {
        // 创建SAXBuilder对象
        SAXBuilder saxBuilder = new SAXBuilder();

        try {
            // 读取资源，获得document对象
            Document doc = saxBuilder.build(this.getClass().getClassLoader()
                    .getResourceAsStream(fileName));
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

                BeanDefinition beanDefine = new BeanDefinition(id,clazz);
                // 将javabean添加到集合中
                beanDefines.add(beanDefine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取bean实例
     */
    @Override
    public Object getBean(String beanName) {
        return this.sigletons.get(beanName);
    }
}
