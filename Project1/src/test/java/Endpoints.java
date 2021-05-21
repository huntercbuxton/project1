import com.ex.model.client.LoginRequest;
import com.ex.model.client.LoginResponse;
import com.ex.model.store.Account;
import com.ex.model.store.dao.DAO;
import com.ex.service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Endpoints {

    Service service;

    @BeforeEach
    public void beforeEachTest() {
        DAO dao = new DAO("project1");
        this.service = new Service(dao);
    }

    @Test
    public void testEmployeeLoginResponse() {
        LoginResponse response =  service.validLoginInfo(new LoginRequest("whozit@wrinkle.com", "it", "employee"));
        assertNotNull(response, "the login should return a response even if the login fails.");
    }

    @Test
    public void testManagerLoginResponse() {
        LoginResponse response =  service.validLoginInfo(new LoginRequest("whozit@wrinkle.com", "it", "manager"));
        assertNotNull(response, "the login should return a response even if the login fails.");
    }

    @Test
    public void testSuccessfulLogin() {
        Account account = new Account("example@email.com", "password", "employee", "x", "x", "x", new ArrayList<>());
        service.getDao().addAccount(account);
        LoginResponse loginResponse = service.validLoginInfo(new LoginRequest("example@email.com", "password", "employee"));
        assertEquals(loginResponse.getErrCode(), 0, "0 indicated successful login info");
    }

    @Test
    public void testSuccessfulManagerLogin() {
        Account account = new Account("example@email.com", "password", "manager", "x", "x", "x", new ArrayList<>());
        service.getDao().addAccount(account);
        LoginResponse loginResponse = service.validLoginInfo(new LoginRequest("example@email.com", "password", "manager"));
        assertEquals(loginResponse.getErrCode(), 0, "0 indicated successful login info");
    }

}
