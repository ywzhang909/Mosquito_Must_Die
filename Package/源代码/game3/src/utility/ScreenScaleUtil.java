package utility;



/**
 * 
 * @author 山东大学android实验室刘昭呈
 *
 */
//用于计算屏幕缩放比的工具类
public class ScreenScaleUtil {
	
	static final float sHpWidth=800;//原始横屏的宽度
	static final float sHpHeight=480;//原始横屏的高度
	static final float whHpRatio=sHpWidth/sHpHeight;//原始横屏的宽高比

	public static ScreenScaleResult calScale
	(
		float targetWidth,	//目标宽度
		float targetHeight	//目标高度
	)
	{
		ScreenScaleResult result=null;
		
		//进行横屏的相关计算
		
		//计算目标的宽高比
		float targetRatio=targetWidth/targetHeight;
	
		//如果目标宽高比大于原始宽高比，则以目标宽高比为准，不过，ratio是以高来计算的
		if(targetRatio>whHpRatio){
			//以高来计算的
			float ratio=targetHeight/sHpHeight;
			//换算出以屏幕的宽为准的宽
			float realTargetWidth=ratio*sHpWidth;
			
			//图片坐标的计算
			float lcuX=(targetWidth-realTargetWidth)/2.0f;
		    float lcuY=0;
			result=new ScreenScaleResult((int)lcuX,(int) lcuY, ratio);
		    return result;
		}
		//如果目标宽高比小于原始宽高比，则以目标宽高比为准，不过，ratio是以宽来计算的
		else{
			//以宽来计算的
			float ratio=targetWidth/sHpWidth;
			//换算出以屏幕的高为准的高
			float realTargetHeight=ratio*sHpHeight;
			//图片坐标的计算
			float lcuY=(targetWidth-realTargetHeight)/2.0f;
		    float lcuX=0;
		    result=new ScreenScaleResult((int)lcuX,(int) lcuY, ratio);
		    return result;
		}
	}
}
