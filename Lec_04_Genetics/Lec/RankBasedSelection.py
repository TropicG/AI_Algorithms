def rank_based_selection(population, fitness_scores, num_selections):
   # Rank individuals based on fitness
    sorted_indices = np.argsort(fitness_scores)
    sorted_fitness = [(i, fitness_scores[i], population[i]) for i in sorted_indices]
    print("Sorted indices and their scores:", sorted_fitness)

    ranks = np.arange(1, len(fitness_scores)+1)
    print("Ranks:", ranks)

    probs = ranks / np.sum(ranks)
    print("Selection probabilities:", probs)
    print("Elements by rank: ", np.array(population)[sorted_indices])

    chosen = np.random.choice(np.array(population)[sorted_indices], size=num_selections, p=probs)
    return chosen

selected = rank_based_selection(population, fitness_scores, num_selections=3)
print("Selected chromosomes:", selected)