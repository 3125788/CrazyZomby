/**********************************************************
 * 项目名称：消除僵尸
 * 作          者：郑敏新
 * 腾讯微博：SuperCube3D
 * 日          期：2013年12月
 * 声          明：版权所有   侵权必究
 * 本源代码供网友研究学习OpenGL ES开发Android应用用，
 * 请勿全部或部分用于商业用途
 ********************************************************/

package elong.CrazyZomby.Interface;

public interface IControl {
	void run();
	void start();
	void end();
	boolean isRun();
	void setToken(int token);
	int getToken();
	void sendMsg();
}
