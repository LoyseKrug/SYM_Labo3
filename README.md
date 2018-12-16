# SYM_Labo3 

Auteurs: Adrien Allemand, James Smith, Loyse Krug

## Question 2.4

```
Dans la manipulation ci-dessus, les tags NFC utilisés contiennent 4 valeurs textuelles codées en UTF-8 dans un format de message NDEF. Une personne malveillante ayant accès au porte-clés peut aisément copier les valeurs stockées dans celui-ci et les répliquer sur une autre puce NFC.
A partir de l’API Android concernant les tags NFC4, pouvez-vous imaginer une autre approche pour rendre plus compliqué le clonage des tags NFC ? Est-ce possible sur toutes les plateformes (Android et iOS), existe-il des limitations ? Voyez-vous d’autres possibilités ?
```

Les tags NFC ne possèdent pas de protéction contre le clonage, ils sont concu pour être lu librement, ainsi il est aisé de récupérer des informations d'un tag NFC et de les dupliquer pour les utiliser ailleurs.

Les tags NFC possèdent un identifiant unique qui pourrait permettre de les authentifier, mais cela n'empêche en rien d'extraitre quand même toutes les données du tag. Il existe même du hardware spécialisé capable de modifier ces identifiants uniques.

Il y a eu des tentatives d'ajouter des signatures digitales avec l'identifiant unique pour doubler la sécuriter, mais, de nouveau, un attaquant pourra se débrouiller pour reproduire la signature en en faisant une copie.

Les tags Type 2 de NXP possèdent un mécanisme pour empêcher le clonage en les 'scellant' pour empêcher tout ré-écriture par la suite. Seulement, cette méthode de faire est une propriété de NXP et ne peut pas être appliquée aux tags standards. 

Pour essayer de sécuriser des tags NFC, il est possible de passer par un système de clé assymétriques (Les smartcards/tags contactless utilisent cette méthode). Seuls les smartphones possèdant une clé secrète seraient capables de décrypter l'information reçue. Ainsi le man-in-the-middle pourrait extraire les données du tag, mais sans réussir à les déchiffrer. Comme il s'agit d'un système de clés, il ne devrait pas y avoir de limitation dans les plateformes pouvant utiliser cette méthode.

Sources: 

- https://security.stackexchange.com/questions/63483/how-do-nfc-tags-prevent-copying

- https://www.quora.com/Is-it-possible-to-prevent-cloning-of-an-NFC-tag-by-using-keys-with-the-PhoneGap-NFC-plug-in


## Question 3.2

```
Comparer la technologie à codes-barres et la technologie NFC, du point de vue d'une utilisation dans des applications pour smartphones, dans une optique :
- Professionnelle (Authentification, droits d’accès, stockage d’une clé)
- Grand public (Billetterie, contrôle d’accès, e-paiement)
- Ludique (Preuves d'achat, publicité, etc.)
- Financier (Coûts pour le déploiement de la technologie, possibilités de recyclage, etc.)
```

#### Professionelle

