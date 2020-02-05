package illumio_coding_challenge.test;

import illumio_coding_challenge.Firewall;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**Test class to accept/reject packet on rules
 * @author Mohit
 *
 */
public class FirewallTest {

	Firewall firewall;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

    @Before
    public void setUp() throws IOException {
        firewall =new Firewall("networkrules.csv");
    }

	@After
	public void tearDown() throws Exception {
	}

	@Test
    public void test() {
        Assert.assertEquals(true, Firewall.acceptPacket("outbound", "tcp", 20000, "192.168.10.11"));
        Assert.assertEquals(true, Firewall.acceptPacket("inbound", "tcp",80,"192.168.1.2"));
        Assert.assertEquals(false, Firewall.acceptPacket("inbound", "tcp",80,"192.168.1.322"));
        Assert.assertEquals(false, Firewall.acceptPacket("inbound", "udp",43,"12.53.6.25"));
        Assert.assertEquals(true, Firewall.acceptPacket("inbound", "tcp",673,"123.45.56.83"));
        Assert.assertEquals(false, Firewall.acceptPacket("inbound", "udp",677,"123.45.56.85"));
        Assert.assertEquals(true, Firewall.acceptPacket("inbound", "tcp",677,"123.45.56.78"));
        Assert.assertEquals(true, Firewall.acceptPacket("inbound", "tcp",670,"123.45.56.85"));
        Assert.assertEquals(false, Firewall.acceptPacket("outbound", "udp",999,"52.12.48.92"));
        Assert.assertEquals(true, Firewall.acceptPacket("outbound", "udp",1500,"52.12.48.92"));
        Assert.assertEquals(true, Firewall.acceptPacket("inbound", "tcp",23,"0.0.0.0"));
        Assert.assertEquals(false, Firewall.acceptPacket("outbound", "udp",23,"255.255.255.255"));
        Assert.assertEquals(true, Firewall.acceptPacket("inbound", "tcp",610,"255.255.255.255"));
    }

	
	
}



