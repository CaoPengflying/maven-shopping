package com.hd.queryVo;

import com.hd.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class CartVo {
    private List<ProductVo> items = new ArrayList<>();
    private Float sum;

    public Float getSum() {
        return sum;
    }

    public void setSum(Float sum) {
        this.sum = sum;
    }

    public List<ProductVo> getItems() {
        return items;
    }

    public void setItems(List<ProductVo> items) {
        this.items = items;
    }
}
