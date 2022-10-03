package com.dunglq.cms.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {
    @Id
    @Column(name = "cardId")
    private Long cardId;
    @Column(name = "type")
    private String type;
    @Column(name = "userId")
    private long userId;
}
