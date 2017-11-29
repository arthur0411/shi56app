package com.base.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

public class HttpUtil {

	public static String get(String url, Map<String, Object> params) {
		StringBuilder sb = new StringBuilder();
		if (params != null) {
			url = url + "?";
			for (Entry<String, Object> e : params.entrySet()) {
				sb.append(e.getKey() + "=" + e.getValue().toString().trim() + "&");
			}
			url = url + sb.substring(0, sb.length() - 1);
		}

		HttpURLConnection con = null;
		try {
			URL u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod("GET");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}

		StringBuilder buffer = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String temp = null;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	public static String post(String url, Map<String, Object> params, Object content, String contentType) {
		StringBuilder sb = new StringBuilder();
		String p = "";
		if (params != null) {
			for (Entry<String, Object> e : params.entrySet()) {
				sb.append(e.getKey() + "=" + e.getValue().toString().trim() + "&");
			}
			p = sb.substring(0, sb.length() - 1);
		}

		HttpURLConnection con = null;
		try {
			URL u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type", contentType);
			OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
			osw.write(p.trim());
			if (content != null) osw.write(content.toString());
			osw.flush();
			osw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}

		StringBuilder buffer = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return buffer.toString();
	}

	public static String upload(String requestUrl, Map<String, Object> params, Map<String, String> attachments) {
		String twoHyphens = "--";
		String boundary = "RQdzAAihJq7Xp1kjraqf";
		String lineEnd = "\r\n";

		try {
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(20000 /* milliseconds */);
			conn.setConnectTimeout(20000 /* milliseconds */);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			//conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			//conn.setRequestProperty("Charsert", "UTF-8"); 
			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());

			if (params != null) {
				for (Entry<String, Object> entry : params.entrySet()) {
					dos.writeBytes(twoHyphens + boundary + lineEnd);
					dos.writeBytes("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + lineEnd);
					dos.writeBytes("Content-Type: text/plain; charset=UTF-8" + lineEnd);
					dos.writeBytes("Content-Length: " + entry.getValue().toString().length() + lineEnd);
					dos.writeBytes(lineEnd);
					dos.writeBytes(entry.getValue().toString() + lineEnd);
				}
			}

			if (attachments != null) {
				for (Entry<String, String> entry : attachments.entrySet()) {
					File file = new File(entry.getValue());
					dos.writeBytes(twoHyphens + boundary + lineEnd);
					dos.writeBytes("Content-Disposition: form-data; name=\"" + entry.getKey() + "\";filename=\"" + file.getName() + "\"" + lineEnd);
					dos.writeBytes("Content-Type: image/jpeg" + lineEnd);
					dos.writeBytes(lineEnd);

					BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file));
					int bytes = 0;
					byte[] buffer = new byte[1024];
					while ((bytes = stream.read(buffer)) != -1) {
						dos.write(buffer, 0, bytes);
					}
					dos.writeBytes(lineEnd);
					stream.close();
				}
			}

			dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
			dos.flush();
			dos.close();

			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String temp = null;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
				sb.append("\n");
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ajax跨域请求时需设置哪些来源允许跨域，*表示所有来源，可指定特定来源，如：http://192.168.0.77:8080/
	 *
	 * @param response
	 */
	public static void accessControlAllowOrigin(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
	}

	/**
	 * 根据浏览器类型对导出文件的文件名进行中文编码处理
	 *
	 * @param request
	 * @param filename
	 * @return
	 */
	public static String encodeFilename(HttpServletRequest request, String filename) {
		String agent = request.getHeader("USER-AGENT").toLowerCase();
		try {
			if (agent.contains("msie")) {
				filename = URLEncoder.encode(filename, "UTF-8");
			} else {
				// firefox/safari不转码
				filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}

}
