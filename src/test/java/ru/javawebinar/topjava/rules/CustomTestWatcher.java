package ru.javawebinar.topjava.rules;

import org.junit.AssumptionViolatedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CustomTestWatcher extends Stopwatch {

    private static final Logger log = LoggerFactory.getLogger(CustomTestWatcher.class);
    private static final String LOG_MESSAGE_TEMPLATE = "Method %s successfully executed. Execution time: %d";
    private Map<String, Long> executionTimeStorage = new HashMap<>();

    @Override
    public long runtime(TimeUnit unit) {
        return super.runtime(unit);
    }

    @Override
    protected void succeeded(long nanos, Description description) {
        log.info(String.format(LOG_MESSAGE_TEMPLATE, description.getMethodName(), nanos));
        super.succeeded(nanos, description);
    }

    @Override
    protected void failed(long nanos, Throwable e, Description description) {
        super.failed(nanos, e, description);
    }

    @Override
    protected void skipped(long nanos, AssumptionViolatedException e, Description description) {
        super.skipped(nanos, e, description);
    }

    @Override
    protected void finished(long nanos, Description description) {
        log.info(String.format(LOG_MESSAGE_TEMPLATE, description.getMethodName(), nanos));
        executionTimeStorage.put(description.getMethodName(), nanos);
    }

    public Map<String, Long> getExecutionTimeStorage() {
        return executionTimeStorage;
    }

}
