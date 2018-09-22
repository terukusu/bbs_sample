package org.terukusu.example.sample1;

import org.terukusu.example.util.db.dao.DaoFactory;

public interface SampleDaoFactory extends DaoFactory {
    SamplePersonDao createSamplerPersonDao();
}
