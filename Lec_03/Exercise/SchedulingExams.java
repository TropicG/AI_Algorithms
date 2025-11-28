public class SchedulingExams {

    static public String[] exams = {"Maths", "Programming", "History", "Literature"};
    static public String[] days = {"Off", "Monday", "Tuesday", "Wednesday"};

    public static int MATHS = 0;
    public static int PROGRAMMING = 1;
    public static int HISTORY = 2;
    public static int LITERATURE = 3;

    public static int TUESDAY = 2;
    public static int WEDNESDAY = 3;

    /*
       CONSTRAINSTS:
       - Maths exam shall not be on the same day as Programming exam
       - Maths exam shall not be on the same day as History exam
       - History exam shall be before Literature exam
       - Programming exams shall be on Tuesday or Wednesday

     */

    static public int numOfDays = 4;

    // only 3 available days for exams
    static public int[] schedule = new int[4];


    public static void main(String[] args) {
        if(isSolvable(0)) {
            printSolution();
        }
        else {
            System.out.println("No solution!");
        }
    }

    static boolean isSolvable(int currentSubject) {

        // if all the exams are covored return true
        if(currentSubject == exams.length) {
            return true;
        }

        // for each subject and for each day check is it okay to have this exam on this day
        for(int day = 1; day < days.length; day++) {

            // check is it okay to place the exam on this day
            if(isSafe(currentSubject, day)) {

                // in case of success, place the exam on this day
                schedule[currentSubject] = day;

                // check for the other exams, can they be put on other days
                if(isSolvable(currentSubject + 1)) {
                    return true;
                }

                // if this the other exams cant be placed on the other days, reseting the current subject
                // and trying to place it on another day
                schedule[currentSubject] = 0;
            }

        }

        return false;
    }

    static boolean isSafe(int currentSubject, int day) {

        // Math exam cannot be on the same day as Programming and History
        if(currentSubject == MATHS && day != schedule[PROGRAMMING] && day != schedule[HISTORY]) {
            return true;
        }
        // History exam shall be on a different day regarding Maths and shall take place before Literature exam
        else if((currentSubject == HISTORY) && (day != schedule[MATHS]) && (day < schedule[LITERATURE] || schedule[LITERATURE] == 0)) {
            return true;
        }

        // Literature exams shall take place after the day for History
        else if((currentSubject == LITERATURE) && (day > schedule[HISTORY])) {
            return true;
        }

        // Programming exam shall take place on Tuesday or Wednesday but not on the same day as Maths
        else if(currentSubject == PROGRAMMING && (day == TUESDAY || day == WEDNESDAY) && (day != schedule[MATHS])) {
            return true;
        }

        return false;
    }

    public static void printSolution() {
        for(int i = 0; i < days.length; i++) {
            System.out.println("Subject: " + exams[i] + " Day: " + days[schedule[i]]);
        }
    }

}
