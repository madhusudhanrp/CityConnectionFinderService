package com.challenge.utils;

import com.challenge.domain.CityConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;


@Component
public class CityConnectionsBuilder
{
	private static final String DELIMITER = ",";

	@Value("classpath:/static/city-connections.txt")
	private Resource res;
	private ConcurrentHashMap<String, CityConnection> cityConnectionsMap;

	@PostConstruct
	public void init()
	{
		cityConnectionsMap = buildCityConnectionsMap();
	}

	public ConcurrentHashMap<String, CityConnection> buildCityConnectionsMap()
	{
		return readFile();
	}


	private ConcurrentHashMap<String, CityConnection> readFile()
	{
		final ConcurrentHashMap<String, CityConnection> cityConnectionsMap = new ConcurrentHashMap<>();
		try (Stream<String> lines = Files.lines(Paths.get(res.getURL().getPath())))
		{
			lines.forEach( line ->
			{
				String connectedCities[] = parseLine(line);
				saveConnection(cityConnectionsMap, connectedCities);
			});

		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return cityConnectionsMap;
	}



	private void saveConnection(final Map<String, CityConnection> dataMap,
			String[] connectedCities)
	{

		if (connectedCities.length == 2)
		{
			ArrayList<CityConnection> cities = new ArrayList<CityConnection>(2);
			CityConnection cityConnection;
			for (String cityName : connectedCities)
			{
				if ((cityConnection = dataMap.get(cityName)) == null)
				{
					cityConnection = new CityConnection(cityName);
					dataMap.put(cityName, cityConnection);
				}
				cities.add(cityConnection);
			}

			if (!cities.get(0).hasConnectionWith(cities.get(1).getCityName()))
			{
				cities.get(0).addConnection(cities.get(1).getCityName(),
						cities.get(1));
			}
			if (!cities.get(1).hasConnectionWith(cities.get(0).getCityName()))
			{
				cities.get(1).addConnection(cities.get(0).getCityName(),
						cities.get(0));
			}
		}
	}


	private final String[] parseLine(final String line)
	{
		String connectedCities[] = getConnectedCities(line);
		return connectedCities;
	}


	private String[] getConnectedCities(String line)
	{
		return line.trim().toLowerCase().replace(DELIMITER + " ", DELIMITER)
				.split(DELIMITER);
	}

	public ConcurrentHashMap<String, CityConnection> getCityConnectionsMap()
	{
		return cityConnectionsMap;
	}

}
