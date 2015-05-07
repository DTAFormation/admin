etape 1 : creer un user 

- lancer add-user.bat présent dans le bin
- créer un management user.


etape deux: configurer Wildfly

- aller dans le dossier de wildly 8.1.0.final
- copier le dossier "mysql" dans le dossier ..\wildfly-8.1.0.Final\modules\system\layers\base\com
- aller dans le dossier ..\wildfly-8.1.0.Final\bin
- lancer le "jboss-cli.bat"
- ecrire "connect"
- copier l'intérieur du fichier driver.txt dans la consolle.


etape 3 : configurer sur le localhost:9990

- lancer le stadealone.bat 
- allez dans configuration 
- faire "Add"
- Name: = ecommercedb
- JNDI Name: =  java:/jdbc/ecommercedb
- next
- Choisir msql
- next
- prendre l'url present dans le fichier persistance.xml
- utiliser le nom de root./ tester /valider.

etape 4: dans eclipse. mise ajour du persistance.xml.

- dans le fichier persistance.
- garder que les deux dernieres ligne du property.
- remplacer le RESSOURCE LOCAL en JTA
- ajouter en dessous de provider : <jta-data-source> VOTRE JNDI NAME </jta-data-source>