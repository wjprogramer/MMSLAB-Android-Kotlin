# Lab 14

## Getting Started

1. Create the file `app/services.build.properties`, and write your information by format of `app/services.build.sample.properties`

## [學習] `AndroidManifest.xml` 設定敏感資料

1. 新增檔案 `app/services.build.properties`
	存敏感資料
2. 由 `app/build.gradle` 讀取該檔案
3. `AndroidManifest.xml` 讀取 `app/build.gradle`

參考資料及關鍵資訊

- [如何讀檔案 (使用 `Properties`)](https://stackoverflow.com/questions/37101589/how-to-read-a-properties-files-and-use-the-values-in-project-gradle-script)
	```gradle
	def props = new Properties()
	file("build.properties").withInputStream { props.load(it) }
	```
- [如何讀檔案 (從檔案根目錄)](https://stackoverflow.com/questions/21999829/how-do-i-read-properties-defined-in-local-properties-in-build-gradle)
	```gradle
	Properties properties = new Properties()
	properties.load(project.rootProject.file('local.properties').newDataInputStream())
	def sdkDir = properties.getProperty('sdk.dir')
	def ndkDir = properties.getProperty('ndk.dir')
	```
-	[AndroidManifest.xml 讀取 BuildConfig 的值](https://stackoverflow.com/questions/28954071/how-can-i-access-a-buildconfig-value-in-my-androidmanifest-xml-file)	
	```gradle
	buildConfigField "long", "FACEBOOK_APP_ID", FACEBOOK_APP_ID
	```
	改為
	```gradle
	resValue "string", "FACEBOOK_APP_ID", FACEBOOK_APP_ID
	```
	> 但不適合存敏感資訊，因為是 res
- [AndroidManifest.xml 讀取 BuildConfig 的值](https://stackoverflow.com/questions/56041697/android-manifestplaceholders-from-gradle-properties)
	```gradle
	manifestPlaceholders = [host: "dev.mysite.com"]
	```
- [在 build.gradle 移除引號](https://stackoverflow.com/questions/14716281/java-properties-class-implementation-handles-double-single-quoted-values)
	```gradle
	String path = "/tmp/my.properties";
	Properties p = new Properties();
	p.load(new FileInputStream(new File(path)));

	String v = p.getProperty("key2");
	if((v.startsWith("\"") && v.endsWith("\"")) || 
	   (v.startsWith("\'") && v.endsWith("\'"))) {
	    v = v.substring(1, v.length()-1);
	}
	```
- [如何在 build.gradle 寫 method](https://stackoverflow.com/questions/27777591/how-to-define-and-call-custom-methods-in-build-gradle)
	```gradle
	ext.myMethod = { param1, param2 ->
	    // Method body here
	}
	```
	範例
	```gradle
	task myTask {
	    ext.myMethod = { param1, param2 ->
	        // Method body here
	    }

	    doLast {
	        myMethod(p1, p2) // This will resolve 'myMethod' defined in task
	    }
	}
	```