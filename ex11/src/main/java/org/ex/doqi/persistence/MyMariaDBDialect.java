package org.ex.doqi.persistence;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * Created by ohjic-donghoon on 2016-02-11.
 */
public class MyMariaDBDialect extends MySQL5InnoDBDialect {

    @Override
    public String getTableTypeString() {
        return "ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;";
    }

}