/**********************************************************
 * 项目名称：消除僵尸
 * 作          者：郑敏新
 * 腾讯微博：SuperCube3D
 * 日          期：2013年12月
 * 声          明：版权所有   侵权必究
 * 本源代码供网友研究学习OpenGL ES开发Android应用用，
 * 请勿全部或部分用于商业用途
 ********************************************************/

package elong.CrazyZomby.Control;

import android.os.Message;
import elong.CrazyZomby.Core.ControlCenter;


//显示结果分数
public class CtlResultScore extends CtlBase{
	
	int mNumber = 0;
	int mResult = 0;
	int mStep = 0;
	int mCount = 0;
	int mDivide = 60;

	public void run()
	{
		if(mStop) return;

		mNumber += mStep;
		mCount++;
		if (mCount > mDivide || mResult < 2000)
		{
			mNumber = mResult;
			mStop = true;
			sendMsg();
		}
	}
	
	public void init(int result)
	{
		mResult = result;
		mStep = mResult / mDivide / 10 * 10 + 1;
		mNumber = 0;
		mCount = 0;
		mStop = false;
	}
	
	public int getNumber()
	{
		return mNumber;
	}			
	
	public void sendMsg()
	{
		Message msg = new Message();
		msg.what = ControlCenter.RESULT_END;
	    ControlCenter.mHandler.sendMessage(msg);					
	}
}




