data class WeatherResponse(
    val forecast: Forecast,
    val location: Location
)

data class Location(
    val name: String,
    val country: String
)

data class Forecast(
    val forecastday: List<ForecastDay>
)

data class ForecastDay(
    val date: String,
    val day: Day,
    val hour: List<Hour>
)

data class Day(
    val maxtemp_c: Double,
    val mintemp_c: Double,
    val avghumidity: Double,
    val maxwind_kph: Double,
)

data class Hour(
    val time: String,
    val wind_dir: String?,
)