import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import io.github.cdimascio.dotenv.dotenv

fun main() {
    val dotenv = dotenv()
    val apiKey = dotenv["WEATHER_API_KEY"] ?: error("Set WEATHER_API_KEY in .env file")
    val cities = listOf("Chisinau", "Madrid", "Kyiv", "Amsterdam")

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.weatherapi.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(WeatherApiService::class.java)

    val rows = mutableListOf<List<String>>()
    rows.add(listOf("City", "Date", "Min C", "Max C", "Humidity %", "Wind kph", "Wind Dir"))

    for (city in cities) {
        val response = service.getForecast(apiKey, city).execute()
        if (response.isSuccessful) {
            val forecastDay = response.body()!!.forecast.forecastday[1] // tomorrow
            val d = forecastDay.day
            val midHour = forecastDay.hour.find { it.time.contains("12:00") }
            val windDir = midHour?.wind_dir ?: forecastDay.hour.firstOrNull()?.wind_dir ?: "N/A"

            rows.add(
                listOf(
                    city.take(12),
                    forecastDay.date,
                    "%.1f".format(d.mintemp_c ?: 0.0),
                    "%.1f".format(d.maxtemp_c ?: 0.0),
                    "%.0f".format(d.avghumidity ?: 0.0),
                    "%.1f".format(d.maxwind_kph ?: 0.0),
                    (windDir?.take(6)) ?: "N/A"
                )
            )
        } else {
            println("Error for $city: ${response.errorBody()?.string()}")
        }
        TimeUnit.MILLISECONDS.sleep(300)
    }

    printAsciiTable(rows)
}


fun printAsciiTable(rows: List<List<String>>) {
    if (rows.isEmpty()) return

    val colWidths = rows[0].indices.map { col ->
        rows.maxOf { it[col].length }
    }

    fun printSeparator() = println(
        colWidths.joinToString(separator = "+", prefix = "+", postfix = "+") { "-".repeat(it + 2) }
    )

    printSeparator()
    for ((rowIndex, row) in rows.withIndex()) {
        val line = row.mapIndexed { i, cell ->
            val padded = if (i > 1) { // numeric columns aligned right
                cell.padStart(colWidths[i])
            } else {
                cell.padEnd(colWidths[i])
            }
            " $padded "
        }.joinToString("|", prefix = "|", postfix = "|")
        println(line)
        printSeparator()
    }
}
