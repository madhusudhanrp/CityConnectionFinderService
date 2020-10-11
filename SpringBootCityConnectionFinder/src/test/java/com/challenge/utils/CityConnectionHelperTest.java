package com.challenge.utils;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CityConnectionHelperTest
{

    private CityConnectionsHelper cityConnectionsHelper;
    private CityConnectionsBuilder cityConnectionsBuilder;

    @Value("classpath:/static/city-connections.txt")
    private Resource res;

    @Before
    public void setup() throws IOException
    {
        cityConnectionsHelper = new CityConnectionsHelper();
        cityConnectionsBuilder = new CityConnectionsBuilder();
        Resource resource = Mockito.mock(Resource.class);
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("static/city-connections.txt").getFile());
        URL fileUrl = file.toURI().toURL();
        Mockito.when(resource.getURL()).thenReturn(fileUrl);
        ReflectionTestUtils.setField(cityConnectionsBuilder, "res", resource);
        cityConnectionsBuilder.init();
        ReflectionTestUtils.setField(cityConnectionsHelper, "cityConnectionsBuilder", cityConnectionsBuilder);
    }

    @Test
    public void shouldReturnTrueForCityRoute() throws IOException
    {
        boolean isRouteExists = cityConnectionsHelper.checkConnections("Dallas","Austin");
        assertThat(isRouteExists).isTrue();

    }

    @Test
    public void shouldReturnFalseForCityRoute() throws IOException
    {
        boolean isRouteExists = cityConnectionsHelper.checkConnections("Dallas","KANSAS");
        assertThat(isRouteExists).isFalse();

    }

}
