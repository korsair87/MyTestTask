import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.PageFactory;
import pages.RandomOrg.DiceOneRoller;
import pages.RandomOrg.DiceTwoRoller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        System.out.println();
        System.out.println("Maximum Deviation in percent is: " + getMaxDeviation(listOfDiceNumber));
        assertTrue(getMaxDeviation(listOfDiceNumber) <= 0.05);
    }

    @Test
    public void testMaximumDeviationOfTwoDiceResultsInWithinBorder() {
        pageFactory.goToRandomOrgDiceRoller(2);
        ArrayList<Integer> listOfDiceNumber = new ArrayList<>();
        getListForTwoDiceNumber(listOfDiceNumber, 1000);
        System.out.println();
        System.out.println("Maximum Deviation in percent is: " + getMaxDeviation(listOfDiceNumber));
        assertTrue(getMaxDeviation(listOfDiceNumber) <= 0.05);
    }

    private void getListOfDiceNumber(ArrayList<Integer> listOfNumbers, int count) {
        IntStream.range(0, count)
                .mapToObj(i -> diceOneRoller.getNumberOnDiceAfterRoller(1))
                .map(Integer::parseInt)
                .forEachOrdered(listOfNumbers::add);
        listOfNumbers.stream()
                .map(listOfNumber -> listOfNumber + ", ")
                .forEachOrdered(System.out::print);
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

    private double getMaxDeviation(ArrayList<Integer> listOfNumbers) {
        Integer sum = listOfNumbers.stream()
                .reduce(0, Integer::sum);
        double mean = sum / listOfNumbers.size();
        List deviation = new ArrayList<>();
        for (double num : listOfNumbers) {
            deviation.add(Math.abs(mean - num));
        }
        return mean/100* (double) Collections.max(deviation);
    }
}
