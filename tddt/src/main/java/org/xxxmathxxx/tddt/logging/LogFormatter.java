package org.xxxmathxxx.tddt.logging;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**This Formatter specifies how the log entries look. It overrides the default formatting which includes class-identifier that are hardly useful.
 * @author xxxMathxxx 2016
 */
public class LogFormatter extends Formatter {
    /* (non-Javadoc)
     * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
     */
    @Override
    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder();
        sb.append(record.getLevel()).append(':');
        sb.append(record.getMessage()).append('\n');
        return sb.toString();
    }    

}
