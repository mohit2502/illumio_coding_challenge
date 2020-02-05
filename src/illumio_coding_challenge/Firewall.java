package illumio_coding_challenge;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Mohit 
 * This is main class to implement the Firewall rules and check valid packet
 *
 */
public class Firewall {

	/**
	 * Using HashMap<Key, Value> to store separate rules for TCP & UDP In-Out bound
	 */
	protected static HashMap<Long, Boolean> InboundTcp = new HashMap<>();
	protected static HashMap<Long, Boolean> OutboundTcp = new HashMap<>();
	protected static HashMap<Long, Boolean> InboundUdp = new HashMap<>();
	protected static HashMap<Long, Boolean> OutboundUdp = new HashMap<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Firewall f = new Firewall("networkrules.csv");

			boolean test1 = acceptPacket("outbound", "tcp", 20000, "192.168.10.11");
			boolean test2 = acceptPacket("inbound", "tcp", 80, "192.168.1.2");
			boolean test3 = acceptPacket("inbound", "tcp", 80, "192.168.1.322");
			boolean test4 = acceptPacket("inbound", "udp", 43, "12.53.6.25");
			boolean test5 = acceptPacket("inbound", "tcp", 673, "123.45.56.83");
			boolean test6 = acceptPacket("inbound", "udp", 677, "123.45.56.85");
			boolean test7 = acceptPacket("inbound", "tcp", 677, "123.45.56.78");
			boolean test8 = acceptPacket("inbound", "tcp", 670, "123.45.56.85");
			boolean test9 = acceptPacket("outbound", "udp", 999, "52.12.48.92");
			boolean test10 = acceptPacket("outbound", "udp", 1500, "52.12.48.92");
			boolean test11 = acceptPacket("inbound", "tcp", 23, "0.0.0.0");
			boolean test12 = acceptPacket("outbound", "udp", 23, "255.255.255.255");
			boolean test13 = acceptPacket("inbound", "tcp", 610, "255.255.255.255");
			System.out.println(test1);
			System.out.println(test2);
			System.out.println(test3);
			System.out.println(test4);
			System.out.println(test5);
			System.out.println(test6);
			System.out.println(test7);
			System.out.println(test8);
			System.out.println(test9);
			System.out.println(test10);
			System.out.println(test11);
			System.out.println(test12);
			System.out.println(test13);
		} catch (FileNotFoundException e) {
			System.out.println("Exception occurred");
		}

	}

	/**
	 * @param file
	 * @throws FileNotFoundException
	 */
	public Firewall(String file) throws FileNotFoundException {
		// read csv line by line
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			for (String line; (line = br.readLine()) != null;) {

				// split line to individual components
				String[] rule_data = line.split(","); // split string found from oracle java
				String direction = rule_data[0], protocol = rule_data[1], portRange = rule_data[2],
						ipRange = rule_data[3];
				buildRuleMap(direction, protocol, portRange, ipRange);

			}

		} catch (IOException e) {
			System.out.println("Exception occurred");
		}

	}
	
 
	/**
	 * This method builds maps for TCP,UDP In-Out bound
	 * @param direction
	 * @param protocol
	 * @param portRange
	 * @param ipRange
	 */
	private void buildRuleMap(String direction, String protocol, String portRange, String ipRange) {
		String[] port = portRange.split("-");
		int portFrom = Integer.parseInt(port[0]), portTo;
		if (port.length > 1) {
			portTo = Integer.parseInt(port[1]);
		} else {
			portTo = portFrom;
		}

		String[] ip = ipRange.split("-");
		long ipFrom = ipToLong(ip[0]), ipTo;
		if (ip.length > 1) {
			ipTo = ipToLong(ip[1]);
		} else {
			ipTo = ipFrom;
		}
		int portDiff = portTo - portFrom;
		long ipDiff = ipTo - ipFrom;
		
		for (int i = 0; i <= portDiff; i++) {
			for (long j = 0; j <= ipDiff; j++) {
				NetworkRule networkRule = new NetworkRule(direction, protocol, portFrom + i, ipFrom + j);
				if (direction.equals("inbound") && protocol.equals("udp")) {
					InboundUdp.put(networkRule.hash(), Boolean.TRUE);
				} else if (direction.equals("outbound") && protocol.equals("udp")) {
					OutboundUdp.put(networkRule.hash(), Boolean.TRUE);
				} else if (direction.equals("inbound") && protocol.equals("tcp")) {
					InboundTcp.put(networkRule.hash(), Boolean.TRUE);
				} else {
					OutboundTcp.put(networkRule.hash(), Boolean.TRUE);
				}
			}
		}
	}

	/**
	 * This Method return true if rule is defined and accept packet
	 * @param direction
	 * @param protocol
	 * @param port
	 * @param ipAddress
	 * @return
	 */
	public static boolean acceptPacket(String direction, String protocol, int port, String ipAddress) {
		NetworkRule networkRule = new NetworkRule(direction, protocol, port, ipToLong(ipAddress));
		if (direction.equals("inbound") && protocol.equals("udp")) {
			return InboundUdp.containsKey(networkRule.hash());
		} else if (direction.equals("outbound") && protocol.equals("udp")) {
			return OutboundUdp.containsKey(networkRule.hash());
		} else if (direction.equals("inbound") && protocol.equals("tcp")) {
			return InboundTcp.containsKey(networkRule.hash());
		} else {
			return OutboundTcp.containsKey(networkRule.hash());
		}
	}

	/**https://mkyong.com/java/java-convert-ip-address-to-decimal-number/
	 * Converts string ip address to long value
	 * @param ipAddress
	 * @return
	 */
	public static long ipToLong(String ipAddress) {

		String[] ipAddressInArray = ipAddress.split("\\.");

		long result = 0;
		for (int i = 0; i < ipAddressInArray.length; i++) {

			int power = 3 - i;
			int ip = Integer.parseInt(ipAddressInArray[i]);
			result += ip * Math.pow(256, power);

		}

		return result;
	}

	/** https://mkyong.com/java/java-convert-ip-address-to-decimal-number/
	 *  Converts ip address to long
	 * @param ipaslong
	 * @return
	 *
	 */
	public static final String getIPFromLong(final long ipaslong) {
		return String.format("%d.%d.%d.%d", (ipaslong >>> 24) & 0xff, (ipaslong >>> 16) & 0xff, (ipaslong >>> 8) & 0xff,
				(ipaslong) & 0xff);
	}
}
