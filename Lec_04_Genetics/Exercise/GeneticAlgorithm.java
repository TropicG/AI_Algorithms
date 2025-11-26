import java.util.Arrays;
import java.util.Random;

public class GeneticAlgorithm {

    // one generation will ahve a population of 100 chromosomes
    private static final int POPULATION_SIZE = 100;

    // the Genetic algorithm will try to find a generation that contains the target string
    private static final String TARGET = "AYTOS";

    // every newly created chromosomes will pick random characters from this string
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // for every next generation it will have the best 10 chromosomes from the last generation
    private static int BEST_CANDIDATES = 10;

    // every gene of a chromosomes have a %0.05 percent chance of mutaion
    private static final double MUTATION_RATE = 0.05;

    public Random random = new Random();

    public class Chromosome implements Comparable<Chromosome>{

        // a gene will contain a letter from A-Z
        char[] genes;

        // the fitness score will be terminated on which characters are on the right place comapred to TARGET
        // example: AXTOZ will have a fitness score of 3, A,T,O are on the same place
        int fitnessScore;

        public Chromosome() {
            // each gene will contain random letter, selected from CHARACTERS
            genes = new char[TARGET.length()];
            for(int i = 0 ; i < genes.length; i++) {
                genes[i] = CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
            }
            fitnessScore = 0;
        }

        public Chromosome(char[] genes) {
            this.genes = genes;
        }

        public void calculateFitness() {
            int currentFitnessScore = 0;
            for(int i = 0; i < genes.length; i++){
                if(genes[i] == TARGET.charAt(i)) {
                    currentFitnessScore++;
                }
            }

            fitnessScore = currentFitnessScore;
        }

        public int getFitnessScore() {
            return fitnessScore;
        }

        public char[] getGenes() {
            return genes;
        }

        @Override
        public String toString() {
            return " " + new String(genes) + " "  + fitnessScore;
        }

        @Override
        public int compareTo(Chromosome other) {
            return Integer.compare(other.fitnessScore, this.fitnessScore);
        }
    }

    public void main(String[] args) {

        // creating the population of chromosomes, it will have a random generated strings at firsts
        Chromosome[] population = new Chromosome[POPULATION_SIZE];
        for(int i = 0; i < population.length; i++) {
            population[i] = new Chromosome();
            population[i].calculateFitness();
            System.out.println(population[i]);
        }

        int generation = 1;

        while(true) {

            System.out.println("Top Chromosome: " + population[0] + " " + population[0].getFitnessScore() + " | Generation " + generation);

            // the best Chromosomes are going to in front
            Arrays.sort(population);

            // if the best candidate is the AYTOS strig
            if(population[0].getFitnessScore() == TARGET.length()) {
                System.out.println("Top Chromosome: " + population[0] + " " + population[0].getFitnessScore() + " | Generation " + generation);
                System.out.println("Target Found");
                return;
            }


            // saving the best chromosomes directly to the next gen
            Chromosome[] nextGeneration = new Chromosome[POPULATION_SIZE];
            for(int i = 0; i < BEST_CANDIDATES; i++){
                nextGeneration[i] = population[i];
            }

            // other 90 percent are going to be selected through SELECTOR
            for(int i = 10; i < POPULATION_SIZE; i++) {

                // selecting the two parents
                Chromosome parent1 = tournamentSelection(population);
                Chromosome parent2 = tournamentSelection(population);

                // creating their child
                Chromosome child = crossover(parent1, parent2);

                // checking for mutation of the child
                mutate(child);
                child.calculateFitness();

                // adding the child into the next generation
                nextGeneration[i] = child;
            }

            population = nextGeneration;
            generation++;
        }
    }

    // chosing of parents
    public Chromosome tournamentSelection(Chromosome[] population) {

        // 5 random chromosomes are going to be selected from the population
        // the best of those 5 is going to be one of the parents
        Chromosome bestChromosomeFound = null;
        for(int i = 0; i < 5; i++){
            Chromosome potentialParent = population[random.nextInt(POPULATION_SIZE)];
            if(bestChromosomeFound == null || bestChromosomeFound.getFitnessScore() < potentialParent.getFitnessScore()){
                bestChromosomeFound = potentialParent;
            }
        }

        return bestChromosomeFound;
    }

    public Chromosome crossover(Chromosome parent1, Chromosome parent2) {

        // everything before midpoint will be taken from parent one and from parent 2 afterwards
        char[] childGenes = new char[TARGET.length()];
        int midpoint = random.nextInt(TARGET.length());
        for(int i = 0; i < TARGET.length(); i++){
            if(i < midpoint) {
                childGenes[i] = parent1.getGenes()[i];
            }
            else {
                childGenes[i] = parent2.getGenes()[i];
            }
        }

        // returning the child
        return new Chromosome(childGenes);
    }

    public void mutate(Chromosome child) {
        for(int i = 0; i < child.getGenes().length; i++){

            // every gene (letter) from the chromosome has a chance of mutation
            if(random.nextDouble() < MUTATION_RATE) {
                child.genes[i] = CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
            }
        }
    }
}
