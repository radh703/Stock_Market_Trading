import pandas as pd
from sklearn.cluster import DBSCAN
from sklearn.metrics import silhouette_score
from sklearn.neighbors import NearestNeighbors
import numpy as np
import matplotlib.pyplot as plt
import pickle
import os
print("Current Working Directory:", os.getcwd())
class DbscanClustering:
    
    def __init__(self, esp_value, min_samples_value, csv_filename, k):
        self.esp_value = esp_value
        self.min_samples_value = min_samples_value
        self.csv_filename = csv_filename
        self.k = k
        self.features = None
        self.df = None
        self.clustering = None

    def get_data(self):
        self.df = pd.read_csv(self.csv_filename)
        self.features = self.df[["Open", "High", "Low", "Close", "Adj Close", "Volume"]].values

    def nearest_neighbors(self):
        nn_model = NearestNeighbors(n_neighbors=self.k)
        nn_model.fit(self.features)
        distances, indices = nn_model.kneighbors(self.features)
        distances = np.sort(distances)
        distances = distances[:, 1]
        plt.plot(distances)
        average_value = np.mean(distances)
        self.esp_value = average_value

    def apply_dbscan(self):
        dbscan = DBSCAN(eps=self.esp_value, min_samples=self.min_samples_value)
        self.clustering = dbscan.fit(self.features)

    def accuracy_dbscan(self):
        cluster_labels = self.clustering.labels_
        self.df['Cluster_Labels'] = cluster_labels
        silhouette_avg = silhouette_score(self.features, cluster_labels)
        print("Silhouette Score:", silhouette_avg)
        print(self.df.columns)
        print(self.df)
        return self.df

    def result_plot(self):
        fig = plt.figure()
        ax = fig.add_subplot(111, projection='3d')
        cluster_labels = self.clustering.labels_
        ax.scatter(self.df["Cluster_Labels"], self.df["Open"], self.df["High"], c=cluster_labels, cmap='viridis', label='Data Points')
        ax.set_title('DBSCAN Clustering')
        ax.set_xlabel('Cluster_Labels')
        ax.set_ylabel('Open')
        ax.set_zlabel('High')
        plt.legend()
        plt.show()

    def save_model(self, filename="dbscan_model.pkl"):
        current_directory = os.getcwd()
        full_path = os.path.join(current_directory, filename)
        with open(full_path, 'wb') as file:
            pickle.dump(self, file)
        print(f"Model saved to {full_path}")

# Uncomment this section to create an instance and save the model
esp_value = 5
min_samples_value = 5
csv_filename = "c:/Users/DELL/Desktop/PI_infini/historical_data.csv"
k = 3
dbscan_instance = DbscanClustering(esp_value, min_samples_value, csv_filename, k)
dbscan_instance.get_data()
dbscan_instance.nearest_neighbors()
dbscan_instance.apply_dbscan()
dbscan_instance.accuracy_dbscan()
dbscan_instance.result_plot()
dbscan_instance.save_model()



