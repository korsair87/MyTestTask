package pages;

import pages.RandomOrg.NewRandomOrgPage;

import static com.codeborne.selenide.Selenide.open;

public class PageFactory {
    private String urlRandomOrg = "https://www.random.org/";

    public NewRandomOrgPage goToRandomOrg() {
        open(urlRandomOrg);
        return new NewRandomOrgPage();
    }

    public NewRandomOrgPage goToRandomOrgDiceRoller(int numberOfRoller) {
        open(urlRandomOrg + "/dice/?num=" + numberOfRoller);
        return new NewRandomOrgPage();
    }
}
