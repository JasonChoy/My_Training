package com.jason.demo.common;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * Created by cjs on 2017/6/17.
 *  关于MybatisPlus代码生成器 http://mp.baomidou.com/#/generate-code
 */
public class MybatisPlusGenerator {
    //基本配置
    public static final String OUTPUT_DIR="F:\\Programming WorkSpace\\My_Training\\MyBatis\\mybatisplus_generator\\src\\main\\java";
    public static final String AUTHOR="JasonChoi";
    public static final boolean IS_FILE_OVERRIDE= true;//是否覆盖同名文件
    public static final boolean IS_OPEN= false;//生成完不打开目录
    //数据库配置
    public static final String JDBC_DRIVER_NAME="com.mysql.jdbc.Driver";
    public static final String JDBC_USER_NAME="root";
    public static final String JDBC_PASSWORD="root";
    public static final String JDBC_URL="jdbc:mysql://127.0.0.1:3306/mybatis-plus?characterEncoding=utf8";
    public static final DbType JDBC_TYPE=DbType.MYSQL;
    // 包配置
    public static final String PACKAGE_PATH="com.jason";
    public static final String MODULE_NAME="demo";
    public static final String CONTROLLER_PATH="controller";
    public static final String SERVICE_PATH="service";
    public static final String MAPPER_INTERFACE_PATH="mapper";
    public static final String MAPPER_XML_PATH="mapper";
    public static final String MODEL_PATH="model";
    //生成策略 (想要对整个数据库执行操作 就把以下两个条件设置为false 注意:不能同时设置两个条件为true)
    public static final boolean IS_ONLY_BUILD_INCLUDE_TABLE = false;//只生成以下包含的表
    public static final String[] ONLY_BUILD_INCLUDE_TABLE = new String[] { "user" };
    public static final boolean IS_ONLY_UN_BUILD_EXINCLUDE_TABLE = false;//不生成以下排除的表，其他的都生成
    public static final String[] ONLY_UN_BUILD_EXINCLUDE_TABLE = new String[] { "user" };
    //是否生成ehcache二级缓存
    public static final boolean IS_EHCACHE = false;

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setAuthor(AUTHOR);//作者
        gc.setOutputDir(OUTPUT_DIR);//输出目录
        gc.setFileOverride(IS_FILE_OVERRIDE);//是否覆盖同名文件(默认false)
        gc.setActiveRecord(true);
        gc.setEnableCache(IS_EHCACHE);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setOpen(IS_OPEN);//生成完不打开目录
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        // gc.setMapperName("%sDao");
        // gc.setXmlName("%sDao");
        // gc.setServiceName("MP%sService");
        // gc.setServiceImplName("%sServiceDiy");
        // gc.setControllerName("%sAction");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(JDBC_TYPE);
        dsc.setDriverName(JDBC_DRIVER_NAME);
        dsc.setUsername(JDBC_USER_NAME);
        dsc.setPassword(JDBC_PASSWORD);
        dsc.setUrl(JDBC_URL);
/*        dsc.setTypeConvert(new MySqlTypeConvert(){
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                System.out.println("转换类型：" + fieldType);
                // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
                return super.processTypeConvert(fieldType);
            }
        });*/
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //strategy.setDbColumnUnderline(false);//设置使得数据库字段强行按照java实体的骆驼式命名法大写字母前转化为下划线加小写的命名规范
        //strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        //strategy.setTablePrefix(new String[] { "tlog_", "tsys_" });// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        if(IS_ONLY_BUILD_INCLUDE_TABLE){
            strategy.setInclude(ONLY_BUILD_INCLUDE_TABLE); // 需要生成的表
        }
        if(IS_ONLY_UN_BUILD_EXINCLUDE_TABLE){
            strategy.setExclude(ONLY_UN_BUILD_EXINCLUDE_TABLE); // 排除生成的表
        }
        // 自定义实体父类
        // strategy.setSuperEntityClass("com.baomidou.demo.TestEntity");
        // 自定义实体，公共字段
        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
        // 自定义 mapper 父类
        // strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
        // 自定义 service 父类
        // strategy.setSuperServiceClass("com.baomidou.demo.TestService");
        // 自定义 service 实现类父类
        // strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
        // 自定义 controller 父类
        // strategy.setSuperControllerClass("com.baomidou.demo.TestController");
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
           strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        //strategy.setEntityBuilderModel(true);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(PACKAGE_PATH);
        pc.setModuleName(MODULE_NAME);
        pc.setController(CONTROLLER_PATH);
        pc.setService(SERVICE_PATH);
        pc.setServiceImpl(SERVICE_PATH  + "." + "impl");
        pc.setMapper(MAPPER_INTERFACE_PATH);
        pc.setXml(MAPPER_XML_PATH);
        pc.setEntity(MODEL_PATH);
        mpg.setPackageInfo(pc);

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/template 下面内容修改，
        // 放置自己项目的 src/main/resources/template 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        // TemplateConfig tc = new TemplateConfig();
        // tc.setController("...");
        // tc.setEntity("...");
        // tc.setMapper("...");
        // tc.setXml("...");
        // tc.setService("...");
        // tc.setServiceImpl("...");
        // mpg.setTemplate(tc);

        // 执行生成
        mpg.execute();
    }

}
