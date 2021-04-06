import json
import sys
import csv

with open(sys.argv[1], "r") as file:
    data = json.load(file)
    
with open(sys.argv[1] + ".csv", "w", newline='', encoding='utf-8') as csvfile:
    writer = csv.writer(csvfile)
    writer.writerow(
        ("time", "velocity", "acceleration", "x", "y", "radians", "curvature")
    )
    for position in data:
        p = (
            position["time"],
            position["velocity"],
            position["acceleration"],
            position["pose"]["translation"]["x"],
            position["pose"]["translation"]["y"],
            position["pose"]["rotation"]["radians"],
            position["curvature"]
        )
        #print(p)
        writer.writerow(p)