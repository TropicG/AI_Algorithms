
#Implementation of Tournament Selection with indexes
def tournament_selection(population, fitness_scores, tournament_size, num_selections):
   selected_chromosomes = [] 
   for _ in range(num_selections):
        #choose random indexes of chromosomes from the population for the tournament
        tournament = np.random.choice(len(population), tournament_size, replace=False)

        #chromosomes in the tournament
        selected_population = [population[i] for i in tournament] # for each chosen chromosome for the tournament get its value from population
        print("Tournament participants:", selected_population)
        print("Fitness scores:", [fitness_scores[i] for i in tournament]) # gets the fitnes scores for the selected_population

        #select the index of best chromosome from the tournament
        winner = tournament[np.argmax([fitness_scores[i] for i in tournament])] # 
        print("Tournament winner:", population[winner])
        selected_chromosomes.append(population[winner])

   return selected_chromosomes

selected = tournament_selection(population, fitness_scores, tournament_size=3, num_selections=3)
print("Selected:", selected)

# Tournament participants: ['A', 'E', 'B']
# Fitness scores: [10, 15, 20]
# Tournament winner: B
# Tournament participants: ['B', 'A', 'D']
# Fitness scores: [20, 10, 25]
# Tournament winner: D
# Tournament participants: ['B', 'A', 'D']
# Fitness scores: [20, 10, 25]
# Tournament winner: D
# Selected: ['B', 'D', 'D']
