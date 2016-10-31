import random
import string
all_usable_chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!\"#$%&\'()*+,-./:;<=>?@[\\]^_`{|}~"
#A-level functions-----------------------------------------------------------------------------------------------
def contains3(password):
#password must contain 3/4: upper, lower, numbers, non-alphanumeric
#helper function to isValid
    pass_value = 0
    conditions = [string.ascii_lowercase, string.ascii_uppercase, string.digits, string.punctuation]
    for condition in conditions:
        for char in condition:
            if char in password:
                pass_value += 1
                break
    if pass_value >= 3:
        return True
    else:
        return False
def cantHaveName(password, first_name, middle_name, last_name):
#checks to ensure names not in password
#helper function to isValid
    lowercase_names = [first_name.lower(), middle_name.lower(), last_name.lower()]
    new_password = password.lower()
    for name in lowercase_names:
        if name in new_password:
            return False
    return True

def cantHaveUser(password, account_name):
#checks to ensure any 3 letter combo from username not in password
#helper function to isValid
    lower_pass = password.lower()
    for i in range(len(account_name)-2):
        if account_name[i: i+3] in lower_pass:
            return False
    return True
    
def isValid(password, first_name, middle_name, last_name, account_name):
    if len(password) > 32 or len(password) < 8:
        return False
    if password[-1] == ' ':
        return False
    if not contains3(password):
        return False
    if not cantHaveName(password, first_name, middle_name, last_name):
        return False
    if not cantHaveUser(password, account_name):
        return False
    #if passes all false conditions, returns True
    return True

def randChar():
#helper function to generatePassword
    char = all_usable_chars[random.randint(0,len(all_usable_chars)-1)]
    return char
    
    
def generatePassword(first_name, middle_name, last_name, account_name):
#generates a password, does not check for validity
#helper function to generateValidPassword
    password = []
    pass_len = random.randint(8,32)
    for char in range(pass_len):
        password.append(randChar())
    return "".join(password)

def generateValidPassword(first_name, middle_name, last_name, account_name):
#generates passwords until a valid one is generated, then returns it
    password = ""
    while not isValid(password, first_name, middle_name, last_name, account_name):
        password = generatePassword(first_name, middle_name, last_name, account_name)
    return password
        
    
def wordToLetter(word):
#returns the first letter of a word plus all punctuation
#helper function to phraseToPass
    newWord = word[0]
    for letter in word:
        if letter not in string.ascii_letters:
            newWord = newWord + letter
    return newWord

def phraseToWords(phrase):
#returns a list containing all words of a phrase
#helper function to phraseToPass
    words = []
    wordStart = 0
    wordEnd = len(phrase)
    for i in range(len(phrase)):
        if phrase[i] == ' ':
            wordEnd = i
            words.append(phrase[wordStart:wordEnd])
            wordStart = wordEnd + 1
    words.append(phrase[wordStart:])
    return words
	
def phraseToPass(pass_phrase):
#returns the first letter of each word plus all punctuation for a phrase
	phrase_word = ""
	word_list = phraseToWords(pass_phrase)
	for word in word_list:
		phrase_word = phrase_word + wordToLetter(word)
	return phrase_word

def obfuscate(password):
    substitutions = {'and':'&', 'e':'3', 'E':'3', 'i':'1', 'I':'|', 'o':'0', 'O':'0', 'W':'|/\|', 'M':'|\/|'}
    new_password = password
    for key in substitutions:
        new_password = new_password.replace(key, substitutions[key])
    return new_password

#extra credit -------------------------------------------------------------------------------
def PasswordFromPassPhrase(pass_phrase, first_name, middle_name, last_name, account_name):
#Takes a passphrase as first argument, will return to user either a valid password
#or a reason for why a valid password could not be generated
#Does NOT rerun for additional input. User will have to rerun manually or setup a while loop with this function
#helper function to generatePasswordFromPassPhrase
    password = phraseToPass(pass_phrase)
    password = obfuscate(password)
    if isValid(password, first_name, middle_name, last_name, account_name):
        return password
    else:
        if not contains3(password):
            print ("Password must contain 3/4: upper, lower, numbers, non-alphanumeric. Try to use more types of characters.")
            return False
        if not cantHaveUser(password, account_name):
            print ("Password cannot contain any part of username. Find combinations not related to username.")
        if not cantHaveName(password, first_name, middle_name, last_name):
            print ("Password cannot contain any part of name. Find non-name combinations")
            return False
        if len(password) > 32 or len(password) < 8:
            print ("Password must be between 8 and 32 characters. Try to use more words or non-letter characters")
            return False
        if password[-1] == ' ':
            print( "Password must not end in a space.")
            return False

def generatePasswordFromPassPhrase(pass_phrase, first_name, middle_name, last_name, account_name):
    while not PasswordFromPassPhrase(pass_phrase, first_name, middle_name, last_name, account_name):
        pass_phrase = input("Enter a new phrase: ")
    return PasswordFromPassPhrase(pass_phrase, first_name, middle_name, last_name, account_name)




