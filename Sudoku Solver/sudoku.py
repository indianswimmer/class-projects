from Helper import *

class Sudoku:
    def __init__(self, size, data):
        self.__size = size  ** 2 #size must be 2 or 3
        self.__data = data
        self.__board = [['' for _ in range(self.__size)] for _ in range(self.__size)]
        for row in range(self.__size):
            for col in range(self.__size):
                self.__board[row][col] = Cell(data[row*self.__size + col], (row, col),self.__size) 
                #initialize a Cell at each location, with the data value, and the possible values
                
    #getters and setters
    def get_value(self, r, c): #c is x-dir and r is y-dir
        return self.__board[r][c].get_value()
    def set_value(self, r, c, value):
        self.__board[r][c].set_value(value)
    def get_size(self):
        return self.__size
    
    def get(self, row, col):
        return self.get_value(row, col)
    
    def isFull(self):
        for row in range(self.__size):
            for col in range(self.__size):
                if self.get_value(row, col) == '0':
                    return False
        return True

    def getBox(self, str_row, str_col):
        row = int(str_row)
        col = int(str_col)
        retlist = []
        if self.get_size() == (2**2):
            if row < 2:
                if col < 2:
                    retlist = [(0,0), (1,0), (1,1), (0,1)]
                elif col >= 2:
                    retlist = [(0,2),(0,3), (1,2), (1,3)]
            if row >= 2:
                if col <2:
                    retlist = [(2,0),(2,1), (3,0), (3,1)]
                elif col >=2:
                    retlist = [(2,2), (2,3), (3,2), (3,3)]
        #below finds the center of the box the selected cell is
        #in and then adds all 9 of the box's cells to neighbors
        elif self.get_size() == (3**2):
            if row <3:
                if col < 3: center = (1,1)
                elif col >=3 and col < 6: center = (1,4)
                elif col >= 6: center = (1,7)
            elif row >= 3 and row < 6:
                if col < 3: center = (4,1)
                elif col >=3 and col < 6: center = (4,4)
                elif col >= 6: center = (4,7)
            elif row >= 6:
                if col < 3: center = (7,1)
                elif col >=3 and col < 6: center = (7,4)
                elif col >= 6: center = (7,7)    
            for r in range (-1, 2):
                for c in range (-1,2):
                    retlist.append((center[0] + r, center[1] + c))
        return retlist
    def getNeighbors(self, row, col): 
        #returns a list of location tuples (r,c); NOT the neighbors themselves
        neighbors = []
        for r in range(len(self.__board)):
            neighbors.append((r, col))
        for c in range(len(self.__board)):
            neighbors.append((row, c))
        box_neighbors = self.getBox(row, col)
        for neighbor in box_neighbors:
            neighbors.append(neighbor)
        while (row,col) in neighbors: #own value is added once through each method, so is removed
            neighbors.remove((row,col))
        return neighbors
        
    def isValid(self):
        #two methods of implementation: using getNeighbors and just checking rows/columns/boxes
        #as it turns out, the latter is about twice as fast as the former (2187 vs 1215 iterations
        for r in range(len(self.__board)):
            for c in range(len(self.__board)):
                neighbors = self.getNeighbors(r,c)
                value = self.get_value(r, c)
                if value != '0':
                    for neighbor in neighbors:
                        #print (value,self.get_value(neighbor[0], neighbor[1]) )
                        if value == self.get_value(neighbor[0], neighbor[1]):
                            return False
        return True

    def __str__(self):
        grid = ''
        dividers = self.__size ** .5
        for r in range(len(self.__board)):
            col_string = ''
            for c in range(len(self.__board[r])):
                val = str( self.__board[r][c].get_value())
                col_string = col_string + val
                if (c+1) % dividers == 0:
                    col_string = col_string + '|'
            grid = grid + col_string + '\n'
            if (r+1) % dividers == 0:
                if dividers == 2:
                    grid = grid + '--+--' + '\n'
                if dividers == 3:
                    grid = grid + '---+---+---' + '\n'
        return grid

    def solve(self):
        if self.isFull() and self.isValid():
            print (self)
        elif not self.isValid():
            print ("There is no solution to this sudoku")
        else:
            for row in self.__board:
                for cell in row:
                    cell.update(self)
            return self.solve()
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        