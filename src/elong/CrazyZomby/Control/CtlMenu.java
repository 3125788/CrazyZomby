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


//自动提示效果
public class CtlMenu extends CtlBase{
	
	int mPicId = 1;
	int mTimeCnt = 0;
	int mPicNum = 4;

	public void run()
	{
		if(mStop) return;
		mTimeCnt++;
		if (1 == (mTimeCnt % 3)) 		//降频
		{
			mPicId++;
			if (mPicId > mPicNum) mPicId = 1;
		}
	}
	
	public void init(int picNum)
	{
		mPicNum = picNum;
	}
	
	public int getPicId()
	{
		return mPicId;
	}			
}

