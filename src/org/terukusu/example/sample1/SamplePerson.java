package org.terukusu.example.sample1;

import java.util.Calendar;

import org.terukusu.example.util.BeanUtil;
import org.terukusu.example.util.db.dao.Entity;

public class SamplePerson implements Entity<Integer> {

    private Integer id;
    private String name;
    private Integer age;
    private Calendar createdAt;
    private Calendar updatedAt;
    
    public SamplePerson() {
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
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the age
     */
    public Integer getAge() {
        return age;
    }
    /**
     * @param age the age to set
     */
    public void setAge(Integer age) {
        this.age = age;
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
