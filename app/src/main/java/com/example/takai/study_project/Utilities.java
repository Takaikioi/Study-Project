package com.example.takai.study_project;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Takai on 9/01/2016
 */
public final class Utilities {
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
    private Utilities() {

    }

    public static int generateViewId() {
        for (;;) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }
}
