package com.beidou.common.netty.codc;

import com.beidou.common.netty.model.ConstantValue;
import com.beidou.common.netty.model.Request;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * ������������
 * <pre>
 * ���ݰ���ʽ
 * +����----����+����-----����+����----����+����----����+����-----����+
 * | ��ͷ          | ģ���        | �����      |  ����        |   ����       |
 * +����----����+����-----����+����----����+����----����+����-----����+
 * </pre>
 * ��ͷ4�ֽ�
 * ģ���2�ֽ�short
 * �����2�ֽ�short
 * ����4�ֽ�(�������ݲ����ֽڳ���)
 *
 *
 */
public class ServerDecoder extends ByteToMessageDecoder {
    /**
     * ���ݰ���������
     */
    public static int BASE_LENTH = 4 + 2 + 2 + 4;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        int size = in.readableBytes();
        Object info=null;
        //�ɶ����ȱ�����ڻ�������
        if (size > ServerDecoder.BASE_LENTH) {
            //��ֹsocket�ֽ�������
            if(in.readableBytes() > 2048){
                in.skipBytes(in.readableBytes());
            }

            //��¼��ͷ��ʼ��index
            int beginReader;

            while(true){
                beginReader = in.readerIndex();
                in.markReaderIndex();
                if(in.readInt() == ConstantValue.FLAG){
                    break;
                }

                //δ������ͷ���Թ�һ���ֽ�
                in.resetReaderIndex();
                in.readByte();

                //�����ֱ�ò�����
                if(in.readableBytes() < BASE_LENTH){
                    //out.add(null);
                }
            }

            //ģ���
            short module = in.readShort();
            //�����
            short cmd = in.readShort();
            //����
            int length = in.readInt();

            //�ж��������ݰ������Ƿ���
            if(in.readableBytes() < length){
                //��ԭ��ָ��
                in.readerIndex(beginReader);
                //out.add(null);
            }

            //��ȡdata����
            byte[] data = new byte[length];
            in.readBytes(data);

            Request request = new Request();
            request.setModule(module);
            request.setCmd(cmd);
            request.setData(data);
            //�������´���
            out.add(request);
            /*byte[] bytes = SerialUtil.getByteFromBuf(in);
            Object info = SerialUtil.decode(bytes);
            out.add(info);*/
        }
    }
}
