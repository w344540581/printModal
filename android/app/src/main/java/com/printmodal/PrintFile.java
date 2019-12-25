package com.printmodal;

import android.widget.Toast;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.Callback;
import com.facebook.react.uimanager.IllegalViewOperationException;
import zpSDK.zpSDK.*;


import java.util.Map;
import java.util.HashMap;

public class PrintFile extends ReactContextBaseJavaModule {
    private static ReactApplicationContext reactContext;

    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String DURATION_LONG_KEY = "LONG";

    public PrintFile(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    private zpBluetoothPrinter zpSDK = new zpBluetoothPrinter(new HelloAppCanNativeActivity());
    @Override
    public String getName() {
        return "PrintFilesName";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put(DURATION_SHORT_KEY, Toast.LENGTH_SHORT);
        constants.put(DURATION_LONG_KEY, Toast.LENGTH_LONG);
        return constants;
    }

    //是否链接成功
    @ReactMethod
    public void connect(ReadableMap args,Callback errorCallback, Callback successCallback){
        ReadableNativeMap map = (ReadableNativeMap) args;
        HashMap map2 = map.toHashMap();
        try {
            successCallback.invoke(zpSDK.connect((String)map2.get("macAddress")));
        }catch (IllegalAccessError e){
            errorCallback.invoke(e.getMessage());
        }
    }

    //成功标志
    @ReactMethod
    public void mark(Callback successCallback){
        printerStatus();
        successCallback.invoke(zpSDK.GetStatus());
    }

    @ReactMethod
    public void printerStatus(){
        zpSDK.printerStatus();
    }

    @ReactMethod
    public void disconnect(){
        zpSDK.disconnect();
    }

    //设置打印纸张大小(打印区域)的大小
    @ReactMethod
    public void pageSetup(int x, int y){
        zpSDK.pageSetup(x,y);
    }

    //边框
    @ReactMethod
    public void drawLine(int lineWidth,int start_x,int start_y,int end_x,int end_y, boolean fullline){
        zpSDK.drawLine(lineWidth,start_x,start_y,end_x,end_y,fullline);
    }

    //内容
    @ReactMethod
    public void drawText(int text_x, int text_y, String text, int fontSize, int rotate, int bold, boolean reverse, boolean underline){
        zpSDK.drawText(text_x, text_y, text, fontSize, rotate, bold, reverse, underline);
    }

    @ReactMethod
    public void print(int horizontal, int skip){
        zpSDK.print(horizontal, skip);
    }


    @ReactMethod
    public void printBilling(ReadableMap args) {
        ReadableNativeMap map = (ReadableNativeMap) args;
        HashMap map2 = map.toHashMap();
        zpBluetoothPrinter zpSDK = new zpBluetoothPrinter(new HelloAppCanNativeActivity());
        //zpBluetoothPrinter zpSDK = new zpBluetoothPrinter(new HelloAppCanNativeActivity());
        //  Toast.makeText(getReactApplicationContext(), (String)map2.get("macAddress"), 1).show();
        // 通过 Mac 地址连接打印机 macAddress 00:42:69:06:58:75
        if (!zpSDK.connect((String)map2.get("macAddress"))) {
            Toast.makeText(getReactApplicationContext(), "蓝牙打印机连接失败", 1).show();
        }
        zpSDK.printerStatus();
        int a = zpSDK.GetStatus();
        // Toast.makeText(getReactApplicationContext(), (String)a,1).show();
        if (a == 1) {
            //"缺纸----------";
            Toast.makeText(getReactApplicationContext(), "蓝牙打印机缺纸", 11).show();
        } else if (a == 2) {
            //"开盖----------";
            Toast.makeText(getReactApplicationContext(), "蓝牙打印机开盖", 11).show();
        } else if (a == 0) {
                drawAirlinesNew(zpSDK, args);
            //"打印机正常-------";
            Toast.makeText(getReactApplicationContext(), "打印成功", 11).show();

        } else {
            Toast.makeText(getReactApplicationContext(), "蓝牙打印机开盖", 11).show();
        }
        zpSDK.disconnect();
        // }
        // }).start();
    }

        @ReactMethod
        public void drawAirlinesNew(zpBluetoothPrinter zpSDK,ReadableMap args){
                ReadableNativeMap map = (ReadableNativeMap) args;
                HashMap map2 = map.toHashMap();
                /**
                 * 设置打印纸张大小(打印区域)的大小
                 * @param pageWidth 打印区域宽度
                 * @param pageHeight 打印区域高度
                 * */
                // 274 + borderLeftAndRightEnd + 60 + addedHeight
                zpSDK.pageSetup(720, 586 );
                // 上边框
                zpSDK.drawLine(5, 20, 20, 570, 20, true);

                // 左边框
                zpSDK.drawLine(5, 20, 20, 20, 516, true);
                // 右边框
                zpSDK.drawLine(5, 570, 20, 570, 516, true);
                // 下边框 (通过数据行计算)
                // zpSDK.drawLine(5, 20, 516 + uldListSumHeight,570, 516 + uldListSumHeight, true);




                // 板箱号
                zpSDK.drawText(45, 60, "板箱号:", 3, 0, 0, false, false);

                // AKE00008MU
                zpSDK.drawText(222, 60, "AKE28653MU", 3, 0, 0, false, false);
                // 航司
                zpSDK.drawText(45, 255, "航司:", 3, 0, 0, false, false);
                // MU
                zpSDK.drawText(222, 230, "MU", 7, 0, 1, false, false);
                // 航班时间
                zpSDK.drawText(45, 450, "航班时间:", 3, 0, 0, false, false);
                // 01-07 11:31
                zpSDK.drawText(222, 450,"2019-11-02", 3, 0, 0, false, false);
                // 结束下边线
                zpSDK.drawLine(5, 20, 510, 570, 510, true);
                // ----------------------------------------------step 3 end----------------------------------------------------------------------------------------
                zpSDK.print(0, 0);

            }

}
