# Pattern Proxy – MatchMaking (Protection Proxy)

## But
Exemple inspiré de *Head First Design Patterns* : on contrôle l’accès à un objet (`PersonBean`)
selon le rôle de l’appelant.

- **Owner** : peut modifier ses intérêts, mais pas sa note.
- **Non-owner** : peut donner une note, mais ne peut pas modifier les intérêts.

Implémentation : **Proxy dynamique Java** (`java.lang.reflect.Proxy`) + `InvocationHandler`.

## Lancer
Si le wrapper Gradle n’est pas présent, copie-le depuis un autre projet (Strategy) :
```bash
cp -a ../strategy/strategy/gradlew .
cp -a ../strategy/strategy/gradlew.bat .
cp -a ../strategy/strategy/gradle .
chmod +x gradlew
```

Puis :
```bash
./gradlew clean run
```

## Structure
- `PersonBean` : interface
- `PersonBeanImpl` : objet réel
- `OwnerInvocationHandler` : règles propriétaire
- `NonOwnerInvocationHandler` : règles non-propriétaire
- `MatchMakingTestDrive` : démo
