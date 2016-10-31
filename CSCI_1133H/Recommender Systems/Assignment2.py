import math
#file = open('ratings_assignment_2_2.csv')

#C-Level
def formatThisShit(line):
    #takes a line of unformatted text and returns a dict wit the form
    #{'User': user, 'Movie Title': movie title, 'Rating': rating}
    
    #I know using a dictionary isn't ideal in terms of efficiency, but being
    #able to use named keys with labels as opposed to a list with l[0] l[1],
    #etc. makes the code a lot more readable later on (in my opinion)
    d = {}
    split_line = line.split()
    d['User'] = split_line[0]
    d['Movie Title'] = ' '.join(split_line[2:])
    d['Rating'] = split_line[1]
    return d

def readRatings(ratings_file):
    #turns a file into a dictionary of dictionares
    #main dictionary will have users as keys and all their ratings as values
    #each user dctionary will have the movie name as the key and the rating as the value
    file = open(ratings_file, 'r')
    main = {}
    for line in file:
        ld = formatThisShit(line)
        #try/except:
        #main[ld['User']][ld['Movie Title']] = ld['Rating']

        #.get() method
        main[ld['User']] = main.get(ld['User'], {})
        main[ld['User']][ld['Movie Title']] = ld['Rating']

    try:
        del main['UserId'] #get rid of title
    except KeyError:
        pass
    return main

def averageOfSum(U):
    #given a dictionary of movie/rating pairs, returns the average of the ratings' sum
    x = 0
    count = 0
    for value in U.values():
        x += float(value)
        count += 1
    return x / count

def sums(U1, U2):
    #returns a list of the following format:
    #[Exy, Exx, Eyy]
    xAvg  = averageOfSum(U1)
    yAvg = averageOfSum(U2)
    sumxy = 0
    sumxx = 0
    sumyy = 0
    for movie in U1:
        if movie in U2:
            sumxy += (float(U1[movie]) - xAvg) * (float(U2[movie]) - yAvg)
            sumxx += (float(U1[movie]) - xAvg) ** 2
            sumyy += (float(U2[movie]) - yAvg) ** 2
    return [sumxy, sumxx, sumyy]
            
            
def similarity(user_ratings_1, user_ratings_2):
    #computes the Pearson coefficient of similarity between two given users
    E = sums(user_ratings_1, user_ratings_2)
    Exy = E[0]
    Exx = E[1]
    Eyy = E[2]
    return Exy /((Exx * Eyy) ** (1/2.0))

#B-Level
def nearestNeighbors(user_id, all_user_ratings, k):
    #returns a list of tuples containing a number of tuples equal to k
    #representing the k nearest neighbors
    all_neighbors = []
    for user in all_user_ratings:
        current_pair = (user, similarity(all_user_ratings[user_id], all_user_ratings[user]))
        all_neighbors.append(current_pair)
    all_neighbors = sorted(all_neighbors, key = lambda all_neighbors: all_neighbors[1], reverse=True)
    return all_neighbors[1:k+1]

#A-level
def predict(item, k_nearest_neighbors, all_user_ratings):
    #k_nearest neighbors is output of nearestNeighbors function
    #all_user_ratings is output of readRatings function

    #Delete all neighbors that don't have movie
    #Delete all neighbors that don't have a 0 or greater pearson coefficient
    #as negative coefficients indicate disagreement
    #Also, negative weights allow for an average greater than 5 and less than 0.5

    #alternate solution: if score <0.5 or >5, just return 0.5 and 5 respectively
    k_with_item = []
    for neighbor in k_nearest_neighbors:
        if item in all_user_ratings[neighbor[0]] and neighbor[1] > 0:
            k_with_item.append(neighbor)
    
    #compute weight for each k-nearest neighbor
    s = 0 #find sum
    for neighbor in k_with_item:
        s += neighbor[1]
    #with weights, predict score, 0.5-5
    score = 0.0
    test_s = 0.0
    for neighbor in k_with_item:

        #debugging
        #print ('Rating', float(all_user_ratings[neighbor[0]][item]))
        #print ('Weight', neighbor[1]/s)

        score += (float(all_user_ratings[neighbor[0]][item])) * (neighbor[1] / s)
    return score

##testing
a = readRatings('ratings_assignment_2_2.csv')
#k = nearestNeighbors('1', a, 10)
#print(predict('Iron Man 3', k, a))

#Extra-Credit
def moviesNotSeen(user_id, neighbors, all_user_ratings):
    #returns a list of movies a user has not seen given k-nearest neighbors (in a list)
    movies_not_seen = []
    for neighbor in neighbors:
        for movie in all_user_ratings[neighbor]:
            if float(all_user_ratings[neighbor][movie]) >= 4.0:
                if movie not in all_user_ratings[user_id] and movie not in movies_not_seen:
                    movies_not_seen.append(movie)
    return movies_not_seen

def recommend(user_id, all_user_ratings):
    #find k-nearest neighbors, k=10
    k_nearest = nearestNeighbors(user_id, all_user_ratings, 10)
    just_neighbors = []
    for pair in k_nearest:
        just_neighbors.append(pair[0])

    not_seen = moviesNotSeen(user_id, just_neighbors, all_user_ratings)
    predicted_scores = []
    for movie in not_seen:
        predicted_score = predict(movie, k_nearest, all_user_ratings)
        predicted_scores.append((movie, predicted_score))
    return sorted(predicted_scores, key = lambda scores: scores[1], reverse = True)
    

