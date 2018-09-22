package org.terukusu.example.bbs.entity;

import java.util.Calendar;

import org.terukusu.example.util.BeanUtil;
import org.terukusu.example.util.db.dao.Entity;

public class Message implements Entity<Integer> {
    private Integer id;
    private Integer writerId;
    private String message;
    private Person writer;
    private Calendar createdAt;
    private Calendar updatedAt;

    public Message() {
        super();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return BeanUtil.dump(this);
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the writerId
     */
    public Integer getWriterId() {
        return writerId;
    }

    /**
     * @param writerId the writerId to set
     */
    public void setWriterId(Integer writerId) {
        this.writerId = writerId;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the writer
     */
    public Person getWriter() {
        return writer;
    }

    /**
     * @param writer the writer to set
     */
    public void setWriter(Person writer) {
        this.writer = writer;
    }

    /**
     * @return the createdAt
     */
    public Calendar getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the updatedAt
     */
    public Calendar getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt the updatedAt to set
     */
    public void setUpdatedAt(Calendar updatedAt) {
        this.updatedAt = updatedAt;
    }
}
