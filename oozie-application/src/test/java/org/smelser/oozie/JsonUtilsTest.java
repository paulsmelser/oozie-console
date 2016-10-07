package org.smelser.oozie;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.smelser.code.hadoop.oozie.client.utils.JsonUtils;
import com.smelser.code.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class JsonUtilsTest {
    @Autowired
    private JsonUtils jsonUtils;
    @Test
    public void run() throws Exception {
        Clock clock = Clock.newClock();
        clock.start("some task", jsonUtils::run);
        System.out.println(clock.watch.getLastTaskInfo().getTaskName() + " " + clock.watch.getLastTaskInfo().getTimeMillis());
    }

}