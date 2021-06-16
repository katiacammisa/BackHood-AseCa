from locust import HttpUser, TaskSet, task, between
import pandas as pd
import string
import random
import sys


symbols = pd.read_csv("all.csv")
basePath = "http://localhost:8080"

def getSymbol():
  return symbols.sample()['symbol'].values[0]

@task
def get_stock_price(l):
  symbol = getSymbol()
  l.client.get(url= basePath + "/" + symbol, name="get random stock price")

@task
def get_all(l):
  letter = random.choice(string.ascii_letters)
  l.client.get(url= basePath + "/list?keyword=" + letter, name="get all with random letter")

@task
def get_stats(l):
  symbol = getSymbol()
  l.client.get(url= basePath + "/stats/" + symbol, name="get stats of random stock")

@task
def buy_stock(l):
  userId = random.randint(0, sys.maxsize)
  quantity = random.randint(0, 100)
  symbol = getSymbol()
  l.client.put(url= basePath + "/" + str(userId) + "/" + symbol + "/" + str(quantity), name="random user buys random stock with random quantity")

class NormalUserTaskSet(TaskSet):
  print("normal")
  tasks = {
     get_all: 10,
     get_stock_price: 100,
     get_stats: 2,
     buy_stock: 1
   }

class WhaleUserTaskSet(TaskSet):
  print("elon")
  tasks = {
    get_all: 100,
    get_stock_price: 1000,
    get_stats: 80,
    buy_stock: 20
  }

class NormalUser(HttpUser):
   weight = 1000
   tasks = [NormalUserTaskSet]
   wait_time = between(1000,2000)

class WhaleUser(HttpUser):
  weight = 1
  tasks = [WhaleUserTaskSet]
  wait_time = between(1000,2000)
