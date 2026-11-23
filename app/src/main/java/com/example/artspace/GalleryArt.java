package com.example.artspace;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public final class GalleryArt {

    private GalleryArt() {
    }

    public static Bitmap draw(int index) {
        Bitmap bitmap = Bitmap.createBitmap(600, 450, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        switch (index) {
            case 0: // Dune Sunrise
                canvas.drawColor(Color.rgb(250, 219, 171));
                paint.setColor(Color.rgb(213, 91, 57));
                canvas.drawCircle(415, 130, 68, paint);
                triangle(canvas, paint, 0xFFD29B62, -90, 450, 225, 160, 570, 450);
                triangle(canvas, paint, 0xFF966347, 180, 450, 510, 225, 710, 450);
                break;

            case 1: // Blue Tide
                canvas.drawColor(Color.rgb(210, 236, 234));
                for (int i = 0; i < 4; i++) {
                    paint.setColor(Color.rgb(75 - i * 15, 160 - i * 18, 178 - i * 14));
                    float y = 105 + i * 78;
                    Path wave = new Path();
                    wave.moveTo(0, y);
                    wave.cubicTo(170, y - 95, 360, y + 100, 600, y);
                    wave.lineTo(600, 450);
                    wave.lineTo(0, 450);
                    wave.close();
                    canvas.drawPath(wave, paint);
                }
                break;

            case 2: // City Windows
                canvas.drawColor(Color.rgb(242, 211, 179));
                for (int i = 0; i < 6; i++) {
                    int left = 8 + i * 100;
                    int top = 65 + (i * 73) % 185;
                    paint.setColor(Color.rgb(53, 75 + i * 8, 87));
                    canvas.drawRect(left, top, left + 84, 450, paint);
                    paint.setColor(Color.rgb(254, 215, 130));
                    for (int y = top + 20; y < 430; y += 39) {
                        canvas.drawRect(left + 14, y, left + 28, y + 16, paint);
                        canvas.drawRect(left + 52, y, left + 66, y + 16, paint);
                    }
                }
                break;

            case 3: // Mountain Echo
                canvas.drawColor(Color.rgb(224, 229, 242));
                triangle(canvas, paint, 0xFF969BB6, -90, 450, 175, 75, 445, 450);
                triangle(canvas, paint, 0xFF616C90, 100, 450, 397, 40, 690, 450);
                triangle(canvas, paint, 0xFFF5F4EE, 341, 119, 397, 40, 454, 120);
                triangle(canvas, paint, 0xFF424F6A, -70, 450, 290, 255, 670, 450);
                break;

            case 4: // Orbit Study
                canvas.drawColor(Color.rgb(34, 44, 65));
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(4);
                paint.setColor(Color.rgb(167, 187, 196));
                for (int radius = 65; radius <= 205; radius += 70) {
                    canvas.drawCircle(300, 225, radius, paint);
                }
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.rgb(248, 196, 107));
                canvas.drawCircle(300, 225, 40, paint);
                paint.setColor(Color.rgb(222, 130, 107));
                canvas.drawCircle(435, 225, 19, paint);
                paint.setColor(Color.rgb(126, 195, 200));
                canvas.drawCircle(300, 20, 13, paint);
                break;

            case 5: // Garden Tiles
                canvas.drawColor(Color.rgb(247, 234, 207));
                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 4; col++) {
                        int x = col * 150;
                        int y = row * 150;
                        boolean alternate = (row + col) % 2 == 0;
                        paint.setColor(alternate ? 0xFF497664 : 0xFFB76347);
                        canvas.drawRoundRect(x + 9, y + 9, x + 141, y + 141,
                                35, 35, paint);
                        paint.setColor(0xFFF1CB84);
                        canvas.drawOval(x + 35, y + 58, x + 115, y + 92, paint);
                        canvas.drawOval(x + 58, y + 35, x + 92, y + 115, paint);
                    }
                }
                break;

            default:
                throw new IllegalArgumentException("Unknown artwork index: " + index);
        }

        return bitmap;
    }

    private static void triangle(Canvas canvas, Paint paint, int color,
                                 float x1, float y1, float x2, float y2,
                                 float x3, float y3) {
        Path path = new Path();
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        path.lineTo(x3, y3);
        path.close();
        paint.setColor(color);
        canvas.drawPath(path, paint);
    }
}