Pour des questions d'authentifications, s'il y a moyen d'utiliser des tags NFC, ceux-ci seront probablement plus appropriés. En effet, la posséssion d'un tag NFC peut déjà fournir une part de sécurisation (il sera néanmoins bien de l'associer à une authetifiaction par mot-de-passe, en cas de vol). Le NFC est facilement mis au porte clé ou dans le porte monnaie et nécessite une proximité pour pouvoir l'utiliser. 

Un QRcode, quant à lui, pourra être lu par n'importe qui possèdant une  application capable de le lire et ne fourni donc pas de sécurisaté supplémentaire. De plus, un QRcode qui s'abime peut devenir illisible et ainsi empêcher des authentifications légitimes.  

#### Grand public

Le QR Code coûte pas cher, et est facilement imprimable sur du papier, ce qui engendre de bien moindre coûts, notemment pour l'impression de billets; de plus, la plupart des téléphones possèdes un appareil photo mais certains n'ont pas accès à la lecture de NFC. 

Pour l'e-paiement, par contre, la proximité que demande le NFC offre en général une plus grande sécurisation, et une simplification de la transaction monnétaire. (un QRcode demande à être scanné et enverra sur une page qui demandera probablement encore une confirmation pour opérer la transaction)

#### Ludique

Pour tout ce qui est information du grand publique et activité ludique, le QRcode aura potentiellement plus de portée. Le NFC coute un peu plus cher et est plus difficile à mettre en place. De plus, il est possible de personnaliser les QR codes avec des images au centre, ce qui peut attirer l'oeil pour des publicitaires.

#### Financier

Le QR Code est à usage unique mais coute le coût d'une impression. Ce qui est un prix négligeable. Il est aussi possible de les mettre sur des pages web ce qui le rends gratuit et accéssible depuis partout.

Le tag NFC à un coût à la fabrication mais on peut changer les valeurs, en avoir plusieurs et l'utiliser pour différentes opérations (SwissPass).

## Question 4.2

```
Les iBeacons sont très souvent présentés comme une alternative à NFC. Pouvez-vous commenter cette affirmation en vous basant sur 2-3 exemples de cas d’utilisations (use-cases) concrets (par exemple e-paiement, second facteur d’identification, accéder aux horaires à un arrêt de bus, etc.).
```

#### E-paiements

Le principe bien des e-paiements avec NFC c'est de pouvoir approcher sa carte ou son natel du lecteur à la caisse et de faire le paiment, c'est pratique et rapide. Seulement, tous les smartphones ne possèdent pas de lecteur de NFC, ce qui est encore un frein à ce moyen de paiement (bien que déjà beaucoup utilisé). De plus, le paiement par NFC demande encore au client de s'approcher physiquement de la caisse, ce qui peut impliquer un possible temps d'attente dans une file de clients. 

Les iBeacons pourraient envisager les achats autrement, un client entrerait dans un magasin et capterait un beacon qui pourrait lui donner les informations nécessaires pour établir une communication avec un service (caisse) du magasin. Pour chaque article pris ou reposé, l'ardoise pourrait se mettre à jour et, une fois sorti du magasin et le signal du beacon quitté, le paiement pourrait se faire automatiquement, permettant aux client d'éviter les désagréments d'une file d'attente. Cependant ce système nécessiterait l'insatallation de beacons dans les magasins, d'accès au Bluetooth pour les clients et d'intallation d'une application qui gérerait toutes les communications (application qui devrait être sécurisée par ailleurs et qui devrait être capable de gérer les sitations de panne). Ainsi, les iBeacons pourraient être prometteurs dans le domaine de l'e-paiment, mais il faudrait encore mettre en place toute l'infrastructure pour rendre leur utilisation aisée et sécurisée.

Source: https://blog.beaconstac.com/2015/12/beacons-vs-nfc-which-payment-technology-should-your-business-use/

#### Arrêts de bus

Le iBeacon permettent de diffuser l'information à touts les personnes sur l'arrêt de bus en même temps alors que le NFC demandera a chaque passager d'approcher leur natel individuellement. Pour le commun des arrêts de bus ça ne posera pas de problème mais si c'est un arrêt près d'une gare a une heure de pointe il risque d'y avoir une file d'attente.

#### 2-factor auth

Il s'agit de nouveau d'une question de proximité. Pour une double authentification où l'on devrait se rendre de toute manière à une borne, l'utilisation du tag NFC, semble être une bonne alternative. Cela serait de nouveau pour une question déplacement à éviter que les iBeacons pourraient être une bonne alternative vu la portée de leur signal. 

Néanmoins, de nouveau, pour l'utilisation de beacons, une installation préliminaire d'une application, ainsi qu'une première authentification manuelle semblent inévitables. 

#### En conclusion

Dans tous les cas, pour obtenir une information, les iBeacons semblent être de bonnes alternatives, mais pour tous les cas d'actions sécurisées, la liberté de mouvement qu'ils offriraient  se ferait aisément au détriment d'une confirmation de la dite action (à laquelle on est beaucoup plus attentifs lorsqu'on a dû faire le geste de payer de s'authentifier)



## Question 5.2

```
Une fois la manipulation effectuée, vous constaterez que les animations de la flèche ne sont pas fluides, il va y avoir un tremblement plus ou moins important même si le téléphone ne bouge pas.
Veuillez expliquer quelle est la cause la plus probable de ce tremblement et donner une manière (sans forcément l’implémenter) d’y remédier.
```

La cause être qu'il y a beaucoup de perturbation au niveau magnétique autour de nous. Il y a des wifis, des ordinateurs, du métal, des aiments un peut partout qui perturbe nos capteurs et la terre elle même possède un champ gravitationnel et champ magnétique très hétérogènes. 

Une façon de résoudre ceci serait de travailler sur des moyennes des données récupéré. Ca éviterais ce tremblement tous en donner des informations précise. 

Il serait aussi possible de limiter la vélocité augulaire maximale de l'aiguille. Cette dernière bougerait alors avec une certaine inertie, mais éviterait les tremblements.