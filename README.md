spark-cdi-sample
================

Usage:

I've tried to use: gradle run, but it doesn't seem to work. It just not runs as expected!
So, a workaround is to run the org.jboss.weld.environment.se.StartMain class outside gradle, for example, running this class on your IDE, it just works! The method main of RoutesStarter execute as expected and all dependencies are injected.

Tentei usar o comando: gradle run, só que aparentemente não funciona. Ele não inicia o método main.
O que eu consegui fazer foi rodar a classe org.jboss.weld.environment.se.StartMain por fora do gradle, por exemplo rodando na IDE, funciona certinho! O método main da RoutesStarted é executado e todas as dependencias são injetadas.

Você pode querer criar um pacote da aplicação para executar como standalone, então é só você fazer:
Maybe you want to create a jar of your application to run as a standalone, if so, just do as following:

```
gradle distZip
cd build/distributions/  
unzip spark-sample-1.0.0-SNAPSHOT.zip
cd spark-sample-1.0.0-SNAPSHOT
cdbin
./spark-sample
```

gradle distZip creates a zip of your application. Just run the .sh or the bat generated, which is inside the bin folder and your application will run!
O gradle distZip cria um zip da sua aplicação. É só rodar o .sh ou o bat gerado, que está dentro da pasta bin que a sua aplicação vai rodar!