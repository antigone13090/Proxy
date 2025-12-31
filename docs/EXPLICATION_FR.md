# Pattern Proxy — Explication détaillée (FR)

## 1) Intention
Mettre un **intermédiaire** (proxy) entre le client et l’objet réel (*RealSubject*) pour :
- **contrôler l’accès** (droits, sécurité),
- **différer** la création (lazy loading),
- **ajouter** du comportement (cache, logs),
- **agir à distance** (remote proxy).

## 2) Problème typique
Tu veux empêcher certains appels selon le rôle (owner / non-owner), mais sans modifier `Person` partout.
Sans proxy, tu te retrouves avec des `if (role...)` dans tout le code client.

## 3) Principe
Le client manipule une **interface commune** :
- `Subject` (ex: `PersonBean`)
- `RealSubject` (objet réel)
- `Proxy` (implémente la même interface, délègue et bloque/autorise)

## 4) Dans ce projet
- Objet réel : `PersonBeanImpl` (les données de profil)
- Proxies dynamiques Java : `InvocationHandler`
  - **Owner proxy** : autorise modification des intérêts, bloque la note
  - **Non-owner proxy** : autorise la note, bloque la modification des intérêts

## 5) Déroulé du run
Point d’entrée : `MatchMakingTestDrive` (ou équivalent dans ton code)
1) Création du profil
2) Test via proxy owner : setInterests OK, setHotOrNotRating bloqué
3) Test via proxy non-owner : setHotOrNotRating OK, setInterests bloqué

## 6) Avantages
- Sécurité / contrôle d’accès centralisés
- Aucun changement dans l’interface côté client
- Extensible (nouveaux rôles / règles)

## 7) Inconvénients
- Complexité (surtout avec proxies dynamiques)
- Débogage moins direct (interception)

## 8) Erreurs classiques
- Proxies qui ne respectent pas exactement l’interface
- Trop de logique métier dans le proxy (il doit contrôler, pas remplacer le modèle)

## 9) Liens avec d’autres patterns
- **Decorator** : ajoute des responsabilités, mais sans contrôle d’accès
- **Adapter** : change l’interface, proxy conserve la même
- **Facade** : simplifie une API, proxy contrôle l’accès à une API
