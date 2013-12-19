/**********************************************************
 * 项目名称：消除僵尸
 * 作          者：郑敏新
 * 腾讯微博：SuperCube3D
 * 日          期：2013年12月
 * 声          明：版权所有   侵权必究
 * 本源代码供网友研究学习OpenGL ES开发Android应用用，
 * 请勿全部或部分用于商业用途
 ********************************************************/

package elong.CrazyZomby;

import elong.CrazyZomby.Core.ControlCenter;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class CrazyZombyActivity extends Activity {	
	CrazyZombyGLSurfaceView mGLSurfaceView;
	private MediaPlayer mp;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);	//直屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//获取屏幕信息
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);		
		CrazyZombyConstent.REAL_WIDTH = dm.widthPixels;		 
		CrazyZombyConstent.REAL_HEIGHT = dm.heightPixels;

		getWindow().setFormat(PixelFormat.TRANSLUCENT);
        mGLSurfaceView = new CrazyZombyGLSurfaceView(this);
        setContentView(mGLSurfaceView);
        mGLSurfaceView.requestFocus();
        mGLSurfaceView.setFocusableInTouchMode(true);
    }

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		if(mp==null){

			mp = MediaPlayer.create(this, R.raw.s_background);
			mp.setLooping(true);
			mp.start();

			}
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		ControlCenter.mTimer.pause();
		super.onStop();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mGLSurfaceView.onResume();
		ControlCenter.mTimer.resume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mGLSurfaceView.onPause();
		mp.pause();
		ControlCenter.mTimer.pause();
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mp.stop();
	}
}