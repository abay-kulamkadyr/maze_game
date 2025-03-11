# Nazareth: Kiki's Search for Za'atar

Welcome to **Nazareth**, a game where Kiki embarks on a perilous journey through dungeons in search of ancient Za'atar. We hope you enjoy the adventure and challenges presented in this project.

---

## Table of Contents

- [Overview](#overview)
- [Requirements](#requirements)
- [Installation](#installation)
- [Building the Game](#building-the-game)
- [Running the Game](#running-the-game)
- [Testing the Game](#testing-the-game)
- [Packaging a Self-Contained Jar](#packaging-a-self-contained-jar)
- [Additional Notes](#additional-notes)

---

## Overview

In **Nazareth**, the protagonist Kiki must overcome obstacles and fight enemies in a dungeon to obtain the elusive Za'atar. The game is built using Java with Slick2D and LWJGL libraries, and Maven is used to manage dependencies and build the project. The final JAR is self-contained—it automatically extracts and loads the required native libraries at runtime.

---

## Requirements

- **Java 8:**
  This project must be compiled and run using Java 8.

- **Maven:**
  Ensure Maven is installed on your system. See [Maven Installation](https://maven.apache.org/install.html) for instructions.

- **Operating System:**
  The game supports Linux, Windows, and macOS. Native libraries for each OS are packaged and automatically extracted at runtime.

- **Executable JAR:**
  An executable JAR is included in the root folder of the project for convenience.

---

## Installation

### 1. Clone the Repository

Clone the project repository from GitHub (or your version control system):

```bash
git clone https://github.com/abay-kulamkadyr/maze_game.git
```

### 2. Install Maven (if needed)

Follow the official [Maven Installation Guide](https://maven.apache.org/install.html).

---

## Building the Game

1. Open a terminal or command prompt and navigate to the project root (the directory containing the `pom.xml` file).

2. Run the following command to clean and build the project:

```bash
mvn clean install
```

Maven will download all required dependencies (including native libraries) and run tests. On a successful build, the compiled classes and packaged JAR(s) will be generated in the `target` directory.

---

## Running the Game

### Option 1: Run via Maven (Development Mode)

1. Ensure you have completed the build step.

2. From the project root, run:

```bash
mvn compile exec:java -Dexec.mainClass=RunGame
```

### Option 2: Run the Executable JAR

An executable JAR file is included both in the `target` directory and in the project root. To run the game using the self-contained JAR, simply execute:

```bash
java -jar Nazareth-2.jar
```

*Note:* The JAR automatically extracts all necessary native libraries (LWJGL, JInput, OpenAL, etc.) at runtime, so no extra command-line options are needed.

---

## Testing the Game

To run the unit tests, execute the following command from the project root:

```bash
mvn clean test
```

Maven will compile the tests and display the results in the console.

---

## Packaging a Self-Contained JAR

The final JAR is created using the Maven Shade Plugin, which merges your project classes with all dependencies (including native libraries) into a single executable JAR.

1. From the project root, run:

```bash
mvn clean package
```

2. The packaged JAR, typically named `Nazareth-2.jar`, will be located in the `target` directory and also included in the project root.

3. To run the JAR, use:

```bash
java -jar Nazareth-2.jar
```

---

## Additional Notes

- **Java Version:**
  This project must be compiled and run using Java 8. Newer Java versions may not be compatible with Slick2D and some native libraries used in the project.

- **Native Libraries:**
  Maven downloads the native libraries into your local repository (typically in `~/.m2/repository/`), and the Maven Shade Plugin merges them into the final JAR. The `NativeLoader` class automatically extracts these files to a temporary directory at runtime and sets the necessary system properties (such as `org.lwjgl.librarypath` and `net.java.games.input.librarypath`).

- **Cross-Platform Support:**
  The game is designed to run on Linux, Windows, and macOS. The `NativeLoader` extracts and loads the appropriate native files based on the operating system.

- **Executable JAR:**
  An executable JAR file (`Nazareth-2.jar`) is included in the root folder for ease of use. Users can run it without any extra configuration.

