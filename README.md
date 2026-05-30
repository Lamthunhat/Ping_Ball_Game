# Don't Drop It Adventure (Ping Ball Game)

A classic 2D arcade-style ping-pong adventure game built in Java using **Swing** and **AWT**. The game features smooth character and ball movement, collision physics, sound effects, background music, score tracking, high score system, and lives management.

---

## 🎮 Gameplay & Controls

Your goal is to keep the falling grenade (ball) in the air using the character (paddle) and prevent it from dropping below the screen.

- **Left Arrow Key**: Move the character to the left.
- **Right Arrow Key**: Move the character to the right.
- **P Key**: Pause / Resume the game.
- **Space Key**: Restart the game (when Game Over).
- **Escape Key**: Return to the main menu (when Game Over).

---

## 🛠️ Project Structure

The project follows a clean Model-View-Controller (MVC) architecture:

```text
src/main/java/
├── main/
│   └── Main.java                 # Game entry point
├── controllers/
│   ├── IGameController.java      # Interface for game control logic
│   └── GameController.java       # Core game controller managing state updates and collisions
├── models/
│   ├── GameConstants.java        # Centralized game constants (window dimensions, speed, size)
│   ├── GameState.java            # Manages current score, high score, game-over, and pause status
│   ├── Ball.java                 # Representation of the ball (grenade) and its physics/movement
│   └── Paddle.java               # Representation of the paddle (player character) and movement
├── views/
│   ├── GameWindow.java           # Main JFrame containing panels
│   ├── GamePanel.java            # Main gameplay rendering canvas (handles sprites drawing)
│   └── MenuPanel.java            # Interactive main menu screen
└── utils/
    └── SoundPlayer.java          # Audio manager for background music and sound effects
```

### 📂 Resources (`src/main/resources/`)
- `pic_transparent.png`: Spritesheet containing the game ball and character assets.
- `opensound.wav`: Background music track.
- `hit.wav`: Sound effect played upon successful ball-to-paddle collisions.
- `game_over.wav`: Sound effect played when the player runs out of lives.

---

## 🚀 How to Run the Game

### Prerequisites
- Java Development Kit (JDK) 8 or higher.
- Apache Maven (optional, but recommended for build automation).

### Method 1: Running with Maven (Command Line)
1. Clean and build the package:
   ```bash
   mvn clean package
   ```
2. Run the application:
   ```bash
   mvn exec:java -Dexec.mainClass="main.Main"
   ```

### Method 2: Running directly via Java Compiler (`javac` / `java`)
1. Compile the source code:
   ```bash
   javac -d target/classes -sourcepath src/main/java src/main/java/main/Main.java
   ```
2. Run the compiled application:
   ```bash
   java -cp target/classes main.Main
   ```
