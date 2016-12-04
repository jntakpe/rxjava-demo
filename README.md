# RxJava démo

Projet servant d'introduction à la programmation réactive en Java.

## Utilisation

#### Serveur de mock

Un serveur de mock *WireMock* permet de simuler des appels sur le réseau ainsi que les latences.

Pour démarrer le serveur de mock, dans le répertoire [mock-server](mock-server), exécutez le script :
 * [startMockServer.sh](mock-server//Users/jOSS/dev/rxjava-demo/mock-server/startMockServer.sh) sur Unix ou Linux
 * [startMockServer.bat](mock-server//Users/jOSS/dev/rxjava-demo/mock-server/startMockServer.bat) sur Windows

#### Application réactive
 
L'application de démo réactive permet d'initier aux concepts clés de la programmation réactive en utilisant 
[RxJava](https://github.com/ReactiveX/RxJava/wiki).

Le démarrage de l'application peut se faire de différentes manières :
* en exécutant la classe [ReactiveAppApplication](reactive-app/src/main/java/com/github/jntakpe/reactiveapp/ReactiveAppApplication.java)
* via Maven avec la commande ``mvn spring-boot:run``
* via le jar exécutable avec la commande ``java --jar CHEMIN_DE_MON_JAR``

Les travaux pratiques seront vérifiés via exécution des tests unitaires présents dans [ce répertoire](reactive-app/src/test/java).