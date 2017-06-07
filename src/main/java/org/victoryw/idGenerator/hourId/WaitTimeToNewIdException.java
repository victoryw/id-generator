package org.victoryw.idGenerator.hourId;

public class WaitTimeToNewIdException extends RuntimeException {
    public WaitTimeToNewIdException(final long lastTimestamp, final long sequenceMask) {
        super(String.format("%s is run over %d", lastTimestamp, sequenceMask));
    }
}
