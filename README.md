# KAS - Konference Administrations System

Dette projekt er mit første semesterprojekt på Datamatikeruddannelsen. Formålet var at designe og implementere et system til administration af internationale konferencer, hotelbookinger og udflugter.

## Projektbeskrivelse
Systemet erstatter manuelle tilmeldingsblanketter med en digital løsning, der automatiserer håndteringen af deltagere, ledsagere og komplekse prisberegninger. Projektet har haft særligt fokus på korrekt implementering af klassemodeller og forretningslogik.

### Hovedfunktioner
* **Konferencestyring:** Oprettelse og administration af konferencer med specifikke lokationer, datoer og priser.
* **Tilmeldingssystem:** Håndtering af deltagere (herunder foredragsholdere med særregler) og deres ledsagere.
* **Hotel- og Udflugtsbooking:** Mulighed for at tilknytte hoteller til konferencer og booke specifikke udflugter for ledsagere.
* **Avanceret Prisberegning:** Automatisk beregning af samlede omkostninger baseret på:
    * Konferencegebyr (undtaget for foredragsholdere).
    * Hotelovernatninger med tillæg (f.eks. for ledsager og ekstra services).
    * Tilmeldte udflugter.
* **Administrativ Oversigt:** Generering af lister over deltagere for specifikke hoteller og udflugter.

## Teknisk Stack
* **Sprog:** Java (JDK)
* **GUI:** JavaFX (brugergrænseflade til administration og tilmelding)
* **Arkitektur:** Layered Architecture (GUI, Controller, Model, Storage)
* **Værktøjer:** IntelliJ IDEA, Git, UML (Klassediagrammer)

## Udviklingsfokus
Som det første store projekt på uddannelsen har fokus været på:
1. **Objektorienteret Design:** Korrekt brug af nedarvning (Person, Deltager, Ledsager) og associationer (1..* og 0..*).
2. **Controller Pattern:** Sikring af, at GUI'en ikke indeholder forretningslogik, men kommunikerer gennem en central Controller.
3. **Validering:** Implementering af input-validering for at sikre korrekte data ved tilmelding.
4. **Kodestandarder:** Fokus på læsbar kode, navngivningskonventioner og struktur.

## Forfattere
Karsten Kirkegaard, Simon Dideriksen, Anders Bo Jensen og Sidse Borch Mogensen
