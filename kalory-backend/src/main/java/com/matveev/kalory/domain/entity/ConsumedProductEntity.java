package com.matveev.kalory.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

import static javax.persistence.GenerationType.SEQUENCE;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
@Table(name = "consumed_products")
public class ConsumedProductEntity {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "consumed_product_generator")
    @SequenceGenerator(name = "consumed_product_generator", sequenceName = "consumed_product_id_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "consumption_date")
    private LocalDate consumptionDate;

    @Column(name = "consumption_amount")
    private BigDecimal consumptionAmount;

    @Column(name = "calculated_calories")
    private BigDecimal calculatedCalories;

    @Column(name = "calculated_carbs")
    private BigDecimal calculatedCarbs;

    @Column(name = "calculated_lipids")
    private BigDecimal calculatedLipids;

    @Column(name = "calculated_proteins")
    private BigDecimal calculatedProteins;

    @Column(name = "comment")
    private String comment;
}
