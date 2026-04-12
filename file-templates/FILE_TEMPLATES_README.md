# File Templates

Android Studio File Templates für das YD Android Tooling Stack.

> **Wichtig:** File Templates können nicht automatisch per Gradle-Dependency eingebunden werden.
> Android Studio liest Templates ausschließlich aus bestimmten Verzeichnissen auf dem Dateisystem.
> Diese README erklärt die zwei unterstützten Installationswege.

---

## Verfügbare Templates

| Template               | Erstellt                            | Verwendung              |
|------------------------|-------------------------------------|-------------------------|
| `YD Composable Screen` | `@Composable` Funktion + `@Preview` | Neue Screen-Komponente  |
| `YD ViewModel`         | `ViewModel` + `UiState` data class  | ViewModel mit StateFlow |
| `YD UseCase`           | UseCase mit `runCatching`           | Domain-Layer Logik      |
| `YD Repository`        | Repository Interface + Impl         | Data-Layer Abstraktion  |

---

## Installation

### Option A – Projektlokal via Git (empfohlen für Teams)

Kopiere den Inhalt des `fileTemplates/`-Ordners in das `.idea`-Verzeichnis deines Projekts:

```
MeinProjekt/
└── .idea/
    └── fileTemplates/
        └── internal/
            ├── YD Composable Screen.kt
            ├── YD ViewModel.kt
            ├── YD UseCase.kt
            └── YD Repository.kt
```

Committe die Dateien ins Repository. Alle Teammitglieder bekommen die Templates automatisch beim
nächsten `git pull`.

Stelle sicher dass `.idea/fileTemplates/` **nicht** in der `.gitignore` steht:

```gitignore
# .gitignore
.idea/*
!.idea/fileTemplates       # ← diese Zeile hinzufügen
```

---

### Option B – Global für alle Projekte

Kopiere die Template-Dateien in das globale Android Studio Konfigurationsverzeichnis.

**macOS:**

```bash
cp fileTemplates/internal/* \
  "~/Library/Application Support/Google/AndroidStudio2024.x/fileTemplates/internal/"
```

**Windows:**

```bash
cp fileTemplates/internal/* \
  "%APPDATA%\Google\AndroidStudio2024.x\fileTemplates\internal\"
```

**Linux:**

```bash
cp fileTemplates/internal/* \
  "~/.config/Google/AndroidStudio2024.x/fileTemplates/internal/"
```

> Ersetze `AndroidStudio2024.x` mit deiner tatsächlich installierten Version,
> z.B. `AndroidStudio2024.2` für Android Studio Ladybug.

Starte Android Studio neu damit die Templates geladen werden.

---

## Verwendung in Android Studio

Nach der Installation sind die Templates verfügbar unter:

```
Rechtsklick auf Package → New → [YD Composable Screen / YD ViewModel / ...]
```

Android Studio fragt nach dem Dateinamen – der Name wird automatisch als Klassenname verwendet.

---

## Template-Variablen

Alle Templates verwenden folgende eingebaute Android-Studio-Variablen:

| Variable          | Bedeutung                               |
|-------------------|-----------------------------------------|
| `${NAME}`         | Vom Nutzer eingegebener Dateiname       |
| `${PACKAGE_NAME}` | Package des ausgewählten Verzeichnisses |

---

## Neues Template hinzufügen

1. Neue `.kt`-Datei unter `fileTemplates/internal/` anlegen
2. Dateiname = Name des Templates wie er in Android Studio erscheint
3. Template-Variablen `${NAME}` und `${PACKAGE_NAME}` verwenden
4. Gemäß gewählter Installationsoption deployen
