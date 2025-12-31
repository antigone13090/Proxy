# Proxy (FR) – Explications détaillées

## 1) Idée du pattern Proxy
Un **Proxy** est un objet qui **présente la même interface** que l’objet réel,
mais qui **contrôle l’accès** (ou ajoute un comportement) avant de déléguer.

Variantes classiques :
- **Protection Proxy** : contrôle d’accès (droits)
- **Virtual Proxy** : chargement à la demande (lazy)
- **Remote Proxy** : objet distant (réseau)

Ici : **Protection Proxy**.

## 2) Règles de l’exemple
Objet réel : `PersonBeanImpl`

Méthodes :
- `setInterests(...)` : modif des intérêts
- `setHotOrNotRating(int)` : vote / note

Politique :
- Propriétaire : autorisé `setInterests`, interdit `setHotOrNotRating`
- Non-propriétaire : autorisé `setHotOrNotRating`, interdit `setInterests`/`setName`

## 3) Comment ça marche techniquement (Java Dynamic Proxy)
On crée un proxy à l’exécution :
- `Proxy.newProxyInstance(classLoader, interfaces, handler)`

Le **handler** reçoit chaque appel :
- si la méthode est autorisée → `method.invoke(realObject, args)`
- sinon → exception

Avantage : pas besoin d’écrire une classe proxy par cas d’usage.
