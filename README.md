---
#文章标题
title: spingmvc与Mybatis整合
#创建时间
date: 2017-07-18 23:46:15
#所属标签
tags:
    - SSM
    - java
    - spingmvc
    - Mybatis
#所属类别
categories: 
    - 后台框架
---

### 一、spingmvc整合

1. 整合过程

![image](https://github.com/naughty-pig/sharePicture/blob/master/SpingMVC/0718/spingmvc%E5%92%8Cmybatis%E6%95%B4%E5%90%88.jpg?raw=true)

<!--more-->

> 数据库连接配置

```
#数据库连接驱动
jdbc.driver=com.mysql.jdbc.Driver
#数据库访问路径
jdbc.url=jdbc:mysql://localhost:3306/springmvc
#用户名
jdbc.username=root
#密码
jdbc.password=1234

```

> 日记文件配置

```
# Global logging configuration，建议开发环境中要用debug
log4j.rootLogger=DEBUG, stdout
# Console output...
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%5p [%t] - %m%n
```

> MyBatis配置


```
1. 位置：src/config/mybatis ; 不固定

<configuration>

<!-- 配置别名 -->
	<typeAliases>
		<!-- 批量扫描别名 -->
		<package name="org.hdj.ssm.po"/>
	</typeAliases>
<!--
 配置mapper 
 <mappers></mappers>
 
这里使用spingmvc和mybatis整合包进行mapper扫描，不需要配置了
mapper扫描器 ; 在ApplicationContext-dao.xml文件中定义
	<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
		扫描包路径，如果需要扫描多个包，中间使用半角逗号隔开 
		<property name="basePackage" value="org.hdj.ssm.mapper"></property>
		<property name="sqlSessionFactoryBeanName" value="sqlSessionFactory" />
	</bean>
-->
</configuration>
```

> sping配置

- 管理dao层配置  
    主要配置数据库连接，扫描mapper映射文件

```
<!--加载db.properties文件中的内容，db.properties文件中key命名要有一定的特殊规则 ；jdbc.xxx-->
	<context:property-placeholder location="classpath:config/db.properties" />

<!-- 配置数据源 ，dbcp;需要从db.properties文件中读取内容； -->

<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource"
	destroy-method="close">
	<property name="driverClassName" value="${jdbc.driver}" />
	<property name="url" value="${jdbc.url}" />
	<property name="username" value="${jdbc.username}" />
	<property name="password" value="${jdbc.password}" />
	<property name="maxActive" value="30" />
	<property name="maxIdle" value="5" />
</bean>
	
	
<!-- sqlSessionFactory -->
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
	<!-- 数据库连接池 -->
	<property name="dataSource" ref="dataSource" />
	<!-- 加载mybatis的全局配置文件 ； classpath：指定文件路径-->
	<property name="configLocation" value="classpath:config/mybatis/sqlMapConfig.xml" />
</bean>
	
	
	<!-- mapper扫描器 -->
	<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
		<!-- 扫描包路径，如果需要扫描多个包，中间使用半角逗号隔开 -->
		<property name="basePackage" value="org.hdj.ssm.mapper"></property>
		<property name="sqlSessionFactoryBeanName" value="sqlSessionFactory" />
	</bean>
```

- service服务层配置  
    配置事务，通知，AOP

```
<!-- 配置事务管理器 -->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
<!-- 指定数据源; 在dao层配置已定义 -->
	<property name="dataSource" ref="dataSource" ></property>
</bean>


<!-- 通知  ，可以规范代码编写；propagation：指定是否需要事务管理 -->
<tx:advice id="txAdvice" transaction-manager="transactionManager">
	<tx:attributes>
		<tx:method name="find*" propagation="SUPPORTS" read-only="true"/>
		<tx:method name="get*" propagation="SUPPORTS" read-only="true"/>
		<tx:method name="selective*" propagation="SUPPORTS" read-only="true"/>
		<tx:method name="save*" propagation="REQUIRED"/>
		<tx:method name="insert*" propagation="REQUIRED"/>
		<tx:method name="delete*" propagation="REQUIRED"/>
		<tx:method name="update*" propagation="REQUIRED"/>
	</tx:attributes>
</tx:advice>


<!-- 面向切面 -->
<aop:config >
	<aop:advisor advice-ref="txAdvice" pointcut="execution(* org.hdj.ssm.service.Impl.ItemServiceImpl.*.*(..))"/>
</aop:config>
```

- 事务管理配置ApplicationContext-transraction.xml
```
<!-- 管理service事务层 -->
<bean id="itemServiceImpl" class="org.hdj.ssm.service.Impl.ItemServiceImpl"/>
```

- sping配置文件

```
<!-- 组件扫描 -->
<context:component-scan base-package="org.hdj.ssm.controller"></context:component-scan>
	
<!--注解驱动  -->
<mvc:annotation-driven></mvc:annotation-driven>
<!-- 视图解析器 -->
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	<property name="prefix" value="/WEB-INF/page/"/>
	<property name="suffix" value=".jsp"/>
</bean>
```

> [MyBatis逆向工程生成代码](https://github.com/naughty-pig/MyBatisReverseEngineering)


> 逻辑代码编写



- 映射文件Mapper; 这个是重写的，已把生成的删除
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<!--namespace：指定与之对应的dao层接口-->
<mapper namespace="org.hdj.ssm.mapper.ItemsMapperCustom" >
  
  <!--
  	查询条件
    -->
  <sql id="find_item_where">
  	<if test="ItemsCustom!=null">
  		<if test="ItemsCustom.name!=null and ItemsCustom.name=!''">
  			items.name LIKE '%'|| ${ItemsCustom.name} ||'%'
  		</if>
  	</if>
  </sql>
  
  <select id="findItemsList" resultType="org.hdj.ssm.po.ItemsCustom" parameterType="org.hdj.ssm.po.ItemsCustomVo">
	  	SELECT
		*
		FROM
			items
		<where>
			<include refid="find_item_where"></include>
		</where>
  </select>
</mapper>
```

- dao层接口
```
public interface ItemsMapperCustom {
    
	public Collection<ItemsCustom> findItemsList(ItemsCustomVo itemsCustomVo) throws Exception;
}
```
- 服务层
- 
```
1. 接口
public interface ItemService {
	
	public Collection<ItemsCustom> findItemsList(ItemsCustomVo itemsCustomVo) throws Exception;
}
```
2. 实现类

```
public class ItemServiceImpl implements ItemService{
    //@Autowired自动注入
	@Autowired
	public ItemsMapperCustom itemsMapperCustom;
	
	@Override
	public Collection<ItemsCustom> findItemsList(ItemsCustomVo itemsCustomVo) throws Exception{
		return itemsMapperCustom.findItemsList(itemsCustomVo);
	}
}
```

- 控制器

```
@Controller
public class ItemsController {

	@Autowired
	public ItemService itemService;
	//查询商品
		@RequestMapping("/queryItems")
		public ModelAndView queryItems() throws Exception{
			
			Collection<ItemsCustom> findItemsList = itemService.findItemsList(null);
			//创建一个modelAndView 向前端适配器返回请求数据
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.addObject("items", findItemsList);
			
			//指定响应的视图路径
			//modelAndView.setViewName("/WEB-INF/page/items/itemsList.jsp");
			modelAndView.setViewName("items/itemsList");
			return modelAndView;
		}
}
```