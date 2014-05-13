package utility;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

import com.userView.CoopView;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

public class ClientThread extends Thread{
	 public BluetoothSocket mSocket;
	    private final BluetoothDevice mDevice;
	    public CoopView coopView;
	    boolean flag;
	    public ManagerThread managerThread;
	    public ClientThread(BluetoothDevice device,CoopView coopView){
	    	this.coopView=coopView;
	    	mDevice=device;
	    	try{
	    		Log.e(mDevice.getName(), "客户端");
	    		mSocket=mDevice.createRfcommSocketToServiceRecord(UUID.fromString("1539F5CF-0C4D-F0A6-54C8-67FC277A4B5D"));
	    	} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    public void run(){
	    	 //取消搜索
	    	 coopView.mBluetoothAdapter.cancelDiscovery();
	    	 try {
	    		 Log.e("connect", "begin");
				mSocket.connect();
				//开启管理数据流
		    	managerThread=new ManagerThread(mSocket);
		    	managerThread.start();
		    	coopView.mHandler.sendEmptyMessage(4);
	    	 } catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				Log.e("illegal", "s");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		public void setFlag(boolean flag) {
			this.flag = flag;
		}	    
}
