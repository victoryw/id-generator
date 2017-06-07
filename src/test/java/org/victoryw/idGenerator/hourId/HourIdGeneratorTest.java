package org.victoryw.idGenerator.hourId;

import org.junit.Assert;
import org.junit.Test;
import org.victoryw.ParallelRunFacility;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class HourIdGeneratorTest {
    @Test
    public void should_use_the_current_month_day_hour_and_linear_growth_sequence(){
        int workerId = 1;
        int startSequence = 100;
        int hourPrecision = 1000 * 60 * 60;
        long currentTimePrecisionToHour = (System.currentTimeMillis() / hourPrecision) * hourPrecision;
        HourIdGenerator entityIdGenerator = new HourIdGenerator(workerId, startSequence, currentTimePrecisionToHour, 99999);

        String timestamp = new SimpleDateFormat("yyMMddHH").format(currentTimePrecisionToHour);
        String prefix = "01";
        String expectedResult = String.format("%s%s%d%05d", prefix, timestamp, workerId, startSequence + 1);
        Assert.assertEquals(expectedResult, entityIdGenerator.getDecorate().getEntityId(prefix));

    }

    @Test(expected = WaitTimeToNewIdException.class)
    public void should_throw_wait_exception_when_generate_over_max(){
        int workerId = 1;
        int maxSequenceInHour = 99999;
        int hourPrecision = 1000 * 60 * 60;
        long currentTimePrecisionToHour = (System.currentTimeMillis() / hourPrecision) * hourPrecision;
        HourIdGenerator entityIdGenerator = new HourIdGenerator(workerId, maxSequenceInHour, currentTimePrecisionToHour, maxSequenceInHour);

        entityIdGenerator.getDecorate();
    }

    @Test
    public void should_not_generate_same_entity_id_in_10000_when_concurrence() {
        HourIdGenerator entityIdGenerator = new HourIdGenerator(1, 1, -1L, 99999);

        System.out.println("--------多线程测试不重复------------------");
        Set<String> idSet = Collections.synchronizedSet(new HashSet<>());


        int maxCount = 100000;

        ParallelRunFacility.ParallelTestContainerMethod(() -> {
            HourIdElementDecorate newId = entityIdGenerator.getDecorate();

            String val = newId.getEntityId("01");

            if (!idSet.contains(val)){
                idSet.add(val);
            }
        }, maxCount);

        Assert.assertEquals(maxCount,idSet.size());
    }

}
