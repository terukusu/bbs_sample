package org.terukusu.example.sample1;

public class SampleDaoFactoryImpl implements SampleDaoFactory {

    @Override
    public SamplePersonDao createSamplerPersonDao() {
        return new SamplePersonDao();
    }

}
