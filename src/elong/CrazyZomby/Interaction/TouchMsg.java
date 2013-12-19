package elong.CrazyZomby.Interaction;

import java.util.ArrayList;

import android.os.Message;
import elong.CrazyZomby.Core.ControlCenter;
import elong.CrazyZomby.Interface.IControl;
import elong.CrazyZomby.CrazyZombyConstent.E_TOUCHAREA;

public class TouchMsg {
	
	class TouchList{
		ArrayList<TouchArea> mTouchAreaList;
		ArrayList<IControl> mControl;
		
		public TouchList()
		{
			mTouchAreaList = new ArrayList<TouchArea>();
			mControl = new ArrayList<IControl>();
		}
	}
	
	static public TouchList mTouchList;
	
	public TouchMsg()
	{
		mTouchList = new TouchList();		
	}
	
	public void initTouchList()
	{
		mTouchList.mTouchAreaList.clear();
		mTouchList.mControl.clear();
	}
	
	public void touchRegister(TouchArea touchArea, IControl control)
	{
		if(touchArea == null) return;
		mTouchList.mTouchAreaList.add(touchArea);
		mTouchList.mControl.add(control);
	}
	
	//输入坐标是(x,y)，检测是否落在某个TouchArea
	void raiseTouchEvent(int x, int y)
	{
		TouchArea touchArea;
		IControl control;
		for(int i = 0; i < mTouchList.mTouchAreaList.size(); i++)
		{
			touchArea = mTouchList.mTouchAreaList.get(i);
			control = mTouchList.mControl.get(i);
			if(x >= touchArea.mStartX && x <= touchArea.mStartX + touchArea.mW)
			{
				if(y >= touchArea.mStartY && y <= touchArea.mStartY + touchArea.mH)
				{
					if(control.isRun())
					{
						//检测到一个有效的就立即退出
						sendMsg(touchArea.mTouchArea);
						return;
					}
				}
			}
		}
		sendMsg(E_TOUCHAREA.NONE);
		return;
	}
	
	void sendMsg(E_TOUCHAREA area)
	{
		Message msg = new Message();
		msg.what = ControlCenter.TOUCH_EVENT_BASE + area.ordinal();
	    ControlCenter.mHandler.sendMessage(msg);
	}
	
}


