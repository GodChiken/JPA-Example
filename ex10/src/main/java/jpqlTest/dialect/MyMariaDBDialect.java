package jpqlTest.dialect;

import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

/**
 * Created by Kim Donghoon on 2015-12-26.
 */
public class MyMariaDBDialect extends MySQLDialect {
    public MyMariaDBDialect() {
        registerFunction("get_a", new StandardSQLFunction("get_a", StandardBasicTypes.STRING));
    }
}
