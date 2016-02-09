package eudcApi.rest.filter;

import eudcApi.model.AuthenticatedUser;
import eudcApi.model.User;
import org.easymock.EasyMockRunner;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(EasyMockRunner.class)
public class EudcApiPrincipalTest {

    AuthenticatedUser authenticatedUser = getUser();

    @TestSubject
    private EudcApiPrincipal EudcApiPrincipal = new EudcApiPrincipal(authenticatedUser);

    @Test
    public void getName() {
        assertEquals("admin@admin.kz", EudcApiPrincipal.getName());
    }

    @Test
    public void getToken() {
        assertNull(EudcApiPrincipal.getSecurityToken());
    }

    @Test
    public void isUserInRole() {
        assertTrue(EudcApiPrincipal.isUserInRole("USER"));
    }

    private AuthenticatedUser getUser() {
        AuthenticatedUser authenticatedUser = new AuthenticatedUser();
        User user = new User();
        user.setEmail("admin@admin.kz");
        user.setRole("USER");
        authenticatedUser.setUser(user);

        return authenticatedUser;
    }
}
