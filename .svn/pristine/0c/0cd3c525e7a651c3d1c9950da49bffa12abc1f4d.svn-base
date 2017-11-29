package com.flf.util.push;

import org.json.JSONArray;
import org.json.JSONObject;

import com.flf.util.push.android.AndroidBroadcast;
import com.flf.util.push.android.AndroidCustomizedcast;
import com.flf.util.push.android.AndroidFilecast;
import com.flf.util.push.android.AndroidGroupcast;
import com.flf.util.push.android.AndroidUnicast;
import com.flf.util.push.ios.IOSBroadcast;
import com.flf.util.push.ios.IOSCustomizedcast;
import com.flf.util.push.ios.IOSFilecast;
import com.flf.util.push.ios.IOSGroupcast;
import com.flf.util.push.ios.IOSUnicast;

/**
 * APP推送消息工具
 * 
 * @author SevenWong<br>
 * @date 2016年6月20日下午3:16:19
 */
public class APPPushUtil {
	private static String APPKEY_IOS = "57666c24e0f55a4440003231";
	private static String APP_MASTERSECRET_IOS = "ykadss5vxwsx8reic1up8scw7vf8j7pv";
	private static PushClient client = new PushClient();

	/**
	 * 广播(broadcast): 向安装该App的所有设备发送消息。
	 * 
	 * @author SevenWong<br>
	 * @date 2016年6月20日下午3:14:45
	 * @throws Exception
	 */
	public void sendAndroidBroadcast() throws Exception {
		AndroidBroadcast broadcast = new AndroidBroadcast(APPKEY_IOS, APP_MASTERSECRET_IOS);
		broadcast.setTicker("Android broadcast ticker");
		broadcast.setTitle("中文的title");
		broadcast.setText("Android broadcast text");
		broadcast.goAppAfterOpen();
		broadcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		// TODO Set 'production_mode' to 'false' if it's a test device.
		// For how to register a test device, please see the developer doc.
		broadcast.setProductionMode();
		// Set customized fields
		broadcast.setExtraField("test", "helloworld");
		client.send(broadcast);
	}

	/**
	 * 单播(unicast): 向指定的设备发送消息，包括向单个device_token或者单个alias发消息。
	 * 
	 * @author SevenWong<br>
	 * @date 2016年6月20日下午3:14:58
	 * @throws Exception
	 */
	public void sendAndroidUnicast() throws Exception {
		AndroidUnicast unicast = new AndroidUnicast(APPKEY_IOS, APP_MASTERSECRET_IOS);
		// TODO Set your device token
		unicast.setDeviceToken("your device token");
		unicast.setTicker("Android unicast ticker");
		unicast.setTitle("中文的title");
		unicast.setText("Android unicast text");
		unicast.goAppAfterOpen();
		unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		// TODO Set 'production_mode' to 'false' if it's a test device.
		// For how to register a test device, please see the developer doc.
		unicast.setProductionMode();
		// Set customized fields
		unicast.setExtraField("test", "helloworld");
		client.send(unicast);
	}

	/**
	 * 组播(groupcast): 向满足特定条件的设备集合发送消息，<br>
	 * 例如:"特定版本"、"特定地域"等。友盟消息推送所支持的维度筛选和友盟统计分析所提供的数据展示维度是一致的，后台数据也是打通的
	 * 
	 * @author SevenWong<br>
	 * @date 2016年6月20日下午3:15:23
	 * @throws Exception
	 */
	public void sendAndroidGroupcast() throws Exception {
		AndroidGroupcast groupcast = new AndroidGroupcast(APPKEY_IOS, APP_MASTERSECRET_IOS);
		/*
		 * TODO Construct the filter condition: "where": { "and": [
		 * {"tag":"test"}, {"tag":"Test"} ] }
		 */
		JSONObject filterJson = new JSONObject();
		JSONObject whereJson = new JSONObject();
		JSONArray tagArray = new JSONArray();
		JSONObject testTag = new JSONObject();
		JSONObject TestTag = new JSONObject();
		testTag.put("tag", "test");
		TestTag.put("tag", "Test");
		tagArray.put(testTag);
		tagArray.put(TestTag);
		whereJson.put("and", tagArray);
		filterJson.put("where", whereJson);
		System.out.println(filterJson.toString());

		groupcast.setFilter(filterJson);
		groupcast.setTicker("Android groupcast ticker");
		groupcast.setTitle("中文的title");
		groupcast.setText("Android groupcast text");
		groupcast.goAppAfterOpen();
		groupcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		// TODO Set 'production_mode' to 'false' if it's a test device.
		// For how to register a test device, please see the developer doc.
		groupcast.setProductionMode();
		client.send(groupcast);
	}

