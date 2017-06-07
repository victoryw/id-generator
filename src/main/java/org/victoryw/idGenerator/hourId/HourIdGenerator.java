package org.victoryw.idGenerator.hourId;

public class HourIdGenerator {

    private final long workerId;
    private final long sequenceMask;

    private long lastTimestamp;
    private long sequence;
    private static final int HOUR_PRECISION = 3600000;


    public HourIdGenerator(final long workerId, final int startSequence, final long lastTimestamp, final int sequenceMask) {
        this.workerId = workerId;
        this.sequence = startSequence;
        this.lastTimestamp = lastTimestamp;
        this.sequenceMask = sequenceMask;
    }


    public final HourIdElementDecorate getDecorate() {
        return nextId();
    }

    private synchronized HourIdElementDecorate nextId() {
        long timestamp = getNowTimestamp();

        getNewSequence(timestamp);

        lastTimestamp = timestamp;

        return new HourIdElementDecorate(sequence, workerId, lastTimestamp);
    }

    private void getNewSequence(final long timestamp) {
        if (lastTimestamp == timestamp) {
            sequence = sequence + 1;
        } else {
            sequence = 0;
        }
        if (sequence > sequenceMask) {
            throw new WaitTimeToNewIdException(timestamp, sequenceMask);
        }
    }

    private long getNowTimestamp() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new IllegalStateException("Clock moved backwards.");
        }
        return timestamp;
    }

    private long timeGen() {
        return (System.currentTimeMillis() / HOUR_PRECISION) * HOUR_PRECISION;
    }
}
