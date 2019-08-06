package com.mgp.common;

import com.netflix.config.PollResult;
import com.netflix.config.PolledConfigurationSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class FillConfigurationSource implements PolledConfigurationSource {
    @Override
    public PollResult poll(boolean b, Object o) {
        System.out.println("polling.....");
        FileInputStream in = null;
        try {
            in = new FileInputStream("classpath:config.properties");
            Properties properties = new Properties();
            properties.load(in);
            Set<Object> keys = properties.keySet();
            Map<String, Object> map = new HashMap<>();
            for (Object k : keys) {
                map.put((String) k, properties.get(k));
            }
            return PollResult.createFull(map);
        } catch (IOException e) {

        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }
}
