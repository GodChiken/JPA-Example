package querydsl.app;

import com.mysema.query.annotations.QueryDelegate;
import com.mysema.query.types.expr.BooleanExpression;
import oopQueryModel.Member;
import oopQueryModel.Product;
import oopQueryModel.QMember;
import oopQueryModel.QProduct;

/**
 * Created by Kim Donghoon on 2016-01-10.
 */
public class ProductExpression {
    @QueryDelegate(Product.class)
    public static BooleanExpression isExpression(QProduct product, Integer price) {
        return product.price.gt(price);
    }

    @QueryDelegate(Member.class)
    public static BooleanExpression helloStart(QMember member) {
        return member.name.startsWith("hello");
    }
}
