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

import elong.CrazyZomby.CrazyZombyConstent.E_SCENARIO;
import elong.CrazyZomby.Core.ControlCenter;
import elong.CrazyZomby.Interaction.ScreenTouch;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Message;
import android.view.MotionEvent;

public class CrazyZombyGLSurfaceView extends GLSurfaceView{
	
    private SceneRenderer mRenderer;
    Context mContext;    
	static boolean m_bThreadRun = false;	 
	static ControlCenter controlCenter;

	
	public CrazyZombyGLSurfaceView(CrazyZombyActivity activity) {
        super(activity);
        mContext = this.getContext();
        mRenderer = new SceneRenderer();	//场景渲染器
        setRenderer(mRenderer);						
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);//设置连续渲染模式
        
        
        
    	if (!m_bThreadRun)
    	{
    		m_bThreadRun = true;
    		controlCenter = new ControlCenter(mContext);
    		new Thread()
    		{
    			public void run()
    			{
    				while(true)
    				{
    					try
    			        {
    						controlCenter.run();
    			      	  	Thread.sleep(CrazyZombyConstent.DELAY_MS);//延时50ms
    			        }
    			        catch(Exception e)
    			        {
    			      	  	e.printStackTrace();
    			        }        			  
    				}
    			}
    		}.start();   	    	
    	}
        
    }
	
    //处理屏幕触摸事件
    @Override public boolean onTouchEvent(MotionEvent e) {
    	if(ControlCenter.mScreenTouch != null)
    	{
    		if (ControlCenter.mScene == E_SCENARIO.GAME)
    		{
    			ControlCenter.mScreenTouch.touchGameView(e);
    			ControlCenter.mLife.reset();
    		}
    		else if (ControlCenter.mScene == E_SCENARIO.MENU)
    		{
    			ControlCenter.mScreenTouch.touchMenuView(e);
    		}
    		else if (ControlCenter.mScene == E_SCENARIO.RESULT)
    		{
    			ControlCenter.mScreenTouch.touchResultView(e);
    		}
    	}
        return true;
    }	
	
	private class SceneRenderer implements GLSurfaceView.Renderer 
    { 

        public void onDrawFrame(GL10 gl) {  
        	gl.glShadeModel(GL10.GL_SMOOTH);		//��ɫģʽΪƽ����ɫ
        	gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);//�����ɫ��������Ȼ�����
        	gl.glMatrixMode(GL10.GL_MODELVIEW);		//���þ���Ϊģʽ����
        	gl.glLoadIdentity();					//���õ�ǰ����Ϊ��λ����
        	gl.glTranslatef(0f, 0f, -10f);			        	
        
        	if (ControlCenter.mScene == E_SCENARIO.GAME)
        	{
        		controlCenter.drawGameScene(gl);
        	}
        	else if (ControlCenter.mScene == E_SCENARIO.MENU)
        	{
        		ControlCenter.startMenuScene();
        		controlCenter.drawMenuScene(gl);
        	}
        	else if (ControlCenter.mScene == E_SCENARIO.RESULT)
        	{
        		controlCenter.drawResultScene(gl);
        	}
        	
        }  
        
        public void onSurfaceChanged(GL10 gl, int width, int height) {
       	
        	CrazyZombyConstent.REAL_WIDTH = width;
        	CrazyZombyConstent.REAL_HEIGHT = height;
           	CrazyZombyConstent.translateRatio = (float) width / height;
        	CrazyZombyConstent.screentRatio = (float) width / height;       
   			CrazyZombyConstent.ADP_SIZE = CrazyZombyConstent.UNIT_SIZE * CrazyZombyConstent.VIEW_HEIGHT/height * width/CrazyZombyConstent.VIEW_WIDTH;   			
        	ControlCenter.mScreenTouch = new ScreenTouch(mContext, width, height);        	
        	gl.glViewport(0, 0, width, height);        	
            gl.glMatrixMode(GL10.GL_PROJECTION);        
            gl.glLoadIdentity();            			
           	
           	gl.glOrthof(-CrazyZombyConstent.screentRatio*CrazyZombyConstent.GRID_NUM/2, 
           			CrazyZombyConstent.screentRatio*CrazyZombyConstent.GRID_NUM/2, 
           			-1*CrazyZombyConstent.GRID_NUM/2, 
           			1*CrazyZombyConstent.GRID_NUM/2, 10, 100);
        }

        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        	gl.glDisable(GL10.GL_DITHER);			//�رտ�����
            gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,GL10.GL_FASTEST);//�����ض�Hint��Ŀ��ģʽ������Ϊ����Ϊʹ�ÿ���ģʽ
            gl.glClearColor(0,0,0,0);            	//清楚颜色为黑色   
            gl.glShadeModel(GL10.GL_SMOOTH);        //平滑
            gl.glEnable(GL10.GL_DEPTH_TEST);		//使能深度测试

            /*********透明效果设置***********/
        	gl.glEnable(GL10.GL_BLEND);  
        	gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);  
        	gl.glEnable(GL10.GL_ALPHA_TEST);
        	gl.glAlphaFunc(GL10.GL_GREATER,0.1f);
        	
        	ControlCenter.mTouchMsg.initTouchList();
            controlCenter.initTexture(gl);         
            controlCenter.initDraw(gl);
            if (ControlCenter.mScene == E_SCENARIO.GAME)
        	{
	    		Message msg = new Message();
	    	    msg.what = ControlCenter.LOADING_START;
	    	    ControlCenter.mHandler.sendMessage(msg);
        	}

        }
                
    }
	

}


