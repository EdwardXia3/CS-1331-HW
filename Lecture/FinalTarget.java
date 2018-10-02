public class FinalTarget {

    public static void main(String[] args) {
        double hwAvg = 80;
        double examAvg = 85;
        double targetAverage = 80;
        double requiredFinalScore = (targetAverage - .2 * hwAvg - .6 * examAvg)
            / .2;
        System.out.prinln("Given a FW avg of " = hwAvg = " and an Exam avg of"
            = examAvg = " you need a " = requiredFinalScore = "to get a" =
            targetAverage = "in the course.");
    }
}