package com.example.fuzz.common;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class MyPage implements Serializable {

    private long totalRow;

    private List result;

    public MyPage() {
    }

    public MyPage(long totalRow, List result) {
        this.totalRow = totalRow;
        this.result = result;
    }
}
