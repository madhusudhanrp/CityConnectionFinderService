# CityConnectionFinderService
Run SpringBootCityConnectionApplication.jav to start the appliation
Code will read the txt file have City pair information (location static/city-connections.txt)
http://localhost:8080/connected?origin=Boston&destination=Newark
Should return yes http://localhost:8080/connected?origin=Boston&destination=Philadelphia Should return yes http://localhost:8080/connected?origin=Philadelphia&destination=Albany Should return no
