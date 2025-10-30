# tennis

Simple Java Swing based tennis (ping-pong-style)

This is a simple demo with an optional RMI based gaming server.

Back in 2003 it started as a learning project under Java 5 with an Apache Ant `build.xml`.
Later I added an Apache Maven `pom.xml` and now it also supports a Gradle `build.gradle` build.

## Requirements

### To Build:
- **Java 17 or later** (required by Gradle 9.2.0)
- Gradle wrapper included (`gradlew`)

### To Run:
- **Java 8 or later** (the compiled JAR is Java 8 compatible)

**Note:** While the project compiles to Java 8 bytecode (source/target compatibility 1.8), you need Java 17+ installed to run the Gradle build process.

## Building the Project

### Using Gradle (recommended)

```bash
./gradlew build
```

This will:
- Compile all Java sources
- Run unit tests
- Create a fat JAR file in `build/libs/tennis-1.0.0-SNAPSHOT.jar` (includes all dependencies)

### Using Maven

```bash
mvn clean package
```

### Using Ant

```bash
ant compile
ant jar
```

## Running the Application

### Local Game (Single Machine, Two Players)

Play tennis with two players on the same computer using keyboard controls.

**Using the JAR file:**
```bash
./gradlew jar
java -jar build/libs/tennis-1.0.0-SNAPSHOT.jar
```

The JAR file is a "fat JAR" that includes all dependencies (TableLayout, SLF4J, Logback), so it can be run standalone.

**How to play:**
1. Select `Game → Login` from the menu
2. Enter a player name
3. Use keyboard controls:
   - **Player 1**: Arrow keys (↑/↓/←/→)
   - **Player 2**: Page Up/Page Down/F1/F2

### Remote Game (RMI-based Multiplayer)

Play tennis over the network using Java RMI.

#### Step 1: Start the RMI Server

On the server machine:

```bash
./gradlew jar
java -cp build/libs/tennis-1.0.0-SNAPSHOT.jar de.sepe.tennis.remote.server.ServerImpl
```

(Note: Use `-cp` not `-jar` since we need to specify the main class)

You should see:
```
RMI registry created on port 1099
tennisServer bound in registry
Server ready and waiting for connections...
Press Ctrl+C to stop.
```

The server will:
- Create an RMI registry on port 1099
- Bind the tennis server as "tennisServer"
- Keep running until you press Ctrl+C

#### Step 2: Start the Client(s)

On the client machine(s):

```bash
./gradlew jar
java -cp build/libs/tennis-1.0.0-SNAPSHOT.jar de.sepe.tennis.remote.gui.Tennis
```

**Note:** If the server is on a different machine, you'll need to update the hardcoded localhost reference in `Court.java:166` to point to the server's IP address or hostname.

## Architecture

The project contains two separate implementations:

### Local Package (`de.sepe.tennis.local`)
- Single-machine game
- Two players share one keyboard
- All game logic runs locally
- Classes: `Tennis`, `Court`, `Player`, `Ball`, `Controller`

### Remote Package (`de.sepe.tennis.remote`)
- Network-based multiplayer using Java RMI
- Server manages game sessions
- Clients connect to play
- Classes:
  - Server: `ServerImpl`, `Session`, `Player`, `Ball`
  - Client: `Tennis`, `Court`, `PlayerView`, `BallView`
  - Interfaces: `Server` (RMI remote interface)

## Troubleshooting

### RMI Registry Issues

If you see `Connection refused to host` errors:
- Make sure the server is running first
- Check that a firewall does not block port 1099
- The server creates its own RMI registry, so you don't need to run `rmiregistry` separately

### ClassNotFoundException with RMI

This is fixed in the current version.
The server creates the RMI registry in the same JVM, ensuring all classes are available.

**Note:** RMI stub classes are generated dynamically at runtime (since Java 5), so no explicit stub generation step is needed.

## Build Systems

The project supports three build systems for historical reasons:

- **Gradle** (`build.gradle`) - Recommended, most modern
- **Maven** (`pom.xml`) - Alternative build system
- **Ant** (`build.xml`) - Legacy build system from 2003

All three should produce equivalent results.

## License

Copyright © 2003 Sebastian Peters. All Rights Reserved.

