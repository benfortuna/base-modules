package org.mnode.base.log.adapter;

import org.apache.commons.logging.Log;

public class JclAdapter extends AbstractLogAdapter {

    private final Log logger;
    
    public JclAdapter(Log logger) {
        this.logger = logger;
    }
    
    @Override
    protected void debug(String message) {
        logger.debug(message);
    }

    @Override
    protected void debug(String message, Throwable e) {
        logger.debug(message, e);
    }

    @Override
    protected void error(String message) {
        logger.error(message);
    }

    @Override
    protected void error(String message, Throwable e) {
        logger.error(message, e);
    }

    @Override
    protected void info(String message) {
        logger.info(message);
    }

    @Override
    protected void info(String message, Throwable e) {
        logger.info(message, e);
    }

    @Override
    protected boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    protected boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    @Override
    protected boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    @Override
    protected boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    @Override
    protected void warn(String message) {
        logger.warn(message);
    }

    @Override
    protected void warn(String message, Throwable e) {
        logger.warn(message, e);
    }

}
