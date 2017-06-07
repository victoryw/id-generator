package org.victoryw.idGenerator.hourId;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class HourIdElementDecorate {
    private final long sequence;
    private final long workerId;
    private final long lastTimestamp;

    private static final DateFormat IDFORMATE = new SimpleDateFormat("yyMMddHH");

    public HourIdElementDecorate(final long sequence, final long workerId, final long lastTimestamp) {
        this.sequence = sequence;
        this.workerId = workerId;
        this.lastTimestamp = lastTimestamp;
    }

    public final String getEntityId(final String prefix) {
        return String.format("%s%s%d%05d", prefix, IDFORMATE.format(lastTimestamp), workerId, sequence);
    }
}

