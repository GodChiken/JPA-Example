package org.ex.doqi.domain;


/**
 * @author Doqi Kim
 * @version 1.0
 * @created 09-2-2016 오후 6:06:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    private long id;
    private String name;
    private BigDecimal price;
    private int stockQuantity;
    private List<Category> categories;

}