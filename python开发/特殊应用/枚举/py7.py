from enum import Enum
class meiju(Enum):
    Month = Enum('Month', ('Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'))

for name, member in meiju.__members__.items():
    print(name, '=>', member, ',', member.value)



class Weekday(Enum):
    Sun = 0 # Sun的value被设定为0
    Mon = 1
    Tue = 2
    Wed = 3
    Thu = 4
    Fri = 5
    Sat = 6
print(Weekday.Tue.value)
for name, member in Weekday.__members__.items():
    print(name, '=>', member.value)
