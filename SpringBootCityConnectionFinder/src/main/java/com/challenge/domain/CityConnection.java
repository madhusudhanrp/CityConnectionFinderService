package com.challenge.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CityConnection
{

	private String cityName;
	private boolean traversed;
	private Map<String, CityConnection> cityConnectionsMap;

	public CityConnection(final String cityName)
	{
		this.cityName = cityName;
		this.traversed = false;
		cityConnectionsMap = new ConcurrentHashMap<>();
	}


	public String getCityName()
	{
		return this.cityName;
	}


	public void addConnection(final String cityName, final CityConnection connection)
	{
		this.cityConnectionsMap.put(cityName, connection);
	}


	public Map<String, CityConnection> getCityConnectionsMap()
	{
		return this.cityConnectionsMap;
	}


	public boolean hasConnectionWith(final String cityName)
	{
		if (cityConnectionsMap != null && !cityConnectionsMap.isEmpty())
		{
			return cityConnectionsMap.containsKey(cityName);
		}
		return false;
	}

	public boolean isTraversed()
	{
		return this.traversed;
	}


	public void setTraversed(boolean traversed)
	{
		this.traversed = traversed;
	}

	public void reset()
	{
		this.traversed = false;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cityName == null) ? 0 : cityName.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CityConnection other = (CityConnection) obj;
		if (cityName == null) {
			if (other.cityName != null) {
				return false;
			}
		} else if (!cityName.equals(other.cityName)) {
			return false;
		}
		return true;
	}

}
