
import com.ex.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private AbstractApp app;

    @BeforeEach
    public void beforeEachTest() {
        app = new App();
    }

    @AfterEach
    public void afterEachTest() { }

    @Test
    public void testMVCSetup() {
        app.willRun();
        assertNotNull(app.dao, "the app's dao variable should not be null");
    }

    @Test
    public void testMVCSetup1() {
        app.willRun();
        assertNotNull(app.service, "the app's service variable should not be null");
    }

    @Test
    public void testMVCSetup2() {
        app.willRun();
        assertNotNull(app.controller, "the app's controller variable should not be null");
    }

    @Test
    public void testMVCSetup3() {
        app.willRun();
        assertNotNull(app.javApp, "the app's javApp variable should not be null");
    }

}
