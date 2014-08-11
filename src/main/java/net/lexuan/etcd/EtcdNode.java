package net.lexuan.etcd;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * Etcd Node Object
 * 
 * @author Upton
 * 
 */
public class EtcdNode {
    // base values
    private String key;
    private long createdIndex;
    private long modifiedIndex;
    private String value;

    // TTL keys
    private String expiration;
    private Integer ttl;

    // listings
    private boolean dir;
    private List<EtcdNode> nodes;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getCreatedIndex() {
        return createdIndex;
    }

    public void setCreatedIndex(long createdIndex) {
        this.createdIndex = createdIndex;
    }

    public long getModifiedIndex() {
        return modifiedIndex;
    }

    public void setModifiedIndex(long modifiedIndex) {
        this.modifiedIndex = modifiedIndex;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    public boolean isDir() {
        return dir;
    }

    public void setDir(boolean dir) {
        this.dir = dir;
    }

    public List<EtcdNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<EtcdNode> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}