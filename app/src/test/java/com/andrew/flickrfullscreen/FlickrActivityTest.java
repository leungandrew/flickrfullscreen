package com.andrew.flickrfullscreen;

import android.support.v7.widget.RecyclerView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class FlickrActivityTest {

    private FlickrActivity activity;

    @Inject
    TestRetrofitClient testRetrofitClient;

    @Before
    public void setup() {
        testRetrofitClient.addResponse(TestRetrofitClient.createResponse(200, TestUtil.getResponse(5)));
        activity = Robolectric.setupActivity(FlickrActivity.class);
    }

    @Test
    public void shouldContainViews() {
        assertThat(activity.list).isInstanceOf(RecyclerView.class);
    }


}