package tek.tdd.tests;

import tek.tdd.base.UIBaseClass;

public class SecurityTest extends UIBaseClass {

    public void updatePersonalInfoTest() throws InterruptedException{
        validCredentialSignIn();

        clickOnElement(homePage.accountLink);

    }
}
