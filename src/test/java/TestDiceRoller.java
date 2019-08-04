import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.PageFactory;
import pages.RandomOrg.DiceOneRoller;
import pages.RandomOrg.DiceTwoRoller;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static org.testng.Assert.assertTrue;

public class TestDiceRoller {

    private static Logger LOG = LoggerFactory.getLogger(TestDiceRoller.class);
    private DiceOneRoller diceOneRoller = new DiceOneRoller();
    private DiceTwoRoller diceTwoRoller = new DiceTwoRoller();
    PageFactory pageFactory = new PageFactory();

    @BeforeMethod
    public void beforeMethod() {
        pageFactory.goToRandomOrg();
    }

    @Test
    public void testMaximumDeviationOfDiceResultsInWithinBorder() {
        pageFactory.goToRandomOrgDiceRoller(1);
        ArrayList<Integer> listOfDiceNumber = new ArrayList<>();
        getListOfDiceNumber(listOfDiceNumber, 1000);
        assertTrue(getStandardDeviation(listOfDiceNumber) <= 2.7);
    }

    @Test
    public void testMaximumDeviationOfTwoDiceResultsInWithinBorder() {
        pageFactory.goToRandomOrgDiceRoller(2);
        ArrayList<Integer> listOfDiceNumber = new ArrayList<>();
        getListForTwoDiceNumber(listOfDiceNumber, 1000);
        System.out.println(getStandardDeviation(listOfDiceNumber));
        assertTrue(getStandardDeviation(listOfDiceNumber) <= 5.4);
    }

    private void getListOfDiceNumber(ArrayList<Integer> listOfNumbers, int count) {
        IntStream.range(0, count)
                .mapToObj(i -> diceOneRoller.getNumberOnDiceAfterRoller(1))
                .map(Integer::parseInt)
                .forEachOrdered(listOfNumbers::add);
        listOfNumbers.stream()
                .map(listOfNumber -> listOfNumber + ", ")
                .forEachOrdered(e -> LOG.info(e));
    }

    private void getListForTwoDiceNumber(ArrayList<Integer> listOfNumbers, int count) {
        IntStream.range(0, count)
                .mapToObj(i -> diceTwoRoller.getNumberSumDiceAfterRoller())
                .map(Integer::parseInt)
                .forEachOrdered(listOfNumbers::add);
        listOfNumbers.stream()
                .map(listOfNumber -> listOfNumber + ", ")
                .forEachOrdered(System.out::print);
    }

    private double getStandardDeviation(ArrayList<Integer> listOfNumbers) {
        double sum = 0.0, standardDeviation = 0.0;
        int length = listOfNumbers.size();
        for (double num : listOfNumbers) {
            sum += num;
        }
        double mean = sum / length;
        for (double num : listOfNumbers) {
            standardDeviation += Math.pow(num - mean, 2);
        }
        return Math.sqrt(standardDeviation / length);
    }
}
