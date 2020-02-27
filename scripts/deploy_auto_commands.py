# IMPORTANT: run the following code to install the NetworkTables package
# py -3 -m pip install pynetworktables

import threading
from networktables import NetworkTables
from tkinter.filedialog import askopenfilename

cond = threading.Condition()
notified = [False]

def connectionListener(connected, info):
    print(info, '; Connected=%s' % connected)
    with cond:
        notified[0] = True
        cond.notify()

NetworkTables.initialize(server='10.1.31.2')
NetworkTables.addConnectionListener(connectionListener, immediateNotify=True)

with cond:
    print("Waiting")
    if not notified[0]:
        cond.wait()

print("Connected!")

filename = askopenfilename()
print(filename)

with open(filename) as f:
    content = f.readlines()
content = [x.strip() for x in content] 

print(content)

table = NetworkTables.getTable('Preferences')
for key in table.getKeys():
    table.delete(key)

step = 0
for line in content:
    table.putString('auto' + str(step), line)
    step = step + 1

for key in table.getKeys():
    print(key, table.getString(key, ""))