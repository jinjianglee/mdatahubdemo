package com.dh.common.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeGenerator {

    public static final String[] TABLES = {"SOS_ORDER"};
    public static final String COMMENT = "snapshot";
    public static final String PARENT = "com.dh";
    public static final String PACKAGE = "datahub";

    public static final DbType DB_TYPE = DbType.MYSQL;
    //    public static final String JDBC_URL = "jdbc:sqlite:v2rayController.db";
//    public static final String DRIVER = "org.sqlite.JDBC";
//    public static final String DB_PASSWORD = "hXlacesarJKbI*3K1DMk";
//    public static final String DB_USER = "DBAdmin";
    public static final String JDBC_URL = "jdbc:mysql://193.112.153.22:3306/vwork?autoReconnectForPools=true";
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_PASSWORD = "hXlacesarJKbI*3K1DMk";
    public static final String DB_USER = "DBAdmin";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("Nevernow");
        gc.setOpen(false);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(JDBC_URL);
        dsc.setDriverName(DRIVER);
        dsc.setDbType(DB_TYPE);
        dsc.setUsername(DB_USER);
        dsc.setPassword(DB_PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(PACKAGE);
        pc.setParent(PARENT);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                this.getConfig().getTableInfoList().forEach(tableInfo -> {
                    tableInfo.setComment(tableInfo.getEntityName() + COMMENT);
                    tableInfo.getFields().forEach(tableField -> {
                        Map customMap = new HashMap(1);
                        customMap.put("fnName", toUpperCaseFirstOne(tableField.getPropertyName()));
                        tableField.setCustomMap(customMap);

                        tableField.setColumnType(tableField.getColumnType().equals(DbColumnType.LOCAL_DATE_TIME) ? DbColumnType.DATE : tableField.getColumnType());
                    });

                    if (tableInfo.getImportPackages().parallelStream().anyMatch(pkg -> DbColumnType.LOCAL_DATE_TIME.getPkg().equals(pkg))) {
                        tableInfo.getImportPackages().remove(DbColumnType.LOCAL_DATE_TIME.getPkg());
                        tableInfo.setImportPackages(DbColumnType.DATE.getPkg());
                    }
                });
                Map<String, Object> map = new HashMap<>();
                map.put("commonPath", PARENT + ".common.basic.*");
                map.put("requestMapping", PACKAGE.replaceAll("\\.","/"));
                this.setMap(map);
            }
        };

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig("generator/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapping/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapping" + StringPool.DOT_XML;
            }
        });
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig("generator/template-ant-design.vue.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/vue/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + ".vue";
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setEntity("generator/entity.java");
        templateConfig.setMapper("generator/mapper.java");
        templateConfig.setService("generator/service.java");
        templateConfig.setServiceImpl("generator/serviceImpl.java");
        templateConfig.setController("generator/controller.java");
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id");
        strategy.setInclude(TABLES);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }

    private static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        }
        return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
}