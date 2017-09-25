package com.yahoo.plumbus.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.annotation.Px;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.yahoo.plumbus.R;

/**
 * Created by yangxiang on 9/24/17.
 * Minimum column count is 1
 */

public class ColumnDivideLayout extends ViewGroup {

    private int mColumnCount;
    private final Rect mTempRect = new Rect();

    public ColumnDivideLayout(Context context) {
        this(context, null);
    }

    public ColumnDivideLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColumnDivideLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ColumnDivideLayout, 0, 0);
        try {
            mColumnCount = Math.max(1, typedArray.getInteger(R.styleable.ColumnDivideLayout_columnCount, 1));
        } finally {
            typedArray.recycle();
        }
    }


    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
        int hSpec = MeasureSpec.getMode(heightMeasureSpec);
        int maxHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (hSpec == MeasureSpec.AT_MOST) {
            measureChildren(
                    MeasureSpec.makeMeasureSpec(((maxWidth - getPaddingRight() - getPaddingLeft()) / mColumnCount),
                            MeasureSpec.EXACTLY), heightMeasureSpec);
            int measuredHeight = 0;
            int maxRowChildHeight = 0;
            int columnCounter = 0;
            for (int i = 0; i < count; i++) {
                final View child = getChildAt(i);
                if (child.getVisibility() != GONE) {
                    LayoutParams lp = (LayoutParams) child.getLayoutParams();
                    measureChild(child, MeasureSpec.makeMeasureSpec(((maxWidth - getPaddingRight() - getPaddingLeft()) / mColumnCount) -
                            lp.leftMargin - lp.rightMargin,
                            MeasureSpec.EXACTLY), heightMeasureSpec);
                    if (columnCounter < mColumnCount) {
                        maxRowChildHeight = Math.max(child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin, maxRowChildHeight);
                    } else {
                        measuredHeight += maxRowChildHeight;
                        maxRowChildHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
                        columnCounter = 0;
                    }
                    columnCounter++;
                }
            }
            measuredHeight += maxRowChildHeight + getPaddingBottom() + getPaddingTop();
            maxHeight = Math.min(maxHeight, measuredHeight);
        }
        setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, 0),
                resolveSizeAndState(maxHeight, heightMeasureSpec, 0));

    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (!changed) {
            return;
        }
        int count = getChildCount();
        int leftPos = getPaddingLeft();
        int maxRowChildHeight = 0;
        int currentHeight = getPaddingTop();
        int columnCounter = 0;
        int maxColumnWidth = (getMeasuredWidth() - getPaddingLeft() - getPaddingRight()) / mColumnCount;
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                final LayoutParams lp = (LayoutParams) child.getLayoutParams();
                final int height = child.getMeasuredHeight();
                final int width = child.getMeasuredWidth();
                if (columnCounter >= mColumnCount) {
                    currentHeight += maxRowChildHeight;
                    maxRowChildHeight = 0;
                    columnCounter = 0;
                }
                mTempRect.top = currentHeight + lp.topMargin;
                mTempRect.left = columnCounter * maxColumnWidth + lp.leftMargin + leftPos;
                mTempRect.right = mTempRect.left + width;
                mTempRect.bottom = mTempRect.top + height;
                maxRowChildHeight = Math.max(maxRowChildHeight, height + lp.topMargin + lp.bottomMargin);
                child.layout(mTempRect.left, mTempRect.top, mTempRect.right, mTempRect.bottom);
                columnCounter++;
            }
        }

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new ColumnDivideLayout.LayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    /**
     * Custom per-child layout information.
     */
    public static class LayoutParams extends MarginLayoutParams {

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(@Px int width, @Px int height) {
            super(width, height);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }
}
