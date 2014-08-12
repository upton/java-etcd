package net.lexuan.etcd;

public class Cluster {
    private String[] machines;
    private String leader;

    public Cluster(String[] machines) {
        this.machines = machines;
    }

    public String[] getMachines() {
        return machines;
    }

    public void setMachines(String[] machines) {
        this.machines = machines;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }
    
    
}