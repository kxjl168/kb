package com.kxjl.web.routeM.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.kxjl.tool.common.ByteIntChange;
import com.kxjl.tool.config.ConfigReader;
import com.kxjl.tool.utils.DateUtil;
import com.kxjl.web.device.model.Device;
import com.kxjl.web.device.model.Device.RouteFlag;
import com.kxjl.web.device.model.Device.RouteFreeStaus;


/**
 * 线程池处理路由器连接TCP长连接请求
 * 
 * @author Administrator
 * 
 */
public class RouteConnectSvr extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8823073680488148194L;

	private int port = 18000;
	private final int POOL_SIZE = 20;// 单个CPU线程池大小

	private static Logger logger = Logger.getLogger(RouteConnectSvr.class);

	// @Autowired
	// PcExportService pcExportService;

	@Override
	public void init(ServletConfig config) throws ServletException {

		// autowire起作用
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {

				try {
					String ROUTE_SK_PORT = ConfigReader.getInstance()
							.getProperty("ROUTE_SK_PORT");
					try {
						port = Integer.valueOf(ROUTE_SK_PORT);
					} catch (Exception e) {

					}
					SocketServer test = new SocketServer();
					test.initServer(port);
					test.listen();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
		thread.start();

		// 空闲路由器 重新分配
		new Thread(new RouteFreeStatusCheck()).start();

	}

	// boolean sendflag = true;

	/**
	 * Nio socket服务器，事件驱动方式，用线程池进行多线程处理读取事件
	 */
	public class SocketServer {
		// 线程池
		ExecutorService pool = Executors.newFixedThreadPool(Runtime
				.getRuntime().availableProcessors() * POOL_SIZE);
		// 通道管理器
		private Selector selector;

		/**
		 * 获得一个ServerSocket通道，并对该通道做一些初始化的工作
		 * 
		 * @param port
		 *            绑定的端口号
		 * @throws IOException
		 */
		public void initServer(int port) throws IOException {
			// 获得一个ServerSocket通道
			ServerSocketChannel serverChannel = ServerSocketChannel.open();
			// 设置通道为非阻塞
			serverChannel.configureBlocking(false);
			// 将该通道对应的ServerSocket绑定到port端口
			serverChannel.socket().bind(new InetSocketAddress(port));
			// 获得一个通道管理器
			this.selector = Selector.open();
			// 将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件,注册该事件后，
			// 当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞。
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
			logger.info("服务端启动成功！");
		}

		/**
		 * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
		 * 
		 * @throws IOException
		 */
		@SuppressWarnings("unchecked")
		public void listen() throws IOException {
			// 轮询访问selector
			while (true) {
				// 当注册的事件到达时，方法返回；否则,该方法会一直阻塞
				selector.select();
				// 获得selector中选中的项的迭代器，选中的项为注册的事件
				Iterator ite = this.selector.selectedKeys().iterator();
				while (ite.hasNext()) {
					SelectionKey key = (SelectionKey) ite.next();
					// 删除已选的key,以防重复处理
					ite.remove();
					// 客户端请求连接事件
					try {
						if (key.isAcceptable()) {
							ServerSocketChannel server = (ServerSocketChannel) key
									.channel();
							// 获得和客户端连接的通道
							SocketChannel channel = server.accept();
							// 设置成非阻塞
							channel.configureBlocking(false);
							// 在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限。
							channel.register(this.selector,
									SelectionKey.OP_READ);
							// 获得了可读的事件

							/*
							 * PcExportInfo pcInfo = new PcExportInfo();
							 * pcInfo.setMsgTime(System.currentTimeMillis());
							 * pcInfo.setSendInfo(false); pcInfo.setType(0);
							 * SocketClient.getInstance().putSocketInfo(channel,
							 * pcInfo);
							 */

							// 记录socket与路由器关系
							Device routeInfo = new Device();
						/*	routeInfo.setIp(channel.getRemoteAddress()
									.toString().substring(1,channel.getRemoteAddress()
									.toString().indexOf(":"))); // 路由器外网IP
*/							// 地市转化
							String city = "";// TODO
							routeInfo.setCity(city);
							routeInfo.setOnlinetime(DateUtil.getNowStr(""));
							routeInfo.setOfflinetime("");

							RouteSocketClient.getInstance().putSocketInfo(
									channel, routeInfo);

						} else if (key.isReadable()) {
							// 取消可读触发标记
							key.interestOps(key.interestOps()
									& (~SelectionKey.OP_READ));
							// 加入线程池
							pool.execute(new ThreadHandlerChannel(key));
						}
					} catch (CancelledKeyException ex) {
						continue;
					}
				}
			}
		}
	}

	/**
	 * 用多线程处理读取客户端发来的信息的事件
	 * 
	 * @param SelectionKey
	 * @throws IOException
	 */
	class ThreadHandlerChannel extends Thread {
		private SelectionKey key;

		ThreadHandlerChannel(SelectionKey key) {
			this.key = key;
		}

		@Override
		public void run() {
			// 服务器可读取消息:得到事件发生的Socket通道
			SocketChannel channel = (SocketChannel) key.channel();
			// 创建读取的缓冲区
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			// 把读取到的缓冲区内容存入到流内，到最后一次性取出来，建议定义自定义协议先传内容长度然后再传内容，就可以根据长度建立对应长度缓冲区然后读取数据
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				int size = 0;
				while ((size = channel.read(buffer)) > 0) {
					buffer.flip();
					baos.write(buffer.array(), 0, size);
					buffer.clear();
				}
				baos.close();
				if (baos.size() > 0) {
					byte[] buf = baos.toByteArray();
					int index = 0;
					int type = ByteIntChange.ByteTwoToInt(buf[index],
							buf[index + 1]);
					// 获取报文长度
					int length = ByteIntChange.ByteTwoToInt(buf[index + 2],
							buf[index + 3]);
					if (index + 4 + length != buf.length) {
						logger.info("数据长度不正确");
						JSONObject d = new JSONObject();
						d.put("msg", "data length error!");
						RouteSocketClient.getInstance().SendData(channel,
								new byte[] { 0x09, 0x00 }, d);
					} else {
						switch (type) {
						case 0x0901: {
							logger.debug("conn type:0x0901");

							// 上报路由器端唯一标识
							byte[] data_value = new byte[length];
							for (int i = 0; i < length; i++) {
								data_value[i] = buf[i + 4 + index];
							}
							String connect_info = new String(data_value);
							Device pcInfo = RouteSocketClient.getInstance()
									.getSocketInfo(channel);
							if (pcInfo != null) {
								// 解析json 字符串 获取国家、省、市信息
								try {
									logger.debug("connect_info" + connect_info);
									JSONObject params_json = new JSONObject(
											connect_info);

									// logger.debug("conn type:0x0901");

									String clientid = params_json
											.getString("id");

									// TODO 设置路由器信息
									pcInfo.setRouteid(clientid);
									pcInfo.setFree(RouteFreeStaus.Free
											.toString());
									pcInfo.setFlag(RouteFlag.UNASSIGN
											.toString());

									RouteSocketClient.getInstance()
											.putSocketInfo(channel, pcInfo);

									// 设置分配标示为分配中,
									// 准备分配中转服务器
									RouteSocketClient.getInstance()
											.assignTransServer(channel);

									logger.info("new route connecting: "
											+ clientid + " socket is "
											+ channel.socket().toString());
								} catch (JSONException e) {
									e.printStackTrace();
								}
							}

						}
							break;
						case 0x0902: {
							/*
							 * PcExportInfo pcInfo = SocketClient.getInstance()
							 * .getSocketInfo(channel); if (pcInfo != null) { //
							 * 修改虚拟机与网络出口关联关系 SocketClient.getInstance()
							 * .CheckExportInImageMap(pcInfo); }
							 */
						}
							break;
						case 0x0903: {
							// 设备心跳信息
							/*
							 * PcExportInfo pcInfo = SocketClient.getInstance()
							 * .getSocketInfo(channel); if (pcInfo != null) { //
							 * pcInfo.setPcUid(pc_uid); logger.info("PCConnect "
							 * + pcInfo.getUid() + "接收到心跳信息, key is " +
							 * channel.socket().toString());
							 * pcInfo.setMsgTime(System.currentTimeMillis()); //
							 * pcClient.put(channel, pcInfo);
							 * SocketClient.getInstance().putSocketInfo(
							 * channel, pcInfo); // 接收到Ping报文发送响应 byte[]
							 * buffResponse = new byte[4]; buffResponse[0] =
							 * 0x09; buffResponse[1] = 0x04; buffResponse[2] =
							 * 0x00; buffResponse[3] = 0x00; ByteBuffer writeBuf
							 * = ByteBuffer .allocate(buffResponse.length);
							 * writeBuf.put(buffResponse); writeBuf.flip();
							 * channel.write(writeBuf); }
							 */
						}
							break;
						default:
							break;
						}
					}
				}
				if (size == -1) {
					logger.debug("客户端断开。。。");
					channel.close();
					RouteSocketClient.getInstance().disconnect(channel);

				} else {
					// 没有可用字节,继续监听OP_READ
					key.interestOps(key.interestOps() | SelectionKey.OP_READ);
					key.selector().wakeup();
				}
			} catch (Exception e) {
				// logger.error(e.getLocalizedMessage());
				if (e.getLocalizedMessage().contains("远程主机强迫关闭了一个现有的连接")) {
					logger.debug("客户端断开...");

					RouteSocketClient.getInstance().disconnect(channel);
				} else {
					try {
						logger.debug("客户端data error 断开...");
						JSONObject d = new JSONObject();
						d.put("msg", "data error!");
						RouteSocketClient.getInstance().SendData(channel,
								new byte[] { 0x09, 0x00 }, d);

						RouteSocketClient.getInstance().disconnect(channel);
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
		}
	}

	/**
	 * 检测 是否 有空闲路由器，重新分配中转服务器
	 * 
	 * @author Administrator
	 * 
	 */
	public class RouteFreeStatusCheck implements Runnable {

		RouteFreeStatusCheck() {
		};

		public void run() {
			while (true) {
				RouteSocketClient.getInstance().checkFreeRoute();

				try {
					Thread.sleep(120 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}

	/**
	 * unicode 转换成 中文
	 * 
	 * @param theString
	 * @return
	 */
	public static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException(
									"Malformed      encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't') {
						aChar = '\t';
					} else if (aChar == 'r') {
						aChar = '\r';
					} else if (aChar == 'n') {
						aChar = '\n';
					} else if (aChar == 'f') {
						aChar = '\f';
					}
					outBuffer.append(aChar);
				}
			} else {
				outBuffer.append(aChar);
			}
		}
		return outBuffer.toString();
	}
}
