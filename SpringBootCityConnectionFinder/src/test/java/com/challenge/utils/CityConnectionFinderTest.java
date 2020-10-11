package com.challenge.utils;

import com.challenge.domain.CityConnection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.util.ReflectionTestUtils;
import static org.mockito.BDDMockito.given;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CityConnectionFinderTest
{
    @Mock
    private CityConnectionsBuilder cityConnectionsBuilder;

    private CityConnectionFinder cityConnectionFinder;

    private ConcurrentHashMap<String, CityConnection> cityConnectionConcurrentHashMap;

    @Before
    public void setup()
    {
        cityConnectionConcurrentHashMap = new ConcurrentHashMap<>();

    }

    @Test
    public void shouldReturnConnectionAsTrue() throws IOException
    {
        CityConnection cityConnection = new CityConnection("austin");
        cityConnectionConcurrentHashMap.put("dallas", cityConnection);
      /*  CityConnection cityConnectionD = new CityConnection("dallas");
        cityConnectionConcurrentHashMap.put("austin", cityConnectionD);*/
        ConcurrentHashMap<String, CityConnection> cityConnectionsMap = cityConnectionsBuilder.getCityConnectionsMap();
        cityConnectionFinder = new CityConnectionFinder("Dallas",
                "Austin", cityConnectionConcurrentHashMap);

        boolean isConnectionExists = cityConnectionFinder.checkConnection();
      //  resetDataMap();
        assertThat(isConnectionExists).isTrue();
    }

    @Test
    public void shouldReturnConnectionAsFalse() throws IOException
    {
        CityConnection cityConnection = new CityConnection("austin");
        cityConnectionConcurrentHashMap.put("dallas", cityConnection);
      /*  CityConnection cityConnectionD = new CityConnection("dallas");
        cityConnectionConcurrentHashMap.put("austin", cityConnectionD);*/
        ConcurrentHashMap<String, CityConnection> cityConnectionsMap = cityConnectionsBuilder.getCityConnectionsMap();
        cityConnectionFinder = new CityConnectionFinder("Dallas",
                "Kansas", cityConnectionConcurrentHashMap);

        boolean isConnectionExists = cityConnectionFinder.checkConnection();
        //  resetDataMap();
        assertThat(isConnectionExists).isFalse();
    }

    @Test
    public void shouldReturnConnectionAsTrueForMultiRoute() throws IOException
    {
        CityConnection cityConnection = new CityConnection("dallas");
        CityConnection houstonC = new CityConnection("houston");
        cityConnection.addConnection("houston",houstonC);

        CityConnection austinC = new CityConnection("austin");
        cityConnection.addConnection("austin",austinC);

        cityConnectionConcurrentHashMap.put("dallas", cityConnection);

       // cityConnectionConcurrentHashMap.put("austin", cityConnectionD);
        ConcurrentHashMap<String, CityConnection> cityConnectionsMap = cityConnectionsBuilder.getCityConnectionsMap();
        cityConnectionFinder = new CityConnectionFinder("Dallas",
                "Houston", cityConnectionConcurrentHashMap);

        boolean isConnectionExists = cityConnectionFinder.checkConnection();
        assertThat(isConnectionExists).isTrue();
    }

    @Test
    public void shouldReturnConnectionAsFalseForMultiRoute() throws IOException
    {
        CityConnection cityConnection = new CityConnection("dallas");
        CityConnection houstonC = new CityConnection("houston");
        cityConnection.addConnection("houston",houstonC);

        CityConnection austinC = new CityConnection("austin");
        cityConnection.addConnection("austin",austinC);

        cityConnectionConcurrentHashMap.put("dallas", cityConnection);

        // cityConnectionConcurrentHashMap.put("austin", cityConnectionD);
        ConcurrentHashMap<String, CityConnection> cityConnectionsMap = cityConnectionsBuilder.getCityConnectionsMap();
        cityConnectionFinder = new CityConnectionFinder("Dallas",
                "Kansas", cityConnectionConcurrentHashMap);

        boolean isConnectionExists = cityConnectionFinder.checkConnection();
        assertThat(isConnectionExists).isFalse();
    }
}
