package com.challenge.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class CityConnectionServiceTest
{
    @Mock
    private CityConnectionsService cityConnectionsService;

    @Before
    public void setup()
    {

    }
    @Test
    public void shouldFindCityConnection()
    {
        given(cityConnectionsService.findRoadConnectionForOriginDestination("Dallas","Austin")).willReturn("YES");

        String connectionExists = cityConnectionsService.findRoadConnectionForOriginDestination("Dallas","Austin");
        assertThat(connectionExists).isEqualTo("YES");
    }

}
