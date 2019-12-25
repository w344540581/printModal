/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React from 'react';
import {
  SafeAreaView,
  StyleSheet,
  ScrollView,
  View,
  Text,
  StatusBar,
} from 'react-native';

import {
  Header,
  LearnMoreLinks,
  Colors,
  DebugInstructions,
  ReloadInstructions,
} from 'react-native/Libraries/NewAppScreen';
import PrintModal from './PrintExample';

const App: () => React$Node = () => {

//标志
function mark(){
    PrintModal.mark(a => {
        if (a == 1) {
            //"缺纸----------";
            console.log("蓝牙打印机缺纸");
        } else if (a == 2) {
            //"开盖----------";
            console.log("蓝牙打印机开盖");
        } else if (a == 0) {
             this.drawAirlinesNew();
            //"打印机正常-------";
           console.log("打印成功");

        } else {
            console.log( "蓝牙打印机开盖");
        }
    })
}


  return (
    <>
      <View>
          <Text
                onPress={() => {
                  PrintModal.connect({macAddress: '00:42:69:06:58:75'},msg => {console.log(msg),(mark) => {if(!mark){console.log('蓝牙打印机链接失败')}else{}}});
                }}>
          打印
        </Text>
      </View>
    </>
  );
};

const styles = StyleSheet.create({
  scrollView: {
    backgroundColor: Colors.lighter,
  },
  engine: {
    position: 'absolute',
    right: 0,
  },
  body: {
    backgroundColor: Colors.white,
  },
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
  },
  sectionTitle: {
    fontSize: 24,
    fontWeight: '600',
    color: Colors.black,
  },
  sectionDescription: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
    color: Colors.dark,
  },
  highlight: {
    fontWeight: '700',
  },
  footer: {
    color: Colors.dark,
    fontSize: 12,
    fontWeight: '600',
    padding: 4,
    paddingRight: 12,
    textAlign: 'right',
  },
});

export default App;
