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
@Table(name = "weight_checks")
public class WeightCheckEntity {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "weight_check_generator")
    @SequenceGenerator(name = "weight_check_generator", sequenceName = "weight_check_id_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "weight_check_value")
    private BigDecimal value;

    @Column(name = "check_time")
    private LocalDate checkTime;
}
