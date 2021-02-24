package com.anthonykim.grpc.model;

import com.anthonykim.grpc.annotation.StringData;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class StringModel {
    @StringData(data = "Test")
    private final String myData;

    @StringData
    private final String defaultData;

    public StringModel() {
        this.myData = "No data";
        this.defaultData = "No data";
    }
}
