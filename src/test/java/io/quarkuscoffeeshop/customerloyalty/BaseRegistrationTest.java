package io.quarkuscoffeeshop.customerloyalty;

import io.quarkuscoffeeshop.customerloyalty.domain.LoyaltyMember;
import io.quarkuscoffeeshop.customerloyalty.domain.MembershipApplication;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

public abstract class BaseRegistrationTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String CODENAME_REGEX = "^([A-Z]){1}[a-z]*\\1[a-z]*$";

    @Test
    public void testMemberRegistration(){

        logger.info("testing member registration");
        String email = "elwood@bluesbrothers.com";
        MembershipApplication membershipApplication = new MembershipApplication(email);
        LoyaltyMember loyaltyMember = LoyaltyMember.processMembershipApplication(membershipApplication);
        logger.info(loyaltyMember.toString());
        assertNotNull(loyaltyMember);
        assertNotNull(loyaltyMember.getCodeName());
        assertNotNull(loyaltyMember.getEmail());
        assertEquals(email, loyaltyMember.getEmail());
        assertTrue(loyaltyMember.getCodeName().matches(BaseRegistrationTest.CODENAME_REGEX));
    }

    @Test
    public void testRegex() {

        assertTrue("FerretFerret".matches(BaseRegistrationTest.CODENAME_REGEX));
        assertFalse("Ferret".matches(BaseRegistrationTest.CODENAME_REGEX));
    }





}
