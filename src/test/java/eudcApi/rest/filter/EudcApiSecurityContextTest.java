package eudcApi.rest.filter;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(EasyMockRunner.class)
public class EudcApiSecurityContextTest {

    @Mock
    private EudcApiPrincipal EudcApiPrincipal;

    @Mock
    private UriInfo uriInfo;

    @Test
    public void isUserInRoleNull() {
        EudcApiSecurityContext securityContext = getEudcApiSecurityContext(null, uriInfo);

        boolean response = securityContext.isUserInRole("USER");

        assertFalse(response);
    }

    @Test
    public void isUserInRole() {
        EudcApiSecurityContext securityContext = getEudcApiSecurityContext(EudcApiPrincipal, uriInfo);

        expect(EudcApiPrincipal.isUserInRole("USER")).andReturn(true);

        replay(EudcApiPrincipal, uriInfo);

        assertTrue(securityContext.isUserInRole("USER"));

        verify(EudcApiPrincipal, uriInfo);

    }

    @Test
    public void isSecureFalse() throws URISyntaxException {
        EudcApiSecurityContext securityContext = getEudcApiSecurityContext(EudcApiPrincipal, uriInfo);
        URI uri = new URI("http://random.org");

        expect(uriInfo.getRequestUri()).andReturn(uri);

        replay(EudcApiPrincipal, uriInfo);

        assertFalse(securityContext.isSecure());

        verify(EudcApiPrincipal, uriInfo);
    }

    @Test
    public void isSecure() throws URISyntaxException {
        EudcApiSecurityContext securityContext = getEudcApiSecurityContext(EudcApiPrincipal, uriInfo);
        URI uri = new URI("https://random.org");

        expect(uriInfo.getRequestUri()).andReturn(uri);

        replay(EudcApiPrincipal, uriInfo);

        assertTrue(securityContext.isSecure());

        verify(EudcApiPrincipal, uriInfo);
    }

    private EudcApiSecurityContext getEudcApiSecurityContext(EudcApiPrincipal EudcApiPrincipal, UriInfo uriInfo) {
        return new EudcApiSecurityContext(EudcApiPrincipal, uriInfo);
    }
}
