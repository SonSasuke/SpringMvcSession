package mvc.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order_table")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private int orderId;

    @Column(name = "orderDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;

    @NotEmpty(message = "Must have not empty")
    @Column(name = "customerName")
    private String customerName;

    @Column(name = "customerAddress")
    private String customerAddress;

    @OneToMany(mappedBy = "ordersEntity",fetch = FetchType.EAGER)
    private List<OrderDetailsEntity> orderDetailsEntityList;


    public OrderEntity() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public List<OrderDetailsEntity> getOrderDetailsEntityList() {
        return orderDetailsEntityList;
    }

    public void setOrderDetailsEntityList(List<OrderDetailsEntity> orderDetailsEntityList) {
        this.orderDetailsEntityList = orderDetailsEntityList;
    }
}
