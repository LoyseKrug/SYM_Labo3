# SYM_Labo3 

## Question 2.4

```
Dans la manipulation ci-dessus, les tags NFC utilisés contiennent 4 valeurs textuelles codées en UTF-8 dans un format de message NDEF. Une personne malveillante ayant accès au porte-clés peut aisément copier les valeurs stockées dans celui-ci et les répliquer sur une autre puce NFC.
A partir de l’API Android concernant les tags NFC4, pouvez-vous imaginer une autre approche pour rendre plus compliqué le clonage des tags NFC ? Est-ce possible sur toutes les plateformes (Android et iOS), existe-il des limitations ? Voyez-vous d’autres possibilités ?
```

- Les tags NFC (Comme défini par le https://nfc-forum.org/) n'a pas de protection contre le cloning. Ces tags sont conçu pour être lu librement (C'est pour ceci qu'il s'appelle NDEF messages). Tous le monde peut lire un message NDEF depuis un tag et le dupliquer sur un autre tag.

- Plusieurs tag NFC contiennent aussi un identifiant unique qui est pré-programmé par le fabriquant et ne peut pas être modifié. Ces idendentifiants uniques peuvent être utilisé uniquement pour identifié un tag unique. Mais ces donnée peuvent tous de même être extraite du tag. Du hardware spécialisé (Comme Proxmark)  et des tags prêt à l'emploie ou les attaquant arrive à changer cet identifiant unique.
  NXP a essayé de faire des tags qui se base sur l'ajout d'une signature digital et plus de l'identifiant unique mais rien n'empêche les hacker de faire une copy de cette signature static.

- Les smartcards/tags contactless font des échanges de clé asymétrique comme font les communications http. Beaucoup de ces smartcards sont construite sur la base de Java Card, et contiennent un microcontrolleur qui exécute une application customsée.

  Source: https://security.stackexchange.com/questions/63483/how-do-nfc-tags-prevent-copying


## Question 3.2

```
Comparer la technologie à codes-barres et la technologie NFC, du point de vue d'une utilisation dans des applications pour smartphones, dans une optique :
- Professionnelle (Authentification, droits d’accès, stockage d’une clé)
- Grand public (Billetterie, contrôle d’accès, e-paiement)
- Ludique (Preuves d'achat, publicité, etc.)
- Financier (Coûts pour le déploiement de la technologie, possibilités de recyclage, etc.)
```

### Professionelle

Il est difficile de sécuriser et d'utliser discrêtement un QR Code. Il est assez facile de le lire à distance et n'est pas facilement transportable. Le NFC est plus facilement mis au porte clé ou dans le porte monnaie et nécessite une proximité pour pouvoir l'utiliser.

### Grand public

Le QR Code coûte pas cher, et est facilement imprimable sur du papier. 
La plupart des téléphones possèdes un appareil photo mais pas tous possède le NFC.

### Ludique

Je pense que la partie ludique est très lié au grand publique. Le NFC coute un peu plus cher et est plus difficile à mettre en place. Il est possible de personnaliser les QR codes avec des images au centre.

### Financier

Le QR Code est à usage unique mais coute le coût d'une impression. Ce qui est un prix à négliger. Il est aussi possible de les mettre sur des pages web ce qui le rends gratuit et accéssible depuis partout.

Le tag NFC à un coût à la fabrication mais on peut changer les valeurs, en avoir plusieurs et l'utiliser pour différentes opérations (SwissPass).

## Question 4.2

```
Les iBeacons sont très souvent présentés comme une alternative à NFC. Pouvez-vous commenter cette affirmation en vous basant sur 2-3 exemples de cas d’utilisations (use-cases) concrets (par exemple e-paiement, second facteur d’identification, accéder aux horaires à un arrêt de bus, etc.).
```

#### e-paiements

Le principe bien des e-paiements avec NFC c'est de pouvoir approcher sa carte ou son natel du lecteur à la caisse et de faire le paiment, c'est pratique et rapide. Les iBeacons, de part leur portée ferait probablement payer le client 3 personne derrière nous ou alors demanderaient une confirmation depuis le téléphonne ce qui enlève une partie de l'intérêt du paiment sans contact. 

#### Arrêts de bus

Le iBeacon permettent de diffuser l'information à touts les personnes sur l'arrêt de bus en même temps alors que le NFC demandera a chaque passager d'approcher leur natel individuellement. Pour le commun des arrêts de bus ça ne posera pas de problème mais si c'est un arrêt près d'une gare ou a une heure de pointe il risque d'y avoir une file d'attente.

#### 2-factor auth





## Question 5.2

```
Une fois la manipulation effectuée, vous constaterez que les animations de la flèche ne sont pas fluides, il va y avoir un tremblement plus ou moins important même si le téléphone ne bouge pas.
Veuillez expliquer quelle est la cause la plus probable de ce tremblement et donner une manière (sans forcément l’implémenter) d’y remédier.
```

La cause être qu'il y a beaucoup de perturbation au niveau magnétique autour de nous. Il y a des wifis, des ordinateurs, du métal, des aiments un peut partout qui perturbe nos capteurs.
Une façon de résoudre ceci serait de travailler sur des moyennes des données récupéré. Ca éviterais ce tremblement tous en donner des informations précise.