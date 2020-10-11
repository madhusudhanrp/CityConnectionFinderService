package com.challenge.utils;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.challenge.domain.CityConnection;


public class CityConnectionFinder
{

	private String origin;
	private String destination;
	private ConcurrentHashMap<String, CityConnection> cityConnectionsMap;

	public CityConnectionFinder(final String origin, final String destination,
								final ConcurrentHashMap<String, CityConnection> cityConnectionsMap)
	{
		this.origin = origin.toLowerCase();
		this.destination = destination.toLowerCase();
		this.cityConnectionsMap = cityConnectionsMap;
	}

	public boolean checkConnection()
	{
		return isConnected();
	}

	private boolean isConnected()
	{
		CityConnection startCityConnection = cityConnectionsMap.get(origin);
		return startCityConnection != null ? searchCityConnections(startCityConnection) : false;
	}

	private boolean searchCityConnections(CityConnection cityConnection)
	{
		if (isMatching(cityConnection))
		{
			return true;
		}
		if (!cityConnection.isTraversed())
		{
			cityConnection.setTraversed(true);
		} else
		{
			return false;
		}
		Optional<CityConnection> result =
				cityConnection.getCityConnectionsMap().values().stream().filter(
						city -> searchCityConnections(city)).findFirst();
		return result.isPresent();
	}

	private boolean isMatching(CityConnection cityConnection)
	{
		return cityConnection.getCityName().equalsIgnoreCase(destination);
	}

}
