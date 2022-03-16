package vn.edu.hcmuaf.fit.webdt.beans;

import java.io.Serializable;

public class Detail implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    Product product;
    int quantity;
    double price;
}
