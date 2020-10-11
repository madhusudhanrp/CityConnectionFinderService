package com.challenge.utils;

import java.util.concurrent.ConcurrentHashMap;

import com.challenge.domain.CityConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityConnectionsHelper
{
	@Autowired
	CityConnectionsBuilder cityConnectionsBuilder;

	public boolean checkConnections(String origin, String destination)
    {
        return isConnectionExists(origin,  destination);
	}


	private  boolean isConnectionExists(String origin, String destination)
    {
		CityConnectionFinder cityConnectionFinder = new CityConnectionFinder(origin,
				destination, cityConnectionsBuilder.getCityConnectionsMap());
		 boolean isConnectionExists = cityConnectionFinder.checkConnection();
		 resetDataMap();
		return isConnectionExists;
	}


	private void resetDataMap()
	{
		synchronized(this)
		{
		cityConnectionsBuilder.getCityConnectionsMap().entrySet()
				.stream().forEach(e -> e.getValue().reset());
		}
	}

}
