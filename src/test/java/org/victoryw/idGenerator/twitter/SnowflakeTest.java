package org.victoryw.idGenerator.twitter;

import org.junit.Assert;
import org.junit.Test;
import org.victoryw.ParallelRunFacility;
import org.victoryw.idGenerator.twitter.Snowflake;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class SnowflakeTest {
    @Test
    public void should_generate_non_duplicated_id_when_parallel_call_each_single_node() {
        System.out.println("--------多线程测试不重复------------------");
        Set<Long> idSet = Collections.synchronizedSet(new HashSet<>());


        int maxCount = 100000;
        Snowflake snowflakeBuilder = new Snowflake(1);
        ParallelRunFacility.ParallelTestContainerMethod(() -> {
            long id = 0;
            try {
                id = snowflakeBuilder.nextId();
                if (!idSet.contains(id)){
                    idSet.add(id);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, maxCount);

        Assert.assertEquals(maxCount,idSet.size());
    }

}

