package mvc.entity;


import javax.persistence.*;

@Entity
@Table(name = "order_details")
public class OrderDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderDetailId")
    private int orderDetailId;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name= "orderId")
    private OrderEntity ordersEntity;

    @ManyToOne
    @JoinColumn(name= "productId")
    private ProductEntity productEntity;



    public OrderDetailsEntity() {
    }


    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderEntity getOrdersEntity() {
        return ordersEntity;
    }

    public void setOrdersEntity(OrderEntity ordersEntity) {
        this.ordersEntity = ordersEntity;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }
}
