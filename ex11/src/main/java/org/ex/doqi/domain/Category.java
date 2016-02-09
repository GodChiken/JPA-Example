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
public class Category {

    private long id;
    private String name;
    private List<Product> products;
    private List<Category> children;
    private Category parent;

}