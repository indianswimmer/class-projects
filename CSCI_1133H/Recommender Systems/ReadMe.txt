Aditya Pidaparti, alone
Level Attempted: Extra-credit

C-level:
main function is readRatings, helper function is formatThisShit.
The functino of the helper function is to format each line first,
then add it to the dictionary.

main function is similarity, helper functions are averageOfSum and sums.
The reason for the sums helper function is to get the 4 variables necessary
to compute the pearson coefficient without all of the syntax being an isse.
The averageOfSum function isn't really necessary as it could just be put as a 
few lines under sums, but I felt it was better place in terms of organization.

B-Level: 
only function is nearestNeighbors, wich basically just calls a bunch of previous
functions as helper functions.
Note that I used the sorted() method here. I know we haven't introduced it in class,
but I mostly understand how lambda works. The purpose of using sorted() as opposed to 
sort() is due to it's ability to more cleanly sort by the 2nd element of the tuples within
the list.

A-Level:
the predict function was difficult. The issue with the pearson coefficient is that it has
a range of -1 to 1. Since that represents the weight when calculating the average, it is
possible for the values predicted to be greater than the range of any single value.
In order to work around this issue, I only used the users who had a positive pearson
coefficient. It doesn't make much sense to use values where there is disagreement
and people with 0 agreeement have a weight of 0 and don't factor in.

an alternate solution would be to simply return a score of 5 for a weighted average above 5
and 0.5 for a weighted score below 0.5. However, this solution doesn't work well because if a 
movie was seen by all users and rated highly by all users, regardless of agreement/disagreement
with the user we are testing, it returns approximately a rating of 0. This is the correct 
mathematical solution but the incorrect solution for the optimal user result.

Extra-credit:
the recommend function seemed relatively straighforward. I used a helper function that wasn't too
necessary but seemed slightly cleaner and more modular.
Note: I returned the sorted version of the list of tuples. There is no variable that exists to hold
it.