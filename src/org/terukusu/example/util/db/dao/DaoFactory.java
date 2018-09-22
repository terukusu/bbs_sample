package org.terukusu.example.util.db.dao;

import java.lang.reflect.InvocationTargetException;

import org.terukusu.example.util.Config;
import org.terukusu.example.util.db.DataAccessException;

public interface DaoFactory {

    /**
     * {@link DaoFactory} の実装クラスのインスタンスを取得します。
     * 
     * @return {@link DaoFactory} の実装クラスのインスタンスです。
     */
    public static DaoFactory getInstance() {
        String implName = Config.get("db.dao.factory.class");
        DaoFactory daoFactoryImpl = null;
        try {
            daoFactoryImpl = (DaoFactory) Class.forName(implName).getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            throw new DataAccessException(e);
        }

        return daoFactoryImpl;
    }
}
