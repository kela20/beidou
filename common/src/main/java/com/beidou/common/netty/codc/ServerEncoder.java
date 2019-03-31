package com.beidou.common.netty.codc;

import com.beidou.common.netty.model.ConstantValue;
import com.beidou.common.netty.model.Response;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * ������������
 * <pre>
 * ���ݰ���ʽ
 * +����----����+����-----����+����----����+����----����+����-----����+����-----����+
 * | ��ͷ          | ģ���        | �����       |  ״̬��    |  ����          |   ����       |
 * +����----����+����-----����+����----����+����----����+����-----����+����-----����+
 * </pre>
 * ��ͷ4�ֽ�
 * ģ���2�ֽ�short
 * �����2�ֽ�short
 * ����4�ֽ�(�������ݲ����ֽڳ���)
 */
public class ServerEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object in, ByteBuf out) throws Exception {
        Response response = (Response)(in);
        //��ͷ
        out.writeInt(ConstantValue.FLAG);
        //module
        out.writeShort(response.getModule());
        //cmd
        out.writeShort(response.getCmd());
        //״̬��
        out.writeInt(response.getStateCode());
        //����
        out.writeInt(response.getDataLength());
        //data
        if(response.getData() != null){
            out.writeBytes(response.getData());
        }
//        byte[] bytes = SerialUtil.encodes(in);
//        out.writeBytes(bytes);
    }
}
