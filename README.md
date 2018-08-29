Small homework application.  
App use in memory database so after restart all data will be erased.  
To run app:  
#With maven installed:  
```
mvn clean install spring-boot:run -Dspring-boot.run.profiles=DataGenenration  
```
#Maven wrapper:  
```
mvnw clean install spring-boot:run -Dspring-boot.run.profiles=DataGenenration  
```

or for windows  
```
mvnw.cmd clean install spring-boot:run -Dspring-boot.run.profiles=DataGenenration  
```

Rest endpoinds  
```
GET http://localhost:8080/rest/v1/loans - get applied loans
optional params(page and pageSize)

http://localhost:8080/rest/v1/loans?page=1&pageSize=5
```



```
POST http://localhost:8080/rest/v1/loans apply for loan
Request body:
{
	"amount": 2.94,
	"term": 26,
	"name": "Test2",
	"surname": "TestSurname",
	"personalId": "12554-12239"
}
```
