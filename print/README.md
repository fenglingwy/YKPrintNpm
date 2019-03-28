
# react-native-print

## Getting started

`$ npm install react-native-print --save`

### Mostly automatic installation

`$ react-native link react-native-print`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.yiako.rf.print.RNPrintPackage;` to the imports at the top of the file
  - Add `new RNPrintPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-print'
  	project(':react-native-print').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-print/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-print')
  	```


## Usage
```javascript
import RNPrint from 'react-native-print';

// TODO: What to do with the module?
RNPrint;
```
  