package ru.bestfoxy.econom.server.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import ru.bestfoxy.econom.client.ClientProxy;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class SyncConfigMessage implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<SyncConfigMessage, SyncConfigMessage> {
	
	public List<String> config;
	
	public SyncConfigMessage() {}
	
	public SyncConfigMessage(List<String> config) {
		this.config = config;
	}	

	public void fromBytes(ByteBuf buf) {
		config = new ArrayList();
		int l = buf.readInt();
		for (int i = 0; i < l; i++) {
			config.add(ByteBufUtils.readUTF8String(buf));
		}
	}
	

	public void toBytes(ByteBuf buf) {
		buf.writeInt(config.size());
		for (String s : config) {
			ByteBufUtils.writeUTF8String(buf, s);
		}
	}
	

	public SyncConfigMessage onMessage(SyncConfigMessage message, MessageContext ctx) {
		List<String> config = message.config;
		ClientProxy.loadConfig(config);
		return null;
	}
}
