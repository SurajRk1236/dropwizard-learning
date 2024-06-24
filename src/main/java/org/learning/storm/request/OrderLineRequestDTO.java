package org.learning.storm.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderLineRequestDTO {
    @NotNull
    String productName;

    @NotNull
    @Min(value = 1)
    int quantity;

    @NotNull
    @Min(value = 1)
    double price;

    @NotNull
    String sku;
}
