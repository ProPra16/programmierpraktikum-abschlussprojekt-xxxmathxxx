package org.xxxmathxxx.tddt.logging;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder();
        sb.append(record.getLevel()).append(':');
        sb.append(record.getMessage()).append('\n');
        return sb.toString();
    }    

}
