import React from 'react';
import {View,Text} from 'react-native';
import PrintModal from './PrintExample'




class Demo extends React.Component {
  constructor(props) {
   super(props)
  }
  mark = () => {
    PrintModal.mark(a => {
      console.log(a)
      if (a === 1) {
        //"缺纸----------";
        console.log("蓝牙打印机缺纸");
      } else if (a === 2) {
        //"开盖----------";
        console.log("蓝牙打印机开盖");
      } else if (a === 0) {
        this.drawAirlinesNew();
        //"打印机正常-------";
        console.log("打印成功");

      } else {
        console.log( "蓝牙打印机开盖");
      }
      PrintModal.disconnect();
    })
  }
  drawAirlinesNew = () => {
    //设置打印纸张大小(打印区域)的大小
    PrintModal.pageSetup(720, 586 );
    // 上边框
    PrintModal.drawLine(5, 20, 20, 570, 20, true);
    // 左边框
    PrintModal.drawLine(5, 20, 20, 20, 516, true);
    // 右边框
    PrintModal.drawLine(5, 570, 20, 570, 516, true);
    // 板箱号
    PrintModal.drawText(45, 60, "板箱号:", 3, 0, 0, false, false);

    // AKE00008MU
    PrintModal.drawText(222, 60, "AKE28653MU", 3, 0, 0, false, false);
    // 航司
    PrintModal.drawText(45, 255, "航司:", 3, 0, 0, false, false);
    // MU
    PrintModal.drawText(222, 230, "MU", 7, 0, 0, false, false);
    // 航班时间
    PrintModal.drawText(45, 450, "航班时间:", 3, 0, 0, false, false);
    // 01-07 11:31
    PrintModal.drawText(222, 450,"2019-11-02", 3, 0, 0, false, false);
    // 结束下边线
    PrintModal.drawLine(5, 20, 510, 570, 510, true);
    // ----------------------------------------------step 3 end----------------------------------------------------------------------------------------
    PrintModal.print(0, 0)
  }

  render(){
    return(
      <View>
        <Text onPress={()=>{
          PrintModal.connect(
            {macAddress:'00:42:69:06:58:75'},
              msg => {console.log(msg)},
              mark => {
              console.log(mark)
              if(!mark){
                console.log('打印机链接失败')
              }else{
                this.mark();
              }
              })
        }}>打印</Text>
      </View>
    )
  }
}
export default Demo

