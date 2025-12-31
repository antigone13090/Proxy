# Pattern Proxy — Explication détaillée (FR)

## 1) Intention
Contrôler l’accès à un objet en plaçant un **intermédiaire (proxy)** entre le client et l’objet réel.

Un proxy peut :
- sécuriser (droits, authentification)
- retarder la création (lazy)
- faire du distant (RPC/RMI)
- mettre en cache
- journaliser / mesurer / limiter

## 2) Problème typique
On veut fournir **la même interface** que l’objet réel mais :
- empêcher certaines actions selon l’utilisateur
- ajouter des règles (ex: “tu ne peux pas changer tes propres notes”)
- éviter d’instancier un objet coûteux tant qu’il n’est pas utilisé

## 3) Principe
- Le client parle à une **interface** (ex: `PersonBean`)
- L’objet réel implémente l’interface (ex: `PersonBeanImpl`)
- Le proxy implémente aussi l’interface, mais **filtre / délègue** :
  - autorise certaines méthodes
  - bloque d’autres (souvent via exception)
  - peut ajouter du code avant/après

## 4) Dans ce projet (Owner / Non-Owner Proxy)
Cas d’usage “dating service” (Head First) :
- **Owner proxy** : l’utilisateur peut modifier ses infos (interests), mais ne peut pas se mettre une note.
- **Non-owner proxy** : un autre utilisateur peut mettre une note, mais ne peut pas modifier les infos.

Implémentation typique :
- proxy dynamique Java via `InvocationHandler`
- interception des appels : `invoke(...)`
- règles : autoriser / refuser selon la méthode

## 5) Déroulé du run
Point d’entrée : `...TestDrive` (main)
1) Création d’un `PersonBean`
2) Création d’un proxy “owner” et tests (modifs autorisées / interdites)
3) Création d’un proxy “non-owner” et tests (rating autorisé / modifs interdites)
4) Affichage du résultat

## 6) Avantages
- Séparation des préoccupations : logique de contrôle isolée
- Ajout de sécurité sans modifier l’objet réel
- Transparent pour le client (même interface)

## 7) Inconvénients
- Complexité supplémentaire (proxies/handlers)
- Débogage parfois moins direct (appel intercepté)
- Beaucoup de proxies => code plus verbeux

## 8) Erreurs classiques
- Proxy qui ne respecte pas exactement l’interface (surprises)
- Règles dispersées dans plusieurs endroits au lieu d’un seul handler
- Exceptions non gérées côté client

## 9) Liens avec d’autres patterns
- **Decorator** : ajoute des responsabilités ; Proxy contrôle l’accès
- **Adapter** : change l’interface ; Proxy garde la même interface
- **State** : change le comportement selon état ; Proxy filtre selon contexte/droits
