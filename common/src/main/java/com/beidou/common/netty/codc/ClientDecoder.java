package com.beidou.common.netty.codc;

import com.beidou.common.netty.model.ConstantValue;
import com.beidou.common.netty.model.Response;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class ClientDecoder extends ByteToMessageDecoder {
    /**
     * ���ݰ���������
     */
    public static int BASE_LENTH = 4 + 2 + 2 + 4;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        //�ɶ����ȱ�����ڻ�������
        if(in.readableBytes() >= BASE_LENTH){

            //��¼��ͷ��ʼ��index
            int beginReader = in.readerIndex();

            while(true){
                if(in.readInt() == ConstantValue.FLAG){
                    break;
                }
            }

            //ģ���
            short module = in.readShort();
            //�����
            short cmd = in.readShort();
            //״̬��
            int stateCode = in.readInt();
            //����
            int length = in.readInt();

            if(in.readableBytes() < length){
                //��ԭ��ָ��
                in.readerIndex(beginReader);
                out.add(null);
            }

            byte[] data = new byte[length];
            in.readBytes(data);

            Response response = new Response();
            response.setModule(module);
            response.setCmd(cmd);
            response.setStateCode(stateCode);
            response.setData(data);

            //�������´���
            out.add(response);

        }
    }
}
