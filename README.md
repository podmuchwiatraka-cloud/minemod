# MineMod (Fabric 1.21.11)

## Budowanie
1. Zainstaluj JDK 21.
2. Wejdz na https://fabricmc.net/develop , wybierz 1.21.11 i skopiuj do gradle.properties
   aktualne: yarn_mappings, loader_version, fabric_api_version (oraz wersje Loom do build.gradle).
3. W folderze projektu: `gradle wrapper` (lub skopiuj gradlew z szablonu Fabric), potem:
   `./gradlew build`  -> jar w build/libs/
4. Do testow: `./gradlew runClient`

Menu: prawy Shift.
