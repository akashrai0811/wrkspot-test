package customer.demo.demo.entity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
