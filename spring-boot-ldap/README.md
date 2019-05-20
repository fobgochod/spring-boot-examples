# Getting Started

### LDAP简介

&emsp;&emsp;LDAP（轻量级目录访问协议，Lightweight Directory Access Protocol)是实现提供被称为目录服务的信息服务。目录服务是一种特殊的数据库系统，其专门针对读取，浏览和搜索操作进行了特定的优化。目录一般用来包含描述性的，基于属性的信息并支持精细复杂的过滤能力。目录一般不支持通用数据库针对大量更新操作操作需要的复杂的事务管理或回卷策略。而目录服务的更新则一般都非常简单。这种目录可以存储包括个人信息、web链结、jpeg图像等各种信息。为了访问存储在目录中的信息，就需要使用运行在TCP/IP 之上的访问协议—LDAP。

&emsp;&emsp;LDAP目录中的信息是是按照树型结构组织，具体信息存储在条目(entry)的数据结构中。条目相当于关系数据库中表的记录；条目是具有区别名DN （Distinguished Name）的属性（Attribute），DN是用来引用条目的，DN相当于关系数据库表中的关键字（Primary Key）。属性由类型（Type）和一个或多个值（Values）组成，相当于关系数据库中的字段（Field）由字段名和数据类型组成，只是为了方便检索的需要，LDAP中的Type可以有多个Value，而不是关系数据库中为降低数据的冗余性要求实现的各个域必须是不相关的。LDAP中条目的组织一般按照地理位置和组织关系进行组织，非常的直观。LDAP把数据存放在文件中，为提高效率可以使用基于索引的文件数据库，而不是关系数据库。类型的一个例子就是mail，其值将是一个电子邮件地址。

&emsp;&emsp;LDAP的信息是以树型结构存储的，在树根一般定义国家(c=CN)或域名(dc=com)，在其下则往往定义一个或多个组织 (organization)(o=Acme)或组织单元(organizational units) (ou=People)。一个组织单元可能包含诸如所有雇员、大楼内的所有打印机等信息。此外，LDAP支持对条目能够和必须支持哪些属性进行控制，这是有一个特殊的称为对象类别(objectClass)的属性来实现的。该属性的值决定了该条目必须遵循的一些规则，其规定了该条目能够及至少应该包含哪些属性。例如：inetorgPerson对象类需要支持sn(surname)和cn(common name)属性，但也可以包含可选的如邮件，电话号码等属性。

### LDAP 名词解释

| 简称 | 全称 | 用途 |
| :--- : | :---: | :---: |
| o | Organizaiton | 组织/公司 |
| ou | Organizaiton Unit | 组织单元 |
| c | Country | 国家 |
| dc | Domain Component | 域名 |
| sn | Suer Name | 真实名称 | 
| cn | Common Name | 常用名称 | 
| dn | Distiguished Name | 唯一标识名 | 
| uid | User ID | 用户标识 | 

<table>
<tr><th>简称</th><th>全称</th><th>用途</th></tr>
<tr><td>o</td><td>Organizaiton</td><td>组织/公司</td></tr>
<tr><td>ou</td><td>Organizaiton Unit</td><td>组织单元</td></tr>
<tr><td>c</td><td>Country</td><td>国家</td></tr>
<tr><td>dc</td><td>Domain Component</td><td>域名</td></tr>
<tr><td>sn</td><td>Suer Name</td><td>真实名称</td></tr>
<tr><td>cn</td><td>Common Name</td><td>常用名称</td></tr>
<tr><td>dn</td><td>Distiguished Name</td><td>唯一标识名</td></tr>
<tr><td>uid</td><td>User ID</td><td>用户标识</td></tr>
</table>

### AD域与LDAP的区别
&emsp;&emsp;Windows AD(Active Directory)域应该是LDAP的一个应用实例，而不应该是LDAP本身。Windows AD域的用户、权限管理应该是微软公司使用LDAP存储了一些数据来解决域控这个具体问题，AD域提供了相关的用户接口，我们可以把AD域当做微软定制的LDAP服务器。Active Directory先实现一个LDAP服务器，然后自己先用这个LDAP服务器实现了自己的一个具体应用。

### Spring Boot集成LDAP配置

- 在pom.xml中添加Maven依赖
```xml
<dependencys>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-ldap</artifactId>
    </dependency>
    <dependency>
        <groupId>com.unboundid</groupId>
        <artifactId>unboundid-ldapsdk</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
</dependencys>
```
&emsp;&emsp;其中，spring-boot-starter-data-ldap是Spring Boot封装的对LDAP自动化配置的实现，它是基于spring-data-ldap来对LDAP服务端进行具体操作的。

&emsp;&emsp;而unboundid-ldapsdk主要是为了在这里使用嵌入式的LDAP服务端来进行测试操作，所以scope设置为了test，实际应用中，我们通常会连接真实的、独立部署的LDAP服务器，所以不需要此项依赖。

&emsp;&emsp;在src/test/resources目录下创建ldap-server.ldif文件，用来存储LDAP服务端的基础数据，以备后面的程序访问之用。
```
dn: dc=didispace,dc=com
objectClass: top
objectClass: domain

dn: ou=people,dc=didispace,dc=com
objectclass: top
objectclass: organizationalUnit
ou: people

dn: uid=ben,ou=people,dc=didispace,dc=com
objectclass: top
objectclass: person
objectclass: organizationalPerson
objectclass: inetOrgPerson
cn: didi
sn: zhaiyongchao
uid: didi
userPassword: {SHA}nFCebWjxfaLbHHG1Qk5UU4trbvQ=
```

- application.yml中添加AD域配置
```yaml
spring:
  ldap:
    urls: ldap://192.168.1.1:389
    base: dc=example,dc=com
    username: administrator@example.com
    password: 111111
```

### References

* [LDAP的相关概念与objectClass介绍](https://www.zhukun.net/archives/8012)
* [Spring Boot中使用LDAP来统一管理用户信息](http://blog.didispace.com/spring-boot-ldap-user/)
* [LDAP快速入门](http://www.cnblogs.com/obpm/archive/2010/08/28/1811065.html)
* [Spring LDAP Reference](https://docs.spring.io/spring-ldap/docs/2.3.2.RELEASE/reference/)


