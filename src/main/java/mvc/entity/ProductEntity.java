package mvc.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="productId")
    private int productId;

    @Column(name = "productName")
    private String productName;

    @Column(name = "productDescription")
    private String productDescription;

    @Column(name = "unitPrice")
    private int unitPrice;

    @OneToMany(mappedBy = "productEntity",fetch = FetchType.EAGER)
    private List<OrderDetailsEntity> orderDetailsEntityList;

    public ProductEntity() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public List<OrderDetailsEntity> getOrderDetailsEntityList() {
        return orderDetailsEntityList;
    }

    public void setOrderDetailsEntityList(List<OrderDetailsEntity> orderDetailsEntityList) {
        this.orderDetailsEntityList = orderDetailsEntityList;
    }
}
