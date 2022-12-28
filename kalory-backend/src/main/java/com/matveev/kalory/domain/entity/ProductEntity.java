package com.matveev.kalory.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.SEQUENCE;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "product_generator")
    @SequenceGenerator(name = "product_generator", sequenceName = "product_id_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "base_amount")
    private BigDecimal baseAmount;

    @Enumerated(value = STRING)
    @Column(name = "base_amount_type")
    private AmountType baseAmountType;

    @Column(name = "base_calories")
    private BigDecimal baseCalories;

    @Column(name = "base_carbs")
    private BigDecimal baseCarbs;

    @Column(name = "base_lipids")
    private BigDecimal baseLipids;

    @Column(name = "base_proteins")
    private BigDecimal baseProteins;
}
