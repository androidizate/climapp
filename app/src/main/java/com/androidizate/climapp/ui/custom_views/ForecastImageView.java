package com.androidizate.climapp.ui.custom_views;

import android.content.Context;
import android.util.AttributeSet;

/**
 * @author Andres Oller.
 */
public class ForecastImageView extends android.support.v7.widget.AppCompatImageView {

    int width;
    int height;
    int calculatedWidth;
    int calculatedHeight;
    int drawableWidth;
    int drawableHeight;
    double relation;

    public ForecastImageView(Context context) {
        super(context);
        initComponents();
    }

    public ForecastImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initComponents();
    }

    public ForecastImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initComponents();
    }

    private void initComponents() {
        calcutateDrawableSize();
        calcutateScreenSize();
        setDrawableSize();
    }

    private void setDrawableSize() {
        if (width <= 320) {
            calculatedWidth = (int) (width * 0.3);
        } else if (width <= 480) {
            calculatedWidth = (int) (width * 0.35);
        } else if (width <= 800) {
            calculatedWidth = (int) (width * 0.4);
        } else {
            calculatedWidth = (int) (width * 0.5);
        }
        calculatedHeight = (int) (calculatedWidth * relation);
    }

    private void calcutateScreenSize() {
        height = getContext().getResources().getDisplayMetrics().heightPixels;
        width = getContext().getResources().getDisplayMetrics().widthPixels;
    }

    private void calcutateDrawableSize() {
        drawableHeight = getDrawable().getIntrinsicHeight();
        drawableWidth = getDrawable().getIntrinsicWidth();
        setRelation();
    }

    private void setRelation() {
        if (drawableHeight >= drawableWidth) {
            relation = drawableHeight / drawableWidth;
        } else {
            relation = drawableWidth / drawableHeight;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(calculatedWidth, calculatedHeight);
    }
}
