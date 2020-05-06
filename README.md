# DBS
## Architecture: MVVM

## Language: Kotlin

## Libraries:
1. Dagger2
2. RxJava2
3. Retrofit2
4. AndroidX (ViewModel, LiveData)
5. JUnit
6. Mockito

## Modules:
1. app: contains Application class, article, detail and edit feature
2. article: contains business logic for list article feature
3. base: contains BaseActivity class
4. config: configuration scheduler
5. data: contains data model
6. detail: contains business logic for detail feature
7. network: configurate network with retrofit2 and okhttp
8. service: configurate article and detail service
9. testsupport: contains live data testing support for unit test
10. util: contains utility classes

## Configurate:
1. Api host: the host is in appConfiguration.gradle file, under this code
```
ext.urlConfiguration = [
        host : "https://146l9.mocklab.io"
]
```
So just update the host for connecting to your server.

## Features:
1. Fetching articles list from api /article and show them into a recyclerView
2. Fetching detail from api /article/{id} and native to detail screen
3. Edit detail
4. Edit detail and save, native to detail screen and show the editted text
5. Sorted articles list base on last update

## Test:
1.Unit test with JUnit and Mockito  
2.Espresso test with mockweb server and core espresso libirary

## Note:
The api that you provide is limited by number of request per days, that why i change to other api, please update it.
