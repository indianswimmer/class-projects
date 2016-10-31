from sudoku import *

def test_str():
    print ('---------Test for printing sudoku board-------------')
    print ("testing size 2 board:")
    test_2 = "1 42 2  3  1    "
    test_board = Sudoku(2, test_2)
    print (test_board)
    print ("testing size 3 board")
    test_3 = "333323633933335331331836433338132933733333338336738233332639533833233339335313333"
    test_board = Sudoku(3, test_3)
    print (test_board)
    print ('----------End test for printing sudoku board-----------')
 
 
def test_isFull():
    print ('-----------testing isFull fuction----------------')
    not_empty = "483921657967345821251876493548132976729564138136798245372689514814253769695417382"
    empty = '0' * len(not_empty)
    test_board = Sudoku(3, not_empty)
    print ("full, should be true: ")
    print (test_board.isFull())
    test_board = Sudoku(3,empty)
    print ("empty board, should be false: ")
    print (test_board.isFull())
    print ('----------------end test isFull-------------')
    
def test_getNeighbors():
    print ("-------------------testing getNeighbors --------------")
    board_str = "003020600900305001001806400008102900700000008006708200002609500800203009005010300"
    test_board = Sudoku(3, board_str)
    print (test_board.getNeighbors(4,4))
    print ('---------------------end test getNeighbors-------------')

def test_getvalid():
    print ('-------------------testing getValid -----------')
    true_empty = "003020600900305001001806400008102900700000008006708200002609500800203009005010300"
    false_empty = "003020600900305001001806400008102900700000008006708200002609500800203009005010303"
    true_full = "483921657967345821251876493548132976729564138136798245372689514814253769695417382"
    false_full = "333323633933335331331836433338132933733333338336738233332639533833233339335313333"
    test_board1 = Sudoku(3, true_empty)
    test_board2 = Sudoku(3, false_empty)
    test_board3 = Sudoku(3, true_full)
    test_board4 = Sudoku(3, false_full)
    print ("empty, should return true:")
    print (test_board1.isValid())
    print ("empty, should return false:")
    print (test_board2.isValid())
    print ("full, should return true:")
    print (test_board3.isValid())
    print ("full, should return false:")
    print (test_board4.isValid())
    print ('---------------end test getValid------------------')

def c_tests():
    test_str()
    test_isFull()
    test_getNeighbors()
    test_getvalid()

def test_solve():
    print ('----------------Testing Basic Solver ----------------')
    solved = "483921657967345821251876493548132976729564138136798245372689514814253769695417382"
    unsolved = "003020600900305001001806400008102900700000008006708200002609500800203009005010300"
    unsolved2 = "0000301002030000"
    cannot_solve = "000000907000420180000705026100904000050000040000507009920108000034059000507000033"
    print ("Pre-Solved board:")
    solved_board = Sudoku(3, solved)
    solved_board.solve()
    print ("Solver in action")
    unsolved_board = Sudoku(3, unsolved)
    unsolved_board.solve()
    print ("Testing 2 size sudoku")
    unsolved2 = Sudoku(2, unsolved2)
    unsolved2.solve()
    print ("Testing unsolvable sudoku")
    cannot_solve = Sudoku(3, cannot_solve)
    cannot_solve.solve()
    print ('------------- end test basic solver-----------------------')
    
def a_tests():
    test_solve()








