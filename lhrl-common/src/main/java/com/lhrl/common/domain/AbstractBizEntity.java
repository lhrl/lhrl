package com.lhrl.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lhrl.common.model.BizEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Mei Xianzhi
 * @param <PK>
 */
@MappedSuperclass
public abstract class AbstractBizEntity<PK extends Serializable> extends AbstractPersistEntity<PK> implements BizEntity {

    @Version
    private Long version;

    @Column(name = "creator", nullable = false, updatable = false)
    private String creator;

    @Temporal(value = TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time", nullable = false, updatable = false)
    private Date createTime;

    @Column(name = "updator", nullable = false)
    private String updator;

    @Temporal(value = TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "update_time", nullable = false)
    private Date updateTime;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
