class Cell:
    def __init__(self, value, location, board_size):
        self.__location = location #should be in format (r, c)
        self.__value = value
        self.__possible_vals = [str(x) for x in range(1,board_size+1)]
        
    def get_value(self):
        return self.__value
    def set_value(self, value):
        self.__value = value
    
    def get_possible_values(self):
        return self.__possible_vals
    def add_pos_val(self, val):
        self.__possible_vals.append(val)
    
    def delete_pos_val(self, val):
        if val in self.__possible_vals:
            self.__possible_vals.remove(val)
        #else:
        #   val = str(val)
        #   print (val + 'not in posvals')
        
    def update_possible_values(self, board):
        #finds neighbors, checks their values, and removes any that are still in 
        #current list of possible values
        neighbors = board.getNeighbors(self.__location[0], self.__location[1])
        for neighbor in neighbors:
            if board.get_value(neighbor[0],neighbor[1]) in self.__possible_vals:
                self.__possible_vals.remove(board.get_value(neighbor[0],neighbor[1]))
    def update(self, board):
        self.update_possible_values(board)
        if len(self.__possible_vals) == 1:
            self.set_value(self.__possible_vals[0])
            self.__possible_vals = []
