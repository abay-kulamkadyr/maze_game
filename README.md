# Nazareth: Kiki's Search for Za'atar

Welcome to **Nazareth**, a game where Kiki embarks on a perilous journey through treacherous dungeons in search of
ancient Za'atar. We hope you enjoy the adventure and challenges presented in this project.

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

In **Nazareth**, the protagonist Kiki must overcome obstacles and fight enemies in a dungeon to obtain the elusive
Za'atar. The game is built using **Java** with **Slick2D** and **LWJGL** libraries, and **Maven** is used to manage
dependencies and build the project. The final JAR is self-contained—it automatically extracts and loads the required
native libraries at runtime.

---

## Requirements

- **Java 8:** This project must be compiled and run using Java 8.
- **Maven:** Ensure Maven is installed on your system. See [Maven Installation](https://maven.apache.org/install.html)
  for instructions.
- **Operating System:** The game supports **Linux, Windows, and macOS**. Native libraries for each OS are packaged and
  automatically extracted at runtime.
- **Executable JAR:** An executable JAR is included in the root folder of the project for convenience.

---

## Installation

1. **Clone the Repository**

   Clone the project repository from GitHub (or your version control system):

   ```bash
   git clone https://github.com/abay-kulamkadyr/maze_game.git

    Install Maven (if not installed)

    Follow the official Maven Installation Guide.

Building the Game

    Open a terminal or command prompt and navigate to the project root (the directory containing the pom.xml file).

    Run the following command to clean and build the project:

    mvn clean install

Maven will download all required dependencies (including native libraries) and run tests. On a successful build, the
compiled classes and packaged JAR(s) will be generated in the target/ directory.
Running the Game
Option 1: Run via Maven (for development)

    Ensure you have completed the build step.

    From the project root, run:

    mvn compile exec:java -Dexec.mainClass=RunGame

Option 2: Run the Executable JAR

An executable JAR file is included in the root folder and in the target/ directory. To run the game using the
self-contained JAR, simply execute:

java -jar Nazareth-2.jar

Note: The JAR is self-contained—it extracts all necessary native libraries (LWJGL, JInput, OpenAL, etc.) automatically
at runtime, so you don't need to set any extra command-line options.
Testing the Game

To run the unit tests, execute the following command from the project root:

mvn clean test

Maven will compile the tests and display the results in the console.
Packaging a Self-Contained JAR

The final JAR is created using the Maven Shade Plugin, which merges your project classes with all dependencies (
including native libraries) into a single executable JAR.

To create the self-contained JAR, run:

mvn clean package

The packaged JAR, typically named Nazareth-2.jar, will be located in the target/ directory and also included in the
project root.

To run the JAR, use:

java -jar Nazareth-2.jar

Additional Notes
Java Version

This project must be compiled and run using Java 8. Newer Java versions may not be compatible with Slick2D and some
native libraries used in the project.
Native Libraries

    Maven downloads the native libraries into your local repository (~/.m2/repository/...), and the Maven Shade Plugin merges them into the final JAR.
    The NativeLoader class automatically extracts these files to a temporary directory at runtime and sets the necessary system properties so that LWJGL and JInput can locate them.

Cross-Platform Support

The game is designed to run on Linux, Windows, and macOS. The NativeLoader extracts the appropriate native files based
on the operating system.
Executable JAR

An executable JAR file (Nazareth-2.jar) is included in the root folder for ease of use. Users can simply run it without
any extra configuration.