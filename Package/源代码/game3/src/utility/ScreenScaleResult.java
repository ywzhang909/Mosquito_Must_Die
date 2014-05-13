package utility;
/**
 * 
 * @author 山东大学android实验室刘昭呈
 *
 */

//缩放比例计算结果的工具类
public class ScreenScaleResult {
    public int lcuX;//左上角的x坐标
    public int lcuY;//左上角的Y坐标
    public float ratio;//图片的缩放比例
	public ScreenScaleResult(int lcuX,int lcuY,float ratio){
		this.lcuX=lcuX;
		this.lcuY=lcuY;
		this.ratio=ratio;
	}
	public String toString()
	{
		return "lucX="+lcuX+", lucY="+lcuY+", ratio="+ratio;
	}
}
