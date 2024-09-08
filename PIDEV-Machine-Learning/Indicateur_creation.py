import pandas as pd  # install using 'pip install pandas'
from datetime import datetime
import time

class Indicateur:
    def __init__(self,csv_filename,sma_period,rsi_period,atr_period):
        self.csv_filename = csv_filename
        self.df = pd.read_csv(self.csv_filename)
        self.sma_period = sma_period
        self.rsi_period = rsi_period
        self.atr_period = atr_period
    def get_data(self):
        self.df = pd.read_csv(self.csv_filename)
        self.features = self.df[["Open", "High", "Low", "Close", "Adj Close", "Volume"]].values
        print(self.df)
 
    def SMA(self,sma_period):
        
        last_close =[] 
        sma = []
        for i in range(0,len(self.df)):
            sma.append(self.df['Close'][i:i+sma_period].mean())
            last_close.append(self.df['Close'][i:i+sma_period].iloc[0])
        self.df['sma'] = sma
        self.df['last_close'] = last_close
        #SMA tratégie
        self.df['sma_direction'] = (self.df['sma'] - self.df['last_close']).apply(lambda x: 'sell' if x>0 else('buy' if x<0 else 'none'))
        
    
    def RSI_14(self,rsi_period):
         # Calcul des gains et des pertes
        self.df['gain'] = (self.df['Close'] - self.df['Open']).apply(lambda x: x if x > 0 else 0)
        self.df['perte'] = (self.df['Open'] - self.df['Close']).apply(lambda x: x if x > 0 else 0)
    
        # Calcul de l'EMA des gains et des pertes sur 14 jours
        self.df['ema_gain'] = self.df['gain'].ewm(span=rsi_period).mean()
        self.df['ema_perte'] = self.df['perte'].ewm(span=rsi_period).mean()
    
        # Calcul de la force relative
        self.df['rs'] = self.df['ema_gain'] / self.df['ema_perte']
    
        # Calcul du RSI
        self.df['rsi_14'] = 100 - (100 / (1 + self.df['rs']))
        #RSI stratégie
        self.df['rsi_direction'] = ( self.df['rsi_14']).apply(lambda x: 'sell' if x > 70 else ('buy' if x < 30 else 'none'))
        
    
    def ATR_14(self,atr_period):
         # calculating the range of each candle
        self.df['range'] = self.df['High'] - self.df['Low']
        # calculating the average value of ranges
        self.df['atr_14'] = self.df['range'].rolling(atr_period).mean()
 
    
    def saveDfToCsv(self):
        self.df.to_csv("data_with_indicateur.csv")
        return self.df

        

    
csv_filename = "c:/Users/DELL/Desktop/PI_infini/historical_data.csv"
sma_period = 10
rsi_period = 20
atr_period = 20
indicateur_instance = Indicateur(csv_filename, sma_period, rsi_period, atr_period)
#indicateur_instance.get_df()
#indicateur_instance.get_data()
indicateur_instance.SMA(sma_period)
indicateur_instance.RSI_14(rsi_period)
indicateur_instance.ATR_14(atr_period)
indicateur_instance.saveDfToCsv()
