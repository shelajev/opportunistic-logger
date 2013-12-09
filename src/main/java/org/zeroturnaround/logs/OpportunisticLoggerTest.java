package org.zeroturnaround.logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeroturnaround.logs.OpportunisticLogger.Level;

public class OpportunisticLoggerTest {
  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(OpportunisticLoggerTest.class);
    OpportunisticLogger log = new OpportunisticLogger(logger, Level.WARN);

    log.info("Hello world!");
    System.out.println("See, no output before this line");
    log.warn("%s, people!", "Triggering");

    log.trace("This message is immediate!");
  }
}
