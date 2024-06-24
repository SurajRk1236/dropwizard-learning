package org.learning.storm.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

import static org.learning.storm.constants.ColumnNames.CUSTOMER_EMAIL;
import static org.learning.storm.constants.ColumnNames.CUSTOMER_NAME;
import static org.learning.storm.constants.ColumnNames.ORDER_STATE_ID;
import static org.learning.storm.constants.TableNames.ORDERS;
import static org.learning.storm.entity.OrderLine.Fields.order;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = ORDERS)
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = CUSTOMER_NAME)
    String customerName;

    @Column(name = CUSTOMER_EMAIL)
    String customerEmail;

    @OneToMany(mappedBy = order, fetch = FetchType.LAZY)
    List<OrderLine> items;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ORDER_STATE_ID)
    OrderStateChange state;
}
