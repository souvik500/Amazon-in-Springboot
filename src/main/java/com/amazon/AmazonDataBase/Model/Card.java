package com.amazon.AmazonDataBase.Model;

import com.amazon.AmazonDataBase.Enum.CardType;
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
public class Card
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardId;

    private String cardNo;

    private int cvv;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    /**
     * Relation b/w child (Card) to parent (Customer) [Many : 1]
     */
    @ManyToOne
    @JoinColumn
    Customer customer;

}
