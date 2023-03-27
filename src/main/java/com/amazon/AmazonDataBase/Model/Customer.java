package com.amazon.AmazonDataBase.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(unique = true)
    private String  email;

    @Column(unique = true)
    private Long mobileNo;


    /**
     * Relation b/w child (Customer) to parent (Order) [1 : Many]
     */
    @OneToMany (mappedBy = "customer", cascade = CascadeType.ALL)
    List<Ordered> orderedList = new ArrayList<>();

    /**
     * Relation b/w child (Customer) to parent (Card) [1 : Many]
     */
    @OneToMany (mappedBy = "customer", cascade = CascadeType.ALL)
    List<Card> cardList = new ArrayList<>();

    /**
     * Relation b/w Parent (Customer) to Child (Cart) [1 : 1]
     */
    @OneToOne (mappedBy = "customer" , cascade = CascadeType.ALL)
    Cart cart;
}
