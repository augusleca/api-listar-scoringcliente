package com.bcba.nosisensolnet.configs.logs.color;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import ch.qos.logback.core.pattern.color.ForegroundCompositeConverterBase;

public class LogLevelColorConfig extends ForegroundCompositeConverterBase<ILoggingEvent> {
    @Override
    protected String getForegroundColorCode(ILoggingEvent event) {
        Level level = event.getLevel();
        return switch (level.levelInt) {
            case Level.ERROR_INT -> ANSIConstants.RED_FG;
            case Level.WARN_INT -> ANSIConstants.YELLOW_FG;
            default -> ANSIConstants.DEFAULT_FG;
        };
    }
}
