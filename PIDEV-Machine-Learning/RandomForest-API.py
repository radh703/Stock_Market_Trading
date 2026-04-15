from fastapi import FastAPI, HTTPException
from pathlib import Path
import os
from pydantic import BaseModel
import numpy as np
import pickle


app = FastAPI()

class VolumesItem(BaseModel):
    Open: float
    High: float  # Corrected typo: Hight -> High
    Low: float
    Close: float
    Adj_close: float

@app.get('/')
async def scoring_endpoint():
    return {"hello": "world"}  # Corrected "word" -> "world"

@app.post('/Prediction')
async def prediction_volume(item: VolumesItem):
    try:
        # Extract values directly from the item
        values_list = [item.Open, item.High, item.Low, item.Close, item.Adj_close]
        array_list = np.array(values_list).reshape(1, -1)
        
        # Load the model
        default_model_path = Path(__file__).resolve().parent / "random_model.pkl"
        model_path = Path(os.getenv("RANDOM_FOREST_MODEL_PATH", str(default_model_path)))
        with open(model_path, "rb") as model_file:
            loaded_prediction_instance = pickle.load(model_file)
        
        # Make predictions
        predicted_value = loaded_prediction_instance.predict(array_list)
        
        # Convert NumPy array to Python list
        predicted_value_list = predicted_value.tolist()  

        # Debugging: print predicted_value_list
        print("Predicted value list:", predicted_value_list)

        # Return a stable JSON contract for backend parsing
        return {"prediction": float(predicted_value_list[0])}
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))

