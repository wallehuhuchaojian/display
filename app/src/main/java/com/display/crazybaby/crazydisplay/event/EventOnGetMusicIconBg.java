package com.display.crazybaby.crazydisplay.event;

import android.graphics.Bitmap;

/**
 * Created by hu on 17-3-18.
 */

public class EventOnGetMusicIconBg  {
    Bitmap bitmap;

    public EventOnGetMusicIconBg(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
