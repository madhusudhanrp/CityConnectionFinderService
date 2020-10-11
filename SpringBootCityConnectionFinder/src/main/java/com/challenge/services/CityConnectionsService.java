package com.challenge.services;

import com.challenge.utils.CityConnectionsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityConnectionsService
{
	@Autowired
    CityConnectionsHelper cityConnectionsHelper;

	@RequestMapping("/connected")
	public String findRoadConnectionForOriginDestination(@RequestParam(name = "origin", defaultValue = "Boston") String origin,
														 @RequestParam(name = "destination", defaultValue = "New York") String destination )
	{
		return cityConnectionsHelper.checkConnections(origin,destination) ? "yes": "no";
	}

}
