import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class NativeLoader {

  /**
   * Loads native libraries from the jar. Extracts natives into a temporary directory and sets
   * system properties for LWJGL and JInput.
   */
  public static void loadNatives() throws IOException {
    // Load libjawt, required by LWJGL.
    loadJAWT();

    // Create a temporary directory for native libraries.
    Path nativesDir = Files.createTempDirectory("lwjgl-natives");
    nativesDir.toFile().deleteOnExit();

    String os = System.getProperty("os.name").toLowerCase();
    if (os.contains("win")) {
      extractResource("/lwjgl64.dll", nativesDir);
      extractResource("/jinput-raw_64.dll", nativesDir);
      extractResource("/OpenAL64.dll", nativesDir);
    } else if (os.contains("mac")) {
      extractResource("/liblwjgl.dylib", nativesDir);
      extractResource("/libjinput-osx.jnilib", nativesDir);
      extractResource("/openal.dylib", nativesDir);
    } else {
      // Assume Linux (64-bit)
      extractResource("/liblwjgl64.so", nativesDir);
      extractResource("/libjinput-linux64.so", nativesDir);
      // For OpenAL, choose the 64-bit version (adjust if needed)
      extractResource("/libopenal64.so", nativesDir);
    }

    // Set system properties so that LWJGL and JInput can locate the natives.
    System.setProperty("org.lwjgl.librarypath", nativesDir.toAbsolutePath().toString());
    System.setProperty("net.java.games.input.librarypath", nativesDir.toAbsolutePath().toString());
    System.out.println(
        "Set org.lwjgl.librarypath and net.java.games.input.librarypath to "
            + nativesDir.toAbsolutePath());
  }

  /** Loads libjawt.so from the JDK so that LWJGL's dependency is met. */
  private static void loadJAWT() {
    String javaHome = System.getProperty("java.home");
    File jawtFile = new File(javaHome + "/lib/amd64/libjawt.so");
    if (!jawtFile.exists()) {
      // Try parent directory if java.home points to a JRE.
      jawtFile = new File(new File(javaHome).getParent() + "/lib/amd64/libjawt.so");
    }
    if (jawtFile.exists()) {
      System.load(jawtFile.getAbsolutePath());
      System.out.println("Loaded libjawt from: " + jawtFile.getAbsolutePath());
    } else {
      throw new UnsatisfiedLinkError("libjawt.so not found in expected locations.");
    }
  }

  /**
   * Extracts a resource from the jar to the given directory.
   *
   * @param resourcePath The path of the native resource in the jar (e.g., "/liblwjgl64.so")
   * @param destinationDir The directory to extract the file into.
   * @throws IOException if the resource is not found or an I/O error occurs.
   */
  private static void extractResource(String resourcePath, Path destinationDir) throws IOException {
    InputStream in = NativeLoader.class.getResourceAsStream(resourcePath);
    if (in == null) {
      throw new IOException("Native resource not found: " + resourcePath);
    }
    String fileName = resourcePath.substring(resourcePath.lastIndexOf('/') + 1);
    File outFile = new File(destinationDir.toFile(), fileName);
    try (OutputStream out = Files.newOutputStream(outFile.toPath())) {
      byte[] buffer = new byte[4096];
      int len;
      while ((len = in.read(buffer)) != -1) {
        out.write(buffer, 0, len);
      }
    }
    outFile.deleteOnExit();
    System.out.println("Extracted native " + fileName + " to " + outFile.getAbsolutePath());
  }
}
