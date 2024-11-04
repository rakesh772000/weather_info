package com.weatherInfoForPincode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.weatherInfoForPincode.model.Pincode;
import com.weatherInfoForPincode.model.Weather;
import com.weatherInfoForPincode.repository.PincodeRepository;
import com.weatherInfoForPincode.repository.WeatherRepository;
import com.weatherInfoForPincode.util.GeoApiResponse;
import com.weatherInfoForPincode.util.WeatherApiResponse;

import java.util.Optional;

@Service
public class WeatherService {

	@Autowired
	private PincodeRepository pincodeRepository;

	@Autowired
	private WeatherRepository weatherRepository;

	private final String OPENWEATHER_API_URL = "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=YOUR_API_KEY&units=metric";
	private final String GEO_API_URL = "https://api.openweathermap.org/geo/1.0/zip?zip=%s,IN&appid=1234567890abcdef";

	public Weather getWeatherInfo(String pincode, String forDate) {

		Optional<Pincode> existingPincode = pincodeRepository.findByPincode(pincode);
		if (existingPincode.isPresent()) {

			Optional<Weather> existingWeather = weatherRepository.findByPincodeAndDate(existingPincode.get(), forDate);
			if (existingWeather.isPresent()) {
				return existingWeather.get();
			} else {

				Pincode pin = existingPincode.get();
				double lat = pin.getLatitude();
				double lon = pin.getLongitude();

				String url = String.format(OPENWEATHER_API_URL, lat, lon);
				RestTemplate restTemplate = new RestTemplate();
				WeatherApiResponse apiResponse = restTemplate.getForObject(url, WeatherApiResponse.class);

				if (apiResponse != null) {
					Weather weather = new Weather();
					weather.setPincode(pin);
					weather.setDate(forDate);
					weather.setTemperature(apiResponse.getMain().getTemp());
					weather.setHumidity(apiResponse.getMain().getHumidity());
					weather.setDescription(apiResponse.getWeather().get(0).getDescription());
					weatherRepository.save(weather);
					return weather;
				}
			}
		} else {

			String geoUrl = String.format(GEO_API_URL, pincode);
			RestTemplate restTemplate = new RestTemplate();
			GeoApiResponse[] geoApiResponseArray = restTemplate.getForObject(geoUrl, GeoApiResponse[].class);

			if (geoApiResponseArray != null && geoApiResponseArray.length > 0) {
				GeoApiResponse geoApiResponse = geoApiResponseArray[0];
				Pincode newPincode = new Pincode();
				newPincode.setPincode(pincode);
				newPincode.setLatitude(geoApiResponse.getLat());
				newPincode.setLongitude(geoApiResponse.getLon());
				pincodeRepository.save(newPincode);

				return getWeatherInfo(pincode, forDate);
			}
		}
		return null;
	}
}
