Aditya Pidaparti, ID: 5164637
Highest level attempted: A-level/Extra-credit

C-Level:
My writeup is pretty straightforward and obeys the rules given, with one notable
exception: I have a Class Cell that I initialize for every spot in the Sudoku board.
It's under Helper.py, as I didn't want it clogging up the main sudoku.py file.
I've set up the import already, and all 3 files (sudoku.py, Helper.py, and tests.py)
should be in the folder. Only the first two are necessary, but if you want to look
at my testing suite, it's available as well. 

I don't like this 'get' function that I have to make. I've created it, but it just
calls a get_value function that I feel is a more appropriate name.

getNeighbors isn't the most 'dense' of code writing, but it gets the job done.
for a 2 size sudoku board, I just call the stupid locations I need. I do however
like my implementation for the 3-size. It finds the box that the Cell is in, 
finds the center of that box, and then adds every value in that box. The one downside
of this is that the value itself gets added to neighbors 3 times, so I've removed it.

B-Level: 
I skipped this and Professor Terveen said this was ok as long as my A-level worked.

My reason for doing so was that I've done a lot of Sudoku and the A-Level took me 
30 minutes because I know the heuristic to solve Sudoku extremely well. (I'll rant
more on that later)

A-Level:
I used a tail-recursion method to solve this, although I don't think it's apparent
I also could redo this as an iterative solution since I'm basically just returning
one statement over and over again, so a while loop works just fine. 

My method is, in my opinion a better way of addressing the extra-credit, but it doesn't
technically fulfill the criteria.

I update each cell's possible values to reflect the box, row, and column, then after
updating the possible values of every cell, I set the value of all cells which have
only one possible value. After that, I just call the function again with the updated board.

Extra-credit:
I auto check each cell for all possible values, so if there are no possible values for
a cell, then it returns that the sudoku is unsolvable. Also, if a cell already has a value, I don't
bother checking it.

Summary:
I solve this is a completely different way from what is proposed. This is the way that 
I do sudokus by hand. You store the possible values, and once there is one, you set
it to the value on the grid. I personally don't like the method entailed by extra-credit
because it doesn't solve the problem, it only helps you find out when you have a wrong
solution. 

This algorithm works by breadth first as opposed to depth first search, which I believe
is a FAR superior method. Instead of constantly going to dead ends, this one
only chases down the path that is correct.