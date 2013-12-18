package org.zeroturnaround.logs;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.helpers.MarkerIgnoringBase;

public class OpportunisticLogger extends MarkerIgnoringBase {

  public static enum Level {
    ERROR(0), WARN(1), INFO(2), DEBUG(3), TRACE(4);

    private int level;

    private Level(int lvl) {
      this.level = lvl;
    }
  }

  private final Logger delegate;

  public final Level triggerLevel;

  private LinkedList<String> messages = new LinkedList<String>();
  private volatile boolean triggered;

  public OpportunisticLogger(Logger delegate, Level triggerLevel) {
    if (delegate == null) {
      throw new IllegalArgumentException("Delegate logger shouldn't be null");
    }
    this.delegate = delegate;
    this.triggerLevel = triggerLevel;
  }

  private void tryTriggering(Level lvl) {
    if (!triggered && lvl.level <= triggerLevel.level)
      trigger();
  }

  private void trigger() {
    for (String msg : messages) {
      delegate.info(msg);
    }
    triggered = true;
  }

  private void log(String msg) {
    if (triggered) {
      delegate.info(msg);
    }
    else {
      messages.add(msg);
    }
  }

  public boolean isTraceEnabled() {
    return true;
  }

  public void trace(String msg) {
    tryTriggering(Level.TRACE);
    log(String.format(msg));
  }

  public void trace(String format, Object arg) {
    tryTriggering(Level.TRACE);
    log(String.format(format, arg));
  }

  public void trace(String format, Object arg1, Object arg2) {
    tryTriggering(Level.TRACE);
    log(String.format(format, arg1, arg2));
  }

  public void trace(String format, Object... arguments) {
    tryTriggering(Level.TRACE);
    log(String.format(format, arguments));
  }

  public void trace(String msg, Throwable t) {
    tryTriggering(Level.TRACE);
    log(msg + ": " + t.getMessage());
  }

  public boolean isDebugEnabled() {
    return true;
  }

  public void debug(String msg) {
    tryTriggering(Level.DEBUG);
    trace(msg);
  }

  public void debug(String format, Object arg) {
    tryTriggering(Level.DEBUG);
    trace(format, arg);
  }

  public void debug(String format, Object arg1, Object arg2) {
    tryTriggering(Level.DEBUG);
    trace(format, arg1, arg2);
  }

  public void debug(String format, Object... arguments) {
    tryTriggering(Level.DEBUG);
    trace(format, arguments);
  }

  public void debug(String msg, Throwable t) {
    tryTriggering(Level.DEBUG);
    trace(msg, t);
  }

  public boolean isInfoEnabled() {
    return true;
  }

  public void info(String msg) {
    tryTriggering(Level.INFO);
    trace(msg);
  }

  public void info(String format, Object arg) {
    tryTriggering(Level.INFO);
    trace(format, arg);
  }

  public void info(String format, Object arg1, Object arg2) {
    tryTriggering(Level.INFO);
    trace(format, arg1, arg2);
  }

  public void info(String format, Object... arguments) {
    tryTriggering(Level.INFO);
    trace(format, arguments);
  }

  public void info(String msg, Throwable t) {
    tryTriggering(Level.INFO);
    trace(msg, t);
  }

  public boolean isWarnEnabled() {
    return true;
  }

  public void warn(String msg) {
    tryTriggering(Level.WARN);
    trace(msg);
  }

  public void warn(String format, Object arg) {
    tryTriggering(Level.WARN);
    trace(format, arg);
  }

  public void warn(String format, Object... arguments) {
    tryTriggering(Level.WARN);
    trace(format, arguments);
  }

  public void warn(String format, Object arg1, Object arg2) {
    tryTriggering(Level.WARN);
    trace(format, arg1, arg2);
  }

  public void warn(String msg, Throwable t) {
    tryTriggering(Level.WARN);
    trace(msg, t);
  }

  public boolean isErrorEnabled() {
    return true;
  }

  public void error(String msg) {
    tryTriggering(Level.ERROR);
    trace(msg);
  }

  public void error(String format, Object arg) {
    tryTriggering(Level.ERROR);
    trace(format, arg);
  }

  public void error(String format, Object arg1, Object arg2) {
    tryTriggering(Level.ERROR);
    trace(format, arg1, arg2);
  }

  public void error(String format, Object... arguments) {
    tryTriggering(Level.ERROR);
    trace(format, arguments);
  }

  public void error(String msg, Throwable t) {
    tryTriggering(Level.ERROR);
    trace(msg, t);
  }

}
