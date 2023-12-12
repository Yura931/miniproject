package subproject.admin.common.util;

import com.querydsl.core.types.NullExpression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import subproject.admin.common.enums.SortDirection;

public class OrderByNull extends OrderSpecifier {

    private static final OrderByNull DEFAULT = new OrderByNull();

    private OrderByNull() {
        super(Order.ASC, NullExpression.DEFAULT, NullHandling.Default);
    }

    public static OrderByNull getDefault() {
        return OrderByNull.DEFAULT;
    }

}
