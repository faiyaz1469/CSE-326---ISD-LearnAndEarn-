#!/usr/bin/python
from email.mime import image
import tweepy
import config
import os
import sys
import pandas as pd

os.system("clear")

# connecting to twitter api
# authentication
api_key = config.api_key
api_key_secret = config.api_key_secret

bearer_token = config.bearer_token

access_token = config.access_token
access_token_secret = config.access_token_secret

auth = tweepy.OAuthHandler(api_key, api_key_secret)
auth.set_access_token(access_token, access_token_secret)

# api instance to fetch data
api = tweepy.API(auth)

keywords = ["Cox's Bazar Tour", "Bandarban Tour", "Rangamati Tour", "Khagrachori Tour", "Sylhet Tour", "Sundarban Tour", "Sunamgaj Tour"]

print(keywords)

colums = ["Keyword", "Text", "Image", "User"]
data = []
for keyword in keywords:
    limit=5
    tweets = tweepy.Cursor(api.search_tweets, q=keyword, count=5, tweet_mode="extended").items(limit)

    for tweet in tweets:
        if 'media' in tweet.entities:
            for img in tweet.entities['media']:
                data.append([keyword, tweet.full_text, img['media_url'], tweet.user.screen_name])

df = pd.DataFrame(data, columns=colums)
output_file = 'output.csv'
df.to_csv(output_file)