	/**
	 * 自定义播(customizedcast): 开发者通过自有的alias进行推送,
	 * 可以针对单个或者一批alias进行推送，也可以将alias存放到文件进行发送。
	 * 
	 * @author SevenWong<br>
	 * @date 2016年6月20日下午3:17:20
	 * @throws Exception
	 */
	public void sendAndroidCustomizedcast() throws Exception {
		AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(APPKEY_IOS, APP_MASTERSECRET_IOS);
		// TODO Set your alias here, and use comma to split them if there are
		// multiple alias.
		// And if you have many alias, you can also upload a file containing
		// these alias, then
		// use file_id to send customized notification.
		customizedcast.setAlias("alias", "alias_type");
		customizedcast.setTicker("Android customizedcast ticker");
		customizedcast.setTitle("中文的title");
		customizedcast.setText("Android customizedcast text");
		customizedcast.goAppAfterOpen();
		customizedcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		// TODO Set 'production_mode' to 'false' if it's a test device.
		// For how to register a test device, please see the developer doc.
		customizedcast.setProductionMode();
		client.send(customizedcast);
	}

	public void sendAndroidCustomizedcastFile() throws Exception {
		AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(APPKEY_IOS, APP_MASTERSECRET_IOS);
		// TODO Set your alias here, and use comma to split them if there are
		// multiple alias.
		// And if you have many alias, you can also upload a file containing
		// these alias, then
		// use file_id to send customized notification.
		String fileId = client.uploadContents(APPKEY_IOS, APP_MASTERSECRET_IOS, "aa" + "\n" + "bb" + "\n" + "alias");
		customizedcast.setFileId(fileId, "alias_type");
		customizedcast.setTicker("Android customizedcast ticker");
		customizedcast.setTitle("中文的title");
		customizedcast.setText("Android customizedcast text");
		customizedcast.goAppAfterOpen();
		customizedcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		// TODO Set 'production_mode' to 'false' if it's a test device.
		// For how to register a test device, please see the developer doc.
		customizedcast.setProductionMode();
		client.send(customizedcast);
	}

	/**
	 * 文件播(filecast): 开发者将批量的device_token或者alias存放到文件, 通过文件ID进行消息发送。
	 * 
	 * @author SevenWong<br>
	 * @date 2016年6月20日下午3:16:04
	 * @throws Exception
	 */
	public void sendAndroidFilecast() throws Exception {
		AndroidFilecast filecast = new AndroidFilecast(APPKEY_IOS, APP_MASTERSECRET_IOS);
		// TODO upload your device tokens, and use '\n' to split them if there
		// are multiple tokens
		String fileId = client.uploadContents(APPKEY_IOS, APP_MASTERSECRET_IOS, "aa" + "\n" + "bb");
		filecast.setFileId(fileId);
		filecast.setTicker("Android filecast ticker");
		filecast.setTitle("中文的title");
		filecast.setText("Android filecast text");
		filecast.goAppAfterOpen();
		filecast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		client.send(filecast);
	}

	/**
	 * 广播(broadcast): 向安装该App的所有设备发送消息。
	 * 
	 * @author SevenWong<br>
	 * @date 2016年6月20日下午3:36:21
	 * @param alert
	 *            推送的消息内容
	 * @throws Exception
	 */
	public static boolean sendIOSBroadcast(String alert) throws Exception {
		IOSBroadcast iosBroadcast = new IOSBroadcast(APPKEY_IOS, APP_MASTERSECRET_IOS);
		iosBroadcast.setAlert(alert);
		return client.send(iosBroadcast);
	}

