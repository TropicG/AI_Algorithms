import java.util.Arrays;
import java.util.Random;

public class Main {

    private static final int POPULATION_SIZE = 100;

    private static final String TARGET = "AYTOS";
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static int BEST_CANDIDATES = 10;

    private static final double MUTATION_RATE = 0.05;

    public Random random = new Random();

    public class Chromosome implements Comparable<Chromosome>{
        char[] genes;
        int fitnessScore;

        public Chromosome() {
            // each gene will contain random letter
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

        // creating the population of chromosomes
        Chromosome[] population = new Chromosome[POPULATION_SIZE];
        for(int i = 0; i < population.length; i++) {
            population[i] = new Chromosome();
            population[i].calculateFitness();
            System.out.println(population[i]);
        }

        int generation = 1;

        while(true) {

            System.out.println("Top Chromosome: " + population[0] + " " + population[0].getFitnessScore() + " | Generation " + generation);

            if(population[0].getFitnessScore() == TARGET.length()) {
                System.out.println("Top Chromosome: " + population[0] + " " + population[0].getFitnessScore() + " | Generation " + generation);
                System.out.println("Target Found");
                return;
            }

            // the best Chromosomes are going to in front
            Arrays.sort(population);

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

                Chromosome child = crossover(parent1, parent2);

                mutate(child);

                child.calculateFitness();
                nextGeneration[i] = child;
            }

            population = nextGeneration;
            generation++;
        }
    }

    // chosing of parents
    public Chromosome tournamentSelection(Chromosome[] population) {

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

        Chromosome child = new Chromosome(childGenes);
        return child;
    }

    public void mutate(Chromosome child) {
        for(int i = 0; i < child.getGenes().length; i++){
            if(random.nextDouble() < MUTATION_RATE) {
                child.genes[i] = CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
            }
        }
    }

}
