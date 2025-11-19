# Members & Stopwatch (JavaFX)

A small JavaFX application that manages a list of members and includes a basic stopwatch tool.

## Features
- Add and save members (first name, last name, phone, address)
- Members stored locally in `members.csv`
- Styled member table with automatic updates
- Stopwatch with start, stop, and reset
- Simple navigation between views

## Project Structure
```
src/
 └── com.nilsson
     ├── application/      # Main JavaFX entry point
     ├── controller/       # UI controllers
     ├── model/            # Data model classes
     ├── repository/       # CSV file handling
     └── view/             # JavaFX views (Menu, Members, Stopwatch)
resources/
 └── com/nilsson/styling/  # CSS styles
```

## How It Works
- Members are loaded from and written to `members.csv` using `MemberRepository`.
- MembersView displays a form and a table; MembersController handles saving.
- StopwatchView is updated using an AnimationTimer controlled by StopwatchController.
- All screens share a common CSS theme.

## Run the Project
Requires:
- Java 17+
- JavaFX

Run using:
mvn javafx:run

## License
MIT
