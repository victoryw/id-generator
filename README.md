# id-generator
The package published in the jcenter
named as org.victoryw.idGenerator:IDGenerator

### Maven
```
<dependency>
  <groupId>org.victoryw.idGenerator</groupId>
  <artifactId>IDGenerator</artifactId>
  <version>1.2</version>
  <type>pom</type>
</dependency>
```

### gradle 
```
compile 'org.victoryw.idGenerator:IDGenerator:1.2'
```


* org.victoryw.idGenerator.twitter.Snowflake 提供了twitter Snowflake Id 生成的实现
* org.victoryw.idGenerator.hourId.HourIdGenerator 支持每小时内自增的算法

具体使用方式请见测试
https://github.com/victoryw/id-generator/tree/master/src/test/java/org/victoryw/idGenerator
