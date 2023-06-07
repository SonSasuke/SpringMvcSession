package mvc.repository;


import mvc.entity.OrderDetailsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository  extends CrudRepository<OrderDetailsEntity, Integer> {

    @Query(value = "select order_details.orderDetailId,order_details.orderId,order_details.productId,order_details.quantity \n" +
            "from order_details join product \n" +
            "on order_details.productId=product.productId \n" +
            "where orderId=?1",nativeQuery = true)
    List<OrderDetailsEntity> findCartDetailsByOrderId(int orderId);
}
