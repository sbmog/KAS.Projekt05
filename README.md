# KAS - Conference Management System

This project was my first semester project in the Computer Science program. The objective was to design and implement a system for managing international conferences, hotel bookings, and excursions.

## Project Description
The system replaces manual registration forms with a digital solution that automates the handling of participants, companions, and complex price calculations. The project focused specifically on the correct implementation of class models and business logic.

### Key Features
* **Conference Management:** Creation and administration of conferences with specific locations, dates, and pricing.
* **Registration System:** Handling of participants (including keynote speakers with special rules) and their companions.
* **Hotel and Excursion Booking:** Ability to link hotels to conferences and book specific excursions for companions.
* **Advanced Price Calculation:** Automatic calculation of total costs based on:
    * Conference fees (waived for keynote speakers).
    * Hotel stays with supplements (e.g., for companions and extra services).
    * Registered excursions.
* **Administrative Overview:** Generation of participant lists for specific hotels and excursions.

## Technical Stack
* **Language:** Java (JDK)
* **GUI:** JavaFX (user interface for administration and registration)
* **Architecture:** Layered Architecture (GUI, Controller, Model, Storage)
* **Tools:** IntelliJ IDEA, Git, UML (Class Diagrams)

## Development Focus
As the first major project of the program, the focus was on:
1. **Object-Oriented Design:** Correct use of inheritance (Person, Participant, Companion) and associations (1..* and 0..*).
2. **Controller Pattern:** Ensuring the GUI does not contain business logic but communicates through a central Controller.
3. **Validation:** Implementation of input validation to ensure correct data during registration.
4. **Coding Standards:** Emphasis on readable code, naming conventions, and structure.

## Authors
Karsten Kirkegaard, Simon Dideriksen, Anders Bo Jensen, and Sidse Borch Mogensen
