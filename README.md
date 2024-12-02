# CY Board
Voici notre rendu pour le projet JEE du premier semestre de l'année 2024-2025. Les contributeurs sont tous en deuxième année du cycle ingénieur (Filière Génie des Systèmes Informatiques ou GSI). Ce projet est une application vous permettant de :
- Gérer une liste de matières, cours, notes et d'utilisateurs
- Faire des recherches sur la liste d'utilisateurs en fonction d'une entrée de texte, de filtres et de leurs permissions
- Générer votre moyenne si vous êtes connecté en tant qu'étudiant
- Afficher votre profil avec vos informations
- Afficher le profil de n'importe quel utilisateur si vous êtes connecté en tant qu'administrateur

## Installation
Pour faire tourner notre site, il vous faudra installer **Apache Tomcat** et le configurer sur votre éditeur de code : https://tomcat.apache.org/download-10.cgi <br><br>
Si votre configuration est correcte, vous devrez directement arriver sur la page `index.jsp`, sinon pensez à vérifier l'**Application Context** de votre artéfact dans **Deployement**.  
<br>
Maintenant vous devez configurer pour **Hibernate** le fichier `hibernate.cfg.xml` qui contient les informations sur votre base de données.  
Tout d'abord, nous avons utilisé un driver **MySQL** donc si vous utiliser un autre service de base de données, il faut changer la propriété `hibernate.connection.driver_class` avec le driver de votre choix.  
<br>

Ensuite, il faudra modifier les propriétés suivantes:
1. `hibernate.connection.url` : C'est le nom de votre base de donnée, la valeur doit ressembler à **jdbc:mysql://localhost:3306/VotreBaseDeDonnée** pour MySQL.
2. `hibernate.connection.username` : C'est votre nom d'utilisateur pour la session MySQL, la valeur doit ressembler à **root** par exemple.
3. `hibernate.connection.password`: C'est votre mot de passe pour la session MySQL, la valeur doit ressembler à **cytech0001** par exemple.

Enfin, pour récupérer les données de `Test.sql`, vous pouvez soit aller sur la catégorie Subject sur le site pour créer les tables dynamiquement ou directement ouvrir MySQL et faire `SOURCE Tables.sql;`  
Et pour mettre les données dans les différentes tables que vous avez générées, allez sur MySQL et faites `SOURCE Test.sql;` dans **la base choisie pour Hibernate** (i.e VotreBaseDeDonnée)
Avec ces configurations, l'application devrait alors être opérationnelle.

## Utilisation
Utiliser notre site est très simple :
* Si vous n'êtes pas connecté, vous n'aurez accès qu'à la liste des matières
* Si vous êtes connecté en tant qu'étudiant, vous pouvez consulter votre profil, vos notes et moyennes, les cours et matières disponibles
* Si vous êtes connecté en tant qu'enseignant, vous pouvez consulter votre profil, les cours et matières disponibles
* Si vous êtes connecté en tant qu'admin, vous pourrez tout consulter, faire des recherches sur les utilisateurs

## License
Ce projet est sous license MIT. Cela veut dire globalement que vous pouvez faire un usage personnel ou commercial de code en y apportant les modifications que vous souhaitez.
