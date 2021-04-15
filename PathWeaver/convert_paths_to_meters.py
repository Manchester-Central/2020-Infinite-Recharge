import os
import sys
import csv

m_to_in = (1/39.3701)

def convert(reader, writer):
    writer.writerow(next(reader))
    for row in reader:
        x, y, tanx, tany, theta, reserved, name = row
        x = float(x) * m_to_in
        y = float(y) * m_to_in
        tanx = float(tanx) * m_to_in
        tany = float(tany) * m_to_in
        new = x, y, tanx, tany, theta, reserved, name
        writer.writerow(new)

for basedir, dirs, files in os.walk(os.path.join(os.getcwd(), "Paths-old")):
    for filename in files:
        old = os.path.join(os.getcwd(), "Paths-old", filename)
        new = os.path.join(os.getcwd(), "Paths", filename)
        with open(old, 'r') as oldfile:
            oldreader = csv.reader(oldfile)
            with open(new, "w", newline='', encoding='utf-8') as newfile:
                newwriter = csv.writer(newfile)
                convert(oldreader, newwriter)