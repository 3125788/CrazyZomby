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

public class CtlFill extends CtlBase{
	int mDeltaY;	
	int mStep = 25;				//Æ«ÒÆ²½³¤	

	public CtlFill()
	{
		mStop = false;
		mDeltaY = 100;		
	}
	
	public void run()
	{
		//½«Æ«ÒÆ·¶Î§ÏÞ¶¨ÔÚ-0.5~0.5¼ä£¬³¬³öÖ¸¶¨Çø¼ä¼´µôÍ·
		if(!mStop)
		{
			mDeltaY -= mStep;		
			if(mDeltaY <= 0)
			{
				mStop = true;
			}
			if(mStop)
			{
				sendMsg();
			}			
		}
	}
		
	public float getY()
	{
		return mDeltaY/100.0f;
	}

	public void start()
	{
		mDeltaY = 100;
		super.start();
	}

	public void sendMsg()
	{
		Message msg = new Message();
	    msg.what = ControlCenter.FILL_END;
	    ControlCenter.mHandler.sendMessage(msg);
	}
}

