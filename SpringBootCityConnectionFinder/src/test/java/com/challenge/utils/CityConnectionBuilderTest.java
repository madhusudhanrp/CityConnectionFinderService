package com.challenge.utils;

import com.challenge.services.CityConnectionsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import static org.fest.assertions.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CityConnectionBuilderTest
{

    private CityConnectionsBuilder cityConnectionsBuilder;

    @Value("classpath:/static/city-connections.txt")
    private Resource res;

    @Before
    public void setup()
    {
        cityConnectionsBuilder = new CityConnectionsBuilder();
    }

    @Test
    public void shouldLoadCityConnectionsMapFromFile() throws IOException
    {
        Resource resource = Mockito.mock(Resource.class);
        ClassLoader classLoader = getClass().getClassLoader();
        classLoader.getResource("static/city-connections.txt").getFile();
        File file = new File(classLoader.getResource("static/city-connections.txt").getFile());
        URL fileUrl = file.toURI().toURL();
        Mockito.when(resource.getURL()).thenReturn(fileUrl);
        ReflectionTestUtils.setField(cityConnectionsBuilder, "res", resource);
        cityConnectionsBuilder.init();
        assertThat(cityConnectionsBuilder.getCityConnectionsMap()).isNotNull();
        assertThat(cityConnectionsBuilder.getCityConnectionsMap().size()).isEqualTo(9);
    }

}
