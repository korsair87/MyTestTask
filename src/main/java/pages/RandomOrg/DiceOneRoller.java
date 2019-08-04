package pages.RandomOrg;

public class DiceOneRoller extends NewRandomOrgPage{

    public String getNumberOnDiceAfterRoller(int index) {
        clickRoleAgainButton();
        return  getNumberOnDice(index);
    }
}
