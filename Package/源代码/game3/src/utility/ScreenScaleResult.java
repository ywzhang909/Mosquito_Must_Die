package utility;
/**
 * 
 * @author ɽ����ѧandroidʵ�������ѳ�
 *
 */

//���ű����������Ĺ�����
public class ScreenScaleResult {
    public int lcuX;//���Ͻǵ�x����
    public int lcuY;//���Ͻǵ�Y����
    public float ratio;//ͼƬ�����ű���
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
