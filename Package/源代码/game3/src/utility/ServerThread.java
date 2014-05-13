package utility;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.UUID;

import com.publicClass.Constant;
import com.userView.CoopView;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;
/**
 * 
 * @author ɽ����ѧandroidʵ�������ѳ�
 *
 */
public class ServerThread extends Thread {
	 private final BluetoothServerSocket mServerSocket;
	 CoopView coopView;
	 boolean flag=true;
	 public BluetoothSocket socket = null;
	 public ManagerThread managerThread;
	 public ServerThread(CoopView coop){
		 coopView=coop;
		 BluetoothServerSocket tmp = null;
		 try{
			 tmp=coopView.mBluetoothAdapter.listenUsingRfcommWithServiceRecord(Constant.GAME_NAME,UUID.fromString("1539F5CF-0C4D-F0A6-54C8-67FC277A4B5D"));
		 }catch(IOException e){
			 Log.e("serverSocket", "�������ɹ�");
		 }
		 mServerSocket=tmp;
	 }
public void run(){
		try{
			socket=mServerSocket.accept();
		}catch(IOException e){
			Log.e("socket", "���Ӳ��ɹ�");
			}
		if(socket!=null){
		managerThread=new ManagerThread(socket);
		managerThread.start();
		//������Ϣ
		coopView.mHandler.sendEmptyMessage(3);
		}
}
public void cancelSocket(){

}
public void setFlag(boolean flag) {
	this.flag = flag;
}

}
