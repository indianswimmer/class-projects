import random
import copy

class World:
    def __init__(self, grid_size = 10):
        self.__grid = [[Cell() for x in range(grid_size)] for x in range(grid_size)]

    ##getters and setters
    def get_grid(self): #for this function you input y coordinate 1st
        return self.__grid

    def get_grid_at_location(self, c, r): #returns cell at given location
        return self.__grid[r][c]
        #for immediately above and below, x coordinate is input first
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
                cell.move_immigrants()
    def run(self,days): #runs simulate for a number of days, note whole # of days required
        
        for x in range(days):
            self.simulate()
    def move_organism(self, organism):
        col_move = 0
        row_move = 0
        current_col = organism.get_location()[0]
        current_row = organism.get_location()[1]
        while True:
            while col_move == 0 and row_move == 0:
                col_move = random.randint(-1,1)
                row_move = random.randint(-1,1)
            new_pos = (current_col+col_move, current_row+row_move)
            if new_pos[0] >= 0 and new_pos[0] <= len(self.__grid) and new_pos[1] >= 0 and new_pos[1] <= len(self.__grid):
                break
            else:
                col_move = 0
                row_move = 0
        #delete from old cell
        cell = self.get_grid()[organism.get_location()[1]][organism.get_location()[0]]
        cell.delete_organism(organism)
        #change organism location
        organism.set_location(new_pos)
        #add to new cell's immigrants
        new_cell = self.__grid[new_pos[1]][new_pos[0]]
        new_cell.add_immigrant(organism)
        
class Cell:
    def __init__(self, plant_capacity = 1000):
        self.__organisms = []
        self.__plant_capacity = plant_capacity
        self.__sunlight = random.random()
        self.__water = random.random()
        self.__immigrants = []

    def get_organisms(self):
        return self.__organisms
    def set_organisms(self, new):
        self.__organisms = new
    def get_immigrants (self):
        return self.__immigrants
    def set_immigrants(self, new):
        self.__immigrants = new
    def get_water(self):
        return self.__water
    def set_water(self,new=random.random()):
        self.__water = new
    def get_sunlight(self):
        return self.__sunlight
    def set_sunlight(self, new=random.random()):
        self.__sunlight = new
        
    def add_organism(self, organism):
        self.__organisms.append(organism)
    def delete_organism(self, organism):
        try:
            self.__organisms.remove(organism)
        except ValueError:
            print ("Organism not in list")
    def add_immigrant(self, immigrant):
        self.__immigrants.append(immigrant)
    def move_immigrants(self):
        for immigrant in self.__immigrants:
            self.add_organism(immigrant)
        self.__immigrants = []
        

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
    def get_location(self):
        return self.__location
    def set_location(self, new):
        self.__location = new
    def get_energy(self):
        return self.__energy
    def set_energy(self, new):
        self.__energy = new
    
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
        
    def produce_energy(self, water, sunlight):
        p = self.get_production_power()
        e = (p*(min((1.0/6)*water, sunlight)) + ((max(.2*water, sunlight))**2)*100)
        self.set_energy(self.get_energy() + e)
        
    def live_one_day(self, world):
        
        Organism.live_one_day(self, world)
        cell = world.get_grid_at_location(self.get_location()[0], self.get_location()[1])
        self.produce_energy(cell.get_water(), cell.get_sunlight())

class Animal(Organism):
    def __init__(self, energy, lifeSpan, location, upkeep, foods):
        super().__init__(energy, lifeSpan, location, upkeep)
        self.__fill = 0 #represents hunger, a value between 0 (empty) and 1 (full)
        self.__foods = foods #dict of key:foods animal can eat, values: how much fill increases
        #assumption is that foods' keys are Classes themselves, 
        #not instances of classes for hunt function
    
    def consume(self, organism, world):
        new_energy = self.get_energy() + organism.get_energy()/10.0
        self.set_energy(new_energy)
        self.__fill += self.__foods.get(type(organism), 0)
        if self.__fill > 1:
            self.__fill = 1
        cell = world.get_grid_at_location(self.get_location()[0], self.get_location()[1])
        cell.delete_organism(organism)
    
    def hunt(self, world):

        if isinstance(self, Lion):
            min_hunt_energy = 5000 * 2.0/ 3
        elif isinstance(self, Wildebeest):
            min_hunt_energy = 7500 * 2.0/3
        cell = world.get_grid_at_location(self.get_location()[0], self.get_location()[1])
        while self.get_energy() > min_hunt_energy and self.__fill < 1:
            while True:
                hunted_organism = random.choice(cell.get_organisms())

                if hunted_organism != self:
                    break
                
            if type(hunted_organism) in self.__foods:
                self.consume(hunted_organism, world)
            else:
                self.set_energy(self.get_energy()-100)
                
    def migrate(self, world):
        if self.__fill < 1:
            world.move_organism(self)
    
    def live_one_day(self, world):
        self.__fill = 0
        Organism.live_one_day(self, world)
        self.hunt(world)
        self.migrate(world)
        
class Grass(Plant):
    def __init__(self,location):
        self.__production_power = 10
        energy = 50
        lifespan = 5
        upkeep = 2
        super().__init__(energy, lifespan, location, upkeep)
    
    def get_production_power(self):
        return self.__production_power
    def set_produciton_power(self, new):
        self.__production_power = new
        
class Wildebeest(Animal):
    def __init__(self,location):
        energy = 7500
        lifespan = 30
        upkeep = 100
        foods = {Grass:0.05} 
        super().__init__(energy, lifespan, location, upkeep, foods)
        
class Lion(Animal):
    def __init__(self, location):
        energy = 5000
        lifespan = 25
        upkeep = 50
        foods = {Wildebeest:0.25}
        super().__init__(energy, lifespan, location, upkeep, foods)

