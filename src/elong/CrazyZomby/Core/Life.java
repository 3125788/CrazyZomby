/**********************************************************
 * 项目名称：消除僵尸
 * 作          者：郑敏新
 * 腾讯微博：SuperCube3D
 * 日          期：2013年12月
 * 声          明：版权所有   侵权必究
 * 本源代码供网友研究学习OpenGL ES开发Android应用用，
 * 请勿全部或部分用于商业用途
 ********************************************************/

package elong.CrazyZomby.Core;

import android.os.Message;
import elong.CrazyZomby.CrazyZombyConstent;
import elong.CrazyZomby.CrazyZombyConstent.E_GAMEMODE;
import elong.CrazyZomby.CrazyZombyConstent.E_SCENARIO;
import elong.CrazyZomby.Control.CtlBase;
import elong.CrazyZomby.CrazyZombyConstent.E_SOUND;

public class Life extends CtlBase{
	
	int mLife = 0;
	int mCount = 0;
	
	public Life()
	{
		mLife = CrazyZombyConstent.LIFE_NUM;
		mCount = 0;
	}

	void decrease()
	{
		mLife--;
		ControlCenter.mSound.play(E_SOUND.LIFEDEL);
		if(0 == mLife)
		{
			Message msg = new Message();
			msg.what = ControlCenter.GAME_OVER_START;
		    ControlCenter.mHandler.sendMessage(msg);				
		}
	}
	
	void increase()
	{
		mLife++;
		ControlCenter.mSound.play(E_SOUND.LIFEADD);
	}
	
	int get()
	{
		return mLife;
	}
	
	public void set(int life)
	{
		mLife = life;
	}
	
	public void addMsg()
	{
		if(ControlCenter.mMode != E_GAMEMODE.LIFE) return;
		Message msg = new Message();
		msg.what = ControlCenter.LIFEADD_START;
	    ControlCenter.mHandler.sendMessage(msg);			
	}
	

	
	public void delMsg()
	{
		if(ControlCenter.mMode != E_GAMEMODE.LIFE) return;
		Message msg = new Message();
		msg.what = ControlCenter.LIFEDEL_START;
	    ControlCenter.mHandler.sendMessage(msg);			
	}
	
	public void run()
	{
		if(ControlCenter.mMode != E_GAMEMODE.LIFE) return;
		if(ControlCenter.mScene != E_SCENARIO.GAME) return;
		mCount++;
		if(mCount > (1000/CrazyZombyConstent.DELAY_MS) * CrazyZombyConstent.LIFE_TIMEOUT)
		{
			mCount = 0;
			delMsg();
		}
	}
	
	public void reset()
	{
		mCount = 0;
	}
}
