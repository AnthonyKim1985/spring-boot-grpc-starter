package com.anthonykim.grpc;

import com.anthonykim.grpc.annotation.IntData;
import com.anthonykim.grpc.annotation.StringData;
import com.anthonykim.grpc.handler.AnnotationHandler;
import com.anthonykim.grpc.model.IntModel;
import com.anthonykim.grpc.model.StringModel;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AnnotationTests {
    @Test
    void testAnnotation() {
        final AnnotationHandler handler = new AnnotationHandler();
        final IntModel intModel = handler.getInstance(IntModel.class, IntData.class).orElse(new IntModel());
        final StringModel stringModel = handler.getInstance(StringModel.class, StringData.class).orElse(new StringModel());

        assertThat(intModel.getMyData(), equalTo(30));
        assertThat(intModel.getDefaultData(), equalTo(-1));
        assertThat(stringModel.getMyData(), equalTo("Test"));
        assertThat(stringModel.getDefaultData(), equalTo("No data"));
    }
}
