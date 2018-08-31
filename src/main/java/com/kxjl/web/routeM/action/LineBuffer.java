package com.kxjl.web.routeM.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

import org.apache.http.conn.HttpInetSocketAddress;
import org.json.JSONObject;

public class LineBuffer {
	private int size;

	public LineBuffer(int size) {
		this.size = size;
	}

	public String readLine(InputStream input) throws IOException {
		int flag = 0;
		int index = 0;
		byte[] bts = new byte[this.size];
		int b;
		while (flag != 2 && (b = input.read()) != -1) {
			bts[index++] = (byte) b;
			if (b == '\r' && flag % 2 == 0) {
				flag++;
			} else if (b == '\n' && flag % 2 == 1) {
				flag++;
				if (flag == 2) {
					return new String(bts, 0, index - 2);
				}
			} else {
				flag = 0;
			}
			if (index == bts.length) {
				// 满了扩容
				byte[] newBts = new byte[bts.length * 2];
				System.arraycopy(bts, 0, newBts, 0, bts.length);
				bts = null;
				bts = newBts;
			}
		}
		return null;
	}

	private static void printbytes(byte[] btype, JSONObject jsonValue) {
		// 发送数据
		String value = jsonValue.toString();
		byte[] v_datapath = value.getBytes(Charset.forName("UTF-8"));
		byte[] buffResponse1 = new byte[4 + v_datapath.length];
		buffResponse1[0] = btype[0];// 0x09;
		buffResponse1[1] = btype[1];// 0x02;
		buffResponse1[2] = (byte) ((v_datapath.length >> 8) & 0xFF);
		buffResponse1[3] = (byte) (v_datapath.length & 0xFF);
		for (int i = 0; i < v_datapath.length; i++) {
			buffResponse1[4 + i] = v_datapath[i];
		}

		for (byte b : buffResponse1) {
			System.out.printf("%02X ", b);
		}

		try {

			SocketAddress ipAddress = new InetSocketAddress("127.0.0.1", 18000);
			SocketChannel sc = SocketChannel.open(ipAddress);
			ByteBuffer writeBuf = ByteBuffer.allocate(buffResponse1.length);
			writeBuf.put(buffResponse1);
			writeBuf.flip();
			sc.write( writeBuf);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		System.out.println(2 % 2);
		byte[] d = new byte[] { 0x7B, 0x22, 0x6D, 0x73, 0x67, 0x22, 0x3A, 0x22,
				0x64, 0x61, 0x74, 0x61, 0x20, 0x65, 0x72, 0x72, 0x6F, 0x72,
				0x21, 0x22, 0x7D };
		String k = "";
		try {
			k = new String(d, "utf-8");

			// System.out.println(k);

			JSONObject j = new JSONObject();
			j.put("id", "testroute1");

			printbytes(new byte[] { 0x09, 0x01 }, j);
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
