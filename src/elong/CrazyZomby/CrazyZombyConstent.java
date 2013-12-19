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

public class CrazyZombyConstent {
	public static final float GRID_NUM = 7.0f;
	public static int UNIT_SIZE = (int) (88 * CrazyZombyConstent.GRID_NUM);
	public static int VIEW_WIDTH = 480;		//适配的宽度
	public static int VIEW_HEIGHT = 800;	//适配的高度
	public static int REAL_WIDTH = 480;		//实际宽度
	public static int REAL_HEIGHT = 800;	//实际高度
	public static int ADP_SIZE = 0;			//适配的比率，需要计算得出
	public static float screentRatio = 0;
	public static float translateRatio = 0;
	public static float denisty = 0;
	public static float widthPixel = 0;
	
	public static int DELAY_MS = 50;	//延时50MS
	public static int AUTOTIP_DELAY = 5 * 1000 / DELAY_MS;	//自动提示延时
	public static int MONSTER_APPEAR = 5;	//MONSTER出现的概率
	public static int MAX_TOKEN = 6;		//同时操作的最大令牌数
	public static int MOVE_THRESDHOLDER = 5;  //滑动门限
	public static int LIFE_NUM = 3;			//基础生命数	
	public static int LIFE_UP_NUM = 9;			//一次性消除数大于该值时增加生命
	public static int LIFE_TIMEOUT = 10;	//操作超时时失去生命
	
	public static int MAX_TIME = 100;		//一局游戏时间
	public static int TIME_ADD = 5;			//每次增加时间
	public static int TIME_DEL = 2;			//每次减少时间
	public static int TIME_ADD_NUM = 5;		//单次消除数大于5个时增加时间
	

	//声音类型
	public enum E_SOUND
	{
		SLIDE,	/*滑动*/
		FILL,	/*填充*/
		DISAPPEAR3,	/*消除3个*/
		DISAPPEAR4, /*消除4个*/
		DISAPPEAR5,	/*消除5个及以上*/
		READYGO,	/*readygo*/
		TIMEOVER,	/*timeover*/
		LEVELUP,	/*levelup*/
		SUPER,		/*super*/
		COOL,		/*cool*/
		GOOD,		/*good*/
		PERFECT,	/*perfect*/
		BOMB,		/*炸弹*/
		MONSTER,	/*怪兽*/
		LIFEADD,	/*添加生命*/
		LIFEDEL,	/*失去生命*/
		RESULTSCORE,/*分数结果*/
	}
	
	public enum E_TIP
	{
		READYGO,
		LEVELUP,
		GAMEOVER,
	}
	
	//场景
	public enum E_SCENARIO
	{
		MENU,	/*菜单场景*/
		GAME,	/*游戏场景*/
		RESULT,	/*结果场景*/
	}
	
	public enum E_GAMEMODE
	{
		TIME,	/*限时模式*/
		LIFE,	/*生存模式*/
	}
	
	//定义触摸区域
	public enum E_TOUCHAREA
	{
		NONE,
		MENU_TIME_MODE,
		MENU_LIFE_MODE,
		MENU_ABOUT,
		MENU_EXIT,
		RESULT_CONTINUE,
		RESULT2,
		MAX,		
	}
	

}
