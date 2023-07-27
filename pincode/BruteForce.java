import requests

def weatherStation(keyword):
    def get_weather_data(page=1):
        url = f"https://jsonmock.hackerrank.com/api/weather/search/{keyword}?page={page}"
        response = requests.get(url)

        if response.status_code == 200:
            result = response.json()
            weather_records = result['data']
            total_pages = result['total_pages']

            if page < total_pages:
                next_page = page + 1
                next_weather_records = get_weather_data(next_page)
                weather_records.extend(next_weather_records)

            return weather_records
        else:
            print(f"Failed to retrieve data for page {page}")
            return []

    weather_records = get_weather_data()
    weather_data = []

    for record in weather_records:
        city_name = record['name']
        weather = record['weather'].split()[0].replace(" ","")
        wind_speed = record['status'][0].replace("Kmph", "").replace("Wind: ","")
        humidity = record['status'][1].replace("%", "").replace("Humidity: ","")

        weather_data.append(f"{city_name},{weather},{wind_speed},{humidity}")

    return "\n".join(weather_data)


# Example usage:
keyword = "all"
result = weatherStation(keyword)
print(result)
