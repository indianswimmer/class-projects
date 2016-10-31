from passwords import *

print("------testing C level")

if isValid("a","Daniel","Allen","Kluver","kluve018"):
  print('------isValid("a","Daniel","Allen","Kluver","kluve018") should return false')
if isValid("","Daniel","Allen","Kluver","kluve018"):
  print('------isValid("","Daniel","Allen","Kluver","kluve018") should return false')
if isValid("aA1!","Daniel","Allen","Kluver","kluve018"):
  print('------isValid("aA1!","Daniel","Allen","Kluver","kluve018") should return false')
loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi purus odio, euismod at egestas id, consectetur sit amet nullam1!"
if isValid(loremIpsum,"Daniel","Allen","Kluver","kluve018"):
  print('------isValid("'+loremIpsum+'","Daniel","Allen","Kluver","kluve018") should return false')
if isValid("3BlindMice! ","Daniel","Allen","Kluver","kluve018"):
  print('------isValid("3BlindMice! ","Daniel","Allen","Kluver","kluve018") should return false')


if not isValid("3 Blind Mice","Daniel","Allen","Kluver","kluve018"):
  print('------isValid("3 Blind Mice","Daniel","Allen","Kluver","kluve018") should return true')
if not isValid("A long Password with 24!","Daniel","Allen","Kluver","kluve018"):
  print('------isValid("A long Password with 24!","Daniel","Allen","Kluver","kluve018") should return true')

if not phraseToPass("Computer Science at the U of M is fun!") == "CSatUoMif!":
  print('------phraseToPass("Computer Science at the U of M is fun!") should equal "CSatUoMif!"')

print("------testing B level")
   
if isValid("QWER1212","Daniel","Allen","Kluver","kluve018"):
  print('------isValid("QWER1212","Daniel","Allen","Kluver","kluve018") should return false')
if isValid("qwer1212","Daniel","Allen","Kluver","kluve018"):
  print('------isValid("qwer1212","Daniel","Allen","Kluver","kluve018") should return false')
if isValid("qwer!@#$","Daniel","Allen","Kluver","kluve018"):
  print('------isValid("qwer!@#$","Daniel","Allen","Kluver","kluve018") should return false')
if isValid("QWER!@#$","Daniel","Allen","Kluver","kluve018"):
  print('------isValid("QWER!@#$","Daniel","Allen","Kluver","kluve018") should return false')

if not isValid("qwER12#$","Daniel","Allen","Kluver","kluve018"):
  print('------isValid("qwER12#$","Daniel","Allen","Kluver","kluve018") should return true')
if not isValid("qwer!@34","Daniel","Allen","Kluver","kluve018"):
  print('------isValid("qwer!@34","Daniel","Allen","Kluver","kluve018") should return true')
if not isValid("QWer1234","Daniel","Allen","Kluver","kluve018"):
  print('------isValid("QWer1234","Daniel","Allen","Kluver","kluve018") should return true')

if not obfuscate("andeEiIoOWMwm")=="&331|00|/\||\/|wm":
  print('------obfuscate("andeEiIoOWMwm") should equal "&331|00|/\||\/|wm"')

print("------testing A level")
if isValid("Daniel Allen Kluver","Daniel","Allen","Kluver","kluve018"):
  print('------isValid("Daniel Allen Kluver","Daniel","Allen","Kluver","kluve018") should return false')
if isValid("asdfl;hdanielasdf;lkjasd312f","Daniel","Allen","Kluver","kluve018"):
  print('------isValid("asdfl;hdanielasdf;lkjasd312f","Daniel","Allen","Kluver","kluve018") should return false')
if isValid("the conductor yells allen!","Daniel","Allen","Kluver","kluve018"):
  print('------isValid("the conductor yells allen!","Daniel","Allen","Kluver","kluve018") should return false')
if isValid("my name is kluve018 and I am super cool","Daniel","Allen","Kluver","kluve018"):
  print('------isValid("my name is kluve018 and I am super cool","Daniel","Allen","Kluver","kluve018") should return false')
if isValid("I luve P1zza!","Daniel","Allen","Kluver","kluve018"):
  print('------isValid("I luve P1zza!","Daniel","Allen","Kluver","kluve018") should return false')
if isValid("I am worth 1018$","Daniel","Allen","Kluver","kluve018"):
  print('------isValid("I am worth 1018$","Daniel","Allen","Kluver","kluve018") should return false')
if isValid("got a KlU?","Daniel","Allen","Kluver","kluve018"):
  print('------isValid("got a KlU?","Daniel","Allen","Kluver","kluve018") should return false')

if not isValid("dani3l All3n klvu3R","Daniel","Allen","Kluver","kluve018"):
  print('------isValid("dani3l All3n klvu3R","Daniel","Allen","Kluver","kluve018") should return true')
if not isValid("K l u v e 0 1 8 @ u m n . e d u","Daniel","Allen","Kluver","kluve018"):
  print('------isValid("K l u v e 0 1 8 @ u m n . e d u","Daniel","Allen","Kluver","kluve018") should return true')

