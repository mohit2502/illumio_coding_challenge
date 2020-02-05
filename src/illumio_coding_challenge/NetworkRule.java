package illumio_coding_challenge;

/**
 * @author Mohit
 * wrapper class to contain a network rule used for hashing key
 */
public class NetworkRule {
    protected String direction;
    protected String protocol;
    protected int port;
    protected long ipAddress;
    protected long hashCode;

    
    /**constructor for building rules
     * @param direction
     * @param protocol
     * @param port
     * @param ipAddress
     */
    public NetworkRule(String direction, String protocol, int port, long ipAddress) {
        this.direction = direction;
        this.protocol = protocol;
        this.port = port;
        this.ipAddress = ipAddress;
        this.hashCode =  13 * (this.ipAddress + this.port + direction.hashCode() + protocol.hashCode()); //get unique key from all the components
    }

    /**
     * @return
     */
    public long gethashCode() {
        return this.hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NetworkRule)) return false;
        NetworkRule networkRule = (NetworkRule) o;
        return  direction == networkRule.direction && protocol == networkRule.protocol && port == networkRule.port && ipAddress == networkRule.ipAddress;
    }

    @Override
    public String toString() {
        return this.direction +  ", " + this.protocol + ", " + Integer.toString(this.port) + ", " + Long.toString(this.ipAddress);
    }


    /**
     * @return
     */
    public long hash() {
        long hash =  13 * (this.ipAddress + this.port + this.direction.hashCode() + this.protocol.hashCode()); //get unique key from all the components
        return Long.valueOf(hash).hashCode();
    }

}

