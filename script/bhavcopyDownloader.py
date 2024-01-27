import requests
import zipfile
import csv
import io
from datetime import datetime, timedelta

def download_bhavcopy(date):
    date_str = date.strftime("%d%m%y")
    url = f"https://www.bseindia.com/download/BhavCopy/Equity/EQ250124_CSV.ZIP"
    response = requests.get(url)
    print(url)
    if response.status_code == 200:
        return response.content
    else:
        print(response.status_code)
        return None
    
def extract_csv(zip_content):
    with zipfile.ZipFile(io.BytesIO(zip_content), 'r') as zip_ref:
        for file_name in zip_ref.namelist():
            with zip_ref.open(file_name) as csvfile:
                csvreader = csv.reader(io.TextIOWrapper(csvfile))
                next(csvreader)
                for row in csvreader:
                    yield row

def process_data_for_last_50_days():
    for i in range(1):
        date = datetime.now() - timedelta(days=i)
        zip_content = download_bhavcopy(date)
        if zip_content:
            for row in  extract_csv(zip_content):
                print(row)

process_data_for_last_50_days()