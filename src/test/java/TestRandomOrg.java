import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;


public class TestRandomOrg {

    @BeforeMethod
    public void beforeMethod() {
        open("https://www.random.org/dice/?num=1");
    }

    @Test
    public void test() {
        List myList = new List();
        for (int i = 0; i < 20; i++) {
            clickRoleAgainButton();
            String a = $x("//*[@id='invisible']/p[3]/img").getAttribute("alt");
            myList.add(a);
            System.out.println(a);
        }
    }
    private String rollAgainButton = "//input[@value='Roll Again']";

    public TestRandomOrg clickRoleAgainButton(){
        $x(rollAgainButton).click();
        return this;
    }

    public void getRolledDie(){
        $x("//*[@id='invisible']/p[3]/img").getAttribute("alt");
    }


}
