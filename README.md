# rateExchange

此程式碼採用 spring-boot 框架寫成
啟動方式 :

1. ide -> 按照一般啟動方法 選擇 run java 即可
2. cmd -> mvn clean install ， java -jar ... 啟動

# 測試方法

--------------------------------------------------------------------

http://localhost:8080/
為入口，啟動成功後會回傳 hello

範例 :

http://127.0.0.1:8080/exchangeRate?source=USD&target=JPY&amount=$1,525