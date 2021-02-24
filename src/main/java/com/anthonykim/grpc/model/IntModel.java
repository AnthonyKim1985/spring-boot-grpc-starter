package com.anthonykim.grpc.model;

import com.anthonykim.grpc.annotation.IntData;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class IntModel {
    @IntData(data = 30)
    private final int myData;

    @IntData
    private final int defaultData;

    public IntModel() {
        this.myData = -1;
        this.defaultData = -1;
    }
}