	/**
	 * 单播(unicast): 向指定的设备发送消息，包括向单个device_token或者单个alias发消息。
	 * 
	 * @author SevenWong<br>
	 * @date 2016年6月20日下午3:16:40
	 * @throws Exception
	 */
	public static boolean sendIOSUnicast(String deviceToken, String alert) throws Exception {
		IOSUnicast unicast = new IOSUnicast(APPKEY_IOS, APP_MASTERSECRET_IOS);
		unicast.setDeviceToken(deviceToken);
		unicast.setAlert(alert);
		return client.send(unicast);
	}

	/**
	 * 组播(groupcast): 向满足特定条件的设备集合发送消息，<br>
	 * 例如:"特定版本"、"特定地域"等。友盟消息推送所支持的维度筛选和友盟统计分析所提供的数据展示维度是一致的，后台数据也是打通的
	 * 
	 * @author SevenWong<br>
	 * @date 2016年6月20日下午3:16:22
	 * @throws Exception
	 */
	public static boolean sendIOSGroupcast(String alert, String tag) throws Exception {
		IOSGroupcast groupcast = new IOSGroupcast(APPKEY_IOS, APP_MASTERSECRET_IOS);
		JSONObject filterJson = new JSONObject();
		JSONObject whereJson = new JSONObject();
		JSONArray tagArray = new JSONArray();
		JSONObject testTag = new JSONObject();
		testTag.put("tag", tag);
		tagArray.put(testTag);
		whereJson.put("and", tagArray);
		filterJson.put("where", whereJson);

		// Set filter condition into rootJson
		groupcast.setFilter(filterJson);
		groupcast.setAlert(alert);
		return client.send(groupcast);
	}

	/**
	 * 自定义播(customizedcast): 开发者通过自有的alias进行推送,
	 * 可以针对单个或者一批alias进行推送，也可以将alias存放到文件进行发送。
	 * 
	 * @author SevenWong<br>
	 * @date 2016年6月20日下午3:17:14
	 * @throws Exception
	 */
	public void sendIOSCustomizedcast() throws Exception {
		IOSCustomizedcast customizedcast = new IOSCustomizedcast(APPKEY_IOS, APP_MASTERSECRET_IOS);
		// TODO Set your alias and alias_type here, and use comma to split them
		// if there are multiple alias.
		// And if you have many alias, you can also upload a file containing
		// these alias, then
		// use file_id to send customized notification.
		customizedcast.setAlias("alias", "alias_type");
		customizedcast.setAlert("IOS 个性化测试");
		customizedcast.setBadge(0);
		customizedcast.setSound("default");
		// TODO set 'production_mode' to 'true' if your app is under production
		// mode
		customizedcast.setTestMode();
		client.send(customizedcast);
	}

	/**
	 * 文件播(filecast): 开发者将批量的device_token或者alias存放到文件, 通过文件ID进行消息发送。
	 * 
	 * @author SevenWong<br>
	 * @date 2016年6月20日下午3:16:10
	 * @throws Exception
	 */
	public void sendIOSFilecast() throws Exception {
		IOSFilecast filecast = new IOSFilecast(APPKEY_IOS, APP_MASTERSECRET_IOS);
		// TODO upload your device tokens, and use '\n' to split them if there
		// are multiple tokens
		String fileId = client.uploadContents(APPKEY_IOS, APP_MASTERSECRET_IOS, "aa" + "\n" + "bb");
		filecast.setFileId(fileId);
		filecast.setAlert("IOS 文件播测试");
		filecast.setBadge(0);
		filecast.setSound("default");
		// TODO set 'production_mode' to 'true' if your app is under production
		// mode
		filecast.setTestMode();
		client.send(filecast);
	}

	public static void main(String[] args) {
		// TODO set your appkey and master secret here
		try {
			/*
			 * TODO these methods are all available, just fill in some fields
			 * and do the test demo.sendAndroidCustomizedcastFile();
			 * demo.sendAndroidBroadcast(); demo.sendAndroidGroupcast();
			 * demo.sendAndroidCustomizedcast(); demo.sendAndroidFilecast();
			 * demo.sendAndroidUnicast();
			 * 
			 * demo.sendIOSBroadcast(); demo.sendIOSUnicast();
			 * demo.sendIOSGroupcast(); demo.sendIOSCustomizedcast();
			 * demo.sendIOSFilecast();
			 */
			APPPushUtil.sendIOSGroupcast("IOS 组播测试", "预备会员");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
