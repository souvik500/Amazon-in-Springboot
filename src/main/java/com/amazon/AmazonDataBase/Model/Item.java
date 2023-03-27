package com.amazon.AmazonDataBase.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int requiredQuantity;

    /**
     * Relation b/w Child (Item) to Parent(Product) [1 : 1]
     */
    @OneToOne
    @JoinColumn
    Product product;

    /**
     * Relation b/w Child (Item) to Parent(Cart) [Many : 1]
     */
    @ManyToOne
    @JoinColumn
    Cart cart;

    /**
     * Relation b/w Child (Item) to Parent(Order) [Many : 1]
     */
    @ManyToOne
    @JoinColumn
    Ordered ordered;
}
