import random
import copy

class World:
    def __init__(self, grid_size = 10):
        self.__grid = [[Cell() for x in range(grid_size)] for x in range(grid_size)]

    ##getters and setters
    def get_grid(self):
        return self.__grid

    def get_grid_at_location(self, c, r): #returns cell at given location
        return self.__grid[r][c]
    
    def set_grid_at_location(self, c, r, cell):#x-c, y-r
        self.__grid[r][c] = cell

    def print_world(self):
        size = len(self.__grid)
        divider = "-" * ((size * 10) + 1) # hacked up math that works pretty well
        for row in self.__grid:
            # print a divider above each row
            print(divider)
            # print a horizontal divider before the first row
            print("|", end="")
            for cell in row:
                print(cell, end="|" )
            print()
        # print a divider after the last row
        print(divider)

    ##inital state setup
    def populate(self):
        #populate initializes the grid with 300-500 grass per cell,
        #60-100 Wildebeest, and 12-20 Lions
        for row in range(len(self.__grid)):
            for col in range(len(self.__grid[row])):
                cell = self.__grid[row][col]
                tup = (col, row)
                for x in range(random.randint(300,500)):
                    cell.add_organism(Grass(tup))
                for x in range(random.randint(60,100)):
                    cell.add_organism(Wildebeest(tup))
                for x in range(random.randint(12,20)):
                    cell.add_organism(Lion(tup))

    ##simulation functions
    def simulate(self): #simulates one day of movement/life
        for row in range(len(self.__grid)):
            for cell in self.__grid[row]:
                cell.update(self)

    def run(self,days): #runs simulate for a number of days, note whole # of days required
       for x in range(days):
           self.simulate()

class Cell:
    def __init__(self, plant_capacity = 1000):
        self.__organisms = []
        self.__plant_capacity = plant_capacity

    def get_organisms(self):
        return self.__organisms
    def set_organisms(self, new):
        self.__organisms = new

    def add_organism(self, organism):
        self.__organisms.append(organism)
    def delete_organism(self, organism):
        self.__organisms.remove(organism)

    def get_org_count(self, organism_type):
        # "P" - Plants; "W" - Wildebeest; "L" - Lion
        #gets the total number of one kind of organism for a specific cell
        org_count = 0
        types = {"P": Plant, "W": Wildebeest, "L" : Lion}
        if organism_type not in types.keys():
            print ("Invalid type selected")
        else:
            for organism in self.__organisms:
                if isinstance(organism, types[organism_type]):
                    org_count += 1
        return org_count

    def update(self, world):
        #checking to make sure plant count does not exceed 1,000
        self.remove_to_capacity(world)
        self.update_organisms(world)

    def remove_to_capacity(self, world):
        plant_count = 0
        to_remove = []
        for i in range(len(self.__organisms)):
            organism = self.__organisms[-i-1]
            if isinstance(organism, Plant) and plant_count < 1000:
                plant_count += 1
            elif isinstance(organism, Plant) and plant_count >= 1000:
                to_remove.append(organism)
        for plant in to_remove:
            self.__organisms.remove(plant)

    def update_organisms(self, world):
        #progresses life of one organism by one day for a given cell
        i = 0
        while i < len(self.__organisms):
            organism = self.__organisms[i]
            if organism.is_alive():
                organism.live_one_day(world)
                i += 1
            else:
                self.delete_organism(organism)

    def __str__(self):
        return "{},{},{}".format(self.get_org_count("P"), self.get_org_count("W"), self.get_org_count("L"))

class Organism:
    def __init__(self, energy, lifespan, location, upkeep):
        self.__age = 0
        self.__energy = energy
        self.__lifespan = lifespan
        self.__location = location #stored in (x,y) or (c,r) format
        self.__upkeep = upkeep

    def get_age(self):
        return self.__age
    def set_age(self, new):
        self.__age = new
    
    def is_alive(self):
        if self.__energy < self.__upkeep or self.__age >= self.__lifespan:
            return False
        else:
            return True

    def live_one_day(self, world):
        self.__age += 1
        self.__energy -= self.__upkeep

class Plant(Organism):
    def __init__(self, energy, lifeSpan, location, upkeep):
        super().__init__(energy, lifeSpan, location, upkeep)

class Animal(Organism):
    def __init__(self, energy, lifeSpan, location, upkeep):
        super().__init__(energy, lifeSpan, location, upkeep)


class Grass(Plant):
    def __init__(self,location):
        super().__init__(50,5, location,2)
        #energy,lifespan,location,upkeep

class Wildebeest(Animal):
    def __init__(self,location):
        super().__init__(7500,30, location, 100)
        #energy,lifespan,location,upkeep
        
class Lion(Animal):
    def __init__(self, location):
        super().__init__(5000,25, location, 50)
        #energy,lifespan,location,upkeep
