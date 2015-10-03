//package org.smelser.web.oozie;
//
//import static org.junit.Assert.fail;
//
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.smelser.web.oozie.utilities.EncryptUtils;
//
//public class PortalRestControllerTests {
//
//    @BeforeClass
//    public static void setUpBeforeClass() throws Exception {
//    }
//
//    @AfterClass
//    public static void tearDownAfterClass() throws Exception {
//    }
//
//    @Before
//    public void setUp() throws Exception {
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }
//
//    @Test
//    public final void test() {
//	String cookie = "oozieconsole.com=\"OC.1.2.aHR0cHM6Ly93eHhwMWcxaGQuYXp1cmVoZGluc2lnaHQubmV0fGFkbWlufG0zT2RfdzQ1R2Q=\"; JSESSIONID=1CF6D46B0DD78D5566B64A2CF1F1CFC9";
//
//	// String[] cookieSplit = cookie.split("=")[1].split("\\.");
//	// String[] cook = cookieSplit[1].split("\\.");
//	String[] cookieValue = EncryptUtils.base64decode(cookie.split("=")[1].split("\\.")[3]).split("\\|");
//
//	fail("Not yet implemented"); // TODO
//    }
//
//}
