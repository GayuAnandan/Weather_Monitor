# Real-Time Data Processing System for Weather Monitoring

# Objective
Develop a real-time data processing system to monitor weather conditions and provide summarized insights using rollups and aggregates. The system retrieves data from the [OpenWeatherMap API](https://openweathermap.org/) and focuses on key weather parameters such as temperature, perceived temperature, and weather conditions.

# Artifacts
**Codebase**: [GitHub Repository](https://github.com/GayuAnandan/Weather_Monitor/)

# Data Source
The system continuously retrieves weather data from the OpenWeatherMap API. You will need to sign up for a free API key to access the data. The API provides various weather parameters, specifically:
- **main**: Main weather condition (e.g., Rain, Snow, Clear)
- **temp**: Current temperature in Kelvin
- **feels_like**: Perceived temperature in Kelvin
- **dt**: Time of the data update (Unix timestamp)

# Dependencies
## Required Software
 -**Java 17+** for the application code
 - **Maven** for dependency management
- **MySQL** (or another relational database) for persistent storage

# Design Choices
* Real-Time Data Processing: The system is designed to retrieve and process data continuously at configurable intervals, ensuring timely insights.
* Data Aggregation: Daily summaries provide meaningful insights into weather trends, helping users understand changes over time.
* User Configurability: The ability to set thresholds allows users to customize alerts based on personal preferences.
  
# Conclusion
This real-time data processing system provides a comprehensive solution for monitoring weather conditions, offering aggregated insights and alerting capabilities. The architecture supports extensibility and integration with various visualization tools for enhanced data presentation.
