package org.learning.storm.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.learning.storm.enums.OrderStateChangeStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static org.learning.storm.constants.ColumnNames.PROCESSING_SEQUENCE;
import static org.learning.storm.constants.TableNames.ORDER_STATE_CHANGE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = ORDER_STATE_CHANGE)
public class OrderStateChange {

    @Id
    Long id;

    String name;

    OrderStateChangeStatus status;

    @Column(name = PROCESSING_SEQUENCE)
    int processingSequence;

}
