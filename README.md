# 快速开始

## 0.引入sdk

```java
<dependency>
    <groupId>io.github.isMrxxx</groupId>
    <artifactId>mrxapi-client-sdk</artifactId>
    <version>0.0.1</version>
</dependency>
```

## 1.从个人中心获取开发者密钥对

```java
MrxApiClient mrxApiClient = new MrxApiClient("你的 accessKey","你的 secretKey");
```

## 2.方法2：通过配置注入对象

修改配置：

```java
mrxapi:
  client:
    access-key: 你的 accessKey
    secret-key: 你的 secretKey
```

## 3.构造请求参数

```Java
//中医药问答Gpt 示例代码
MrxApiClient mrxApiClient = new MrxApiClient("你的 accessKey","你的 secretKey");
Gpt gpt = new Gpt();
gpt.setQuestion("草药的种类");
try {
    String s = "";
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = objectMapper.readTree(mrxApiClient.getGptByGet(gpt));
    // 获取"data"字段下的"content"数据
    String content = jsonNode.get("data").get("content").asText();
    s += content;
    System.out.println(s);
} catch (Exception e) {
    e.printStackTrace();
}
```
