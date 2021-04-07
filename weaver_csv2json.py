import json
import sys
import csv

data = []

with open(sys.argv[1], "r") as csvfile:
    reader = csv.reader(csvfile)
    headers = next(reader)
    for t, v, a, x, y, r, c in reader:
        data.append({
            "time": float(t),
            "velocity": float(v),
            "acceleration": float(a),
            "pose": {
                "translation": {
                    "x": float(x),
                    "y": float(y)
                },
                "rotation" : {
                    "radians": float(r)
                }
            },
            "curvature": float(c)
        })
    

with open(sys.argv[1] + ".json", "w") as jsonfile:
    json.dump(data, jsonfile)
   