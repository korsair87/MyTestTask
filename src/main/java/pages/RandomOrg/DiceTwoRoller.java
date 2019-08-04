package pages.RandomOrg;

public class DiceTwoRoller extends NewRandomOrgPage {

    public String getNumberSingleDiceAfterRoller(int index) {
        clickRoleAgainButton();
        return getNumberOnDice(index);
    }

    public String getNumberSumDiceAfterRoller() {
        clickRoleAgainButton();
        int firstDice = Integer.parseInt(getNumberSingleDiceAfterRoller(1));
        int secondDice = Integer.parseInt(getNumberSingleDiceAfterRoller(2));
        int sum = firstDice + secondDice;
        return String.valueOf(sum);
    }
}
