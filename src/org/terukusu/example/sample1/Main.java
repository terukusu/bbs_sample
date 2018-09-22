package org.terukusu.example.sample1;

import java.util.List;

import org.terukusu.example.util.Log;
import org.terukusu.example.util.db.ConnectionManager;
import org.terukusu.example.util.db.QueryUtil;
import org.terukusu.example.util.db.dao.DaoFactory;

public class Main {
    public static void main(String[] args) {
        
        // Excecute SQL sample
        String sql = "SELECT * from sample_person";
        List<SamplePerson> list = QueryUtil.getInstance().queryAsBean(sql, SamplePerson.class);
        Log.debug("all person records: " + list);
        
        // DAO Sample
        SampleDaoFactory daoFactory = (SampleDaoFactory)DaoFactory.getInstance();
        SamplePersonDao dao = daoFactory.createSamplerPersonDao();

        // SELECT
        SamplePerson person = dao.findById(1);
        Log.debug("person: " + person);
        
        // UPDATE
        person.setName(person.getName().split(":")[0] + ":" + System.currentTimeMillis());
        SamplePerson newPerson = dao.save(person);
        Log.debug("new person: " + newPerson);
        
        ConnectionManager.getInstance().rollback();
    }
}
