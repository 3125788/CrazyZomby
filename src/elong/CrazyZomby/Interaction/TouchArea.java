package elong.CrazyZomby.Interaction;

import elong.CrazyZomby.CrazyZombyConstent.E_TOUCHAREA;

public class TouchArea {
	//开始坐标
	int mStartX = 0;
	int mStartY = 0;
	//宽和高
	int mW = 0;
	int mH = 0;
	
	//触摸区域
	E_TOUCHAREA mTouchArea = E_TOUCHAREA.NONE;
	
	public TouchArea(int x, int y, int w, int h, E_TOUCHAREA area)
	{
		mStartX = x;
		mStartY = y;
		mW = w;
		mH = h;
		mTouchArea = area;
	}
	
}
