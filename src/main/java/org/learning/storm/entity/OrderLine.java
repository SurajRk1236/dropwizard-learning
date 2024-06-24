package org.learning.storm.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import static org.learning.storm.constants.ColumnNames.ORDERS_ID;
import static org.learning.storm.constants.ColumnNames.ORDER_LINE_STATE_ID;
import static org.learning.storm.constants.ColumnNames.PRODUCT_NAME;
import static org.learning.storm.constants.TableNames.ORDER_LINE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = ORDER_LINE)
@FieldNameConstants
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ORDERS_ID)
    Orders order;

    @Column(name = PRODUCT_NAME)
    String productName;

    String sku;

    @Min(value = 1)
    int quantity;

    @Min(value = 1)
    double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ORDER_LINE_STATE_ID, nullable = false)
    OrderLineStatesChange state;
}
