import requests

# Define the URL of the API endpoint
url = 'http://127.0.0.1:8000/Prediction'  # Replace with the actual URL of your API endpoint

# Define sample data to send in the request
sample_data = {
    "Open": 130.899993896484,
    "High": 130.899993896484,
    "Low": 130.899993896484,
    "Close": 130.899993896484,
    "Adj_close": 130.899993896484
}

# Send a POST request to the API endpoint with the sample data
response = requests.post(url, json=sample_data)

# Check if the request was successful (status code 200)
if response.status_code == 200:
    # Print the response from the API
    print("Predicted value:", response.json())
else:
    # Print the error message if the request was not successful
    print("Error:", response.text)
