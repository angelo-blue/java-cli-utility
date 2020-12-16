package org.mikev.sampleUtility;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

/**
 * Main class with CLI annotations
 * @author mikev
 */
public class App implements Callable<Void> {
  Logger log = LoggerFactory.getLogger(App.class);

  @Option(names = {"-h", "--help"}, usageHelp = true, description = "display this help message")
  private boolean usageHelpRequested;

  @Parameters(index = "0", description = "The file to describe")
  private File file;

  @Option(names = {"-v", "--verbose"}, description = "verbose logging")
  private boolean verbose;

  @Option(
      names = {"-m", "--message"},
      required = true,
      description = "just an example")
  private String message;

  public static void main(String[] args) {
    int exitCode = new CommandLine(new App()).setOptionsCaseInsensitive(true).execute(args);
    System.exit(exitCode);
  }

  public Void call() throws Exception {
    log.info("Running in utility with options: Verbose={}, Message={}", verbose, message);
    try {
      log.info("Full path={}", file.getCanonicalPath());
    } catch (IOException e) {
      log.error("Error running utility", e);
    }
    return null;
  }
}
