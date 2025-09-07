# Weather Challenge 🌦️

A Kotlin console application for the **WeatherAPI.com Code Challenge**.
The app retrieves the next-day forecast for the following cities:

- Chisinau
- Madrid
- Kyiv
- Amsterdam

It prints the results in a formatted ASCII table, showing:

- Minimum Temperature (°C)
- Maximum Temperature (°C)
- Humidity (%)
- Wind Speed (kph)
- Wind Direction

---

## ✨ Features
- Written in **Kotlin** (bonus requirement ✅)
- Uses **Gradle** for build automation
- Fetches data from [WeatherAPI.com](https://www.weatherapi.com/)
- Outputs a clean, formatted ASCII table

---

## 🚀 Requirements
- **JDK 21** (Temurin recommended)
- **Gradle Wrapper** (already included: `gradlew` / `gradlew.bat`)
- A valid [WeatherAPI.com API key](https://www.weatherapi.com/signup.aspx)

---

## 🔧 Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/<your-username>/weather-challenge.git
   cd weather-challenge

2. Set your WeatherAPI.com API key

   Manually:
      ```
      export WEATHER_API_KEY=your_api_key_here   # macOS/Linux
      setx WEATHER_API_KEY "your_api_key_here"   # Windows (permanent)
      set WEATHER_API_KEY=your_api_key_here      # Windows (current session)
      ```
   In .env file:
   ```
   WEATHER_API_KEY=your_api_key_here
   ```
3. Ensure Java is available
   Check Java version:
   ```
   java -version
   ```
   If not, set JAVA_HOME manually:
   ```
   # Windows
   set JAVA_HOME="Your/path/to/Java"
   # Linux/macOS
   export JAVA_HOME="Your/path/to/Java"
   ```
4. Running the App
   Linux/MacOS
   ```
   ./gradlew build
   ./gradlew run
   ```
   Windows (cmd or PowerShell)
      ```
      gradlew build
      gradlew run
      ```