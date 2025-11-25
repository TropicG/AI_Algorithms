import numpy as np

# all the chromosomes
population = ['A', 'B', 'C', 'D', 'E']

# the fitnes_scores of all chromosomes
fitness_scores = [10, 20, 30, 25, 15] #higher is better

def roulette_wheel_selection(population, fitness_scores, num_selections):
    total_fitness = sum(fitness_scores) # sums the total fitness of the chromosomes
    selection_probs = [f / total_fitness for f in fitness_scores] # each elements reprecend chromose's fitnes / total_fitnes
    selected_chromosomes = np.random.choice(population, size=num_selections, replace=True, p=selection_probs); 
    return selected_chromosomes;

selected = roulette_wheel_selection(population, fitness_scores, 3)
print("Selected chromosomes: ", selected)
