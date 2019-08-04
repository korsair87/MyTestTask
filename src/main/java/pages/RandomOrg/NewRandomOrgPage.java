package pages.RandomOrg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$x;

public class NewRandomOrgPage {
    private static Logger LOG = LoggerFactory.getLogger(NewRandomOrgPage.class);

    private String rollAgainButton = "//input[@value='Roll Again']";
    private String rollerNumber = "(//*[@id='invisible']/p[3]/img)";

    public NewRandomOrgPage clickRoleAgainButton() {
        $x(rollAgainButton).click();
        return this;
    }

    public String getNumberOnDice(int index) {
        return  $x(rollerNumber + "["+ index + "]").getAttribute("alt");
    }

}
